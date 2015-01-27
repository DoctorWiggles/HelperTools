package helpertools.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helpertools.HelpTab;
import helpertools.Helpertoolscore;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ImitationBedrock extends Block
{
    public ImitationBedrock()
    {
        super(Material.rock);
        this.setBlockName("Imitation Bedrock");        
        //this.setCreativeTab(CreativeTabs.tabBlock);
        //setCreativeTab(Helpertoolscore.HelperTools);
        setCreativeTab(HelpTab.HelperTools);
        this.setBlockTextureName("bedrock");
        this.setResistance(20F);
        this.setHardness(15.0F);
        
    }
    //Normal block settings
    
    
    
    //When broken will drop itself instead of nothing
    
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(this);
    }
   
}