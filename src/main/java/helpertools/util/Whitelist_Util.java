package helpertools.util;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockStairs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Whitelist_Util {
	
	
	String Tcon = "TConstruct";
	static String Chisel = "chisel";
	
	public static boolean Block_Whitelist(Block block, EntityPlayer player, int meta){
		//int last_Meta = -1;
		
		if(!player.inventory.hasItem(Item.getItemFromBlock(block))){
			return false;
		}
		
		if(Loader.isModLoaded(Chisel))
		 {
			//if(block instanceof ICarvable ){return true;}
		 }
		//if(block instanceof BlockStairs){return true;}
		
		//if(block instanceof BlockLog){return true;}
		
		if(block instanceof BlockRotatedPillar
				||block instanceof BlockStairs
				||block == Blocks.quartz_block){
			int newMeta = block.damageDropped(meta);
			ItemStack stacky = new ItemStack (block,0, newMeta); 
			//System.out.println(meta);
			//System.out.println("new"+newMeta);
			if(InventoryUtil.StackScan(stacky, player.inventory) >0){return true;
				}
				
			}
	
		return false;
	}
	
	
	public static void Consume_Whitelist(ItemStack stack, EntityPlayer player, Block block, int meta){
		
		if(block instanceof BlockRotatedPillar
				||block instanceof BlockStairs
				||block == Blocks.quartz_block){
			int newMeta = block.damageDropped(meta);
			ItemStack stacky = new ItemStack (block,0, newMeta); 
			//System.out.println(meta);
			//System.out.println("new"+newMeta);
			InventoryUtil.consumeInventoryItemStack(stacky, player.inventory);
				
			}
		//else
		//	InventoryUtil.consumeInventoryItemStack(stack, player.inventory); 
		
		
	}

}
