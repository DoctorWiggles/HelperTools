package helpertools.items;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helpertools.HelpTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

//Bedrock Miner Code
public class Armor_Mystic extends ItemArmor {
	
	public String textureName;

	public Armor_Mystic(String unlocalizedName, ArmorMaterial material, String textureName, int type) {
	    super(material, 0, type);
	    this.textureName = textureName;
	    this.setUnlocalizedName(unlocalizedName);
	    this.setTextureName("helpertools" + ":" + unlocalizedName);
	    setCreativeTab(HelpTab.HelperTools);
	    
	    //EnchantmentHelper.setEnchantments(Mappy(4), this);
	}
	
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
	    return "helpertools" + ":textures/models/armor/" + this.textureName + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
	
	 @Override
	    @SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
	      {
	      par3List.add(EnumChatFormatting.ITALIC + "Masquerade and");
	      par3List.add(EnumChatFormatting.ITALIC + "Demolition essential");
	      
	      }
	
	
	//Custom pre enchanted gear for creative tab 
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs p_150895_2_, List list)
    {
		//list.add(new ItemStack(item, 1, 0));
		ItemStack stack = new ItemStack(item, 1, 0);
		stack.addEnchantment(Enchantment.blastProtection, 5);
		list.add(stack);
    }
	
}