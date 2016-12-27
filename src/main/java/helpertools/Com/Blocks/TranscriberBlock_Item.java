
package helpertools.Com.Blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TranscriberBlock_Item extends ItemBlock
{
    public final Block blocky;

    public TranscriberBlock_Item(Block block)
    {	super(block);
        this.blocky = block;
    }

    public TranscriberBlock_Item setUnlocalizedName(String unlocal)
    {
        super.setUnlocalizedName(unlocal);
        return this;
    }
        
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
      {
      par3List.add(TextFormatting.WHITE + "Creates a Transposer Guide"); 
      par3List.add(TextFormatting.ITALIC + "Right click sides with hands");
      par3List.add(TextFormatting.ITALIC + "- To move guide");
      par3List.add(TextFormatting.ITALIC + "Right click with Transposer");
      par3List.add(TextFormatting.ITALIC + "- To get or set at guide");
      
      }

}