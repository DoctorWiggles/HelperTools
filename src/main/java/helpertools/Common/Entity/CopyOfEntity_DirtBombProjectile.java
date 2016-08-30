package helpertools.Common.Entity;

import helpertools.Common.ItemRegistry;
import helpertools.Utils.BlockStateHelper;

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
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CopyOfEntity_DirtBombProjectile extends EntityThrowable{

   public CopyOfEntity_DirtBombProjectile(World par1World) {
       super(par1World);
   }

   public CopyOfEntity_DirtBombProjectile(World par2World, EntityPlayer par3EntityPlayer) {
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
    	   this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + this.motionX * (double)i / 4.0D, this.posY + .8+ this.motionY * (double)i / 4.0D, this.posZ + this.motionZ * (double)i / 4.0D, 0, 0 , 0);
           this.worldObj.spawnParticle(EnumParticleTypes.CLOUD, this.posX + this.motionX * (double)i / 4.0D, this.posY + .8+ this.motionY * (double)i / 4.0D, this.posZ + this.motionZ * (double)i / 4.0D, 0, .1 , 0);
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
	   Block pblock = ItemRegistry.LooseDirtBlock;
      
	   
	   EnumFacing sideHit = mop.sideHit;
	   
	   if(this.worldObj.isRemote){
		      //Block theblock = worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
		      Block theblock = worldObj.getBlockState(mop.getBlockPos()).getBlock();
		      
		      
		      if (mop.entityHit != null )
		      {
		    	 return;
		      }		    	
		    	//BlockPos pos1 = new BlockPos(x1, y1, z1);
		    	/////////
		    	int blockX = mop.getBlockPos().getX();
		    	int blockY = mop.getBlockPos().getY();
		    	int blockZ = mop.getBlockPos().getZ();
		    	
		    	
		      int i4 = blockX-1;
		      int j4 = blockY-1;
		      int k4 = blockZ-2;
		      
		      
		      if(sideHit == EnumFacing.DOWN){
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
		           
		           this.worldObj.spawnParticle(EnumParticleTypes.CLOUD, i4+f-.5, j4+f1+.5, k4+f2+.5, p, p1, p2);
		           
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
		           
		           this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, i4+f-.5, j4+f1, k4+f2+.5, p, p1, p2);
		           
		       }
	   }
	   if(!this.worldObj.isRemote){
	  //The block it lands on in the world
	  Block theblock = worldObj.getBlockState(mop.getBlockPos()).getBlock();
      //The torch block to be placed in the world
      
	  /**
      if (Type == 0){
      pblock = Common_Registry.LooseDirtBlock;
      }
      if (Type == 2){
          pblock = Blocks.sand;
          }
      **/
      
      if (mop.entityHit != null )
      {
    	 return;
      }
      
      //once it has hit anything
      //create and explosion
      //this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)1, true);

      //this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)1.3, true);
      this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)1.9, false);
      
      	int blockX = mop.getBlockPos().getX();
  		int blockY = mop.getBlockPos().getY();
  		int blockZ = mop.getBlockPos().getZ();
      
      int P = 3;
      int i1 = blockX-1;
      int j1 = blockY;
      int k1 = blockZ-2;
      
      int i2 = blockX-2;
      int j2 = blockY+1;
      int k2 = blockZ-1;
      
      int i3 = blockX-1;
      int j3 = blockY+1;
      int k3 = blockZ-1;
      ////////////////
      if(sideHit == EnumFacing.DOWN){
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
  						
  						 
  						BlockPos pos2 = new BlockPos(i1+U, j1+1+l, k1+G2);
      if(worldObj.isAirBlock(pos2)
    	  
    	  ||  worldObj.getBlockState(pos2).getBlock().getMaterial()== Material.PLANTS
    	  ||  worldObj.getBlockState(pos2).getBlock().getMaterial()== Material.LAVA
    	  ||  worldObj.getBlockState(pos2).getBlock().getMaterial()== Material.WATER)
      {
    	  //this.worldObj.setBlock(i1+U, j1+1+l, k1+G2, pblock); 
    	  worldObj.setBlockState(pos2, pblock.getDefaultState(), 02);
    	  
      }
      else {
    	  //pblock.dropBlockAsItem(worldObj,i1+U, j1+1+l, k1+G2, 0, 0);
    	  pblock.dropBlockAsItem(worldObj, pos2, pblock.getDefaultState(), 0);
    	 
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
  						BlockPos pos3 = new BlockPos(i2+U, j2+1+l, k2+G2);
      if(worldObj.isAirBlock(pos3)
    	  
    		  ||  worldObj.getBlockState(pos3).getBlock().getMaterial()== Material.PLANTS
        	  ||  worldObj.getBlockState(pos3).getBlock().getMaterial()== Material.LAVA
        	  ||  worldObj.getBlockState(pos3).getBlock().getMaterial()== Material.WATER)
      {
    	  worldObj.setBlockState(pos3, pblock.getDefaultState(), 02); 
      }
      else {
    	  //pblock.dropBlockAsItem(worldObj,i2+U, j2+1+l, k2+G2, 0, 0);
    	  pblock.dropBlockAsItem(worldObj, pos3, pblock.getDefaultState(), 0);
    	  
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
  					BlockPos pos4 = new BlockPos(i3+U, j3+1+l, k3+G2);
      if(worldObj.isAirBlock(pos4)
    	  
    		  ||  worldObj.getBlockState(pos4).getBlock().getMaterial()== Material.PLANTS
        	  ||  worldObj.getBlockState(pos4).getBlock().getMaterial()== Material.LAVA
        	  ||  worldObj.getBlockState(pos4).getBlock().getMaterial()== Material.WATER)
      {
    	  //this.worldObj.setBlock(i3+U, j3+1+l, k3+G2, pblock);
    	  worldObj.setBlockState(pos4, pblock.getDefaultState(), 02);
      }
      else {
    	  
    	  pblock.dropBlockAsItem(worldObj, pos4, pblock.getDefaultState(), 0);
    	  
      }
  					
  			}}}
  		
  		///////

  			
  		
      
      //Ensures the entity itself is deleted once its objective is reached
      //otherwise it will slide along the ground for a while
      this.setDead();
	   }
   }
  
}