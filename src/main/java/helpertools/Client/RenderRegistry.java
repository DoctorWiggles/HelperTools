package helpertools.Client;

import helpertools.Main;
import helpertools.Common.ConfigurationFactory;
import helpertools.Common.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
/**http://bedrockminer.jimdo.com/modding-tutorials/basic-modding-1-8/first-item/**/
public final class RenderRegistry {

	public static void registerItemRenderer() {
		Main.logger.info("Registering Renders");
		reg(ItemRegistry.chocolatemilk);
		reg(ItemRegistry.milkbottle);
		reg(ItemRegistry.pattern_tool);
		reg(ItemRegistry.expandertool);
		reg(ItemRegistry.exchange_tool);
		reg(ItemRegistry.debug_states_tool);
		reg(ItemRegistry.dynamitebolt);
		reg(ItemRegistry.crossbow_tool);
		//ModelBakery.addVariantName(Item.getItemFromBlock(ModBlocks.propertyBlock),"tutorial:block_properties_black", "tutorial:block_properties_white");
		
		//ModelBakery.addVariantName(ItemRegistry.crossbow_tool,"helpertools:crossbow_item", "helpertools:crossbow_item.torch");
		//Meta_reg(ItemRegistry.expandertool, 0, "expander_item_alt");
		
		//Meta_reg(ItemRegistry.crossbow_tool, 0, "crossbow_item_torch");
		//Meta_reg(ItemRegistry.crossbow_tool, 0, "crossbow_item_torch");
		/**
		if (!ConfigurationFactory.Use_3D_Models){
			Main.logger.info("Using 2D Sprites");
			Alt_Reg(ItemRegistry.expandertool);
			Alt_Reg(ItemRegistry.exchange_tool);	
		}
		**/
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
		Main.logger.info(modid + ":" + item.getUnlocalizedName().substring(5), "inventory");
	}
	
	public static void Meta_reg(Item item, int meta, String file) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register
	    (item, meta, new ModelResourceLocation(modid + ":" + file, "inventory"));
	    Main.logger.info(modid + ":" + file, "inventory");
	}
	

	public static void reg(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(modid + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
}