package helpertools.Client.Render;

import helpertools.Main;
import helpertools.Common.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class RenderRegistry {

	public static void registerItemRenderer() {
		Main.logger.info("Registering Renders");
		reg(ItemRegistry.chocolatemilk);
		reg(ItemRegistry.milkbottle);
		reg(ItemRegistry.expandertool);
		reg(ItemRegistry.exchange_tool);
		reg(ItemRegistry.pattern_tool);
		
	}
	
	public static void registerBlockRenderer(){
		reg(ItemRegistry.falseBedrock);
		reg(ItemRegistry.transcriberBlock);
	}

	//==========================================================================//

	public static String modid = Main.MODID;

	public static void reg(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register
		(item, 0, new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	

	public static void reg(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(modid + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
}