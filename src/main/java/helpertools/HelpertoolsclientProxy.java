package helpertools;

import helpertools.blocks.tile_entities.TileEntityObelisk;
import helpertools.entities.EntityBoltProjectile;
import helpertools.entities.EntityDirtBombProjectile;
import helpertools.entities.EntityDynamiteProjectile;
import helpertools.entities.EntityRedTorchProjectile;
import helpertools.entities.EntityTorchProjectile;
import helpertools.renders.BlockRenderObelisk;
import helpertools.renders.ItemRenderEuclidean1;
import helpertools.renders.ItemRenderStaff4;
import helpertools.renders.ItemRenderStaff5;
import helpertools.renders.ItemRenderTorchLauncher1;
import helpertools.renders.RenderBoltProjectile;
import helpertools.renders.RenderDirtBombProjectile;
import helpertools.renders.RenderDynamiteProjectile;
import helpertools.renders.RenderRedTorchProjectile;
import helpertools.renders.RenderTorchProjectile;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.client.MinecraftForgeClient;

public class HelpertoolsclientProxy extends HelpertoolsCommonProxy {
	
	//Class Cof = ConfigurationFactory.class;
		
	@SideOnly(Side.CLIENT)
    @Override
	public void registerRenderers() {
        	
        // This is for rendering entities and so forth later on
        	//Attaches the rendering code to entities
		Helpertoolscore.logger.info("Registering Renders");
        	
		//configuration call
		if(ConfigurationFactory.Render3DStaffModels == true){
		MinecraftForgeClient.registerItemRenderer(Common_Registry.staffofexpansion, new ItemRenderStaff4());              
        MinecraftForgeClient.registerItemRenderer(Common_Registry.staffoftransformation2, new ItemRenderStaff5());
        MinecraftForgeClient.registerItemRenderer(Common_Registry.euclideantransposer, new ItemRenderEuclidean1());
        		}
		
        if(ConfigurationFactory.Render3DCrossbowModel == true){       
        MinecraftForgeClient.registerItemRenderer(Common_Registry.torchlauncher,new ItemRenderTorchLauncher1());
        }
        
        super.registerRenderers();    
        RenderingRegistry.registerEntityRenderingHandler(EntityTorchProjectile.class, new RenderTorchProjectile());
        //
        RenderingRegistry.registerEntityRenderingHandler(EntityRedTorchProjectile.class, new RenderRedTorchProjectile());
        //
        RenderingRegistry.registerEntityRenderingHandler(EntityDynamiteProjectile.class, new RenderDynamiteProjectile());
        //
        RenderingRegistry.registerEntityRenderingHandler(EntityBoltProjectile.class, new RenderBoltProjectile());
        //

        RenderingRegistry.registerEntityRenderingHandler(EntityDirtBombProjectile.class, new RenderDirtBombProjectile(1));
        //
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityObelisk.class, new BlockRenderObelisk());
     }
	
	
	
	
		
		
	
	
       
        
        //
        @Override
        public void initSounds(){
        	
        			
        }
}