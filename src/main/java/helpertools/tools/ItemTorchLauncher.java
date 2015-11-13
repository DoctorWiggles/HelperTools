package helpertools.tools;

import java.util.List;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helpertools.Common_Registry;
import helpertools.HelpTab;
import helpertools.Helpertoolscore;
import helpertools.entities.EntityBoltProjectile;
import helpertools.entities.EntityDynamiteProjectile;
import helpertools.entities.EntityRedTorchProjectile;
import helpertools.entities.EntityTorchProjectile;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;



public class ItemTorchLauncher extends ItemSpade{

   public ItemTorchLauncher(ToolMaterial material) {
       super(material);
       this.maxStackSize = 1;  
       setUnlocalizedName("torchlauncher");
       //setCreativeTab(Helpertoolscore.HelperTools);
       setCreativeTab(HelpTab.HelperTools);
       setTextureName("helpertools:launcher");
       //this.setMaxDamage(1428);
       
   }
   
   @Override
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
   {
   par3List.add(EnumChatFormatting.WHITE + "Fires Torches & Arrows & Dynamite ");
   par3List.add(EnumChatFormatting.ITALIC + "While sneaking to toggle ammunition");
   par3List.add(EnumChatFormatting.ITALIC + "Right click to reload or Fire");
   }
   
   
   ///////////////////
   /** Ammo Type Latch **/
   /////////////////////
  
