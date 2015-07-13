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

	//public static final Section sectionEnderminy = new Section("Enderminy", "enderminy");
	//public static boolean enderminyEnabled = true;
	
	public static final Section sect_Dura = new Section("Tool Durabilites", "tool durabilites");
	public static ToolMaterial ExpRodMatt;
	public static int Durability_ExpandingTool;
	
	public static final Section sect_Extra = new Section("Extra Settings", "extra settings");
	public static boolean RenderToolHuds = true;
	public static boolean ToolModeMesseges = true;
	public static boolean ToolPowerMesseges = false;
	//public static boolean Use_3D_Models = true;
	
	public static final Section sect_Blocks = new Section("Block Recipes", "block recipes");
	//public static boolean TechnicalSlabs = true;
	
	public static final Section sect_Tool_Rec = new Section("Tool Recipes", "tool recipes");
	public static boolean Diamonds_forTools;
	public static boolean Pearls_forTools;
	public static boolean Emeralds_forTools;
	
	public static final Section sect_Item_Rec = new Section("Item Recipes", "item recipes");
	public static boolean Recipe_BottledMilk;
	
	
	public static void Configurator_Load (FMLPreInitializationEvent event){
		//enderminyEnabled  = config.getBoolean("enderminyEnabled", sectionEnderminy.name, enderminyEnabled, "Wether Enderminies are enabled");
		
		//sect Tool Durabilities
		Durability_ExpandingTool  = config.get(sect_Dura.name, "Durability_ExpandingTool", 1024).getInt();
		
		
		//sect Extra settings
		RenderToolHuds  = config.get(sect_Extra.name, "tool_Huds",true, "Enables Tools heads up displays").getBoolean();
		ToolModeMesseges = config.get(sect_Extra.name, "ToolModeMesseges", true).getBoolean();
		ToolPowerMesseges = config.get(sect_Extra.name, "ToolPowerMesseges", false).getBoolean();
		//1.8's model system is dumb so you can't multiple available models per unlocalized name(or something like that)		
		//Use_3D_Models = config.get(sect_Extra.name, "Use_3D_Models", true, "Use special 3D models for tools or 2D sprites if disabled").getBoolean();
		
		//sect Block Recipes
		// as of 1.8 technical blocks were removed
		//TechnicalSlabs = config.get(sect_Blocks.name, "TechnicalSlabs", true).getBoolean();
		
		//sect Tool Recipes
		Diamonds_forTools = config.get(sect_Tool_Rec.name, "Diamonds_forTools", true).getBoolean();
		Pearls_forTools = config.get(sect_Tool_Rec.name, "Pearls_forTools", true).getBoolean();
		Emeralds_forTools = config.get(sect_Tool_Rec.name, "Emeralds_forTools", true).getBoolean();
		
		//sect Item Recipes
		Recipe_BottledMilk = config.get(sect_Item_Rec.name, "Recipe_BottledMilk", true).getBoolean();
	}
	//Process objects like materials after configuration settings
			public static void PostConfig(FMLPreInitializationEvent event){
				
			ExpRodMatt = EnumHelper.addToolMaterial("ExpRodMaterial", 0, Durability_ExpandingTool, 0.8F, 4F, 15);
				
				
			}
	
	

}
