package helpertools.Common.Tools;

import helpertools.Main;
import helpertools.Common.ConfigurationFactory;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import helpertools.Utils.InventoryUtil;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemStaffofTransformation extends ToolBase_Default
{
    public ItemStaffofTransformation(ToolMaterial material, String unlocalizedName)
    {
    	super (material);
        this.maxStackSize = 1;  
        setUnlocalizedName(unlocalizedName);
        setCreativeTab(HelpTab.HelperTools);
    }
    
    protected static Random growrand = new Random();
    
    //Adds fancy flavor text for item
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	par3List.add(EnumChatFormatting.ITALIC + "swaps blocks");
    	if (stack.hasTagCompound()){
    if(whatBlockString(stack) != "null" && whatModeString(stack)!= "null"){
    	par3List.add(whatBlockString(stack) + whatModeString(stack)+ " mode");
    }}}
    
    
    /**Declarations, that are used for processing later **/
    //
    @Override    
    public String whatModeString(ItemStack stack){	  
  		String modestring = "null";
  		
  		if (getMode(stack) == 2){
  			modestring = "Single";
  		}
  		else if(getMode(stack) == 4){
  			modestring = "Wall";
  		}
  		else if(getMode(stack) == 6){
  			modestring = "Mass";
  		}
  		else{
  			modestring = "null";
  		}  		
  		
  		
  		return modestring;
  	};
   
	
	private Block Gblock = Blocks.air;	
	private int Gmeta = 0;	
	
	
	/** Custom loop, during swings that allows modes to change **/
	//Checks if sneaking and which mode it currently is in to change further
	//

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
		
		
		if (getOffMode(stack)== 0)
   		{
		   setOffMode(stack, 2);
   		}
		if (!entityLiving.worldObj.isRemote) {
		if (entityLiving.isSneaking()&& getOffMode(stack)== 2)
    	{ 
			//Chat Messege and mode switcher
			setMode(stack, getMode(stack)+2);
			if (getMode(stack) > 6){
				setMode(stack, 2);
			}
			String Messy = "";
			double loud1 = 3;
			double loud2 = 0.3;
			
			switch(getMode(stack)){
			case 2: Messy = whatModeString(stack) + " Mode";
			break;
			case 4: Messy = whatModeString(stack) + " Mode";
					loud1 = 0.3;
					loud2 = 3;
			break;
			case 6: Messy = whatModeString(stack) + " Mode";
			break;
			default:
			break;
			}
			entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", (float)(loud1), (float)(loud2));
			//config hook
			
			if(ConfigurationFactory.ToolModeMesseges){	    
			ChatComponentTranslation chatmessy = new ChatComponentTranslation(EnumChatFormatting.GRAY + Messy, new Object[0]);
			((EntityPlayer) entityLiving).addChatComponentMessage(chatmessy);
		    }
			
			return true;
    	}
		}
		if (getOffMode(stack)== 4)
   		{
		   setOffMode(stack, 2);
   		}
		 return false;
    }
		
	public void TRANSFORM (ItemStack thestaff, EntityPlayer theplayer, World world, int x2, int y2, int z2, EnumFacing theface, float fty1, float fty2, float fty3)
	{
        BlockPos pos2 = new BlockPos(x2, y2, z2);
        
      //The block that is being transformed
			ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff)); 
		if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItemStack(stacky))
  		{	
			world.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), 
   				 returnTBlock(thestaff).stepSound.getStepSound(), 
   				 (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, 
   				 returnTBlock(thestaff).stepSound.getFrequency() * 0.8F);
			(world.getBlockState(pos2).getBlock()).dropBlockAsItem(world, pos2, world.getBlockState(pos2), 0);
      		//Gblock.dropBlockAsItem(theblock, x2, y2, z2, Gmeta, 0);
      		//theblock.setBlock(x2, y2, z2, Blocks.air);
      		world.setBlockState(pos2, BlockStateHelper.returnState(getTBlock(thestaff)), 02);
      		
      		
      		int crackid = (getTBlock(thestaff));
			int crackmeta = (returnTMeta(thestaff));
			//int crackid = Gblock.getIdFromBlock(Gblock);
			//int crackmeta = Gmeta;
			String particle = "blockcrack_" + crackid + "_" + crackmeta;
			for (int pl = 0; pl < 5; ++pl)
			{
				float f = (this.growrand.nextFloat() - .2F) * 1.4F;
				float f1 = (this.growrand .nextFloat() - .2F) * 1.4F;
				float f2 = (this.growrand .nextFloat() - .2F) * 1.4F;
				world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, x2+f, y2+f1+.3, z2+f2, 0, 0, 0, crackid,crackmeta );       	            		
			}
			//successful = 1;
			if (!theplayer.capabilities.isCreativeMode){                	
    			InventoryUtil.consumeInventoryItemStack(stacky, theplayer.inventory);        	                        
                thestaff.damageItem(1, theplayer);
                }	
  		}
        
        
	}
	
	
	
	
	//The guts of the placement code
	/** This is a huge mess **/
	
	//Basically It will go through checks and determine what action it should perform next
	/**During this, it looks for Which mode -> Which face of the block 
	 * -> Which blocks are legal -> If they are legal, place or swap
	 * -> And finally depending on which gamemode to remove durability and items**/
	public boolean onItemUse(ItemStack thestaff, EntityPlayer theplayer, World world, BlockPos pos, EnumFacing theface, float fty1, float fty2, float fty3)
	{
		//Modifies size based on tool level
    	int mass = (getToolLevel(thestaff)+ 3);
    	int wall = (getToolLevel(thestaff)+ 2); 
    	
    	int x1 = pos.getX();
    	int y1 = pos.getY();
    	int z1 = pos.getZ();    	
    	BlockPos pos1 = new BlockPos(x1, y1, z1);
    		
    	
    	if (getOffMode(thestaff)== 0)
   		{
		   setOffMode(thestaff, 2);
   		}
    	
    	//Adds the block you are looking to the boolean, it is checked later or droped etc.
    	//For the pallete
		Gblock = BlockStateHelper.getBlockfromState(world, pos1);
		Gmeta = BlockStateHelper.getMetafromState(world, pos1); 
		
		////////////////////////////////////////////////////////////////
		/**      ~~~~~~~~      Small Mode       ~~~~~~~~             **/
		////////////////////////////////////////////////////////////////
		/** if this is true it performs this action **/
		if (!theplayer.isSneaking() && (returnTBlock_FromState(thestaff)) != Blocks.air    			
      			&& !theplayer.capabilities.isCreativeMode && Gblock != Blocks.bedrock
      			&& getMode(thestaff) == 2
      					||
      			!theplayer.isSneaking() && (returnTBlock_FromState(thestaff)) != Blocks.air    			
      			&& theplayer.capabilities.isCreativeMode
      			&& getMode(thestaff) == 2)
      	{ 
			int x2= x1;
	    	int y2= y1;
	    	int z2= z1;
	    	BlockPos pos2 = new BlockPos(x2, y2, z2);
      		if (BlockStateHelper.getBlockfromState(world, pos2) != (returnTBlock_FromState(thestaff))
              		|| BlockStateHelper.getMetafromState(world, pos2) != (returnTMeta(thestaff))
                       && BlockStateHelper.getBlockfromState(world, pos2) == (returnTBlock_FromState(thestaff)))
      		{
      			
      			TRANSFORM(thestaff, theplayer, world, x2, y2, z2, theface, fty1, fty2, fty3);      			
      		
      		}
      		world.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
					(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
      		return true;
      	
      	} 
    		////////////////////////////////////////////////////////////////////////////
    		/**    ~~~~~~~~                  Wall Mode               ~~~~~~~~~~~~~  **/
    		////////////////////////////////////////////////////////////////////////////


    	if (!theplayer.isSneaking() && (returnTBlock_FromState(thestaff)) != Blocks.air    			
      			&& !theplayer.capabilities.isCreativeMode && Gblock != Blocks.bedrock
      			&& getMode(thestaff) == 4
      					||
      			!theplayer.isSneaking() && (returnTBlock_FromState(thestaff)) != Blocks.air    			
      			&& theplayer.capabilities.isCreativeMode
      			&& getMode(thestaff) == 4)
      	{  
    		
    			
    			for (int l = 0; l < wall; ++l)
    			{   
    				for (int ml = 0; ml < wall; ++ml)
    				{ 
    					for (int sl = 0; sl < 4; ++sl)
    					{ 

    						int sectA =1;
    						int sectB =1;
    						switch(sl){
    						case 0:
    							break;
    						case 1: sectA= -1;       						
    							break;        							
    						case 2:	sectA= -1;
    								sectB= -1;
    							break;
    						case 3:	sectB= -1;
    							break;
    							
    						}
    						int x3 = 0;
    						int y3 = 0;
    						int z3 = 0;
    						//////////////////////////////////////
    						switch(theface){
    						//Bottom 0
    						case DOWN:	z3 = z3 - l*sectA; 
    						x3 = x3 - ml*sectB;        	    			 		
    						break;
    						//Top 1
    						case UP:	z3 = z3 - l*sectA; 
    						x3 = x3 - ml*sectB;

    						break;
    						//North 2
    						case NORTH:	y3 = y3 - l*sectA; 
    						x3 = x3 - ml*sectB; 
    						break;
    						//South 3
    						case SOUTH:	y3 = y3 - l*sectA; 
    						x3 = x3 - ml*sectB;
    						break;
    						//West 4
    						case WEST:   y3 = y3 - l*sectA; 
    						z3 = z3 - ml*sectB;			 
    						break;
    						//East 5
    						case EAST:	 y3 = y3 - l*sectA; 
    						z3 = z3 - ml*sectB;	
    						break;
    						default: 
    						}
    						int x2 = x1 + x3;
    						int y2 = y1 + y3;
    						int z2 = z1 + z3;
            	            BlockPos pos2 = new BlockPos(x2, y2, z2);
            	            Block Compare_Block = BlockStateHelper.getBlockfromState(world, pos2);
            	            int Compare_Meta = BlockStateHelper.getMetafromState(world, pos2);
    						//Whitelist Placement
    							if (Compare_Block != (returnTBlock_FromState(thestaff))
    				    				&& Compare_Block == Gblock
    				    				&& Compare_Meta == Gmeta
    				            		|| Compare_Meta != (returnTMeta(thestaff))
    				                     && Compare_Block == (returnTBlock_FromState(thestaff))
    				     				&& Compare_Block == Gblock
    				     				&& Compare_Meta == Gmeta)
    				    			{
    								TRANSFORM(thestaff, theplayer, world, x2, y2, z2, theface, fty1, fty2, fty3);  
    				    			}
    				    			
    					}
    				}
    			}

    			world.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
    					(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
    			return true;

    		}

		/////////////////////////////////////////////////////////////////////////
		/**          ~~~~~~~         Mass mode 6        ~~~~~~~             **/
		////////////////////////////////////////////////////////////////////////
		if (!theplayer.isSneaking() && (returnTBlock_FromState(thestaff)) != Blocks.air    			
      			&& !theplayer.capabilities.isCreativeMode && Gblock != Blocks.bedrock
      			&& getMode(thestaff) == 6
      					||
      			!theplayer.isSneaking() && (returnTBlock_FromState(thestaff)) != Blocks.air    			
      			&& theplayer.capabilities.isCreativeMode
      			&& getMode(thestaff) == 6)
      	{  
    			
    			for (int xl = 0; xl < mass; ++xl)
    			{   
    				for (int yl = 0; yl < mass; ++yl)
    				{ 
    					for (int zl = 0; zl < mass; ++zl)
        				{    						
    						int offy = (mass-1)/2;
    						int x3 = xl - offy;
    						int y3 = yl - offy;
    						int z3 = zl - offy;
    						//////////////////////////////////////
    						int x2 = x1 + x3;
    						int y2 = y1 + y3;
    						int z2 = z1 + z3;

    						BlockPos pos2 = new BlockPos(x2, y2, z2);
            	            Block Compare_Block = BlockStateHelper.getBlockfromState(world, pos2);
            	            int Compare_Meta = BlockStateHelper.getMetafromState(world, pos2);
    						//Whitelist Placement
    						
    							if (Compare_Block != (returnTBlock_FromState(thestaff))
    				    				&& Compare_Block == Gblock
    				    				&& Compare_Meta == Gmeta
    				            		|| Compare_Meta != (returnTMeta(thestaff))
    				                     && Compare_Block == (returnTBlock_FromState(thestaff))
    				     				&& Compare_Block == Gblock
    				     				&& Compare_Meta == Gmeta)
    				    			{
    								TRANSFORM(thestaff, theplayer, world, x2, y2, z2, theface, fty1, fty2, fty3);  
    				    			}
    						}
        				}
    			
    				
    			}
    			world.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
    					(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
    			return true;

    		}
    
    		
    		
    		/**
    		 * If these all are false it returns with a default action
    		 */
    		
    		
    		
    	if (theplayer.isSneaking())
    	{ 
    		
    		//setTBlock(thestaff, theblock.getBlock(x1, y1, z1).getIdFromBlock(theblock.getBlock(x1, y1, z1)));
    		//setTMeta(thestaff,theblock.getBlockMetadata(x1, y1, z1)); 		
    		//
    		setTBlock(thestaff, BlockStateHelper.returnID(world, pos1)); 
    		setTMeta(thestaff, BlockStateHelper.getMetafromState(world, pos1)); 	
    		
    		world.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
    				(double)z1 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
    		
    		 setOffMode(thestaff, 4);
    			return true;
    		
    	}
    	
    	 
    	world.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
				(double)z1 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
		return false;
    	
    }
    
    
	
    
}