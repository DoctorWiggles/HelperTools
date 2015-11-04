package helpertools.items;

import helpertools.HelpTab;
import helpertools.entities.EntityDirtBombProjectile;
import helpertools.entities.EntityDynamiteProjectile;
import helpertools.entities.EntityGravelBombProjectile;
import helpertools.entities.EntitySandBombProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGravelBomb extends Item {

	  public ItemGravelBomb() {
	       super();
	       this.maxStackSize = 32;  
	       setUnlocalizedName("gravelbomb");
	       //setCreativeTab(Helpertoolscore.HelperTools);
	       setCreativeTab(HelpTab.HelperTools);
	       setTextureName("helpertools:GravelBomb");
	       
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
       EntityGravelBombProjectile Bomb = new EntityGravelBombProjectile(world, player);
       		//Bomb.Type=2;
       		world.spawnEntityInWorld(Bomb);
       		
	 
       		world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		   
       		return stack;
	   }
}
