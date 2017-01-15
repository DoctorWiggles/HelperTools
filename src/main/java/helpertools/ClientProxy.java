package helpertools;

import helpertools.blocks.tile_entities.TileEntityObelisk;
import helpertools.entities.Bolt_Projectile;
import helpertools.entities.Bomb_Projectile;
import helpertools.entities.Dynamite_Projectile;
import helpertools.entities.RedTorch_Projectile;
import helpertools.entities.Torch_Projectile;
import helpertools.renders.BlockRenderObelisk;
import helpertools.renders.BombProjectile_Render;
import helpertools.renders.Render_EuclideanStaff;
import helpertools.renders.Render_ExpansionStaff;
import helpertools.renders.Render_TransformationStaff;
import helpertools.renders.Render_TorchLauncher;
import helpertools.renders.RenderBoltProjectile;
import helpertools.renders.RenderDynamiteProjectile;
import helpertools.renders.RenderRedTorchProjectile;
import helpertools.renders.RenderTorchProjectile;
import helpertools.util.Armor_Render_Handler;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {
	
	//Class Cof = ConfigurationFactory.class;
	
		
	@SideOnly(Side.CLIENT)
    @Override
	public void registerRenderers() {
        	
        // This is for rendering entities and so forth later on
        	//Attaches the rendering code to entities
		Main.logger.info("Registering Renders");
        	
		//configuration call
		if(ConfigurationFactory.Render3DStaffModels == true){
		MinecraftForgeClient.registerItemRenderer(ModRegistry.staffofexpansion, new Render_ExpansionStaff());              
        MinecraftForgeClient.registerItemRenderer(ModRegistry.staffoftransformation, new Render_TransformationStaff());
        MinecraftForgeClient.registerItemRenderer(ModRegistry.euclideantransposer, new Render_EuclideanStaff());
        		}
		
        if(ConfigurationFactory.Render3DCrossbowModel == true){       
        MinecraftForgeClient.registerItemRenderer(ModRegistry.torchlauncher,new Render_TorchLauncher());
        }
        
        super.registerRenderers();    
        RenderingRegistry.registerEntityRenderingHandler(Torch_Projectile.class, new RenderTorchProjectile());
        //
        RenderingRegistry.registerEntityRenderingHandler(RedTorch_Projectile.class, new RenderRedTorchProjectile());
        //
        RenderingRegistry.registerEntityRenderingHandler(Dynamite_Projectile.class, new RenderDynamiteProjectile());
        //
        RenderingRegistry.registerEntityRenderingHandler(Bolt_Projectile.class, new RenderBoltProjectile());
        
        
        RenderingRegistry.registerEntityRenderingHandler(Bomb_Projectile.class, new BombProjectile_Render(1));
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