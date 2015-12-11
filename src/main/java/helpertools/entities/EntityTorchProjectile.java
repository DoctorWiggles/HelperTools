package helpertools.entities;

import java.util.Stack;



import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
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

public class EntityTorchProjectile extends EntityThrowable{

	//required fields to extend Entitythrowable
   public EntityTorchProjectile(World par1World) {
       super(par1World);
   }

   public EntityTorchProjectile(World par2World, EntityPlayer par3EntityPlayer) {
       super(par2World,par3EntityPlayer);
   }
   
 //spawns with a designated projectory for dispencers
   public EntityTorchProjectile(World world, double d, double e, double f, 
		   double x, double y, double z, float f1, float f2) {
       super(world);       
       this.setPosition(d, e, f);       
       this.setThrowableHeading(x, y, z, f1, f2);
   }
   
   @Override
   protected void entityInit() {

	   //if enabled spawns particle effects
   }
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
   //Called upon when the entity touches an object, being an entity or block
   @Override
  protected void onImpact(MovingObjectPosition mop) {
      
	   if(!this.worldObj.isRemote){
	   //some early calls and casting to simplify things later
	   
	  //The block it lands on in the world
      Block theblock = worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
      //The torch block to be placed in the world     
      Block pblock = Blocks.torch;
      
      int Gmeta = 0;	
      Gmeta =  worldObj.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ); 
      
      //some things i was testing with
      // Block Gblock = ((worldObj) theblock).getBlock(mop.blockX, mop.blockY, mop.blockZ);
      //int i = mop.blockX;
      //int j = mop.blockY;
      //int k = mop.blockZ;
      
      if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.plants
    		  || worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.vine){
    	 // this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.air); 
    	  
    	  //defualt placement on ground for plants etc
    	 if(worldObj.getBlock(mop.blockX, mop.blockY+1, mop.blockZ) == Blocks.air
    			&& worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ) != worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ)//){
    			 && worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ).getMaterial() != Material.vine
    			 
    			 || 
    			 worldObj.getBlock(mop.blockX, mop.blockY+1, mop.blockZ) == Blocks.air
     			&& worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ) != worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ)//){
     			 && worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ).getMaterial()!= Material.plants
     			 
     			|| 
   			 worldObj.getBlock(mop.blockX, mop.blockY+1, mop.blockZ).getMaterial()== Material.plants
    			&& worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ) != worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ)//){
    			 //&& worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ).getMaterial()!= Material.plants
     			 
    			 
    			 ){
    		 worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).dropBlockAsItem(worldObj,mop.blockX, mop.blockY, mop.blockZ, Gmeta, 0);
    		 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
    		 this.setDead();
    	 }
    	 //vine on wall placement
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.vine
    				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ+1) != Blocks.air
    				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ+1).getMaterial() != Material.vine
    				 
    				 ){
    			 worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).dropBlockAsItem(worldObj,mop.blockX, mop.blockY, mop.blockZ, Gmeta, 0);
    			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
        		 this.setDead();
    		 }
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.vine
     				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ-1) != Blocks.air
     				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ-1).getMaterial() != Material.vine
     				 
     				 ){
    			 worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).dropBlockAsItem(worldObj,mop.blockX, mop.blockY, mop.blockZ, Gmeta, 0);
     			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
         		 this.setDead();
     		 }
    	 //
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.vine
     				&& worldObj.getBlock(mop.blockX+1, mop.blockY, mop.blockZ) != Blocks.air
     				&& worldObj.getBlock(mop.blockX+1, mop.blockY, mop.blockZ).getMaterial() != Material.vine
     				 
     				 ){
    			 worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).dropBlockAsItem(worldObj,mop.blockX, mop.blockY, mop.blockZ, Gmeta, 0);
     			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
         		 this.setDead();
     		 }
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.vine
      				&& worldObj.getBlock(mop.blockX-1, mop.blockY, mop.blockZ) != Blocks.air
      				&& worldObj.getBlock(mop.blockX-1, mop.blockY, mop.blockZ).getMaterial() != Material.vine
      				 
      				 ){
    			 worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).dropBlockAsItem(worldObj,mop.blockX, mop.blockY, mop.blockZ, Gmeta, 0);
      			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
          		 this.setDead();
      		 }
    	 
    	 
    	//plants on wall placement
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.plants
    				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ+1) != Blocks.air
    				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ+1).getMaterial() != Material.plants
    				 
    				 ){
    			 worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).dropBlockAsItem(worldObj,mop.blockX, mop.blockY, mop.blockZ, Gmeta, 0);
    			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
        		 this.setDead();
    		 }
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.plants
     				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ-1) != Blocks.air
     				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ-1).getMaterial() != Material.plants
     				 
     				 ){
    			 worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).dropBlockAsItem(worldObj,mop.blockX, mop.blockY, mop.blockZ, Gmeta, 0);
     			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
         		 this.setDead();
     		 }
    	 //
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.plants
     				&& worldObj.getBlock(mop.blockX+1, mop.blockY, mop.blockZ) != Blocks.air
     				&& worldObj.getBlock(mop.blockX+1, mop.blockY, mop.blockZ).getMaterial() != Material.plants
     				 
     				 ){
    			 worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).dropBlockAsItem(worldObj,mop.blockX, mop.blockY, mop.blockZ, Gmeta, 0);
     			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
         		 this.setDead();
     		 }
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.plants
      				&& worldObj.getBlock(mop.blockX-1, mop.blockY, mop.blockZ) != Blocks.air
      				&& worldObj.getBlock(mop.blockX-1, mop.blockY, mop.blockZ).getMaterial() != Material.plants
      				 
      				 ){
    			 worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).dropBlockAsItem(worldObj,mop.blockX, mop.blockY, mop.blockZ, Gmeta, 0);
      			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
          		 this.setDead();
      		 }
    				
      }
      
      
      else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() != Material.plants
    		  || worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() != Material.vine){
      
      //The side of a block the entity hits
      int sideHit = mop.sideHit;
     
      
      
      //when it hits an entity do this
      if (mop.entityHit != null &&!(mop.entityHit instanceof EntityEnderman))
      {
    	  mop.entityHit.setFire(2);
    	  this.setDead();
      }
      if ((mop.entityHit instanceof EntityEnderman))
      {		
    	  mop.entityHit.setFire(1);
      }
      
      //when it hits a block go through this procedure to place or not place the block
      //ensures its properly placed depending on which part of a block you hit
      if(sideHit == 0)
    	  //bottom side
      {
    	  if (worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ) == Blocks.air
    			  || 
    			  worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ) == Blocks.snow_layer)
    		  //if true place the block here
    	  {
    	  this.worldObj.setBlock(mop.blockX, mop.blockY-1, mop.blockZ, pblock);
    	  }
    	  //if conditions aren't met drop an item object instead
    	  else
    	  {pblock.dropBlockAsItem(worldObj,mop.blockX, mop.blockY-1, mop.blockZ, 0, 0);}
    	  this.setDead();
      }
      //      
      if(sideHit == 1)
    	  //top side
      {  	  
    	  if(worldObj.getBlock(mop.blockX, mop.blockY+1, mop.blockZ) == Blocks.air  &&
    			  worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ) != Blocks.snow_layer)
    	  {
    	  this.worldObj.setBlock(mop.blockX, mop.blockY+1, mop.blockZ, pblock);
    	  }
    	  else if( worldObj.getBlock(mop.blockX, mop.blockY+1, mop.blockZ) == Blocks.air  &&
    			  worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ) == Blocks.snow_layer)
    	  {
    		  this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
    	  }
    	  else if(worldObj.getBlock(mop.blockX, mop.blockY+1, mop.blockZ) != Blocks.air &&
    			  worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ) != Blocks.snow_layer &&
    			  worldObj.getBlock(mop.blockX, mop.blockY+1, mop.blockZ) != pblock)
    	  {
    		  pblock.dropBlockAsItem(worldObj,mop.blockX, mop.blockY+1, mop.blockZ, 0, 0);
    	  }
    	  else if(worldObj.getBlock(mop.blockX, mop.blockY+1, mop.blockZ) == pblock) 
    	  {
    		  pblock.dropBlockAsItem(worldObj,mop.blockX, mop.blockY+1, mop.blockZ, 0, 0);
    	  }
    	  this.setDead();
      }
      //
      if(sideHit == 2 )
      {
    	  if (worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ-1) == Blocks.air
    			  || 
    			  worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ-1) == Blocks.snow_layer)
    	  {
    	  this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ-1, pblock);
    	  }      	
	  	else
	  	{
	  		pblock.dropBlockAsItem(worldObj,mop.blockX, mop.blockY, mop.blockZ-1, 0, 0);
	  	}
    	  this.setDead();
	  }

      //
      if(sideHit == 3 )
    	  {if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ+1) == Blocks.air
    	  || 
		  worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ+1) == Blocks.snow_layer)
    	  {
    	  this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ+1, pblock);
    	  }
    	  else
    	  {
    		  pblock.dropBlockAsItem(worldObj,mop.blockX, mop.blockY, mop.blockZ+1, 0, 0);}
    	  this.setDead();
    	  }
      //
      if(sideHit == 4) {
    	  if( worldObj.getBlock(mop.blockX-1, mop.blockY, mop.blockZ) == Blocks.air
    			  || 
    			  worldObj.getBlock(mop.blockX-1, mop.blockY, mop.blockZ) == Blocks.snow_layer)
      
    	  {
    	  this.worldObj.setBlock(mop.blockX-1, mop.blockY, mop.blockZ, pblock);
    	  }
    	  else
    	  {
    		  pblock.dropBlockAsItem(worldObj,mop.blockX-1, mop.blockY, mop.blockZ, 0, 0);}
    	  this.setDead();
    	  }
      //
      if(sideHit == 5){
    	  if(worldObj.getBlock(mop.blockX+1, mop.blockY, mop.blockZ) == Blocks.air
    			  || 
    			  worldObj.getBlock(mop.blockX+1, mop.blockY, mop.blockZ) == Blocks.snow_layer)
      
    	  {
    	  this.worldObj.setBlock(mop.blockX+1, mop.blockY, mop.blockZ, pblock);
    	  }
    	  else
    	  {
    		  pblock.dropBlockAsItem(worldObj,mop.blockX+1, mop.blockY, mop.blockZ, 0, 0); }
    	  this.setDead();
    	  }
      
    
     
      
      
	   }
	   }
   }

  
}