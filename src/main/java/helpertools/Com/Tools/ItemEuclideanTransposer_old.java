package helpertools.Com.Tools;

import helpertools.Com.Config;
import helpertools.Com.ItemRegistry;
import helpertools.Com.Blocks.TileEntityTranscriber;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import helpertools.Utils.InventoryUtil;
import helpertools.Utils.ModUtil;
import helpertools.Utils.Whitelist_Util;

import java.util.List;
import java.util.Random;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemEuclideanTransposer_old extends ToolBase_Patterns
{
    public ItemEuclideanTransposer_old(ToolMaterial material, String unlocalizedName)
    {
    	super (material); 
	    setUnlocalizedName(unlocalizedName);
	    this.MaxMode = 4;
    }
    protected static Random growrand = new Random();
    
    
    //flavor text
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
    	list.add(TextFormatting.WHITE + "Sets blocks in a 5^cube pattern");
    	list.add(TextFormatting.ITALIC + "Use while sneaking for a pattern");
    	list.add(" ");
    	list.add(TextFormatting.ITALIC + "Can also be used with");
    	list.add(TextFormatting.ITALIC + "- Transcriber Block");
    	if (stack.hasTagCompound()){
    		list.add(whatModeString(stack)+ " mode");
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
	
	public String whatModeString(ItemStack itemStack){	  
    	String modestring= "Error";
    	int mode = getMode(itemStack);    	
    	switch(mode){
		case 2:	modestring = "Flush";				 
			break;		
		case 4: modestring = "Submerged -1";
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
			ModUtil.print(living, TextFormatting.GRAY + Messy);
		    }
	}
	
		
	public EnumActionResult onItemUse(ItemStack thestaff, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing theface, float hitX, float hitY, float hitZ)
	{
		
		int x1 = pos.getX();
    	int y1 = pos.getY();
    	int z1 = pos.getZ();
    	
    	BlockPos pos1 = new BlockPos(x1, y1, z1);
    	BlockPos pos3 ;
    	
    	float py = (this.growrand .nextFloat()) ;
    	int mOffset = ((((getMode(thestaff)/2)*-1) +1));
    	
    	if (getOffMode(thestaff)== 0)
   		{
		   setOffMode(thestaff, 2);
   		}
    	
    	
    	
    	/////////////////////////////
    	/** placement and get code**/
    	////////////////////////////
    	int successful = 0;
    	int P = 5;
    	int proxyskip = 0;
    	if (!player.isSneaking()){
    		
    		
    		//placement via transcriber proxy
    		if(world.getBlockState(pos1).getBlock() == ItemRegistry.transcriberBlock){
    			TileEntityTranscriber tile = (TileEntityTranscriber)world.getTileEntity(pos1);
    			if (tile != null)
                {
    				x1 = x1 -2 + (tile.offX);
    				y1 = y1 -1 + (tile.offY);
    				z1 = z1 -2 + (tile.offZ);
    				proxyskip = 1;
                }
    		}
    		
    		//placement via staff
    		if(world.getBlockState(pos1).getBlock() !=ItemRegistry.transcriberBlock
    				&& proxyskip == 0){
    			    		
    		//dynamic placement offsets
        	//W/E_T/B_N/S
    			

    			switch(theface){
    			case DOWN:	
    				y1 = y1 - mOffset -6;
    				x1 = x1 - 2;
    				z1 = z1 - 2;			 
    				break;
    			case UP:	
    				y1 = mOffset + y1;
    				x1 = x1 - 2;
    				z1 = z1 - 2;			 
    				break;
    			case NORTH:
    				y1 = y1-3;
    				x1 = x1 - 2;
    				z1 = z1 - 5 -mOffset;
    				break;
    			case SOUTH:
    				y1 = y1-3;
    				x1 = x1 - 2;
    				z1 = z1 +1 +mOffset;
    				break;
    			case WEST: 
    				y1 = y1-3;
    				x1 = x1 - 5 -mOffset;
    				z1 = z1 -2 ; 			 
    				break;
    			case EAST:	
    				y1 = y1-3;
    				x1 = x1 +1 +mOffset;
    				z1 = z1 -2 ; 
    				break;
    			default:
    			}

    		}
    		
    	for (int G2 = 0; G2 < P; ++G2)
        {
    		int G2counter = G2*P*P;
    		for (int U = 0; U < P; ++U)
    		{
    			int Ucounter = U*P;
    			for (int l = 0; l < P; ++l)
    			{
    				int Nbtcounter = G2counter+Ucounter+l+1;
    				
    				int X_1 = x1+U;
    				int Y_1 = y1+1+l;
    				int Z_1 = z1+G2;
    				BlockPos pos2 = new BlockPos(X_1, Y_1, Z_1);
    				
    	    		
    	    		
    				if (returnTBlock_FromState(thestaff, Nbtcounter) != Blocks.AIR){
    				
    					/** displacement whitelist **/
    				if (world.isAirBlock(pos2)
    						|| world.getBlockState(pos2).getMaterial() == Material.LAVA 
    						|| world.getBlockState(pos2).getMaterial() == Material.WATER
    						|| world.getBlockState(pos2).getMaterial() == Material.PLANTS 
    						|| world.getBlockState(pos2).getMaterial() == Material.VINE
    						|| world.getBlockState(pos2).getMaterial() == Material.SNOW)
    				{
    					ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock_FromState(thestaff, Nbtcounter)),0, returnTMeta(thestaff, Nbtcounter)); 
    					
    					Boolean whitelist_flag;
    					whitelist_flag = Whitelist_Util.Block_Whitelist(returnTBlock_FromState(thestaff, Nbtcounter), player, returnTMeta(thestaff, Nbtcounter));
    					if (player.capabilities.isCreativeMode|| player.inventory.hasItemStack(stacky)
    							||whitelist_flag){
    					
    						/** plants reinbursement **/ /**Having to work around blocks like this isn't fun **/
    						if (world.getBlockState(pos2).getMaterial() == Material.VINE
    	    						|| world.getBlockState(pos2).getMaterial() == Material.PLANTS
    	    						|| world.getBlockState(pos2).getMaterial() == Material.SNOW) 
    						{
    							world.getBlockState(pos2).getBlock().dropBlockAsItem(world, pos2, world.getBlockState(pos2), 0);
    						}
    					
    					world.setBlockState(pos2, BlockStateHelper.returnState(getTBlock(thestaff, Nbtcounter)), 02);
    					successful = 1;
    					short short1 = 32;
    					for (int lp = 0; lp < short1; ++lp)
    		            {
    		                double d6 = (double)lp / ((double)short1 - 1.0D);
    		                
    		                float f = (this.growrand.nextFloat() - .5F) * 1.4F;
    		                float f1 = (this.growrand .nextFloat() - .5F) * 1.4F;
    		                float f2 = (this.growrand .nextFloat() - .5F) * 1.4F;
    		                
    		                float p = (this.growrand.nextFloat()) ;
    		                float p1 = (this.growrand .nextFloat() ) ;
    		                float p2 = (this.growrand .nextFloat() ) ;
    		                world.spawnParticle(EnumParticleTypes.PORTAL, X_1 +p+.1, y1+.6+l+p1, Z_1 +p2+.1, f, f1, f2);
    		               
    		               
    		            }
    					
    					if (!player.capabilities.isCreativeMode){    						
    						if(!whitelist_flag)InventoryUtil.consumeInventoryItemStack(stacky, player.inventory); 
    		    			if(whitelist_flag){
    		    				Whitelist_Util.Consume_Whitelist(stacky, player, returnTBlock_FromState(thestaff, Nbtcounter), returnTMeta(thestaff, Nbtcounter));
    		    				
    		    				}
    						 thestaff.damageItem(1, player);
    					}
    					}
    					
        
    				}
    			}
    			
    				}
    			}
    		
    		
        }
    	if (successful == 1){
			
    		player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.2F, .5F+py);
			player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.7F, .5F+py);
			successful = 0;
			return EnumActionResult.SUCCESS;
		}
		if (successful ==0){
			player.playSound(SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, .4F, itemRand.nextFloat() * 0.4F + 0.5F);
			successful = 0;
			return EnumActionResult.SUCCESS;
		}
    	}
    	
    	/////////////////////////////
    	/** Pattern Collection **/
    	if (player.isSneaking()){
    		
    		if(world.getBlockState(pos1).getBlock() == ItemRegistry.transcriberBlock){
    			TileEntityTranscriber tile = (TileEntityTranscriber)world.getTileEntity(pos1);
    			if (tile != null)
                {

    				
    				x1 = x1 + (tile.offX);
    				y1 = y1 + (tile.offY);
    				z1 = z1 + (tile.offZ);
                }
    		}
    		
    		
    		x1 = x1 - 2;
    		z1 = z1 - 2;
    		/** Z **/
    		for (int G2 = 0; G2 < P; ++G2)
            {
        		int G2counter = G2*P*P;
        		/** X **/
        		for (int U = 0; U < P; ++U)
        		{
        			int Ucounter = U*P;
        			/** Y **/
        			for (int l = 0; l < P; ++l)
        			{
        				int Nbtcounter = G2counter+Ucounter+l+1;
        				
        				pos3 = new BlockPos(x1+U, y1+l, z1+G2);

        		    		setTBlock(thestaff, BlockStateHelper.returnID(world, pos3), Nbtcounter); 
        		    		setTMeta(thestaff, BlockStateHelper.getMetafromState(world, pos3), Nbtcounter); 	
        					
            		            
        				}
        			}
            }
    		player.playSound(SoundEvents.ENTITY_GHAST_SHOOT, 1.5F, .2F+py/4);
    		if(!player.worldObj.isRemote){
    			ModUtil.print((EntityLivingBase)player, TextFormatting.GRAY + "Pattern Saved");
    		}
    		setOffMode(thestaff, 4);
    		return EnumActionResult.SUCCESS;
    		
    	}
    	return EnumActionResult.FAIL; 
    }

    
}