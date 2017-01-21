package helpertools.Com;

import helpertools.Com.Items.Item_BombCharm;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;

public class Mod_Recipes extends Config{
	
	
	//================================ Custom Recipes for Repairs ===================================================//	
	
	static class Repair_recipe implements IRecipe{

		protected ItemStack output = null;
		protected int repairAmount = 0;

		protected ArrayList<Object> input = new ArrayList<Object>();

		public Repair_recipe(ItemStack result, int repairAmount, Object... recipe)
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
	
	//================================ Charm Upgrade Recipe ===================================================//	
	
		static class Charm_recipe implements IRecipe{

			protected ItemStack output = null;
			protected int repairAmount = 0;

			protected ArrayList<Object> input = new ArrayList<Object>();

			public Charm_recipe(ItemStack result, Object... recipe)
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

				ItemStack req0 = (ItemStack) input.get(0);
				ItemStack future = null;

				Item i0 = req0.getItem();
      
				Item compare;
				int emptyslots = 0;
				int additive = 0;
				int totalcharms = 0;
				int highest = 0;
				int location = 0;

				for (int x = 0; x < inv.getSizeInventory(); x++)
				{
					ItemStack slot = inv.getStackInSlot(x);

					if (slot != null)
					{	
						compare = slot.getItem();
						if(compare == i0){
							totalcharms++;
							Item_BombCharm charm = (Item_BombCharm)i0;
							if(charm.getlevel(slot) > highest){
								highest = charm.getlevel(slot);
								location = x;
							}							
						}
					}	            
					else{
						emptyslots++;
					}
				}
				if(totalcharms > highest && (inv.getSizeInventory()-totalcharms) == emptyslots){
					
					output = upgrade(inv.getStackInSlot(location));
					return true;
				}

				return required.isEmpty();
			}
			
			public ItemStack upgrade(ItemStack stack){
				
				ItemStack future = stack.copy();
				
				Item_BombCharm charm = (Item_BombCharm)future.getItem();
				int level = charm.getlevel(future);
				
				if(level <5){
					
					charm.setlevel(future, level+1);
					switch(level+1){
						case 2:future.setStackDisplayName("˜aPulsing Bomb Charm (+2)"); break;
						case 3:future.setStackDisplayName("˜eGreater Bomb Charm (+3)"); break;
						case 4:future.setStackDisplayName("˜9Grand Bomb Charm (+4)"); break;
						case 5:future.setStackDisplayName("˜dMaster Bomb Charm (+5)"); break;
					}
					
				}
				return future;
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
