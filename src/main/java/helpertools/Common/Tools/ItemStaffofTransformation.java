package helpertools.Common.Tools;

import helpertools.Common.Config;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import helpertools.Utils.InventoryUtil;
import helpertools.Utils.Texty;
import helpertools.Utils.Whitelist_Util;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemStaffofTransformation extends ToolBase_Default
{
    public ItemStaffofTransformation(ToolMaterial material, String unlocalizedName)
    {
    	super (material);  
        setUnlocalizedName(unlocalizedName);
        this.MaxMode = 6;
    }
    
    protected static Random growrand = new Random();
    
    //Adds fancy flavor text for item
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	par3List.add(TextFormatting.WHITE + "Swaps blocks in the world");
        par3List.add(TextFormatting.ITALIC + "While sneaking change mode");
        par3List.add(TextFormatting.ITALIC + "- Or select block to swap");
        par3List.add(TextFormatting.ITALIC + "While enchanted with efficiency");
        par3List.add(TextFormatting.ITALIC + "- Press 'o' to toggle size");
        par3List.add(TextFormatting.ITALIC + "");
    	if (stack.hasTagCompound()){
    if(whatBlockString(stack) != "null" && whatModeString(stack)!= "null"){
    	par3List.add(whatBlockString(stack) + whatModeString(stack)+ " mode");
    }}}
    
    
    public String whatModeString(ItemStack itemStack){	  
    	String modestring= "Error";
    	int mode = getMode(itemStack);    	
    	switch(mode){
		case 2:	modestring = "Single";				 
			break;		
		case 4: modestring = "Wall";
			break;
		case 6: modestring = "Mass";
		break;
		default: modestring = "Error";
			break;
		}
    	return modestring;
    };
    
	public void ModeText(EntityLivingBase living, ItemStack itemStack){
		int mode = getMode(itemStack);
		if(Config.ToolModeMesseges){
			String Messy = whatModeString(itemStack) + " Mode";
			Texty.print(living, TextFormatting.GRAY + Messy);
		    }
	}	
	
	private Block Gblock = Blocks.AIR;	
	private int Gmeta = 0;	
	
	
	
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
		
	public void TRANSFORM (ItemStack thestaff, EntityPlayer player, World world, int x2, int y2, int z2, EnumFacing theface, float hitY, float hitX, float hitZ)
	{
        BlockPos pos2 = new BlockPos(x2, y2, z2);
        
      //The block that is being transformed
			ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock_FromState(thestaff)),0, returnTMeta(thestaff)); 
			Boolean whitelist_flag;
			whitelist_flag = Whitelist_Util.Block_Whitelist(returnTBlock_FromState(thestaff), player, returnTMeta(thestaff));
		if(player.capabilities.isCreativeMode || player.inventory.hasItemStack(stacky)
				|| whitelist_flag)
  		{	
			SoundType soundtype = returnTBlock_FromState(thestaff).getSoundType();
            world.playSound(player, player.getPosition(), soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
            
			(world.getBlockState(pos2).getBlock()).dropBlockAsItem(world, pos2, world.getBlockState(pos2), 0);      		
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
			//successful = 1;
			if (!player.capabilities.isCreativeMode){                	
				if(!whitelist_flag)InventoryUtil.consumeInventoryItemStack(stacky, player.inventory); 
    			if(whitelist_flag){
    				Whitelist_Util.Consume_Whitelist(stacky, player, returnTBlock_FromState(thestaff), returnTMeta(thestaff));
    			}
                thestaff.damageItem(1, player);
                }	
  		}
        
        
	}
	
	
	
	
	//The guts of the placement code
	/** This is a huge mess **/
	
	//Basically It will go through checks and determine what action it should perform next
	/**During this, it looks for Which mode -> Which face of the block 
	 * -> Which blocks are legal -> If they are legal, place or swap
	 * -> And finally depending on which gamemode to remove durability and items**/
	//public boolean onItemUse(ItemStack thestaff, EntityPlayer player, World world, BlockPos pos, EnumFacing theface, float hitY, float hitX, float hitZ)
	public EnumActionResult onItemUse(ItemStack thestaff, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing theface, float hitX, float hitY, float hitZ)
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
		if (!player.isSneaking() && (returnTBlock_FromState(thestaff)) != Blocks.AIR    			
      			&& !player.capabilities.isCreativeMode && Gblock != Blocks.BEDROCK
      			&& getMode(thestaff) == 2
      					||
      			!player.isSneaking() && (returnTBlock_FromState(thestaff)) != Blocks.AIR    			
      			&& player.capabilities.isCreativeMode
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
      			
      			TRANSFORM(thestaff, player, world, x2, y2, z2, theface, hitY, hitX, hitZ);      			
      		
      		}
      		failedsound(world, player);
      		return EnumActionResult.SUCCESS;
      	
      	} 
    		////////////////////////////////////////////////////////////////////////////
    		/**    ~~~~~~~~                  Wall Mode               ~~~~~~~~~~~~~  **/
    		////////////////////////////////////////////////////////////////////////////


    	if (!player.isSneaking() && (returnTBlock_FromState(thestaff)) != Blocks.AIR    			
      			&& !player.capabilities.isCreativeMode && Gblock != Blocks.BEDROCK
      			&& getMode(thestaff) == 4
      					||
      			!player.isSneaking() && (returnTBlock_FromState(thestaff)) != Blocks.AIR    			
      			&& player.capabilities.isCreativeMode
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
    								TRANSFORM(thestaff, player, world, x2, y2, z2, theface, hitY, hitX, hitZ);  
    				    			}
    				    			
    					}
    				}
    			}
    			failedsound(world, player);
    			return EnumActionResult.SUCCESS;

    		}

		/////////////////////////////////////////////////////////////////////////
		/**          ~~~~~~~         Mass mode 6        ~~~~~~~             **/
		////////////////////////////////////////////////////////////////////////
		if (!player.isSneaking() && (returnTBlock_FromState(thestaff)) != Blocks.AIR    			
      			&& !player.capabilities.isCreativeMode && Gblock != Blocks.BEDROCK
      			&& getMode(thestaff) == 6
      					||
      			!player.isSneaking() && (returnTBlock_FromState(thestaff)) != Blocks.AIR    			
      			&& player.capabilities.isCreativeMode
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
    								TRANSFORM(thestaff, player, world, x2, y2, z2, theface, hitY, hitX, hitZ);  
    				    			}
    						}
        				}
    			
    				
    			}
    			failedsound(world, player);
    			return EnumActionResult.SUCCESS;

    		}
    
    		
    		
    		/**
    		 * If these all are false it returns with a default action
    		 */
    		
    		
    		
    	if (player.isSneaking())
    	{ 
    		setTBlock(thestaff, BlockStateHelper.returnID(world, pos1)); 
    		setTMeta(thestaff, BlockStateHelper.getMetafromState(world, pos1)); 	
    		
    		failedsound(world, player);
    		
    		 setOffMode(thestaff, 4);
    		 return EnumActionResult.SUCCESS;
    		
    	}
    	
    	 
    	failedsound(world, player);
    	return EnumActionResult.FAIL; 
    	
    }
    
    
	
    
}