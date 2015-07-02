package helpertools.Common.Items;

import helpertools.Utils.HelpTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemChocolateMilk extends ItemFood
{
	
    public ItemChocolateMilk(int healAmount, float saturationModifier, boolean wolvesFavorite, String unlocalizedName)
    {	super(healAmount, saturationModifier, wolvesFavorite);
    
    this.maxStackSize = 16;  
    this.setUnlocalizedName(unlocalizedName);
    setCreativeTab(HelpTab.HelperTools);
    

    //this.effects = effects;
    }
    
    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
    super.onFoodEaten(stack, world, player);
           return;
  
    }
    

    
    public ItemStack onEaten(ItemStack p_150910_1_, World p_77654_2_, EntityPlayer p_150910_2_)
    {
    	 if (!p_77654_2_.isRemote)
         {
    		 p_150910_2_.curePotionEffects(new ItemStack(Items.milk_bucket));
            
             
         }
    	 
        if (p_150910_2_.capabilities.isCreativeMode)
        {
        	p_150910_2_.getFoodStats().addStats(this, p_150910_1_);
            p_77654_2_.playSoundAtEntity(p_150910_2_, "random.burp", 0.5F, p_77654_2_.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(p_150910_1_, p_77654_2_, p_150910_2_);
            return p_150910_1_;
        }
        else if (--p_150910_1_.stackSize <= 0)
        {
        	p_150910_2_.getFoodStats().addStats(this, p_150910_1_);
            p_77654_2_.playSoundAtEntity(p_150910_2_, "random.burp", 0.5F, p_77654_2_.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(p_150910_1_, p_77654_2_, p_150910_2_);
            return new ItemStack(Items.glass_bottle);
        }
        else
        {
            if (!p_150910_2_.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle)))
            {
                p_150910_2_.dropPlayerItemWithRandomChoice(new ItemStack(Items.glass_bottle, 1, 0), false);
            }
            p_150910_2_.getFoodStats().addStats(this, p_150910_1_);
            p_77654_2_.playSoundAtEntity(p_150910_2_, "random.burp", 0.5F, p_77654_2_.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(p_150910_1_, p_77654_2_, p_150910_2_);
            return p_150910_1_;
        }
    }
    
    
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.DRINK;
    }
}