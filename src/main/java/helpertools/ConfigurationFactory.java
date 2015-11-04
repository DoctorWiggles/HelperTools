package helpertools;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.util.EnumHelper;

public class ConfigurationFactory extends Helpertoolscore{
	
	////////////////////////////
	/** Configuration       **/
	//////////////////////////
	//Tool durability
	public static int  DurabilityExpandingRod;
	public static int  DurabilityMetamorphicStaff;
	public static int  DurabilityTorchLauncher;
	public static int  DurabilityEuclideanStaff;
	//Items
	public static int OutputDynamiteBolt;
	public static int OutputDirtBomb;
	//Blocks
	public static int OutputImitationBedrock;
	public static int OutputChimneyPipe;
	public static int OutputMagicalFuel;		
	//Booleans enable

	/** 3D Models **/
	public static boolean Render3DStaffModels;
	public static boolean Render3DCrossbowModel;
	public static boolean RenderToolHuds;
	public static boolean ToolModeMesseges;
	public static boolean ToolPowerMesseges;
	//ExpStaff
	public static boolean RecipeEmeraldsForExpansionStaff;
	public static boolean RecipeDiamondsForExpansionStaff;
	public static boolean RecipePearlsForExpansionStaff;
	//MetaStaff
	public static boolean RecipeEmeraldsForMetamorphicStaff;
	public static boolean RecipeDiamondsForMetamorphicStaff;
	public static boolean RecipePearlsForMetamorphicStaff;
	//EuclideanStaff
	public static boolean RecipeEmeraldsForEuclideanStaff;
	public static boolean RecipeDiamondsForEuclideanStaff;
	public static boolean RecipePearlsForEuclideanStaff;
	//Torch Launcher
	public static boolean RecipeTorchLauncher;
	public static boolean RecipeStringForDynamiteBolt;		
	public static boolean RecipeSlimeForDynamiteBolt;
	//Blocks
	public static boolean RecipeImitationBedrock;
	public static boolean RecipeMagicalFuel;
	public static boolean RecipeChimenyPipes;
	public static boolean RecipeEuclideanBlock;
	//Vanilla Blocks
	public static boolean RecipePodzol;
	public static boolean RecipeDoubleSlab;
	public static boolean RecipeFullSlab;
	public static boolean RecipeFullSandSlab;

	//Items
	public static boolean Recipe_Bombs;
	public static boolean RecipeBottledmilk;
	public static boolean RecipeChocolatemilk;
	
	//Handler enables
	public static boolean HandlerBottledmilk;
	public static boolean Bomb_Debris;
	
	 //Materials
	public static ToolMaterial helpMaterial;
	public static ToolMaterial ExpRodMaterial;
	public static ToolMaterial MetaStaffMaterial;
	public static ToolMaterial EUStaffMaterial;
	public static ToolMaterial TorchMaterial;
	
	
	//===============================================================================//
	
