package helpertools.Com;

import helpertools.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;

/** https://github.com/Choonster/TestMod3/blob/7d877920df256a11d7797178addd8d9d32cf92c7/src/main/java/choonster/testmod3/init/ModLootTables.java **/
public class ModLootTables {
	
	public static final ResourceLocation Dungeon_Additive = register("dungeon_additive");

	/**
	 * Register a {@link LootTable} with the specified ID.
	 *
	 * @param id The ID of the LootTable without the testmod3: prefix
	 * @return The ID of the LootTable
	 */
	private static ResourceLocation register(String id) {
		return LootTableList.register(new ResourceLocation(Main.MODID, id));
	}
}
