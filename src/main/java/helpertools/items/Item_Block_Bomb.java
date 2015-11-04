package helpertools.items;

import java.util.List;

import helpertools.HelpTab;
import helpertools.entities.BombProjectile_Entity;
import helpertools.entities.EntityDirtBombProjectile;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Item_Block_Bomb extends Item {

    public Item_Block_Bomb(String unlocalizedName) {
            super();
            setHasSubtypes(true);
            maxStackSize = 32; 
            setUnlocalizedName(unlocalizedName);
           // this.setCreativeTab(CreativeTabs.tabMaterials);
            setCreativeTab(HelpTab.HelperTools);
            //setTextureName("helpertools:" + unlocalizedName + "_text");
    }
    
    public IIcon[] icons = new IIcon[6];
    
    @Override
    public void registerIcons(IIconRegister reg) {
        for (int i = 0; i < 6; i ++) {
            this.icons[i] = reg.registerIcon("helpertools:" + "bomb_" + i);
        }
    }
    
    
    
    
    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta > 5)
            meta = 0;

        return this.icons[meta];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 6; i ++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName() + "_" + stack.getItemDamage();
    }
    
    
//====================================================================================================//
	  @Override
	   public ItemStack onItemRightClick(ItemStack stack, World world,
	           EntityPlayer player) {
		  
		  int type = stack.getItemDamage();
	           
	           float f = 6.0F;
     f = (f * f + f * 2.0F) / 3.0F;
     if( player.worldObj.isRemote){
  	   return stack;
     }
     if(!player.capabilities.isCreativeMode){--stack.stackSize;}
     BombProjectile_Entity Bomb = new BombProjectile_Entity(world, player, type);
     		//Bomb.Type=2;
     		world.spawnEntityInWorld(Bomb);
     		
	 
     		world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		   
     		return stack;
	   }
    
    
    
    
    
}