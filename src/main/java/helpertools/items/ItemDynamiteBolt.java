package helpertools.items;

import java.util.List;

import helpertools.HelpTab;
import helpertools.Helpertoolscore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemDynamiteBolt extends Item {

	  public ItemDynamiteBolt() {
	       super();
	       this.maxStackSize = 16;  
	       setUnlocalizedName("dynamitebolt");
	       //setCreativeTab(Helpertoolscore.HelperTools);
	       setCreativeTab(HelpTab.HelperTools);
	       setTextureName("helpertools:Darrow");
	       
	   }
	  @Override
	    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	    {
	    par3List.add(EnumChatFormatting.WHITE + "Fired from Torch Launcher");
	    par3List.add(EnumChatFormatting.WHITE + "- Or Dispensers");
	    par3List.add(" ");
	    par3List.add(EnumChatFormatting.GRAY + "*Danger-Explosive*");
	    }
	  
}
