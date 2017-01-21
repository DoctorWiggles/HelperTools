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
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

/** Various shortcut method utilities **/
public class ModUtil {
	
	/** Send a messege to a player.	  
	 * @param living The target to send the messege to
	 * @param text The ITextCompnent (string with optional formatting)
	 */
	public static void print(EntityLivingBase living, TextFormatting format, String text){
		ITextComponent chatmessy = new TextComponentTranslation(format+text, new Object[0]);
		((EntityPlayer)living).addChatComponentMessage(chatmessy);
	}
	public static void print(EntityLivingBase living,String text){
		print(living, TextFormatting.WHITE, text);
	}	
	public static void print(EntityPlayer player, String text){
		print((EntityLivingBase)player, TextFormatting.WHITE, text);
	}
	
	public static void print(EntityPlayer player, TextFormatting format, String text){
		print((EntityLivingBase)player, format, text);
	}
	
	
	
	//============================ Sound Routines ==========================//
	
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
	
	//=============================================================//
	
	public static TargetPoint Message_Point(EntityPlayer player, int range){
		BlockPos pos = player.getPosition();
		WorldProvider provider= player.worldObj.provider;
		NetworkRegistry.TargetPoint point =
				new TargetPoint(provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), range);
		return point;
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
	/** Drop an itemstack **/
	public static void itemdrop(World world, BlockPos pos,  ItemStack stack){
		if(world.isRemote){return;}
		EntityItem entity = new EntityItem(world, 0, 0, 0, stack);
		entity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
		world.spawnEntityInWorld(entity);
		
	}
	/** Drop an item block **/
	public static void itemdrop(World world, BlockPos pos, Block block){
		if(world.isRemote){return;}
		EntityItem entity = new EntityItem(world, 0, 0, 0, new ItemStack(block));
		entity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
		world.spawnEntityInWorld(entity);
		
	}
	/** Drop an a block in the world as an item **/
	public static void dropblock(World world, BlockPos pos){
		
		IBlockState state = world.getBlockState(pos);		
		state.getBlock().dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
	}
	
	//========================== Whitelist Util =================================//
	/** Whitelist for block placement **/
	public static boolean isValid(World world, BlockPos pos){
		IBlockState state = world.getBlockState(pos);
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
	
	public static void Destructables(World world, BlockPos pos){
		IBlockState State = world.getBlockState(pos);
		
		if (State.getMaterial() == Material.VINE
				|| State.getMaterial() == Material.PLANTS
				|| State.getBlock() == Blocks.SNOW_LAYER) 
		{

			(State.getBlock()).dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
		}  

	}
	
	public static boolean isAir(World world, IBlockState state, BlockPos pos){
		
		return state.getBlock().isAir(state, world, pos);
	}


}
