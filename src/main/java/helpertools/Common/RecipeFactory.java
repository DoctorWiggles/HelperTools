package helpertools.Common;

import helpertools.Main;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeFactory extends ConfigurationFactory{

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
		
		
		ShapedRecipe(Diamonds_forTools, new ItemStack(expandertool,1,0),new Object[]{
			"XZX",
			" Y ",
			" Y ", 'X',"ingotIron", 'Z', "gemDiamond",'Y',"stickWood"});
		
		ShapedRecipe(Pearls_forTools, new ItemStack(expandertool,1,0),new Object[]{
			"XZX",
			" Y ",
			" Y ", 'X',"ingotIron", 'Z', Items.ender_pearl,'Y',"stickWood"});
		
		ShapedRecipe(Emeralds_forTools, new ItemStack(expandertool,1,0),new Object[]{
			"XZX",
			" Y ",
			" Y ", 'X',"ingotIron", 'Z', "gemEmerald",'Y',"stickWood"});
		
		ItemStack BucketOut = new ItemStack(Items.milk_bucket.setContainerItem(Items.bucket));		
		ShapelessRecipe(Recipe_BottledMilk,new ItemStack(milkbottle, 1 , 0), new Object[]{
			BucketOut, Items.glass_bottle});
		
	}
}
