package helpertools.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class modetest {

//obviously not mekanism source code
	
	public int getEfficiency(ItemStack itemStack)
	{
		switch(getMode(itemStack))
		{
			case 0:
				return 20;
			case 1:
				return 8;
			case 2:
				return 128;
			case 3:
				return 20;
			case 4:
				return 0;
		}

		return 0;
	}

	public int getMode(ItemStack itemStack)
	{
		if(itemStack.stackTagCompound == null)
		{
			return 0;
		}

		return itemStack.stackTagCompound.getInteger("mode");
	}

	public String getModeName(ItemStack itemStack)
	{
		int mode = getMode(itemStack);

		switch(mode)
		{
			case 0:
				//return MekanismUtils.localize("tooltip.disassembler.normal");
			case 1:
				//return MekanismUtils.localize("tooltip.disassembler.slow");
			case 2:
				//return MekanismUtils.localize("tooltip.disassembler.fast");
			case 3:
				//return MekanismUtils.localize("tooltip.disassembler.vein");
			case 4:
				//return MekanismUtils.localize("tooltip.disassembler.off");
		}

		return null;
	}

	public void toggleMode(ItemStack itemStack)
	{
		if(itemStack.stackTagCompound == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		itemStack.stackTagCompound.setInteger("mode", getMode(itemStack) < 4 ? getMode(itemStack)+1 : 0);
	}
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if(!world.isRemote && entityplayer.isSneaking())
		{
			toggleMode(itemstack);
			entityplayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_BLUE + "[Mekanism] " + " " + EnumChatFormatting.BLUE + getModeName(itemstack) + EnumChatFormatting.AQUA + " (" + getEfficiency(itemstack) + ")"));
		}

		return itemstack;
	}

	
}
