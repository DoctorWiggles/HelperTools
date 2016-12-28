package helpertools.Com.Blocks;

import helpertools.Com.ItemRegistry;
import helpertools.Utils.HelpTab;
import helpertools.Utils.Texty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FloaterBlock extends Block{
	
	public FloaterBlock(String unlocalizedName) {
        super(Material.CLOTH);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(HelpTab.HelperTools);
        this.setHardness(0.2F);
        setHarvestLevel("axe",0);
        this.setSoundType(SoundType.WOOD);
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }
    
    
    
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	Block block = ItemRegistry.BalloonBlock;
    	IBlockState balloon = ItemRegistry.BalloonBlock.getDefaultState();
    	
    	BlockPos offset = new BlockPos(pos.up());
    	IBlockState blocky = world.getBlockState(offset);  
    	Material matt = blocky.getMaterial();
    	if(blocky.getBlock().isAir(blocky, world, offset)
    			|| matt == Material.FIRE
    			|| matt == Material.WATER
    			|| matt == Material.LAVA
    			|| matt == Material.PLANTS
    			|| matt == Material.VINE){
    		world.setBlockState(offset, balloon, 123);
    	}
    	else{
    		ItemStack item = new ItemStack(block);
    		Texty.itemdrop(world, offset, item);
    	}
    	
    	
        return this.getStateFromMeta(meta);
    }
    

}
