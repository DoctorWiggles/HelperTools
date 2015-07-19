package helpertools.Common.Entity;

import helpertools.Utils.BlockStateHelper;

import java.util.Stack;






import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.util.BlockPos;
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
   
   public void drop_blockItem(World worldu, BlockPos pos1){
	   
	   (worldu.getBlockState(pos1).getBlock()).dropBlockAsItem(worldu, pos1, worldu.getBlockState(pos1), 0);
   }
   
   
   //Called upon when the entity touches an object, being an entity or block
   @Override
  protected void onImpact(MovingObjectPosition mop) {
	   
	   this.setDead();
	   return;
	   /**
	   
	   World worldu = this.worldObj;
	   BlockPos pos1 = new BlockPos(mop.getBlockPos());
	   Block theblock = BlockStateHelper.getBlockfromState(worldu, pos1);

	   //The torch block to be placed in the world     
	   Block pblock = Blocks.torch;
	   //ItemStack p_stack = itemstack(pblock, 0, 0);
	      
	   int Gmeta = 0;	
	   //Gmeta =  worldObj.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ); 
	   Gmeta = BlockStateHelper.getMetafromState(worldu,pos1);
	      
	   IBlockState state_1 = BlockStateHelper.returnState(Gmeta);
	   
	   
      
	   if(!this.worldObj.isRemote){
	   //some early calls and casting to simplify things later
	   
	  //The block it lands on in the world		   
      //Block theblock = worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
      //Block theblock = worldObj.getBlockState(mop.getBlockPos());
      **/
      /**
      
      
      if(theblock.getMaterial() == Material.plants
    		  || theblock.getMaterial() == Material.vine){
    	 // this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.air); 
    	  
    	  //defualt placement on ground for plants etc
    	 if(worldObj.getBlock(mop.blockX, mop.blockY+1, mop.blockZ) == Blocks.air
    			&& worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ) != theblock//){
    			 && worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ).getMaterial() != Material.vine
    			 
    			 || 
    			 worldObj.getBlock(mop.blockX, mop.blockY+1, mop.blockZ) == Blocks.air
     			&& worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ) != theblock//){
     			 && worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ).getMaterial()!= Material.plants
     			 
     			|| 
   			 worldObj.getBlock(mop.blockX, mop.blockY+1, mop.blockZ).getMaterial()== Material.plants
    			&& worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ) != theblock//){
    			 //&& worldObj.getBlock(mop.blockX, mop.blockY-1, mop.blockZ).getMaterial()!= Material.plants
     			 
    			 
    			 ){
    		 drop_blockItem(worldu, pos1);
    		 //this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
    		 worldu.setBlockState(pos1,(IBlockState) pblock, 02);
    		 this.setDead();
    	 }
    	 //vine on wall placement
    		 else if(theblock.getMaterial() == Material.vine
    				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ+1) != Blocks.air
    				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ+1).getMaterial() != Material.vine
    				 
    				 ){
    			 drop_blockItem(worldu, pos1);
    			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
        		 this.setDead();
    		 }
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.vine
     				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ-1) != Blocks.air
     				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ-1).getMaterial() != Material.vine
     				 
     				 ){
    			 drop_blockItem(worldu, pos1);
     			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
         		 this.setDead();
     		 }
    	 //
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.vine
     				&& worldObj.getBlock(mop.blockX+1, mop.blockY, mop.blockZ) != Blocks.air
     				&& worldObj.getBlock(mop.blockX+1, mop.blockY, mop.blockZ).getMaterial() != Material.vine
     				 
     				 ){
    			 drop_blockItem(worldu, pos1);
     			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
         		 this.setDead();
     		 }
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.vine
      				&& worldObj.getBlock(mop.blockX-1, mop.blockY, mop.blockZ) != Blocks.air
      				&& worldObj.getBlock(mop.blockX-1, mop.blockY, mop.blockZ).getMaterial() != Material.vine
      				 
      				 ){
    			 //BlockStateHelper.getMetafromState(worldu,pos1).dropBlockAsItem(theblock, Gmeta, 0);
    			 //(worldu.getBlockState(pos1).getBlock()).dropBlockAsItem(worldu, pos1, worldu.getBlockState(pos1), 0);
    			 drop_blockItem(worldu, pos1);
      			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
          		 this.setDead();
      		 }
    	 
    	 
    	//plants on wall placement
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.plants
    				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ+1) != Blocks.air
    				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ+1).getMaterial() != Material.plants
    				 
    				 ){
    			 drop_blockItem(worldu, pos1);
    			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
        		 this.setDead();
    		 }
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.plants
     				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ-1) != Blocks.air
     				&& worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ-1).getMaterial() != Material.plants
     				 
     				 ){
    			 drop_blockItem(worldu, pos1);
     			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
         		 this.setDead();
     		 }
    	 //
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.plants
     				&& worldObj.getBlock(mop.blockX+1, mop.blockY, mop.blockZ) != Blocks.air
     				&& worldObj.getBlock(mop.blockX+1, mop.blockY, mop.blockZ).getMaterial() != Material.plants
     				 
     				 ){
    			 drop_blockItem(worldu, pos1);
     			 this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, pblock);  
         		 this.setDead();
     		 }
    		 else if(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.plants
      				&& worldObj.getBlock(mop.blockX-1, mop.blockY, mop.blockZ) != Blocks.air
      				&& worldObj.getBlock(mop.blockX-1, mop.blockY, mop.blockZ).getMaterial() != Material.plants
      				 
      				 ){
    			 drop_blockItem(worldu, pos1);
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
	   }**/
   }

  
}