package helpertools.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helpertools.HelpTab;
import helpertools.Helpertoolscore;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SugarBlock extends BlockFalling
{
    public SugarBlock(Material sand)
    {
        super();
        this.setBlockName("SugarBlock");       
        setCreativeTab(HelpTab.HelperTools);        
        this.setBlockTextureName("helpertools:sugar4");
        
        this.setResistance(20F);
        this.setHardness(15.0F);
        
        //this.setBlockBounds(-1F, -1.0F, -1F, 2F, 2.0F, 2F);
        //this.setBlockBounds(-2F, -2.0F, -2F, 3F, 3.0F, 3F);
        
    }
    
	
}