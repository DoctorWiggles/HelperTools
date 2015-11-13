package helpertools.util;

import helpertools.Common_Registry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryUtil{
	
	/** Adds a metadata sensitive search and remove for items. **/
	  
	  /**Scans inventory for a specific item, and metadata (stack sensitive)**/
	  private static int StackScan(ItemStack stack, InventoryPlayer entity)
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
	  
	  /**Attempts to remove an itemstack from the inventory (metadata senstive)**/
	  public static boolean consumeInventoryItemStack(ItemStack stack, InventoryPlayer inventory)
	    {
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
	  
	  
	  
	  
	//=====================================================================================// 
	  
	  
	  
	  static ItemStack cbc_stack = new ItemStack(Common_Registry.cbc);
	 
	 /** scans a specicific slot for charms **/
	  private static int scan_cbc(InventoryPlayer entity)
	    {
		  		  
		 for (int i = 0; i < entity.mainInventory.length; ++i)
	        {	        	
	        	 if (entity.mainInventory[i] != null && entity.mainInventory[i].isItemEqual(cbc_stack))
		            {
	                return i;
	            }
	        }
	        
	        return -1;
	    }
	  
	  /**Scans inventory for a charm and it's level**/
	  public static int check_cbc_charms(InventoryPlayer inventory)
	    {		  
	        int i = scan_cbc(inventory);
	        
	        if (i < 0)
	        {	        	
	            return 0;
	        }
	        else
	        {
	            
	            int Toolmax;
	            Toolmax = EnchantmentHelper.getEnchantmentLevel(32, inventory.mainInventory[i]);
	           
	            return Toolmax;
	        }
	    }
	  
	  
	  
	  
	  
	  
	
}
