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
	  /**
	  public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	   {
	   par3List.add("§6*Danger Explosive*");
	   //par3List.add("Red: " + "255");
	   //par3List.add("§4dogs");
	   
	   }
	   **/
	  @Override
	    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	    {
	    par3List.add(EnumChatFormatting.WHITE + "For use in Torch Launcher");
	    par3List.add(" ");
	    par3List.add(EnumChatFormatting.GRAY + "*Danger-Explosive*");
	    }
	  /*
	    public String getItemStackDisplayName(ItemStack p_77653_1_)
	    {
	        return (EnumChatFormatting.GOLD  + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(p_77653_1_) + ".name")).trim();
	    }
	    */
}
