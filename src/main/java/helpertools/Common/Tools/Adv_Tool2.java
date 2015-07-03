package helpertools.Common.Tools;

import helpertools.Main;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;


public class Adv_Tool2 extends ItemSpade{

	//public short aShort;
	public Adv_Tool2(ToolMaterial material) {
		super(material);
		// TODO Auto-generated constructor stub
	}

    public boolean isMetadataSpecific(ItemStack itemStack)
 	{
 		return false;
 	}
    

	/**
	 public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	    {
	    nbt.setShort("Damage", this.aShort);
		 
		 return nbt;
	    }
	**/


    /////////////////////////////////////////////////////////////////////
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isheld) {
    	
    	if (!stack.hasTagCompound()) {
    		stack.setTagCompound(new NBTTagCompound());    		
    		stack.getTagCompound().setInteger("TBlock", 0);
    		stack.getTagCompound().setInteger("TMeta", 0);
    		stack.getTagCompound().setInteger("mode", 2);    		
    		stack.getTagCompound().setInteger("ToolLevel", 0);
    		stack.getTagCompound().setInteger("OffMode", 0); 
    		
    		//String Messy = "No compound";
    		//ChatComponentTranslation chatmessy = new ChatComponentTranslation(EnumChatFormatting.GRAY + Messy, new Object[0]);
			//((EntityPlayer) entity).addChatComponentMessage(chatmessy);
    		}
    	if (stack.hasTagCompound() && (Main.Randy.nextFloat() >= 1)) {
    		String Messy = "mode " + (getMode(stack) + " Block " + (getTBlock(stack))
    				+ " Level " + getToolLevel(stack)
    				+ " Offmode " + getOffMode(stack)
    				+ " meta " + getTMeta(stack));
    		ChatComponentTranslation chatmessy = new ChatComponentTranslation(EnumChatFormatting.GRAY + Messy, new Object[0]);
			((EntityPlayer) entity).addChatComponentMessage(chatmessy);
    	}
    	

   	

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
  	public int getMode(ItemStack itemStack) {
		return itemStack.getTagCompound().getInteger("mode");

	}        
      
      public void setMode(ItemStack itemStack, int Value)
   	{
   		itemStack.getTagCompound().setInteger("mode",  Value );
   		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
   	} 
      
   	///////////////////
      
    public int getTBlock(ItemStack itemStack)
	{
		return itemStack.getTagCompound().getInteger("TBlock");
		
	}
	public void setTBlock(ItemStack itemStack, int Value)
	{
		itemStack.getTagCompound().setInteger("TBlock",  Value );
		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	} 
	////////
	public int getTMeta(ItemStack itemStack)
	{
		return itemStack.getTagCompound().getInteger("TMeta");
		
	}
	public void setTMeta(ItemStack itemStack, int Value)
	{
		itemStack.getTagCompound().setInteger("TMeta",  Value );
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
			
			return itemStack.getTagCompound().getInteger("OffMode");

		}		
		public void setOffMode(ItemStack itemStack, int Value) {
			
			itemStack.getTagCompound().setInteger("OffMode", Value);			
		}
    
    //////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////
    
    /** Tool levels **/
		public int getToolLevel(ItemStack itemStack) {
			
			return itemStack.getTagCompound().getInteger("ToolLevel");

		}		
		public void setToolLevel(ItemStack itemStack, int Value) {
			
			itemStack.getTagCompound().setInteger("ToolLevel", Value);			
		}
		
		public void ToolEmpower(ItemStack itemStack, EntityLivingBase entityLiving){
			int Toolmax = EnchantmentHelper.getEnchantmentLevel(32, itemStack);
			int NextLevel = (getToolLevel(itemStack))+1;
			if(NextLevel>Toolmax){
				setToolLevel(itemStack,0);
				entityLiving.worldObj.playSoundAtEntity(entityLiving, "random.fizz", (float)(1), (float)(1.3));
			}
			if(NextLevel<=Toolmax){
				setToolLevel(itemStack,NextLevel);
				entityLiving.worldObj.playSoundAtEntity(entityLiving, "random.orb", (float)(.8), (float)( itemRand.nextFloat()*.75+.2));
			}
			
			 //if(Helpertoolscore.ToolPowerMesseges == true){	
				 String Messy = ("Rank: "+(getToolLevel(itemStack)));
					ChatComponentTranslation chatmessy = new ChatComponentTranslation(EnumChatFormatting.GRAY + Messy, new Object[0]);
					((EntityPlayer) entityLiving).addChatComponentMessage(chatmessy);
				   // }
				    
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
		

		
}
