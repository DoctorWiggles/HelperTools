package helpertools.Common.Tools;

import helpertools.Common.ConfigurationFactory;
import helpertools.Common.ItemRegistry;
import helpertools.Common.Blocks.TileEntityTranscriber;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import helpertools.Utils.InventoryUtil;
import helpertools.Utils.Texty;
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

public class ItemEuclideanTransposer extends ToolBase_Patterns
{
    public ItemEuclideanTransposer(ToolMaterial material, String unlocalizedName)
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
    	list.add(TextFormatting.ITALIC + "sets patterns 5x5");
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
		if(ConfigurationFactory.ToolModeMesseges){
			String Messy = whatModeString(itemStack) + " Mode";
			Texty.print(living, TextFormatting.GRAY + Messy);
		    }
	}
	
		
	//The guts of the placement code
	/** This is a huge mess **/
	
	//Basically It will go through checks and determine what action it should perform next
	/**During this, it looks for Which mode -> Which face of the block 
	 * -> Which blocks are legal -> If they are legal, place or swap
	 * -> And finally depending on which gamemode to remove durability and items**/
	//public boolean onItemUse(ItemStack thestaff, EntityPlayer player, World world, BlockPos pos, EnumFacing theface, float fty1, float fty2, float fty3)    
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
    			//Bottom 0
    			case DOWN:	
    				y1 = y1 - mOffset -6;
    				x1 = x1 - 2;
    				z1 = z1 - 2;			 
    				break;
    				//Top 1
    			case UP:	
    				y1 = mOffset + y1;
    				x1 = x1 - 2;
    				z1 = z1 - 2;			 
    				break;
    				//North 2
    			case NORTH:
    				y1 = y1-3;
    				x1 = x1 - 2;
    				z1 = z1 - 5 -mOffset;
    				break;
    				//South 3
    			case SOUTH:
    				y1 = y1-3;
    				x1 = x1 - 2;
    				z1 = z1 +1 +mOffset;
    				break;
    				//West 4
    			case WEST: 
    				y1 = y1-3;
    				x1 = x1 - 5 -mOffset;
    				z1 = z1 -2 ; 			 
    				break;
    				//East 5
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
    				
    	    		
    	    		
    				/**
    				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
							"Counter is? : " + (Nbtcounter), new Object[0]);
					((EntityPlayer) player)
							.addChatComponentMessage(chatcomponenttranslation);
    				**/
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
    					//stacky = new ItemStack (Item.getItemFromBlock(Blocks.DIRT), 0,0);
    					//if (player.capabilities.isCreativeMode|| player.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff, Nbtcounter)))
    					Boolean whitelist_flag;
    					whitelist_flag = Whitelist_Util.Block_Whitelist(returnTBlock_FromState(thestaff, Nbtcounter), player, returnTMeta(thestaff, Nbtcounter));
    					if (player.capabilities.isCreativeMode|| player.inventory.hasItemStack(stacky)
    							||whitelist_flag){
    					//theblock.playSoundEffect((double)((float)X_1  + 0.5F), (double)((float)Y_1  + 0.5F), (double)((float)Z_1  + 0.5F), returnTBlock(thestaff, Nbtcounter).stepSound.getStepResourcePath(), (returnTBlock(thestaff, Nbtcounter).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff, Nbtcounter).stepSound.getPitch() * 0.8F);
    						/** plants reinbursement **/ /**Having to work around blocks like this isn't fun **/
    						if (world.getBlockState(pos2).getMaterial() == Material.VINE
    	    						|| world.getBlockState(pos2).getMaterial() == Material.PLANTS
    	    						|| world.getBlockState(pos2).getMaterial() == Material.SNOW) 
    						{
    							//world.getBlockState(pos2).getBlock().dropBlockAsItem(world,X_1 , Y_1 , Z_1 , (world.getBlockMetadata(pos2)), 0);
    							world.getBlockState(pos2).getBlock().dropBlockAsItem(world, pos2, world.getBlockState(pos2), 0);
    						}
    						//theblock.setBlock(X_1 , Y_1 , Z_1 , Blocks.DIRT);
    						
    					//world.setBlock(X_1 , Y_1 , Z_1 , returnTBlock_FromState(thestaff, Nbtcounter), (returnTMeta(thestaff, Nbtcounter)), 0);
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
    		                //world.spawnParticle("portal", X_1 +p+.1, y1+.6+l+p1, Z_1 +p2+.1, f, f1, f2);
    		                
    		               
    		            }
    					
    					if (!player.capabilities.isCreativeMode){
    						//player.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff, Nbtcounter)));	
    						//stacky = new ItemStack (Item.getItemFromBlock(Blocks.DIRT), 0,0); 
    						if(!whitelist_flag)InventoryUtil.consumeInventoryItemStack(stacky, player.inventory); 
    		    			if(whitelist_flag){
    		    				Whitelist_Util.Consume_Whitelist(stacky, player, returnTBlock_FromState(thestaff, Nbtcounter), returnTMeta(thestaff, Nbtcounter));
    		    				//player.inventory.consumeInventoryItem(item);
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
			
    		
			//player.worldObj.playSoundAtEntity(player, "mob.endermen.portal", 1.2F, .5F+py);
			//player.worldObj.playSoundAtEntity(player, "mob.endermen.portal", 1.7F, .5F+py);
    		player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.2F, .5F+py);
			player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.7F, .5F+py);
			successful = 0;
			return EnumActionResult.SUCCESS;
		}
		if (successful ==0){
			//player.worldObj.playSoundAtEntity(player, "random.click",.4F, itemRand.nextFloat() * 0.4F + 0.5F);
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

    				
    				//player.worldObj.playSoundAtEntity(player, "mob.ghast.fireball", 1.2F, .2F+py/5);
    				
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
        				/**
        				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
    							"Counter is? : " + (Nbtcounter), new Object[0]);
    					((EntityPlayer) player)
    							.addChatComponentMessage(chatcomponenttranslation);
        				**/
        				
        					//setTBlock(thestaff, theblock.getBlock(x1+U, y1+l, z1+G2).getIdFromBlock(theblock.getBlock(x1+U, y1+l, z1+G2)), Nbtcounter);
        					//setTMeta(thestaff,theblock.getBlockMetadata(x1+U, y1+l, z1+G2), Nbtcounter); 	
        				pos3 = new BlockPos(x1+U, y1+l, z1+G2);

        		    		setTBlock(thestaff, BlockStateHelper.returnID(world, pos3), Nbtcounter); 
        		    		setTMeta(thestaff, BlockStateHelper.getMetafromState(world, pos3), Nbtcounter); 	
        					//theblock.playSoundEffect((double)((float)x1+U + 0.5F), (double)((float)y1+1+l + 0.5F), (double)((float)z1+G2 + 0.5F), returnTBlock(thestaff, Nbtcounter).stepSound.getStepResourcePath(), (returnTBlock(thestaff, Nbtcounter).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff, Nbtcounter).stepSound.getPitch() * 0.8F);
        					//theblock.setBlock(x1+U, y1+1+l, z1+G2, returnTBlock(thestaff, Nbtcounter));
            		            
        				}
        			}
            }
    		player.playSound(SoundEvents.ENTITY_GHAST_SHOOT, 1.5F, .2F+py/4);
    		if(!player.worldObj.isRemote){
    			Texty.print((EntityLivingBase)player, TextFormatting.GRAY + "Pattern Saved");
				//player.worldObj.playSoundAtEntity(player, "mob.ghast.fireball", 1.5F, .2F+py/4);
    		}
    		setOffMode(thestaff, 4);
    		return EnumActionResult.SUCCESS;
    		
    	}
    	return EnumActionResult.FAIL; 
    }

    
}