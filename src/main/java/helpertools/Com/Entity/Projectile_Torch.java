package helpertools.Com.Entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class Projectile_Torch extends EntityThrowable{

	
   public Projectile_Torch(World world) {
       super(world);
   }
   EntityPlayer person;
   public Projectile_Torch(World world, EntityPlayer entityPlayer) {
       super(world,entityPlayer);
   }
     
   public void onUpdate()
   {
	   int i;
       super.onUpdate();
       for (i = 0; i < 2; ++i)
       {
    	  //enable for particle effect trails.
           //this.worldObj.spawnParticle("crit", this.posX + this.motionX * (double)i / 4.0D, this.posY + this.motionY * (double)i / 4.0D, this.posZ + this.motionZ * (double)i / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
    	   //this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + this.motionX * (double)i / 4.0D, this.posY + this.motionY * (double)i / 4.0D, this.posZ + this.motionZ * (double)i / 4.0D, 0.0D, 0.0D, 0.0D, new int[0]);
    	   //this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX + this.motionX * (double)i / 4.0D, this.posY + this.motionY * (double)i / 4.0D, this.posZ + this.motionZ * (double)i / 4.0D, 0.0D, 0.0D, 0.0D, new int[0]);
       }   
   }
   
   /** Compacted Block Dropper method **/
   public void drop_blockItem(World world, BlockPos pos1){
	   
	   (world.getBlockState(pos1).getBlock()).dropBlockAsItem(world, pos1, world.getBlockState(pos1), 0);
   }
   
   /** Adjusts torches' facing postion to correct block face for placement **/
   public void place_block(World world, BlockPos pos2, EnumFacing sideHit, Block p_Block, boolean flag){
	   
	   IBlockState p_State = p_Block.getDefaultState();
	   int meta = 5;
	   //set to false prevents reshaping face, in case of hitting a grassblock sideways
	   if (flag){
	   switch(sideHit){
	   case UP: //p_State = p_Block.getDefaultState();			   
		   break;
	   case DOWN:  break;
	   case NORTH: meta = 4; break;
	   case SOUTH: meta = 3; break;
	   case EAST:  meta = 1; break;
	   case WEST:  meta = 2; break;
	   default:              break;	   
	   }
	   }
	   
	   p_State = p_Block.getStateFromMeta(meta); 
	   world.setBlockState(pos2, p_State, 02);
   }
   
   
   
   /** Called whenever the entities hitbox touches another box, being an entitie's or block. **/
   @Override
  protected void onImpact(RayTraceResult mop) {
	   
	   if (this.world.isRemote){ return;}
	   if(mop.entityHit != null){
	    	  Entity_Impact(mop);
	    	  return;
	      }
	   
	      EnumFacing sideHit = mop.sideHit; //face of a block
	      World world = this.world;
	      BlockPos pos1 = mop.getBlockPos();
	      Block P_Block = Blocks.TORCH;
	      IBlockState P_State = Blocks.TORCH.getDefaultState();
	     
	      
	      Block_Impact(mop, world, pos1, sideHit, P_Block, P_State);	
	      
	      this.setDead();
	   
   }
   
   /** Seperate Unit for entity processing **/
   public void Entity_Impact(RayTraceResult mop){
	   
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
   }
   
   /** Seperate Unit for block impact processing **/
   public void Block_Impact(RayTraceResult mop, World world, BlockPos pos1, EnumFacing sideHit, Block p_Block, IBlockState p_State) {
	   BlockPos pos2 = pos1;
	   
	   switch(sideHit){
	   case UP: pos2 = pos1.up();
		   break;
	   case DOWN: pos2 = pos1.down();
		   break;
	   case NORTH: pos2 = pos1.north();
		   break;
	   case SOUTH: pos2 = pos1.south();
		   break;
	   case EAST: pos2 = pos1.east();
		   break;
	   case WEST: pos2 = pos1.west();
		   break;
	   default:
		break;	   
	   }	   
	   if(world.getBlockState(pos1).getMaterial() == Material.PLANTS 
				|| world.getBlockState(pos1).getMaterial() == Material.VINE 
				|| world.getBlockState(pos1).getBlock() == Blocks.SNOW_LAYER){
		   drop_blockItem(world, pos1);
		   //world.setBlockState(pos1, p_State, 02);
		   boolean flag = false;
		   if(world.getBlockState(pos1).getMaterial() == Material.VINE
				   && !(world.getBlockState(pos1).getBlock() == Blocks.TALLGRASS)){
			   flag = true;
		   }
		   place_block(world, pos1, sideHit, p_Block, flag);
		   this.setDead();
		   return;		   
		   
	   }
	   if(world.getBlockState(pos2).getMaterial() == Material.PLANTS 
				|| world.getBlockState(pos2).getMaterial() == Material.VINE 
				|| world.getBlockState(pos2).getBlock() == Blocks.SNOW_LAYER){
		   drop_blockItem(world, pos2);
		   place_block(world, pos2, sideHit, p_Block, true);
		   this.setDead();
		   return;		   
		   
	   }
	   if(world.isAirBlock(pos2)){
		   place_block(world, pos2, sideHit, p_Block, true);
		   this.setDead();
		   return;
		   
	   }
	   else{
		   drop_blockItem(world, pos2);
		   this.setDead();
		   return;
	   }
	   
   }
   
  
}