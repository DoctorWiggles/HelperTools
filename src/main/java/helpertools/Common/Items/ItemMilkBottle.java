package helpertools.Common.Items;

import helpertools.Utils.HelpTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemMilkBottle extends Item
{
	private boolean alwaysEdible = true;

    public ItemMilkBottle(String unlocalizedName)
    {
    	super();        
        this.maxStackSize = 16;  
	    setUnlocalizedName(unlocalizedName);
        setCreativeTab(HelpTab.HelperTools);
	   
    }
    @Override
    //TODO public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player)
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase living)
    {
    	if (!(living instanceof EntityPlayer)){return stack; }    	 
    	EntityPlayer player = (EntityPlayer)living;
    	
    	 if (!world.isRemote)
         {
    		 
             //p_77654_3_.curePotionEffects(p_77654_1_);
    		 player.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
    		 //world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
    		 world.playSound(player, player.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
    		
    		 //p_77654_3_.setFire(0);
             //p_77654_3_.extinguish();             
         }
    	 
        if (player.capabilities.isCreativeMode)
        {
            return stack;
        }
        else if (--stack.stackSize <= 0)
        {
            return new ItemStack(Items.GLASS_BOTTLE);
        }
        else
        {
            if (!player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE)))
            {
                //player.dropPlayerItemWithRandomChoice(new ItemStack(Items.GLASS_BOTTLE, 1, 0), false);
                //player.dropItem(Items.GLASS_BOTTLE, 1);
                player.entityDropItem(new ItemStack(Items.GLASS_BOTTLE, 1, 0), 1);
            }

            return stack;
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
    /*
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        return p_77659_1_;
    }
    */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if (playerIn.canEat(this.alwaysEdible))
        {
            playerIn.setActiveHand(hand);
            return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
        }
        else
        {
            return new ActionResult(EnumActionResult.FAIL, itemStackIn);
        }
    }
}