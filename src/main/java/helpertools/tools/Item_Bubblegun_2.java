package helpertools.tools;

import cpw.mods.fml.relauncher.Side;
import helpertools.Common_Registry;
import helpertools.HelpTab;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ItemFluidContainer;

public class Item_Bubblegun_2 extends ItemFluidContainer{

	public Item_Bubblegun_2(int itemID, int capacity) {
		super(itemID, capacity);
		
		setTextureName("helpertools:Bubblegun_2");
		setCreativeTab(HelpTab.HelperTools);
		this.setUnlocalizedName("bubblegun_2");
		
		this.capacity = capacity;
	}
	
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
		//this.isFull = Blocks.air;
		//this.get
		
	}
	
	public ItemStack onItemRightClick(ItemStack tool, World world, EntityPlayer player)
    {
		if (world.isRemote){
			return tool;
		}
		if(player.isSneaking()){
			
		MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
		int x = mop.blockX;
        int y = mop.blockY;
        int z = mop.blockZ;
        
		Block block = world.getBlock(x, y, z);
		
		if(FluidRegistry.lookupFluidForBlock(block) != null){		
		Fluid picked = FluidRegistry.lookupFluidForBlock(block);
		
		//String pickedname = FluidRegistry.getDefaultFluidName(picked);
		String pickedname = FluidRegistry.getFluidName(picked);
		
		FluidStack pickedstack = FluidRegistry.getFluidStack(pickedname, 1000);
		
		ChatComponentTranslation whatfluid = new ChatComponentTranslation(
				"What fluid? : " + pickedname);
		(player).addChatComponentMessage(whatfluid);
		
		
		this.fill(tool, pickedstack, true);
		
		announce_Fluid_Amount(tool, player);
		}
		
		
		FluidStack contained = getFluid(tool);
		
		//String containedname = null;
		if(contained != null){
			String containedname = FluidRegistry.getFluidName(contained);
			//containedname = contained.getUnlocalizedName();}
		
			ChatComponentTranslation whatcontained = new ChatComponentTranslation(
					"What contained? : " + containedname);
			(player).addChatComponentMessage(whatcontained);
		
		
		}
		}
		if(!player.isSneaking()){		
		MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
		int x = mop.blockX;
        int y = mop.blockY;
        int z = mop.blockZ;
        
        if(getFluid(tool) != null){
        FluidStack fluidstack =  getFluid(tool);
        Fluid fluid = fluidstack.getFluid();
        Block block = fluid.getBlock();
		//world.setBlock(p_147449_1_, p_147449_2_, p_147449_3_, p_147449_4_)
		world.setBlock(x, y, z, block, 0, 3);
        }
        
        this.drain(tool, 1000, true);
		announce_Fluid_Amount(tool, player);
			
		}
		
		
		return tool;
    
    }
	
	
	
	/** Handler to delegate how much fluid is present **/
	public int return_Fluid_Amount (ItemStack tool){
		int currentAmount = 0;
		
		if (tool.stackTagCompound == null) {
			tool.setTagCompound(new NBTTagCompound());
			}	
		 try{
			 FluidStack stack = FluidStack.loadFluidStackFromNBT(tool.stackTagCompound.getCompoundTag("Fluid"));
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
