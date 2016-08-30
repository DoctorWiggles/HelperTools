package helpertools.items;

import helpertools.HelpTab;
import helpertools.Main;
import helpertools.entities.Entity_Extraction_Balloon;
import helpertools.gui.Gui_Handler;
import helpertools.network.fob.Forward_Operating_Base;
import helpertools.util.Text;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class Item_Extraction_Balloon extends Item{

	public Item_Extraction_Balloon(String name) {
	       super();
	       this.maxStackSize = 16;  
	       setUnlocalizedName(name);
	       setCreativeTab(HelpTab.HelperTools);
	       setTextureName("helpertools:extraction_balloon");
	       
	   }
	
	
	 
	    @Override
	    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity)
	    {
	    	
	   //   String.valueOf(obj)
	    	World world = player.worldObj;
	    	int outcome = 100;
	    	boolean resisting = false;
	    	
	        if (entity.worldObj.isRemote)
	        {
	            return false;
	        }
	        if(entity instanceof EntityPlayer || entity instanceof Entity_Extraction_Balloon){
	    		return false;
	    	}
	        
	        if(entity.riddenByEntity instanceof Entity_Extraction_Balloon){
	        	return false;
	        }
	        if (!(entity instanceof EntityPlayer) && !(player.isSneaking()))
	        	
	        {
	        	Float Maxhp = entity.getMaxHealth();
        		Float hp = entity.getHealth();
        		
        		//Text.out("Max hp: "+Maxhp +" current :"+ hp);
        		
	        	if(world.isRaining()){
	        		outcome = outcome - 10;
	        	}
	        	if(world.isThundering()){
	        		outcome = outcome - 10;
	        	}	        	
	        	if(hp <= (Maxhp/4)){
	        		outcome = outcome - 10;
	        	}
	        	
	        	
        		/*
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
	        	*/
	        	this.attach_balloon(entity, player, outcome);
	        	/**
	        	
	        	Entity_Extraction_Balloon balloon = new Entity_Extraction_Balloon(entity.worldObj);
	            balloon.setLocationAndAngles(entity.posX,entity.posY,entity.posZ, entity.rotationYaw, 0F);
	            //balloon.onSpawnWithEgg((IEntityLivingData)null);
	            entity.worldObj.spawnEntityInWorld(balloon);
	            balloon.mountEntity(entity);
	            
	            Text.out(outcome);
	        	**/
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
	    @Override
	    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	    //public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float f1, float f2, float f3)
	    {
	    	
	    	ChunkCoordinates coord;
    		coord = player.getPlayerCoordinates();
    		
    		
	    	int x = (int)(coord.posX);
	    	int y = (int)player.posY;
	    	int z = (int)(coord.posZ);
	    	if (!player.isSneaking())	        	
	        {
	    		if (!world.isRemote) {
	        
	    		//Forward_Operating_Base.registerMailbox("Tokyo", x, y, z);
	    		
	    		Forward_Operating_Base data = Forward_Operating_Base.forWorld(world);
	    		
	    		//Text.out(" ");
	    		
	    		//Text.out("before: "+ data.getMailboxesCount(), EnumChatFormatting.GRAY);
	    		System.out.println("before: "+ data.getMailboxesCount());
	    		
	    		String stringy = ("Saint Brazil");
	    		//stringy = "Roaming Mexico";
	    		stringy = "Deadly Merrows";
	    		
	    		if(data.isNameFree(stringy)){
	    		data.registerMailbox(stringy, x, y, z);
	    		}
	    		
	    		//Text.out("after: "+ data.getMailboxesCount(), EnumChatFormatting.GREEN);
	    		System.out.println("after: "+ data.getMailboxesCount());
	    				
	    		//data.unregisterMailbox("Tokyo");
	    		
	    		//Text.out("deleted: "+ data.getMailboxesCount(), EnumChatFormatting.RED);
	    				System.out.println("deleted: "+ data.getMailboxesCount());
	    		
	    		data.debug_list();
	        	}
	        }
	    	
	    	
	    	if (player.isSneaking())	        	
	        {
	    		 if (world.isRemote) {
	    		        player.openGui(Main.instance, Gui_Handler.Extraction_Selection_Gui, world, x, y, z);
	    		    }
	        }
	    	
	        return stack;
	    }
	
	    
	    public void attach_balloon(EntityLivingBase entity, EntityPlayer player, int outcome){
	    	
	    	entity.setLocationAndAngles(entity.posX,entity.posY+1.25F,entity.posZ, entity.rotationYaw, 0F);
	    	//Wrapper function that sets mobs to not despawn
	    	((EntityLiving) entity).func_110163_bv();
	    	//((EntityLiving) entity).persistenceRequired = true;
	    	
	    	//Text.out(entity.posY);
	    	//int a =0;
	    	//if(a==0)return;
	    	//Entity_Extraction_Balloon balloon = new Entity_Extraction_Balloon(entity.worldObj, player);
	    	
	    	NBTTagCompound tag = player.getEntityData();
	    	
	    	NBTBase xTag = tag.getTag("Balloon_X");
			int ox = ((NBTTagInt)xTag).func_150287_d();
			
			NBTBase yTag = tag.getTag("Balloon_Y");
			int oy = ((NBTTagInt)yTag).func_150287_d();
			
			NBTBase zTag = tag.getTag("Balloon_Z");
			int oz = ((NBTTagInt)zTag).func_150287_d();
	    	
	    	 
	    	Entity_Extraction_Balloon balloon = new Entity_Extraction_Balloon(entity.worldObj, player, ox, oy, oz);
	    		    	
            balloon.setLocationAndAngles(entity.posX,entity.posY,entity.posZ, entity.rotationYaw, 0F);
            balloon.onSpawnWithEgg((IEntityLivingData)null);
            entity.worldObj.spawnEntityInWorld(balloon);
            balloon.mountEntity(entity);
            
           // Text.out(outcome);
            
         
	    	
	    	return;
	    }
		
	
}
