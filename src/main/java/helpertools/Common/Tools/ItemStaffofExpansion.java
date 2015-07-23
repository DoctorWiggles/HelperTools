package helpertools.Common.Tools;

import helpertools.Common.ConfigurationFactory;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import helpertools.Utils.InventoryUtil;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos.MutableBlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkEvent;

public class ItemStaffofExpansion extends ToolBase_Default
{
    public ItemStaffofExpansion(ToolMaterial material, String unlocalizedName)
    {
    	super (material);
        this.maxStackSize = 1;  
        setUnlocalizedName(unlocalizedName);
        setCreativeTab(HelpTab.HelperTools);
    }
    
    protected static Random growrand = new Random();
    //flavor text
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	par3List.add(EnumChatFormatting.ITALIC + "sets blocks");
    	if (stack.hasTagCompound()){
    if(whatBlockString(stack) != "null" && whatModeString(stack)!= "null"){
    	par3List.add(whatBlockString(stack) + whatModeString(stack)+ " mode");
    }}}
        
    
	//(theblock.getBlock(i1, j1+2, k1)).setTickRandomly(false);
   
	//tileEntity.setFacing((short)change);
	//tileBlock.setFacing((short)theface);
	 
	
	//Custom mode changing code
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
	
	//Expanding function
	public void EXPAND (ItemStack thestaff, EntityPlayer theplayer, World world, int x2, int y2, int z2, EnumFacing theface, float fty1, float fty2, float fty3)
	{
        BlockPos pos2 = new BlockPos(x2, y2, z2);
        
        
        //Whitelist Placement
		if (world.isAirBlock(pos2)
        		|| world.getBlockState(pos2).getBlock().getMaterial() == Material.lava 
        		|| world.getBlockState(pos2).getBlock().getMaterial() == Material.water
				|| world.getBlockState(pos2).getBlock().getMaterial() == Material.plants 
				|| world.getBlockState(pos2).getBlock().getMaterial() == Material.vine 
				|| world.getBlockState(pos2).getBlock() == Blocks.snow_layer)
        {
			//ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff)); 
			ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock_FromState(thestaff)),0, returnTMeta(thestaff)); 
        	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItemStack(stacky))
    		{
        		//destroys and returns blocks like grass
        		if (world.getBlockState(pos2).getBlock().getMaterial() == Material.vine
						|| world.getBlockState(pos2).getBlock().getMaterial() == Material.plants
						|| world.getBlockState(pos2).getBlock() == Blocks.snow_layer) 
				{
					//(world.getBlockState(pos2)).dropBlockAsItem(world,pos2, (((Chunk) world).getBlockMetadata(x2, y2, z2)), 0);
					//(world.getBlockState(pos2)).dropBlockAsItem();
					
					//int METACUCK = (world.getBlockState(pos2).getBlock()).getStateId(world.getBlockState(pos2));
					
					//(world.getBlockState(pos2).getBlock()).dropBlockAsItem(world, pos2, Status, 0);
					
					(world.getBlockState(pos2).getBlock()).dropBlockAsItem(world, pos2, world.getBlockState(pos2), 0);
				}
        		 world.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), 
        				 returnTBlock(thestaff).stepSound.getStepSound(), 
        				 (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, 
        				 returnTBlock(thestaff).stepSound.getFrequency() * 0.8F);
        		 
        		//world.setBlockState(pos2, (IBlockState) Blocks.cobblestone);
        		//world.setBlock(x2, y2, z2, Blocks.air);  
        		//world.setBlockState(pos2, BlockStateHelper.returnState(choiseID), 012);
        		world.setBlockState(pos2, BlockStateHelper.returnState(getTBlock(thestaff)), 02);
        		
        		int crackid = (getTBlock(thestaff));
                int crackmeta = (returnTMeta(thestaff));
                String particle = "blockcrack_" + crackid + "_" + crackmeta;
        		for (int pl = 0; pl < 5; ++pl)
    			{
        		float f = (this.growrand.nextFloat() - .2F) * 1.4F;
                float f1 = (this.growrand .nextFloat() - .2F) * 1.4F;
                float f2 = (this.growrand .nextFloat() - .2F) * 1.4F;
        		//world.spawnParticle(particle, x2+f, y2+f1+.3, z2+f2, 0, 0, 0);    
        		//world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, x2, y2, z2, f, f1+.3, f2, crackid,crackmeta ); 
        		world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, x2+f, y2+f1+.3, z2+f2, 0, 0, 0, crackid,crackmeta ); 
    			}
        		
        		if (!theplayer.capabilities.isCreativeMode){                	
        			InventoryUtil.consumeInventoryItemStack(stacky, theplayer.inventory);        	                        
                    thestaff.damageItem(1, theplayer);
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
	public boolean onItemUse(ItemStack thestaff, EntityPlayer theplayer, World world, BlockPos pos, EnumFacing theface, float fty1, float fty2, float fty3){
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
    	
    	if (!theplayer.isSneaking()
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
        	            EXPAND(thestaff, theplayer, world, x2, y2, z2, theface, fty1, fty2, fty3);
        			}

        			world.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
        					(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
    	        		 return true;
    	               	           
    	        }
    	///////////////////////////////////////////////////////////////////////////
    	/**    ~~~~~~~~                  Wall Mode               ~~~~~~~~~~~~~  **/
    	/////////////////////////////////////////////////////////////////////////
    	

    	if (!theplayer.isSneaking()
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
        	            EXPAND(thestaff, theplayer, world, x2, y2, z2, theface, fty1, fty2, fty3);
        	            
    	        	
    	          
                			}
            			}
        			}
        			world.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
        					(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
        			return true;
    	}
    
    	/////////////////////////////////////////////////////////////////////////
    	/** Matching Mode 6 **/
    	/////////////////////////////////////////////////////////////////////////
    	if (!theplayer.isSneaking()
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
        	            //if (world.getBlockMetadata(x2, y2, z2) == returnTMeta(thestaff) && 
        	            		//world.getBlockState(x2, y2, z2) == returnTBlock(thestaff)){
        	            
        	            BlockPos pos3 = new BlockPos(x2, y2, z2);	
        	            if (
        	            		//BlockStateHelper.getMetafromState(world, pos3) == returnTMeta(thestaff) && 
            	            	//BlockStateHelper.getBlockfromState(world, pos3) == returnTBlock(thestaff)
            	            	//|| 
            	            	world.getBlockState(pos3) ==BlockStateHelper.returnState(getTBlock(thestaff))){
        	            	
        	            	/////////////////////////
            	            /**Expanding function**/
            	            ///////////////////////
            	            EXPAND(thestaff, theplayer, world, xT4, yT4, zT4, theface, fty1, fty2, fty3);
        	            
                			}
        	            }
        			}        	
        		}
        			world.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
        					(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
        			return true;
    	}
        
        //I don't actually know what this does, so that's kill
        /**
        if (!theplayer.canPlayerEdit(pos1, theface, thestaff))
        {
            return false;
        }
        **/
        
    	
    	/////////////////////////////////
        /** If Sneaking Select Block **/
        ///////////////////////////////
    	if (theplayer.isSneaking())
    	{ 
    		//setTBlock(thestaff, world.getBlock(x1, y1, z1).getIdFromBlock(world.getBlock(x1, y1, z1)));
    		//setTMeta(thestaff,world.getBlockMetadata(x1, y1, z1)); 		
    		//
    		
    		setTBlock(thestaff, BlockStateHelper.returnID(world, pos1)); 
    		setTMeta(thestaff, BlockStateHelper.getMetafromState(world, pos1)); 	
    		
    		//ItemStack stacky2 = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff)); 
    		
    		
    		world.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
    				(double)z1 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
    		
    		
    		
    		 setOffMode(thestaff, 4);
 			return true;
    	}
    	else
    	{ 
    		
    		return false;     		
    	}
    	
    }
	}
    
    
}


