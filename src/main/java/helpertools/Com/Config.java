package helpertools.Com;

import java.util.ArrayList;
import java.util.List;

import helpertools.Main;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
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

	
	//public static final Section sect_Dura = new Section("Tool Durabilites", "tool durabilites");
	public static String Durability ="Durability";
	public static ToolMaterial ExpRod_Matt;
	public static ToolMaterial Exchange_Matt;
	public static ToolMaterial Pattern_Matt;
	public static ToolMaterial Crossbow_Matt;
	public static int Durability_ExpandingTool;
	public static int Durability_ExchangingTool;
	public static int Durability_PatternTool;
	public static int Durability_CrossbowTool;
	public static double Tool_Attack_Damage;
	public static double Tool_Attack_Speed;
	
	
	//public static final Section sect_Extra = new Section("Extra Settings", "extra settings");
	public static String GUI ="GUI";
	public static boolean RenderToolHuds = true;
	public static boolean ToolModeMesseges = true;
	public static boolean ToolPowerMesseges = false;
	//public static boolean Use_3D_Models = true;
	public static boolean DirtBomb_Debris = true;
	public static boolean Use_Wire_Frame_Guides = true;
	public static boolean Use_Fake_Block_Guides = true;
	public static boolean Fancy_Expand = true;
	public static boolean Fancy_Exchange = true;
	public static boolean Fancy_PatternTool = true;
	
	
	//public static final Section sect_Blocks = new Section("Block Recipes", "block recipes");
	public static String Blockz ="Block Recipes";
	//public static boolean TechnicalSlabs = true;
	public static boolean Recipe_EuclideanBlock;
	public static boolean Recipe_Fake_Bedrock;
	public static int 	  Output_False_Bedrock;
	public static boolean Recipe_Podzol;
	
	//public static final Section sect_Tool_Rec = new Section("Tool Recipes", "tool recipes");
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
	
	
	//public static final Section sect_Item_Rec = new Section("Item Recipes", "item recipes");
	public static String ItemRecipe ="Item Recipes";
	public static boolean Recipe_BottledMilk;
	public static boolean Recipe_Chocolatemilk;
	public static boolean Recipe_String_For_Dynamite;
	public static boolean Recipe_Slime_For_Dynamite;
	public static int     Output_Dynamite;
	public static boolean Recipe_DirtBombs;
	public static int     Output_DirtBombs;
	
	
	public static String Generation ="Generation";
	public static boolean Extra_Dungeon_Loot;
	public static boolean No_Fun_Allowed;
	
	
	
	public static String dura = TextFormatting.GREEN + "Durability " +TextFormatting.GRAY;
	public static String rec = TextFormatting.YELLOW + "Recipe " +TextFormatting.GRAY;
	public static String output = TextFormatting.BLUE + "Output " +TextFormatting.GRAY;
	
	//=============================================================================//
	public static void syncConfig (){
		
		//sect Tool Durabilities
		Durability_ExpandingTool  = config.get(Durability, dura+"ExpandingTool", 1024).getInt();
		Durability_ExchangingTool  = config.get(Durability, dura+"ExchangingTool", 1024).getInt();
		Durability_PatternTool  = config.get(Durability, dura+"PatternTool", 1240).getInt();
		Durability_CrossbowTool  = config.get(Durability, dura+"CrossbowTool", 1428).getInt();
		Tool_Attack_Damage  = config.get(Durability, "Tool Attack Damage", 4, "Play around with attack damage and speed, from slow yet heavy hitter or quick but week attacks").getDouble();
		Tool_Attack_Speed  = config.get(Durability, "Tool Attack Speed", -3.2F, "-4F = 0 Attacks per second, -3F = 1, 0F = 4 Attacks per second").getDouble();
		
		
		//sect Extra settings
		RenderToolHuds  = config.get(GUI, "Tool Huds",true, "Enables Tools heads up displays").getBoolean();
		ToolModeMesseges = config.get(GUI, "Tool Mode Messeges", true).getBoolean();
		ToolPowerMesseges = config.get(GUI, "Tool Power Messeges", false).getBoolean();
		Use_Wire_Frame_Guides = config.get(GUI, "Use Wire Frames for Hightlighting", true, "Disable for light performance gains").getBoolean();
		Use_Fake_Block_Guides = config.get(GUI, "Use Fake Blocks for Highlights", true, "This is a huge performance hog, disable it if you have issues displaying it").getBoolean();
		Fancy_Expand = config.get(GUI, "Fancy Expander Model", true, "Disable to use old 2d sprites").getBoolean();
		Fancy_Exchange = config.get(GUI, "Fancy Exchange Model", true, "Disable to use old 2d sprites").getBoolean();
		Fancy_PatternTool = config.get(GUI, "Fancy Euclidean Model", true, "Disable to use old 2d sprites").getBoolean();
		
		
		//sect Block Recipes
		// as of 1.8 technical blocks's itemstacks models were removed
		//TechnicalSlabs = config.get(Blocks, "TechnicalSlabs", true).getBoolean();
		Recipe_EuclideanBlock = config.get(Blockz, rec+"EuclideanBlock", true).getBoolean();
		Recipe_Fake_Bedrock = config.get(Blockz, rec+"Fake Bedrock", true).getBoolean();
		Output_False_Bedrock  = config.get(Blockz, output+"False Bedrock", 4).getInt();
		Recipe_Podzol = config.get(Blockz, rec+"Podzol", true).getBoolean();
		
		
		//sect Tool Recipes
		Diamonds_forTools = config.get(ToolRecipe, "Diamonds for Tools", true).getBoolean();
		Pearls_forTools = config.get(ToolRecipe, "Pearls for Tools", true).getBoolean();
		Emeralds_forTools = config.get(ToolRecipe, "Emeralds for Tools", true).getBoolean();
		Recipe_TorchLauncher = config.get(ToolRecipe, rec+"TorchLauncher", true).getBoolean();
		Recipe_MirageHusk = config.get(ToolRecipe, "Craftable Mirage Husk", true).getBoolean();
		Repairs_allowed = config.get(ToolRecipe, "Allow Easy Repairs", true,"Tools can be repaired without anvils").getBoolean();
		Expander_Amount  = config.get(ToolRecipe, "Expander Repair Amount", 300, "How much an iron ingot is worth for repairs").getInt();
		Exchanger_Amount  = config.get(ToolRecipe, "Exchanger Repair Amount", 300, "How much a gold into is worth for repairs").getInt();
		Pattern_Amount  = config.get(ToolRecipe, "Pattern Repair Amount", 300, "How much a piece of lapis is worth for repairs").getInt();
		Crossbow_Amount  = config.get(ToolRecipe, "Crossbow Repair Amount", 300, "How much a piece of string is worth for repairs").getInt();
		
		//sect Item Recipes
		DirtBomb_Debris = config.get(ItemRecipe, "DirtBomb Debris", true, "Toggles most of block items dropped by dirt bombs, disable if you have performance issues").getBoolean();
		Recipe_BottledMilk = config.get(ItemRecipe, rec+"BottledMilk", true).getBoolean();
		Recipe_Chocolatemilk = config.get(ItemRecipe, rec+"Chocolatemilk", true).getBoolean();
		Recipe_String_For_Dynamite = config.get(ItemRecipe, rec+"String For Dynamite", true).getBoolean();
		Recipe_Slime_For_Dynamite = config.get(ItemRecipe, rec+"Slime For Dynamite", true).getBoolean();
		Output_Dynamite  = config.get(ItemRecipe, output+"Dynamite", 4).getInt();
		Recipe_DirtBombs = config.get(ItemRecipe, rec+"DirtBombs", true).getBoolean();
		Output_DirtBombs  = config.get(ItemRecipe, output+"DirtBombs", 6).getInt();
		
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
				
				
			}
	
	

}
