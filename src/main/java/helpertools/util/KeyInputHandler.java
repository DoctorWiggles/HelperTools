package helpertools.util;

import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

/** http://www.minecraftforge.net/wiki/Key_Binding **/
public class KeyInputHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {    	    	
        if(KeyBindings.ding.isPressed()){
            System.out.println("ding"); 
            }
        if(KeyBindings.dong.isPressed()){
            System.out.println("dong");
        }
    }

}