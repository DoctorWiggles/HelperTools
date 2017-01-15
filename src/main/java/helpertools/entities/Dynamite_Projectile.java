package helpertools.entities;

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
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Dynamite_Projectile extends EntityThrowable{

   public Dynamite_Projectile(World par1World) {
       super(par1World);
   }

   public Dynamite_Projectile(World world, EntityPlayer player) {
       super(world,player);
   }
   
   //spawns with a designated projectory for dispencers
   public Dynamite_Projectile(World world, double d, double e, double f, 
		   double x, double y, double z, float f1, float f2) {
       super(world);       
       this.setPosition(d, e, f);       
       this.setThrowableHeading(x, y, z, f1, f2);
   }

   @Override
   protected void entityInit() {

   }
   //spawns particle effects if enabled
   public void onUpdate()
   {
	   int i;
       super.onUpdate();
       for (i = 0; i < 2; ++i)
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
   protected static Random rand = new Random();
   
   @Override
  protected void onImpact(MovingObjectPosition mop) {
      
	   if(!this.worldObj.isRemote){
	  //The block it lands on in the world
      Block theblock = worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
      //The torch block to be placed in the world
      Block pblock = Blocks.redstone_torch;
      
      //can be called to simplify things
      //int i = mop.blockX;
      //int j = mop.blockY;
      //int k = mop.blockZ;
      
      if (mop.entityHit != null &&!(mop.entityHit instanceof EntityEnderman))
      {
    	  
    	  this.setDead();
      }
      /**
      //This code doesn't need to be here, since it won't place any blocks and will
      //just explode anyways
      //left it because I may need it later
      **/
      
      //The side the entity hits
      int sideHit = mop.sideHit;
      //
      	if(sideHit == 0 && worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ) == Blocks.air)
      	{
    	  //pblock = Blocks.lapis_block;
    	  //pblock = Blocks.torch;
    	  //this.worldObj.setBlock(mop.blockX, mop.blockY-1, mop.blockZ, pblock);
    	  this.setDead();
      	}
      	//      
      	if(sideHit == 1)
      	{  	  
    	  if(worldObj.getBlock(mop.blockX, mop.blockY+1, mop.blockZ) == Blocks.air)
    	  {
    	  //this.worldObj.setBlock(mop.blockX, mop.blockY+1, mop.blockZ, pblock);
    	  //this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, (float)3, true);
    	  //Blocks.torch.dropBlockAsItem(worldObj,mop.blockX, mop.blockY+1, mop.blockZ, 0, 0);
    	  }
    	  else
    	  {
    		 
    	  }
      	}
      	//
      	if(sideHit == 2 && worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ-1) == Blocks.air)
      	{
    	  //this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ-1, pblock);
    	  //this.worldObj.newExplosion((Entity)null, this.posX, this.posY, this.posZ, (float)1, true, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
      	}
      	//
      	if(sideHit == 3 && worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ+1) == Blocks.air)
      	{
    	  //this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ+1, pblock);
      	}
      	//
      	if(sideHit == 4 && worldObj.getBlock(mop.blockX-1, mop.blockY, mop.blockZ) == Blocks.air)
      	{
    	  //this.worldObj.setBlock(mop.blockX-1, mop.blockY, mop.blockZ, pblock);
     	}
      	//
      	if(sideHit == 5&& worldObj.getBlock(mop.blockX+1, mop.blockY, mop.blockZ) == Blocks.air)
      	{
    	  //this.worldObj.setBlock(mop.blockX+1, mop.blockY, mop.blockZ, pblock);
      	}
     
      
      
      //once it has hit anything
      //create and explosion
      this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)4, true);
      //HelperExplosions.doExplosionU(worldObj, 4, this.posX, this.posY, this.posZ);
      //HelperExplosions.doExplosionG(worldObj, 5, (int)this.posX, (int)this.posY, (int)this.posZ);
      
      //Ensures the entity itself is deleted once its objective is reached
      //otherwise it will slide along the ground for a while
      this.setDead();
	   }
   }
  
}