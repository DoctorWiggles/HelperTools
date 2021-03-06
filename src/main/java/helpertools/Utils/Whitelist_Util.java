package helpertools.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

/** Lists to manually modify normally awkward block items
 * that other base method seems to inconsistently return in these cases **/
public class Whitelist_Util {
	
	
	String Tcon = "TConstruct";
	static String Chisel = "chisel";
	
	/** Checks if whitelist object is present then adjust's it meta value accordingly **/
	public static boolean Block_Whitelist(Block block, EntityPlayer player, int meta){
		
		if(!InventoryUtil.hasItem(Item.getItemFromBlock(block), player.inventory)){
			return false;
		}
		
		if(Loader.isModLoaded(Chisel))
		 {
			//if(block instanceof ICarvable ){return true;}
		 }
		
		if(block instanceof BlockLeaves){
			ItemStack stacky  = new ItemStack (block,0, meta-4);
			if(InventoryUtil.StackScan(stacky, player.inventory) >0){return true;
			}
		  }
		
		if(block instanceof BlockRotatedPillar
				||block instanceof BlockStairs
				||block instanceof BlockSlab && meta >= 8
				||block == Blocks.QUARTZ_BLOCK && meta >= 2){
			ItemStack stacky = new ItemStack (block,0, 0);
			if(block == Blocks.QUARTZ_BLOCK && meta >= 2){
				stacky = new ItemStack (block,0, 2);
			}
			if(block instanceof BlockSlab && meta >= 8){
				stacky = new ItemStack (block,0, meta - 8);
			  }
			if(InventoryUtil.StackScan(stacky, player.inventory) >0){return true;
				}
				
			}
	
		return false;
	}
	
	public static ItemStack Whitelist_stack(ItemStack stack, EntityPlayer player, Block block, int meta){
		ItemStack last_stack = stack;
		
		if(block instanceof BlockLeaves){
			return new ItemStack (block,0, meta-4);
		  }
		
		if(block instanceof BlockRotatedPillar
				||block instanceof BlockStairs
				||block instanceof BlockSlab && meta >= 8
				||block == Blocks.QUARTZ_BLOCK && meta >= 2){
			ItemStack stacky = new ItemStack (block,0, 0);
			if(block == Blocks.QUARTZ_BLOCK && meta >= 2){
				stacky = new ItemStack (block,0, 2);
			}
			if(block instanceof BlockSlab && meta >= 8){
				stacky = new ItemStack (block,0, meta - 8);
			  }
			last_stack = stacky;
		}
		
			return last_stack;
	}
	
	/** Consumes the flagged item **/
	public static void Consume_Whitelist(ItemStack stack, EntityPlayer player, Block block, int meta){
		
		if(block instanceof BlockLeaves){
			ItemStack stacky  = new ItemStack (block,0, meta-4);
			InventoryUtil.consumeInventoryItemStack(stacky, player.inventory);
		  }
		
		if(block instanceof BlockRotatedPillar
				||block instanceof BlockStairs
				||block instanceof BlockSlab && meta >= 8
				||block == Blocks.QUARTZ_BLOCK && meta >= 2){
			ItemStack stacky = new ItemStack (block,0, 0);
			if(block == Blocks.QUARTZ_BLOCK && meta >= 2){
				stacky = new ItemStack (block,0, 2);
			}
			if(block instanceof BlockSlab && meta >= 8){
				stacky = new ItemStack (block,0, meta - 8);
			  }
			InventoryUtil.consumeInventoryItemStack(stacky, player.inventory);
				
			}
	}

}
