package helpertools;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeFactory extends Common_Registry{
	
	
	/** Registers Recipes and ore dictionary etc **/
	public static void RegisterRecipes(){
		
		/**Recipes Tool //Recipes Tools	//Recipes Tools	//Recipes Tools	//Recipes Tools **/
    	
    	//None Ore dictionary version
    	
    	//GameRegistry.addRecipe(new ItemStack(staffofexpansion), new Object[]
    			//{"XZX",
    			//" Y ",
    			//" Y ",
    			//'X', Items.iron_ingot, 'Y', Items.stick, 'Z', Items.diamond
    			//});
    	
		

    	//Expansion Recipes
    	///////////////////
    	if(RecipeDiamondsForExpansionStaff == true){
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(staffofexpansion), true, new Object[]{
    		"XZX",
			" Y ",
			" Y ", Character.valueOf('X'),"ingotIron", Character.valueOf('Z'),"gemDiamond",
			Character.valueOf('Y'),"stickWood"}));
    	}
    	if(RecipeEmeraldsForExpansionStaff == true){
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(staffofexpansion), true, new Object[]{
    		"XZX",
			" Y ",
			" Y ", Character.valueOf('X'),"ingotIron", Character.valueOf('Z'),"gemEmerald",
			Character.valueOf('Y'),"stickWood"}));
    	}
    	if(RecipePearlsForExpansionStaff == true){
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(staffofexpansion), true, new Object[]{
    		"XZX",
			" Y ",
			" Y ", Character.valueOf('X'),"ingotIron", Character.valueOf('Z'), Items.ender_pearl,
			Character.valueOf('Y'),"stickWood"}));
    	}
    	//Transformation Recipes
    	/////////////////////////
    	if(RecipeDiamondsForExpansionStaff == true){
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(staffoftransformation2), true, new Object[]{
    		"XZX",
			" Y ",
			" Y ", Character.valueOf('X'),"ingotGold", Character.valueOf('Z'),"gemDiamond",
			Character.valueOf('Y'),"stickWood"}));
    	}
    	if(RecipeEmeraldsForExpansionStaff == true){
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(staffoftransformation2), true, new Object[]{
    		"XZX",
			" Y ",
			" Y ", Character.valueOf('X'),"ingotGold", Character.valueOf('Z'),"gemEmerald",
			Character.valueOf('Y'),"stickWood"}));
    	}
    	if(RecipePearlsForExpansionStaff == true){
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(staffoftransformation2), true, new Object[]{
    		"XZX",
			" Y ",
			" Y ", Character.valueOf('X'),"ingotGold", Character.valueOf('Z'), Items.ender_pearl,
			Character.valueOf('Y'),"stickWood"}));
    	}
    	//EU Staff Recipes
    	/////////////////////////
    	if(RecipeDiamondsForEuclideanStaff == true){
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(euclideantransposer), true, new Object[]{
    		"SZS",
			"LYL",
			" Y ", Character.valueOf('S'),"sandstone", Character.valueOf('L'),"gemLapis",
			Character.valueOf('Z'),"gemDiamond",Character.valueOf('Y'),Items.reeds}));
    	}
    	if(RecipeEmeraldsForEuclideanStaff == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(euclideantransposer), true, new Object[]{
        		"SZS",
    			"LYL",
    			" Y ", Character.valueOf('S'),"sandstone", Character.valueOf('L'),"gemLapis",
    			Character.valueOf('Z'),"gemEmerald",Character.valueOf('Y'),Items.reeds}));
        	}
    	if(RecipePearlsForEuclideanStaff == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(euclideantransposer), true, new Object[]{
        		"SZS",
    			"LYL",
    			" Y ", Character.valueOf('S'),"sandstone", Character.valueOf('L'),"gemLapis",
    			Character.valueOf('Z'), Items.ender_pearl, Character.valueOf('Y'),Items.reeds}));
        	}
    	        	
    	//Torch Launcher Recipes
    	////////////////////////       
    	if(RecipeTorchLauncher == true){
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(torchlauncher),true, new Object[]{
    		"   ",
    		"FBR",
    		"   ", Character.valueOf('B'), Items.bow, Character.valueOf('F'), Items.flint_and_steel,
			 Character.valueOf('R'), "plankWood"}));
    	
    	
    	//Torch Launcher Alternate Recipes
    	//////////////////////////////////        	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(torchlauncher),true, new Object[]{
    		"FWS",
    		"W S",
    		"RWS", Character.valueOf('S'), "helpstring", Character.valueOf('F'), Items.flint_and_steel,
    		Character.valueOf('R'), "plankWood",
			Character.valueOf('W'), "stickWood"}));
    	
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(torchlauncher,1, 0), new Object[]{
    		Items.bow, Items.flint_and_steel, "plankWood"}));
    	}
    	
    	
    	//Dynamite bolt Recipes
    	////////////////////////
    	if(RecipeStringForDynamiteBolt == true){
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Common_Registry.dynamitebolt, OutputDynamiteBolt, 0), new Object[]{
    		Blocks.tnt, Items.arrow, Items.arrow, Items.arrow,Items.arrow, "string"}));
    	}
    	if(RecipeSlimeForDynamiteBolt == true){
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Common_Registry.dynamitebolt, OutputDynamiteBolt, 0), new Object[]{
    		Blocks.tnt, Items.arrow, Items.arrow, Items.arrow, Items.arrow, "slimeball"}));
    	}
    	
    	
    	
    	
    	//Imitation bedrock recipes
    	
    	//GameRegistry.addShapelessRecipe(new ItemStack(Helpertoolscore.Ibedrock, 2 , 0), new Object[]
    	//		{ Items.redstone, Blocks.sandstone, Blocks.cobblestone, Blocks.gravel
    	//		});
    	
    	/*
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Helpertoolscore.Ibedrock, 2 , 0), new Object[]{
    		"dustRedstone", "cobblestone", "sandstone", "gravel" }));
    	*/
    	if(RecipeImitationBedrock == true){
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Common_Registry.Ibedrock, OutputImitationBedrock , 0), new Object[]{
    		"cobblestone", "cobblestone", "cobblestone", Blocks.coal_block }));
    	}
    	
    	if(RecipeEuclideanBlock == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Common_Registry.TranscriberBlock),true, new Object[]{
            		" L ",
            		"LsL",
            		" L ", Character.valueOf('s'), "sandstone", Character.valueOf('L'), "gemLapis"}));
        	}
    	
    	
    	if(RecipeChimenyPipes == true){
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Common_Registry.StokedPipe, OutputChimneyPipe , 0), new Object[]{
    		"ingotIron", Items.coal, "ingotIron"}));
    	}
    	if(RecipeMagicalFuel == true){
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Common_Registry.MagicalFuelBlock, OutputMagicalFuel , 0), new Object[]{
    		"ingotIron", "helpbonemeal", "gemLapis"}));
    	}
    	//Debugging tool
    	     /**   	
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Helpertoolscore.debugtool, 1 , 0), new Object[]{
    		Items.nether_star, Blocks.bedrock, }));
    	**/
    	/**
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Helpertoolscore.rfdebugtool, 1 , 0), new Object[]{
    		Items.nether_star, Blocks.redstone_block, }));
    		**/
    		
    		
    	if(RecipeDirtBomb == true){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(dirtbomb, OutputDirtBomb, 0), true, new Object[]{
        		"ZZZ",
    			"ZXZ",
    			"ZZZ", Character.valueOf('X'), Items.gunpowder, Character.valueOf('Z'), Blocks.dirt}));
        	}
    	
    	ItemStack BucketOut = new ItemStack(Items.milk_bucket.setContainerItem(Items.bucket));
    	//ItemStack BottleOut = new ItemStack(Helpertoolscore.bottledmilk.setContainerItem(Items.glass_bottle));
    	//I don't know but once set it overrides it for every other recipe, setting it to null only override the other.
    	//.oncreated --consume item seems to balance this with only a minor dupe bug for the first in the inventory
    	//Recipes shouldn't require this much effort, what the hell
    	ItemStack Bottledestroy = new ItemStack(Common_Registry.bottledmilk.setContainerItem(null));
    	if(RecipeBottledmilk == true){
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Common_Registry.bottledmilk, 1 , 0), new Object[]{
    		 BucketOut, Items.glass_bottle}));
    	}
    	//GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.milk_bucket, 1 , 0), new Object[]{
    	//	BottleOut, Items.bucket}));
    	if(RecipeChocolatemilk == true){
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Common_Registry.chocolatemilk, 1 , 0), new Object[]{
    		Bottledestroy, new ItemStack(Items.dye, 1, 3)}));
    	}
    	
    	//podzol recipe
    	if(RecipePodzol == true){
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.dirt, 1 , 2), new Object[]{
    		Blocks.dirt, "treeLeaves"}));
    	
    	}
    	//Slab Recipes
    	Block Fullslab = Block.getBlockById(43);
    	//Double Slab
    	if(RecipeDoubleSlab == true){
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Fullslab, 1, 0 ), new Object[]{
    		Blocks.stone_slab, Blocks.stone_slab
    	}));
    	}
    	//Full Slab    
    	if(RecipeFullSlab == true){
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Fullslab, 2, 8 ),true, new Object[]{
    		"ss ",
    		"ss ",
    		"   ", Character.valueOf('s'), new ItemStack(Blocks.stone_slab, 1, 0 )}));
    	}
    	//Full Sandstone Slab
    	if(RecipeFullSandSlab == true){
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Fullslab, 2, 9 ),true, new Object[]{
    		"ss ",
    		"ss ",
    		"   ", Character.valueOf('s'), new ItemStack(Blocks.stone_slab, 1, 1 )}));
    	}
    	
    	
    	
    	/**Extra Dictionaries**/
    	///////////////////////
    	OreDictionary.registerOre("helpstring", Items.string);
    	OreDictionary.registerOre("helpgravel", Blocks.gravel);
    	OreDictionary.registerOre("helpbonemeal", new ItemStack(Items.dye, 1, 15));
    	//  
    	 
		
	}

}
