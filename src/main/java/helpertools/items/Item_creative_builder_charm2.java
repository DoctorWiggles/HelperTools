package helpertools.items;

import java.util.LinkedHashMap;
import java.util.Map;

import helpertools.HelpTab;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class Item_creative_builder_charm2 extends Item {

	  public Item_creative_builder_charm2(String unlocalname) {
	       super();
	       this.maxStackSize = 1;  
	       setUnlocalizedName(unlocalname);
	       setCreativeTab(HelpTab.HelperTools);
	       setTextureName("helpertools:cbc_0");
	       
	   }
	  
	  
	  public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x1, int y1, int z1, int theface, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	    {
		  if( player.worldObj.isRemote){return false;}
		  int Toolmax;
		  Toolmax = EnchantmentHelper.getEnchantmentLevel(32, stack);
		  
		  Map mapu = EnchantmentHelper.getEnchantments(stack);
		  
		  //NBTTagList nbt = stack.getEnchantmentTagList();
		  NBTTagList nbt = stack.getItem() == Items.enchanted_book ? Items.enchanted_book.func_92110_g(stack) : stack.getEnchantmentTagList();
		  short short1 = nbt.getCompoundTagAt(32).getShort("id");
          short short2 = nbt.getCompoundTagAt(32).getShort("lvl");
          
          ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("id "+short1 +" lvl "+ short2 + " max "+ Toolmax + " MAP "+ mapu, new Object[0]);
        	 player.addChatComponentMessage(chatcomponenttranslation); 
        	 
        	 EnchantmentHelper.setEnchantments(Mappy(stack, 8), stack);
        	 
        	 ChatComponentTranslation chatcomponenttranslation2 = new ChatComponentTranslation("id "+short1 +" lvl "+ short2 + " max "+ Toolmax + " MAP "+ mapu, new Object[0]);
        	 player.addChatComponentMessage(chatcomponenttranslation2); 
        	 
		  //nbt.setShort("lvl", (short)((Integer)p_82782_0_.get(Integer.valueOf(i))).intValue());
		  
			return false;
		  
	    }
	  
	  public static Map Mappy(ItemStack stack, int level)
	    {
	        LinkedHashMap linkedhashmap = new LinkedHashMap();
	        //NBTTagList nbttaglist = p_82781_0_.getItem() == Items.enchanted_book ? Items.enchanted_book.func_92110_g(p_82781_0_) : p_82781_0_.getEnchantmentTagList();

	         
	                short short1 = 32;
	                		//nbttaglist.getCompoundTagAt(i).getShort("id");
	                short short2 = (short) level;
	                //nbttaglist.getCompoundTagAt(i).getShort("lvl");
	                linkedhashmap.put(Integer.valueOf(short1), Integer.valueOf(short2));
	        

	        return linkedhashmap;
	    }
  
  
  
  
	  
	  
}