   //
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
	/** Ammo Supply Latch **/
	//////////////////
	private int Tload = 0;
	public int getTload() {
		return Tload;
	}
	public void setTload(int tload) {
		Tload = tload;
	}
	////////////////////////////////
	public int getTload(ItemStack itemStack)
	{
		if(itemStack.stackTagCompound == null)
		{
			return 0;
		}

		return itemStack.stackTagCompound.getInteger("Tload");
		
	}
	public void setTload(ItemStack itemStack, int Value)
	{
		if(itemStack.stackTagCompound == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		itemStack.stackTagCompound.setInteger("Tload",  Value );
		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	} 
	
	
	
	 public void onCreated(ItemStack p_77622_1_, World p_77622_2_, EntityPlayer p_77622_3_) 
	 {
		 //Add unique name for bow
		 // Player's Torch Launcher
	 }
	
	
	//////////////////
	/** Mode Offset **/
	int offmode = 2;
	///////////////
	

	//Tinker's Construct Stone Torch Support
	
	String TinkTorch = "TConstruct:decoration.stonetorch";
	//String TinkTorch = "HelperToolsID:SugarBlock";
	String Tcon = "TConstruct";
	//String Tcon = "HelperToolsID";
	
	public boolean StoneTorchSearch(EntityLivingBase entityLiving) {
		
		if(Loader.isModLoaded(Tcon))
		 {			
			 return ((EntityPlayer) entityLiving).inventory.hasItem(Item.getItemFromBlock(Block.getBlockFromName(TinkTorch)));
			 
			}
		 else
			return false;
			};
			
	public boolean StoneTorchConsume(EntityLivingBase entityLiving) {
		
		if(Loader.isModLoaded(Tcon))
		 {			
			 return ((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Item.getItemFromBlock(Block.getBlockFromName(TinkTorch)));
					 
			}
		 else
			return false;
			};
			
			
			
			
			// || StoneTorch(entityLiving)
			// ((EntityPlayer) entityLiving).inventory.hasItem(Item.getItemFromBlock(Blocks.torch))
			// par3EntityPlayer.inventory.hasItem(Item.getItemFromBlock(Blocks.torch)) && getMode(stack) == 2
			
	
			
   
   /**
    * Ammo Modes
    */
   /////////////////////////////////////////////////////////////////
   @Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
   {
	   
	  
	   
	   
	   
	   if (getMode(stack)== 0)
   		{
		   setMode(stack, 2);
   		}
	   
	   if(!entityLiving.worldObj.isRemote)
	   {
		  
		if (entityLiving.isSneaking() )
		{ 		
			
				//ChatComponentTranslation chatcomponenttranslation2 = new ChatComponentTranslation("Mode is? : " +(getMode(stack)), new Object[0]);
				//((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation2);
				//ChatComponentTranslation chatcomponenttranslation2 = new ChatComponentTranslation("Off 0", new Object[0]);
				 //((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation2);
				//entityLiving.worldObj.playSoundAtEntity(entityLiving, "fire.ignite", 1.0F, 3.0F);
			
				/////////////////////////////////////
				/**Mode change from torch setting**/
				///////////////////////////////////
				if (getMode(stack) == 2 )
				{
					//To Red torches
					if(((EntityPlayer) entityLiving).capabilities.isCreativeMode || 
							((EntityPlayer) entityLiving).inventory.hasItem(Item.getItemFromBlock(Blocks.redstone_torch)))						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 4);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Blocks.torch), 0.0F);
							((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.redstone_torch));	
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Redstone loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					//Skip To Dynamite
					else if(!((EntityPlayer) entityLiving).capabilities.isCreativeMode && 
							((EntityPlayer) entityLiving).inventory.hasItem(Common_Registry.dynamitebolt))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 6);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Blocks.torch), 0.0F);
							((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Common_Registry.dynamitebolt);
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Dynamite loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					//Skip To Arrow
					else if(!((EntityPlayer) entityLiving).capabilities.isCreativeMode && 
							((EntityPlayer) entityLiving).inventory.hasItem(Items.arrow))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 8);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Blocks.torch), 0.0F);
							((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Items.arrow);	
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Arrows loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					else
					{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "fire.ignite",.4F, itemRand.nextFloat() * 0.4F + 0.8F);
					}
				 
				}
				
				///////////////////////////////////////////
				/** Mode change from Red torch setting **/
				/////////////////////////////////////////
				else if (getMode(stack) == 4 )
				{
					//To Dynamite
					if(((EntityPlayer) entityLiving).capabilities.isCreativeMode || 
							((EntityPlayer) entityLiving).inventory.hasItem(Common_Registry.dynamitebolt))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 6);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Blocks.redstone_torch), 0.0F);
							((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Common_Registry.dynamitebolt);	
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Dynamite loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					//Skip To Arrow
					else if(!((EntityPlayer) entityLiving).capabilities.isCreativeMode && 
							((EntityPlayer) entityLiving).inventory.hasItem(Items.arrow))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 8);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Blocks.redstone_torch), 0.0F);
							((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Items.arrow);	
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Arrows loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					//To Torches
					else if(!((EntityPlayer) entityLiving).capabilities.isCreativeMode &&
							((EntityPlayer) entityLiving).inventory.hasItem(Item.getItemFromBlock(Blocks.torch)))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 2);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Blocks.redstone_torch), 0.0F);						
							((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.torch));	
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Torches loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					//To Stone Torches
					else if(StoneTorchSearch(entityLiving))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 2);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Blocks.redstone_torch), 0.0F);						
							StoneTorchConsume(entityLiving); /** **/
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Torches loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					else
					{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "fire.ignite",.4F, itemRand.nextFloat() * 0.4F + 0.8F);
					}
				 
				}
				
				////////////////////////////////////////
				/**Mode change from Dynamite setting **/
				///////////////////////////////////////
				else if (getMode(stack) == 6)
				{
					//To Arrow
					if(((EntityPlayer) entityLiving).capabilities.isCreativeMode || 
							((EntityPlayer) entityLiving).inventory.hasItem(Items.arrow))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 8);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Common_Registry.dynamitebolt), 0.0F);
							((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Items.arrow);	
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Arrows loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					//Skip To Torches
					else if(!((EntityPlayer) entityLiving).capabilities.isCreativeMode && 
							((EntityPlayer) entityLiving).inventory.hasItem(Item.getItemFromBlock(Blocks.torch)))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 2);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Common_Registry.dynamitebolt), 0.0F);
							((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.torch));
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Torches loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					//Skip To Stone Torches
					else if(StoneTorchSearch(entityLiving))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 2);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Common_Registry.dynamitebolt), 0.0F);
							StoneTorchConsume(entityLiving); /** **/
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Torches loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					//Skip To Red torches
					else if(!((EntityPlayer) entityLiving).capabilities.isCreativeMode && 
							((EntityPlayer) entityLiving).inventory.hasItem(Item.getItemFromBlock(Blocks.redstone_torch)))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 4);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Common_Registry.dynamitebolt), 0.0F);
							((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.redstone_torch));	
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Redstone loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					else
					{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "fire.ignite",.4F, itemRand.nextFloat() * 0.4F + 0.8F);
					}
				}
				
				//////////////////////////////////////
				/**Mode change from Arrows setting**/
				////////////////////////////////////
				else if (getMode(stack) == 8)
				{
					//To Torches
					if(((EntityPlayer) entityLiving).capabilities.isCreativeMode || 
							((EntityPlayer) entityLiving).inventory.hasItem(Item.getItemFromBlock(Blocks.torch))
							|| StoneTorchSearch(entityLiving))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 2);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Items.arrow), 0.0F);
							((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.torch));
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Torches loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					//To Stone Torches
					if(StoneTorchSearch(entityLiving))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 2);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Items.arrow), 0.0F);
							StoneTorchConsume(entityLiving); /** **/
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Torches loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					
					//Skip To Red torches
					else if(!((EntityPlayer) entityLiving).capabilities.isCreativeMode && 
							((EntityPlayer) entityLiving).inventory.hasItem(Item.getItemFromBlock(Blocks.redstone_torch)))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 4);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Items.arrow), 0.0F);
							((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.redstone_torch));
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Redstone loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					//Skip To Dynamite
					else if(!((EntityPlayer) entityLiving).capabilities.isCreativeMode && 
							((EntityPlayer) entityLiving).inventory.hasItem(Common_Registry.dynamitebolt))
						{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 1.0F, 3.0F);
						setMode(stack, 6);
						if(getTload(stack)== 2 && !((EntityPlayer) entityLiving).capabilities.isCreativeMode){
							((EntityPlayer) entityLiving).entityDropItem(new ItemStack(Items.arrow), 0.0F);						
							((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Common_Registry.dynamitebolt);
						}
						ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Dynamite loaded", new Object[0]);
						((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
						return false;
						}
					else
					{
						entityLiving.worldObj.playSoundAtEntity(entityLiving, "fire.ignite",.4F, itemRand.nextFloat() * 0.4F + 0.8F);
					}
				 
				
				}
				
				
				}
   		
	   }	
   	
		 return false;
   }
