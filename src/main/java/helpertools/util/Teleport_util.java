package helpertools.util;

import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class Teleport_util extends Entity{
	
	
	public Teleport_util(World p_i1582_1_) {
		super(p_i1582_1_);
		// TODO Auto-generated constructor stub
	}

	
	//wait a second here.....
	public void move_to_dim(Entity entity, int dim_id){
		
		MinecraftServer minecraftserver = MinecraftServer.getServer();
		int j = entity.dimension;
        WorldServer origin_world = minecraftserver.worldServerForDimension(j);
        WorldServer target_world = minecraftserver.worldServerForDimension(dim_id);
        
        entity.worldObj.removeEntity(entity);
        entity.isDead = false;
        
        Entity entity_2 = entity;
        
        if (entity != null)
        {
        	
        entity_2.copyDataFrom(entity, true);
        }
        target_world.spawnEntityInWorld(entity_2);
		
	}
	
	public static Entity move_to_dim2(Entity entity, int dim_id){
		
		
		if (!entity.worldObj.isRemote)
        {
		MinecraftServer minecraftserver = MinecraftServer.getServer();
		int j = entity.dimension;
        WorldServer origin_world = minecraftserver.worldServerForDimension(j);
        WorldServer target_world = minecraftserver.worldServerForDimension(dim_id);
        
        entity.worldObj.removeEntity(entity);
        entity.isDead = false;
        
        Entity entity_2 = entity;
        
        if (entity != null)
        {
        	
        entity_2.copyDataFrom(entity, true);
        }
        target_world.spawnEntityInWorld(entity_2);
		return entity_2;
        }
		return entity;
		
	}
	
	
	
	/*
	 *  MinecraftServer minecraftserver = MinecraftServer.getServer();
         int j = this.dimension;
         WorldServer worldserver = minecraftserver.worldServerForDimension(j);
         WorldServer worldserver1 = minecraftserver.worldServerForDimension(dim_id);
         
         minecraftserver.getConfigurationManager().transferEntityToWorld(this, j, worldserver, worldserver1);
	*/
	

}
