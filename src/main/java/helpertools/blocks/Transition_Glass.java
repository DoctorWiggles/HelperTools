package helpertools.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helpertools.HelpTab;
import helpertools.Main;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Transition_Glass extends BlockBreakable
{
    public Transition_Glass(Material glass, boolean b, String name)
    {
    	 super(name, glass, b);
        this.setBlockName("TransitionGlass");        
        //this.setCreativeTab(CreativeTabs.tabBlock);
        //setCreativeTab(Helpertoolscore.HelperTools);
        setCreativeTab(HelpTab.HelperTools);
        this.setBlockTextureName("helpertools:TransitionGlassOff");
        this.setResistance(2F);
        this.setHardness(1.0F);
        this.setLightOpacity(10);
        //this.shouldSideBeRendered(false);
        
    }
   
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }
    /**
    public boolean isOpaqueCube(){
    	return false;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    **/
    

    
    //When broken will drop itself instead of nothing
    
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(this);
    }
   
}