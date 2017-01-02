package helpertools.Utils;


import helpertools.Com.ItemRegistry;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
public class ForgeEventHandler {
  
	
	////////////////////////////////////
	/** credit Wuppy & ToolTorchMod**/
	//////////////////////////////////
	
	@SubscribeEvent
	public void milkbottlehandler(EntityInteract event){

    	
		if (event.isCanceled() ||  !(event.getTarget() instanceof EntityCow)) { 
		      return;
		    }
		
		ItemStack heldItem = event.getEntityPlayer().inventory.getCurrentItem();
		 if ((heldItem == null) || (!(heldItem.getItem() instanceof ItemGlassBottle))) {
		      return;
		    }
		 Item Bottled = ItemRegistry.milkbottle;
		 
		 
		 if (heldItem.stackSize-- == 1)
         {
			 event.getEntityPlayer().inventory.setInventorySlotContents(event.getEntityPlayer().inventory.currentItem, new ItemStack(Bottled));
         }
         else if (!event.getEntityPlayer().inventory.addItemStackToInventory(new ItemStack(Bottled)))
         {
        	 //event.getEntityPlayer().dropPlayerItemWithRandomChoice(new ItemStack(Bottled, 1, 0), false);
        	 event.getEntityPlayer().dropItem(new ItemStack(Bottled, 1, 0), false);
         }

		 event.setCanceled(true);
	}
	
}