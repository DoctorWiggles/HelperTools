package helpertools.tools;

import java.util.List;
import java.util.Random;

import helpertools.HelpTab;
import helpertools.Helpertoolscore;
import helpertools.util.InventoryUtil;
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
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkEvent;

public class ItemStaffofExpansion2 extends ItemSpade
{
    private static final String __OBFID = "CL_00000035";

  
   

    public ItemStaffofExpansion2(ToolMaterial material)
    {
    	super (material);
        this.maxStackSize = 1;  
        setUnlocalizedName("staffofexpansion");
        //setCreativeTab(Helpertoolscore.HelperTools);
        setCreativeTab(HelpTab.HelperTools);
        setTextureName("helpertools:wand7");
        //this.setMaxDamage(1428);
    }
    
    protected static Random growrand = new Random();
    //flavor text
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	
    	par3List.add(EnumChatFormatting.ITALIC + "sets blocks");
    if(whatBlockString(stack) != "null" && whatModeString(stack)!= "null"){
    	par3List.add(whatBlockString(stack) + whatModeString(stack)+ " mode");
    }
    }
    /*
    public String getItemStackDisplayName(ItemStack p_77653_1_)
    {
        return (EnumChatFormatting.GREEN + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(p_77653_1_) + ".name")).trim();
    }
    */
    
    
    ////////////////////////////////////////////////////////////////////////
	  public int getSpinDec(ItemStack itemStack) {
			if (itemStack.stackTagCompound == null) {
				return 360;
			}

			return itemStack.stackTagCompound.getShort("SpinDec");

		}		
		public void setSpinDec(ItemStack itemStack, short Value) {
			if (itemStack.stackTagCompound == null) {
				itemStack.setTagCompound(new NBTTagCompound());
			}

			itemStack.stackTagCompound.setShort("SpinDec",  Value);			
		}
