package helpertools.Common.Entity;

import helpertools.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class Registry_Entity {
	
	
	static RenderManager Randy = Minecraft.getMinecraft().getRenderManager();	
	
	public static void register_entity_renderer(){
		//super.registerRenderers();    
        RenderingRegistry.registerEntityRenderingHandler
        //(EntityTorchProjectile.class, new RenderTorchProjectile(null, null, 0));
        (EntityTorchProjectile.class, new RenderTorchProjectile(Randy));
		
	}
	
	public static void register_entities(){
		
		 EntityRegistry.registerModEntity(EntityTorchProjectile.class, "TorchBolt", 10, Main.instance, 350, 10, true);
		
	}
	
	public void reg_ent(Class<? extends Entity> entityClass, Render renderer){
		
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderer);
	}

}
