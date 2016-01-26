package helpertools.tools;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import helpertools.Mod_Registry;
import helpertools.HelpTab;
import helpertools.blocks.tile_entities.TileEntityTranscriber;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.ItemFluidContainer;

public class Item_Bubblegun_2 extends ItemFluidContainer{

	public Item_Bubblegun_2(int itemID, int capacity) {
		super(itemID, capacity);
		
		setTextureName("helpertools:Bubblegun_2");
		setCreativeTab(HelpTab.HelperTools);
		this.setUnlocalizedName("bubblegun_2");
		
		this.capacity = capacity;
	}
	protected static Random growrand = new Random();
	
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
		//this.isFull = Blocks.air;
		//this.get
		
	}
	
	public ItemStack onItemRightClick(ItemStack tool, World world, EntityPlayer player)
    {
		if (world.isRemote){
			return tool;
		}
		MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
		if(mop == null){
			return tool;
		}
		int x = mop.blockX;
        int y = mop.blockY;
        int z = mop.blockZ;
        
		/** Sneaking functions, withdraw etc. **/
		if(player.isSneaking()){	
		Block block = world.getBlock(x, y, z);
				
		if (bucket_Fluid(block, tool, world, x,y,z)){
			return tool;
		}
		take_Fluid(world, tool, x, y, z);		
		
		
		announce_Fluid_contained(tool, player);
		
		}
		/** defualt functions, place etc. **/
		if(!player.isSneaking()){
        
        if(getFluid(tool) != null){
        FluidStack fluidstack =  getFluid(tool);
        Fluid fluid = fluidstack.getFluid();
        Block block = fluid.getBlock();
		//world.setBlock(p_147449_1_, p_147449_2_, p_147449_3_, p_147449_4_)
		//world.setBlock(x, y, z, block, 0, 3);
        

        if(insert_Fluid(world, fluid, tool, x, y, z)){
        	return tool;
        }
        
        
		Place_Fluid(world,tool, block, x,y,z);
        }
        
       
		//announce_Fluid_Amount(tool, player);
              
			
		}
		
		
		return tool;
    
    }
	
	//================================================================================================================//
	
	

	/** Place fluid in the world
	 * @param tool **/
	public void Place_Fluid (World world, ItemStack tool, Block block, int x, int y, int z ){
		
		world.setBlock(x, y, z, block, 0, 3);
		
		//setTBlock(thestaff, theblock.getBlock(x1, y1, z1).getIdFromBlock(theblock.getBlock(x1, y1, z1)));
		//setTMeta(thestaff,theblock.getBlockMetadata(x1, y1, z1)); 
		
		int crackid = block.getIdFromBlock(block);
		int crackmeta = world.getBlockMetadata(x, y, z);
		//int crackmeta = 
		
		//int crackid = (getTBlock(thestaff));
        //int crackmeta = (returnTMeta(thestaff));
        String particle = "blockcrack_" + crackid + "_" + crackmeta;
		for (int pl = 0; pl < 5; ++pl)
		{
		float f = (this.growrand.nextFloat() - .2F) * 1.4F;
        float f1 = (this.growrand .nextFloat() - .2F) * 1.4F;
        float f2 = (this.growrand .nextFloat() - .2F) * 1.4F;
		world.spawnParticle(particle, x+f, y+f1+.3, z+f2, 0, 0, 0);  
		
		 this.drain(tool, 1000, true);
		
		}
	}
	
	/** If applicable attempts to insert a fluid into a target tank **/
	public Boolean insert_Fluid(World world, Fluid fluid, ItemStack tool, int x,int y,int z){
		//tile entity support?
				//if(world.getBlock(x, y, z) == Common_Registry.TranscriberBlock){
					//TileEntityTranscriber tile = (TileEntityTranscriber)world.getTileEntity(i1, j1, k1);  
					TileEntity localTileEntity = world.getTileEntity(x,y,z);
					if(localTileEntity != null){
						if (localTileEntity instanceof IFluidHandler){
							if(((IFluidHandler) localTileEntity).canFill(ForgeDirection.NORTH, fluid)){
								FluidStack fillstack = new FluidStack(fluid, 1000);
								((IFluidHandler) localTileEntity).fill(ForgeDirection.NORTH, fillstack, true);
								this.drain(tool, 1000, true);
								return true;
							}
							//FillBucketEvent event = new FillBucketEvent(player, tool, world, mop);
				            //if (MinecraftForge.EVENT_BUS.post(event)) {	 return tool;  }

						}
					}
					return false;
					
					//if (world.getBlock(x, y, z))
		        
	}
	
	/** withdraw a fluid from a tank **/
	public Boolean take_Fluid(World world, ItemStack tool, int x,int y,int z){
		
		TileEntity localTileEntity = world.getTileEntity(x,y,z);
		if(localTileEntity != null){
			if (localTileEntity instanceof IFluidHandler){				
				//fluid =  ((IFluidHandler) localTileEntity).getTankInfo(ForgeDirection.NORTH);
				
				//if(((IFluidHandler) localTileEntity).canDrain(ForgeDirection.NORTH, fluid)){
					FluidStack localFluidStack = null;
					localFluidStack = ((IFluidHandler) localTileEntity).drain(ForgeDirection.NORTH, 1000, false);
					if (localFluidStack == null){
						return false;
					}
					
				    Fluid TE_Fluid = localFluidStack.getFluid();
				    if(getFluid(tool) != null){
				        FluidStack fluidstack =  getFluid(tool);
				        Fluid fluid = fluidstack.getFluid();
				        if(TE_Fluid != fluid){
				        	return false;}
				        }					
					
					//FluidStack fillstack = new FluidStack(fluid, 1000);
					((IFluidHandler) localTileEntity).drain(ForgeDirection.NORTH, localFluidStack, true);
					this.fill(tool, localFluidStack, true);
					return true;
				}				
			//}
		}
		return false;
	}
	
	/** scoop up a fluid in the world **/
	public boolean bucket_Fluid(Block block, ItemStack tool, World world, int x, int y, int z){

		if(FluidRegistry.lookupFluidForBlock(block) != null){		
		Fluid picked = FluidRegistry.lookupFluidForBlock(block);
		
		//String pickedname = FluidRegistry.getDefaultFluidName(picked);
		String pickedname = FluidRegistry.getFluidName(picked);
		
		FluidStack pickedstack = FluidRegistry.getFluidStack(pickedname, 1000);
		/**
		ChatComponentTranslation whatfluid = new ChatComponentTranslation(
				"What fluid? : " + pickedname);
		(player).addChatComponentMessage(whatfluid);
		**/
		
		this.fill(tool, pickedstack, true);
		world.setBlock(x, y, z, Blocks.air, 0, 2);
		return true;
		}
		return false;
		
	}	
	// To Do's
	
	
	
	//==========================================================================================//
	
	/** Handler to delegate how much fluid is present **/
	public int return_Fluid_Amount (ItemStack tool){
		int currentAmount = 0;
		
		if (tool.stackTagCompound == null) {
			tool.setTagCompound(new NBTTagCompound());
			}	
		 try{
			 FluidStack stack = FluidStack.loadFluidStackFromNBT(tool.stackTagCompound.getCompoundTag("Fluid"));
			 //FluidStack stack = getFluid(tool);
				currentAmount = stack.amount;
		      }
		      catch(NullPointerException exception){
		    	  System.out.println("------------- SOMETHING BROKE ----------");
		    	  System.out.println("------------- SOMETHING BROKE ----------");
		    	  System.out.println("------------- SOMETHING BROKE ----------");
		      }
		
		
		return currentAmount;
	}
		
	
	/** Announce how much fluid is contained **/
	public void announce_Fluid_Amount (ItemStack tool, EntityPlayer player){
				
		int cap = this.getCapacity(tool);		
		int capy = return_Fluid_Amount(tool);
		
		ChatComponentTranslation capacity = new ChatComponentTranslation(
				"Capacity? : " + cap + " / " + capy);
		(player).addChatComponentMessage(capacity);
	}
	
	public String what_fluidname(ItemStack tool){
		String containedname = "empty";
		FluidStack contained = getFluid(tool);
		containedname = FluidRegistry.getFluidName(contained);	
		return containedname;
	}
	
	/** anounce contained fluids **/
	public void announce_Fluid_contained(ItemStack tool, EntityPlayer player) {
		FluidStack contained = getFluid(tool);
		Fluid fluid;
		long color = 0;
		try{
		fluid = contained.getFluid();		
		color = fluid.getColor();}
		catch(NullPointerException exception){
			System.out.println("------------- COLORS BROKE ----------");
			System.out.println("------------- COLORS BROKE ----------");
			System.out.println("------------- COLORS BROKE ----------");
		}
		
		//String containedname = null;
		if(contained != null){
			String containedname = FluidRegistry.getFluidName(contained);
			//containedname = contained.getUnlocalizedName();}
		
			ChatComponentTranslation whatcontained = new ChatComponentTranslation(
					"What contained? : " + containedname);
			(player).addChatComponentMessage(whatcontained);
			ChatComponentTranslation whatcolor = new ChatComponentTranslation(
					"What color? : " + color);
			(player).addChatComponentMessage(whatcolor);	
		}		
		
		
		
	}
	
	
	/* IFluidContainerItem */
    public static FluidStack getFluid_2(ItemStack container)
    {
        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Fluid"))
        {
            return null;
        }
        return FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));
    } 

}
