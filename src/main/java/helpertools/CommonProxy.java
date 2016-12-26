package helpertools;

import helpertools.Client.ToolHud;
import helpertools.Common.Config;
import helpertools.Common.ItemRegistry;
import helpertools.Common.Mirage_Message;
import helpertools.Common.Tool_Message;
import helpertools.Common.RecipeFactory;
import helpertools.Common.Registry_Entity;
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
		
		/** Networking & Packets **/
    	Main.network = NetworkRegistry.INSTANCE.newSimpleChannel("GoatsInABoat"); 	//registerMessage(MyMessageHandler.class, MyMessage.class, packetID, receivingSide)
    	Main.network.registerMessage(Tool_Message.Handler.class, Tool_Message.class, 0, Side.SERVER);  // network.registerMessage(SecondMessage.Handler.class, SecondMessage.class, 1, Side.CLIENT);
    	Main.network.registerMessage(Mirage_Message.Handler.class, Mirage_Message.class, 1, Side.SERVER); 
    	
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
