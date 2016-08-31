package helpertools.Common.Tools;

import helpertools.Common.ItemRegistry;
import helpertools.Common.Blocks.TileEntityTranscriber;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import helpertools.Utils.InventoryUtil;
import helpertools.Utils.Texty;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemEuclideanTransposer extends ToolBase_Patterns
{
    public ItemEuclideanTransposer(ToolMaterial material, String unlocalizedName)
    {
    	super (material);
        this.maxStackSize = 1;  
	    setUnlocalizedName(unlocalizedName);
        setCreativeTab(HelpTab.HelperTools);
    }
    protected static Random growrand = new Random();
    
    
    //flavor text
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    par3List.add(TextFormatting.ITALIC + "sets patterns 5x5");
    }
      
	//Custom mode changing code
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
		
		if (getMode(stack)== 0)
   		{
		   setMode(stack, 2);
   		}
		if (getOffMode(stack)== 0)
   		{
		   setOffMode(stack, 2);
   		}
		if (!entityLiving.worldObj.isRemote) {
		if (entityLiving.isSneaking()&& getOffMode(stack)== 2)
    	{ 
			if (getMode(stack) == 4)
			{
		   		//entityLiving.playSound("mob.chicken.plop", 3.0F, .3F);
		   		entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 3F, .3F);
				Texty.print(entityLiving, TextFormatting.GRAY + "Flush Mode");				
				setMode(stack,2);
				return true;
			}
			else if (getMode(stack) == 2)
			{	
				Texty.print(entityLiving, TextFormatting.GRAY + "Submerged -1");
			//entityLiving.playSound("mob.chicken.plop", .3F, 3.0F);}
			entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", .3F, 3.0F);
			setMode(stack,4);
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
	
		
	//The guts of the placement code
	/** This is a huge mess **/
	
	//Basically It will go through checks and determine what action it should perform next
	/**During this, it looks for Which mode -> Which face of the block 
	 * -> Which blocks are legal -> If they are legal, place or swap
	 * -> And finally depending on which gamemode to remove durability and items**/
	public boolean onItemUse(ItemStack thestaff, EntityPlayer theplayer, World world, BlockPos pos, EnumFacing theface, float fty1, float fty2, float fty3)    
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
    	if (!theplayer.isSneaking()){
    		
    		
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
					((EntityPlayer) theplayer)
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
    					//if (theplayer.capabilities.isCreativeMode|| theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff, Nbtcounter)))
    					if (theplayer.capabilities.isCreativeMode|| theplayer.inventory.hasItemStack(stacky)
    							){
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
    					
    					if (!theplayer.capabilities.isCreativeMode){
    						//theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff, Nbtcounter)));	
    						//stacky = new ItemStack (Item.getItemFromBlock(Blocks.DIRT), 0,0); 
    						InventoryUtil.consumeInventoryItemStack(stacky, theplayer.inventory);
    						 thestaff.damageItem(1, theplayer);
    					}
    					}
    					
        
    				}
    			}
    			
    				}
    			}
    		
    		
        }
    	if (successful == 1){
			
    		
			theplayer.worldObj.playSoundAtEntity(theplayer, "mob.endermen.portal", 1.2F, .5F+py);
			theplayer.worldObj.playSoundAtEntity(theplayer, "mob.endermen.portal", 1.7F, .5F+py);
			successful = 0;
			return true;
		}
		if (successful ==0){
			theplayer.worldObj.playSoundAtEntity(theplayer, "random.click",.4F, itemRand.nextFloat() * 0.4F + 0.5F);
			successful = 0;
			return true;
		}
    	}
    	
    	/////////////////////////////
    	/** Pattern Collection **/
    	if (theplayer.isSneaking()){
    		
    		if(world.getBlockState(pos1).getBlock() == ItemRegistry.transcriberBlock){
    			TileEntityTranscriber tile = (TileEntityTranscriber)world.getTileEntity(pos1);
    			if (tile != null)
                {

    				
    				//theplayer.worldObj.playSoundAtEntity(theplayer, "mob.ghast.fireball", 1.2F, .2F+py/5);
    				
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
    					((EntityPlayer) theplayer)
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
    		if(!theplayer.worldObj.isRemote){				
				Texty.print(theplayer, TextFormatting.GRAY + "Pattern Saved");
    		theplayer.worldObj.playSoundAtEntity(theplayer, "mob.ghast.fireball", 1.5F, .2F+py/4);
    		}
    		setOffMode(thestaff, 4);
    		return true;
    		
    	}
    	return false;
    }

    
}