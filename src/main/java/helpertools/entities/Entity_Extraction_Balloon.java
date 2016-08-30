package helpertools.entities;

import helpertools.Main;
import helpertools.Mod_Registry;
import helpertools.util.Teleport_util;
import helpertools.util.Text;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ForgeChunkManager;

public class Entity_Extraction_Balloon extends EntityLiving
{
   
    public int lift_wait = 40;
    public Entity Original_Player;
    
    public String player_id ;
    public int x = 0;
    public int y = 0;
    public int z = 0;
    

    public Entity_Extraction_Balloon(World world)
    {
        super(world);
        this.setHealth(1);    
    }
    
    //lets not please
    protected boolean canDespawn()
    {
        return false;
    }
    
    public Entity_Extraction_Balloon(World world, EntityPlayer player, int outcome, boolean resisting)
    {
        super(world);

        this.setHealth(1);
        this.Original_Player = player;   
        //getCommandSenderName()
    }
    
    public Entity_Extraction_Balloon(World world, EntityPlayer player)
    {
        super(world);

        this.setHealth(1);
        this.Original_Player = player;   
    }
    
    
    public Entity_Extraction_Balloon(World world, EntityPlayer player, int x, int y, int z)
    {
        super(world);

        this.setHealth(1);
        //this.Original_Player = player;  
        
        //this.player_id = player.getp;
        
        //this.player_id = player.getUniqueID().toString();
        this.x = x;
        this.y = y;
        this.z = z;
        
        
        
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * The number of iterations PathFinder.getSafePoint will execute before giving up.
     */
    public int getMaxSafePointTries()
    {
        return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
    }


    protected void entityInit()
    {
        super.entityInit();
       //this.dataWatcher.addObject(22, 40);
        this.dataWatcher.addObject(16, Byte.valueOf((byte) - 1));
        this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
        this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound tag)
    {
        super.writeEntityToNBT(tag);

    }

    /** delegate extraction failure **/
    public void failed_extraction(int type){
    	
    	String text= "Extraction Failed";
    	if(type == 1){
    		text = "Extraction Failed: Obstruction";
    	}
    	if(type == 2){
    		text = "Extraction Failed: Host Died";
    	}
    	if(type == 3){
    		text = "Extraction Failed: Balloon Popped";
    	}
    	
    	
    	if(!this.worldObj.isRemote){
    		EntityItem balloon= new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(Mod_Registry.extraction_balloon, 1, 0));
    		this.worldObj.spawnEntityInWorld(balloon);
    		Text.out(text);
    		}
    	
    	
    	
    	
    	this.setDead();
    }
    
    
    public void readEntityFromNBT(NBTTagCompound tag)
    {
        super.readEntityFromNBT(tag);
       // this.dataWatcher.updateObject(17, Byte.valueOf((byte)(tag.getBoolean("powered") ? 1 : 0)));

        
        lift_wait = tag.getInteger("lift_wait");
      
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {

    	if(!this.worldObj.isRemote){
 	   //Text.out(this.posY);
    	}
    	/*
    	ForgeChunkManager.Ticket ticket 
    	= ForgeChunkManager.requestTicket(Main.instance, this.worldObj, ForgeChunkManager.Type.ENTITY);
    	
    	if( ticket!= null){
    		Chunk chunk = worldObj.getChunkFromBlockCoords( (int)this.posX,  (int)this.posZ);
    		ChunkCoordIntPair pair = new ChunkCoordIntPair((int)this.posX,  (int)this.posZ);
    		ForgeChunkManager.forceChunk(ticket, pair);
    	};
 	   */
    	this.lift_wait = lift_wait -1;
    	//Text.out("UHHHHHH");
    	//this.setDead();
    	Entity target;
    	if( this.ridingEntity !=null){
    		target = this.ridingEntity;
    		
    		
    		float lift = 0.1f;
    		
    		//lift = 0.090f;
    		//lift = 0.085f;
    		lift = 0.0825f;
    		//Text.out("LIFT OFF");
    		
    		if(lift_wait <= 0){
    			lift = 0.12f;
    		}
    		
    		//if(lift_wait <= -1200){lift = 0.2f;}
    		//Text.out(lift_wait);
    		target.addVelocity(0, lift, 0);
    		
    		if(this.posY > 150){
        		
    			
        		//target.t
        		int x1 = 88;
        		x1= -999;
        		//x1 = 100;
        		float y1 = 6.5F;
        		int z1 = 255;
        		z1 = -998;
        		
        		x1 = this.x;
        		y1 = this.y+1;
        		z1 = this.z;
        		
        		//dimensional code - overlook for copy data
        		//target.travelToDimension(this.dimension);
        		//Text.out(this.dimension);
        		//target.travelToDimension(0);        		
        		//target = Teleport_util.move_to_dim2(target, 0);
        		
        		target.setLocationAndAngles((double)x1, (double)y1, (double)z1, target.rotationYaw, 0.0F);
        		target.motionX = target.motionY = target.motionZ = 0.0D;
        		
        		
        		if(this.worldObj.isRemote){        		
        		Text.out("Extraction Successful");
        		//Text.out(this.Original_Player);
        		}
        		
        		this.setDead();
        		}
    	}
    	if( this.ridingEntity == null && lift_wait <= 0 &&this.isEntityAlive()){    		
    		failed_extraction(2);
    	}
    	if(!this.isInsideOfMaterial(Material.air) &&this.isEntityAlive()){
    		failed_extraction(1);
    	}
    	if(this.getHealth()==0){
    		failed_extraction(3);
    	}
    	
    	
    	

        super.onUpdate();
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.creeper.say";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.creeper.death";
    }

    protected boolean interact(EntityPlayer p_70085_1_)
    {
		return true;
    }


}