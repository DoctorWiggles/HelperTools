package helpertools.Com.Entity;

import helpertools.Main;
import helpertools.Com.Config;
import helpertools.Com.ItemRegistry;
import helpertools.Utils.BombHelper;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class Projectile_Bomb extends EntityThrowable{
	
	private static final DataParameter<Integer> TYPE = EntityDataManager.<Integer>createKey(Projectile_Bomb.class, DataSerializers.VARINT);
	public int Type;
	
	
   public Projectile_Bomb(World world) {
       super(world);
   }

   public Projectile_Bomb(World world, EntityPlayer player) {
       super(world,player);
   }
   
   public Projectile_Bomb(World world, EntityPlayer player, int type) {
       super(world,player);
       this.Type = type;
       this.setType(type);
   }

   protected void entityInit() {
		super.entityInit();
		this.dataManager.register(TYPE, Integer.valueOf(0));
	}
   
   public int getType() {
		return this.dataManager.get(TYPE).intValue();
	}
   
   public void setType(int par1) {
		this.dataManager.set(TYPE, Integer.valueOf(par1));
	}
   
   public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("Type")) {
			int b0 = tag.getInteger("Type");
			this.setType(b0);
		}
	}

	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setInteger("Type", this.getType());
	}
	
   //flying particle effect
   public void onUpdate()
   {
       super.onUpdate();       
       if(this.getType() != 7){
    	   this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + this.motionX * (double)1 / 4.0D, this.posY + .8+ this.motionY * (double)1 / 4.0D, this.posZ + this.motionZ * (double)1 / 4.0D, 0, 0 , 0);
    	   this.worldObj.spawnParticle(EnumParticleTypes.CLOUD, this.posX + this.motionX * (double)1 / 4.0D, this.posY + .8+ this.motionY * (double)1 / 4.0D, this.posZ + this.motionZ * (double)1 / 4.0D, 0, .1 , 0);
    	   }
    	   else{
    		   short i;
    		   for (i = 0; i < 20; ++i)
    	       {
    	    	   this.worldObj.spawnParticle(EnumParticleTypes.PORTAL,
    	    			   this.posX + this.motionX * (double)i / 4.0D,
    	    			   this.posY + .8+ this.motionY * (double)i / 4.0D,
    	    			   this.posZ + this.motionZ * (double)i / 4.0D, 0, 0 , 0);
    	           
    	          
    	       } 
    	   }
   }
   
   
   
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
		           float f = (Main.Randy.nextFloat()*3) ;
		           float f1 = (Main.Randy.nextFloat()*3 );
		           float f2 = (Main.Randy.nextFloat()*3 );
		           
		           float p1 = (Main.Randy.nextFloat()/5 ) ;
		           float p = (Main.Randy.nextFloat()-.5F )/5 ;
		           float p2 = (Main.Randy.nextFloat()-.5F )/5 ;		
		           
		           world.spawnParticle(EnumParticleTypes.CLOUD, i4+f-.5, j4+f1+.5, k4+f2+.5, p, p1, p2);
		        }
				
				short short2 = 8;
				for (int lp = 0; lp < short2; ++lp)
		       {
		           double d6 = (double)lp / ((double)short1 - 1.0D);
		           float f = (Main.Randy.nextFloat()*4) ;
		           float f1 = (Main.Randy .nextFloat()*4 );
		           float f2 = (Main.Randy.nextFloat()*4 );		          
		           float p1 = (Main.Randy.nextFloat()/3 ) ;
		           float p = (Main.Randy.nextFloat()-.5F )/5 ;
		           float p2 = (Main.Randy.nextFloat()-.5F )/5 ;
		           
		           world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, i4+f-.5, j4+f1, k4+f2+.5, p, p1, p2);
		           
		       }
	   }

	   if(!world.isRemote){

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


		   if(Type == 0){
			   BombHelper.Block_Sphere(world, ItemRegistry.LooseDirtBlock.getDefaultState(), mop.getBlockPos(), 4);
		   }
		   if(Type == 1){
			   BombHelper.Block_Sphere(world, Blocks.SAND.getDefaultState(), mop.getBlockPos(), 4);
		   }
		   if(Type == 2){
			   BombHelper.Block_Sphere(world, Blocks.GRAVEL.getDefaultState(), mop.getBlockPos(), 4);
		   }
		   if(Type == 3){
			   BombHelper.Miracle_Sphere(world, mop.getBlockPos(), 4);
		   }
		   if(Type == 4){
			   BombHelper.Frost_Sphere(world, mop.getBlockPos(), 6);
			   BombHelper.Frost_Sphere(world, mop.getBlockPos(), 3);
		   }
		   if(Type == 5){
			   BombHelper.Desert_Sphere(world, mop.getBlockPos(), 6, false);
			   BombHelper.Desert_Sphere(world, mop.getBlockPos(), 4, true);
			   BombHelper.Desert_Sphere(world, mop.getBlockPos(), 3, false);
		   }
		   if(Type == 6){
			   BombHelper.Mushroom_Sphere(world, mop.getBlockPos(), 6);
			   BombHelper.Mushroom_Sphere(world, mop.getBlockPos(), 4);
			   BombHelper.Mushroom_Sphere(world, mop.getBlockPos(), 3);
		   }

		   if(Type == 7){
			   BombHelper.Void_Sphere(world, mop.getBlockPos(), 5);
		   }



		   /*
      		//creates a static area to place dirt, becuase i'm dumb ;^)
      		//top section
      		block_placement(worldObj, pblock, dirtblock, i1, j1, k1, 5, 3, 1, true);
      		//mid section
      		block_placement(worldObj, pblock, dirtblock, i2, j2, k2, 3, 5, 1, true);
      		//bottom section
      		block_placement(worldObj, pblock, dirtblock, i3, j3, k3, 3, 3, 1, false);
		    */
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
 						int ig = Main.Randy.nextInt(6);
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