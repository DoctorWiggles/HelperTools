package helpertools.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class Gui_Handler implements IGuiHandler{
	
	
	 public static final int Extraction_Selection_Gui = 1;
	
	 	//registering gui objects to sides
	 	//things like inventories need to be fired on the server and client
	 	//gui's without inventores will fail if fired off on server (null pointer)
	 
	 	@Override
	    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		 //pass down and along to open right gui during element fetch
	 		/**
	 		if (ID == Extraction_Selection_Gui)
	            return new Gui_Extraction_Selection();
	           **/
		 
		 return null;
	    }

	    @Override
	    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	    	if (ID == Extraction_Selection_Gui)
	    		//default simple gui
	            //return new Gui_Extraction_Selection();
	    		
	    		//adding constructors to utilize references
	    		return new Gui_Extraction_Selection(player, world, x, y, z);
	    	
	    	return null;
	    }

}
