package helpertools.tools;

import java.util.List;

import helpertools.HelpTab;
import helpertools.Helpertoolscore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
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
    private static final String __OBFID = "CL_00000035";
    //
    //Basic Item things
    public ItemStaffofTransformation2(ToolMaterial material)
    {
    	super (material);
        this.maxStackSize = 1;  
        setUnlocalizedName("staffoftransformation2");
        //setCreativeTab(Helpertoolscore.HelperTools);
        setCreativeTab(HelpTab.HelperTools);
        setTextureName("helpertools:wand8");
    }
    //Adds fancy flavor text for item
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    par3List.add(EnumChatFormatting.ITALIC + "swaps blocks");
    if(whatBlockString(stack) != "null" && whatModeString(stack)!= "null"){
    	par3List.add(whatBlockString(stack) + whatModeString(stack)+ " mode");
    }
    }
    /**
    public String getItemStackDisplayName(ItemStack p_77653_1_)
    {
        return (EnumChatFormatting.GREEN + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(p_77653_1_) + ".name")).trim();
    }
    **/
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
			if (getMode(stack) == 4)
			{
				entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 3F, 0.3F);
		   		ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Single Mode", new Object[0]);
				((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
				setMode(stack, 2);
			}
			else if (getMode(stack) == 2)
			{			
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Mass Mode", new Object[0]);
				((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
				entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", .3F, 3.0F);
			setMode(stack, 4);
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
    public boolean onItemUse(ItemStack thestaff, EntityPlayer theplayer, World theblock, int i1, int j1, int k1, int theface, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
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
    	Gblock = theblock.getBlock(i1, j1, k1);
		Gmeta = theblock.getBlockMetadata(i1, j1, k1); 
		
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
      		if (theblock.getBlock(i1, j1, k1) != (returnTBlock(thestaff))
              		|| theblock.getBlockMetadata(i1, j1, k1) != (returnTMeta(thestaff))
                       && theblock.getBlock(i1, j1, k1) == (returnTBlock(thestaff)))
      		{
      			//The block that is being transformed
              	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
          		{	
              		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
              		theblock.setBlock(i1, j1, k1, Blocks.air);
              		theblock.setBlock(i1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
              		Gblock.dropBlockAsItem(theblock, i1, j1, k1, Gmeta, 0);
              		
              		if (!theplayer.capabilities.isCreativeMode){                	
                          theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                          thestaff.damageItem(1, theplayer);
                          }
              		return true;
          		}
      		}
      		else
      		{

          		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
          				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * .1F + .6F);
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
    		
    		//&& theblock.getBlockMetadata(i1, j1, k1) != (returnTMeta(thestaff))
    		
    		//The block that is being hit
    		
    		//center 1
    		if (theblock.getBlock(i1, j1, k1) != (returnTBlock(thestaff))
            		|| theblock.getBlockMetadata(i1, j1, k1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1, j1, k1) == (returnTBlock(thestaff)))
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1, j1, k1, Blocks.air);
            		theblock.setBlock(i1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		//theblock.setBlock(i1-1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//WestC
    		if (theblock.getBlock(i1-1, j1, k1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1-1, j1, k1) == Gblock
    				&& theblock.getBlockMetadata(i1-1, j1, k1) == Gmeta
            		|| theblock.getBlockMetadata(i1-1, j1, k1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1-1, j1, k1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1-1, j1, k1) == Gblock
     				&& theblock.getBlockMetadata(i1-1, j1, k1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        			{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1-1, j1, k1, Blocks.cobblestone);
            		theblock.setBlock(i1-1, j1, k1, Blocks.air);
            		theblock.setBlock(i1-1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        			} 
    			}
    		//EastC
    		if (theblock.getBlock(i1+1, j1, k1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1+1, j1, k1) == Gblock
    				&& theblock.getBlockMetadata(i1+1, j1, k1) == Gmeta
            		|| theblock.getBlockMetadata(i1+1, j1, k1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1+1, j1, k1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1+1, j1, k1) == Gblock
     				&& theblock.getBlockMetadata(i1+1, j1, k1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        			{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1+1, j1, k1, Blocks.cobblestone);
            		theblock.setBlock(i1+1, j1, k1, Blocks.air);
            		theblock.setBlock(i1+1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        			}
    			}
    		//y+1 y+1 y+1
    		//center +1y
    		if (theblock.getBlock(i1, j1+1, k1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1, j1+1, k1) == Gblock
    				&& theblock.getBlockMetadata(i1, j1+1, k1) == Gmeta
            		|| theblock.getBlockMetadata(i1, j1+1, k1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1, j1+1, k1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1, j1+1, k1) == Gblock
     				&& theblock.getBlockMetadata(i1, j1+1, k1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1, j1+1, k1, Blocks.air);
            		theblock.setBlock(i1, j1+1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//West +1y
    		if (theblock.getBlock(i1-1, j1+1, k1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1-1, j1+1, k1) == Gblock
    				&& theblock.getBlockMetadata(i1-1, j1+1, k1) == Gmeta
            		|| theblock.getBlockMetadata(i1-1, j1+1, k1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1-1, j1+1, k1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1-1, j1+1, k1) == Gblock
     				&& theblock.getBlockMetadata(i1-1, j1+1, k1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1-1, j1+1, k1, Blocks.air);
            		theblock.setBlock(i1-1, j1+1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//East +1y
    		if (theblock.getBlock(i1+1, j1+1, k1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1+1, j1+1, k1) == Gblock
    				&& theblock.getBlockMetadata(i1+1, j1+1, k1) == Gmeta
            		|| theblock.getBlockMetadata(i1+1, j1+1, k1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1+1, j1+1, k1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1+1, j1+1, k1) == Gblock
     				&& theblock.getBlockMetadata(i1+1, j1+1, k1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1+1, j1+1, k1, Blocks.air);
            		theblock.setBlock(i1+1, j1+1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//y-1 y-1 y-1
    		//center -1y
    		if (theblock.getBlock(i1, j1-1, k1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1, j1-1, k1) == Gblock
    				&& theblock.getBlockMetadata(i1, j1-1, k1) == Gmeta
            		|| theblock.getBlockMetadata(i1, j1-1, k1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1, j1-1, k1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1, j1-1, k1) == Gblock
     				&& theblock.getBlockMetadata(i1, j1-1, k1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1, j1-1, k1, Blocks.air);
            		theblock.setBlock(i1, j1-1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//West -1y
    		if (theblock.getBlock(i1-1, j1-1, k1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1-1, j1-1, k1) == Gblock
    				&& theblock.getBlockMetadata(i1-1, j1-1, k1) == Gmeta
            		|| theblock.getBlockMetadata(i1-1, j1-1, k1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1-1, j1-1, k1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1-1, j1-1, k1) == Gblock
     				&& theblock.getBlockMetadata(i1-1, j1-1, k1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1-1, j1-1, k1, Blocks.air);
            		theblock.setBlock(i1-1, j1-1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//East -1y
    		if (theblock.getBlock(i1+1, j1-1, k1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1+1, j1-1, k1) == Gblock
    				&& theblock.getBlockMetadata(i1+1, j1-1, k1) == Gmeta
            		|| theblock.getBlockMetadata(i1+1, j1-1, k1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1+1, j1-1, k1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1+1, j1-1, k1) == Gblock
     				&& theblock.getBlockMetadata(i1+1, j1-1, k1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1+1, j1-1, k1, Blocks.air);
            		theblock.setBlock(i1+1, j1-1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		////////////////////////////////////////////////////////////////////////////////////////////
    		
    		//+1z

    		//center 1
    		if (theblock.getBlock(i1, j1, k1+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1, j1, k1+1) == Gblock
    				&& theblock.getBlockMetadata(i1, j1, k1+1) == Gmeta
            		|| theblock.getBlockMetadata(i1, j1, k1+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1, j1, k1+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1, j1, k1+1) == Gblock
     				&& theblock.getBlockMetadata(i1, j1, k1+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1, j1, k1+1, Blocks.air);
            		theblock.setBlock(i1, j1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		//theblock.setBlock(i1-1, j1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//WestC
    		if (theblock.getBlock(i1-1, j1, k1+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1-1, j1, k1+1) == Gblock
    				&& theblock.getBlockMetadata(i1-1, j1, k1+1) == Gmeta
            		|| theblock.getBlockMetadata(i1-1, j1, k1+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1-1, j1, k1+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1-1, j1, k1+1) == Gblock
     				&& theblock.getBlockMetadata(i1-1, j1, k1+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        			{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1-1, j1, k1+1, Blocks.cobblestone);
            		theblock.setBlock(i1-1, j1, k1+1, Blocks.air);
            		theblock.setBlock(i1-1, j1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        			} 
    			}
    		//EastC
    		if (theblock.getBlock(i1+1, j1, k1+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1+1, j1, k1+1) == Gblock
    				&& theblock.getBlockMetadata(i1+1, j1, k1+1) == Gmeta
            		|| theblock.getBlockMetadata(i1+1, j1, k1+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1+1, j1, k1+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1+1, j1, k1+1) == Gblock
     				&& theblock.getBlockMetadata(i1+1, j1, k1+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        			{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1+1, j1, k1+1, Blocks.cobblestone);
            		theblock.setBlock(i1+1, j1, k1+1, Blocks.air);
            		theblock.setBlock(i1+1, j1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        			}
    			}
    		//y+1 y+1 y+1
    		//center +1y
    		if (theblock.getBlock(i1, j1+1, k1+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1, j1+1, k1+1) == Gblock
    				&& theblock.getBlockMetadata(i1, j1+1, k1+1) == Gmeta
            		|| theblock.getBlockMetadata(i1, j1+1, k1+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1, j1+1, k1+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1, j1+1, k1+1) == Gblock
     				&& theblock.getBlockMetadata(i1, j1+1, k1+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1, j1+1, k1+1, Blocks.air);
            		theblock.setBlock(i1, j1+1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//West +1y
    		if (theblock.getBlock(i1-1, j1+1, k1+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1-1, j1+1, k1+1) == Gblock
    				&& theblock.getBlockMetadata(i1-1, j1+1, k1+1) == Gmeta
            		|| theblock.getBlockMetadata(i1-1, j1+1, k1+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1-1, j1+1, k1+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1-1, j1+1, k1+1) == Gblock
     				&& theblock.getBlockMetadata(i1-1, j1+1, k1+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1-1, j1+1, k1+1, Blocks.air);
            		theblock.setBlock(i1-1, j1+1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//East +1y
    		if (theblock.getBlock(i1+1, j1+1, k1+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1+1, j1+1, k1+1) == Gblock
    				&& theblock.getBlockMetadata(i1+1, j1+1, k1+1) == Gmeta
            		|| theblock.getBlockMetadata(i1+1, j1+1, k1+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1+1, j1+1, k1+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1+1, j1+1, k1+1) == Gblock
     				&& theblock.getBlockMetadata(i1+1, j1+1, k1+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1+1, j1+1, k1+1, Blocks.air);
            		theblock.setBlock(i1+1, j1+1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//y-1 y-1 y-1
    		//center -1y
    		if (theblock.getBlock(i1, j1-1, k1+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1, j1-1, k1+1) == Gblock
    				&& theblock.getBlockMetadata(i1, j1-1, k1+1) == Gmeta
            		|| theblock.getBlockMetadata(i1, j1-1, k1+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1, j1-1, k1+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1, j1-1, k1+1) == Gblock
     				&& theblock.getBlockMetadata(i1, j1-1, k1+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1, j1-1, k1+1, Blocks.air);
            		theblock.setBlock(i1, j1-1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//West -1y
    		if (theblock.getBlock(i1-1, j1-1, k1+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1-1, j1-1, k1+1) == Gblock
    				&& theblock.getBlockMetadata(i1-1, j1-1, k1+1) == Gmeta
            		|| theblock.getBlockMetadata(i1-1, j1-1, k1+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1-1, j1-1, k1+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1-1, j1-1, k1+1) == Gblock
     				&& theblock.getBlockMetadata(i1-1, j1-1, k1+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1-1, j1-1, k1+1, Blocks.air);
            		theblock.setBlock(i1-1, j1-1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//East -1y
    		if (theblock.getBlock(i1+1, j1-1, k1+1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1+1, j1-1, k1+1) == Gblock
    				&& theblock.getBlockMetadata(i1+1, j1-1, k1+1) == Gmeta
            		|| theblock.getBlockMetadata(i1+1, j1-1, k1+1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1+1, j1-1, k1+1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1+1, j1-1, k1+1) == Gblock
     				&& theblock.getBlockMetadata(i1+1, j1-1, k1+1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1+1, j1-1, k1+1, Blocks.air);
            		theblock.setBlock(i1+1, j1-1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		
    		
    		
    		///////////////////////////////////////////////////////////////////////////////////
    		
    		

    		//-1z

    		//center 1
    		if (theblock.getBlock(i1, j1, k1-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1, j1, k1-1) == Gblock
    				&& theblock.getBlockMetadata(i1, j1, k1-1) == Gmeta
            		|| theblock.getBlockMetadata(i1, j1, k1-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1, j1, k1-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1, j1, k1-1) == Gblock
     				&& theblock.getBlockMetadata(i1, j1, k1-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1, j1, k1-1, Blocks.air);
            		theblock.setBlock(i1, j1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		//theblock.setBlock(i1-1, j1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//WestC
    		if (theblock.getBlock(i1-1, j1, k1-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1-1, j1, k1-1) == Gblock
    				&& theblock.getBlockMetadata(i1-1, j1, k1-1) == Gmeta
            		|| theblock.getBlockMetadata(i1-1, j1, k1-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1-1, j1, k1-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1-1, j1, k1-1) == Gblock
     				&& theblock.getBlockMetadata(i1-1, j1, k1-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        			{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1-1, j1, k1-1, Blocks.cobblestone);
            		theblock.setBlock(i1-1, j1, k1-1, Blocks.air);
            		theblock.setBlock(i1-1, j1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        			} 
    			}
    		//EastC
    		if (theblock.getBlock(i1+1, j1, k1-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1+1, j1, k1-1) == Gblock
    				&& theblock.getBlockMetadata(i1+1, j1, k1-1) == Gmeta
            		|| theblock.getBlockMetadata(i1+1, j1, k1-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1+1, j1, k1-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1+1, j1, k1-1) == Gblock
     				&& theblock.getBlockMetadata(i1+1, j1, k1-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        			{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1+1, j1, k1-1, Blocks.cobblestone);
            		theblock.setBlock(i1+1, j1, k1-1, Blocks.air);
            		theblock.setBlock(i1+1, j1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        			}
    			}
    		//y+1 y+1 y+1
    		//center +1y
    		if (theblock.getBlock(i1, j1+1, k1-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1, j1+1, k1-1) == Gblock
    				&& theblock.getBlockMetadata(i1, j1+1, k1-1) == Gmeta
            		|| theblock.getBlockMetadata(i1, j1+1, k1-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1, j1+1, k1-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1, j1+1, k1-1) == Gblock
     				&& theblock.getBlockMetadata(i1, j1+1, k1-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1, j1+1, k1-1, Blocks.air);
            		theblock.setBlock(i1, j1+1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//West +1y
    		if (theblock.getBlock(i1-1, j1+1, k1-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1-1, j1+1, k1-1) == Gblock
    				&& theblock.getBlockMetadata(i1-1, j1+1, k1-1) == Gmeta
            		|| theblock.getBlockMetadata(i1-1, j1+1, k1-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1-1, j1+1, k1-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1-1, j1+1, k1-1) == Gblock
     				&& theblock.getBlockMetadata(i1-1, j1+1, k1-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1-1, j1+1, k1-1, Blocks.air);
            		theblock.setBlock(i1-1, j1+1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//East +1y
    		if (theblock.getBlock(i1+1, j1+1, k1-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1+1, j1+1, k1-1) == Gblock
    				&& theblock.getBlockMetadata(i1+1, j1+1, k1-1) == Gmeta
            		|| theblock.getBlockMetadata(i1+1, j1+1, k1-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1+1, j1+1, k1-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1+1, j1+1, k1-1) == Gblock
     				&& theblock.getBlockMetadata(i1+1, j1+1, k1-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1+1, j1+1, k1-1, Blocks.air);
            		theblock.setBlock(i1+1, j1+1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//y-1 y-1 y-1
    		//center -1y
    		if (theblock.getBlock(i1, j1-1, k1-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1, j1-1, k1-1) == Gblock
    				&& theblock.getBlockMetadata(i1, j1-1, k1-1) == Gmeta
            		|| theblock.getBlockMetadata(i1, j1-1, k1-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1, j1-1, k1-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1, j1-1, k1-1) == Gblock
     				&& theblock.getBlockMetadata(i1, j1-1, k1-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1, j1-1, k1-1, Blocks.air);
            		theblock.setBlock(i1, j1-1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//West -1y
    		if (theblock.getBlock(i1-1, j1-1, k1-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1-1, j1-1, k1-1) == Gblock
    				&& theblock.getBlockMetadata(i1-1, j1-1, k1-1) == Gmeta
            		|| theblock.getBlockMetadata(i1-1, j1-1, k1-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1-1, j1-1, k1-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1-1, j1-1, k1-1) == Gblock
     				&& theblock.getBlockMetadata(i1-1, j1-1, k1-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1-1, j1-1, k1-1, Blocks.air);
            		theblock.setBlock(i1-1, j1-1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		//East -1y
    		if (theblock.getBlock(i1+1, j1-1, k1-1) != (returnTBlock(thestaff))
    				&& theblock.getBlock(i1+1, j1-1, k1-1) == Gblock
    				&& theblock.getBlockMetadata(i1+1, j1-1, k1-1) == Gmeta
            		|| theblock.getBlockMetadata(i1+1, j1-1, k1-1) != (returnTMeta(thestaff))
                     && theblock.getBlock(i1+1, j1-1, k1-1) == (returnTBlock(thestaff))
     				&& theblock.getBlock(i1+1, j1-1, k1-1) == Gblock
     				&& theblock.getBlockMetadata(i1+1, j1-1, k1-1) == Gmeta)
    			{
    			//The block that is being transformed
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock((returnTBlock(thestaff)))))
        		{	
            		theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), (returnTBlock(thestaff)).stepSound.getStepResourcePath(), ((returnTBlock(thestaff)).stepSound.getVolume() + 1.0F) / 2.0F, (returnTBlock(thestaff)).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1+1, j1-1, k1-1, Blocks.air);
            		theblock.setBlock(i1+1, j1-1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		Gblock.dropBlockAsItem(theblock,i1, j1, k1, Gmeta, 0);
            		
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock((returnTBlock(thestaff))));	
                        thestaff.damageItem(1, theplayer);
                        }
        		        		}
            		}
    		
    		////////////////////////////////////////////////////////////////////////
    		
    		
    		/**
    		 * If these all are false it returns with a default action
    		 */
    		
    		
    		else
    		{

        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * .1F + .6F);
    			return true;
    		}
    	//
    	}
    	if (theplayer.isSneaking())
    	{ 
    		
    		setTBlock(thestaff, theblock.getBlock(i1, j1, k1).getIdFromBlock(theblock.getBlock(i1, j1, k1)));
    		setTMeta(thestaff,theblock.getBlockMetadata(i1, j1, k1)); 		
    		//
    		
    		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
    				(double)k1 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
    		
    		 setOffMode(thestaff, 4);
    			return true;
    		
    	}
    	
    	 
    	theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * .1F + .6F);
		return false;
    	
    }
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    
    
}