package helpertools;

import helpertools.Client.ToolHud;
import helpertools.Common.ConfigurationFactory;
import helpertools.Common.ItemRegistry;
import helpertools.Common.NetworkMessage;
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
		ConfigurationFactory.InitialConfig(e);
		ConfigurationFactory.PostConfig(e);
		
		/** Networking & Packets **/
    	Main.network = NetworkRegistry.INSTANCE.newSimpleChannel("GoatsInABoat");
    	//registerMessage(MyMessageHandler.class, MyMessage.class, packetID, receivingSide)
    	Main.network.registerMessage(NetworkMessage.Handler.class, NetworkMessage.class, 0, Side.SERVER);
        // network.registerMessage(SecondMessage.Handler.class, SecondMessage.class, 1, Side.CLIENT);
    	
    	
    	
	}

	public void init(FMLInitializationEvent e) {
		
		ItemRegistry.createItems();
		ItemRegistry.createBlocks();
		RecipeFactory.RegisterRecipes();
		Registry_Entity.register_entities();

	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}
