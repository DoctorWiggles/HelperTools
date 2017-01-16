package helpertools.util;

import helpertools.Main;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

/** http://www.minecraftforge.net/wiki/Key_Binding **/
public class KeyInputHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) { 
    	//Primary " O "
        if(KeyBindings.ding.isPressed()){
            //System.out.println("Primary"); 
            Main.network.sendToServer(new NetworkMessage("Primary"));
            }
        /**
        //Primary p
        if(KeyBindings.dong.isPressed()){
            System.out.println("Primary p");
        }
        **/
    }

}