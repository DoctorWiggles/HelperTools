package helpertools.Com;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import helpertools.Main;
import helpertools.Com.Entity.Entity_Mirage;
import helpertools.Com.Items.Item_MirageHusk;
import helpertools.Com.Tools.Staff_Expansion;
import helpertools.Com.Tools.Staff_Transformation;
import helpertools.Utils.ModUtil;
import ibxm.Player;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class Mirage_Client_Message implements IMessage {
   
	int x;
	int y;
	int z;
	
	public Mirage_Client_Message() {
    }
	
    public Mirage_Client_Message(int x, int y, int z) {
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
    
    //Dummy server handler to prevent server implosions
    public static class Dummy_Handler implements IMessageHandler<Mirage_Client_Message, IMessage>
    {
      public IMessage onMessage(Mirage_Client_Message message, MessageContext ctx) {
        return null;
      }
    }
    //The real handler for the client side
    public static class Handler implements IMessageHandler<Mirage_Client_Message, IMessage> {
       
        @Override
        public IMessage onMessage(Mirage_Client_Message mess, MessageContext ctx) {
        	 	
            EntityPlayer player = Minecraft.getMinecraft().player;
            if(player == null) return null;
            
            int x = mess.x;
            int y = mess.y;
            int z = mess.z;
            
            player.setLocationAndAngles(
            		x+0.5, y, z+0.5, player.rotationYaw, player.rotationPitch );
            for (int i = 0; i < (Main.Randy.nextInt(50)*2)+20; ++i)
        		ModUtil.Particle(2F, player, EnumParticleTypes.SMOKE_NORMAL,0,0,0);
            
            return null; 
        }
    }
}
