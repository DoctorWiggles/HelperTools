package helpertools.util;
import helpertools.Helpertoolscore;
import net.minecraft.entity.passive.EntityCow;
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
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class ForgeEventHandler {
  
	//Usefull debugging tool
	//Stick into an action like below or around ticking things to spot pesky problems.
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
	/** @credit Wuppy & ToolTorchMod**/
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
		 Item Bottled = Helpertoolscore.bottledmilk;
		 
		 
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
	  @SubscribeEvent
	  public void playerInteractEventHandler(PlayerInteractEvent event)
	  {
	    if ((event.isCanceled()) || (event.world.isRemote) || (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
	      return;
	    }
	    ItemStack heldItem = event.entityPlayer.field_71071_by.func_70448_g();
	    if ((heldItem == null) || (!(heldItem.func_77973_b() instanceof ItemTool))) {
	      return;
	    }
	    int oldSlot = event.entityPlayer.field_71071_by.field_70461_c;
	    if ((oldSlot < 0) || (oldSlot > 8)) {
	      return;
	    }
	    int newSlot = this.slots[oldSlot];
	    if ((newSlot < 0) || (newSlot > 8)) {
	      return;
	    }
	    ItemStack slotStack = event.entityPlayer.field_71071_by.func_70301_a(newSlot);
	    if (slotStack == null) {
	      return;
	    }
	    event.entityPlayer.field_71071_by.field_70461_c = newSlot;
	    if (D3Core.debug()) {
	      this.logger.info("Player: " + event.entityPlayer.getDisplayName() + "\tOldSlot: " + oldSlot + "\tOldStack: " + slotStack);
	    }
	    boolean b = ((EntityPlayerMP)event.entityPlayer).field_71134_c.func_73078_a(event.entityPlayer, event.world, slotStack, event.x, event.y, event.z, event.face, 0.5F, 0.5F, 0.5F);
	    if (slotStack.field_77994_a <= 0) {
	      slotStack = null;
	    }
	    if (D3Core.debug()) {
	      this.logger.info("Player: " + event.entityPlayer.getDisplayName() + "\tNewSlot: " + newSlot + "\tNewStack: " + slotStack + "\tResult: " + b);
	    }
	    event.entityPlayer.field_71071_by.field_70461_c = oldSlot;
	    
	    event.entityPlayer.field_71071_by.func_70299_a(newSlot, slotStack);
	    ((EntityPlayerMP)event.entityPlayer).field_71135_a.func_147359_a(new S2FPacketSetSlot(0, newSlot + 36, slotStack));
	    
	    event.setCanceled(true);
	  }
	  **/
	
	    
	    
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}