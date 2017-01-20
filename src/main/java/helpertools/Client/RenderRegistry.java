package helpertools.Client;

import helpertools.Main;
import helpertools.Com.Config;
import helpertools.Com.ItemRegistry;
import helpertools.Com.Items.Item_Bomb;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
/**http://bedrockminer.jimdo.com/modding-tutorials/basic-modding-1-8/first-item/**/
public final class RenderRegistry {

	public static void registerItemRenderer() {
		Main.logger.info("Registering Renders");
		reg(ItemRegistry.chocolatemilk);
		reg(ItemRegistry.milkbottle);
		Alternates_handler(ItemRegistry.expandertool, Config.Fancy_Expand);
		Alternates_handler(ItemRegistry.exchange_tool, Config.Fancy_Exchange);
		Alternates_handler(ItemRegistry.pattern_tool, Config.Fancy_PatternTool);		
		//reg(ItemRegistry.debug_states_tool);
		reg(ItemRegistry.dynamitebolt);
		reg(ItemRegistry.crossbow_tool);
		//reg(ItemRegistry.dirtbomb);
		Meta_Resource_handler(ItemRegistry.dirtbomb, Item_Bomb.max_types);
		reg(ItemRegistry.miragehusk);
		reg(ItemRegistry.shadecore);
		
		
			    
	}	
	
	public static void registerBlockRenderer(){
		reg(ItemRegistry.falseBedrock);
		reg(ItemRegistry.transcriberBlock);
		reg(ItemRegistry.LooseDirtBlock);
		reg(ItemRegistry.BalloonBlock);
		reg(ItemRegistry.LampBlock);
		reg(ItemRegistry.LampBlock_on);
		reg(ItemRegistry.LampBlock_perm);
		reg(ItemRegistry.FloaterBlock);
		
	}

//===========================Short cut Methods========================================//

	public static String modid = Main.MODID;
	public static String path = Main.PATH;
	

	/** Master shortcut method for meta items
	 * Creates a resource path
	 * Then registers the item to that path
	 * @param item Item to register
	 * @param range Subtype meta range '0-12 ex'
	 */
	public static void Meta_Resource_handler(Item item, int range){	
		//Iterate through subtype range add new entries along the way
		for (int i = 0; i < range; i ++) {
			//A new resource path to bind to the item
			ModelBakery.registerItemVariants(item, entry(item.getUnlocalizedName().substring(5)+"_"+i));
		 	//Register the item to the new path
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register
			(item, i, entry(item.getUnlocalizedName().substring(5)+"_"+i));		
		}
	}
	
	/** Swaps model resource locations **/
	public static void Alternates_handler(Item item, boolean flag){	
		String alt = "";
		if(!flag) alt = "_alt";

		ModelBakery.registerItemVariants(item, entry(item.getUnlocalizedName().substring(5)+alt));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register
		(item, 0, entry(item.getUnlocalizedName().substring(5)+alt));	
	}


	public static ModelResourceLocation entry(String file){
		return new ModelResourceLocation(path + file, "inventory");		
	}
	

	public static void reg(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register
		(item, 0, new ModelResourceLocation(path + item.getUnlocalizedName().substring(5), "inventory"));
		//Main.logger.info(path + item.getUnlocalizedName().substring(5), "inventory");
	}
	
	public static void reg(Item item, String file) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register
		(item, 0, new ModelResourceLocation(path + file, "inventory"));
		//Main.logger.info(path + item.getUnlocalizedName().substring(5), "inventory");
	}
	
	public static void Meta_reg(Item item, int meta, String file) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register
	    (item, meta, new ModelResourceLocation(path + file, "inventory"));
	    //Main.logger.info(path + file, "inventory");
	}

	public static void reg(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(path + block.getUnlocalizedName().substring(5), "inventory"));
		//Main.logger.info(path + block.getUnlocalizedName().substring(5), "inventory");
	}
		
	public static void Meta_reg(Block block, int meta, String file) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register
	    (Item.getItemFromBlock(block), meta, new ModelResourceLocation(path + file, "inventory"));
	    //Main.logger.info(path + file, "inventory");
	}
	

	
}