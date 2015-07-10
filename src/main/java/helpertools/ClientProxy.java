package helpertools;

import helpertools.Client.KeyBindings;
import helpertools.Client.KeyInputHandler;
import helpertools.Client.Render.RenderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		KeyBindings.init();  
		//MinecraftForge.EVENT_BUS.register(new ToolHud(Minecraft.getMinecraft()));
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);

		RenderRegistry.registerItemRenderer();
		RenderRegistry.registerBlockRenderer();
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

}

