package helpertools.Common;

import java.util.ArrayList;
import java.util.List;

import helpertools.Main;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/** https://github.com/SleepyTrousers/EnderZoo/blob/master/src/main/java/crazypants/enderzoo/config/Config.java **/
/** Un-horrible config thanks to enderZoo code **/

public class ConfigurationFactory extends ItemRegistry{

	// I don't know what this is exactly but it
	//cleans up section code and other things well
	public static class Section {
		public final String name;
		public final String lang;
		public Section(String name, String lang) {
			this.name = name;
			this.lang = lang;
			register();
		}
		private void register() {
			sections.add(this);
		}
		public String lc() {
			return name.toLowerCase();
		}
	}
	public static final List<Section> sections;
	static {
		sections = new ArrayList<Section>();
	}
	public static Configuration config;
	
	//==========================================================================//
	
	//Creates initial configuration settings
		public static void InitialConfig(FMLPreInitializationEvent event){
			
			Main.logger.info("Generating Configuration");
			config = new Configuration(event.getSuggestedConfigurationFile());			
			config.load();		
			
			Configurator_Load(event);
			
			config.save(); 
			Main.logger.info("Configurations Saved");
			
		}		

	
	public static final Section sect_Dura = new Section("Tool Durabilites", "tool durabilites");
	public static ToolMaterial ExpRod_Matt;
	public static ToolMaterial Exchange_Matt;
	public static ToolMaterial Pattern_Matt;
	public static ToolMaterial Crossbow_Matt;
	public static int Durability_ExpandingTool;
	public static int Durability_ExchangingTool;
	public static int Durability_PatternTool;
	public static int Durability_CrossbowTool;
	
	public static final Section sect_Extra = new Section("Extra Settings", "extra settings");
	public static boolean RenderToolHuds = true;
	public static boolean ToolModeMesseges = true;
	public static boolean ToolPowerMesseges = false;
	//public static boolean Use_3D_Models = true;
	public static boolean DirtBomb_Debris = true;
	
	
	public static final Section sect_Blocks = new Section("Block Recipes", "block recipes");
	//public static boolean TechnicalSlabs = true;
	public static boolean Recipe_EuclideanBlock;
	public static boolean Recipe_Fake_Bedrock;
	public static int 	  Output_False_Bedrock;
	public static boolean Recipe_Podzol;
	
	public static final Section sect_Tool_Rec = new Section("Tool Recipes", "tool recipes");
	public static boolean Diamonds_forTools;
	public static boolean Pearls_forTools;
	public static boolean Emeralds_forTools;
	public static boolean Recipe_TorchLauncher;
	
	public static final Section sect_Item_Rec = new Section("Item Recipes", "item recipes");
	public static boolean Recipe_BottledMilk;
	public static boolean Recipe_Chocolatemilk;
	public static boolean Recipe_String_For_Dynamite;
	public static boolean Recipe_Slime_For_Dynamite;
	public static int     Output_Dynamite;
	public static boolean Recipe_DirtBombs;
	public static int     Output_DirtBombs;
	
	
	//=============================================================================//
	public static void Configurator_Load (FMLPreInitializationEvent event){
		
		
		//sect Tool Durabilities
		Durability_ExpandingTool  = config.get(sect_Dura.name, "Durability_ExpandingTool", 1024).getInt();
		Durability_ExchangingTool  = config.get(sect_Dura.name, "Durability_ExchangingTool", 1024).getInt();
		Durability_PatternTool  = config.get(sect_Dura.name, "Durability_PatternTool", 1240).getInt();
		Durability_CrossbowTool  = config.get(sect_Dura.name, "Durability_CrossbowTool", 1428).getInt();
		
		
		//sect Extra settings
		RenderToolHuds  = config.get(sect_Extra.name, "tool_Huds",true, "Enables Tools heads up displays").getBoolean();
		ToolModeMesseges = config.get(sect_Extra.name, "ToolModeMesseges", true).getBoolean();
		ToolPowerMesseges = config.get(sect_Extra.name, "ToolPowerMesseges", false).getBoolean();
		DirtBomb_Debris = config.get(sect_Extra.name, "DirtBomb_Debris", true, "Toggles most of block items dropped by dirt bombs, disable if you have performance issues").getBoolean();
		
		//sect Block Recipes
		// as of 1.8 technical blocks's itemstacks models were removed
		//TechnicalSlabs = config.get(sect_Blocks.name, "TechnicalSlabs", true).getBoolean();
		Recipe_EuclideanBlock = config.get(sect_Blocks.name, "Recipe_EuclideanBlock", true).getBoolean();
		Recipe_Fake_Bedrock = config.get(sect_Blocks.name, "Recipe_Fake_Bedrock", true).getBoolean();
		Output_False_Bedrock  = config.get(sect_Blocks.name, "Output_False_Bedrock", 4).getInt();
		Recipe_Podzol = config.get(sect_Blocks.name, "Recipe_Podzol", true).getBoolean();
		
		
		//sect Tool Recipes
		Diamonds_forTools = config.get(sect_Tool_Rec.name, "Diamonds_forTools", true).getBoolean();
		Pearls_forTools = config.get(sect_Tool_Rec.name, "Pearls_forTools", true).getBoolean();
		Emeralds_forTools = config.get(sect_Tool_Rec.name, "Emeralds_forTools", true).getBoolean();
		Recipe_TorchLauncher = config.get(sect_Tool_Rec.name, "Recipe_TorchLauncher", true).getBoolean();
		
		//sect Item Recipes
		Recipe_BottledMilk = config.get(sect_Item_Rec.name, "Recipe_BottledMilk", true).getBoolean();
		Recipe_Chocolatemilk = config.get(sect_Item_Rec.name, "Recipe_Chocolatemilk", true).getBoolean();
		Recipe_String_For_Dynamite = config.get(sect_Item_Rec.name, "Recipe_String_For_Dynamite", true).getBoolean();
		Recipe_Slime_For_Dynamite = config.get(sect_Item_Rec.name, "Recipe_Slime_For_Dynamite", true).getBoolean();
		Output_Dynamite  = config.get(sect_Item_Rec.name, "Output_Dynamite", 4).getInt();
		Recipe_DirtBombs = config.get(sect_Item_Rec.name, "Recipe_DirtBombs", true).getBoolean();
		Output_DirtBombs  = config.get(sect_Item_Rec.name, "Output_DirtBombs", 6).getInt();
		
	}
	
	
		//Process objects like materials after configuration settings
		public static void PostConfig(FMLPreInitializationEvent event){
				
			ExpRod_Matt = EnumHelper.addToolMaterial("ExpRodMaterial", 0, Durability_ExpandingTool, 0.8F, 4F, 15);
			Exchange_Matt = EnumHelper.addToolMaterial("ExpRodMaterial", 0, Durability_ExpandingTool, 0.8F, 4F, 15);
			Pattern_Matt = EnumHelper.addToolMaterial("ExpRodMaterial", 0, Durability_ExpandingTool, 0.8F, 4F, 15);
			Crossbow_Matt = EnumHelper.addToolMaterial("ExpRodMaterial", 0, Durability_ExpandingTool, 0.8F, 4F, 15);
				
				
			}
	
	

}
