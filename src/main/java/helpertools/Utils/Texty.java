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
import net.minecraft.world.World;

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
	public static void Sound_Server(World world,EntityPlayer player,SoundEvent event, Float volume, Float pitch){
		world.playSound(null, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(),
				event, SoundCategory.PLAYERS, volume, pitch);
	}
	public static void Sound_Blocks(World world,EntityPlayer player,SoundEvent event, Float volume, Float pitch){
		world.playSound(null, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(),
				event, SoundCategory.BLOCKS, volume, pitch);
	}
	public static void Sound_Server(EntityPlayer player,SoundEvent event, Float volume, Float pitch){
		Sound_Server(player.worldObj, player, event, pitch, pitch);
	}
	public static void Sound_Server(EntityLivingBase living,SoundEvent event, Float volume, Float pitch){
		Sound_Server(living.worldObj, (EntityPlayer)living, event, pitch, pitch);
	}
}
