package helpertools.Client;

import helpertools.Common.ItemRegistry;
import helpertools.Common.Entity.Entity_DirtBombProjectile;
import helpertools.Common.Entity.Entity_DynamiteProjectile;
import helpertools.Common.Entity.Entity_FlyingItem;
import helpertools.Common.Entity.Entity_RedTorchProjectile;
import helpertools.Common.Entity.Entity_TorchProjectile;
import helpertools.Common.Entity.Renders.RenderDirtBombProjectile;
import helpertools.Common.Entity.Renders.RenderDynamiteProjectile;
import helpertools.Common.Entity.Renders.RenderRedTorchProjectile;
import helpertools.Common.Entity.Renders.RenderTorchProjectile;
import helpertools.Common.Entity.Renders.Render_FlyingItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class Render_Entity {
	

	static RenderManager Randy = Minecraft.getMinecraft().getRenderManager();	
	static RenderItem Item = Minecraft.getMinecraft().getRenderItem();
	
	public static void register_entity_renderer(){
		//super.registerRenderers();    
		reg(Entity_TorchProjectile.class, new RenderTorchProjectile(Randy));
		
        reg(Entity_DynamiteProjectile.class, new RenderDynamiteProjectile(Randy));
        
        reg(Entity_RedTorchProjectile.class, new RenderRedTorchProjectile(Randy));
        
        reg(Entity_DirtBombProjectile.class, new RenderDirtBombProjectile(Randy, ItemRegistry.dirtbomb, Item));
       
		reg(Entity_FlyingItem.class, new Render_FlyingItem(Randy, ItemRegistry.miragehusk, Item));
        
	}
	

	public static void reg(Class<? extends Entity> entityClass, Render renderer){
		
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderer);
	}

}
