package helpertools.Client;
import org.lwjgl.input.Keyboard;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

/** http://www.minecraftforge.net/wiki/Key_Binding **/
public class KeyBindings {

    // Declare two KeyBindings, ping and pong
    public static KeyBinding ding;
    /**
    public static KeyBinding dong;
    **/

    public static void init() {
        // Define the "ping" binding, with (unlocalized) name "key.ping" and
        // the category with (unlocalized) name "key.categories.mymod" and
        // key code 24 ("O", LWJGL constant: Keyboard.KEY_O)
        ding = new KeyBinding("key.ding", Keyboard.KEY_O, "key.categories.helpertools");
        
        /**
        // Define the "pong" binding, with (unlocalized) name "key.pong" and
        // the category with (unlocalized) name "key.categories.mymod" and
        // key code 25 ("P", LWJGL constant: Keyboard.KEY_P)
        dong = new KeyBinding("key.dong", Keyboard.KEY_P, "key.categories.helpertools");
         **/
        // Register both KeyBindings to the ClientRegistry
        ClientRegistry.registerKeyBinding(ding);
        /**
        ClientRegistry.registerKeyBinding(dong);
        **/
    }

}
