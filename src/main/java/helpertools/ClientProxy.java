package helpertools;

import helpertools.Client.Armor_Render_Handler;
import helpertools.Client.Highlight_Handler;
import helpertools.Client.KeyBindings;
import helpertools.Client.KeyInputHandler;
import helpertools.Client.RenderRegistry;
import helpertools.Client.Render_Entity;
import helpertools.Client.ToolHud;
import helpertools.Com.Config;
import helpertools.Com.Mirage_Client_Message;
import helpertools.Com.Registry_Entity;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		KeyBindings.init();  
		MinecraftForge.EVENT_BUS.register(new ToolHud(Minecraft.getMinecraft()));
		Main.network.registerMessage(Mirage_Client_Message.Handler.class, Mirage_Client_Message.class, 2, Side.CLIENT); 
		//RenderRegistry.Bakery_Advance();
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);

		RenderRegistry.registerItemRenderer();
		RenderRegistry.registerBlockRenderer();		
		Render_Entity.register_entity_renderer();
		MinecraftForge.EVENT_BUS.register(new Armor_Render_Handler());
		MinecraftForge.EVENT_BUS.register(new Highlight_Handler());
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

}

