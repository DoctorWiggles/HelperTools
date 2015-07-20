package helpertools.Common.Entity;

import helpertools.Utils.BlockStateHelper;

import java.util.Stack;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Entity_DynamiteProjectile extends EntityThrowable{

	
   public Entity_DynamiteProjectile(World world) {
       super(world);
   }

   public Entity_DynamiteProjectile(World world, EntityPlayer entityPlayer) {
       super(world,entityPlayer);
   }
   
   
   @Override
   protected void entityInit() { }
   
   public void onUpdate()
   {
	   int i;
       super.onUpdate();
       for (i = 0; i < 2; ++i)
       {}
   }
   
   //uniqueness for each projectile entity
   //Requires customs packets so I won't do that.
   @Override
   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {}
   @Override
   public void writeEntityToNBT(NBTTagCompound nbttagcompound) { }
   
   
   /** Called whenever the entities hitbox touches another box, being an entitie's or block. **/
   @Override
  protected void onImpact(MovingObjectPosition mop) {
	   
	   if (this.worldObj.isRemote){ return;}	  
	      
	     this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)4, true);
	      
	     this.setDead();
	   
   
   
	
	
   }
   
   
   
   
   

  
}