package helpertools.Com.Entity;

import helpertools.Com.Config;
import helpertools.Utils.ModUtil;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Entity_Mirage extends EntityLiving{

	public Entity_Mirage(World worldIn) {
		super(worldIn);
	}
	
	public Entity_Mirage(World worldIn, UUID ID, EntityPlayer player) {
		super(worldIn);
		this.setID(ID);
		if(Config.Render_Mirage_Gear){
		this.setHeld(player);
		this.setArmor(EntityEquipmentSlot.HEAD, player);
		this.setArmor(EntityEquipmentSlot.CHEST, player);
		this.setArmor(EntityEquipmentSlot.LEGS, player);
		this.setArmor(EntityEquipmentSlot.FEET, player);
		}
	}
				
	protected void initEntityAI()
    {
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }
	
	protected void entityInit() {
		super.entityInit();		
		this.dataManager.register(player, String.valueOf(""));
	}
	
	public void setHeld(EntityPlayer player){
		if(player.getHeldItemMainhand() != null){
			this.setHeldItem(EnumHand.MAIN_HAND, player.getHeldItemMainhand().copy());
		}
		if(player.getHeldItemOffhand() != null){
			this.setHeldItem(EnumHand.OFF_HAND, player.getHeldItemOffhand().copy());
		}
	}
	
	public void setArmor(EntityEquipmentSlot slot, EntityPlayer player){
		if(player.getItemStackFromSlot(slot) != null){
		this.setItemStackToSlot(slot, player.getItemStackFromSlot(slot).copy());
		}
	}
	
	public boolean canBePushed(){return false;}
	protected void collideWithEntity(Entity entityIn){}
    protected void collideWithNearbyEntities(){}
    public boolean canBreatheUnderwater(){return true;}
    public boolean isPushedByWater(){return false;}
    
    @Override
    protected boolean canDropLoot(){ return false;}
    @Override
    protected void dropEquipment(boolean flag, int chance) {}
    
    @Override
	public void onUpdate(){
		super.onUpdate();
		
		if(!this.worldObj.isRemote){
			if(this.getPlayer() != null){
				EntityLivingBase player = this.getPlayer();
				if(player.isSneaking()){
					this.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 5, 1, true, true));
				}
				
			}
			if(this.getPlayer() == null){this.setDead();}
		}
		 
		 
		BlockPos pos = this.getPosition();
    	for (int i = 0; i < this.rand.nextInt(7); ++i)
    		ModUtil.Particle(1.5F, this, EnumParticleTypes.SMOKE_LARGE,0,-0.05,0);
		
    }
	
	private static final DataParameter<String> player = EntityDataManager.<String>createKey(Entity_Mirage.class, DataSerializers.STRING);
	
	@Nullable
    public EntityLivingBase getPlayer()
    {
        try{
            UUID uuid = this.getplayerID();
            return uuid == null ? null : this.worldObj.getPlayerEntityByUUID(uuid);
        }
        catch (IllegalArgumentException var2)
        {
            return null;
        }
    }
	
	
	public String getplayerString() {		
		return this.dataManager.get(player);
	}
	
	public UUID getplayerID(){		
		return UUID.fromString(getplayerString());		
	}
		
	public void setID(UUID par){
		this.setplayer(par.toString());
	}
	
	public void setplayer(String par1) {		
		this.dataManager.set(player, String.valueOf(par1));
	}
	
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("player")) {
			String b0 = tag.getString("player");
			this.setplayer(b0);
		}
	}	
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setString("player", this.getplayerString());
	}
	
	protected boolean canDespawn(){return false; }


}
