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
    	   this.worldObj.spawnParticle("smoke", this.posX + this.motionX * (double)i / 4.0D, this.posY + .8+ this.motionY * (double)i / 4.0D, this.posZ + this.motionZ * (double)i / 4.0D, 0, 0 , 0);
           this.worldObj.spawnParticle("cloud", this.posX + this.motionX * (double)i / 4.0D, this.posY + .8+ this.motionY * (double)i / 4.0D, this.posZ + this.motionZ * (double)i / 4.0D, 0, .1 , 0);
       }   
   }
   
   public byte Type = 0;
   //uniqueness for each projectile entity
   @Override
   public void readEntityFromNBT(NBTTagCompound nbt) {
	   nbt.setByte("Type", this.Type);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbt) {
	   this.Type = nbt.getByte("Type");
   }
   
   //protected Random rand;
   protected static Random growrand = new Random();
   
   @Override
  protected void onImpact(MovingObjectPosition mop) {
      
	   
	   int sideHit = mop.sideHit;
	   
	   if(this.worldObj.isRemote){
		      Block theblock = worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
		      Block pblock = Helpertoolscore.LooseDirtBlock;
		      
		      if (mop.entityHit != null )
		      {
		    	 return;
		      }
		      
		      int i4 = mop.blockX-1;
		      int j4 = mop.blockY-1;
		      int k4 = mop.blockZ-2;
		      
		      
		      if(sideHit == 0){
		    	  j4 = j4-2;
		    	  
		      }
		      
		      short short1 =32;
				for (int lp = 0; lp < short1; ++lp)
		       {
		           double d6 = (double)lp / ((double)short1 - 1.0D);
		           float f = (this.growrand.nextFloat()*3) ;
		           float f1 = (this.growrand .nextFloat()*3 );
		           float f2 = (this.growrand .nextFloat()*3 );
		           
		           float p1 = (this.growrand .nextFloat()/5 ) ;
		           float p = (this.growrand .nextFloat()-.5F )/5 ;
		           float p2 = (this.growrand .nextFloat()-.5F )/5 ;
		           //float p2 = (this.growrand .nextFloat()-.5F/5 ) ;
		           
		           this.worldObj.spawnParticle("cloud", i4+f-.5, j4+f1+.5, k4+f2+.5, p, p1, p2);
		           
		       }
				
				short short2 = 8;
				for (int lp = 0; lp < short2; ++lp)
		       {
		           double d6 = (double)lp / ((double)short1 - 1.0D);
		           float f = (this.growrand.nextFloat()*4) ;
		           float f1 = (this.growrand .nextFloat()*4 );
		           float f2 = (this.growrand .nextFloat()*4 );		          
		           float p1 = (this.growrand .nextFloat()/3 ) ;
		           float p = (this.growrand .nextFloat()-.5F )/5 ;
		           float p2 = (this.growrand .nextFloat()-.5F )/5 ;
		           
		           this.worldObj.spawnParticle("largesmoke", i4+f-.5, j4+f1, k4+f2+.5, p, p1, p2);
		           
		       }
	   }
	   if(!this.worldObj.isRemote){
	  //The block it lands on in the world
      Block theblock = worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
      //The torch block to be placed in the world
      Block pblock = Helpertoolscore.LooseDirtBlock;
      
      if (Type == 0){
      pblock = Helpertoolscore.LooseDirtBlock;
      }
      if (Type == 2){
          pblock = Blocks.sand;
          }
      
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
      if(sideHit == 0){
    	  j1 = j1-3;
    	  j2 = j2-3;
    	  j3 = j3-3;
      }
      
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