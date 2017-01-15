package helpertools;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class Fuel_Handler implements IFuelHandler {
	/**
	 * credit The_Fireplace
	 */

	@Override
	public int getBurnTime(ItemStack fuel) {
		Block blockFuel = Block.getBlockFromItem(fuel.getItem());
		Item itemFuel = fuel.getItem();
		
		 if(blockFuel == ModRegistry.MagicalFuelBlock)
			 return 4000;
		 if(blockFuel == ModRegistry.ActiveMagicalFuelBlock)
			 return 4000;
		 if(itemFuel == ModRegistry.staffofexpansion)
			 return 2000;
		 if(itemFuel == ModRegistry.euclideantransposer)
			 return 2000;
		 if(itemFuel == ModRegistry.staffoftransformation)
			 return 2000;
		 if(itemFuel == ModRegistry.torchlauncher)
			 return 800;
		 else		
			 return 0;
		 
	}

}
