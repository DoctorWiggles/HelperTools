package helpertools.Utils;

import helpertools.Main;

import java.lang.annotation.Inherited;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
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
	public static void whatSides(World world){
		if(world.isRemote){System.out.println("Client");}
		if(!world.isRemote){System.out.println("Server");}
	}
	
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
	
	
	//============================ Sound Routines ==========================//
	public static void playSound(EntityPlayer player,SoundEvent event, Float volume, Float pitch){
		player.worldObj.playSound(player, player.getPosition(), 
				event, SoundCategory.NEUTRAL,volume, pitch);
	}
	public static void Sound_Server(World world,EntityPlayer player,SoundEvent event, Float volume, Float pitch){
		world.playSound(null, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(),
				event, SoundCategory.PLAYERS, volume, pitch);
	}
	public static void Sound_Server(World world, BlockPos pos,SoundEvent event, Float volume, Float pitch){
		world.playSound(null, pos.getX(), pos.getY(), pos.getZ(),
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
	
	
	//========================Particle Routines =====================//
	public static void Particle(Float f, Entity Host, EnumParticleTypes type, double x, double y, double z){
		Random rand = Main.Randy;
		Host.worldObj.spawnParticle(type, 
				Host.posX + (rand.nextDouble() - 0.5D)*f * (double)Host.width, 
				Host.posY + (rand.nextDouble() * (double)Host.height), 
				Host.posZ + (rand.nextDouble() - 0.5D)*f * (double)Host.width,
				x, y, z);
	}
	
	//========================= Spawn Item =============================//
	public static void itemdrop(World world, BlockPos pos,  ItemStack stack){
		if(world.isRemote){return;}
		EntityItem entity = new EntityItem(world, 0, 0, 0, stack);
		entity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
		world.spawnEntityInWorld(entity);
		
	}
	
	public static void itemdrop(World world, BlockPos pos, Block block){
		if(world.isRemote){return;}
		EntityItem entity = new EntityItem(world, 0, 0, 0, new ItemStack(block));
		entity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
		world.spawnEntityInWorld(entity);
		
	}
	
	//========================== Whitelist Util =================================//
	/** Whitelist for block placement **/
	public static boolean isValid(IBlockState state, World world, BlockPos pos){
    	Material matt = state.getMaterial();

    	if(state.getBlock().isAir(state, world, pos)
    			|| state.getBlock() == Blocks.SNOW_LAYER
    			|| matt == Material.FIRE
    			|| matt == Material.WATER
    			|| matt == Material.LAVA
    			|| matt == Material.PLANTS
    			|| matt == Material.VINE){

    		return true;
    	}
    	return false;
    }
	
	
}
