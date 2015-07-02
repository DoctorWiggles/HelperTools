package helpertools;

import helpertools.Common.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		
		FMLCommonHandler.instance().bus().register(Main.eventHandler);
    	MinecraftForge.EVENT_BUS.register(Main.eventHandler);
		
		ItemRegistry.createItems();
	}

	public void init(FMLInitializationEvent e) {

	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}
