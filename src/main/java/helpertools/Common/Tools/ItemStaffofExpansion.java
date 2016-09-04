package helpertools.Common.Tools;

import helpertools.Common.ConfigurationFactory;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import helpertools.Utils.InventoryUtil;
import helpertools.Utils.Texty;
import helpertools.Utils.Whitelist_Util;

import java.util.List;
import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemStaffofExpansion extends ToolBase_Default
{
    public ItemStaffofExpansion(ToolMaterial material, String unlocalizedName)
    {
    	super (material);
        setUnlocalizedName(unlocalizedName);
        this.MaxMode = 6;
    }
    
    protected static Random growrand = new Random();
    //flavor text
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	par3List.add(TextFormatting.ITALIC + "sets blocks");
    	if (stack.hasTagCompound()){
    if(whatBlockString(stack) != "null" && whatModeString(stack)!= "null"){
    	par3List.add(whatBlockString(stack) +returnTMeta(stack) +" "+ whatModeString(stack)+ " mode");
    }}}
    
    public String whatModeString(ItemStack itemStack){	  
    	String modestring= "Error";
    	int mode = getMode(itemStack);    	
    	switch(mode){
		case 2:	modestring = "Pillar";				 
			break;		
		case 4: modestring = "Wall";
			break;
		case 6: modestring = "Matching";
		break;
		default: modestring = "Error";
			break;
		}
    	return modestring;
    };
    
	
	public void ModeText(EntityLivingBase living, ItemStack itemStack){
		int mode = getMode(itemStack);
		if(ConfigurationFactory.ToolModeMesseges){
			String Messy = whatModeString(itemStack) + " Mode";
			Texty.print(living, TextFormatting.GRAY + Messy);
		    }
	}
    
	/** Cycle through modes on swing and prevents backflow from onItemUse**/
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
		if (getOffMode(stack)== 0){ setOffMode(stack, 2); }
		if (entityLiving.isSneaking()&& getOffMode(stack)== 2)
    	{ 	
			ModeSound(entityLiving, stack);
			if (!entityLiving.worldObj.isRemote) {
				nextMode(stack);
				ModeText(entityLiving, stack);
				return true;
			}			
    	}
		if (getOffMode(stack)== 4){ setOffMode(stack, 2); }
		return false;
    }
	
	//Expanding function
	public void EXPAND (ItemStack thestaff, EntityPlayer player, World world, int x2, int y2, int z2, EnumFacing theface, float hitX, float hitY, float hitZ)
	{
        BlockPos pos2 = new BlockPos(x2, y2, z2);

        if (getTBlock(thestaff) == 0) {
            return;
        }

        //Whitelist Placement
		if (world.isAirBlock(pos2)
        		|| world.getBlockState(pos2).getMaterial() == Material.LAVA 
        		|| world.getBlockState(pos2).getMaterial() == Material.WATER
				|| world.getBlockState(pos2).getMaterial() == Material.PLANTS 
				|| world.getBlockState(pos2).getMaterial() == Material.VINE 
				|| world.getBlockState(pos2).getBlock() == Blocks.SNOW_LAYER)
        {
			//ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff)); 
			ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock_FromState(thestaff)),0, returnTMeta(thestaff)); 
			
			Boolean whitelist_flag;
			whitelist_flag = Whitelist_Util.Block_Whitelist(returnTBlock_FromState(thestaff), player, returnTMeta(thestaff));
        	if(player.capabilities.isCreativeMode || player.inventory.hasItemStack(stacky)
        			|| whitelist_flag)
    		{
        		//destroys and returns blocks like grass
        		if (world.getBlockState(pos2).getMaterial() == Material.VINE
						|| world.getBlockState(pos2).getMaterial() == Material.PLANTS
						|| world.getBlockState(pos2).getBlock() == Blocks.SNOW_LAYER) 
				{
										
					(world.getBlockState(pos2).getBlock()).dropBlockAsItem(world, pos2, world.getBlockState(pos2), 0);
				}
        		/** TODO
        		 world.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), 
        				 returnTBlock(thestaff).stepSound.getStepSound(), 
        				 (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, 
        				 returnTBlock(thestaff).stepSound.getFrequency() * 0.8F);
        				 **/
        		
        		
        		SoundType soundtype = returnTBlock_FromState(thestaff).getSoundType();
                world.playSound(player, player.getPosition(), soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
        		        		
        		
        		world.setBlockState(pos2, BlockStateHelper.returnState(getTBlock(thestaff)), 02);
        		
        		int crackid = (getTBlock(thestaff));
                int crackmeta = (returnTMeta(thestaff));
                String particle = "blockcrack_" + crackid + "_" + crackmeta;
        		for (int pl = 0; pl < 5; ++pl)
    			{
        		float f = (this.growrand.nextFloat() - .2F) * 1.4F;
                float f1 = (this.growrand .nextFloat() - .2F) * 1.4F;
                float f2 = (this.growrand .nextFloat() - .2F) * 1.4F;
        		
        		world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, x2+f, y2+f1+.3, z2+f2, 0, 0, 0, crackid,crackmeta ); 
    			}
        		
        		if (!player.capabilities.isCreativeMode){                	
        			if(!whitelist_flag)InventoryUtil.consumeInventoryItemStack(stacky, player.inventory); 
        			if(whitelist_flag){
        				Whitelist_Util.Consume_Whitelist(stacky, player, returnTBlock_FromState(thestaff), returnTMeta(thestaff));
        				}
                    thestaff.damageItem(1, player);
                    }	
    		}
        
        
        }
	}

	
	//The guts of the placement code
	/** This is a huge mess **/
	
	//Basically It will go through checks and determine what action it should perform next
	/**During this, it looks for Which mode -> Which face of the block 
	 * -> Which blocks are legal -> If they are legal, place or swap
	 * -> And finally depending on which gamemode to remove durability and items**/		
	//public boolean onItemUse(ItemStack thestaff, EntityPlayer player, World world, BlockPos pos, EnumFacing theface, float hitX, float hitY, float hitZ){
	public EnumActionResult onItemUse(ItemStack thestaff, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing theface, float hitX, float hitY, float hitZ)
	{
    	//Modifies size based on tool level	
    	int pillar = (getToolLevel(thestaff)+ 3);
    	int wall = (getToolLevel(thestaff)+ 2); 
    	
    	int x1 = pos.getX();
    	int y1 = pos.getY();
    	int z1 = pos.getZ();
    	
    	BlockPos pos1 = new BlockPos(x1, y1, z1);
		
 		/** if this is true it performs this action **/
    	//Checks to make sure you're not sneaking
    	//////////////////////////////////////////////
    	/** ~~~~~~~~ Pillar Mode 2  ~~~~~~~~~~~~~ **/
    	////////////////////////////////////////////
    	
    	if (!player.isSneaking()
    			&& getMode(thestaff) == 2)
    	{ 
    	            //BOTTOM FACE
    	            //W/E_T/B_N/S
    		
        			for (int l = 0; l < pillar; ++l)
        			{        		
        				int x3 = 0;
        	            int y3 = 0;
        	            int z3 = 0;
        	    		//////////////////////////////////////
        	    		 switch(theface){
        	    		 //Bottom 0
        	    		 case DOWN:	y3 = y3 -1 - l; 			 
        	    			 break;
        	    		//Top 1
        	    		 case UP:	y3 = y3 +1 + l; 			 
        	    			 break;
        	    		//North 2
        	    		 case NORTH:	z3 = z3 -1 - l;
        	    			 break;
        	    		//South 3
        	    		 case SOUTH:	z3 = z3 +1 + l; 
        	    			 break;
        	    		//West 4
        	    		 case WEST:   	x3 = x3 -1 -l; 			 
        	    			 break;
        	    		//East 5
        	    		 case EAST:	x3 = x3 +1 +l;  
        	    			 break;
        	    			 default:
        	    			 }
        				int x2 = x1 + x3;
        	            int y2 = y1 + y3;
        	            int z2 = z1 + z3;
        	            
        	            /////////////////////////
        	            /**Expanding function**/
        	            ///////////////////////
        	            EXPAND(thestaff, player, world, x2, y2, z2, theface, hitX, hitY, hitZ);
        			}

        			failedsound(world, player);
        			return EnumActionResult.SUCCESS;
    	               	           
    	        }
    	///////////////////////////////////////////////////////////////////////////
    	/**    ~~~~~~~~                  Wall Mode               ~~~~~~~~~~~~~  **/
    	/////////////////////////////////////////////////////////////////////////
    	

    	if (!player.isSneaking()
    			&& getMode(thestaff) == 4)
    	{ 
    	            //BOTTOM FACE
    	            //W/E_T/B_N/S
    		
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
        						//z3 = z3 +offy - l*sectA; 
    	    			 		//x3 = x3 +offy - ml*sectB;
        					
        				//int offy = (wall-1)/2;
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
        	            
        	            /////////////////////////
        	            /**Expanding function**/
        	            ///////////////////////
        	            EXPAND(thestaff, player, world, x2, y2, z2, theface, hitX, hitY, hitZ);
        	            
    	        	
    	          
                			}
            			}
        			}
        			failedsound(world, player);
        			return EnumActionResult.SUCCESS;
    	}
    
    	/////////////////////////////////////////////////////////////////////////
    	/** Matching Mode 6 **/
    	/////////////////////////////////////////////////////////////////////////
    	if (!player.isSneaking()
    			&& getMode(thestaff) == 6)
    	{ 
    		//int successful = 0;
    	            //--z1; 
    	            //BOTTOM FACE
    	            //W/E_T/B_N/S
    		
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
        						//z3 = z3 +offy - l*sectA; 
    	    			 		//x3 = x3 +offy - ml*sectB;
        					
        				//int offy = (wall-1)/2;        				
        				int x3 = 0;
        	            int y3 = 0;
        	            int z3 = 0;
        	            //Matching block offset
        	            int x5 = 0;
        	            int y5 = 0;
        	            int z5 = 0; 
        	    		//////////////////////////////////////
        	    		 switch(theface){
        	    		 //Bottom 0
        	    		 case DOWN:	z3 = z3 - l*sectA; 
	    			 				x3 = x3 - ml*sectB; 
	    			 				y5 = y5 - 1;
        	    			 break;
        	    		//Top 1
        	    		 case UP:	z3 = z3 - l*sectA; 
        	    			 		x3 = x3 - ml*sectB;
        	    			 		y5 = y5 + 1;        	    			 		
        	    			 break;
        	    		//North 2
        	    		 case NORTH:	y3 = y3 - l*sectA; 
			 						x3 = x3 - ml*sectB; 
			 						z5 = z5 - 1;
        	    			 break;
        	    		//South 3
        	    		 case SOUTH:	y3 = y3 - l*sectA; 
	 								x3 = x3 - ml*sectB;
	 								z5 = z5 + 1;
	 						break;
        	    		//West 4
        	    		 case WEST:   y3 = y3 - l*sectA; 
	 							   z3 = z3 - ml*sectB;
	 							   x5 = x5 - 1;
        	    			 break;
        	    		//East 5
        	    		 case EAST:	y3 = y3 - l*sectA; 
						   			z3 = z3 - ml*sectB;
						   			x5 = x5 + 1;
        	    			 break;
        	    			 default: 
        	    		 }
        	    		//Target Matching blocks
        				int x2 = x1 + x3;
        	            int y2 = y1 + y3;
        	            int z2 = z1 + z3;
        	            //Matching blocks to be placed
        	            int xT4 = x2 + x5;
        	            int yT4 = y2 + y5;
        	            int zT4 = z2 + z5;   
        	                   	            
        	            BlockPos pos3 = new BlockPos(x2, y2, z2);	
        	            if (        	            		
            	            	world.getBlockState(pos3) ==BlockStateHelper.returnState(getTBlock(thestaff))){
        	            	
        	            	/////////////////////////
            	            /**Expanding function**/
            	            EXPAND(thestaff, player, world, xT4, yT4, zT4, theface, hitX, hitY, hitZ);
        	            
                			}
        	            }
        			}        	
        		}
        			failedsound(world, player);
        			return EnumActionResult.SUCCESS;
    	}
        
        
    	
    	/////////////////////////////////
        /** If Sneaking Select Block **/
        ///////////////////////////////
    	if (player.isSneaking())
    	{ 
    		
    		setTBlock(thestaff, BlockStateHelper.returnID(world, pos1)); 
    		setTMeta(thestaff, BlockStateHelper.getMetafromState(world, pos1)); 	
    		
    		//ItemStack stacky2 = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff)); 
    		
    		
    		failedsound(world, player);
    		
    		 setOffMode(thestaff, 4);
    		 return EnumActionResult.SUCCESS;
    	}
    	else
    	{ 
    		
    		return EnumActionResult.FAIL;   		
    	}
    	
    }
	
    
    
}


