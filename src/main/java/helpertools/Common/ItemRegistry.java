package helpertools.Common;

import helpertools.Main;
import helpertools.Common.Blocks.FalseBedrock;
import helpertools.Common.Blocks.TileEntityTranscriber;
import helpertools.Common.Blocks.TranscriberBlock;
import helpertools.Common.Items.ItemChocolateMilk;
import helpertools.Common.Items.ItemMilkBottle;
import helpertools.Common.Tools.ItemEuclideanTransposer;
import helpertools.Common.Tools.ItemStaffofExpansion;
import helpertools.Common.Tools.ItemStaffofTransformation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static Item chocolatemilk;
	public static Item milkbottle;
	public static Item expandertool;
	public static Item exchange_tool;
	public static Item pattern_tool;

	public static void createItems() {
		Main.logger.info("Registering items");
		GameRegistry.registerItem(chocolatemilk = new ItemChocolateMilk(3, 0.5f, true, "chocolatemilk_item"), "chocolatemilk_item");
		GameRegistry.registerItem(milkbottle = new ItemMilkBottle("bottledmilk_item"), "bottledmilk_item");
		GameRegistry.registerItem(expandertool = new ItemStaffofExpansion(ConfigurationFactory.ExpRodMatt, "expander_item"), "expander_item");
		GameRegistry.registerItem(exchange_tool = new ItemStaffofTransformation(ConfigurationFactory.ExpRodMatt, "exchange_item"), "exchange_item");
		GameRegistry.registerItem(pattern_tool = new ItemEuclideanTransposer(ConfigurationFactory.ExpRodMatt, "pattern_item"), "pattern_item");
	}
	
	public static Block falseBedrock;
	public static Block transcriberBlock;
	
	public static void createBlocks(){
		Main.logger.info("Registering blocks");
		GameRegistry.registerBlock(falseBedrock = new FalseBedrock("falseBedrock_block", Material.rock ,15F,20F), "falseBedrock_block");
        GameRegistry.registerBlock(transcriberBlock = new TranscriberBlock("transcriber_block"),"transcriber_block");
        GameRegistry.registerTileEntity(TileEntityTranscriber.class, TileEntityTranscriber.publicName);
		
	}
	
	
	

}
