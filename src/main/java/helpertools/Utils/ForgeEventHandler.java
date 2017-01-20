package helpertools.Utils;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import helpertools.Main;
import helpertools.Com.ItemRegistry;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
public class ForgeEventHandler {
  
	
	
	/** Wuppy source**/
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
	
	/**https://github.com/Vazkii/Botania/blob/master/src/main/java/vazkii/botania/common/core/loot/LootHandler.java**/
	@SubscribeEvent
	public void Table_Additives(LootTableLoadEvent e){
		
		
		String name = e.getName().toString();	
		String prefix = "minecraft:chests/";
		try{
		if (name.startsWith(prefix)) {
			String file = name.substring(name.indexOf(prefix) + prefix.length());
			switch (file) {
			case "abandoned_mineshaft":
			case "desert_pyramid":
			case "jungle_temple":
			case "simple_dungeon":
			case "spawn_bonus_chest":
			case "stronghold_corridor":
			case "elder_guardian":
			case "village_blacksmith": e.getTable().addPool(getAdditive("dungeon_additive")); break;
			default: break;
			}
		}
		}
		catch(Exception exc){
		}
	}
	
	private LootPool getAdditive(String entryName) {
		return new LootPool(new LootEntry[] { getAdditiveEntry(entryName, 1) }, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "getAdditive_pool");
	}

	private LootEntryTable getAdditiveEntry(String name, int weight) {
		return new LootEntryTable(new ResourceLocation(Main.MODID,  name), weight, 0, new LootCondition[0], "Additive_entry");
	}

	
}