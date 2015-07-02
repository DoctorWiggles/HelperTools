package helpertools.Utils;


import helpertools.Common.ItemRegistry;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
public class ForgeEventHandler {
  
	//Usefull debugging tool	
	/**
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerLoggedInEvent event){
    
        ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("Debug", new Object[0]);
   	 event.player.addChatComponentMessage(chatcomponenttranslation);   
   	 entityplayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_BLUE + "[Mekanism] " + " " + EnumChatFormatting.BLUE + getModeName(itemstack) + EnumChatFormatting.AQUA + " (" + getEfficiency(itemstack) + ")"));

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
		 Item Bottled = ItemRegistry.milkbottle;
		 
		 
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
	
	
}