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
	public int Amplify;
	
	
   public Projectile_Bomb(World world) {
       super(world);
   }

   public Projectile_Bomb(World world, EntityPlayer player) {
       super(world,player);
   }
   
   public Projectile_Bomb(World world, EntityPlayer player, int type, int amp) {
       super(world,player);
       this.Type = type;
       this.setType(type);
       this.Amplify= amp;
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
		if (tag.hasKey("Amplify")) {
			int b0 = tag.getInteger("Amplify");
			this.setType(b0);
		}
	}

	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setInteger("Type", this.getType());
		tag.setInteger("Amplify", this.getType());
	}
	
   //flying particle effect
   public void onUpdate()
   {
       super.onUpdate();       
       if(this.getType() != 7){
    	   this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + this.motionX * (double)1 / 4.0D, this.posY + .8+ this.motionY * (double)1 / 4.0D, this.posZ + this.motionZ * (double)1 / 4.0D, 0, 0 , 0);
    	   this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX + this.motionX * (double)1 / 4.0D, this.posY + .8+ this.motionY * (double)1 / 4.0D, this.posZ + this.motionZ * (double)1 / 4.0D, 0, .1 , 0);
    	   }
    	   else{
    		   short i;
    		   for (i = 0; i < 20; ++i)
    	       {
    	    	   this.world.spawnParticle(EnumParticleTypes.PORTAL,
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
	   World world = this.world;
	   
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

		   world.createExplosion(this, this.posX, this.posY, this.posZ, (float)3, false);

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
		   int amp = Amplify;

		   if(Type == 0){
			   //BombHelper.Block_Sphere(world, ItemRegistry.LooseDirtBlock.getDefaultState(), mop.getBlockPos(), 1, true);
			   BombHelper.Block_Sphere(world, ItemRegistry.LooseDirtBlock.getDefaultState(), mop.getBlockPos(),2+amp, true);
		   }
		   if(Type == 1){
			   //BombHelper.Block_Sphere(world, Blocks.SAND.getDefaultState(), mop.getBlockPos(), 1, false);
			   BombHelper.Block_Sphere(world, Blocks.SAND.getDefaultState(), mop.getBlockPos(), 2+amp, true);
		   }
		   if(Type == 2){
			   //BombHelper.Block_Sphere(world, Blocks.GRAVEL.getDefaultState(), mop.getBlockPos(), 1, false);
			   BombHelper.Block_Sphere(world, Blocks.GRAVEL.getDefaultState(), mop.getBlockPos(), 2+amp, true);
		   }
		   if(Type == 3){
			   BombHelper.Miracle_Sphere(world, mop.getBlockPos(), 3+amp);
		   }
		   if(Type == 4){
			   BombHelper.Frost_Sphere(world, mop.getBlockPos(), 6+amp);
			   BombHelper.Frost_Sphere(world, mop.getBlockPos(), 3+amp);
		   }
		   if(Type == 5){
			   BombHelper.Desert_Sphere(world, mop.getBlockPos(), 6+amp, false);
			   BombHelper.Desert_Sphere(world, mop.getBlockPos(), 4+amp, true);
			   BombHelper.Desert_Sphere(world, mop.getBlockPos(), 3+amp, false);
		   }
		   if(Type == 6){
			   BombHelper.Mushroom_Sphere(world, mop.getBlockPos(), 6+amp);
			   BombHelper.Mushroom_Sphere(world, mop.getBlockPos(), 4+amp);
			   BombHelper.Mushroom_Sphere(world, mop.getBlockPos(), 3+amp);
		   }

		   if(Type == 7){
			   BombHelper.Void_Sphere(world, mop.getBlockPos(), 2+amp);
		   }
		   if(Type == 8){
			   BombHelper.Block_Sphere(world, Blocks.DIRT.getDefaultState(), mop.getBlockPos(),2+amp, true);
		   }
		   
		   this.setDead();
	   }
   }
     
   
}