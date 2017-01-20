package helpertools.Client;

import helpertools.Main;
import helpertools.Com.Mirage_Server_Message;
import helpertools.Com.Tool_Message;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/** http://www.minecraftforge.net/wiki/Key_Binding **/
public class KeyInput_Handler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) { 
    	//Tool Functions "o"
        if(Key_Bindings.ding.isPressed()){
            //System.out.println("Primary"); 
            Main.network.sendToServer(new Tool_Message("Primary"));
            }
        
        //Other Functions "p"
        if(Key_Bindings.dong.isPressed()){
        	
        	Main.network.sendToServer(new Mirage_Server_Message());
        }
        
    }

}