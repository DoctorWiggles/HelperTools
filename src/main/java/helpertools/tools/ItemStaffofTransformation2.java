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
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemStaffofTransformation2 extends ItemSpade
{
    public ItemStaffofTransformation2(ToolMaterial material)
    {
    	super (material);
        this.maxStackSize = 1;  
        setUnlocalizedName("staffoftransformation2");
        //setCreativeTab(Helpertoolscore.HelperTools);
        setCreativeTab(HelpTab.HelperTools);
        setTextureName("helpertools:wand8");
    }
    
    protected static Random growrand = new Random();
    
    //Adds fancy flavor text for item
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    par3List.add(EnumChatFormatting.ITALIC + "swaps blocks");
    if(whatBlockString(stack) != "null" && whatModeString(stack)!= "null"){
    	par3List.add(whatBlockString(stack) + whatModeString(stack)+ " mode");
    }
    }
    //
    /**Declarations, that are used for processing later **/
    //
    public String whatModeString(ItemStack stack){	  
  		String modestring = "null";
  		
  		if (getMode(stack) == 2){
  			modestring = "Single";
  		}
  		else if(getMode(stack) == 4){
  			modestring = "Cube";
  		}
  		else if(getMode(stack) == 6){
  			modestring = "Wall";
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
    
    
	// ///////////////////////////////////////////////////////////
	public int getMode(ItemStack itemStack) {
		if (itemStack.stackTagCompound == null) {
			return 0;
		}

		return itemStack.stackTagCompound.getInteger("mode");

	}

	public boolean isMetadataSpecific(ItemStack itemStack) {
		return false;
	}

	// /////////////////////////////////////////
	public void setMode(ItemStack itemStack, int Value) {
		if (itemStack.stackTagCompound == null) {
			itemStack.setTagCompound(new NBTTagCompound());
		}

		itemStack.stackTagCompound.setInteger("mode", Value);
		// this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	}

	// /////////////////
	
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
	
    
	/** Offmode here prevents getblock from double dipping into switch mode code**/
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
				eff = eff/2;
				if (eff <= 0){
					eff = 0;
				}
				return eff;

			}	

	//int mode = 2;
	//
	//private Block Tblock = Blocks.air;
	//
	//private int Tmeta = 0;
	//
	private Block Gblock = Blocks.air;
	//
	private int Gmeta = 0;	
	
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
	//
	/** Custom loop, during swings that allows modes to change **/
	//Checks if sneaking and which mode it currently is in to change further
	//
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
		if (entityLiving.isSneaking() && getOffMode(stack)== 2)
    	{ 
			if (getMode(stack) == 6)
			{
		   		//entityLiving.playSound("mob.chicken.plop", 3.0F, .3F);
		   		entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 3F, .3F);
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Mass Mode", new Object[0]);
				((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
				setMode(stack,4);
				return true;
			}
			else if (getMode(stack) == 4)
			{
				entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 3F, 0.3F);
		   		ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Single Mode", new Object[0]);
				((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
				setMode(stack, 2);
			}
			else if (getMode(stack) == 2)
			{			
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Wall Mode", new Object[0]);
				((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
				entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", .3F, 3.0F);
			setMode(stack, 6);
			}
			
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
    	int wall = (getToolLevel(thestaff)+ eff2+ 2);
    		
    	if (getMode(thestaff)== 0)
   		{
		   setMode(thestaff, 2);
   		}
    	if (getOffMode(thestaff)== 0)
   		{
		   setOffMode(thestaff, 2);
   		}
    	
    	//Adds the block you are looking to the boolean, it is checked later or droped etc.
    	//For the pallete
    	Gblock = theblock.getBlock(x1, y1, z1);
		Gmeta = theblock.getBlockMetadata(x1, y1, z1); 
		
		//Small mode 2
		/** if this is true it performs this action **/
		if (!theplayer.isSneaking() && (returnTBlock(thestaff)) != Blocks.air    			
      			&& !theplayer.capabilities.isCreativeMode && Gblock != Blocks.bedrock
      			&& getMode(thestaff) == 2
      					||
      			!theplayer.isSneaking() && (returnTBlock(thestaff)) != Blocks.air    			
      			&& theplayer.capabilities.isCreativeMode
      			&& getMode(thestaff) == 2)
      	{ 
			int x2= x1;
	    	int y2= y1;
	    	int z2= z1;
      		if (theblock.getBlock(x2, y2, z2) != (returnTBlock(thestaff))
              		|| theblock.getBlockMetadata(x2, y2, z2) != (returnTMeta(thestaff))
                       && theblock.getBlock(x2, y2, z2) == (returnTBlock(thestaff)))
      		{
      			//The block that is being transformed
              	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
          		{	
              		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
              		theblock.setBlock(x2, y2, z2, Blocks.air);
              		theblock.setBlock(x2, y2, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
              		Gblock.dropBlockAsItem(theblock, x2, y2, z2, Gmeta, 0);
              		
              		if (!theplayer.capabilities.isCreativeMode){                	
                          theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                          thestaff.damageItem(1, theplayer);
                          }
              		return true;
          		}
      		}
      		else
      		{

          		theblock.playSoundEffect((double)x2 + 0.5D, (double)y2 + 0.5D, 
          				(double)z2 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * .1F + .6F);
      			return true;
      		}
      	//
      	}
		
		//large mode 4
		/** If these are true perform the large area mode **/
		//
    	if (!theplayer.isSneaking() && (returnTBlock(thestaff)) != Blocks.air    			
    			&& !theplayer.capabilities.isCreativeMode && Gblock != Blocks.bedrock
    			&& getMode(thestaff) == 4
    					||
    			!theplayer.isSneaking() && (returnTBlock(thestaff)) != Blocks.air    			
    			&& theplayer.capabilities.isCreativeMode
    			&& getMode(thestaff) == 4)
    	{ 
    		int x2= x1;
        	int y2= y1;
        	int z2= z1;
    		
    		//&& theblock.getBlockMetadata(x2, y2, z2) != (returnTMeta(thestaff))
    		
    		//The block that is being hit
    		
    		//center 1
    		if (theblock.getBlock(x2, y2, z2) != (returnTBlock(thestaff))
            		|| theblock.getBlockMetadata(x2, y2, z2) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2, y2, z2) == (returnTBlock(thestaff)))
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2, y2, z2, Blocks.air);
            		theblock.setBlock(x2, y2, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		//theblock.setBlock(x2-1, y2, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//WestC
    		if (theblock.getBlock(x2-1, y2, z2) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2-1, y2, z2) == Gblock
    				&& theblock.getBlockMetadata(x2-1, y2, z2) == Gmeta
            		|| theblock.getBlockMetadata(x2-1, y2, z2) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2-1, y2, z2) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2-1, y2, z2) == Gblock
     				&& theblock.getBlockMetadata(x2-1, y2, z2) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        			{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2-1, y2, z2, Blocks.cobblestone);
            		theblock.setBlock(x2-1, y2, z2, Blocks.air);
            		theblock.setBlock(x2-1, y2, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        			} 
    			}
    		//EastC
    		if (theblock.getBlock(x2+1, y2, z2) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2+1, y2, z2) == Gblock
    				&& theblock.getBlockMetadata(x2+1, y2, z2) == Gmeta
            		|| theblock.getBlockMetadata(x2+1, y2, z2) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2+1, y2, z2) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2+1, y2, z2) == Gblock
     				&& theblock.getBlockMetadata(x2+1, y2, z2) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        			{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2+1, y2, z2, Blocks.cobblestone);
            		theblock.setBlock(x2+1, y2, z2, Blocks.air);
            		theblock.setBlock(x2+1, y2, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        			}
    			}
    		//y+1 y+1 y+1
    		//center +1y
    		if (theblock.getBlock(x2, y2+1, z2) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2, y2+1, z2) == Gblock
    				&& theblock.getBlockMetadata(x2, y2+1, z2) == Gmeta
            		|| theblock.getBlockMetadata(x2, y2+1, z2) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2, y2+1, z2) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2, y2+1, z2) == Gblock
     				&& theblock.getBlockMetadata(x2, y2+1, z2) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2, y2+1, z2, Blocks.air);
            		theblock.setBlock(x2, y2+1, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//West +1y
    		if (theblock.getBlock(x2-1, y2+1, z2) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2-1, y2+1, z2) == Gblock
    				&& theblock.getBlockMetadata(x2-1, y2+1, z2) == Gmeta
            		|| theblock.getBlockMetadata(x2-1, y2+1, z2) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2-1, y2+1, z2) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2-1, y2+1, z2) == Gblock
     				&& theblock.getBlockMetadata(x2-1, y2+1, z2) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2-1, y2+1, z2, Blocks.air);
            		theblock.setBlock(x2-1, y2+1, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//East +1y
    		if (theblock.getBlock(x2+1, y2+1, z2) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2+1, y2+1, z2) == Gblock
    				&& theblock.getBlockMetadata(x2+1, y2+1, z2) == Gmeta
            		|| theblock.getBlockMetadata(x2+1, y2+1, z2) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2+1, y2+1, z2) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2+1, y2+1, z2) == Gblock
     				&& theblock.getBlockMetadata(x2+1, y2+1, z2) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2+1, y2+1, z2, Blocks.air);
            		theblock.setBlock(x2+1, y2+1, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//y-1 y-1 y-1
    		//center -1y
    		if (theblock.getBlock(x2, y2-1, z2) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2, y2-1, z2) == Gblock
    				&& theblock.getBlockMetadata(x2, y2-1, z2) == Gmeta
            		|| theblock.getBlockMetadata(x2, y2-1, z2) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2, y2-1, z2) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2, y2-1, z2) == Gblock
     				&& theblock.getBlockMetadata(x2, y2-1, z2) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2, y2-1, z2, Blocks.air);
            		theblock.setBlock(x2, y2-1, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//West -1y
    		if (theblock.getBlock(x2-1, y2-1, z2) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2-1, y2-1, z2) == Gblock
    				&& theblock.getBlockMetadata(x2-1, y2-1, z2) == Gmeta
            		|| theblock.getBlockMetadata(x2-1, y2-1, z2) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2-1, y2-1, z2) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2-1, y2-1, z2) == Gblock
     				&& theblock.getBlockMetadata(x2-1, y2-1, z2) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2-1, y2-1, z2, Blocks.air);
            		theblock.setBlock(x2-1, y2-1, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//East -1y
    		if (theblock.getBlock(x2+1, y2-1, z2) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2+1, y2-1, z2) == Gblock
    				&& theblock.getBlockMetadata(x2+1, y2-1, z2) == Gmeta
            		|| theblock.getBlockMetadata(x2+1, y2-1, z2) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2+1, y2-1, z2) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2+1, y2-1, z2) == Gblock
     				&& theblock.getBlockMetadata(x2+1, y2-1, z2) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2+1, y2-1, z2, Blocks.air);
            		theblock.setBlock(x2+1, y2-1, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		////////////////////////////////////////////////////////////////////////////////////////////
    		
    		//+1z

    		//center 1
    		if (theblock.getBlock(x2, y2, z2+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2, y2, z2+1) == Gblock
    				&& theblock.getBlockMetadata(x2, y2, z2+1) == Gmeta
            		|| theblock.getBlockMetadata(x2, y2, z2+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2, y2, z2+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2, y2, z2+1) == Gblock
     				&& theblock.getBlockMetadata(x2, y2, z2+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2, y2, z2+1, Blocks.air);
            		theblock.setBlock(x2, y2, z2+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		//theblock.setBlock(x2-1, y2, z2+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//WestC
    		if (theblock.getBlock(x2-1, y2, z2+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2-1, y2, z2+1) == Gblock
    				&& theblock.getBlockMetadata(x2-1, y2, z2+1) == Gmeta
            		|| theblock.getBlockMetadata(x2-1, y2, z2+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2-1, y2, z2+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2-1, y2, z2+1) == Gblock
     				&& theblock.getBlockMetadata(x2-1, y2, z2+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        			{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2-1, y2, z2+1, Blocks.cobblestone);
            		theblock.setBlock(x2-1, y2, z2+1, Blocks.air);
            		theblock.setBlock(x2-1, y2, z2+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        			} 
    			}
    		//EastC
    		if (theblock.getBlock(x2+1, y2, z2+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2+1, y2, z2+1) == Gblock
    				&& theblock.getBlockMetadata(x2+1, y2, z2+1) == Gmeta
            		|| theblock.getBlockMetadata(x2+1, y2, z2+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2+1, y2, z2+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2+1, y2, z2+1) == Gblock
     				&& theblock.getBlockMetadata(x2+1, y2, z2+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        			{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2+1, y2, z2+1, Blocks.cobblestone);
            		theblock.setBlock(x2+1, y2, z2+1, Blocks.air);
            		theblock.setBlock(x2+1, y2, z2+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        			}
    			}
    		//y+1 y+1 y+1
    		//center +1y
    		if (theblock.getBlock(x2, y2+1, z2+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2, y2+1, z2+1) == Gblock
    				&& theblock.getBlockMetadata(x2, y2+1, z2+1) == Gmeta
            		|| theblock.getBlockMetadata(x2, y2+1, z2+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2, y2+1, z2+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2, y2+1, z2+1) == Gblock
     				&& theblock.getBlockMetadata(x2, y2+1, z2+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2, y2+1, z2+1, Blocks.air);
            		theblock.setBlock(x2, y2+1, z2+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//West +1y
    		if (theblock.getBlock(x2-1, y2+1, z2+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2-1, y2+1, z2+1) == Gblock
    				&& theblock.getBlockMetadata(x2-1, y2+1, z2+1) == Gmeta
            		|| theblock.getBlockMetadata(x2-1, y2+1, z2+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2-1, y2+1, z2+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2-1, y2+1, z2+1) == Gblock
     				&& theblock.getBlockMetadata(x2-1, y2+1, z2+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2-1, y2+1, z2+1, Blocks.air);
            		theblock.setBlock(x2-1, y2+1, z2+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//East +1y
    		if (theblock.getBlock(x2+1, y2+1, z2+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2+1, y2+1, z2+1) == Gblock
    				&& theblock.getBlockMetadata(x2+1, y2+1, z2+1) == Gmeta
            		|| theblock.getBlockMetadata(x2+1, y2+1, z2+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2+1, y2+1, z2+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2+1, y2+1, z2+1) == Gblock
     				&& theblock.getBlockMetadata(x2+1, y2+1, z2+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2+1, y2+1, z2+1, Blocks.air);
            		theblock.setBlock(x2+1, y2+1, z2+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//y-1 y-1 y-1
    		//center -1y
    		if (theblock.getBlock(x2, y2-1, z2+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2, y2-1, z2+1) == Gblock
    				&& theblock.getBlockMetadata(x2, y2-1, z2+1) == Gmeta
            		|| theblock.getBlockMetadata(x2, y2-1, z2+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2, y2-1, z2+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2, y2-1, z2+1) == Gblock
     				&& theblock.getBlockMetadata(x2, y2-1, z2+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2, y2-1, z2+1, Blocks.air);
            		theblock.setBlock(x2, y2-1, z2+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//West -1y
    		if (theblock.getBlock(x2-1, y2-1, z2+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2-1, y2-1, z2+1) == Gblock
    				&& theblock.getBlockMetadata(x2-1, y2-1, z2+1) == Gmeta
            		|| theblock.getBlockMetadata(x2-1, y2-1, z2+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2-1, y2-1, z2+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2-1, y2-1, z2+1) == Gblock
     				&& theblock.getBlockMetadata(x2-1, y2-1, z2+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2-1, y2-1, z2+1, Blocks.air);
            		theblock.setBlock(x2-1, y2-1, z2+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//East -1y
    		if (theblock.getBlock(x2+1, y2-1, z2+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2+1, y2-1, z2+1) == Gblock
    				&& theblock.getBlockMetadata(x2+1, y2-1, z2+1) == Gmeta
            		|| theblock.getBlockMetadata(x2+1, y2-1, z2+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2+1, y2-1, z2+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2+1, y2-1, z2+1) == Gblock
     				&& theblock.getBlockMetadata(x2+1, y2-1, z2+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2+1, y2-1, z2+1, Blocks.air);
            		theblock.setBlock(x2+1, y2-1, z2+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		
    		
    		
    		///////////////////////////////////////////////////////////////////////////////////
    		
    		

    		//-1z

    		//center 1
    		if (theblock.getBlock(x2, y2, z2-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2, y2, z2-1) == Gblock
    				&& theblock.getBlockMetadata(x2, y2, z2-1) == Gmeta
            		|| theblock.getBlockMetadata(x2, y2, z2-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2, y2, z2-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2, y2, z2-1) == Gblock
     				&& theblock.getBlockMetadata(x2, y2, z2-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2, y2, z2-1, Blocks.air);
            		theblock.setBlock(x2, y2, z2-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		//theblock.setBlock(x2-1, y2, z2-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//WestC
    		if (theblock.getBlock(x2-1, y2, z2-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2-1, y2, z2-1) == Gblock
    				&& theblock.getBlockMetadata(x2-1, y2, z2-1) == Gmeta
            		|| theblock.getBlockMetadata(x2-1, y2, z2-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2-1, y2, z2-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2-1, y2, z2-1) == Gblock
     				&& theblock.getBlockMetadata(x2-1, y2, z2-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        			{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2-1, y2, z2-1, Blocks.cobblestone);
            		theblock.setBlock(x2-1, y2, z2-1, Blocks.air);
            		theblock.setBlock(x2-1, y2, z2-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        			} 
    			}
    		//EastC
    		if (theblock.getBlock(x2+1, y2, z2-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2+1, y2, z2-1) == Gblock
    				&& theblock.getBlockMetadata(x2+1, y2, z2-1) == Gmeta
            		|| theblock.getBlockMetadata(x2+1, y2, z2-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2+1, y2, z2-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2+1, y2, z2-1) == Gblock
     				&& theblock.getBlockMetadata(x2+1, y2, z2-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        			{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2+1, y2, z2-1, Blocks.cobblestone);
            		theblock.setBlock(x2+1, y2, z2-1, Blocks.air);
            		theblock.setBlock(x2+1, y2, z2-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        			}
    			}
    		//y+1 y+1 y+1
    		//center +1y
    		if (theblock.getBlock(x2, y2+1, z2-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2, y2+1, z2-1) == Gblock
    				&& theblock.getBlockMetadata(x2, y2+1, z2-1) == Gmeta
            		|| theblock.getBlockMetadata(x2, y2+1, z2-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2, y2+1, z2-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2, y2+1, z2-1) == Gblock
     				&& theblock.getBlockMetadata(x2, y2+1, z2-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2, y2+1, z2-1, Blocks.air);
            		theblock.setBlock(x2, y2+1, z2-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//West +1y
    		if (theblock.getBlock(x2-1, y2+1, z2-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2-1, y2+1, z2-1) == Gblock
    				&& theblock.getBlockMetadata(x2-1, y2+1, z2-1) == Gmeta
            		|| theblock.getBlockMetadata(x2-1, y2+1, z2-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2-1, y2+1, z2-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2-1, y2+1, z2-1) == Gblock
     				&& theblock.getBlockMetadata(x2-1, y2+1, z2-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2-1, y2+1, z2-1, Blocks.air);
            		theblock.setBlock(x2-1, y2+1, z2-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//East +1y
    		if (theblock.getBlock(x2+1, y2+1, z2-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2+1, y2+1, z2-1) == Gblock
    				&& theblock.getBlockMetadata(x2+1, y2+1, z2-1) == Gmeta
            		|| theblock.getBlockMetadata(x2+1, y2+1, z2-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2+1, y2+1, z2-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2+1, y2+1, z2-1) == Gblock
     				&& theblock.getBlockMetadata(x2+1, y2+1, z2-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2+1, y2+1, z2-1, Blocks.air);
            		theblock.setBlock(x2+1, y2+1, z2-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//y-1 y-1 y-1
    		//center -1y
    		if (theblock.getBlock(x2, y2-1, z2-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2, y2-1, z2-1) == Gblock
    				&& theblock.getBlockMetadata(x2, y2-1, z2-1) == Gmeta
            		|| theblock.getBlockMetadata(x2, y2-1, z2-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2, y2-1, z2-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2, y2-1, z2-1) == Gblock
     				&& theblock.getBlockMetadata(x2, y2-1, z2-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2, y2-1, z2-1, Blocks.air);
            		theblock.setBlock(x2, y2-1, z2-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//West -1y
    		if (theblock.getBlock(x2-1, y2-1, z2-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2-1, y2-1, z2-1) == Gblock
    				&& theblock.getBlockMetadata(x2-1, y2-1, z2-1) == Gmeta
            		|| theblock.getBlockMetadata(x2-1, y2-1, z2-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2-1, y2-1, z2-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2-1, y2-1, z2-1) == Gblock
     				&& theblock.getBlockMetadata(x2-1, y2-1, z2-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2-1, y2-1, z2-1, Blocks.air);
            		theblock.setBlock(x2-1, y2-1, z2-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//East -1y
    		if (theblock.getBlock(x2+1, y2-1, z2-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(x2+1, y2-1, z2-1) == Gblock
    				&& theblock.getBlockMetadata(x2+1, y2-1, z2-1) == Gmeta
            		|| theblock.getBlockMetadata(x2+1, y2-1, z2-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(x2+1, y2-1, z2-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(x2+1, y2-1, z2-1) == Gblock
     				&& theblock.getBlockMetadata(x2+1, y2-1, z2-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(x2+1, y2-1, z2-1, Blocks.air);
            		theblock.setBlock(x2+1, y2-1, z2-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,x2, y2, z2, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		else
    		{

        		theblock.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
        				(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * .1F + .6F);
    			return true;
    		}
    	//
    	}
    		
    		////////////////////////////////////////////////////////////////////////
    		///////////////////////////////////////////////////////////////////////////
    		/**    ~~~~~~~~                  Wall Mode               ~~~~~~~~~~~~~  **/
    		/////////////////////////////////////////////////////////////////////////


    	if (!theplayer.isSneaking() && (returnTBlock(thestaff)) != Blocks.air    			
      			&& !theplayer.capabilities.isCreativeMode && Gblock != Blocks.bedrock
      			&& getMode(thestaff) == 6
      					||
      			!theplayer.isSneaking() && (returnTBlock(thestaff)) != Blocks.air    			
      			&& theplayer.capabilities.isCreativeMode
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
    						//////////////////////////////////////
    						switch(theface){
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

    						//Whitelist Placement
    						
    							if (theblock.getBlock(x2, y2, z2) != (returnTBlock(thestaff))
    				    				&& theblock.getBlock(x2, y2, z2) == Gblock
    				    				&& theblock.getBlockMetadata(x2, y2, z2) == Gmeta
    				            		|| theblock.getBlockMetadata(x2, y2, z2) != (returnTMeta(thestaff))
    				                     && theblock.getBlock(x2, y2, z2) == (returnTBlock(thestaff))
    				     				&& theblock.getBlock(x2, y2, z2) == Gblock
    				     				&& theblock.getBlockMetadata(x2, y2, z2) == Gmeta)
    				    			{
    							ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff)); 
    							if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItemStack(stacky))
    							{
    								//destroys and returns blocks like grass
    								
    								theblock.getBlock(x2, y2, z2).dropBlockAsItem(theblock,x2, y2, z2, (theblock.getBlockMetadata(x2, y2, z2)), 0);
    								
    								theblock.playSoundEffect((double)((float)x2 + 0.5F), (double)((float)y2 + 0.5F), (double)((float)z2 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
    								theblock.setBlock(x2, y2, z2, Blocks.cobblestone);
    								//theblock.setBlock(x2, y2, z2, Blocks.air);
    								theblock.setBlock(x2, y2, z2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 012);

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
    									theblock.spawnParticle(particle, x2+f, y2+f1+.3, z2+f2, 0, 0, 0);        	            		
    								}
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
    
    		
    		
    		/**
    		 * If these all are false it returns with a default action
    		 */
    		
    		
    		
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
    	
    	 
    	theblock.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
				(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * .1F + .6F);
		return false;
    	
    }
    
    
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    
    
}