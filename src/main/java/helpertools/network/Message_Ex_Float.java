package helpertools.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
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

public class Message_Ex_Float implements IMessage {
   
    private String text;
    private float floatu;

    public Message_Ex_Float() { }

    /*
    public Message_Ex_Float(String text) {
        this.text = text;
        
    }
    */
    
    public Message_Ex_Float(float f1) {
        this.floatu = f1;
        
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        //text = ByteBufUtils.readUTF8String(buf); 
        floatu = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
       // ByteBufUtils.writeUTF8String(buf, text);
        buf.writeFloat(floatu);
    }

    public static class Handler implements IMessageHandler<Message_Ex_Float, IMessage> {
       
        @Override
        public IMessage onMessage(Message_Ex_Float message, MessageContext ctx) {
            //System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().playerEntity.getDisplayName()));
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            ItemStack heldItem = player.inventory.getCurrentItem();
            
            
            	//Text.out("Float network: " + message.text, EnumChatFormatting.RESET);
            	           
          // float amount = Float.parseFloat(message.text);
           
            //if ((heldItem == null) || (!(heldItem.getItem() instanceof Item_Extraction_Balloon))) {
            	
          // Text.out("Parse Float: " + amount);
            	            
            NBTTagCompound tag = player.getEntityData();
			//NBTBase modeTag = tag.getTag("Balloon_X");				
			tag.setFloat("Balloon_F", message.floatu);
			
			NBTBase FTag = tag.getTag("Balloon_F");
			float floaty = ((NBTTagFloat)FTag).func_150288_h();
			
			
				Text.out("Player Float: " + floaty);
             
			
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
