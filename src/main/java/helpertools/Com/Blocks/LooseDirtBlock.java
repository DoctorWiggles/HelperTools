package helpertools.Com.Blocks;

import helpertools.Utils.HelpTab;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LooseDirtBlock extends BlockFalling
{
	//mostly vanilla code but occasionally checks to transform into vanilla dirt
    public static boolean fallInstantly;
    private static final String __OBFID = "CL_00000240";    

    public LooseDirtBlock(String unlocalizedName)
    {
        super(Material.GRASS);
        setCreativeTab(HelpTab.HelperTools);
        setUnlocalizedName(unlocalizedName);
        this.setTickRandomly(true);
    }

    public LooseDirtBlock(Material materialIn)
    {
        super(materialIn);
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    /**
     * Called when a neighboring block changes.
     */
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            this.checkFallable(worldIn, pos);
            
            
				if(fall_check(worldIn, pos.down(), state)){	
					//returns falling dirt to vanilla dirt if it's on solid terrain
					int ig = growrand.nextInt(10);
					if (ig >=2){
						if(!worldIn.isAirBlock(pos.down()) &&
								!worldIn.isAirBlock(pos.down(2))){ 
    				
        		worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState(), 0123);
						}
    			}}
            //else this.checkFallable(worldIn, pos);
        }
    }
    //uproots--crushes some nonsolid blocks below
    public boolean fall_check (World world, BlockPos pos, IBlockState state){
    	
    	if(world.getBlockState(pos).getMaterial()== Material.PLANTS
				|| world.getBlockState(pos).getMaterial()== Material.VINE
				|| world.getBlockState(pos).getBlock() == Blocks.SNOW_LAYER){
		  
		   (world.getBlockState(pos).getBlock()).dropBlockAsItem(world, pos, world.getBlockState(pos), 0);			
		   world.setBlockToAir(pos);
		   return false;}    	
    	
		   else return true;
    }

    private void checkFallable(World worldIn, BlockPos pos)
    {
        if (canFallInto(worldIn, pos.down()) && pos.getY() >= 0)
        {
            byte b0 = 32;

            if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-b0, -b0, -b0), pos.add(b0, b0, b0)))
            {
                if (!worldIn.isRemote)
                {
                    EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
                    this.onStartFalling(entityfallingblock);
                    worldIn.spawnEntityInWorld(entityfallingblock);
                }
            }
            else
            {
                worldIn.setBlockToAir(pos);
                BlockPos blockpos1;

                for (blockpos1 = pos.down(); canFallInto(worldIn, blockpos1) && blockpos1.getY() > 0; blockpos1 = blockpos1.down())
                {
                    ;
                }

                if (blockpos1.getY() > 0)
                {
                    worldIn.setBlockState(blockpos1.up(), this.getDefaultState());
                }
            }
        }
    }

    protected void onStartFalling(EntityFallingBlock fallingEntity) {}

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World worldIn)
    {
        return 2;
    }

    public static boolean canFallInto(World worldIn, BlockPos pos)
    {
        if (worldIn.isAirBlock(pos)) return true;
        Block block = worldIn.getBlockState(pos).getBlock();
        Material material = worldIn.getBlockState(pos).getMaterial();
        return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
    }
    
    protected static Random growrand = new Random();
    
    public void onEndFalling(World worldIn, BlockPos pos) {    	
    	}
    
    public int quantityDropped(Random random)
    {
        return 1;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.DIRT);
    }
}