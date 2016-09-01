package helpertools.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
	
}
