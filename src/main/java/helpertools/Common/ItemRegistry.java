package helpertools.Common;

import helpertools.Common.Items.ItemChocolateMilk;
import helpertools.Common.Items.ItemMilkBottle;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ItemRegistry {

	public static Item chocolatemilk;
	public static Item milkbottle;

	public static void createItems() {
		GameRegistry.registerItem(chocolatemilk = new ItemChocolateMilk(3, 0.5f, true, "chocolatemilk_item"), "chocolatemilk_item");
		GameRegistry.registerItem(milkbottle = new ItemMilkBottle("bottledmilk_item"), "bottledmilk_item");
	
	
	}

}
