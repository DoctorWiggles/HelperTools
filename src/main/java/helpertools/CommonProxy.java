package helpertools;

import helpertools.Client.ToolHud;
import helpertools.Com.Config;
import helpertools.Com.Dummy_Handler;
import helpertools.Com.ItemRegistry;
import helpertools.Com.Mirage_Client_Message;
import helpertools.Com.Mirage_Server_Message;
import helpertools.Com.RecipeFactory;
import helpertools.Com.Registry_Entity;
import helpertools.Com.Tool_Message;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		
		FMLCommonHandler.instance().bus().register(Main.eventHandler);
    	MinecraftForge.EVENT_BUS.register(Main.eventHandler);
		
		/** Configs **/
    	Config.configOptions(e);
		//Config.InitialConfig(e);
		//Config.PostConfig(e);
    	//ID = 0; ID++
		/** Networking & Packets **/
    	Main.network = NetworkRegistry.INSTANCE.newSimpleChannel(Main.MODID); 	//registerMessage(MyMessageHandler.class, MyMessage.class, packetID, receivingSide)
    	Main.network.registerMessage(Tool_Message.Handler.class, Tool_Message.class, 0, Side.SERVER);  // network.registerMessage(SecondMessage.Handler.class, SecondMessage.class, 1, Side.CLIENT);
    	Main.network.registerMessage(Mirage_Server_Message.Handler.class, Mirage_Server_Message.class, 1, Side.SERVER); 
    	Main.network.registerMessage(Dummy_Handler.class, Mirage_Client_Message.class, 2, Side.SERVER); //Dummy Required less explosions
    	
    	ItemRegistry.createItems();
		ItemRegistry.createBlocks();
    	
	}

	public void init(FMLInitializationEvent e) {
		
		RecipeFactory.RegisterRecipes();
		Registry_Entity.register_entities();

	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}
