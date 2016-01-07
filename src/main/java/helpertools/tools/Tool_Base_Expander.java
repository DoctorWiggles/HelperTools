package helpertools.tools;

import helpertools.Common_Registry;
import helpertools.ConfigurationFactory;
import helpertools.util.InventoryUtil;
import helpertools.util.Whitelist_Util;

import java.util.List;
import java.util.Set;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class Tool_Base_Expander extends ItemTool{

	public Tool_Base_Expander(float f, ToolMaterial material, Set s) {
		super (1F, material, Common_Registry.properharvest);
		// TODO Auto-generated constructor stub
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	/**flavor text**/
	/////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	par3List.add(EnumChatFormatting.WHITE + "Sets blocks in the world");
        par3List.add(EnumChatFormatting.ITALIC + "While sneaking change mode");
        par3List.add(EnumChatFormatting.ITALIC + "- Or select block to place");
        par3List.add(EnumChatFormatting.ITALIC + "While enchanted with efficiency");
        par3List.add(EnumChatFormatting.ITALIC + "- Press 'o' to toggle size");
        par3List.add(EnumChatFormatting.ITALIC + "");
    if(whatBlockString(stack) != "null" && whatModeString(stack)!= "null"){
    	par3List.add(whatBlockString(stack) + whatModeString(stack)+ " mode");
    }
    }
   
    //return correct string per mode
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
   //translate block name
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
 
	/////////////////////////////////////////////////////////////////////////////////
	/** Generic tool stuff **/
  	///////////////////////////////////////////////////////////////////////////////
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block,
			int x, int y, int z, EntityLivingBase living) {
		if ((double) block.getBlockHardness(world, x, y, z) != 0.0D) {
			stack.damageItem(1, living);
		}

		return true;
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase livingbase,
			EntityLivingBase living) {
		stack.damageItem(2, living);
		return true;
	}
	
	 public boolean isMetadataSpecific(ItemStack itemStack)
	   	{
	   		return false;
	   	}
	  @SideOnly(Side.CLIENT)
	    public boolean isFull3D()
	    {
	        return true;
	    }
	 
	
	//////////////////////////////////////////////////////////////////////////////////////
	/** Tool levels **/
	////////////////////////////////////////////////////////////////////////////////////
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
	
	public void ToolEmpower(ItemStack itemStack, EntityLivingBase entityLiving){
		int Toolmax = EnchantmentHelper.getEnchantmentLevel(32, itemStack);
		int NextLevel = (getToolLevel(itemStack))+1;
		
		EntityPlayer Player = (EntityPlayer) entityLiving;
		if (Toolmax <5 && Player.capabilities.isCreativeMode){Toolmax = 5;}
		
		if(NextLevel>Toolmax){
			setToolLevel(itemStack,0);
			entityLiving.worldObj.playSoundAtEntity(entityLiving, "random.fizz", (float)(1), (float)(1.3));
		}
		if(NextLevel<=Toolmax){
			setToolLevel(itemStack,NextLevel);
			entityLiving.worldObj.playSoundAtEntity(entityLiving, "random.orb", (float)(.8), (float)( itemRand.nextFloat()*.75+.2));
		}
		 if(ConfigurationFactory.ToolPowerMesseges == true){	
			 String Messy = ("Rank: "+(getToolLevel(itemStack)));
				ChatComponentTranslation chatmessy = new ChatComponentTranslation(EnumChatFormatting.GRAY + Messy, new Object[0]);
				((EntityPlayer) entityLiving).addChatComponentMessage(chatmessy);
			    }
		//
		//System.out.println("Empowering!"+"  The level is... "+(getToolLevel(itemStack))); 
	}
	

	/** returns a rounded number for tool levels**/
	public int getEff2Level(ItemStack itemStack) {
		if (itemStack == null) {
			return 0;
		}
		//int eff = EnchantmentHelper.getEnchantmentLevel(32, itemStack);
		int eff = (getToolLevel(itemStack));
		
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
	////////////////////////////////////////////////////////////////
	//scan inventory for total appropriate items
	////////////////////////////////////////////////////////////////
	public int item_amount(EntityPlayer player, ItemStack thestaff){
		int amount = 0;
		ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff));  
		ItemStack stacky_f = stacky;
		Boolean whitelist_flag;
		whitelist_flag = Whitelist_Util.Block_Whitelist(returnTBlock(thestaff), player, returnTMeta(thestaff));
		
		if(whitelist_flag){
			stacky_f = Whitelist_Util.Whitelist_stack(stacky, player, returnTBlock(thestaff), returnTMeta(thestaff));}
		
    	amount = InventoryUtil.amount_Scan(stacky_f, player);
    	
    	return amount;
	}
	
	 
		/** Offmode here prevents getblock from double dipping into switch mode code because i suck**/
		// ///////////////////////////////////////////////////////////
			public int getOffMode(ItemStack itemStack) {
				if (itemStack.stackTagCompound == null) {				
					return 0;
				}
				return itemStack.stackTagCompound.getInteger("OffMode");
			}		
			
			////////////////////////////////////////////////////////////////
			/** metadat functions **/
			////////////////////////////////////////////
			public void setOffMode(ItemStack itemStack, int Value) {
				if (itemStack.stackTagCompound == null) {
					itemStack.setTagCompound(new NBTTagCompound());
				}
				itemStack.stackTagCompound.setInteger("OffMode", Value);			
			}
					   
		  	public int getMode(ItemStack itemStack) {
				if (itemStack.stackTagCompound == null) {
					return 2;
				}
				if (itemStack.stackTagCompound.getInteger("mode") == 0){
					setMode(itemStack, 2); 
				}

				return itemStack.stackTagCompound.getInteger("mode");

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
					setTBlock(itemStack, 1);
					return 1;
				}

				return itemStack.stackTagCompound.getInteger("TBlock");
				
			}
			public void setTBlock(ItemStack itemStack, int Value)
			{
				if(itemStack.stackTagCompound == null)
				{
					itemStack.setTagCompound(new NBTTagCompound());
					itemStack.stackTagCompound.setInteger("TBlock",  1 );
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
			
		   
		    //////////////////////////////////////////////////////////////
		    
	
	/**
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
  	
		return;
  			
  }
	 **/

}
