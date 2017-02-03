package helpertools.Utils;

import helpertools.Com.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HelpTab extends CreativeTabs
{
			public static HelpTab HelperTools = new HelpTab();
	
	public HelpTab()
	{
		//super(TextFormatting.BLUE +"" + TextFormatting.ITALIC + "Helper Tools");
		super("HelperTools");
		
		 this.setBackgroundImageName("items8.png");
	}
	
	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(ItemRegistry.exchange_tool);
	}
	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(ItemRegistry.exchange_tool);
	}
}