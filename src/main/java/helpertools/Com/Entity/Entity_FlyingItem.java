package helpertools.Com.Entity;

import helpertools.Com.ItemRegistry;
import helpertools.Utils.ModUtil;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.google.common.base.Optional;
/** May or may not be 100% recycled bat code with spooky item spice **/
public class Entity_FlyingItem extends EntityAmbientCreature
{
    private static final DataParameter<Byte> HANGING = EntityDataManager.<Byte>createKey(Entity_FlyingItem.class, DataSerializers.BYTE);
    /** Coordinates of where the bat spawned. */
    private BlockPos spawnPosition;
    private short delayBeforeCanPickup;

    public Entity_FlyingItem(World worldIn, ItemStack stack)
    {
        super(worldIn);
        this.setSize(0.5F, 0.9F);
        this.setIsBatHanging(true);
        this.setEntityItemStack(stack);
        this.delayBeforeCanPickup = 20;
        if(stack.hasDisplayName()){
        	this.setCustomNameTag(stack.getDisplayName());
        	this.setAlwaysRenderNameTag(true);
        }
    }
    
    public Entity_FlyingItem(World worldIn)
    {
        super(worldIn);
        this.setSize(0.5F, 0.9F);
        this.setIsBatHanging(true);
        this.setEntityItemStack(new ItemStack(ItemRegistry.miragehusk));
        this.delayBeforeCanPickup = 20;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(HANGING, Byte.valueOf((byte)0));
        this.getDataManager().register(ITEM, Optional.<ItemStack>absent());
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume(){return 0.1F;}

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch()
    {
        return super.getSoundPitch() * 0.95F;
    }

    @Nullable
    protected SoundEvent getAmbientSound()
    {
        return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : SoundEvents.ENTITY_BAT_AMBIENT;
    }

    protected SoundEvent getHurtSound(){return SoundEvents.ENTITY_BAT_HURT;}

    protected SoundEvent getDeathSound(){return SoundEvents.ENTITY_BAT_DEATH;}

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed(){return false;}

    protected void collideWithEntity(Entity entityIn)
    {
    }

    protected void collideWithNearbyEntities()
    {
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    }

    public boolean getIsBatHanging()
    {
        return (((Byte)this.dataManager.get(HANGING)).byteValue() & 1) != 0;
    }

    public void setIsBatHanging(boolean isHanging)
    {
        byte b0 = ((Byte)this.dataManager.get(HANGING)).byteValue();

        if (isHanging)
        {
            this.dataManager.set(HANGING, Byte.valueOf((byte)(b0 | 1)));
        }
        else
        {
            this.dataManager.set(HANGING, Byte.valueOf((byte)(b0 & -2)));
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
    	//this.setDead();
    	
        super.onUpdate();
        
        if (this.delayBeforeCanPickup > 0)
        {
            --this.delayBeforeCanPickup;
        }
        
        if (this.getIsBatHanging())
        {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
            this.posY = (double)MathHelper.floor_double(this.posY) + 1.0D - (double)this.height;
        }
        else
        {
        	BlockPos pos = this.getPosition();
        	for (int i = 0; i < this.rand.nextInt(3); ++i)
        		ModUtil.Particle(3F,this, EnumParticleTypes.SPELL_WITCH, 0 , 0, 0);
            this.motionY *= 0.6000000238418579D;
        }
    }

    protected void updateAITasks()
    {
        super.updateAITasks();
        BlockPos blockpos = new BlockPos(this);
        BlockPos blockpos1 = blockpos.up();

        if (this.getIsBatHanging())
        {
            if (this.worldObj.getBlockState(blockpos1).isNormalCube())
            {
                if (this.rand.nextInt(200) == 0)
                {
                    this.rotationYawHead = (float)this.rand.nextInt(360);
                }

                if (this.worldObj.getNearestPlayerNotCreative(this, 4.0D) != null)
                {
                    this.setIsBatHanging(false);
                    this.worldObj.playEvent((EntityPlayer)null, 1025, blockpos, 0);
                }
            }
            else
            {
                this.setIsBatHanging(false);
                this.worldObj.playEvent((EntityPlayer)null, 1025, blockpos, 0);
            }
        }
        else
        {
            if (this.spawnPosition != null && (!this.worldObj.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1))
            {
                this.spawnPosition = null;
            }

            if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq((double)((int)this.posX), (double)((int)this.posY), (double)((int)this.posZ)) < 4.0D)
            {
                this.spawnPosition = new BlockPos((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
            }

            double d0 = (double)this.spawnPosition.getX() + 0.5D - this.posX;
            double d1 = (double)this.spawnPosition.getY() + 0.1D - this.posY;
            double d2 = (double)this.spawnPosition.getZ() + 0.5D - this.posZ;
            this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
            this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
            this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
            float f = (float)(MathHelper.atan2(this.motionZ, this.motionX) * (180D / Math.PI)) - 90.0F;
            float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
            this.moveForward = 0.5F;
            this.rotationYaw += f1;

            if (this.rand.nextInt(100) == 0 && this.worldObj.getBlockState(blockpos1).isNormalCube())
            {
                this.setIsBatHanging(true);
            }
        }
    }

    public void fall(float distance, float damageMultiplier)
    {
    }

    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    }


    //============================== pick up item =============================//
    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	Entity Host = source.getEntity();  
    	if(Host == null) return super.attackEntityFrom(source, amount);
    	if(captured(Host))return true;
        return super.attackEntityFrom(source, amount);
    }
    
    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
    	captured(entityIn);
    }
    
