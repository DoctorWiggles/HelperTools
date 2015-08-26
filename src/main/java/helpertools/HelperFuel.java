package helpertools;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class HelperFuel implements IFuelHandler {
	/**
	 * credit The_Fireplace
	 */

	@Override
	public int getBurnTime(ItemStack fuel) {
		Block blockFuel = Block.getBlockFromItem(fuel.getItem());
		Item itemFuel = fuel.getItem();
		
		 if(blockFuel == Common_Registry.MagicalFuelBlock)
			 return 4000;
		 if(blockFuel == Common_Registry.ActiveMagicalFuelBlock)
			 return 4000;
		 if(itemFuel == Common_Registry.staffofexpansion)
			 return 2000;
		 if(itemFuel == Common_Registry.euclideantransposer)
			 return 2000;
		 if(itemFuel == Common_Registry.staffoftransformation2)
			 return 2000;
		 if(itemFuel == Common_Registry.torchlauncher)
			 return 800;
		 else		
			 return 0;
		 
	}

}
