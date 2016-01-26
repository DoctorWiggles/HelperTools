package helpertools.items;

import helpertools.HelpTab;
import helpertools.entities.Entity_Extraction_Balloon;
import helpertools.util.Text;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Item_Extraction_Balloon extends Item{

	public Item_Extraction_Balloon(String name) {
	       super();
	       this.maxStackSize = 1;  
	       setUnlocalizedName(name);
	       setCreativeTab(HelpTab.HelperTools);
	       setTextureName("helpertools:extraction_balloon");
	       
	   }
	
	
	 
	    @Override
	    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity)
	    {
	    	World world = player.worldObj;
	    	int outcome = 100;
	    	boolean resisting = false;
	    	
	        if (entity.worldObj.isRemote)
	        {
	            return false;
	        }
	        if (!(entity instanceof EntityPlayer) && !(player.isSneaking()))
	        	
	        {
	        	Float Maxhp = entity.getMaxHealth();
        		Float hp = entity.getHealth();
        		
        		Text.out("Max hp: "+Maxhp +" current :"+ hp);
        		
	        	if(world.isRaining()){
	        		outcome = outcome - 10;
	        	}
	        	if(world.isThundering()){
	        		outcome = outcome - 10;
	        	}	        	
	        	if(hp <= (Maxhp/4)){
	        		outcome = outcome - 10;
	        	}
	        	
	        	
        		
	        	if(entity instanceof EntityMob || entity instanceof IMob){
	        		///Float Maxhp = entity.getMaxHealth();
	        		//Float hp = entity.getHealth();
	        		
	        		//Text.out("Max hp: "+Maxhp +" current :"+ hp);
	        		if(!(hp <= (Maxhp/2))){
	        			
	        			Text.out("weakon mob first");
	        			resisting = true;
	        			return false;
	        			
	        		}
	        		//world.
	        		if(entity instanceof IBossDisplayData){
	        			outcome = outcome - 20;
	        		}
	        		
	        	}
	        	
	        	
	        	
	        	Entity_Extraction_Balloon balloon = new Entity_Extraction_Balloon(entity.worldObj);
	            balloon.setLocationAndAngles(entity.posX,entity.posY,entity.posZ, entity.rotationYaw, 0F);
	            //balloon.onSpawnWithEgg((IEntityLivingData)null);
	            entity.worldObj.spawnEntityInWorld(balloon);
	            balloon.mountEntity(entity);
	            
	            Text.out(outcome);
	        	
	        	/*
	        	EntityPlayer entityskeleton = EntityPlayer;
	        //EntitySkeleton entityskeleton = new EntitySkeleton(this.worldObj);
	        entityskeleton.setLocationAndAngles(this.posX, this.posY + 100, this.posZ, this.rotationYaw, 0.0F);
	        entityskeleton.onSpawnWithEgg((IEntityLivingData)null);
	        this.worldObj.spawnEntityInWorld(entityskeleton);
	        entityskeleton.mountEntity(this);
	        */
	        	return true;
	            }
	        if (!(entity instanceof EntityPlayer) && (player.isSneaking()))
	        	
	        {
	        	//entity.dismountEntity(player);
	        	//entity.setLocationAndAngles(player.posX, player.posY -3, player.posZ, player.rotationYaw, 0.0F);
	        	//entity.mountEntity(player);
	        	//entity.setLocationAndAngles(player.posX, player.posY -3, player.posZ, player.rotationYaw, 0.0F);
	            
	        	
	        	return true;
	            }
	            
	        return false;
	               
	        
	    }
	
		
	
}
