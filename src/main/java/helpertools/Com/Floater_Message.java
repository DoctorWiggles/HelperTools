package helpertools.Com;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import helpertools.Com.Blocks.Block_Floater;
import helpertools.Com.Tools.Staff_EuclideanTransposer;
import helpertools.Com.Tools.Staff_Expansion;
import helpertools.Com.Tools.Staff_Transformation;
import helpertools.Com.Tools.ToolBase;
import helpertools.Utils.InventoryUtil;
import helpertools.Utils.ModUtil;
import ibxm.Player;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/** http://www.minecraftforge.net/wiki/Packet_Handling **/
/** http://www.minecraftforge.net/forum/index.php/topic,20135.0.html	**/
public class Floater_Message implements IMessage {
   

	int x;
	int y;
	int z;
	
	public Floater_Message() {
    }
	
    public Floater_Message(int x, int y, int z) {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
    	x = buf.readInt();
    	y = buf.readInt();
    	z = buf.readInt();
    	
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
    	buf.writeInt(x);
    	buf.writeInt(y);
    	buf.writeInt(z);    
    }

    public static class Handler implements IMessageHandler<Floater_Message, IMessage> {
       
        @Override
        public IMessage onMessage(Floater_Message message, MessageContext ctx) {
           
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
           
            World world = player.world;
            BlockPos pos = new BlockPos(message.x,message.y,message.z);
            
            if(Block_Floater.FloaterBlock_Item.Place_Floater(world.getBlockState(pos), world, pos)){
				if(!player.capabilities.isCreativeMode)
				InventoryUtil.consumeItem(ItemRegistry.FloaterBlock_Item, player);}
            
            
            
            return null; // no response in this case
        }
    }
}

	// Sending packets:
	//MyMod.network.sendToServer(new MyMessage("foobar"));
	//MyMod.network.sendTo(new SomeMessage(), somePlayer);