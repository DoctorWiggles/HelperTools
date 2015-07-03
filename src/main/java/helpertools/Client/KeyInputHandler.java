package helpertools.Client;

import helpertools.Main;
import helpertools.Common.NetworkMessage;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

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