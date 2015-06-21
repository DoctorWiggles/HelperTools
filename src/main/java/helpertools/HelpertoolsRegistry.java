package helpertools;

import helpertools.blocks.tile_entities.TileEntityIllusion;
import helpertools.blocks.tile_entities.TileEntityObelisk;
import helpertools.blocks.tile_entities.TileEntityTranscriber;
import helpertools.entities.EntityBoltProjectile;
import helpertools.entities.EntityDirtBombProjectile;
import helpertools.entities.EntityDynamiteProjectile;
import helpertools.entities.EntityRedTorchProjectile;
import helpertools.entities.EntityTorchProjectile;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class HelpertoolsRegistry extends Helpertoolscore{
	
	public static void RegistersomeThings(FMLInitializationEvent event){
		
		 
		 
        ////////////////
     	 /**Registries**/
     	////////////////
		
          
          //Items - Items - Items - Items - Items - Items - Items
          ///////////////////////////////////////////////////////
       	
          GameRegistry.registerItem(Helpertoolscore.staffofexpansion, "staffofexpansion");               
          GameRegistry.registerItem(Helpertoolscore.staffoftransformation2, "staffoftransformation2");                
          GameRegistry.registerItem(Helpertoolscore.torchlauncher, "torchlauncher");                
          GameRegistry.registerItem(Helpertoolscore.euclideantransposer, "euclideantransposer");
       
          GameRegistry.registerItem(Helpertoolscore.debugtool, "debugtool");
          //GameRegistry.registerItem(Helpertoolscore.rfdebugtool, "rfdebugtool");
          //LanguageRegistry.addName(Helpertoolscore.debugtool, "Debugging Tool");
          
         
          
          //Consumable Items//
          ////////////////////
          GameRegistry.registerItem(Helpertoolscore.dynamitebolt, "dynamitebolt");
          
          GameRegistry.registerItem(Helpertoolscore.dirtbomb, "dirtbomb");
          
          GameRegistry.registerItem(Helpertoolscore.bottledmilk, "bottledmilk");
          GameRegistry.registerItem(Helpertoolscore.chocolatemilk, "chocolatemilk");
          //GameRegistry.registerItem(chocolatemilk = new ItemChocolateMilk( 2, 0.2f, false ).setAlwaysEdible(), "chocolatemilk");
          //GameRegistry.registerItem(Helpertoolscore.powercrystal, "powercrystal");
          
          //Blocks - Blocks - Blocks - Blocks - Blocks - Blocks
          //////////////////////////////////////////////////////
          GameRegistry.registerBlock(Ibedrock, "Imitation Bedrock");
          GameRegistry.registerBlock(StokedPipe, "StokedPipe");
          //GameRegistry.registerBlock(StokedBlock, "StokedBlock");
          GameRegistry.registerBlock(TranscriberBlock, "TranscriberBlock");
          GameRegistry.registerBlock(MagicalFuelBlock, "MagicalFuelBlock");
          GameRegistry.registerBlock(ActiveMagicalFuelBlock, "ActiveMagicalFuelBlock");
          GameRegistry.registerBlock(LooseDirtBlock,  "LooseDirtBlock");
          //
          GameRegistry.registerBlock(IllusionBlock, "IllusionBlock");
          //
          GameRegistry.registerBlock(SugarBlock, "SugarBlock");
          GameRegistry.registerBlock(TransitionGlass, "TransitionGlass");
          GameRegistry.registerBlock(ObeliskBlock, "ObeliskBlock");
         
          
          //Entities - Entities - Entities - Entities - Entities - Entities
          /////////////////////////////////////////////////////////////////
          EntityRegistry.registerModEntity(EntityTorchProjectile.class, "TorchBolt", 10, instance, 350, 10, true);
          EntityRegistry.registerModEntity(EntityRedTorchProjectile.class, "RedTorchBolt", 11, instance, 350, 10, true);
          EntityRegistry.registerModEntity(EntityDynamiteProjectile.class, "DynamiteBolt", 12, instance, 350, 30, true);
                          
          EntityRegistry.registerModEntity(EntityBoltProjectile.class, "CrossBolt", 13, instance, 350, 10, true);
          EntityRegistry.registerModEntity(EntityDirtBombProjectile.class, "DirtBomb", 14, instance, 350, 30, true);
          //class, stringname, entity ID, thisobject, loading range, update polls, velocity updates
          
         
 			//Tile Entities - Tile Entities - Tile Entities - Tile Entities
          //////////////////////////////////////////////////////////////////////////////////////////////
          
  	    GameRegistry.registerTileEntity(TileEntityTranscriber.class, TileEntityTranscriber.publicName);
  	    GameRegistry.registerTileEntity(TileEntityIllusion.class, TileEntityIllusion.publicName);
  	    GameRegistry.registerTileEntity(TileEntityObelisk.class, TileEntityObelisk.publicName);
  	    
	}

}
