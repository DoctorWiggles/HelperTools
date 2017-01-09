package helpertools.Com;



import java.util.ArrayList;
import java.util.List;

import helpertools.Main;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

//Tutorial Source ->  https://github.com/Minalien/BlogArchive/blob/master/ForgeTutorials/Spotlight__Config_GUIs.md
// + http://forum.feed-the-beast.com/threads/code-snippets-classes.51404/page-4#post-876553
// + http://jabelarminecraft.blogspot.com/p/minecraft-modding-configuration-guis.html

public class Config_Gui extends GuiConfig {

	//Simple version	
	/*
	public Config_Gui(GuiScreen parent) {
		super(parent,
				new ConfigElement(Config.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				Main.MODID, false, false, GuiConfig.getAbridgedConfigPath(Config.config.toString()));
	}  
	*/
	
	//Complex version
		public Config_Gui(GuiScreen parent) {
			super(parent, getConfigElements(), Main.MODID, false, false,
					TextFormatting.BLUE +""+ TextFormatting.ITALIC +"HelperTools Configuration");
		}

		/** Compiles a list of config elements */
		private static List<IConfigElement> getConfigElements() {
			List<IConfigElement> list = new ArrayList<IConfigElement>();

			//Add categories to config GUI
			list.add(categoryElement(Config.Durability,"Tool Damage, atk Speed, and Durabilty","Tool Durability"));
			list.add(categoryElement(Config.GUI,"Gui Elements and Text Messeges","GUI Options"));
			list.add(categoryElement(Config.Blockz,"Modify Block Recipes","Bock Recipes"));
			list.add(categoryElement(Config.ToolRecipe,"Modify Tool Recipes","Tool Recipes"));
			list.add(categoryElement(Config.ItemRecipe,"Modify Item Recipes","Item Recipes"));
			list.add(categoryElement(Config.Generation,"Modify Generation","Generation"));
			
			return list;
		}
	 


	/** Creates a button linking to another screen where all options of the category are available */
	private static IConfigElement categoryElement(String category, String name, String tooltip_key) {
		return new DummyConfigElement.DummyCategoryElement(name, tooltip_key,
				new ConfigElement(Config.config.getCategory(category)).getChildElements());
	}
}