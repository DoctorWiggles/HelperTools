package helpertools.handlers;
import helpertools.Mod_Registry;
import helpertools.Main;
import helpertools.entities.BombProjectile_Entity;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class ForgeEventHandler {
  
	//Usefull debugging tool	
	/**
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerLoggedInEvent event){
    
        ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("Debug", new Object[0]);
   	 event.player.addChatComponentMessage(chatcomponenttranslation);   
   	 entityplayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_BLUE + "[Mekanism] " + " " 
   	 + EnumChatFormatting.BLUE + getModeName(itemstack) + EnumChatFormatting.AQUA + " (" + getEfficiency(itemstack) + ")"));

 	System.out.println("Logging in");
   	event.player.entityDropItem(new ItemStack(Blocks.torch), 0.0F);
      
    }
    **/
	////////////////////////////////////
	/** credit Wuppy & ToolTorchMod**/
	//////////////////////////////////
	
	@SubscribeEvent
	public void milkbottlehandler(EntityInteractEvent event){

    	
		if (event.isCanceled() ||  !(event.target instanceof EntityCow)) { 
		      return;
		    }
		
		ItemStack heldItem = event.entityPlayer.inventory.getCurrentItem();
		 if ((heldItem == null) || (!(heldItem.getItem() instanceof ItemGlassBottle))) {
		      return;
		    }
		 Item Bottled = Mod_Registry.bottledmilk;
		 
		 
		 if (heldItem.stackSize-- == 1)
         {
			 event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, new ItemStack(Bottled));
         }
         else if (!event.entityPlayer.inventory.addItemStackToInventory(new ItemStack(Bottled)))
         {
        	 event.entityPlayer.dropPlayerItemWithRandomChoice(new ItemStack(Bottled, 1, 0), false);
         }

		 event.setCanceled(true);
	}
	
	
	
	/**
	public void onBucketFill(FillBucketEvent event) {
		if (event.target.getblock = HelpertoolsRegistry.jelly_block){
			
		}
		
		
		
	}
	**/
	
	/*
	@SubscribeEvent
	public void handleConstruction(EntityConstructing event){
		 
	    if(event.entity instanceof BombProjectile_Entity){
	    	BombProjectile_Entity ent = (BombProjectile_Entity) event.entity;
	    	if(ent.worldObj.isRemote){ return;}
	        DataWatcher dw = ent.getDataWatcher();	        
	        dw.updateObject(22, ent.type());
	     //   System.out.println(ent.Bomb_Type+ "event");
	        
	    }
	}
	 
	 */
	
	 
	 
	 
}