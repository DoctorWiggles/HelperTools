package helpertools.Utils;

import java.lang.annotation.Inherited;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

/** Consolidating class to simplify the text process **/
public class Texty {
	/*
	public void print(EntityLivingBase living, String Messy){
		ITextComponent chatmessy = new TextComponentTranslation(TextFormatting.GRAY + Messy, new Object[0]);
		((EntityPlayer)living).addChatComponentMessage(chatmessy);
	}
	*/
	/** Send a messege to a player.	  
	 * @param living The target to send the messege to
	 * @param text The ITextCompnent (string with optional formatting)
	 */
	public static void print(EntityLivingBase living, String text){
		ITextComponent chatmessy = new TextComponentTranslation(text, new Object[0]);
		((EntityPlayer)living).addChatComponentMessage(chatmessy);
	}
	
	public static void print(EntityPlayer player, String text){
		print((EntityLivingBase)player, text);
	}
	
	public static void print(String text){
		System.out.println(text);
	}
	
	public static void playSound(EntityPlayer player,SoundEvent event, Float volume, Float pitch){
		player.worldObj.playSound(player, player.getPosition(), 
				event, SoundCategory.NEUTRAL,volume, pitch);
	}
	
}
