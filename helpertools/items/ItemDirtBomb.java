package helpertools.items;

import helpertools.HelpTab;
import helpertools.entities.EntityDirtBombProjectile;
import helpertools.entities.EntityDynamiteProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDirtBomb extends Item {

	  public ItemDirtBomb() {
	       super();
	       this.maxStackSize = 16;  
	       setUnlocalizedName("dirtbomb");
	       //setCreativeTab(Helpertoolscore.HelperTools);
	       setCreativeTab(HelpTab.HelperTools);
	       setTextureName("helpertools:DirtBomb");
	       
	   }
	  
	  
	  @Override
	   public ItemStack onItemRightClick(ItemStack stack, World par2World,
	           EntityPlayer par3EntityPlayer) {
	           
	           float f = 6.0F;
       f = (f * f + f * 2.0F) / 3.0F;
       if( par3EntityPlayer.worldObj.isRemote){
    	   return stack;
       }
       
	  par2World.spawnEntityInWorld(new EntityDirtBombProjectile(par2World, par3EntityPlayer));
	 
		   par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		   
		   return stack;
	   }
}
