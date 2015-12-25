package helpertools.item_blocks;

import helpertools.item_blocks.Item_Block_Magical_Lamp;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Item_Block_Magical_Lamp extends ItemBlock
{
    public final Block blocky;
    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public Item_Block_Magical_Lamp(Block block)
    {	super(block);
        this.blocky = block;
    }

    public Item_Block_Magical_Lamp setUnlocalizedName(String unlocal)
    {
        super.setUnlocalizedName(unlocal);
        return this;
    }
    
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
      {
      par3List.add(EnumChatFormatting.WHITE + "Redstone powered lamp"); 
      par3List.add(EnumChatFormatting.ITALIC + "Right click with a redstone torch");
      par3List.add(EnumChatFormatting.ITALIC + "To self power the lamp");
      
      }

}