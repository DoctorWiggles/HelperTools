package helpertools.Utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import helpertools.Com.ItemRegistry;
import helpertools.Com.Items.Item_BombCharm;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;

public class InventoryUtil{
	
	public static List<NonNullList<ItemStack>> getAllInventories(InventoryPlayer inventory){
		List<NonNullList<ItemStack>> allInventories;
		allInventories = Arrays.<NonNullList<ItemStack>>asList(new NonNullList[] {
				inventory.mainInventory, inventory.armorInventory, inventory.offHandInventory});
		
		 return allInventories;
	}
	
	public static boolean scanStack(ItemStack stack, InventoryPlayer inventory){
		return inventory.hasItemStack(stack);
	}

	public static boolean consumeStack(ItemStack itemStackIn, InventoryPlayer inventory)
    {
        label19:

        for (List<ItemStack> list :getAllInventories(inventory))
        {
            Iterator iterator = list.iterator();

            while (true)
            {
                if (!iterator.hasNext())
                {
                    continue label19;
                }

                ItemStack itemstack = (ItemStack)iterator.next();

                if (!itemstack.isEmpty() && itemstack.isItemEqual(itemStackIn))
                {
                	itemstack.shrink(1);
                    break;
                }
            }

            return true;
        }

        return false;
    }
	
	public static boolean hasItem(Item item, InventoryPlayer inventory)
    {
        label19:

        for (List<ItemStack> list :getAllInventories(inventory))
        {
            Iterator iterator = list.iterator();

            while (true)
            {
                if (!iterator.hasNext())
                {
                    continue label19;
                }

                ItemStack itemstack = (ItemStack)iterator.next();

                if (!itemstack.isEmpty() && itemstack.getItem() == item)
                {
                    break;
                }
            }

            return true;
        }

        return false;
    }
	
	public static int charmScan( InventoryPlayer inventory)
    {	int highest = 0;  	
        label19:

        for (List<ItemStack> list :getAllInventories(inventory))
        {
            Iterator iterator = list.iterator();

            while (true)
            {
                if (!iterator.hasNext())
                {
                    continue label19;
                }

                ItemStack itemstack = (ItemStack)iterator.next();

                if (!itemstack.isEmpty() && itemstack.getItem() instanceof Item_BombCharm)
                {
                	 Item_BombCharm charm = (Item_BombCharm)itemstack.getItem();
                	 int newest = charm.getsize(itemstack);
                	 if(newest > highest)highest = newest;                    
                }
            }
        }

        return highest;
    }

	public static boolean consumeItem(Item item, InventoryPlayer inventory){
		return consumeStack(new ItemStack(item),inventory);
	}

	public static boolean consumeItem(Item item, EntityPlayer player){
		return consumeStack(new ItemStack(item),player.inventory);
	}

}
