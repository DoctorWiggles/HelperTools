package helpertools.Com.Blocks;

import helpertools.Utils.HelpTab;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Balloon_Block extends Block
{
	protected static final AxisAlignedBB HitBox = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 1.0D, 0.8D);
	
    public Balloon_Block(String unlocal)
    {
    	super(Material.CIRCUITS);
        this.setUnlocalizedName(unlocal);
        this.setHardness(0F);
        this.setHarvestLevel("pickaxe",0);
        this.setCreativeTab(HelpTab.HelperTools);  
        this.setSoundType(SoundType.CLOTH);
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return HitBox;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    
    

}
