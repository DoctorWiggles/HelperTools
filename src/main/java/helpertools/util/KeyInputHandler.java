package helpertools.util;

import helpertools.Helpertoolscore;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

/** http://www.minecraftforge.net/wiki/Key_Binding **/
public class KeyInputHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) { 
    	//Alternate o
        if(KeyBindings.ding.isPressed()){
            System.out.println("Alternate o"); 
            Helpertoolscore.network.sendToServer(new NetworkMessage("foobar"));
            }
        //Primary p
        if(KeyBindings.dong.isPressed()){
            System.out.println("Primary p");
        }
    }

}