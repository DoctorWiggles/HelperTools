package helpertools.Com;

import java.util.ArrayList;
import java.util.List;

import helpertools.Main;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/** https://github.com/SleepyTrousers/EnderZoo/blob/master/src/main/java/crazypants/enderzoo/config/Config.java **/
/** Un-horrible config thanks to enderZoo code **/

public class Config extends ItemRegistry{

	
	public static Configuration config;
	
	//==========================================================================//
	

		public static void configOptions(FMLPreInitializationEvent event) {
			Main.logger.info("Syncing Config");
			config = new Configuration(event.getSuggestedConfigurationFile());
			syncConfig();
		}	
	
	public static String Durability ="Durability";
	public static ToolMaterial ExpRod_Matt;
	public static ToolMaterial Exchange_Matt;
	public static ToolMaterial Pattern_Matt;
	public static ToolMaterial Crossbow_Matt;
	public static ArmorMaterial Mystic_Material;
	public static int Durability_ExpandingTool;
	public static int Durability_ExchangingTool;
	public static int Durability_PatternTool;
	public static int Durability_CrossbowTool;
	public static int Durability_MirageHusk;
	public static double Tool_Attack_Damage;
	public static double Tool_Attack_Speed;
	
		
	public static String GUI ="GUI";
	public static boolean RenderToolHuds;
	public static boolean ToolModeMesseges;
	public static boolean ToolPowerMesseges;
	public static boolean Use_Wire_Frame_Guides;
	public static boolean Use_Fake_Block_Guides;
	public static boolean Fancy_Expand;
	public static boolean Fancy_Exchange;
	public static boolean Fancy_PatternTool;
	
	public static String  Functions = "Functions";
	public static boolean ExchangeReturns;	
	public static boolean DirtBomb_Debris;
	public static int 	  BombCharm_Level;
	public static boolean BombCharm_Ward;
	public static int     Shadow_Cost;
	
	public static String Blockz ="Block Recipes";
	public static boolean Recipe_EuclideanBlock;
	public static boolean Recipe_Fake_Bedrock;
	public static int 	  Output_False_Bedrock;
	public static boolean Recipe_Podzol;
	
	
	public static String ToolRecipe ="Tool Recipes";
	public static boolean Diamonds_forTools;
	public static boolean Pearls_forTools;
	public static boolean Emeralds_forTools;
	public static boolean Recipe_TorchLauncher;
	public static boolean Recipe_MirageHusk;
	
	public static boolean Repairs_allowed;
	public static int Expander_Amount;
	public static int Exchanger_Amount;
	public static int Pattern_Amount;
	public static int Crossbow_Amount;
	
	public static String ItemRecipe ="Item Recipes";
	public static boolean Recipe_BottledMilk;
	public static boolean Recipe_Chocolatemilk;
	public static boolean Recipe_String_For_Dynamite;
	public static boolean Recipe_Slime_For_Dynamite;
	public static int     Output_Dynamite;
	public static boolean Recipe_BombCharm;
	
	//bombs
	public static String BombRecipe = "Bomb Recipes";
	public static boolean Recipe_DirtBombs;
	public static boolean Recipe_SandBombs;
	public static boolean Recipe_GravelBombs;
	public static boolean Recipe_PlantBombs;
	public static boolean Recipe_SnowBombs;
	public static boolean Recipe_DesertBombs;	
	public static boolean Recipe_MushroomBombs;
	public static boolean Recipe_VoidBombs;
	public static boolean Recipe_StickyBombs;
	
	public static int Output_DirtBombs;
	public static int Output_SandBombs;
	public static int Output_GravelBombs;
	public static int Output_PlantBombs;
	public static int Output_SnowBombs;
	public static int Output_DesertBombs;
	public static int Output_MushroomBombs;
	public static int Output_VoidBombs;
	public static int Output_StickyBombs;
		
	
	public static String Generation ="Generation";
	public static boolean Extra_Dungeon_Loot;
	public static boolean No_Fun_Allowed;
	
	
	public static String dura = TextFormatting.GREEN + "Durability " +TextFormatting.GRAY;
	public static String rec = TextFormatting.YELLOW + "Recipe " +TextFormatting.GRAY;
	public static String output = TextFormatting.BLUE + "Output " +TextFormatting.GRAY;
	public static String bomb_rec = TextFormatting.BLUE + "Output " +TextFormatting.GRAY;
	public static String repairs = TextFormatting.DARK_PURPLE + "Repair Amount: " +TextFormatting.GRAY;
	
	
	
	
	
