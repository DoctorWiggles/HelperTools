package helpertools;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigurationFactory extends Helpertoolscore{
	
	/** Begins Registering, loading and saving configuration**/
	public static void ProcessConfiguration(FMLPreInitializationEvent event){

		////////////////////
		/** Config Core **/
		//////////////////       	        	

		config = new Configuration(event.getSuggestedConfigurationFile());
		
		//Bunch of tabs for config hierchy
		Property ToolDurability = config.get("1 Tool Durabilities", " ", " ");
		ToolDurability.comment = "Assign custom balance of tool durabilies";

		Property Recipes = config.get("2 Recipes", " ", " ");
		Recipes.comment = "Enable or disable specific recipes";

		//Property BlocksRecipes = config.get("2 Recipes", "", "");
		//BlocksRecipes.comment = "Assign custom recipe creation results";            

		//Property BlocksOutput = config.get("3 Item Results", "", "");
		//BlocksOutput.comment = "Assign custom balance for recipe creation results";

		//Property ItemsRecipes = config.get("2 Recipes", "", "");
		//ItemsRecipes.comment = "Assign custom recipe creation results"; 

		Property ItemsOutput = config.get("3 Item Results", " ", " ");
		ItemsOutput.comment = "Assign custom balance for recipe creation results";       

		Property Extra = config.get("5 Extra Settings", " ", " ");
		//Extra.comment = "Enable or disable special 3d models for Tools, will rollback to 2d sprites";
		Extra.comment = "Enable or disable back engine features";

		//Property Interactions = config.get("Custom Interactions", "", "");
		//Interactions.comment = "Enable or disable world interactions";


		config.load();

		logger.info("Loading Configs");
		//Tools
		DurabilityExpandingRod = config.get("1 Tool Durabilities", "DurabilityExpandingRod", 1024).getInt();
		DurabilityMetamorphicStaff = config.get("1 Tool Durabilities", "DurabilityMetamorphicStaff", 1024).getInt();
		DurabilityTorchLauncher = config.get("1 Tool Durabilities", "DurabilityTorchLauncher", 1428).getInt();
		DurabilityEuclideanStaff = config.get("1 Tool Durabilities", "DurabilityEuclideanStaff", 1148).getInt();
		//Items
		OutputDynamiteBolt = config.get("3 Item Results", "OutputDynamiteBolt", 4).getInt();
		//Blocks
		OutputImitationBedrock = config.get("3 Item Results", "OutputImitationBedrock", 4).getInt();
		OutputChimneyPipe = config.get("3 Item Results", "OutputChimneyPipe", 8).getInt();
		OutputMagicalFuel = config.get("3 Item Results", "OutputMagicalFuel", 1).getInt();
		OutputDirtBomb = config.get("3 Item Results", "OutputDirtBomb", 4).getInt();
		//Boolean Enables
		/**3D models **/
		Render3DStaffModels = config.get("5 Extra Settings", "Render3DStaffModels", true).getBoolean(true);
		Render3DCrossbowModel = config.get("5 Extra Settings", "Render3DCrossbowModel", true, "Enable or disable special 3d models for Tools, will rollback to 2d sprites").getBoolean(true);
		RenderToolHuds = config.get("5 Extra Settings", "RenderToolHuds", true).getBoolean(true);
		ToolModeMesseges = config.get("5 Extra Settings", "ToolModeMesseges", true).getBoolean(true);
		ToolPowerMesseges = config.get("5 Extra Settings", "ToolPowerMesseges", false).getBoolean(true);
		
		//Expansion Staff Recipes
		RecipeDiamondsForExpansionStaff = config.get("2 Recipes", "RecipeDiamondsForExpansionStaff", true).getBoolean(true);
		RecipeEmeraldsForExpansionStaff = config.get("2 Recipes", "RecipeEmeraldsForExpansionStaff", true).getBoolean(true);
		RecipePearlsForExpansionStaff = config.get("2 Recipes", "RecipePearlsForExpansionStaff", true).getBoolean(true);
		//Metamorphic Staff Recipes
		RecipeDiamondsForMetamorphicStaff = config.get("2 Recipes", "RecipeDiamondsForMetamorphicStaff", true).getBoolean(true);
		RecipeEmeraldsForMetamorphicStaff = config.get("2 Recipes", "RecipeEmeraldsForMetamorphicStaff", true).getBoolean(true);
		RecipePearlsForMetamorphicStaff = config.get("2 Recipes", "RecipePearlsForMetamorphicStaff", true).getBoolean(true);
		//Euclidian Staff
		RecipeDiamondsForEuclideanStaff = config.get("2 Recipes", "RecipeDiamondsForEuclideanStaff", true, "Diamonds Option").getBoolean(true);
		RecipeEmeraldsForEuclideanStaff = config.get("2 Recipes", "RecipeEmeraldsForEuclideanStaff", true, "Emeralds Option").getBoolean(true);
		RecipePearlsForEuclideanStaff = config.get("2 Recipes", "RecipePearlsForEuclideanStaff", true, "Ender Pearls Option").getBoolean(true);
		//Torch Launcher
		RecipeTorchLauncher = config.get("2 Recipes", "RecipeTorchLauncher", true, "Torch Launcher").getBoolean(true);
		//Dynamite Bolt
		RecipeStringForDynamiteBolt = config.get("2 Recipes", "RecipeStringForDynamiteBolt", true).getBoolean(true);
		RecipeSlimeForDynamiteBolt = config.get("2 Recipes", "RecipeSlimeForDynamiteBolt", true).getBoolean(true);
		//Blocks
		RecipeImitationBedrock = config.get("2 Recipes", "RecipeImitationBedrock", true).getBoolean(true);
		RecipeMagicalFuel = config.get("2 Recipes", "RecipeMagicalFuel", true).getBoolean(true);
		RecipeChimenyPipes = config.get("2 Recipes", "RecipeChimenyPipes", false).getBoolean(true);
		RecipeEuclideanBlock = config.get("2 Recipes", "RecipeEuclideanBlock", true).getBoolean(true);
		RecipePodzol = config.get("2 Recipes", "RecipePodzol", true).getBoolean(true);
		//Items
		RecipeDirtBomb = config.get("2 Recipes", "RecipeDirtBomb", false).getBoolean(true);
		RecipeBottledmilk = config.get("2 Recipes", "RecipeBottledmilk", true).getBoolean(true);
		RecipeChocolatemilk = config.get("2 Recipes", "RecipeChocolatemilk", true).getBoolean(true);
		//Handlers
		HandlerBottledmilk = config.get("5 Extra Settings", "HandlerBottledmilk", true, "Enable or disable bottle interaction with cows").getBoolean(true);

		config.save(); 
		logger.info("Configurations Saved");




	}




	

}
