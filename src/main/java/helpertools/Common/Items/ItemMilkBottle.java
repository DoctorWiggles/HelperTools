package helpertools.Common.Items;

import helpertools.Utils.HelpTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMilkBottle extends Item
{
   

    public ItemMilkBottle(String unlocalizedName)
    {
    	super();        
        this.maxStackSize = 16;  
	    setUnlocalizedName(unlocalizedName);
        setCreativeTab(HelpTab.HelperTools);
	   
    }
    @Override
    public ItemStack onItemUseFinish(ItemStack p_150910_1_, World p_77654_2_, EntityPlayer p_150910_2_)
    {
    	 if (!p_77654_2_.isRemote)
         {
             //p_77654_3_.curePotionEffects(p_77654_1_);
    		 p_150910_2_.curePotionEffects(new ItemStack(Items.milk_bucket));
    		 p_77654_2_.playSoundAtEntity(p_150910_2_, "random.burp", 0.5F, p_77654_2_.rand.nextFloat() * 0.1F + 0.9F);
             //p_77654_3_.setFire(0);
             //p_77654_3_.extinguish();
             
         }
    	 
        if (p_150910_2_.capabilities.isCreativeMode)
        {
            return p_150910_1_;
        }
        else if (--p_150910_1_.stackSize <= 0)
        {
            return new ItemStack(Items.glass_bottle);
        }
        else
        {
            if (!p_150910_2_.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle)))
            {
                p_150910_2_.dropPlayerItemWithRandomChoice(new ItemStack(Items.glass_bottle, 1, 0), false);
            }

            return p_150910_1_;
        }
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.DRINK;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        return p_77659_1_;
    }
}