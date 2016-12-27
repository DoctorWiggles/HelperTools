package helpertools.Com.Blocks;

import helpertools.Utils.HelpTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Lamp_Block extends Block{

	protected static final AxisAlignedBB HitBox = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 1.0D, 0.8D);
	
	public Lamp_Block(String unlocal)
	{
		super(Material.CLAY);
		this.setUnlocalizedName(unlocal);
		this.setCreativeTab(HelpTab.HelperTools);       
		this.setHardness(0.6F);
		setHarvestLevel("pickaxe",0);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return HitBox;
    }

}