	/** Begins Registering, loading and saving configuration**/
	public static void ProcessConfiguration(FMLPreInitializationEvent event){

		////////////////////
		/** Config Core **/
		//////////////////       	        	

		config = new Configuration(event.getSuggestedConfigurationFile());
		
		//Bunch of tabs for config hierchy
		String conf1 = "1 Tool Durabilities";
		Property ToolDurability = config.get(conf1, " ", " ");
		ToolDurability.comment = "Assign custom balance of tool durabilies";

		String conf2 = "2 Tool Recipes";
		Property ToolRecipes = config.get(conf2, " ", " ");
		ToolRecipes.comment = "Enable or disable specific tool recipes";
		
		String conf3 = "3 Item Recipes";
		Property Recipes = config.get(conf3, " ", " ");
		Recipes.comment = "Enable or disable Item recipes";
		
		String conf4 = "4 Block Recipes";
		Property BlockRecipes = config.get(conf4, " ", " ");
		BlockRecipes.comment = "Enable or disable Block recipes";

		String conf5 = "5 Recipe Results";
		Property ItemsOutput = config.get(conf5, " ", " ");
		ItemsOutput.comment = "Assign custom balance for recipe creation results";       

		String conf6 = "6 Extra Settings";
		Property Extra = config.get("6 Extra Settings", " ", " ");
		//Extra.comment = "Enable or disable special 3d models for Tools, will rollback to 2d sprites";
		Extra.comment = "Enable or disable back engine features";

		//Property Interactions = config.get("Custom Interactions", "", "");
		//Interactions.comment = "Enable or disable world interactions";


		config.load();

		logger.info("Loading Configs");
		//Tools
		DurabilityExpandingRod = config.get(conf1, "DurabilityExpandingRod", 1024).getInt();
		DurabilityMetamorphicStaff = config.get(conf1, "DurabilityMetamorphicStaff", 1024).getInt();
		DurabilityTorchLauncher = config.get(conf1, "DurabilityTorchLauncher", 1428).getInt();
		DurabilityEuclideanStaff = config.get(conf1, "DurabilityEuclideanStaff", 1148).getInt();
		//Items
		OutputDynamiteBolt = config.get(conf5, "OutputDynamiteBolt", 4).getInt();
		//Blocks
		OutputImitationBedrock = config.get(conf5, "OutputImitationBedrock", 4).getInt();
		OutputChimneyPipe = config.get(conf5, "OutputChimneyPipe", 8).getInt();
		OutputMagicalFuel = config.get(conf5, "OutputMagicalFuel", 1).getInt();
		OutputDirtBomb = config.get(conf5, "OutputDirtBomb", 4).getInt();
		//Boolean Enables
		/**3D models **/
		Render3DStaffModels = config.get(conf6, "Render3DStaffModels", true).getBoolean(true);
		Render3DCrossbowModel = config.get(conf6, "Render3DCrossbowModel", true, "Enable or disable special 3d models for Tools, will rollback to 2d sprites").getBoolean(true);
		RenderToolHuds = config.get(conf6, "RenderToolHuds", true, "Enables Hud and chat messeges for tools").getBoolean(true);
		ToolModeMesseges = config.get(conf6, "ToolModeMesseges", true).getBoolean(true);
		ToolPowerMesseges = config.get(conf6, "ToolPowerMesseges", false).getBoolean(true);
		Bomb_Debris = config.get(conf5, "DirtBomb_Debris", true, "Toggles most of block items dropped by bombs, disable if you have performance issues").getBoolean(true);
		
		//Expansion Staff Recipes
		RecipeDiamondsForExpansionStaff = config.get(conf2, "RecipeDiamondsForExpansionStaff", true).getBoolean(true);
		RecipeEmeraldsForExpansionStaff = config.get(conf2, "RecipeEmeraldsForExpansionStaff", true).getBoolean(true);
		RecipePearlsForExpansionStaff = config.get(conf2, "RecipePearlsForExpansionStaff", true).getBoolean(true);
		//Metamorphic Staff Recipes
		RecipeDiamondsForMetamorphicStaff = config.get(conf2, "RecipeDiamondsForMetamorphicStaff", true).getBoolean(true);
		RecipeEmeraldsForMetamorphicStaff = config.get(conf2, "RecipeEmeraldsForMetamorphicStaff", true).getBoolean(true);
		RecipePearlsForMetamorphicStaff = config.get(conf2, "RecipePearlsForMetamorphicStaff", true).getBoolean(true);
		//Euclidian Staff
		RecipeDiamondsForEuclideanStaff = config.get(conf2, "RecipeDiamondsForEuclideanStaff", true, "Diamonds Option").getBoolean(true);
		RecipeEmeraldsForEuclideanStaff = config.get(conf2, "RecipeEmeraldsForEuclideanStaff", true, "Emeralds Option").getBoolean(true);
		RecipePearlsForEuclideanStaff = config.get(conf2, "RecipePearlsForEuclideanStaff", true, "Ender Pearls Option").getBoolean(true);
		//Torch Launcher
		RecipeTorchLauncher = config.get(conf2, "RecipeTorchLauncher", true, "Torch Launcher").getBoolean(true);
		//Dynamite Bolt
		RecipeStringForDynamiteBolt = config.get(conf3, "RecipeStringForDynamiteBolt", true).getBoolean(true);
		RecipeSlimeForDynamiteBolt = config.get(conf3, "RecipeSlimeForDynamiteBolt", true).getBoolean(true);
		//Blocks
		RecipeImitationBedrock = config.get(conf4, "RecipeImitationBedrock", true).getBoolean(true);
		RecipeMagicalFuel = config.get(conf4, "RecipeMagicalFuel", true).getBoolean(true);
		RecipeChimenyPipes = config.get(conf4, "RecipeChimenyPipes", false).getBoolean(true);
		RecipeEuclideanBlock = config.get(conf4, "RecipeEuclideanBlock", true).getBoolean(true);
		RecipePodzol = config.get(conf4, "RecipePodzol", true).getBoolean(true);
		RecipeDoubleSlab = config.get(conf4, "RecipeDoubleSlab", true).getBoolean(true);
		RecipeFullSlab = config.get(conf4, "RecipeFullSlab", true).getBoolean(true);
		RecipeFullSandSlab = config.get(conf4, "RecipeFullSandSlab", true).getBoolean(true);
		
		
		//Items
		Recipe_Bombs = config.get(conf3, "Recipe_Bombs", true).getBoolean(true);
		RecipeBottledmilk = config.get(conf3, "RecipeBottledmilk", true).getBoolean(true);
		RecipeChocolatemilk = config.get(conf3, "RecipeChocolatemilk", true).getBoolean(true);
		//Handlers
		HandlerBottledmilk = config.get(conf6, "HandlerBottledmilk", true, "Enable or disable bottle interaction with cows").getBoolean(true);

		config.save(); 
		logger.info("Configurations Saved");
		
		
		//////////////////////////////
		/**Material Configurations**/
		////////////////////////////

		helpMaterial = EnumHelper.addToolMaterial("helpMaterial", 0, 1024, 0.8F, 4F, 15); 

		ExpRodMaterial = EnumHelper.addToolMaterial("ExpRodMaterial", 0, DurabilityExpandingRod, 0.8F, 4F, 15);
		MetaStaffMaterial = EnumHelper.addToolMaterial("MetaStaffMaterial", 0, DurabilityMetamorphicStaff, 0.8F, 4F, 15);
		EUStaffMaterial = EnumHelper.addToolMaterial("EUStaffMaterial", 0, DurabilityEuclideanStaff, 0.8F, 4F, 15);
		TorchMaterial = EnumHelper.addToolMaterial("TorchMaterial", 0, DurabilityTorchLauncher, 0.8F, 4F, 15);
		//name, harvest level, max uses, efficiency, damage, enchantability




	}




	

}
