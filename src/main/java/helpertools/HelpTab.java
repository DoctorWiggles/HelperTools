package helpertools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

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
		return new ItemStack(Common_Registry.staffoftransformation2);
	}
	@Override
	public Item getTabIconItem() 
	{
		return Common_Registry.staffoftransformation2;
	}
}