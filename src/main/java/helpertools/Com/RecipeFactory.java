package helpertools.Com;

import helpertools.Main;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeFactory extends Config{

	/** Boolean for Configuration, The ItemStack Output(Item,Number,Metadata), and the Recipes **/
	public static void ShapedRecipe(Boolean Bool,ItemStack output, Object... recipe ){		
		if (Bool == true){		
		ShapedRecipe(output, recipe);
		}
	}	
	public static void ShapedRecipe(ItemStack output, Object... recipe){		
		GameRegistry.addRecipe(new ShapedOreRecipe(output,true, recipe));
	}
	
	/** Auto Constructor for shapeless recipes **/
	public static void ShapelessRecipe(Boolean Bool,ItemStack output, Object... recipe){
		if (Bool == true){		
		ShapelessRecipe(output, recipe);
		}
	}	
	public static void ShapelessRecipe(ItemStack output, Object... recipe){	
		GameRegistry.addRecipe(new ShapelessOreRecipe(output,recipe));
		
	}
	
	public static void RegisterRecipes(){
		Main.logger.info("Loading Recipes");
		
		OreDictionary.registerOre("string", Items.STRING);
		
		//Tools
		//expand
		
		ShapedRecipe(Diamonds_forTools, new ItemStack(expandertool,1,0),new Object[]{
			"XZX",
			" Y ",
			" Y ", 'X',"ingotIron", 'Z', "gemDiamond",'Y',"stickWood"});		
		ShapedRecipe(Pearls_forTools, new ItemStack(expandertool,1,0),new Object[]{
			"XZX",
			" Y ",
			" Y ", 'X',"ingotIron", 'Z', Items.ENDER_PEARL,'Y',"stickWood"});		
		ShapedRecipe(Emeralds_forTools, new ItemStack(expandertool,1,0),new Object[]{
			"XZX",
			" Y ",
			" Y ", 'X',"ingotIron", 'Z', "gemEmerald",'Y',"stickWood"});
		
		//Exchange
		ShapedRecipe(Diamonds_forTools, new ItemStack(exchange_tool,1,0),new Object[]{
			"XZX",
			" Y ",
			" Y ", 'X',"ingotGold", 'Z', "gemDiamond",'Y',"stickWood"});		
		ShapedRecipe(Pearls_forTools, new ItemStack(exchange_tool,1,0),new Object[]{
			"XZX",
			" Y ",
			" Y ", 'X',"ingotGold", 'Z', Items.ENDER_PEARL,'Y',"stickWood"});		
		ShapedRecipe(Emeralds_forTools, new ItemStack(exchange_tool,1,0),new Object[]{
			"XZX",
			" Y ",
			" Y ", 'X',"ingotGold", 'Z', "gemEmerald",'Y',"stickWood"});
		
		//pattern tool		
		ShapedRecipe(Diamonds_forTools, new ItemStack(pattern_tool,1,0),new Object[]{
			"SZS",
			"LYL",
			" Y ", 'S',"sandstone", 'L',"gemLapis",'Z',"gemDiamond",'Y',Items.REEDS});
		ShapedRecipe(Pearls_forTools, new ItemStack(pattern_tool,1,0),new Object[]{
			"SZS",
			"LYL",
			" Y ", 'S',"sandstone", 'L',"gemLapis",'Z',Items.ENDER_PEARL,'Y',Items.REEDS});
		ShapedRecipe(Emeralds_forTools, new ItemStack(pattern_tool,1,0),new Object[]{
			"SZS",
			"LYL",
			" Y ", 'S',"sandstone", 'L',"gemLapis",'Z',"gemEmerald",'Y',Items.REEDS});
		
		//Torch launcher		
	    ShapedRecipe(Recipe_TorchLauncher, new ItemStack(crossbow_tool,1,0),new Object[]{
	    	"   ",
	    	"FBR",
	    	"   ", 'B', Items.BOW,'F', Items.FLINT_AND_STEEL,'R', "plankWood"});	    
	    ShapedRecipe(Recipe_TorchLauncher, new ItemStack(crossbow_tool,1,0),new Object[]{
	    	"FWS",
    		"W S",
    		"RWS", 'S', "string", 'F', Items.FLINT_AND_STEEL,'R', "plankWood",'W', "stickWood"});	    
	    ShapelessRecipe(Recipe_TorchLauncher,new ItemStack(crossbow_tool, 1 , 0), new Object[]{
	    	Items.BOW, Items.FLINT_AND_STEEL, "plankWood"});
	    
	    ShapedRecipe(Recipe_DirtBombs, new ItemStack(dirtbomb, Output_DirtBombs,0),new Object[]{
	    	"SSS",
    		"SWS",
    		"SSS", 'S', Blocks.DIRT, 'W', Items.GUNPOWDER});
	    	
		
		//Blocks		
		ShapedRecipe(Emeralds_forTools, new ItemStack(transcriberBlock,1,0),new Object[]{
			" L ",
    		"LsL",
    		" L ", 's', "sandstone", 'L', "gemLapis"});
		
		ShapelessRecipe(Recipe_Fake_Bedrock,new ItemStack(falseBedrock, Output_False_Bedrock , 0), new Object[]{
			"cobblestone", "cobblestone", "cobblestone", Blocks.COAL_BLOCK });
		
	    	
		
		//Items
		ItemStack BucketOut = new ItemStack(Items.MILK_BUCKET.setContainerItem(Items.BUCKET));		
		ShapelessRecipe(Recipe_BottledMilk,new ItemStack(milkbottle, 1 , 0), new Object[]{
			BucketOut, Items.GLASS_BOTTLE});
		
		ItemStack Bottledestroy = new ItemStack(milkbottle.setContainerItem(null));
		ShapelessRecipe(Recipe_Chocolatemilk,new ItemStack(chocolatemilk, 1 , 0), new Object[]{
    		Bottledestroy, new ItemStack(Items.DYE, 1, 3)});
		
		ShapelessRecipe(Recipe_String_For_Dynamite, new ItemStack(ItemRegistry.dynamitebolt, Output_Dynamite, 0), new Object[]{
    		Blocks.TNT, Items.ARROW, Items.ARROW, Items.ARROW,Items.ARROW, "string"});
		
		ShapelessRecipe(Recipe_Slime_For_Dynamite, new ItemStack(ItemRegistry.dynamitebolt, Output_Dynamite, 0), new Object[]{
    		Blocks.TNT, Items.ARROW, Items.ARROW, Items.ARROW,Items.ARROW, "slimeball"});
		
		ShapelessRecipe(Recipe_Podzol, new ItemStack(Blocks.DIRT, 1 , 2), new Object[]{
    		Blocks.DIRT, "treeLeaves"});
		
    		
		
		
		
		
	}
}
