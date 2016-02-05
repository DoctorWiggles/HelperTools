package helpertools;
import java.util.Random;

import helpertools.blocks.tile_entities.TileEntityIllusion;
import helpertools.blocks.tile_entities.TileEntityObelisk;
import helpertools.blocks.tile_entities.TileEntityTranscriber;
import helpertools.entities.EntityBoltProjectile;
import helpertools.entities.EntityDirtBombProjectile;
import helpertools.entities.EntityDynamiteProjectile;
import helpertools.entities.EntityRedTorchProjectile;
import helpertools.entities.EntityTorchProjectile;
import helpertools.gui.Fluid_Tool_Hud;
import helpertools.gui.Gui_Handler;
import helpertools.gui.ToolHud;
import helpertools.handlers.Armor_Render_Handler;
import helpertools.handlers.BucketHandler;
import helpertools.handlers.Dispenser_Handler;
import helpertools.handlers.ForgeEventHandler;
import helpertools.handlers.Ghostblock_Handler;
import helpertools.items.ItemChocolateMilk;
import helpertools.items.ItemDirtBomb;
import helpertools.items.ItemDynamiteBolt;
import helpertools.items.ItemMilkBottle;
import helpertools.network.KeyBindings;
import helpertools.network.KeyInputHandler;
import helpertools.network.NetworkMessage;
import helpertools.renders.ItemRenderStaff4;
import helpertools.renders.ItemRenderStaff5;
import helpertools.renders.ItemRenderTorchLauncher1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import helpertools.test.GuiBuffBar;
import helpertools.test.GuiHandler;
import helpertools.tools.ItemDebugTool;
import helpertools.tools.ItemEuclideanTransposer;
import helpertools.tools.ItemStaffofExpansion;
import helpertools.tools.ItemStaffofTransformation2;
import helpertools.tools.ItemTorchLauncher;
import net.java.games.input.Keyboard;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
//import cpw.mods.fml.common.Mod.PreInit;    // used in 1.5.2
//import cpw.mods.fml.common.Mod.Init;       // used in 1.5.2
//import cpw.mods.fml.common.Mod.PostInit;   // used in 1.5.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
//import cpw.mods.fml.common.network.NetworkMod; // not used in 1.7
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid="HelperToolsID", name="HelperTools", version="v1.6f")
public class Main 
{			
	////////////////////		
	/** Forge Stuffs **/
	////////////////////

	/** Chocolate milk etc **/
	public static ForgeEventHandler eventHandler = new ForgeEventHandler();
	//public static ForgeEventHandler eventHandler2 = new ForgeEventHandler();

	/** packet, network and client sycning, for guis etc**/
	public static SimpleNetworkWrapper network;

	public static final Logger logger = LogManager.getLogger("HelperToolsID");


	/**  NotGyro **/
	public static Configuration config;		
	
	

	// The instance of your mod that Forge uses.
	@Instance(value = "HelperToolsID")
	public static Main instance; 

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="helpertools.ClientProxy", serverSide="helpertools.CommonProxy")
	public static CommonProxy proxy;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		/** Configuration **/      	
		Mod_Configuration.ProcessConfiguration(event);   

		///////////////////////
		/** Repair Materials **/
		///////////////////////
		/**
    		powercrystal = new ItemPowerCrystal();
    		ExpRodMaterial.setRepairItem(new ItemStack(powercrystal));
    		MetaStaffMaterial.setRepairItem(new ItemStack(powercrystal));
    		EUStaffMaterial.setRepairItem(new ItemStack(powercrystal));
    		TorchMaterial.setRepairItem(new ItemStack(Items.stick));
		 **/
		//causing serverside issues for some reason



		/**Forge Handlers**/        	
		if(Mod_Configuration.HandlerBottledmilk == true){
			FMLCommonHandler.instance().bus().register(Main.eventHandler);
			MinecraftForge.EVENT_BUS.register(Main.eventHandler);
		}		
		GameRegistry.registerFuelHandler(new Fuel_Registry());


		//////////////////////////
		/** Keybinding & Huds **/
		////////////////////////
		if (FMLCommonHandler.instance().getSide().isClient()){
			FMLCommonHandler.instance().bus().register(new KeyInputHandler());
			KeyBindings.init();  
			MinecraftForge.EVENT_BUS.register(new ToolHud(Minecraft.getMinecraft()));
			//
			MinecraftForge.EVENT_BUS.register(new Fluid_Tool_Hud(Minecraft.getMinecraft()));
			//MinecraftForge.EVENT_BUS.register(new GuiBuffBar(Minecraft.getMinecraft()));
		}
		
		

		/** Networking & Packets **/
		network = NetworkRegistry.INSTANCE.newSimpleChannel("GoatsInABoat");
		//registerMessage(MyMessageHandler.class, MyMessage.class, packetID, receivingSide)
		network.registerMessage(NetworkMessage.Handler.class, NetworkMessage.class, 0, Side.SERVER);
		// network.registerMessage(SecondMessage.Handler.class, SecondMessage.class, 1, Side.CLIENT);
		
		

		
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		/** Registry **/
		Mod_Registry.create_Items();
		Mod_Registry.create_Entities();

		/** Register Renders**/
		proxy.registerRenderers();               


		/** Fluids **/
		//BucketHandler.INSTANCE.buckets.put(Common_Registry.jelly_block, Common_Registry.jelly_bucket);
		//MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

		

		/** Dungeon Loot **/
		//DungeonLoot.addLoot(event);              

		//////////////////
		/** Gui Stuff **/
		////////////////
		/*
		if (FMLCommonHandler.instance().getSide().isClient()){
			NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		}
		*/
		
		 NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new Gui_Handler());

	}

	@EventHandler // used in 1.6.2
	//@PostInit   // used in 1.5.2
	public void postInit(FMLPostInitializationEvent event) {

		/** Recipes **/
		Mod_Recipes.RegisterRecipes();
		Dispenser_Handler.registerVanillaDispenserBehaviors();
	}

}