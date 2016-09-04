package helpertools.Utils;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class InventoryUtil{
	
	/** Adds a metadata sensitive search and remove for items. **/
	  
	  /**Scans inventory for a specific item, and metadata (stack sensitive)**/
	  public static int StackScan(ItemStack stack, InventoryPlayer entity)
	    {
		  //String asd = entity.getCommandSenderName();
		  //System.out.println(" " + asd);
		 for (int i = 0; i < entity.mainInventory.length; ++i)
	        {	        	
	        	 if (entity.mainInventory[i] != null && entity.mainInventory[i].isItemEqual(stack))
		            {
	        		 //System.out.println("Stack Return? : " + i);
	                return i;
	            }
	        }
	        
	        return -1;
	    }
	  /**Scans inventory for a specific item**/
	  public static boolean hasItem(Item item, InventoryPlayer entity)
	    {
	        for (int i = 0; i < entity.mainInventory.length; ++i)
	        {
	            if (entity.mainInventory[i] != null && entity.mainInventory[i].getItem() == item)
	            {
	            	
	                return true;
	            }
	        }	        
	        return false;
	    }
	  
	  /**Attempts to remove an itemstack from the inventory (metadata senstive)**/
	  public static boolean consumeInventoryItemStack(ItemStack stack, InventoryPlayer inventory)
	    {
		  //InventoryPlayer inventory = new InventoryPlayer(entity);
		  
	        int i = StackScan(stack, inventory);
	        
	        if (i < 0)
	        {	        	
	            return false;
	        }
	        else
	        {
	            if (--inventory.mainInventory[i].stackSize <= 0)
	            {
	            	inventory.mainInventory[i] = null;
	            }	            
	            //System.out.println("Consume true? : " + i);
	            return true;
	        }
	    }
	  
	  public static boolean consumeItem(Item item, InventoryPlayer inventory){
		  return consumeInventoryItemStack(new ItemStack(item),inventory);
	  }
	  
	  public static boolean consumeItem(Item item, EntityPlayer player){
		  return consumeInventoryItemStack(new ItemStack(item),player.inventory);
	  }
	  /** TODO Arrow types and enchantments
	  public static ItemStack findAmmo(EntityPlayer player)
	    {
	        if (isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
	        {
	            return player.getHeldItem(EnumHand.OFF_HAND);
	        }
	        else if (isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
	        {
	            return player.getHeldItem(EnumHand.MAIN_HAND);
	        }
	        else
	        {
	            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
	            {
	                ItemStack itemstack = player.inventory.getStackInSlot(i);

	                if (isArrow(itemstack))
	                {
	                    return itemstack;
	                }
	            }

	            return null;
	        }
	    }
	  
	  public static boolean isArrow(@Nullable ItemStack stack)
	    {
	        return stack != null && stack.getItem() instanceof ItemArrow;
	    }
	  **/
	
}
