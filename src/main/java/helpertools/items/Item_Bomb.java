package helpertools.items;

import helpertools.HelpTab;
import helpertools.entities.Bomb_Projectile;
import helpertools.util.InventoryUtil;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Item_Bomb extends Item {

    public Item_Bomb(String unlocalizedName) {
            super();
            setHasSubtypes(true);
            maxStackSize = 32; 
            setUnlocalizedName(unlocalizedName);
           // this.setCreativeTab(CreativeTabs.tabMaterials);
            setCreativeTab(HelpTab.HelperTools);
            //setTextureName("helpertools:" + unlocalizedName + "_text");
    }
    
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
    {	int type = stack.getItemDamage();
    
    	String line = "";
    	String line2 = "x";
    	switch(type){
    	case 0: line = "Lays a blob of dirt";break;
    	case 1: line = "Lays a blob of sand";break;
    	case 2: line = "Lays a blob of gravel";break;
    	case 3: line = "Releases fertilizer and decays stone";break;
    	case 4: line = "Freezes liquids and lays snow";break;
    	case 5: line = "Weathers stone, dirt, plants";
    			line2 = "- and melts frozen blocks";break;
    	case 6: line = "Releases spores";break;
    	case 7: line = "Corrupts area";break;
    	case 8: line = "Plants Forest Vegetation";break;
    	case 9: line = "Plants Boreal Vegetation";break;
    	default:
    		break;
    	}
    	par3List.add(EnumChatFormatting.ITALIC + line);  
    	if(line2 != "x"){par3List.add(EnumChatFormatting.ITALIC + line2);  }
    }
    
    
    //Number of bomb variants
    //int B = 10;
    int B = 7;
    
    
    public IIcon[] icons = new IIcon[B];
    
    @Override
    public void registerIcons(IIconRegister reg) {
        for (int i = 0; i < B; i ++) {
            this.icons[i] = reg.registerIcon("helpertools:" + "bomb_" + i);
        }
    }
    
    
    
    
    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta > B-1)
            meta = 0;

        return this.icons[meta];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < B; i ++) {
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
		  
		  int type ;
		  type = stack.getItemDamage();
		  int amp = 0;
	           
	           float f = 6.0F;
     f = (f * f + f * 2.0F) / 3.0F;
     if( player.worldObj.isRemote){return stack;}
     
     amp = (InventoryUtil.check_cbc_charms(player.inventory))*5;
     
     if(!player.capabilities.isCreativeMode){--stack.stackSize;}
     Bomb_Projectile Bomb = new Bomb_Projectile(world, player, type, amp);
     		//Bomb.Type=2;
     		world.spawnEntityInWorld(Bomb);
     		
	 
     		world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		   
     		return stack;
	   }
	  
	 
    
}