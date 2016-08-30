package helpertools.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumChatFormatting;
import helpertools.items.Item_Extraction_Balloon;
import helpertools.tools.ItemStaffofExpansion;
import helpertools.tools.ItemStaffofTransformation2;
import helpertools.util.Text;
import ibxm.Player;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/** http://www.minecraftforge.net/wiki/Packet_Handling **/
/** http://www.minecraftforge.net/forum/index.php/topic,20135.0.html	**/
public class Message_Ex implements IMessage {
   
    private String text;
    public int x;
    public int y;
    public int z;

    public Message_Ex() { }

    public Message_Ex(String text, int x, int y, int z) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.z = z;
        
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        text = ByteBufUtils.readUTF8String(buf); 
        x = ByteBufUtils.readVarInt(buf, 5);
        y = ByteBufUtils.readVarInt(buf, 5);
        z = ByteBufUtils.readVarInt(buf, 5);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, text);
        ByteBufUtils.writeVarInt(buf, x, 5);
        ByteBufUtils.writeVarInt(buf, y, 5);
        ByteBufUtils.writeVarInt(buf, z, 5);
    }

    public static class Handler implements IMessageHandler<Message_Ex, IMessage> {
       
        @Override
        public IMessage onMessage(Message_Ex message, MessageContext ctx) {
            //System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().playerEntity.getDisplayName()));
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            ItemStack heldItem = player.inventory.getCurrentItem();
            
            
            	Text.out(message.text, EnumChatFormatting.RESET);
            	
            	int rx = message.x;
            	int ry = message.y;
            	int rz = message.z;
            	           
            
            //if ((heldItem == null) || (!(heldItem.getItem() instanceof Item_Extraction_Balloon))) {
            	
            	
            	Text.out("Messy "+ rx +" " + ry +" " +rz +" ");
            	            
            NBTTagCompound tag = player.getEntityData();
			//NBTBase modeTag = tag.getTag("Balloon_X");				
			tag.setInteger("Balloon_X", rx);
			tag.setInteger("Balloon_Y", ry);
			tag.setInteger("Balloon_Z", rz);
			
			NBTBase xTag = tag.getTag("Balloon_X");
			int ox = ((NBTTagInt)xTag).func_150287_d();
			
			NBTBase yTag = tag.getTag("Balloon_Y");
			int oy = ((NBTTagInt)yTag).func_150287_d();
			
			NBTBase zTag = tag.getTag("Balloon_Z");
			int oz = ((NBTTagInt)zTag).func_150287_d();
			
			
				Text.out("Result: "+ ox + " " + oy + " "+ oz);
             
			
			 return null;
           // return null; // no response in this case
        }
    }
    
    
    

    /**
    Float to string - String.valueOf()

    float amount=100.00f;
    String strAmount=String.valueOf(amount);
    // or  Float.toString(float)

    String to Float - Float.parseFloat()

    String strAmount="100.20";
    float amount=Float.parseFloat(strAmount)
    		// or  Float.valueOf(string)
	**/

}
