package helpertools.entities;

import helpertools.Main;

import java.util.Random;
import java.util.Stack;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Splash_MilkBottle extends EntityThrowable{

   public Splash_MilkBottle(World par1World) {
       super(par1World);
   }

   public Splash_MilkBottle(World par2World, EntityPlayer par3EntityPlayer) {
       super(par2World,par3EntityPlayer);
   }

   @Override
   protected void entityInit() {

   }
   //spawns particle effects if enabled
   public void onUpdate()
   {
	   int i;
       super.onUpdate();
       for (i = 0; i < 1; ++i)
       {
    	   this.worldObj.spawnParticle("smoke", this.posX + this.motionX * (double)i / 4.0D, this.posY + .8+ this.motionY * (double)i / 4.0D, this.posZ + this.motionZ * (double)i / 4.0D, 0, 0 , 0);
           this.worldObj.spawnParticle("cloud", this.posX + this.motionX * (double)i / 4.0D, this.posY + .8+ this.motionY * (double)i / 4.0D, this.posZ + this.motionZ * (double)i / 4.0D, 0, .1 , 0);
       }   
   }
   //uniqueness for each projectile entity
   byte Flavor = 0;
   //0 designate vailla
   //1 is chocolate
   //Called later to change rendering.
   
   @Override
   public void readEntityFromNBT(NBTTagCompound nbt) {
	   
	   nbt.setByte("Flavor", this.Flavor);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbt) {
	   
	   this.Flavor = nbt.getByte("Flavor");
   }
   
   //protected Random rand;
   protected static Random growrand = new Random();
   
   @Override
  protected void onImpact(MovingObjectPosition mop) {
	   
      this.setDead();
	  
   }
  
}