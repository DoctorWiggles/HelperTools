package helpertools.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

/** Debugging Helper **/
public class Text {
	
	public static void out (String string){	
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		Text.out(player, string, EnumChatFormatting.WHITE);			
	}
	
	public static void out (Object obj){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if (obj == null){
			Text.out(player, "NULL OBJECT", EnumChatFormatting.RED);	
			return;}
		
		String string = obj.toString();
		Text.out(player, string, EnumChatFormatting.WHITE);			
	}
	
	public static void out (Object obj, EnumChatFormatting EnumChatFormatting){	
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if (obj == null){
			Text.out(player, "NULL OBJECT", EnumChatFormatting.RED);	
			return;}
		String string = obj.toString();
		Text.out(player, string, EnumChatFormatting);			
	}
	
	/** Final Method **/
	public static void out (EntityPlayer player, String string, EnumChatFormatting EnumChatFormatting){		
		ChatComponentTranslation text = new ChatComponentTranslation(EnumChatFormatting + string, new Object[0]);
		player.addChatComponentMessage(text);				
	}
	
	public static void out (EntityPlayer player, String string){		
		Text.out(player, string, EnumChatFormatting.WHITE);			
	}
	
	
	
	public static void out (EntityPlayer player, Object obj, EnumChatFormatting EnumChatFormatting){
		String string = obj.toString();
		Text.out(player, string, EnumChatFormatting);		
	}
	
	public static void out (EntityPlayer player, Object obj){		
		String string = obj.toString();
		Text.out(player, string);			
	}

}
