package helpertools.Com;

import helpertools.Main;
import helpertools.Com.Blocks.BalloonBlock;
import helpertools.Com.Blocks.FalseBedrock;
import helpertools.Com.Blocks.FloaterBlock;
import helpertools.Com.Blocks.LampBlock;
import helpertools.Com.Blocks.LooseDirtBlock;
import helpertools.Com.Blocks.TileEntityTranscriber;
import helpertools.Com.Blocks.TranscriberBlock;
import helpertools.Com.Items.Chest_Debugger;
import helpertools.Com.Items.ItemChocolateMilk;
import helpertools.Com.Items.ItemDirtBomb;
import helpertools.Com.Items.ItemDynamiteBolt;
import helpertools.Com.Items.ItemMilkBottle;
import helpertools.Com.Items.Item_MirageHusk;
import helpertools.Com.Tools.ItemEuclideanTransposer;
import helpertools.Com.Tools.ItemStaffofExpansion;
import helpertools.Com.Tools.ItemStaffofTransformation;
import helpertools.Com.Tools.ItemTorchLauncher;
import helpertools.Utils.HelpTab;
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
	public static Item shadecore;
	

	public static void createItems() {
		Main.logger.info("Registering items");		
		
		reg(chocolatemilk = new ItemChocolateMilk(3, 0.5f, true, "chocolatemilk_item"));
		reg(milkbottle = new ItemMilkBottle("bottledmilk_item"));
		reg(expandertool = new ItemStaffofExpansion(Config.ExpRod_Matt, "expander_item"));
		reg(exchange_tool = new ItemStaffofTransformation(Config.Exchange_Matt, "exchange_item"));
		reg(pattern_tool = new ItemEuclideanTransposer(Config.Pattern_Matt, "pattern_item"));
		reg(crossbow_tool = new ItemTorchLauncher(Config.Crossbow_Matt, "crossbow_item"));		
		//reg(debug_states_tool = new Debug_States( "debug_states_item"),"debug_states_item");
		reg(dynamitebolt = new ItemDynamiteBolt( "dynamitebolt_item"));
		reg(dirtbomb = new ItemDirtBomb( "dirtbomb_item"));
		reg(miragehusk = new Item_MirageHusk("miragehusk_item", Mystic_Material));
		reg(shadecore = new Chest_Debugger("shadecore"));
	
		
	}
	public static ArmorMaterial Mystic_Material = 
			EnumHelper.addArmorMaterial("Mystic", Main.PATH+"Mystic",
					12, new int[]{2, 2, 2, 2},
					35, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	
	//Updated Registry Interface
	public static void reg(Item item){
		
		item.setRegistryName(item.getUnlocalizedName().substring(5));
		GameRegistry.register(item);
	}

	//public static 
	
	public static Block falseBedrock;
	public static Block transcriberBlock;
	public static Item  TranscriberBlock_Item;
	public static Block LooseDirtBlock;	
	public static Block BalloonBlock;
	public static Block LampBlock;
	public static Item  LampBlock_Item;
	public static Block LampBlock_on;
	public static Block LampBlock_perm;
	public static Block FloaterBlock;
	public static Item  FloaterBlock_Item;
	
	public static void createBlocks(){
		Main.logger.info("Registering blocks");
		reg(falseBedrock = new FalseBedrock("falseBedrock_block", Material.ROCK ,15F,20F));
		reg(LooseDirtBlock = new LooseDirtBlock("loosedirt_block"));
		custom(transcriberBlock = new TranscriberBlock("transcriber_block"),
				TranscriberBlock_Item = new TranscriberBlock.TranscriberBlock_Item(transcriberBlock));	
		reg(BalloonBlock = new BalloonBlock("balloon_block"));
		custom(LampBlock = new LampBlock("lamp_block", false),
				LampBlock_Item = new LampBlock.LampBlock_Item(LampBlock));
		reg(LampBlock_on = new LampBlock("lamp_block_on", true));
		reg(LampBlock_perm = new LampBlock("lamp_block_perm", true));
		custom(FloaterBlock = new FloaterBlock("floater_block"),
				FloaterBlock_Item = new FloaterBlock.FloaterBlock_Item(FloaterBlock));
		
        
        //Tiles
        GameRegistry.registerTileEntity(TileEntityTranscriber.class, TileEntityTranscriber.publicName);
		
	}
	//Updated Registry Interface
	public static void reg(Block block){

		block.setRegistryName(block.getUnlocalizedName().substring(5));
		GameRegistry.register(block);
		register_item_Block(block);
	}
	
	public static void register_item_Block(Block name){	
		
		Item dummy_itemBlockSimple = new ItemBlock(name);
		dummy_itemBlockSimple.setRegistryName(name.getRegistryName());
	    GameRegistry.register(dummy_itemBlockSimple);
	}
	
	public static void custom(Block block, Item item){

		block.setRegistryName(block.getUnlocalizedName().substring(5));
		GameRegistry.register(block);
		item.setRegistryName(block.getRegistryName());
		GameRegistry.register(item);
	}
	
	

}
