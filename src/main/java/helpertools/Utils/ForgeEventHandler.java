package helpertools.Utils;


import helpertools.Main;
import helpertools.Com.Config;
import helpertools.Com.ItemRegistry;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
	
	
	@SubscribeEvent
	public void BombCharm_Handler(LivingHurtEvent event){
		
		if(!Config.BombCharm_Ward){return;}
		if (!(event.getEntityLiving() instanceof EntityPlayer) || event.isCanceled()){
			return;
		}
		if(!event.getSource().isExplosion()){return;}
		EntityPlayer player = (EntityPlayer)event.getEntityLiving();
		
		Float Damage = event.getAmount();
		
		Float Experience = player.experience;
		
		int Levels = player.experienceLevel;		
		
		Float Health = player.getHealth();
		
		if(Damage >= Health){
			
			if(InventoryUtil.StackScan(new ItemStack(ItemRegistry.bomb_charm), player.inventory) != -1){
			
			InventoryUtil.consumeInventoryItemStack(new ItemStack(ItemRegistry.bomb_charm), player.inventory);
			player.setHealth(2F);
			player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 80, 1));
			event.setCanceled(true);
			ModUtil.print(player,TextFormatting.DARK_RED, "Bomb charm activated");
			}
		}
		
		
	}

	
}