package helpertools;

import helpertools.blocks.tile_entities.TileEntityObelisk;
import helpertools.entities.BombProjectile_Entity;
import helpertools.entities.EntityBoltProjectile;
import helpertools.entities.EntityDirtBombProjectile;
import helpertools.entities.EntityDynamiteProjectile;
import helpertools.entities.EntityGravelBombProjectile;
import helpertools.entities.EntityRedTorchProjectile;
import helpertools.entities.EntitySandBombProjectile;
import helpertools.entities.EntityTorchProjectile;
import helpertools.renders.BlockRenderObelisk;
import helpertools.renders.BombProjectile_Render;
import helpertools.renders.ItemRenderEuclidean1;
import helpertools.renders.ItemRenderStaff4;
import helpertools.renders.ItemRenderStaff5;
import helpertools.renders.ItemRenderTorchLauncher1;
import helpertools.renders.RenderBoltProjectile;
import helpertools.renders.RenderDirtBombProjectile;
import helpertools.renders.RenderDynamiteProjectile;
import helpertools.renders.RenderGravelBombProjectile;
import helpertools.renders.RenderRedTorchProjectile;
import helpertools.renders.RenderSandBombProjectile;
import helpertools.renders.RenderTorchProjectile;
import helpertools.util.Armor_Render_Handler;
import helpertools.util.Ghostblock_Handler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

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
        RenderingRegistry.registerEntityRenderingHandler(EntitySandBombProjectile.class, new RenderSandBombProjectile(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityGravelBombProjectile.class, new RenderGravelBombProjectile(1));
        
        
        
        RenderingRegistry.registerEntityRenderingHandler(BombProjectile_Entity.class, new BombProjectile_Render(1));
        //
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityObelisk.class, new BlockRenderObelisk());
     
	
	
        //Mask Rendering
		MinecraftForge.EVENT_BUS.register(new Armor_Render_Handler());
		
		
	
	
	}
	
	
		
	
		
		
	
	
       
        
        //
        @Override
        public void initSounds(){
        	
        			
        }
}