    public boolean captured(Entity Host){
    	if (!Host.worldObj.isRemote)
        {
    	//can we pick it up yet
    	if(cannotPickup())return false;	
    	if(Host != null){
    		if(Host instanceof EntityPlayer){
    			EntityPlayer player = (EntityPlayer)Host;
    			ItemStack itemstack = this.getEntityItem();
    			//If the player's inventory isn't full
    			if (player.inventory.addItemStackToInventory(itemstack)){
    				this.setDead();
    				return true;
    			}
    		}
    	}
        }
		return false;
    }
    
    public void setPickupDelay(short ticks)
    {
        this.delayBeforeCanPickup = ticks;
    }
    
    public boolean cannotPickup()
    {
        return this.delayBeforeCanPickup > 0;
    }
    
    
    //=========================== store item nbt ==========================//
    
    public static final DataParameter<Optional<ItemStack>> ITEM = 
    		EntityDataManager.<Optional<ItemStack>>createKey(Entity_FlyingItem.class, DataSerializers.OPTIONAL_ITEM_STACK);
    
    

    public ItemStack getEntityItem(){

    	ItemStack itemstack = (ItemStack)((Optional)this.getDataManager().get(ITEM)).orNull();

    	if (itemstack == null)
    	{ return new ItemStack(Blocks.STONE); }
    	else
    	{ return itemstack; }
    }

    /**
     * Sets the ItemStack for this entity
     */
    public void setEntityItemStack(@Nullable ItemStack stack)
    {
    	this.getDataManager().set(ITEM, Optional.fromNullable(stack));
    	this.getDataManager().setDirty(ITEM);
    }


    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.dataManager.set(HANGING, Byte.valueOf(compound.getByte("BatFlags")));
        if (compound.hasKey("PickupDelay"))
        {
            this.delayBeforeCanPickup = compound.getShort("PickupDelay");
        }
        
        NBTTagCompound nbttagcompound = compound.getCompoundTag("Item");
        this.setEntityItemStack(ItemStack.loadItemStackFromNBT(nbttagcompound));

        ItemStack item = this.getDataManager().get(ITEM).orNull();
        
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setByte("BatFlags", ((Byte)this.dataManager.get(HANGING)).byteValue());
        compound.setShort("PickupDelay", (short)this.delayBeforeCanPickup);
        
        if (this.getEntityItem() != null)
        {
            compound.setTag("Item", this.getEntityItem().writeToNBT(new NBTTagCompound()));
        }
    }
    
    protected boolean canDespawn(){ return false;}
    
    
    public static void func_189754_b(DataFixer p_189754_0_){EntityLiving.func_189752_a(p_189754_0_, "Bat");}
    
    public float getEyeHeight(){return this.height / 2.0F;}

    
}