/////////////////////////////////////////////////////////////////////
  public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
  	
  	if(entity.worldObj.isRemote){
  		return;  	}
  	//Item item = entity.inventory.currentItem;
  	if (!(entity instanceof EntityPlayer)){
			return;
		}
  	if(((EntityPlayer) entity).getCurrentEquippedItem() == null){
  		return;
  		}
  		
  	 ItemStack item = ((EntityPlayer) entity).getCurrentEquippedItem();
  	 Item item2 = item.getItem();
  	if (!(item2 == this)){
  		return;
  	}
  	//ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
	//			"Timer: " + (getSpinDec(stack)), new Object[0]);
	//	((EntityPlayer) entity)
	//			.addChatComponentMessage(chatcomponenttranslation);
  	/**
  	int eff = EnchantmentHelper.getEnchantmentLevel(32, stack);
  	
  	 System.out.println("Testing levels " + eff);
  	 **/
		if (getSpinDec(stack)== 0){
			setSpinDec(stack, (short) 360);
		}
		else 
			setSpinDec(stack, (short)(getSpinDec(stack)-1));
			//timer--;
		
  			
  }
  	public String whatModeString(ItemStack stack){	  
  		String modestring = "null";
  		
  		if (getMode(stack) == 2){
  			modestring = "Pillar";
  			}
  			else if(getMode(stack) == 4){
  			modestring = "Wall";
  			}
  			else if(getMode(stack) == 6){
  			modestring = "Matching";
  			}
  			else{
  			modestring = "null";
  			}  
  		return modestring;
  	};
   
  	public String whatBlockString(ItemStack stack){  		
  		String modestring = "null";
  		
  		if (getTBlock(stack) == 0){
  			modestring = "null";
  			}  		
  			if (getTBlock(stack) != 0){
  			modestring = returnTBlock(stack).getLocalizedName() + " ";  			
  			return modestring;
  			} 
  		return modestring;
  		
  	};
 
    /////////////////////////////////////////////////////////////
    public int getMode(ItemStack itemStack)
   	{
   		if(itemStack.stackTagCompound == null)
   		{
   			return 0;
   		}

   		return itemStack.stackTagCompound.getInteger("mode");
   		
   	}
      
      public boolean isMetadataSpecific(ItemStack itemStack)
   	{
   		return false;
   	}
      
      ///////////////////////////////////////////
      public void setMode(ItemStack itemStack, int Value)
   	{
   		if(itemStack.stackTagCompound == null)
   		{
   			itemStack.setTagCompound(new NBTTagCompound());
   		}

   		itemStack.stackTagCompound.setInteger("mode",  Value );
   		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
   	} 
   	///////////////////
      
    public int getTBlock(ItemStack itemStack)
	{
		if(itemStack.stackTagCompound == null)
		{
			return 0;
		}

		return itemStack.stackTagCompound.getInteger("TBlock");
		
	}
	public void setTBlock(ItemStack itemStack, int Value)
	{
		if(itemStack.stackTagCompound == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		itemStack.stackTagCompound.setInteger("TBlock",  Value );
		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	} 
	////////
	public int getTMeta(ItemStack itemStack)
	{
		if(itemStack.stackTagCompound == null)
		{
			return 0;
		}

		return itemStack.stackTagCompound.getInteger("TMeta");
		
	}
	public void setTMeta(ItemStack itemStack, int Value)
	{
		if(itemStack.stackTagCompound == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		itemStack.stackTagCompound.setInteger("TMeta",  Value );
		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	} 
	
	
	//////
	public int returnTMeta(ItemStack thestaff)
    {
		return getTMeta(thestaff);
		
    }
	
	public Block returnTBlock(ItemStack thestaff)
	{
		return Block.getBlockById(getTBlock(thestaff));
	}
	
    
	/** Offmode here prevents getblock from double dipping into switch mode code because i suck**/
	// ///////////////////////////////////////////////////////////
		public int getOffMode(ItemStack itemStack) {
			if (itemStack.stackTagCompound == null) {
				return 0;
			}

			return itemStack.stackTagCompound.getInteger("OffMode");

		}		
		public void setOffMode(ItemStack itemStack, int Value) {
			if (itemStack.stackTagCompound == null) {
				itemStack.setTagCompound(new NBTTagCompound());
			}

			itemStack.stackTagCompound.setInteger("OffMode", Value);			
		}
    
    //////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////
    
    /** Tool levels **/
		public int getToolLevel(ItemStack itemStack) {
			if (itemStack.stackTagCompound == null) {
				return 0;
			}

			return itemStack.stackTagCompound.getInteger("ToolLevel");

		}		
		public void setToolLevel(ItemStack itemStack, int Value) {
			if (itemStack.stackTagCompound == null) {
				itemStack.setTagCompound(new NBTTagCompound());
			}

			itemStack.stackTagCompound.setInteger("ToolLevel", Value);			
		}
		
		public int getEff2Level(ItemStack itemStack) {
			if (itemStack == null) {
				return 0;
			}
			int eff = EnchantmentHelper.getEnchantmentLevel(32, itemStack);	
			
			if ((eff%2)!=0){
				//odd
				eff = eff-1;
			}
			if (eff <= 0){
				eff = 0;
			}
			return eff;

		}	
		
    
	//(theblock.getBlock(i1, j1+2, k1)).setTickRandomly(false);
   
	//tileEntity.setFacing((short)change);
	//tileBlock.setFacing((short)theface);
	 
	
	//Generic tool stuff
	public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        if ((double)p_150894_3_.getBlockHardness(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D)
        {
            p_150894_1_.damageItem(1, p_150894_7_);
        }

        return true;
    }
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
        p_77644_1_.damageItem(2, p_77644_3_);
        return true;
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
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Extend Mode", new Object[0]);
				((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
				setMode(stack,2);
				return true;
			}
			else if (getMode(stack) == 2)
			{			
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Flat Mode", new Object[0]);
				((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
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
    public boolean onItemUse(ItemStack thestaff, EntityPlayer theplayer, World theblock, int x1, int y1, int z1, int theface, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	//Modifies size based on tool level
    	int eff = EnchantmentHelper.getEnchantmentLevel(32, thestaff);
    	int pillar = (getToolLevel(thestaff)+ eff+ 3);
    	int eff2 = (getEff2Level(thestaff));
    	int wall = (getToolLevel(thestaff)+ eff2+ 3);
    		
    	//if operation is successful set a flag
    	//boolean successful = false;
    	float sound1 = (this.growrand .nextFloat()) ;
    	
    	//prevents nulls etc
    	if (getMode(thestaff)== 0)
   		{
		   setMode(thestaff, 2);
   		}
    	
    	//////////////////////////////////////////////////////////////////////////////////

		//Wall mode 4
		/** if this is true it performs this action **/
    	//Checks to make sure you're not sneaking
    	
    	if (!theplayer.isSneaking()
    			&& getMode(thestaff) == 2)
    	{ 
    		//int successful = 0;
    	            //--z1; 
    	            //BOTTOM FACE
    	            //W/E_T/B_N/S
    		
        			for (int l = 0; l < pillar; ++l)
        			{        		
        				int x3 = 0;
        	            int y3 = 0;
        	            int z3 = 0;
        	    		//////////////////////////////////////
        	    		 switch(theface){
        	    		 //Bottom
        	    		 case 0:	y3 = y3 -1 - l; 			 
        	    			 break;
        	    		//Top
        	    		 case 1:	y3 = y3 +1 + l; 			 
        	    			 break;
        	    		//North
        	    		 case 2:	z3 = z3 -1 - l;
        	    			 break;
        	    		//South
        	    		 case 3:	z3 = z3 +1 + l; 
        	    			 break;
        	    		//West
        	    		 case 4:   	x3 = x3 -1 -l; 			 
        	    			 break;
        	    		//East
        	    		 case 5:	x3 = x3 +1 +l;  
        	    			 break;
        	    			 default:
        	    				
        		 
        	    			 
        	    		 }
        				int x2 = x1 + x3;
        	            int y2 = y1 + y3;
        	            int z2 = z1 + z3;
        	            
        	            //Whitelist Placement
        				if (theblock.isAirBlock(x2, y2, z2)
        	            		|| theblock.getBlock(x2, y2, z2).getMaterial() == Material.lava 
        	            		|| theblock.getBlock(x2, y2, z2).getMaterial() == Material.water
        						|| theblock.getBlock(x2, y2, z2).getMaterial() == Material.plants 
        						|| theblock.getBlock(x2, y2, z2).getMaterial() == Material.vine 
        						|| theblock.getBlock(x2, y2, z2) == Blocks.snow_layer)
        	            {
        					ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff)); 
        	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItemStack(stacky))
        	        		{
        	            		//destroys and returns blocks like grass
        	            		if (theblock.getBlock(x2, y2, z2).getMaterial() == Material.vine
        	    						|| theblock.getBlock(x2, y2, z2).getMaterial() == Material.plants
        	    						|| theblock.getBlock(x2, y2, z2) == Blocks.snow_layer) 
        						{
        							(theblock.getBlock(x2, y2, z2)).dropBlockAsItem(theblock,x2, y2, z2, (theblock.getBlockMetadata(x2, y2, z2)), 0);
        						}
        	            		 theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
        	            		 theblock.setBlock(x2, y2, z2, Blocks.cobblestone);
        	            		//theblock.setBlock(x2, y2, z2, Blocks.air);
        	            		theblock.setBlock(x2, y2, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 012);
        	            		//successful = 1;
        	            		
        	            		if (!theplayer.capabilities.isCreativeMode){                	
        	            			InventoryUtil.consumeInventoryItemStack(stacky, theplayer.inventory);        	                        
        	                        thestaff.damageItem(1, theplayer);
        	                        }	
        	        		}
        	            
        	            }
        			}
        			
    	            //////////////////
    	            //if (successful == 0){

    	        		theblock.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
    	        				(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
    	        		//successful = 0;
    	        		 return true;
    	            //}
    	            /**
    	            if (successful == 1){

    	            	theplayer.worldObj.playSoundAtEntity(theplayer, "mob.endermen.portal", 1.2F, .5F+sound1);
    	            	successful = 0;
    	            	 return true;
    	            }
    	            **/
    	            //East/west axis, Vertical axis, North/south axis
    	            //return true;
    	        }
    	///////////////////////////////////////////////////////////////////////////
    	/**    ~~~~~~~~                  Wall Mode               ~~~~~~~~~~~~~  **/
    	/////////////////////////////////////////////////////////////////////////
    	

    	if (!theplayer.isSneaking()
    			&& getMode(thestaff) == 4)
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
        					
        				int offy = (wall-1)/2;
        				int x3 = 0;
        	            int y3 = 0;
        	            int z3 = 0;
        	    		//////////////////////////////////////
        	    		 switch(theface){
        	    		 //Bottom
        	    		 case 0:	z3 = z3 +offy - l; 
	    			 				x3 = x3 +offy - ml;        	    			 		
        	    			 break;
        	    		//Top
        	    		 case 1:	z3 = z3 +offy - l; 
        	    			 		x3 = x3 +offy - ml;
        	    			 break;
        	    		//North
        	    		 case 2:	y3 = y3 +offy - l; 
			 						x3 = x3 +offy - ml; 
        	    			 break;
        	    		//South
        	    		 case 3:	y3 = y3 +offy - l; 
	 								x3 = x3 +offy - ml;
	 						break;
        	    		//West
        	    		 case 4:   y3 = y3 +offy - l; 
	 							   z3 = z3 +offy - ml;			 
        	    			 break;
        	    		//East
        	    		 case 5:	 y3 = y3 +offy - l; 
						   			 z3 = z3 +offy - ml;	
        	    			 break;
        	    			 default: 
        	    		 }
        				int x2 = x1 + x3;
        	            int y2 = y1 + y3;
        	            int z2 = z1 + z3;
        	            
        	            //Whitelist Placement
        				if (theblock.isAirBlock(x2, y2, z2)
        	            		|| theblock.getBlock(x2, y2, z2).getMaterial() == Material.lava 
        	            		|| theblock.getBlock(x2, y2, z2).getMaterial() == Material.water
        						|| theblock.getBlock(x2, y2, z2).getMaterial() == Material.plants 
        						|| theblock.getBlock(x2, y2, z2).getMaterial() == Material.vine 
        						|| theblock.getBlock(x2, y2, z2) == Blocks.snow_layer)
        	            {
        					ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff)); 
        	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItemStack(stacky))
        	        		{
        	            		//destroys and returns blocks like grass
        	            		if (theblock.getBlock(x2, y2, z2).getMaterial() == Material.vine
        	    						|| theblock.getBlock(x2, y2, z2).getMaterial() == Material.plants
        	    						|| theblock.getBlock(x2, y2, z2) == Blocks.snow_layer) 
        						{
        							(theblock.getBlock(x2, y2, z2)).dropBlockAsItem(theblock,x2, y2, z2, (theblock.getBlockMetadata(x2, y2, z2)), 0);
        						}
        	            		 theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
        	            		 theblock.setBlock(x2, y2, z2, Blocks.cobblestone);
        	            		//theblock.setBlock(x2, y2, z2, Blocks.air);
        	            		theblock.setBlock(x2, y2, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 012);
        	            		//successful = 1;
        	            		
        	            		if (!theplayer.capabilities.isCreativeMode){                	
        	            			InventoryUtil.consumeInventoryItemStack(stacky, theplayer.inventory);        	                        
        	                        thestaff.damageItem(1, theplayer);
        	                        }	
        	        			}
        	           
                			   }
                			}
        	            }
        			}
        			
    	            
    	        	theblock.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
    	        				(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
    	        		
    	        	return true;
    	          
    	        }
    	
   ////////////// 	/////////////////////	////////// /////////////////////////////////////////////////////////////
    		 /** if (theface == 1)
    	        {
    	            ++y1;
    	            //TOP FACE
 	            //W/E_T/B_N/S
 	            if (theblock.isAirBlock(x1-1, y1-1, z1)
 	            		|| theblock.getBlock(x1-1, y1-1, z1).getMaterial() == Material.lava 
 	            		|| theblock.getBlock(x1-1, y1-1, z1).getMaterial() == Material.water)
 	            {
 	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
 	        		{
 	            		 theblock.playSoundEffect((double)((float)x1-1 + 0.5F), (double)((float)y1-1 + 0.5F), (double)((float)z1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
 	            		 theblock.setBlock(x1-1, y1-1, z1, Blocks.cobblestone);
 	            		 theblock.setBlock(x1-1, y1-1, z1, Blocks.air);
 	            		theblock.setBlock(x1-1, y1-1, z1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
 	            		if (!theplayer.capabilities.isCreativeMode){                	
 	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
 	                        thestaff.damageItem(1, theplayer);
 	                        }	
 	        		}
 	            
 	            }
    	**/
////////////////////////////////////////////////////////////////////////////////////////////

		
		/**
		 * If these all are false it returns with a default action
		 */
		
        
        //////////////////////////
        if (!theplayer.canPlayerEdit(x1, y1, z1, theface, thestaff))
        {
            return false;
        }
        
    	
    	//////
    	if (theplayer.isSneaking())
    	{ 
    		setTBlock(thestaff, theblock.getBlock(x1, y1, z1).getIdFromBlock(theblock.getBlock(x1, y1, z1)));
    		setTMeta(thestaff,theblock.getBlockMetadata(x1, y1, z1)); 		
    		//
    		
    		theblock.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
    				(double)z1 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
    		
    		 setOffMode(thestaff, 4);
 			return true;
    	}
    	else
    	{ 
    		
    		return false;     		
    	}
    	//}
    	//return false;
    	
    }

    //Standard line that allows 3D rendering
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    
    
}