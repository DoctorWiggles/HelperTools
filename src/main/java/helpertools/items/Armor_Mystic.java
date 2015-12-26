package helpertools.items;

import helpertools.HelpTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

//Bedrock Miner Code
public class Armor_Mystic extends ItemArmor {
	
	public String textureName;

	public Armor_Mystic(String unlocalizedName, ArmorMaterial material, String textureName, int type) {
	    super(material, 0, type);
	    this.textureName = textureName;
	    this.setUnlocalizedName(unlocalizedName);
	    this.setTextureName("helpertools" + ":" + unlocalizedName);
	    setCreativeTab(HelpTab.HelperTools);
	}
	
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
	    return "helpertools" + ":textures/models/armor/" + this.textureName + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
	
	
	
}