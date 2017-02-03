package helpertools.Com;

import helpertools.Main;
import helpertools.Com.Entity.Projectile_Bomb;
import helpertools.Com.Entity.Projectile_Dynamite;
import helpertools.Com.Entity.Entity_FlyingItem;
import helpertools.Com.Entity.Entity_Mirage;
import helpertools.Com.Entity.Projectile_RedTorch;
import helpertools.Com.Entity.Projectile_Torch;
import helpertools.Com.Entity.Renders.Render_DynamiteProjectile;
import helpertools.Com.Entity.Renders.Render_RedTorchProjectile;
import helpertools.Com.Entity.Renders.Render_TorchProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class Registry_Entity {
	
	
	
	public static void register_entities(){
		
		 reg(Projectile_Torch.class, "TorchBolt", 10);
		 reg(Projectile_RedTorch.class, "RedTorchBolt", 11);
		 reg(Projectile_Dynamite.class, "DynamiteBolt", 12);
		 reg(Projectile_Bomb.class, "DirtBomb", 13);
		
		 reg(Entity_FlyingItem.class, "FlyingItem", 14, 010201,327238 );		 
		 reg(Entity_Mirage.class, "Mirage", 15);
	}
	
	
	public static void reg (Class<? extends Entity> entityClass, String entityName, int id){
		EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID, entityName),
				entityClass, entityName, id, Main.instance, 350, 10, true);	
	}
	public static void reg (Class<? extends Entity> entityClass, String entityName, int id, int egg, int eggspots){
		EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID, entityName),
				entityClass, entityName, id, Main.instance, 350, 1, true, egg, eggspots);	
	}

}
