package helpertools.items;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import helpertools.ConfigurationFactory;
import helpertools.HelpTab;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class Item_creative_builder_charm extends Item {

	  public Item_creative_builder_charm(String unlocalname) {
	       super();
	       this.maxStackSize = 1;  
	       setUnlocalizedName(unlocalname);
	       setCreativeTab(HelpTab.HelperTools);
	       setTextureName("helpertools:cbc_0");	       
	   }
	  
	  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	    {list.add(EnumChatFormatting.ITALIC + "Boosts Bomb Radius");
	    list.add(EnumChatFormatting.ITALIC + "Right click to toggle");}
	  
	  //here we toggle and manipulate enchantments to simulate powering up
	  public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	    {

		  if( player.worldObj.isRemote){return stack;}
		  int Toolmax;		  
		  Toolmax = EnchantmentHelper.getEnchantmentLevel(32, stack);
		  int nextMax = 0;
		  
		  if(player.isSneaking()){
			  if(Toolmax == 0){
				  return stack;
			  }
				  nextMax = 0;
		  }
		  if(!player.isSneaking()){
			  
			  nextMax= Toolmax + 1;
			  if(nextMax >5){nextMax = 0;}
		  }		  		  
		  		          	 
		  EnchantmentHelper.setEnchantments(Mappy(stack, nextMax), stack);
		  if (nextMax ==0){
			  stack.getTagCompound().removeTag("ench");			 
		  }
		  
		  if(ConfigurationFactory.ToolModeMesseges == true){
        	 ChatComponentTranslation text = new ChatComponentTranslation(EnumChatFormatting.GRAY +"Boost: +"+ nextMax*5, new Object[0]);
        	 ChatComponentTranslation text2 = new ChatComponentTranslation(EnumChatFormatting.GRAY +"Boost: off", new Object[0]);
        	 if (nextMax == 0){ player.addChatComponentMessage(text2); }
        	 else
        	 player.addChatComponentMessage(text); 
        	 player.worldObj.playSoundAtEntity(player, "random.orb", (float)(.8), 
        			 (float)( itemRand.nextFloat()*.75+.2));
		  }
			return stack;
		  
	    }
	  
	  //custom enchantment map
	  public static Map Mappy(ItemStack stack, int level)
	    {
		  LinkedHashMap linkedhashmap = new LinkedHashMap();
	        
	                short short1 = 32;
	                short short2 = (short) level;
	                linkedhashmap.put(Integer.valueOf(short1), Integer.valueOf(short2));
	        
	        return linkedhashmap;
	    }
	  
	    
  
  
	  
	  
}