///////////////////////////////////////////////////////
   
   @Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		return false;
	
	}
  
///////////////////////////////////////////////////////
   @Override
   public ItemStack onItemRightClick(ItemStack stack, World par2World,
           EntityPlayer par3EntityPlayer) 
   {	
	   if (getMode(stack)== 0)
  		{
		   setMode(stack, 2);
  		}
	  if( !par3EntityPlayer.worldObj.isRemote){
	   //Calls for audio and entity arrow casting
	   EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, 1 * 2.0F);
	   //EntityBoltProjectile entityarrow = new EntityBoltProjectile(par2World, par3EntityPlayer, 1 * 2F);
	   float f = 6.0F;
       f = (f * f + f * 2.0F) / 3.0F;
      // ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("Mode is? : " +(getMode(stack)));
		//((EntityPlayer) par3EntityPlayer).addChatComponentMessage(chatcomponenttranslation);
       
	   if(!par2World.isRemote && !par3EntityPlayer.isSneaking() && getTload(stack) ==2){
		  
		   //////////////////////////////////////
		   /** If player is not sneaking attempt to fire **/
		   //////////////////////////////////////
		   switch(getMode(stack))
			{
				case 2:
				
					par2World.spawnEntityInWorld(new EntityTorchProjectile(par2World, par3EntityPlayer));
					   par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					   setTload(stack, 0);
					   
					  
				break;
					
				case 4:
					 //par3EntityPlayer.playSound("mob.chicken.plop", 3F, 3.0F);
					   par2World.spawnEntityInWorld(new EntityRedTorchProjectile(par2World, par3EntityPlayer));
					   par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					   setTload(stack, 0);
					   
					
				break;
				
				case 6:
					//par3EntityPlayer.playSound("mob.chicken.plop", 3F, 3.0F);
		       		   par2World.spawnEntityInWorld(new EntityDynamiteProjectile(par2World, par3EntityPlayer));
		       		   par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		       		   setTload(stack, 0);
		       		   
					
				break;
					
				case 8:
					 //par3EntityPlayer.playSound("mob.chicken.plop", 3F, 3.0F);
		       		   //par2World.spawnEntityInWorld(new EntityTorchProjectile(par2World, par3EntityPlayer));
		       		   par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		       		   setTload(stack, 0);
		       		   par2World.spawnEntityInWorld(entityarrow);
		       		   
					
				break;
		   
			}
       	   //stack.damageItem(3, par3EntityPlayer);
       	   return stack;
       	   
   		}
	   
	   /////////////////////////
	   /** Ammo Loading **/
	   ////////////////////////
	   	if(par3EntityPlayer.isSneaking() && getTload(stack)== 0){
	   		
	   		//////////////////////////////
	   		/**If player is sneaking attempt to load ammo **/
	   		////////////////////////////
	   		//Mode 2 Load
	   		//Coal Torches
	   		if(par3EntityPlayer.capabilities.isCreativeMode && getMode(stack) == 2||
	   				par3EntityPlayer.inventory.hasItem(Item.getItemFromBlock(Blocks.torch)) && getMode(stack) == 2)
	   		{
	   			setTload(stack, 2);
	   			par3EntityPlayer.worldObj.playSoundAtEntity(par3EntityPlayer, "fire.ignite",.4F, itemRand.nextFloat() * 0.4F + 0.8F);
	   			if (!par3EntityPlayer.capabilities.isCreativeMode){                	
					   par3EntityPlayer.inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.torch));
					   //stack.damageItem(2, par3EntityPlayer);
				   }
	   			stack.damageItem(2, par3EntityPlayer);
	   			return stack;}
	   		//Tcon Mode 2 Load
	   		//Stone Toches
	   		else if( StoneTorchSearch(par3EntityPlayer)&& getMode(stack) == 2)
	   		{
	   			setTload(stack, 2);
	   			par3EntityPlayer.worldObj.playSoundAtEntity(par3EntityPlayer, "fire.ignite",.4F, itemRand.nextFloat() * 0.4F + 0.8F);
	   			if (!par3EntityPlayer.capabilities.isCreativeMode){                	
	   				StoneTorchConsume(par3EntityPlayer);
					   //stack.damageItem(2, par3EntityPlayer);
				   }
	   			stack.damageItem(2, par3EntityPlayer);
	   			return stack;}
	   		//Mode 4 Load
	   		//Redstone
	   		else if(par3EntityPlayer.capabilities.isCreativeMode && getMode(stack) == 4||
	   				par3EntityPlayer.inventory.hasItem(Item.getItemFromBlock(Blocks.redstone_torch)) && getMode(stack) == 4){
	   			setTload(stack, 2);
	   			par3EntityPlayer.worldObj.playSoundAtEntity(par3EntityPlayer, "fire.ignite",.4F, itemRand.nextFloat() * 0.4F + 0.8F);
	   			if (!par3EntityPlayer.capabilities.isCreativeMode){                	
					   par3EntityPlayer.inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.redstone_torch));	
					   //stack.damageItem(2, par3EntityPlayer);
	               	}
	   			stack.damageItem(2, par3EntityPlayer);
	   			return stack;}
	   		//Mode 6 Load
	   		//Dynamite
	   		else if(par3EntityPlayer.capabilities.isCreativeMode && getMode(stack) == 6||
	   				par3EntityPlayer.inventory.hasItem(Common_Registry.dynamitebolt) && getMode(stack) == 6){
	   			setTload(stack, 2);
	   			par3EntityPlayer.worldObj.playSoundAtEntity(par3EntityPlayer, "fire.ignite",.4F, itemRand.nextFloat() * 0.4F + 0.8F);
	   			if (!par3EntityPlayer.capabilities.isCreativeMode){                	
					   par3EntityPlayer.inventory.consumeInventoryItem(Common_Registry.dynamitebolt);	
					  //stack.damageItem(3, par3EntityPlayer);
	            	}
	   			stack.damageItem(3, par3EntityPlayer);
	   			return stack;}
	   		//Mode 8 Load
	   		//Arrows
	   		else if(par3EntityPlayer.capabilities.isCreativeMode && getMode(stack) == 8||
	   				par3EntityPlayer.inventory.hasItem(Items.arrow) && getMode(stack) == 8){
	   			setTload(stack, 2);
	   			par3EntityPlayer.worldObj.playSoundAtEntity(par3EntityPlayer, "fire.ignite",.4F, itemRand.nextFloat() * 0.4F + 0.8F);
	   			if (!par3EntityPlayer.capabilities.isCreativeMode){                	
					   par3EntityPlayer.inventory.consumeInventoryItem(Items.arrow);	
					   //stack.damageItem(3, par3EntityPlayer);
	       		   }
	   			stack.damageItem(3, par3EntityPlayer);
	   			return stack;}
	   		////////////////////////////////////////
	   	
	   		///////////////////////////////////////////
	   		else{
	   			par3EntityPlayer.worldObj.playSoundAtEntity(par3EntityPlayer, "fire.ignite",0.4F, itemRand.nextFloat() * 0.4F + 0.2F);
	   			return stack;
	   			}
	   	}
	  }
	   	return stack;
   }
}
