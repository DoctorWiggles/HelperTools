package helpertools;

import helpertools.blocks.TileEntityTranscriber;
import cpw.mods.fml.common.registry.GameRegistry;

public class HelpertoolsCommonProxy {

        // Serverside stuff
		// Doesn't do anything really
		//But its required.
	
        public void registerRenderers() {
        }

		public void initSounds() {
			// TODO Auto-generated method stub
			
		}
		
		public void registerTileEntities() {
			 
	        GameRegistry.registerTileEntity(TileEntityTranscriber.class, TileEntityTranscriber.publicName);
	    }
		
}