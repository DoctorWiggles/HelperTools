package helpertools.Com;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
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

/** http://www.minecraftforge.net/wiki/Packet_Handling **/
/** http://www.minecraftforge.net/forum/index.php/topic,20135.0.html	**/
public class Mirage_Server_Message implements IMessage {
   
    public Mirage_Server_Message() { }
    @Override
    public void fromBytes(ByteBuf buf) {}
    
    @Override
    public void toBytes(ByteBuf buf) {}

    public static class Handler implements IMessageHandler<Mirage_Server_Message, IMessage> {

    	@Override
    	public IMessage onMessage(Mirage_Server_Message message, MessageContext ctx) {

    		final EntityPlayerMP player = ctx.getServerHandler().playerEntity;            
    		if(player == null) return null;

    		World world = player.worldObj;
    		ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
    		if(stack == null) return null;

    		if(stack.getItem() != ItemRegistry.miragehusk) return null;

    		Item_MirageHusk husk = (Item_MirageHusk)stack.getItem();

    		if(!player.isSneaking()){ 	
    			BlockPos pos = husk.Swap_Mirage(stack, player, world);
    			if(pos == null){return null;}
    			Mirage_Client_Message packet = 
    					new Mirage_Client_Message((int)pos.getX(), (int)pos.getY(), (int)pos.getZ());
    			ModUtil.Sound_Server(player, SoundEvents.ENTITY_GHAST_SHOOT, 1.5F, 0.5F);  
    			
    			return packet;
    		}           
    		else{
    			ModUtil.Sound_Server(player, SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.2F, 1.0F);  
    			husk.Create_Mirage(stack, player, world);
    			return null;
    		}
    	}
    }
}
