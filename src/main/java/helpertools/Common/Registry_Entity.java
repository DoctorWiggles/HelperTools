package helpertools.Common;

import helpertools.Main;
import helpertools.Common.Entity.Entity_DirtBombProjectile;
import helpertools.Common.Entity.Entity_DynamiteProjectile;
import helpertools.Common.Entity.Entity_RedTorchProjectile;
import helpertools.Common.Entity.Entity_TorchProjectile;
import helpertools.Common.Entity.Renders.RenderDynamiteProjectile;
import helpertools.Common.Entity.Renders.RenderRedTorchProjectile;
import helpertools.Common.Entity.Renders.RenderTorchProjectile;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class Registry_Entity {
	
	
	
	public static void register_entities(){
		
		 EntityRegistry.registerModEntity(Entity_TorchProjectile.class, "TorchBolt", 10, Main.instance, 350, 10, true);
		 EntityRegistry.registerModEntity(Entity_RedTorchProjectile.class, "RedTorchBolt", 11, Main.instance, 350, 10, true);
		 EntityRegistry.registerModEntity(Entity_DynamiteProjectile.class, "DynamiteBolt", 12, Main.instance, 350, 30, true);
		 EntityRegistry.registerModEntity(Entity_DirtBombProjectile.class, "DirtBomb", 13, Main.instance, 350, 30, true);
		
	}
	

}
