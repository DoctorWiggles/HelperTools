package helpertools.Client;

import helpertools.Common.Entity.Entity_DynamiteProjectile;
import helpertools.Common.Entity.Entity_RedTorchProjectile;
import helpertools.Common.Entity.Entity_TorchProjectile;
import helpertools.Common.Entity.Renders.RenderDynamiteProjectile;
import helpertools.Common.Entity.Renders.RenderRedTorchProjectile;
import helpertools.Common.Entity.Renders.RenderTorchProjectile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class Render_Entity {
	

	static RenderManager Randy = Minecraft.getMinecraft().getRenderManager();	
	
	public static void register_entity_renderer(){
		//super.registerRenderers();    
        RenderingRegistry.registerEntityRenderingHandler
        (Entity_TorchProjectile.class, new RenderTorchProjectile(Randy));
        RenderingRegistry.registerEntityRenderingHandler
        (Entity_DynamiteProjectile.class, new RenderDynamiteProjectile(Randy));
        RenderingRegistry.registerEntityRenderingHandler
        (Entity_RedTorchProjectile.class, new RenderRedTorchProjectile(Randy));
        
       
		
	}
	

	public void reg_ent(Class<? extends Entity> entityClass, Render renderer){
		
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderer);
	}

}
