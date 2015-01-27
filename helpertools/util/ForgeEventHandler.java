package helpertools.util;
import net.minecraft.util.ChatComponentTranslation;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class ForgeEventHandler {
  
	//Usefull debugging tool
	//Stick into an action like below or around ticking things to spot pesky problems.
	/**
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerLoggedInEvent event){
    
        ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("Hello world!", new Object[0]);
   	 event.player.addChatComponentMessage(chatcomponenttranslation);    
      
    }
    **/
	//////////////
}