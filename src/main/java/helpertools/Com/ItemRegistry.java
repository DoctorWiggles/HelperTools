package helpertools.Com;

import helpertools.Main;
import helpertools.Com.Blocks.FalseBedrock;
import helpertools.Com.Blocks.LooseDirtBlock;
import helpertools.Com.Blocks.TileEntityTranscriber;
import helpertools.Com.Blocks.TranscriberBlock;
import helpertools.Com.Blocks.TranscriberBlock_Item;
import helpertools.Com.Items.Debug_States;
import helpertools.Com.Items.ItemChocolateMilk;
import helpertools.Com.Items.ItemDirtBomb;
import helpertools.Com.Items.ItemDynamiteBolt;
import helpertools.Com.Items.ItemMilkBottle;
import helpertools.Com.Items.Item_MirageHusk;
import helpertools.Com.Tools.ItemEuclideanTransposer;
import helpertools.Com.Tools.ItemStaffofExpansion;
import helpertools.Com.Tools.ItemStaffofTransformation;
import helpertools.Com.Tools.ItemTorchLauncher;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static Item chocolatemilk;
	public static Item milkbottle;
	public static Item expandertool;
	public static Item exchange_tool;
	public static Item pattern_tool;
	public static Item crossbow_tool;	
	public static Item debug_states_tool;
	public static Item dynamitebolt;
	public static Item dirtbomb;
	public static Item miragehusk;
	

	public static void createItems() {
		Main.logger.info("Registering items");
		//Legacy Registry
		//GameRegistry.registerItem(chocolatemilk = new ItemChocolateMilk(3, 0.5f, true, "chocolatemilk_item"), "chocolatemilk_item");
		reg(chocolatemilk= new ItemChocolateMilk(3, 0.5f, true, "chocolatemilk_item"), "chocolatemilk_item");
		reg(milkbottle = new ItemMilkBottle("bottledmilk_item"), "bottledmilk_item");
		reg(expandertool = new ItemStaffofExpansion(Config.ExpRod_Matt, "expander_item"), "expander_item");
		reg(exchange_tool = new ItemStaffofTransformation(Config.Exchange_Matt, "exchange_item"), "exchange_item");
		reg(pattern_tool = new ItemEuclideanTransposer(Config.Pattern_Matt, "pattern_item"), "pattern_item");
		reg(crossbow_tool = new ItemTorchLauncher(Config.Crossbow_Matt, "crossbow_item"),"crossbow_item");		
		//reg(debug_states_tool = new Debug_States( "debug_states_item"),"debug_states_item");
		reg(dynamitebolt = new ItemDynamiteBolt( "dynamitebolt_item"),"dynamitebolt_item");
		reg(dirtbomb = new ItemDirtBomb( "dirtbomb_item"),"dirtbomb_item");
		reg(miragehusk = new Item_MirageHusk("miragehusk_item", Mystic_Material), "miragehusk_item");
	
		
	}
	public static ArmorMaterial Mystic_Material = 
			EnumHelper.addArmorMaterial("Mystic", Main.PATH+"Mystic",
					12, new int[]{2, 2, 2, 2},
					35, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	
	//Updated Registry Interface
	public static void reg(Item item, String name){
		
		item.setRegistryName(name);
		GameRegistry.register(item);
	}

	//public static 
	
	public static Block falseBedrock;
	public static Block transcriberBlock;
	public static Block LooseDirtBlock;
	public static Item TranscriberBlock_Item;
	
	public static void createBlocks(){
		Main.logger.info("Registering blocks");
		reg(falseBedrock = new FalseBedrock("falseBedrock_block", Material.ROCK ,15F,20F), "falseBedrock_block");
		reg(LooseDirtBlock = new LooseDirtBlock("loosedirt_block"),"loosedirt_block");
		custom(transcriberBlock = new TranscriberBlock("transcriber_block"), "transcriber_block",
				TranscriberBlock_Item = new TranscriberBlock_Item(transcriberBlock));		
        
        //Tiles
        GameRegistry.registerTileEntity(TileEntityTranscriber.class, TileEntityTranscriber.publicName);
		
	}
	//Updated Registry Interface
	public static void reg(Block block, String name){

		block.setRegistryName(name);
		GameRegistry.register(block);
		register_item_Block(block);
	}
	
	public static void register_item_Block(Block name){	
		
		Item dummy_itemBlockSimple = new ItemBlock(name);
		dummy_itemBlockSimple.setRegistryName(name.getRegistryName());
	    GameRegistry.register(dummy_itemBlockSimple);
	}
	
	public static void custom(Block block, String name, Item item){

		block.setRegistryName(name);
		GameRegistry.register(block);
		item.setRegistryName(block.getRegistryName());
		GameRegistry.register(item);
	}
	
	

}
