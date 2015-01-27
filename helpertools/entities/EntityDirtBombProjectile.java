package helpertools.entities;

import helpertools.Helpertoolscore;

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

public class EntityDirtBombProjectile extends EntityThrowable{

   public EntityDirtBombProjectile(World par1World) {
       super(par1World);
   }

   public EntityDirtBombProjectile(World par2World, EntityPlayer par3EntityPlayer) {
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
           //this.worldObj.spawnParticle("crit", this.posX + this.motionX * (double)i / 4.0D, this.posY + this.motionY * (double)i / 4.0D, this.posZ + this.motionZ * (double)i / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
       }   
   }
   //uniqueness for each projectile entity
   @Override
   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {

   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {

   }
   
   //protected Random rand;
   protected static Random growrand = new Random();
   
   @Override
  protected void onImpact(MovingObjectPosition mop) {
      
	   if(!this.worldObj.isRemote){
	  //The block it lands on in the world
      Block theblock = worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
      //The torch block to be placed in the world
      Block pblock = Helpertoolscore.LooseDirtBlock;
      
      //can be called to simplify things
      //int i = mop.blockX;
      //int j = mop.blockY;
      //int k = mop.blockZ;
      
      if (mop.entityHit != null )
      {
    	 return;
      }
      
      //once it has hit anything
      //create and explosion
      //this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)1, true);

      //this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)1.3, true);
      this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)1.9, false);
      
      int P = 3;
      int i1 = mop.blockX-1;
      int j1 = mop.blockY;
      int k1 = mop.blockZ-2;
      
      int i2 = mop.blockX-2;
      int j2 = mop.blockY+1;
      int k2 = mop.blockZ-1;
      
      int i3 = mop.blockX-1;
      int j3 = mop.blockY+1;
      int k3 = mop.blockZ-1;
      ////////////////
      
  		for (int G2 = 0; G2 < 5; ++G2)
  		{
  			//int G2counter = G2*P*P;
  			for (int U = 0; U < 3; ++U)
  			{
  				//int Ucounter = U*P;
  				for (int l = 0; l <1; ++l)
  				{
  				//i1+U, j1+1+l, k1+G2
  					int ig = growrand.nextInt(6);
  					if (ig >= 2){
      if(worldObj.getBlock(i1+U, j1+1+l, k1+G2) == Blocks.air
    	  
    	  ||  worldObj.getBlock(i1+U, j1+1+l, k1+G2).getMaterial()== Material.plants
    	  ||  worldObj.getBlock(i1+U, j1+1+l, k1+G2).getMaterial()== Material.lava
    	  ||  worldObj.getBlock(i1+U, j1+1+l, k1+G2).getMaterial()== Material.water)
      {
    	  this.worldObj.setBlock(i1+U, j1+1+l, k1+G2, pblock);  
      }
      else {
    	  pblock.dropBlockAsItem(worldObj,i1+U, j1+1+l, k1+G2, 0, 0);
      }
  					}
  			}}}
  		
  		///////
  		
  		for (int G2 = 0; G2 < 3; ++G2)
  		{
  			//int G2counter = G2*P*P;
  			for (int U = 0; U < 5; ++U)
  			{
  				//int Ucounter = U*P;
  				for (int l = 0; l <1; ++l)
  				{
  				//i2+U, j2+1+l, k2+G2
  					int ig = growrand.nextInt(6);
  					if (ig >= 2){
      if(worldObj.getBlock(i2+U, j2+1+l, k2+G2) == Blocks.air
    	  
    	  ||  worldObj.getBlock(i2+U, j2+1+l, k2+G2).getMaterial()== Material.plants
    	  ||  worldObj.getBlock(i2+U, j2+1+l, k2+G2).getMaterial()== Material.lava
    	  ||  worldObj.getBlock(i2+U, j2+1+l, k2+G2).getMaterial()== Material.water)
      {
    	  this.worldObj.setBlock(i2+U, j2+1+l, k2+G2, pblock);  
      }
      else {
    	  pblock.dropBlockAsItem(worldObj,i2+U, j2+1+l, k2+G2, 0, 0);
      }
  					}
  			}}}
  			
  		///////////////
  		for (int G2 = 0; G2 < 3; ++G2)
  		{
  			//int G2counter = G2*P*P;
  			for (int U = 0; U < 3; ++U)
  			{
  				//int Ucounter = U*P;
  				for (int l = 0; l <1; ++l)
  				{
  				//i3+U, j1+1+l, k3+G2
  					
      if(worldObj.getBlock(i3+U, j3+1+l, k3+G2) == Blocks.air
    	  
    	  ||  worldObj.getBlock(i3+U, j3+1+l, k3+G2).getMaterial()== Material.plants
    	  ||  worldObj.getBlock(i3+U, j3+1+l, k3+G2).getMaterial()== Material.lava
    	  ||  worldObj.getBlock(i3+U, j3+1+l, k3+G2).getMaterial()== Material.water)
      {
    	  this.worldObj.setBlock(i3+U, j3+1+l, k3+G2, pblock);  
      }
      else {
    	  pblock.dropBlockAsItem(worldObj,i3+U, j3+1+l, k3+G2, 0, 0);
      }
  					
  			}}}
  		
  		///////

  			
  		
      
      //Ensures the entity itself is deleted once its objective is reached
      //otherwise it will slide along the ground for a while
      this.setDead();
	   }
   }
  
}