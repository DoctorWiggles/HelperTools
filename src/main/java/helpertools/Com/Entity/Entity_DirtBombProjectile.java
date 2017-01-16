package helpertools.Com.Entity;

import helpertools.Com.Config;
import helpertools.Com.ItemRegistry;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.BombHelper;

import java.util.List;
import java.util.Random;
import java.util.Stack;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Entity_DirtBombProjectile extends EntityThrowable{
	
	//a buch of junk we probably don't need	
	private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    protected boolean inGround;
    public int throwableShake;
    /** The entity that threw this throwable item. */
    private EntityLivingBase thrower;
    private String throwerName;
    private int ticksInGround;
    private int ticksInAir;

   public Entity_DirtBombProjectile(World world) {
       super(world);
   }

   public Entity_DirtBombProjectile(World world, EntityPlayer player) {
       super(world,player);
   }

   @Override
   protected void entityInit() {

   }
   //flying particle effect
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
   //special bombs
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
  protected void onImpact(RayTraceResult mop) {
	   if (mop.entityHit != null )
	      {
	    	 return;
	      }
	   Block pblock = ItemRegistry.LooseDirtBlock;
	   Block dirtblock = Blocks.DIRT;      
	   EnumFacing sideHit = mop.sideHit;
	   World world = this.worldObj;
	   
	   //valid_hit(worldObj, mop);
	   
	   //Client Smoke rendering
	   if(world.isRemote){	      
		      	    
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
		           
		           world.spawnParticle(EnumParticleTypes.CLOUD, i4+f-.5, j4+f1+.5, k4+f2+.5, p, p1, p2);
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
		           
		           world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, i4+f-.5, j4+f1, k4+f2+.5, p, p1, p2);
		           
		       }
	   }
	   
	   //server block placement
	   if(!world.isRemote){
	  //The block it lands on in the world
      //once it has hit anything
      //create and explosion
		   world.createExplosion(this, this.posX, this.posY, this.posZ, (float)1.9, false);
      
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
      //BombHelper.Block_Sphere(world, Blocks.GRAVEL.getDefaultState(), mop.getBlockPos(), 4);
      BombHelper.Miracle_Sphere(world, mop.getBlockPos(), 4);
      /*
      //creates a static area to place dirt, becuase i'm dumb ;^)
      //top section
      block_placement(worldObj, pblock, dirtblock, i1, j1, k1, 5, 3, 1, true);
      //mid section
      block_placement(worldObj, pblock, dirtblock, i2, j2, k2, 3, 5, 1, true);
      //bottom section
      block_placement(worldObj, pblock, dirtblock, i3, j3, k3, 3, 3, 1, false);
    	*/
      //Ensures the entity itself is deleted once its objective is reached
      //otherwise it will slide along the ground for a while
      this.setDead();
	   }
   }
   
   //sets up the area to place blocks
   public void block_placement(World world,Block pblock, Block dirtblock, int x1, int y1, int z1, int G2, int U2, int l2, boolean flag){
	    for (int G = 0; G < G2; ++G)
 		{
 			for (int U = 0; U < U2; ++U)
 			{
 				for (int l = 0; l <l2; ++l)
 				{
 					//randomizer for unique dirt mounds
 					BlockPos pos = new BlockPos(x1+U, y1+1+l, z1+G);
 					if(flag){
 						int ig = growrand.nextInt(6);
 						if (ig >= 2){ 					
 							place_block(pos, pblock, dirtblock);
 						}}
  					if(!flag){
  						place_block(pos, pblock, dirtblock);
  					}
   	  
  					}}}
	   
	   }
  
	//place the block
   //goes through a couple checks to displace -- crush non solids
   public void place_block (BlockPos pos, Block pblock, Block dirtblock){

		if(worldObj.isAirBlock(pos)   	  
				||  worldObj.getBlockState(pos).getMaterial()== Material.PLANTS
				||  worldObj.getBlockState(pos).getMaterial()== Material.LAVA
				||  worldObj.getBlockState(pos).getMaterial()== Material.WATER)
		{
			worldObj.setBlockState(pos, pblock.getDefaultState(), 012);
		}
		if(worldObj.getBlockState(pos).getMaterial()== Material.PLANTS
				|| worldObj.getBlockState(pos).getMaterial()== Material.VINE
				|| worldObj.getBlockState(pos).getBlock() == Blocks.SNOW_LAYER){
			(worldObj.getBlockState(pos).getBlock()).dropBlockAsItem(worldObj, pos, worldObj.getBlockState(pos), 0);			
			worldObj.setBlockState(pos, pblock.getDefaultState(), 012);
		}
		
		else {
			if(Config.DirtBomb_Debris){
			dirtblock.dropBlockAsItem(worldObj, pos, dirtblock.getDefaultState(), 0);
		}}
   }
   
   
}