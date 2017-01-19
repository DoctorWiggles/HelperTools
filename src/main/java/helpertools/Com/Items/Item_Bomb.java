package helpertools.Com.Items;


import java.util.List;

import helpertools.Com.ItemRegistry;
import helpertools.Com.Entity.Entity_BombProjectile;
import helpertools.Utils.HelpTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Item_Bomb extends Item {

	public static int max_types = 8;

	public Item_Bomb(String unlocalizedName) {
		super();
		this.maxStackSize = 32;  
		setCreativeTab(HelpTab.HelperTools);
		setUnlocalizedName(unlocalizedName);
		this.setHasSubtypes(true);

	}
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		int meta = stack.getItemDamage();
		switch(meta){
		
		case 0:
			list.add(TextFormatting.ITALIC + "Portable dirt technology");
			list.add(TextFormatting.ITALIC + "Coats a small area with dirt");
			break;
		case 1:
			list.add(TextFormatting.ITALIC + "Coats a small area with sand");
			break;
		case 2:
			list.add(TextFormatting.ITALIC + "Coats a small area with gravel");
			break;
		case 3:
			list.add(TextFormatting.ITALIC + "Releases fertilizer and decays stone");
			break;
		case 4:
			list.add(TextFormatting.ITALIC + "Freezes liquids and lays snow");
			break;
		case 5:
			list.add(TextFormatting.ITALIC + "Weathers stone, dirt, plants");
			list.add(TextFormatting.ITALIC + "- and melts frozen blocks");
			break;
		case 6:
			list.add(TextFormatting.ITALIC + "Releases spores!");
			break;
		case 7:
			list.add(TextFormatting.ITALIC + "Vaporizes the area :(");
			list.add(TextFormatting.ITALIC + "Creative only");
			break;
			
		}

	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < max_types; i ++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}


	@Override	  
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		float f = 6.0F;
		f = (f * f + f * 2.0F) / 3.0F;

		if (!playerIn.capabilities.isCreativeMode)
		{
			--stack.stackSize;
		}


		if (!worldIn.isRemote)
		{
			Entity_BombProjectile Bomb = new Entity_BombProjectile(worldIn, playerIn, stack.getMetadata());
			Bomb.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.spawnEntityInWorld(Bomb);
		}
		worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT,
				SoundCategory.PLAYERS, .4F, itemRand.nextFloat() * 0.4F + 0.8F);

		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
}
