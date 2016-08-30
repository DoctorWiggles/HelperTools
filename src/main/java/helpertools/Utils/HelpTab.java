package helpertools.Utils;

import helpertools.Common.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HelpTab extends CreativeTabs
{
			public static HelpTab HelperTools = new HelpTab();
	
	public HelpTab()
	{
		//super(EnumChatFormatting.BLUE +"" + EnumChatFormatting.ITALIC + "Helper Tools");
		super("HelperTools");
		
		 this.setBackgroundImageName("items8.png");
	}
	
	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(ItemRegistry.exchange_tool);
		//return new ItemStack(Helpertoolscore.staffoftransformation2);
	}
	@Override
	public Item getTabIconItem() 
	{
		//return Items.command_block_minecart;
		return ItemRegistry.exchange_tool;
	}
}