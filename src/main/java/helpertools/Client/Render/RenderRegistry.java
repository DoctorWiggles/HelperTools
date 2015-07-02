package helpertools.Client.Render;

import helpertools.Main;
import helpertools.Common.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class RenderRegistry {

	public static void registerItemRenderer() {
		reg(ItemRegistry.chocolatemilk);
		reg(ItemRegistry.milkbottle);
	}

	//==========================================================================

	public static String modid = Main.MODID;

	public static void reg(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register
		(item, 0, new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}