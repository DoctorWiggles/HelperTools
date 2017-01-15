package helpertools;
import helpertools.gui.Fluid_Tool_Hud;
import helpertools.gui.ToolHud;
import helpertools.test.GuiHandler;
import helpertools.util.Dispenser_Handler;
import helpertools.util.ForgeEventHandler;
import helpertools.util.KeyBindings;
import helpertools.util.KeyInputHandler;
import helpertools.util.NetworkMessage;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.common.network.NetworkMod; // not used in 1.7

@Mod(modid=Main.MODID, name=Main.MODNAME, version=Main.VERSION)
public class Main 
{			
	
	public static final String MODID = "helpertools";
	public static final String MODNAME = "HelperTools";
	public static final String VERSION = "v1.7a";
	
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
	@Instance(value = MODID)
	public static Main instance; 

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="helpertools.ClientProxy", serverSide="helpertools.CommonProxy")
	public static CommonProxy proxy;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		/** Configuration **/      	
		ConfigurationFactory.ProcessConfiguration(event);   


		/**Forge Handlers**/        	
		if(ConfigurationFactory.HandlerBottledmilk == true){
			FMLCommonHandler.instance().bus().register(Main.eventHandler);
			MinecraftForge.EVENT_BUS.register(Main.eventHandler);
		}		
		GameRegistry.registerFuelHandler(new Fuel_Handler());


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
		ModRegistry.create_Items();
		ModRegistry.create_Entities();

		/** Register Renders**/
		proxy.registerRenderers();               

		//////////////////
		/** Gui Stuff **/
		////////////////
		if (FMLCommonHandler.instance().getSide().isClient()){
			NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		}

	}

	@EventHandler 
	public void postInit(FMLPostInitializationEvent event) {

		/** Recipes **/
		RecipeFactory.RegisterRecipes();
		Dispenser_Handler.registerVanillaDispenserBehaviors();
	}

}