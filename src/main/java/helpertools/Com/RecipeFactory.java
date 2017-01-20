package helpertools.Com;

import static net.minecraftforge.oredict.RecipeSorter.Category.SHAPELESS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import helpertools.Main;
import helpertools.Com.Tools.ToolBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
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
	public static void Bomb(Boolean flag, ItemStack output, Object item){
		ShapedRecipe(flag, output,new Object[]{
		    	"SSS",
	    		"SWS",
	    		"SSS", 'S', item, 'W', Items.GUNPOWDER});
	}
	
	
	public static void RegisterRecipes(){
		Main.logger.info("Loading Recipes");
		
		OreDictionary.registerOre("string", Items.STRING);
		//Recipe Type Registration
		RecipeSorter.register(Main.PATH+"repair_recipe",  repair_recipe.class, SHAPELESS, "after:forge:shapelessore");
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
	    //Bombs
	    Bomb(Recipe_DirtBombs, new ItemStack(dirtbomb, Output_DirtBombs,0), Blocks.DIRT);
	    Bomb(Recipe_SandBombs, new ItemStack(dirtbomb, Output_SandBombs,1), Blocks.SAND);
	    Bomb(Recipe_GravelBombs, new ItemStack(dirtbomb, Output_GravelBombs,2), Blocks.GRAVEL);
	    Bomb(Recipe_PlantBombs, new ItemStack(dirtbomb, Output_PlantBombs,3), new ItemStack(Items.DYE, 1, 15));
	    Bomb(Recipe_SnowBombs, new ItemStack(dirtbomb, Output_SnowBombs,4), Items.SNOWBALL);
	    ShapelessRecipe(Recipe_DesertBombs,new ItemStack(dirtbomb, Output_DesertBombs , 5), new Object[]{
	    	new ItemStack(dirtbomb, 1, 3), Blocks.SAND});
	    ShapelessRecipe(Recipe_MushroomBombs,new ItemStack(dirtbomb, Output_MushroomBombs , 6), new Object[]{
	    	new ItemStack(dirtbomb, 1, 3), Blocks.MYCELIUM});
	    Bomb(Recipe_VoidBombs, new ItemStack(dirtbomb, Output_VoidBombs,7), Items.ENDER_PEARL);
	    
	    
	    ShapedRecipe(Recipe_MirageHusk, new ItemStack(miragehusk,1,0),new Object[]{
			"TXT",
			"TYT",
			" Z ", 'X',"logWood", 'Y', Items.ENDER_EYE,'Z',Items.REDSTONE, 'T',"treeLeaves"});
	    	
		
		//Blocks		
		ShapedRecipe(Recipe_EuclideanBlock, new ItemStack(transcriberBlock,1,0),new Object[]{
			" L ",
    		"LsL",
    		" L ", 's', "sandstone", 'L', "gemLapis"});
		
		ShapelessRecipe(Recipe_Fake_Bedrock,new ItemStack(falseBedrock, Output_False_Bedrock , 0), new Object[]{
			"cobblestone", "cobblestone", "cobblestone", Blocks.COAL_BLOCK});
			    	
		
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
		
		if(Repairs_allowed){
		GameRegistry.addRecipe(new repair_recipe(new ItemStack(exchange_tool),Expander_Amount, new ItemStack(exchange_tool), Items.GOLD_INGOT));
		GameRegistry.addRecipe(new repair_recipe(new ItemStack(expandertool),Exchanger_Amount, new ItemStack(expandertool), Items.IRON_INGOT));
		GameRegistry.addRecipe(new repair_recipe(new ItemStack(pattern_tool),Pattern_Amount, new ItemStack(pattern_tool), new ItemStack(Items.DYE, 1, 4)));
		GameRegistry.addRecipe(new repair_recipe(new ItemStack(crossbow_tool),Crossbow_Amount, new ItemStack(crossbow_tool), Items.STRING));
		}
    		
	}
	
	
//================================ Custom Recipes for Repairs ===================================================//	
	
static class repair_recipe implements IRecipe{

	protected ItemStack output = null;
	protected int repairAmount = 0;

	protected ArrayList<Object> input = new ArrayList<Object>();

	public repair_recipe(ItemStack result, int repairAmount, Object... recipe)
	{
		this.repairAmount = repairAmount;
		output = result.copy();
		for (Object in : recipe)
		{
			if (in instanceof ItemStack)
			{
				input.add(((ItemStack)in).copy());
			}
			else if (in instanceof Item)
			{
				input.add(new ItemStack((Item)in));
			}
			else if (in instanceof Block)
			{
				input.add(new ItemStack((Block)in));
			}
			else if (in instanceof String)
			{
				input.add(OreDictionary.getOres((String)in));
			}
			else
			{
				String ret = "Invalid shapeless ore recipe: ";
				for (Object tmp :  recipe)
				{
					ret += tmp + ", ";
				}
				ret += output;
				throw new RuntimeException(ret);
			}

		}

	}

	@Override
	public int getRecipeSize(){ return input.size(); }

	@Override
	public ItemStack getRecipeOutput(){ return output; }

	/**
	 * Returns an Item that is the result of this recipe
	 */
	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1){ return output.copy(); }

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean matches(InventoryCrafting inv, World world)
	{
		ArrayList<Object> required = new ArrayList<Object>(input);

		ItemStack req0 = (ItemStack) input.get(0);
		ItemStack req1 = (ItemStack) input.get(1);
		ItemStack future = null;

		Item i0 = req0.getItem();

		boolean match0 = false, match1 = false;	        
		Item compare;
		int emptyslots = 0;
		int additive = 0;

		for (int x = 0; x < inv.getSizeInventory(); x++)
		{
			ItemStack slot = inv.getStackInSlot(x);

			if (slot != null)
			{	
				compare = slot.getItem();
				if(compare == i0){
					match0 = true;
					future = slot;

				}
				if(OreDictionary.itemMatches(req1, slot, false)){
					match1 = true;
					additive++;
				}
			}	            
			else{
				emptyslots++;
			}
		}
		if(match1 && match0 && emptyslots >inv.getSizeInventory()-2 -additive){
			if (future == null) return false;

			output = repair(future, additive);
			return true;
		}

		return required.isEmpty();
	}

	public ItemStack repair(ItemStack stack, int additive){
		ItemStack future = stack.copy();
		int max = future.getMaxDamage();
		int cur = future.getItemDamage();

		int repair = cur-repairAmount*additive;

		if(repair > max){repair = max;}
		future.setItemDamage(repair);	    	

		return future;
	}

	public ArrayList<Object> getInput()
	{
		return this.input;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) //getRecipeLeftovers
	{
		return ForgeHooks.defaultRecipeGetRemainingItems(inv);
	}		

	}
}