	//=============================================================================//
	public static void syncConfig (){
		
		//sect Tool Durabilities
		Durability_ExpandingTool  = config.get(Durability, dura+"ExpandingTool", 1024).getInt();
		Durability_ExchangingTool  = config.get(Durability, dura+"ExchangingTool", 1024).getInt();
		Durability_PatternTool  = config.get(Durability, dura+"PatternTool", 1240).getInt();
		Durability_CrossbowTool  = config.get(Durability, dura+"CrossbowTool", 1428).getInt();
		Durability_MirageHusk = config.get(Durability, dura+"Mirage Husk", 12,"Scales by x10").getInt();
		Tool_Attack_Damage  = config.get(Durability, "Tool Attack Damage", 4, "Play around with attack damage and speed, from slow yet heavy hitter or quick but week attacks").getDouble();
		Tool_Attack_Speed  = config.get(Durability, "Tool Attack Speed", -3.2F, "-4F = 0 Attacks per second, -3F = 1, 0F = 4 Attacks per second").getDouble();
		
		
		//sect Extra settings
		RenderToolHuds  = config.get(GUI, "Tool Huds",true, "Enables Tools heads up displays").getBoolean();
		ToolModeMesseges = config.get(GUI, "Tool Mode Messeges", true,"Most Tool text messeges").getBoolean();
		ToolPowerMesseges = config.get(GUI, "Tool Power Messeges", false).getBoolean();
		Use_Wire_Frame_Guides = config.get(GUI, "Use Wire Frames for Hightlighting", true, "Disable for light performance gains").getBoolean();
		Use_Fake_Block_Guides = config.get(GUI, "Use Fake Blocks for Highlights", true, "This is a huge performance hog, disable it if you have issues displaying it").getBoolean();
		Fancy_Expand = config.get(GUI, "Fancy Expander Model", true, "Disable to use old 2d sprites").getBoolean();
		Fancy_Exchange = config.get(GUI, "Fancy Exchange Model", true, "Disable to use old 2d sprites").getBoolean();
		Fancy_PatternTool = config.get(GUI, "Fancy Euclidean Model", true, "Disable to use old 2d sprites").getBoolean();
		
		
		//sect Block Recipes
		Recipe_EuclideanBlock = config.get(Blockz, rec+"EuclideanBlock", true).getBoolean();
		Recipe_Fake_Bedrock = config.get(Blockz, rec+"Fake Bedrock", true).getBoolean();
		Output_False_Bedrock  = config.get(Blockz, output+"False Bedrock", 4).getInt();
		Recipe_Podzol = config.get(Blockz, rec+"Podzol", true).getBoolean();
		
		
		//sect Tool Recipes
		Diamonds_forTools = config.get(ToolRecipe, rec+"Diamonds for Tools", true).getBoolean();
		Pearls_forTools = config.get(ToolRecipe, rec+"Pearls for Tools", true).getBoolean();
		Emeralds_forTools = config.get(ToolRecipe, rec+"Emeralds for Tools", true).getBoolean();
		Recipe_TorchLauncher = config.get(ToolRecipe, rec+"Torch Launcher", true).getBoolean();
		Recipe_MirageHusk = config.get(ToolRecipe, "Craftable Mirage Husk", true).getBoolean();
		Repairs_allowed = config.get(ToolRecipe, "Allow Easy Repairs", true,"Tools can be repaired without anvils").getBoolean();
		Expander_Amount  = config.get(ToolRecipe, repairs+"Expander", 300, "How much an iron ingot is worth for repairs").getInt();
		Exchanger_Amount  = config.get(ToolRecipe, repairs+"Exchanger", 300, "How much a gold into is worth for repairs").getInt();
		Pattern_Amount  = config.get(ToolRecipe, repairs+"Pattern", 300, "How much a piece of lapis is worth for repairs").getInt();
		Crossbow_Amount  = config.get(ToolRecipe, repairs+"Crossbow", 300, "How much a piece of string is worth for repairs").getInt();
		
		//sect Item Recipes		
		Recipe_BottledMilk = config.get(ItemRecipe, rec+"BottledMilk", true).getBoolean();
		Recipe_Chocolatemilk = config.get(ItemRecipe, rec+"Chocolatemilk", true).getBoolean();
		Recipe_String_For_Dynamite = config.get(ItemRecipe, rec+"String For Dynamite", true).getBoolean();
		Recipe_Slime_For_Dynamite = config.get(ItemRecipe, rec+"Slime For Dynamite", true).getBoolean();
		Output_Dynamite  = config.get(ItemRecipe, output+"Dynamite", 4).getInt();
		Recipe_BombCharm= config.get(ItemRecipe, rec+"Bomb Charms", true).getBoolean();
		
		//bomb recipes
		Recipe_DirtBombs = config.get(BombRecipe, rec+"Dirt Bombs", true).getBoolean();
		Recipe_SandBombs = config.get(BombRecipe, rec+"Sand Bombs", true).getBoolean();
		Recipe_GravelBombs = config.get(BombRecipe, rec+"Gravel Bombs", true).getBoolean();
		Recipe_PlantBombs = config.get(BombRecipe, rec+"Plant Bombs", true).getBoolean();
		Recipe_DesertBombs = config.get(BombRecipe, rec+"Desert Bombs", true).getBoolean();
		Recipe_SnowBombs = config.get(BombRecipe, rec+"Snow Bombs", true).getBoolean();
		Recipe_MushroomBombs = config.get(BombRecipe, rec+"Mushroom Bombs", true).getBoolean();
		Recipe_VoidBombs = config.get(BombRecipe, rec+"Void Bombs", false).getBoolean();
		Recipe_StickyBombs = config.get(BombRecipe, rec+"Sticky Bombs", true).getBoolean();
		
		Output_DirtBombs  = config.get(BombRecipe, output+"Dirt Bombs", 6).getInt();
		Output_SandBombs  = config.get(BombRecipe, output+"Sand Bombs", 6).getInt();
		Output_GravelBombs  = config.get(BombRecipe, output+"Gravel Bombs", 6).getInt();
		Output_PlantBombs  = config.get(BombRecipe, output+"Plant Bombs", 4).getInt();
		Output_SnowBombs  = config.get(BombRecipe, output+"Snow Bombs", 8).getInt();
		Output_DesertBombs  = config.get(BombRecipe, output+"Desert Bombs", 2).getInt();
		Output_MushroomBombs  = config.get(BombRecipe, output+"Mushroom Bombs", 2).getInt();
		Output_VoidBombs  = config.get(BombRecipe, output+"Void Bombs", 4).getInt();
		Output_StickyBombs  = config.get(BombRecipe, output+"Sticky Bombs", 6).getInt();
		
		
		//Tool functions
		ExchangeReturns = config.get(Functions, "Exchange Tool Returns", true, "Allows the Exchange tool to drop swapped blocks, disable for creative usage etc").getBoolean();
		//DirtBomb_Debris = config.get(Functions, "Bomb Debris", true, "Toggles most of block items dropped by bombs, disable if you have performance issues").getBoolean();
		BombCharm_Ward = config.get(Functions, "Bomb Charm Ward", true, "Allows the Bomb Charm to Prevent Explosion Deaths").getBoolean();
		BombCharm_Level  = config.get(Functions, "Bomb Charm Max Level", 5, "Set the level cap for non-creative charms").getInt();
		Shadow_Cost  = config.get(Functions, "MirageHusk Shadow Cost", 1, "How much summoning and using a shadow should cost durability wise").getInt();
		
		//sect Generation
		Extra_Dungeon_Loot = config.get(Generation, "Extra Dungeon Loot", true,"Adds some tools and items to most generating chests").getBoolean();
		No_Fun_Allowed = config.get(Generation, "No Fun Allowed", false,"Enable if you don't Like Haunted Items Flying away").getBoolean();
		
		PostConfig();
		if(config.hasChanged())
			config.save();
		
	}
	
	
		//Process objects like materials after configuration settings
		public static void PostConfig(){
				
			ExpRod_Matt = EnumHelper.addToolMaterial("ExpRodMaterial", 0, Durability_ExpandingTool, 0.8F, 4F, 15);
			Exchange_Matt = EnumHelper.addToolMaterial("ExpRodMaterial", 0, Durability_ExpandingTool, 0.8F, 4F, 15);
			Pattern_Matt = EnumHelper.addToolMaterial("ExpRodMaterial", 0, Durability_ExpandingTool, 0.8F, 4F, 15);
			Crossbow_Matt = EnumHelper.addToolMaterial("ExpRodMaterial", 0, Durability_ExpandingTool, 0.8F, 4F, 15);
			Mystic_Material = EnumHelper.addArmorMaterial("Mystic", Main.PATH+"Mystic",
					Durability_MirageHusk, new int[]{2, 2, 2, 2},35, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);	
				
			}
	
	

}
