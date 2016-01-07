package helpertools.tools;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;

import helpertools.Common_Registry;
import helpertools.ConfigurationFactory;
import helpertools.HelpTab;
import helpertools.Helpertoolscore;
import helpertools.util.Coordinate;
import helpertools.util.InventoryUtil;
import helpertools.util.KeyBindings;
import helpertools.util.Whitelist_Util;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemStaffofExpansion extends Tool_Base_Expander
{
	
	
    public ItemStaffofExpansion(ToolMaterial material)
    {
    	super (1F, material, Common_Registry.properharvest);
        this.maxStackSize = 1;  
        setUnlocalizedName("staffofexpansion");
        //setCreativeTab(Helpertoolscore.HelperTools);
        setCreativeTab(HelpTab.HelperTools);
        setTextureName("helpertools:wand7");
    }
    
    protected static Random growrand = new Random();
    
	//(world.getBlock(i1, j1+2, k1)).setTickRandomly(false);
   
	//tileEntity.setFacing((short)change);
	//tileBlock.setFacing((short)side);
	
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
			case 2: Messy = "Pillar Mode";
			break;
			case 4: Messy = "Wall Mode";
					loud1 = 0.3;
					loud2 = 3;
			break;
			case 6: Messy = "Matching Mode";
			break;
			default:
			break;
			}
			entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", (float)(loud1), (float)(loud2));
			//config hook
		    if(ConfigurationFactory.ToolModeMesseges == true){		    
			ChatComponentTranslation chatmessy = new ChatComponentTranslation(EnumChatFormatting.GRAY + Messy, new Object[0]);
			((EntityPlayer) entityLiving).addChatComponentMessage(chatmessy);
		    }
			
			return true;
    	}
		}
		if (getOffMode(stack)== 4)
   		{ setOffMode(stack, 2); 
   		}
		 return false;
    }
	
	//Witelist and placement of blocks
	public void Placement (ItemStack thestaff, EntityPlayer player, World world, int x, int y, int z, int side){
		
		//Whitelist Placement
		if (world.isAirBlock(x, y, z)
        		|| world.getBlock(x, y, z).getMaterial() == Material.lava 
        		|| world.getBlock(x, y, z).getMaterial() == Material.water
				|| world.getBlock(x, y, z).getMaterial() == Material.plants 
				|| world.getBlock(x, y, z).getMaterial() == Material.vine 
				|| world.getBlock(x, y, z) == Blocks.snow_layer)
        {
			
			ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff));  
			//Item item = Item.getItemFromBlock(returnTBlock(thestaff));    
			Boolean whitelist_flag;
			whitelist_flag = Whitelist_Util.Block_Whitelist(returnTBlock(thestaff), player, returnTMeta(thestaff));
			
        	if(player.capabilities.isCreativeMode || player.inventory.hasItemStack(stacky )
        			|| whitelist_flag)
    		{
        		//destroys and returns blocks like grass
        		if (world.getBlock(x, y, z).getMaterial() == Material.vine
						|| world.getBlock(x, y, z).getMaterial() == Material.plants
						|| world.getBlock(x, y, z) == Blocks.snow_layer) 
				{
					(world.getBlock(x, y, z)).dropBlockAsItem(world,x, y, z, (world.getBlockMetadata(x, y, z)), 0);
				}
        		 world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
        		 world.setBlock(x, y, z, Blocks.cobblestone);
        		//world.setBlock(x, y, z, Blocks.air);
        		world.setBlock(x, y, z, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 012);
        		
        		int crackid = (getTBlock(thestaff));
                int crackmeta = (returnTMeta(thestaff));
                String particle = "blockcrack_" + crackid + "_" + crackmeta;
        		for (int pl = 0; pl < 5; ++pl)
    			{
        		float f = (this.growrand.nextFloat() - .2F) * 1.4F;
                float f1 = (this.growrand .nextFloat() - .2F) * 1.4F;
                float f2 = (this.growrand .nextFloat() - .2F) * 1.4F;
        		world.spawnParticle(particle, x+f, y+f1+.3, z+f2, 0, 0, 0);        	            		
    			}
        		//successful = 1;
        		if (!player.capabilities.isCreativeMode){                	
        			if(!whitelist_flag)InventoryUtil.consumeInventoryItemStack(stacky, player.inventory); 
        			if(whitelist_flag){
        				Whitelist_Util.Consume_Whitelist(stacky, player, returnTBlock(thestaff), returnTMeta(thestaff));
        				//player.inventory.consumeInventoryItem(item);
        				}  
                    thestaff.damageItem(1, player);
                    }	
    			}
       
			   }
		
	}	
	
	//Witelist and placement of blocks
		public boolean Placement_Simulated (ItemStack thestaff, EntityPlayer player, World world, int x, int y, int z, int side){
			
			//Whitelist Placement
			if (world.isAirBlock(x, y, z)
	        		|| world.getBlock(x, y, z).getMaterial() == Material.lava 
	        		|| world.getBlock(x, y, z).getMaterial() == Material.water
					|| world.getBlock(x, y, z).getMaterial() == Material.plants 
					|| world.getBlock(x, y, z).getMaterial() == Material.vine 
					|| world.getBlock(x, y, z) == Blocks.snow_layer)
	        {
	        		return true;
	    			
	    			}			
			return false;
		}
		
		
	public Set<Coordinate> pillar_selection(ItemStack thestaff, EntityPlayer player, World world, int x1, int y1, int z1, int side, boolean simulate) {
		
		Set<Coordinate> coordinates = new HashSet<Coordinate>();
		int pillar = (getToolLevel(thestaff) + 3);
		int wall = (getToolLevel(thestaff) + 2);
		int amount= 0;
		ItemStack stacky = new ItemStack(Item.getItemFromBlock(returnTBlock(thestaff)), 0,returnTMeta(thestaff));
		ItemStack stacky_f = stacky;
		stacky_f = Whitelist_Util.Whitelist_stack(stacky, player,returnTBlock(thestaff), returnTMeta(thestaff));

		amount = InventoryUtil.amount_Scan(stacky_f, player);
		
		for (int l = 0; l < pillar; ++l) {
			int x3 = 0;
			int y3 = 0;
			int z3 = 0;
			// ////////////////////////////////////
			switch (side) {
			// Bottom
			case 0: y3 = y3 - 1 - l; break;
			// Top
			case 1: y3 = y3 + 1 + l; break;
			// North
			case 2: z3 = z3 - 1 - l; break;
			// South
			case 3: z3 = z3 + 1 + l; break;
			// West
			case 4: x3 = x3 - 1 - l; break;
			// East
			case 5: x3 = x3 + 1 + l; break;
			default:
			}
			int x2 = x1 + x3;
			int y2 = y1 + y3;
			int z2 = z1 + z3;
			
			if (amount > 0) {
				amount--;
				
			if (Placement_Simulated(thestaff, player, world, x2, y2,z2, side)) {
				if (simulate) {
					coordinates.add(new Coordinate(x2, y2, z2));}
				if (!simulate) {
					
					//Placement_func(thestaff, player, world, x2, y2,z2, side);
					Placement(thestaff, player, world, x2, y2,z2, side);}
			}
			}
			}
		if (!simulate) {
			world.playSoundEffect((double) x1 + 0.5D, (double) y1 + 0.5D,
					(double) z1 + 0.5D, "fire.ignite", .4F,
					itemRand.nextFloat() * 0.4F + 0.8F);
		}
		
		return coordinates;

	}	
	
	public Set<Coordinate> wall_selection(ItemStack thestaff, EntityPlayer player, World world, int x1, int y1, int z1, int side, boolean simulate) {
		 
		Set<Coordinate> coordinates = new HashSet<Coordinate>();
		int wall = (getToolLevel(thestaff) + 2);
		int amount= 0;
		ItemStack stacky = new ItemStack(Item.getItemFromBlock(returnTBlock(thestaff)), 0,returnTMeta(thestaff));
		ItemStack stacky_f = stacky;
		stacky_f = Whitelist_Util.Whitelist_stack(stacky, player,returnTBlock(thestaff), returnTMeta(thestaff));

		amount = InventoryUtil.amount_Scan(stacky_f, player);
		
    			for (int l = 0; l < wall; ++l)
    			{   
    				for (int ml = 0; ml < wall; ++ml)
        			{ 
    					for (int sl = 0; sl < 4; ++sl)
            			{ 
    						int sectA =1; int sectB =1;
    						switch(sl){
    						case 0:
    							break;
    						case 1: sectA= -1; break;        							
    						case 2:	sectA= -1;
    								sectB= -1;
    							break;
    						case 3:	sectB= -1; 	break;
    							
    						}
    						//z3 = z3 +offy - l*sectA; 
	    			 		//x3 = x3 +offy - ml*sectB;
    					
    				//int offy = (wall-1)/2;
    				int x3 = 0; int y3 = 0; int z3 = 0;
    	    		//////////////////////////////////////
    	    		 switch(side){
    	    		 //Bottom
    	    		 case 0:	z3 = z3 - l*sectA; 
    			 				x3 = x3 - ml*sectB;        	    			 		
    	    			 break;
    	    		//Top
    	    		 case 1:	z3 = z3 - l*sectA; 
    	    			 		x3 = x3 - ml*sectB;
    	    			 		
    	    			 break;
    	    		//North
    	    		 case 2:	y3 = y3 - l*sectA; 
		 						x3 = x3 - ml*sectB; 
    	    			 break;
    	    		//South
    	    		 case 3:	y3 = y3 - l*sectA; 
 								x3 = x3 - ml*sectB;
 						break;
    	    		//West
    	    		 case 4:   y3 = y3 - l*sectA; 
 							   z3 = z3 - ml*sectB;			 
    	    			 break;
    	    		//East
    	    		 case 5:	 y3 = y3 - l*sectA; 
					   			 z3 = z3 - ml*sectB;	
    	    			 break;
    	    			 default: 
    	    		 }
    				int x2 = x1 + x3;
    	            int y2 = y1 + y3;
    	            int z2 = z1 + z3;
    	            
    	            if (amount > 0) {
    					amount--;
    					
    				if (Placement_Simulated(thestaff, player, world, x2, y2,z2, side)) {
    					if (simulate) {
    						coordinates.add(new Coordinate(x2, y2, z2));}
    					if (!simulate) {
    						Placement(thestaff, player, world, x2, y2,z2, side);}
    				}
    				}
            			}
    	            }
    			}
    			if (!simulate) {world.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
	        				(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);}
	        		
	        	return coordinates;
	}
	
	public Set<Coordinate> matching_selection(ItemStack thestaff, EntityPlayer player, World world, int x1, int y1, int z1, int side, boolean simulate) {
		 
		
		Set<Coordinate> coordinates = new HashSet<Coordinate>();
		int wall = (getToolLevel(thestaff) + 2);
		int amount= 0;
		ItemStack stacky = new ItemStack(Item.getItemFromBlock(returnTBlock(thestaff)), 0,returnTMeta(thestaff));
		ItemStack stacky_f = stacky;
		stacky_f = Whitelist_Util.Whitelist_stack(stacky, player,returnTBlock(thestaff), returnTMeta(thestaff));

		amount = InventoryUtil.amount_Scan(stacky_f, player);
		
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
    				int x3 = 0;  int y3 = 0;  int z3 = 0;
    	            //Matching block offset
    	            int x5 = 0; int y5 = 0;  int z5 = 0; 
    	    		//////////////////////////////////////
    	    		 switch(side){
    	    		 //Bottom
    	    		 case 0:	z3 = z3 - l*sectA; 
    			 				x3 = x3 - ml*sectB; 
    			 				y5 = y5 - 1;
    	    			 break;
    	    		//Top
    	    		 case 1:	z3 = z3 - l*sectA; 
    	    			 		x3 = x3 - ml*sectB;
    	    			 		y5 = y5 + 1;        	    			 		
    	    			 break;
    	    		//North
    	    		 case 2:	y3 = y3 - l*sectA; 
		 						x3 = x3 - ml*sectB; 
		 						z5 = z5 - 1;
    	    			 break;
    	    		//South
    	    		 case 3:	y3 = y3 - l*sectA; 
 								x3 = x3 - ml*sectB;
 								z5 = z5 + 1;
 						break;
    	    		//West
    	    		 case 4:   y3 = y3 - l*sectA; 
 							   z3 = z3 - ml*sectB;
 							   x5 = x5 - 1;
    	    			 break;
    	    		//East
    	    		 case 5:	y3 = y3 - l*sectA; 
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
    	            if (world.getBlockMetadata(x2, y2, z2) == returnTMeta(thestaff) && 
    	            		world.getBlock(x2, y2, z2) == returnTBlock(thestaff)){
    	            
    	            	if (amount > 0) {
        					amount--;
        					
        				if (Placement_Simulated(thestaff, player, world, xT4, yT4, zT4, side)) {
        					if (simulate) {
        						coordinates.add(new Coordinate(xT4, yT4, zT4));}
        					
        					if (!simulate) {
        						Placement(thestaff, player, world, xT4, yT4, zT4, side);}
        				}
        				}
            			}
            			}
    	            }
    			}
    			
    			if (!simulate) {world.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
        				(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);}
        		
    			return coordinates;
	}
	
    public boolean onItemUse(ItemStack thestaff, EntityPlayer player, World world, int x1, int y1, int z1, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	int pillar = (getToolLevel(thestaff)+ 3);
    	int wall = (getToolLevel(thestaff)+ 2); 
    	
    	int mode = getMode(thestaff);
    	
    	if(!player.isSneaking() && item_amount(player,thestaff ) > 0){
    		
    		if(mode == 2){pillar_selection(thestaff, player, world, x1, y1, z1, side, false); }
    		if(mode == 4){wall_selection(thestaff, player, world, x1, y1, z1, side, false); }
    		if(mode == 6){matching_selection(thestaff, player, world, x1, y1, z1, side, false); }
    		return true;
    	}
        //////////////////////////
        if (!player.canPlayerEdit(x1, y1, z1, side, thestaff))
        {
            return false;
        }
        
    	if (player.isSneaking())
    	{ 
    		setTBlock(thestaff, world.getBlock(x1, y1, z1).getIdFromBlock(world.getBlock(x1, y1, z1)));
    		setTMeta(thestaff,world.getBlockMetadata(x1, y1, z1)); 		
    		//    		
    		ItemStack stacky2 = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff)); 
    		    		
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