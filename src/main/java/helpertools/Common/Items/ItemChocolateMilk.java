package helpertools.Common.Items;

import helpertools.Utils.HelpTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemChocolateMilk extends ItemFood
{
	
    public ItemChocolateMilk(int healAmount, float saturationModifier, boolean wolvesFavorite, String unlocalizedName)
    {	super(healAmount, saturationModifier, wolvesFavorite);
    
    this.maxStackSize = 16;  
    this.setUnlocalizedName(unlocalizedName);
    setCreativeTab(HelpTab.HelperTools);
    this.setAlwaysEdible();
    
    }

    @Override
    public void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
    {
    	 if (!world.isRemote){player.curePotionEffects(new ItemStack(Items.MILK_BUCKET));}
    	 
        if (player.capabilities.isCreativeMode)
        {
        	//world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            world.playSound(player, player.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
           
            return;
        }       
        else
        {        	
            //world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            world.playSound(player, player.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            
            if (!player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE)))
            {
            	//player.dropPlayerItemWithRandomChoice(new ItemStack(Items.GLASS_BOTTLE, 1, 0), false);
            	//player.dropItem(Items.GLASS_BOTTLE, 1);
            	player.entityDropItem(new ItemStack(Items.GLASS_BOTTLE, 1, 0), 1);
            }            
            
            return;
        }
    }
    
    
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.DRINK;
    }
}