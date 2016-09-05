package helpertools.Common.Tools;

import helpertools.Main;
import helpertools.Common.Config;
import helpertools.Common.ItemRegistry;
import helpertools.Common.Entity.Entity_DynamiteProjectile;
import helpertools.Common.Entity.Entity_RedTorchProjectile;
import helpertools.Common.Entity.Entity_TorchProjectile;
import helpertools.Utils.InventoryUtil;
import helpertools.Utils.Texty;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ToolBase_Crossbow extends ToolBase{

	public ToolBase_Crossbow(ToolMaterial material) {
		super(material);
		}
		
	//Generic tool stuff
		@Override
		public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
		{
			if ((double)state.getBlockHardness(worldIn, pos) != 0.0D)
       		{
				stack.damageItem(4, entityLiving);
       		}

			return true;
		}
		@Override
		public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	    {
	        stack.damageItem(4, attacker);
	        return true;
	    }
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
	    Item torch = Item.getItemFromBlock(Blocks.TORCH);
	    Item redstone = Item.getItemFromBlock(Blocks.REDSTONE_TORCH);
	    Item arrow = Items.ARROW;
	    
	    /** Checks inventory if specified mode's item exists **/
	    public boolean is_Mode_Availible(ItemStack stack, EntityPlayer player, int mode) {	    	
	    	if(mode>3){return false;}
			switch (mode){
			case 0:
				if(InventoryUtil.hasItem(torch, player.inventory)){return true;}		
			break;
			case 1:
				if(InventoryUtil.hasItem(redstone, player.inventory)){return true;}		
			break;
			case 2:
				if(InventoryUtil.hasItem(ItemRegistry.dynamitebolt, player.inventory)){return true;}		
			break;					
			case 3:
				if(InventoryUtil.hasItem(arrow, player.inventory)){return true;}				
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
						//player.inventory.consumeInventoryItem(torch);
						//InventoryUtil.consumeInventoryItemStack(new ItemStack(torch), player.inventory);
						InventoryUtil.consumeItem(torch, player);						
					break;
					case 1:
						InventoryUtil.consumeItem(redstone, player);
					break;
					case 2:
						InventoryUtil.consumeItem(ItemRegistry.dynamitebolt, player);
						
					break;					
					case 3:
						InventoryUtil.consumeItem(arrow, player);
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
	    	Float sound = Main.Randy.nextFloat()+ 5F;
	    	Texty.Sound_Server(player.worldObj, player, SoundEvents.ENTITY_CHICKEN_EGG, 3F, .3F);
	    	if(Config.ToolModeMesseges){
			Texty.print(player, TextFormatting.GRAY + whatModeString(stack)+" loaded");
	    	}			
	    }
	    
	    
	    /** fires the missle **/
	    public void crossbow_FIRE(ItemStack stack,  World world, EntityPlayer player){	    	
	    	
	    	ItemStack itemstack = new ItemStack(arrow);
	    	ItemArrow itemarrow = (ItemArrow)((ItemArrow)(itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW));
                      
	    	float f = 6.0F;
	    	float vel = 3.0F;
	        f = (f * f + f * 2.0F) / 3.0F;	
	        
	        EntityThrowable SHOT;
	    	switch(getMode(stack))
			{
				case 0:
					SHOT = new Entity_TorchProjectile(world,player);					
					SHOT.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, vel, 1.0F);
					world.spawnEntityInWorld(SHOT);
				break;
				case 1:
					SHOT = new Entity_RedTorchProjectile(world,player);					
					SHOT.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, vel, 1.0F);
					world.spawnEntityInWorld(SHOT);
				break;
				case 2:
					SHOT = new Entity_DynamiteProjectile(world,player);					
					SHOT.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, vel, 1.0F);
					world.spawnEntityInWorld(SHOT);
				break;					
				case 3:
					EntityArrow entityarrow = itemarrow.createArrow(world, itemstack, player); 
					entityarrow.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, vel+1, 1.0F);
					world.spawnEntityInWorld(entityarrow);
				break;		   
			}
	    	
	    		world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	    	
	    		setTload(stack, 0);
	    		if(!player.capabilities.isCreativeMode){stack.damageItem(1, player);}
	    	
	    	}
	    /** loads the missle **/
	    public void crossbow_LOAD(ItemStack stack,  World world, EntityPlayer player){
	    	
	    	if(player.capabilities.isCreativeMode){	  
	    		
	    		setTload(stack, 2);	
	    		failedsound(world, player);
	    		return;
	    	}
	    	else{
	    		if(is_Mode_Availible(stack, player, getMode(stack))){
	    			setTload(stack, 2);
	    			failedsound(world, player);
		    		consume_item(stack, player, getMode(stack));		    		
		    		stack.damageItem(1, player);
		    		return;
		    		
		    		
	    		}	    		
	    		else{
	    			
	    			failedsound(world, player);
		   			return;
		   			}
	    	}
	    	
	    	
	    	
	    }
	    
	    /** TODO Arrow types and enchantments
	       int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                        if (j > 0)
                        {
                            entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                        if (k > 0)
                        {
                            entityarrow.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
                        {
                            entityarrow.setFire(100);
                        }
	     */

}
