package helpertools.Utils;

import helpertools.Main;
import helpertools.Com.ItemRegistry;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BombHelper {
	
	
	public static Set<BlockPos> Sphere_Shape(BlockPos pos, int radius){
		Set<BlockPos> positions = new HashSet<BlockPos>();
		
	      for (int x = -radius; x <= radius; x++)
	      {
	          for (int y = -radius; y <= radius; y++)
	          {
	              for (int z = -radius; z <= radius; z++)
	              {
	                  if (!(x * x + y * y + z * z >= (radius + 0.50f) * (radius + 0.50f)))
	                  {
	                      positions.add(pos.add(x, y, z));
	                  }}}}
		
		return positions;
	}
	
	
	public static void Block_Sphere(World world, IBlockState block, BlockPos pos, int radius){
		
		for (BlockPos location : Sphere_Shape(pos, radius)) {			
			Place_Block(world, block, location, false);
		}
		
	}
	
	public static void Place_Block(World world, IBlockState block, BlockPos pos, boolean flag){
		
		if(ModUtil.isValid(world, pos)){
			ModUtil.Destructables(world, pos);
			
			world.setBlockState(pos, block, 02);  			
		}
		else if(flag)ModUtil.itemdrop(world, pos, block.getBlock());
	}
	
	public static void Miracle_Sphere(World world, BlockPos pos, int radius){
		
		for (BlockPos location : Sphere_Shape(pos, radius)) {
			
			BlockPos PosAbove = location.add(0,1,0);
			IBlockState above = world.getBlockState(PosAbove);
			BlockPos PosBelow = location.add(0,-1,0);
			IBlockState below = world.getBlockState(PosBelow);	
			
			IBlockState state = world.getBlockState(location);
			
			Block target = state.getBlock();
			int roll = Main.Randy.nextInt(100);
			
			if(target == Blocks.DIRT || target == ItemRegistry.LooseDirtBlock){
				placer(world, location, Blocks.GRASS);
			}
			if(target == Blocks.COBBLESTONE){
				if(roll <= 5)placer(world, location, Blocks.GRAVEL);
				else if(roll <=9)placer(world, location, Blocks.MOSSY_COBBLESTONE);
			}
			if(target == Blocks.STONE){
				if(roll <= 35)placer(world, location, Blocks.COBBLESTONE);
			}
			if(target == Blocks.CACTUS && above.getBlock().isAir(above, world, PosAbove) && roll <= 20){
				placer(world, PosAbove, Blocks.CACTUS);				
			}
			if(target == Blocks.REEDS && above.getBlock().isAir(above, world, PosAbove) && roll <= 20){
				placer(world, PosAbove, Blocks.REEDS);				
			}
			if(target == Blocks.DEADBUSH && roll <= 6){	
				world.setBlockState(location, Blocks.SAPLING.getStateFromMeta(3));	
				Grow(world, location);
				Grow(world, location);
				Grow(world, location);
				placer(world, PosBelow, Blocks.DIRT);	
			}
			if(target == Blocks.GRASS || target == Blocks.TALLGRASS){
				if(roll <= 3)Grow(world, location);
			}
			else Grow(world, location);
			
		}
		
	}
	
	public static void placer(World world, BlockPos location, Block block){
		world.setBlockState(location, block.getDefaultState());	
	}
	
	public static void Miracle_Convert(World world, BlockPos pos, IBlockState target, IBlockState dye, int chance){
		IBlockState state = world.getBlockState(pos);		
		if(state == target){
			world.setBlockState(pos, dye);			
		}		
	}
	public static void Miracle_ADD(World world, BlockPos pos, IBlockState target, IBlockState dye, int chance){
		IBlockState state = world.getBlockState(pos);		
		if(state == target){
			world.setBlockState(pos, dye);			
		}
		
	}
	
	public static void Grow(World world, BlockPos pos){	
		IBlockState state = world.getBlockState(pos);
		
		if (state.getBlock() instanceof IGrowable)
        {
            IGrowable igrowable = (IGrowable)state.getBlock();

            if (igrowable.canGrow(world, pos, state, world.isRemote))
            {
                if (!world.isRemote)
                {
                    if (igrowable.canUseBonemeal(world, world.rand, pos, state))
                    {
                        igrowable.grow(world, world.rand, pos, state);
                    }}}}
	}
	

}
