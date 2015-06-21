package helpertools;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class DungeonLoot extends Helpertoolscore{
	
	
	
	/**  Tcon**/
	public static void addLoot(FMLInitializationEvent event)
    
    {	//Min stack, Max stack, Weight/rarity
    	//ChestGenHooks.getInfo("bonusChest").addItem(new WeightedRandomChestContent(new ItemStack(Helpertoolscore.staffoftransformation2), 1, 1, 10));
    	ChestGenHooks.getInfo("bonusChest").addItem(new WeightedRandomChestContent(new ItemStack(Helpertoolscore.chocolatemilk), 1, 8, 5));
    	//ChestGenHooks.getInfo("bonusChest").addItem(new WeightedRandomChestContent(new ItemStack(Helpertoolscore.dynamitebolt), 1, 3, 50));
    	logger.info("Chest Things Loaded");
    }

	
	

    //DungeonHooks.addDungeonLoot(new ItemStack(youritem), 10, 2, 5);
    //ChestGenHooks.addDungeonLoot(ChestGenHooks(String category, WeightedRandomChestContent[] items, int min, int max));
    //(ChestGenHooks("villageBlacksmith"), (new ItemStack(Helpertoolscore.staffoftransformation2)) , 100, 1, 1);
	
	
}
