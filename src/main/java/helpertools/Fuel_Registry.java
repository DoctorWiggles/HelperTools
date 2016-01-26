package helpertools;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class Fuel_Registry implements IFuelHandler {
	/**
	 * credit The_Fireplace
	 */

	@Override
	public int getBurnTime(ItemStack fuel) {
		Block blockFuel = Block.getBlockFromItem(fuel.getItem());
		Item itemFuel = fuel.getItem();
		
		 if(blockFuel == Mod_Registry.MagicalFuelBlock)
			 return 4000;
		 if(blockFuel == Mod_Registry.ActiveMagicalFuelBlock)
			 return 4000;
		 if(itemFuel == Mod_Registry.staffofexpansion)
			 return 2000;
		 if(itemFuel == Mod_Registry.euclideantransposer)
			 return 2000;
		 if(itemFuel == Mod_Registry.staffoftransformation2)
			 return 2000;
		 if(itemFuel == Mod_Registry.torchlauncher)
			 return 800;
		 else		
			 return 0;
		 
	}

}
