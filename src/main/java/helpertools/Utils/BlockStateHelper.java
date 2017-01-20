package helpertools.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Bunch of BlockState Shortcut Utils 
 * Most seem to be vanilla now though
 * **/
public class BlockStateHelper {
		
	/**Extract an ID integer from a blockstate**/
	public static int returnID(World world, BlockPos pos ){
		int GET = 0;		
		GET =(world.getBlockState(pos).getBlock()).getStateId(world.getBlockState(pos));		
		return GET;
	}
	
	/**Extract a blockstate from an ID integer**/
	public static IBlockState returnState(int id)
    {
        int j = id & 4095;
        int k = id >> 12 & 15;
        return Block.getBlockById(j).getStateFromMeta(k);
    }
	
	/**Extracts a block from an ID integer**/
	public static Block returnBlock(int id){
		
		int j = id & 4095;
        int k = id >> 12 & 15;
        return Block.getBlockById(j);
	}
	
	/**Extracts a Meta value from an ID integer**/
	public static int returnMeta(int id)
    {
        int j = id & 4095;
        int k = id >> 12 & 15;
        return k;
    }
	
	/**Extracts a Block from a State via bridge**/
	public static Block getBlockfromState (World world, BlockPos pos){
		Block blocky = Blocks.AIR;
		
		int ID = BlockStateHelper.returnID(world, pos);
		blocky = BlockStateHelper.returnBlock(ID);
		
		return blocky;
	}
	
	/**Extracts a Meta Value from a State via bridge**/
	public static int getMetafromState (World world, BlockPos pos){
		int meta = 0;
		
		int ID = BlockStateHelper.returnID(world, pos);
		meta = BlockStateHelper.returnMeta(ID);
				
		return meta;
	}
	
	/**Extracts a block ID from an ID integer**/
		public static int returnBlock_ID(int id){
			
			int j = id & 4095;
	        int k = id >> 12 & 15;
	        return j;
		}
		
		public static int getblock_ID(World world, BlockPos pos1){
			int id;
			id = BlockStateHelper.returnBlock_ID(BlockStateHelper.returnID(world, pos1));
			return id;
		}
		
		
	
}
