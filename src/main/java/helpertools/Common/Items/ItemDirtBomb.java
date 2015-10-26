package helpertools.Common.Items;


import helpertools.Common.ItemRegistry;
import helpertools.Common.Entity.Entity_DirtBombProjectile;
import helpertools.Utils.HelpTab;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDirtBomb extends Item {

	  public ItemDirtBomb(String unlocalizedName) {
	       super();
	       this.maxStackSize = 32;  
	       setUnlocalizedName("dirtbomb");
	       //setCreativeTab(Helpertoolscore.HelperTools);
	       setCreativeTab(HelpTab.HelperTools);
	       setUnlocalizedName(unlocalizedName);
	       
	   }
	  
	  
	  @Override
	   public ItemStack onItemRightClick(ItemStack stack, World world,
	           EntityPlayer player) {
	           
	           float f = 6.0F;
       f = (f * f + f * 2.0F) / 3.0F;
       
       if( player.worldObj.isRemote){
    	   return stack;
       }
       if(!player.capabilities.isCreativeMode){--stack.stackSize;}
       //consumeInventoryItem(ItemRegistry.dirtbomb);
       
       //Entity_DirtBombProjectile Bomb = new Entity_DirtBombProjectile(world, player);
       world.spawnEntityInWorld(new Entity_DirtBombProjectile(world, player));
       //Entity_DirtBombProjectile Bomb = new Entity_DirtBombProjectile(world, player);
       		//Bomb.Type=2;
       		//world.spawnEntityInWorld(Bomb);
       		
	 
       		world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		   
       		return stack;
	   }
}
