package helpertools.util;

import helpertools.ModRegistry;
import helpertools.ConfigurationFactory;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDye;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class Forest_Helper extends Bomb_Helper{
	
	//protected Random rand;
	   protected static Random rand = new Random();
	   
	   //out of 100 function
	   public static boolean chance (double d){
		   if	( d >=  rand.nextInt(100)){
			 return true;
			} 
			else 
				return false;
	   }
	   //======================================================================//
	 //Simple sphere generator -Adapted from blood magic mod's meteor function
	   public static void sphere_miracle_tree_bomb(World world,int radius, int x, int y, int z)
	   {
	 	  Block target = world.getBlock(x, y-1, z);
	 	  bonemeal_grow(world, target, x, y-1, z);
	       int newRadius = radius;

	       for (int i = -newRadius; i <= newRadius; i++)
	       {
	           for (int j = -newRadius; j <= newRadius; j++)
	           {
	               for (int k = -newRadius; k <= newRadius; k++)
	               {
	                   if (i * i + j * j + k * k >= (newRadius + 0.50f) * (newRadius + 0.50f))
	                   {
	                       continue;
	                   }
	                   boolean hasPlacedBlock = false;
	                   
	                   if (!hasPlacedBlock)
	                   { 
	                 	  //world.setBlock(x + i, y + j, z + k, pblock, 0, 3);
	                	   miracle_grow_tree(world, x + i, y + j, z + k);
	                 	  
	                 	  }}}}
	   }
	   
	  public static void miracle_grow_tree(World world, int x, int y, int z){
	 	 Block target = world.getBlock(x, y, z);
	 	 Block above = world.getBlock(x, y+1, z);
	 	 Block below2 = world.getBlock(x, y-2, z);
	 	 
	 	 /*
	 	if(target == Blocks.sapling){
			bonemeal_grow(world, target, x, y, z); 
		 	bonemeal_grow(world, target, x, y, z);
		 	bonemeal_grow(world, target, x, y, z);
		 	bonemeal_grow(world, target, x, y, z);
		 }
		 */
	 	 
	 	  if(target == Blocks.dirt ||
	 			  target == ModRegistry.LooseDirtBlock)
	 		{
	 		  world.setBlock(x,y,z, Blocks.grass);}
	 	  if(target == Blocks.cobblestone)
	 		{ int ig = rand.nextInt(35);
	 		if (ig <= 1){ 
	 		  world.setBlock(x,y,z, Blocks.gravel);}
	 		else if (ig <= 3){ 
	 			  world.setBlock(x,y,z, Blocks.mossy_cobblestone);}}
	 	  
	 	  if(target == Blocks.stone)
	 		{ int ig = rand.nextInt(6);
	 		if (ig <= 2){ 
	 		  world.setBlock(x,y,z, Blocks.cobblestone);}}
	      
	 	  if(target == Blocks.cactus && above == Blocks.air
	 			  && below2 != Blocks.cactus)
	 		{ int ig = rand.nextInt(40);
	 		if (ig <= 1){ 
	 		  world.setBlock(x,y+1,z, Blocks.cactus);}}
	 	  
	 	  if(target == Blocks.reeds && above == Blocks.air
	 			  && below2 != Blocks.reeds)
	 		{ int ig = rand.nextInt(8);
	 		if (ig <= 1){ 
	 		  world.setBlock(x,y+1,z, Blocks.reeds);}}
	 	  
	 	  if(target == Blocks.deadbush )
	 		{ int ig = rand.nextInt(18);
	 		if (ig <= 1){ 
	 		  world.setBlock(x,y,z, Blocks.sapling);
	 		  world.setBlock(x,y-1,z, Blocks.dirt);
	 		  world.setBlockMetadataWithNotify(x,y,z, 3, 2);
	 		  bonemeal_grow(world, target, x, y, z);
	 		  bonemeal_grow(world, target, x, y, z);
	 		  bonemeal_grow(world, target, x, y, z);}}
	 	  
	 	  int ig = rand.nextInt(3);
	 		if (ig <= 2){ 		  
	 	  if(target == Blocks.grass||
	 			  target == Blocks.tallgrass){
	 		  int rg =rand.nextInt(60);
	 		  if( rg<= 1){
	 			  bonemeal_grow(world, target, x, y, z);
	 		  }
	 	  }
	 	  else
	 	  bonemeal_grow(world, target, x, y, z);
	 		}
	 	  
	   }
	   
//====================================================================================//
 public static void sphere_forest_bomb(World world,int radius, int x, int y, int z)
 {
     int newRadius = radius;

     for (int i = -newRadius; i <= newRadius; i++)
     {
         for (int j = -newRadius; j <= newRadius; j++)
         {
             for (int k = -newRadius; k <= newRadius; k++)
             {
                 if (i * i + j * j + k * k >= (newRadius + 0.50f) * (newRadius + 0.50f))
                 {
                     continue;
                 }
                 boolean hasPlacedBlock = false;
                 
                 if (!hasPlacedBlock)
                 { 
                	 forest_convert(world, x + i, y + j, z + k);
               	  
                 }}}}
 }
 
 public static void forest_convert(World world, int x, int y, int z){
	 Block target = world.getBlock(x, y, z);
	 Block above = world.getBlock(x, y+1, z);	
	 Block below = world.getBlock(x, y-1, z);	
	 
	 if(target == ModRegistry.LooseDirtBlock){
		 world.setBlock(x,y,z, Blocks.dirt);
	 }
	 if (chance(2)){ 
		  if(above == Blocks.air && target == Blocks.grass ||
			above == Blocks.air && target == Blocks.dirt ){		
			  world.setBlock(x,y+1,z, Blocks.sapling, 0,12);
			  world.setBlock(x,y,z, Blocks.grass );
			  //world.setBlockMetadataWithNotify(x,y+1,z, 1, 2);
			  //world.setBlock(x,y+1,z, Blocks.sapling, 2, 2);
			  } 
		 
		  }
	 else if (chance(0.5)){ 
		  if(above == Blocks.air && target == Blocks.grass ||
			above == Blocks.air && target == Blocks.dirt ){		
			  //world.setBlock(x,y+1,z, Blocks.sapling);
			  //world.setBlockMetadataWithNotify(x,y+1,z, 1, 2);
			  world.setBlock(x,y+1,z, Blocks.sapling, 2, 12);
			  world.setBlock(x,y,z, Blocks.grass );
			  } 
		 
		  }
	 /*
	 else if(target == Blocks.sapling){
			bonemeal_grow(world, target, x, y, z); 
		 	bonemeal_grow(world, target, x, y, z);
		 	bonemeal_grow(world, target, x, y, z);
		 	bonemeal_grow(world, target, x, y, z);
		 }	
	 */
		 
 	}
}
