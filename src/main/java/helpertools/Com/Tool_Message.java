package helpertools.Com;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import helpertools.Com.Tools.Staff_EuclideanTransposer;
import helpertools.Com.Tools.Staff_Expansion;
import helpertools.Com.Tools.Staff_Transformation;
import helpertools.Com.Tools.ToolBase;
import helpertools.Utils.ModUtil;
import ibxm.Player;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/** http://www.minecraftforge.net/wiki/Packet_Handling **/
/** http://www.minecraftforge.net/forum/index.php/topic,20135.0.html	**/
public class Tool_Message implements IMessage {
   
    private String text;

    public Tool_Message() { }

    public Tool_Message(String text) {
        this.text = text;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        text = ByteBufUtils.readUTF8String(buf); 
        // this class is very useful in general for writing more complex objects
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, text);
    }

    public static class Handler implements IMessageHandler<Tool_Message, IMessage> {
       
        @Override
        public IMessage onMessage(Tool_Message message, MessageContext ctx) {
            //System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().playerEntity.getDisplayName()));
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            //ItemStack heldItem = theplayer.inventory.getCurrentItem();
            ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
            
            if((heldItem == null) || (!(heldItem.getItem() instanceof ToolBase))){            	
            	heldItem = player.getHeldItem(EnumHand.OFF_HAND);
            	if(heldItem == null || (!(heldItem.getItem() instanceof ToolBase)))
            		return null;
            }
            
            if(heldItem.getItem() instanceof Staff_Expansion){
            	Staff_Expansion  Tool = (Staff_Expansion)heldItem.getItem();
            	Tool.ToolEmpower(heldItem, player);
            	return null;
            }
            
            if(heldItem.getItem() instanceof Staff_Transformation){
            	Staff_Transformation  Tool = (Staff_Transformation)heldItem.getItem();
            	Tool.ToolEmpower(heldItem, player);
            	return null;
            }
            
            if(heldItem.getItem() instanceof Staff_EuclideanTransposer){
            	Staff_EuclideanTransposer  Tool = (Staff_EuclideanTransposer)heldItem.getItem();
            	Tool.rotateCorner(heldItem, player);
            	return null;
            }
            
  		 
            return null; // no response in this case
        }
    }
}

	// Sending packets:
	//MyMod.network.sendToServer(new MyMessage("foobar"));
	//MyMod.network.sendTo(new SomeMessage(), somePlayer);