package helpertools.Common.Tools;

import helpertools.Main;
import helpertools.Common.ItemRegistry;
import helpertools.Common.Entity.Entity_DynamiteProjectile;
import helpertools.Common.Entity.Entity_RedTorchProjectile;
import helpertools.Common.Entity.Entity_TorchProjectile;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ToolBase_Crossbow extends ItemSpade{

	public ToolBase_Crossbow(ToolMaterial material) {
		super(material);
		}
		
	
	//Generic tool stuff
		public boolean onBlockDestroyed(ItemStack stack, World world, Block theblock, BlockPos pos1, EntityLivingBase entity)
	    {
	        if ((double)theblock.getBlockHardness(world, pos1) != 0.0D)
	        {
	            stack.damageItem(400, entity);
	        }
	        return true;
	    }
		
		public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2)
	    {
			stack.damageItem(4, entity2);
	        return true;
	    }
		
		@Override
		   public boolean onItemUse(ItemStack thestaff, EntityPlayer theplayer, World world, BlockPos pos, EnumFacing theface, float fty1, float fty2, float fty3)
			{return false;}
		
		/**Established nbt factors **/
		public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isheld) {
	    	
	    	if (!stack.hasTagCompound()) {
	    		stack.setTagCompound(new NBTTagCompound()); 
	    		stack.getTagCompound().setInteger("Tload", 0);
	    		stack.getTagCompound().setInteger("mode", 0);    		
	    		//stack.getTagCompound().setInteger("ToolLevel", 0);
	    		//stack.getTagCompound().setInteger("OffMode", 0);  
	    		}	    	   		
	    	//if(isheld){	stack.setItemDamage(getMode(stack));}
	    	
		}
		//Modes and load factors
		public int getMode(ItemStack itemStack)
			{
			return itemStack.getTagCompound().getInteger("mode");
			}
		   
		public void setMode(ItemStack itemStack, int Value)
			{
			itemStack.getTagCompound().setInteger("mode",  Value );				
			} 
		
		public int getTload(ItemStack itemStack)
			{
			return itemStack.getTagCompound().getInteger("Tload");
			}
	   
		public void setTload(ItemStack itemStack, int Value)
			{
			itemStack.getTagCompound().setInteger("Tload",  Value );				
			} 
		


		/** Name conversion for modes **/
		public String whatModeString(ItemStack stack){	  
	    	String modestring = "null";

	    	if (getMode(stack) == 0){
	    		modestring = "Torch";
	    	}
	    	else if(getMode(stack) == 1){
	    		modestring = "Redstone";
	    	}
	    	else if(getMode(stack) == 2){
	    		modestring = "Dynamite";
	    	}
	    	else if(getMode(stack) == 3){
	    		modestring = "Arrow";
	    	}
	    	else{
	    		modestring = "null";
	    	}  
	    	return modestring;
	    };
	    
	    //Item Gallery
	    Item torch = Item.getItemFromBlock(Blocks.torch);
	    Item redstone = Item.getItemFromBlock(Blocks.redstone_torch);
	    //Item dynamite = ItemRegistry.dynamitebolt; - doesn't register for some reason �\_(*-*)_/�
	    Item arrow = Items.arrow;
	    
	    /** Checks inventory if specified mode's item exists **/
	    public boolean is_Mode_Availible(ItemStack stack, EntityPlayer player, int mode) {
	    	
	    	if(mode>3){return false;}
			switch (mode){
			case 0:
				if(player.inventory.hasItem(torch)){return true;}		
			break;
			case 1:
				if(player.inventory.hasItem(redstone)){return true;}		
			break;
			case 2:
				if(player.inventory.hasItem(ItemRegistry.dynamitebolt)){return true;}		
			break;					
			case 3:
				if(player.inventory.hasItem(arrow)){return true;}				
			break;			
			}
			return false;
		}
	    /** refunds item while loaded and switching **/
	    public void refund_item(ItemStack stack, EntityPlayer player, int mode) {
	    	
	    	 if(mode>3){return;}
				switch (mode){
				case 0:
					player.entityDropItem(new ItemStack(torch), 0.0F);		
				break;
				case 1:
					player.entityDropItem(new ItemStack(redstone), 0.0F);	
				break;
				case 2:
					player.entityDropItem(new ItemStack(ItemRegistry.dynamitebolt), 0.0F);	
				break;					
				case 3:
					player.entityDropItem(new ItemStack(arrow), 0.0F);				
				break;			
				}
				return;
		}
	    public void consume_item(ItemStack stack, EntityPlayer player, int mode) {	    	
	    	 if(mode>3){return;}
	    	 	switch(mode)
				{
					case 0:
						player.inventory.consumeInventoryItem(torch);
					break;
					case 1:
						player.inventory.consumeInventoryItem(redstone);	
					break;
					case 2:
						player.inventory.consumeInventoryItem(ItemRegistry.dynamitebolt);
					break;					
					case 3:
						player.inventory.consumeInventoryItem(arrow);
					break;		   
				}
				return;
		}
	    
	    /** Mode switching function **/
	    public boolean Transfer_Mode(ItemStack stack, World world, EntityPlayer player){
	    	int Modo = getMode(stack);
	    	
	    	if(player.capabilities.isCreativeMode){	    		
	    		Modo++;
	    		if (Modo>3){
	    			Modo = 0;
	    		}
	    		setMode(stack, Modo);
	    		Transfer_Effect(stack, player);
	    		return true;
	    	}
	    	else{
	    		
	    		int Next_Modo = getMode(stack);
	    		int offy;
	    		for(offy= 0; offy < 3; ++offy) {
	    			if(mode_try(stack, world, player,Modo, Next_Modo, offy)){
	    				return true;}
	    		}
	    		
	    		return false;	    		
	    	}	
	    	
	    	
	    }
	    
	    /** See's if the next suggested mode is valid **/
	    public boolean mode_try(ItemStack stack, World world, EntityPlayer player,int Modo,int Next_Modo, int offset){
	    	int New_Modo = Next_Modo + offset;
	    	New_Modo++;
    		if (New_Modo>3){
    			New_Modo = New_Modo - 4;
    		}
    		if(is_Mode_Availible(stack, player, New_Modo)){
    			//swaps-refunds items while loaded
    			if(getTload(stack) ==2){
    				refund_item(stack, player, Modo);
    				consume_item(stack, player, New_Modo);
    				}
    			setMode(stack, New_Modo);
    			Transfer_Effect(stack, player);
    			return true;
    		}
    		else{
    			return false;
    		}
	    }
	    
	    

	    /** Static special effect clause **/
		public void Transfer_Effect(ItemStack stack, EntityPlayer player){
	    	Float sound = Main.Randy.nextFloat()+ 5F; //1f
	    	player.worldObj.playSoundAtEntity(player, "mob.chicken.plop", sound, 3.0F);			
			ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + whatModeString(stack)+" loaded", new Object[0]);
			player.addChatComponentMessage(chatcomponenttranslation);
	    }
	    
	    
	    /** fires the missle **/
	    public void crossbow_FIRE(ItemStack stack,  World world, EntityPlayer player){
	    	
	    	EntityArrow entityarrow = new EntityArrow(world, player, 1 * 2.0F);
	    	float f = 6.0F;
	        f = (f * f + f * 2.0F) / 3.0F;	    	
	    	switch(getMode(stack))
			{
				case 0:
					world.spawnEntityInWorld(new Entity_TorchProjectile(world, player));
				break;
				case 1:
					world.spawnEntityInWorld(new Entity_RedTorchProjectile(world, player));
				break;
				case 2:
					world.spawnEntityInWorld(new Entity_DynamiteProjectile(world, player));
				break;					
				case 3:
					world.spawnEntityInWorld(entityarrow);
				break;		   
			}
	    	 world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			 setTload(stack, 0);
			 if(!player.capabilities.isCreativeMode){stack.damageItem(1, player);}
	    	
	    }
	    /** loads the missle **/
	    public void crossbow_LOAD(ItemStack stack,  World world, EntityPlayer player){
	    	
	    	if(player.capabilities.isCreativeMode){
	    		setTload(stack, 2);
	    		world.playSoundAtEntity(player, "fire.ignite",.4F, itemRand.nextFloat() * 0.4F + 0.8F);	
	    		return;
	    	}
	    	else{
	    		if(is_Mode_Availible(stack, player, getMode(stack))){	    			
	    			setTload(stack, 2);
	    			world.playSoundAtEntity(player, "fire.ignite",.4F, itemRand.nextFloat() * 0.4F + 0.8F);
		    		consume_item(stack, player, getMode(stack));		    		
		    		stack.damageItem(1, player);
		    		return;
		    		
		    		
	    		}
	    		else{
	    			world.playSoundAtEntity(player, "fire.ignite",4F, itemRand.nextFloat() * 0.4F + 0.9F);
		   			return;
		   			}
	    	}
	    	
	    	
	    	
	    }

}
