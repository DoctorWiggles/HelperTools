package helpertools.entities;

import helpertools.Common_Registry;
import helpertools.ConfigurationFactory;
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
   
   //stable references
   Block dirtblock = Blocks.dirt;
   Block pblock = Common_Registry.LooseDirtBlock;
   
   @Override
  protected void onImpact(MovingObjectPosition mop) {
	    
	   
	   int sideHit = mop.sideHit;
	   
	   if (mop.entityHit != null )
	      {
	    	 return;
	      }
	   
	   if(this.worldObj.isRemote){
		      
		      
		      
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
	   //serverside code
	   if(!this.worldObj.isRemote){
	  
		   
      
      /**
      if (Type == 0){
      pblock = Common_Registry.LooseDirtBlock;
      }
      if (Type == 2){
          pblock = Blocks.sand;
          }
      **/
      
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
      //creates a static area to place dirt, becuase i'm dumb ;^)
      //top section
      block_placement(worldObj, pblock, dirtblock, i1, j1, k1, 5, 3, 1, true);
      //mid section
      block_placement(worldObj, pblock, dirtblock, i2, j2, k2, 3, 5, 1, true);
      //bottom section
      block_placement(worldObj, pblock, dirtblock, i3, j3, k3, 3, 3, 1, false);
    	
      //Ensures the entity itself is deleted once its objective is reached
      //otherwise it will slide along the ground for a while
      this.setDead();
	   }
   }
   
   public void block_placement(World world,Block pblock, Block dirtblock, int x1, int y1, int z1, int G2, int U2, int l2, boolean flag){
	    for (int G = 0; G < G2; ++G)
		{
			for (int U = 0; U < U2; ++U)
			{
				for (int l = 0; l <l2; ++l)
				{
					//randomizer for unique dirt mounds
					//BlockPos pos = new BlockPos(x1+U, y1+1+l, z1+G);					
					int x = x1+U;
					int y = y1+1+l;
					int z = z1+G;
					if(flag){
						int ig = growrand.nextInt(6);
						if (ig >= 2){ 					
							place_block(x,y,z, pblock, dirtblock);
						}}
 					if(!flag){
 						place_block(x,y,z, pblock, dirtblock);
 					}
  	  
 					}}}
	   
	   }
 
	//place the block
  //goes through a couple checks to displace -- crush non solids
  public void place_block (int x, int y, int z, Block pblock, Block dirtblock){

		if(worldObj.getBlock(x,y,z) == Blocks.air 	  
				||  worldObj.getBlock(x,y,z).getMaterial()== Material.plants
				||  worldObj.getBlock(x,y,z).getMaterial()== Material.lava
				||  worldObj.getBlock(x,y,z).getMaterial()== Material.water)
		{
			//worldObj.setBlockState(pos, pblock.getDefaultState(), 012);
			this.worldObj.setBlock(x,y,z, pblock); 
		}
		if(worldObj.getBlock(x,y,z).getMaterial()== Material.plants
				|| worldObj.getBlock(x,y,z).getMaterial()== Material.vine
				|| worldObj.getBlock(x,y,z)== Blocks.snow_layer){
			//(worldObj.getBlockState(pos).getBlock()).dropBlockAsItem(worldObj, pos, worldObj.getBlockState(pos), 0);
			//worldObj.getBlock(x,y,z).dropBlockAsItem(worldObj,x,y,z, 0, 0);
			(worldObj.getBlock(x,y,z)).dropBlockAsItem(worldObj,x,y,z, worldObj.getBlockMetadata(x,y,z), 0);
			//worldObj.setBlockState(pos, pblock.getDefaultState(), 012);
			this.worldObj.setBlock(x,y,z, pblock); 
		}
		
		else {
			if(ConfigurationFactory.Bomb_Debris){
			dirtblock.dropBlockAsItem(worldObj, x,y,z, 0, 0);
		}}
  }
  
}