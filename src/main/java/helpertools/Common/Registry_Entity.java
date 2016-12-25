package helpertools.Common;

import helpertools.Main;
import helpertools.Common.Entity.Entity_DirtBombProjectile;
import helpertools.Common.Entity.Entity_DynamiteProjectile;
import helpertools.Common.Entity.Entity_FlyingItem;
import helpertools.Common.Entity.Entity_Mirage;
import helpertools.Common.Entity.Entity_RedTorchProjectile;
import helpertools.Common.Entity.Entity_TorchProjectile;
import helpertools.Common.Entity.Renders.RenderDynamiteProjectile;
import helpertools.Common.Entity.Renders.RenderRedTorchProjectile;
import helpertools.Common.Entity.Renders.RenderTorchProjectile;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class Registry_Entity {
	
	
	
	public static void register_entities(){
		
		 reg(Entity_TorchProjectile.class, "TorchBolt", 10);
		 reg(Entity_RedTorchProjectile.class, "RedTorchBolt", 11);
		 reg(Entity_DynamiteProjectile.class, "DynamiteBolt", 12);
		 reg(Entity_DirtBombProjectile.class, "DirtBomb", 13);
		
		 reg(Entity_FlyingItem.class, "FlyingItem", 14, 010201,327238 );		 
		 reg(Entity_Mirage.class, "Mirage", 15, 010201,327238);
	}
	
	
	public static void reg (Class<? extends Entity> entityClass, String entityName, int id){
		EntityRegistry.registerModEntity(entityClass, entityName, id, Main.instance, 350, 10, true);	
	}
	public static void reg (Class<? extends Entity> entityClass, String entityName, int id, int egg, int eggspots){
		EntityRegistry.registerModEntity(entityClass, entityName, id, Main.instance, 350, 1, true, egg, eggspots);	
	}

}
