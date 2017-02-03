package helpertools;

import helpertools.Client.Highlight_Handler;
import helpertools.Client.Husk_Render_Layer;
import helpertools.Client.KeyInput_Handler;
import helpertools.Client.Key_Bindings;
import helpertools.Client.RenderRegistry;
import helpertools.Client.Render_Entity;
import helpertools.Client.Tool_Hud;
import helpertools.Com.Mirage_Client_Message;

import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.monster.EntitySkeleton;
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
		
		FMLCommonHandler.instance().bus().register(new KeyInput_Handler());
		Key_Bindings.init();  
		MinecraftForge.EVENT_BUS.register(new Tool_Hud(Minecraft.getMinecraft()));
		Main.network.registerMessage(Mirage_Client_Message.Handler.class, Mirage_Client_Message.class, 2, Side.CLIENT); 
		//RenderRegistry.Bakery_Advance();
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		
		RenderRegistry.registerItemRenderer();
		RenderRegistry.registerBlockRenderer();		
		Render_Entity.register_entity_renderer();
		//MinecraftForge.EVENT_BUS.register(new Armor_Render_Handler());
		Player_Layer_Injection();
		Entity_Layer_Injection();
		MinecraftForge.EVENT_BUS.register(new Highlight_Handler());
				
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
	
	//Add more render layers to a player
	private void Player_Layer_Injection() {
		try{
		Map<String, RenderPlayer> skinMap = Minecraft.getMinecraft().getRenderManager().getSkinMap();
		RenderPlayer render;
		render = skinMap.get("default");
		render.addLayer(new Husk_Render_Layer());

		render = skinMap.get("slim");
		render.addLayer(new Husk_Render_Layer());
		}catch(Exception e){System.out.println("Helper Tools had an accident adding player layers, please report this issue!: "+e);}
	}
	
	
	private void Entity_Layer_Injection() {
		try{					
		Render render =Minecraft.getMinecraft().getRenderManager().getEntityClassRenderObject(EntitySkeleton.class);
		RenderSkeleton skele_render= (RenderSkeleton)render;		
		skele_render.addLayer(new Husk_Render_Layer());
		
		}catch(Exception e){System.out.println("Helper Tools had an accident adding Skeleton layers, please report this issue!: "+e);}
	}
	

}

