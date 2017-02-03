package helpertools.Com;

import helpertools.Com.Items.Item_BombCharm;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;

public class Mod_Recipes extends Config{
	
	
//================================ Custom Recipes for Repairs ===================================================//	
	
	static class Repair_recipe extends Mod_recipe{

		protected int repairAmount = 0;

		public Repair_recipe(ItemStack result, int repairAmount, Object... recipe)
		{	super(result, recipe);
			this.repairAmount = repairAmount;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean matches(InventoryCrafting inv, World world)
		{
			ArrayList<Object> required = new ArrayList<Object>(input);

			ItemStack req_stack_0 = (ItemStack) input.get(0);
			ItemStack req_stack_1 = (ItemStack) input.get(1);
			ItemStack future_output = ItemStack.EMPTY;

			Item req_item_0 = req_stack_0.getItem();

			boolean matched_0 = false, matched_1 = false;	        
			Item compare;
			int emptyslots = 0;
			int additive = 0;

			for (int x = 0; x < inv.getSizeInventory(); x++)
			{
				ItemStack slot_stack = inv.getStackInSlot(x);

				if (!slot_stack.isEmpty())
				{	
					compare = slot_stack.getItem();
					if(compare == req_item_0){
						matched_0 = true;
						future_output = slot_stack;

					}
					if(OreDictionary.itemMatches(req_stack_1, slot_stack, false)){
						matched_1 = true;
						additive++;
					}
				}	            
				else{
					emptyslots++;
				}
			}
			if(matched_1 && matched_0 && emptyslots >inv.getSizeInventory()-2 -additive){
				if (future_output.isEmpty()) return false;

				output = repair(future_output, additive);
				return true;
			}

			return required.isEmpty();
		}

		public ItemStack repair(ItemStack stack, int additive){
			ItemStack future_output = stack.copy();
			int max = future_output.getMaxDamage();
			int cur = future_output.getItemDamage();

			int repair = cur-repairAmount*additive;

			if(repair > max){repair = max;}
			future_output.setItemDamage(repair);	    	

			return future_output;
		}
	}	
//================================ Charm Upgrade Recipe ===================================================//	
	
		static class Charm_recipe extends Mod_recipe{
			
			public Charm_recipe(ItemStack result, Object... recipe) {
				super(result, recipe);
			}

			@SuppressWarnings("unchecked")
			@Override
			public boolean matches(InventoryCrafting inv, World world)
			{
				ArrayList<Object> required = new ArrayList<Object>(input);

				ItemStack req_stack_0 = (ItemStack) input.get(0);
				ItemStack future_output = ItemStack.EMPTY;

				Item req_item_0 = req_stack_0.getItem();
      
				Item compare;
				int emptyslots = 0;
				int additive = 0;
				int totalcharms = 0;
				int highest_level = 0;
				int hightest_charm_location = 0;

				for (int x = 0; x < inv.getSizeInventory(); x++)
				{
					ItemStack slot_stack = inv.getStackInSlot(x);

					if (slot_stack.isEmpty())
					{	
						compare = slot_stack.getItem();
						if(compare == req_item_0){
							totalcharms++;
							Item_BombCharm charm = (Item_BombCharm)req_item_0;
							if(charm.getlevel(slot_stack) > highest_level){
								highest_level = charm.getlevel(slot_stack);
								hightest_charm_location = x;
							}							
						}
					}	            
					else{
						emptyslots++;
					}
				}
				if(totalcharms > highest_level && (inv.getSizeInventory()-totalcharms) == emptyslots){
					
					output = upgrade(inv.getStackInSlot(hightest_charm_location));
					return true;
				}

				return required.isEmpty();
			}
			
			public ItemStack upgrade(ItemStack stack){
				
				ItemStack future_output = stack.copy();
				
				Item_BombCharm charm = (Item_BombCharm)future_output.getItem();
				int level = charm.getlevel(future_output);
				
				if(level <5){
					
					charm.setlevel(future_output, level+1);
					switch(level+1){
						case 2:future_output.setStackDisplayName(
								TextFormatting.GREEN+"Pulsing Bomb Charm (+2)"); break;
						case 3:future_output.setStackDisplayName(
								TextFormatting.YELLOW+"Greater Bomb Charm (+3)"); break;
						case 4:future_output.setStackDisplayName(
								TextFormatting.BLUE+"Grand Bomb Charm (+4)"); break;
						case 5:future_output.setStackDisplayName(
								TextFormatting.DARK_PURPLE+"Master Bomb Charm (+5)"); break;
					}
					
				}
				return future_output;
			}
			
	}
	
//=========================================================================================//
static class Mod_recipe implements IRecipe{ 

			protected ItemStack output = null;

			protected ArrayList<Object> input = new ArrayList<Object>();

			public Mod_recipe(ItemStack result, Object... recipe)
			{
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

				return required.isEmpty();
			}

			public ArrayList<Object> getInput(){ return this.input; }

			@Override
		    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) //getRecipeLeftovers
		    {
		        return ForgeHooks.defaultRecipeGetRemainingItems(inv);
		    }	

		}

}
