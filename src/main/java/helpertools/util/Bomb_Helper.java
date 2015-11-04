package helpertools.util;

import helpertools.ConfigurationFactory;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Bomb_Helper {
	
	//protected Random rand;
	   protected static Random rand = new Random();
		
	public static void particlecloud(World world, int i4, int j4, int k4){
		
	      short short1 =32;
			for (int lp = 0; lp < short1; ++lp)
	       {
	           double d6 = (double)lp / ((double)short1 - 1.0D);
	           float f = (rand.nextFloat()*3) ;
	           float f1 = (rand.nextFloat()*3 );
	           float f2 = (rand.nextFloat()*3 );
	           
	           float p1 = (rand.nextFloat()/5 ) ;
	           float p = (rand.nextFloat()-.5F )/5 ;
	           float p2 = (rand.nextFloat()-.5F )/5 ;
	           //float p2 = (rand .nextFloat()-.5F/5 ) ;
	           
	           world.spawnParticle("cloud", i4+f-.5, j4+f1+.5, k4+f2+.5, p, p1, p2);	           
	       }
			
			short short2 = 8;
			for (int lp = 0; lp < short2; ++lp)
	       {
	           double d6 = (double)lp / ((double)short1 - 1.0D);
	           float f = (rand.nextFloat()*4) ;
	           float f1 = (rand.nextFloat()*4 );
	           float f2 = (rand.nextFloat()*4 );		          
	           float p1 = (rand.nextFloat()/3 ) ;
	           float p = (rand.nextFloat()-.5F )/5 ;
	           float p2 = (rand.nextFloat()-.5F )/5 ;
	           
	           world.spawnParticle("largesmoke", i4+f-.5, j4+f1, k4+f2+.5, p, p1, p2);
	           
	       }
		
		
	}
	
	public static void simple_generate(World world, Block pblock, Block dirtblock, int X, int Y, int Z, int sideHit){

	      int P = 3;
	      int i1 = X-1;
	      int j1 = Y;
	      int k1 = Z-2;
	      
	      int i2 = X-2;
	      int j2 = Y+1;
	      int k2 = Z-1;
	      
	      int i3 = X-1;
	      int j3 = Y+1;
	      int k3 = Z-1;
	      ////////////////
	      if(sideHit == 0){
	    	  j1 = j1-3;
	    	  j2 = j2-3;
	    	  j3 = j3-3;
	      }
	      //creates a static area to place dirt, becuase i'm dumb ;^)
	      //top section
	      block_placement(world, pblock, dirtblock, i1, j1, k1, 5, 3, 1, true);
	      //mid section
	      block_placement(world, pblock, dirtblock, i2, j2, k2, 3, 5, 1, true);
	      //bottom section
	      block_placement(world, pblock, dirtblock, i3, j3, k3, 3, 3, 1, false);
	    	
	}
	
	public static void block_placement(World world,Block pblock, Block dirtblock, int x1, int y1, int z1, int G2, int U2, int l2, boolean flag){
	    for (int G = 0; G < G2; ++G)
		{
			for (int U = 0; U < U2; ++U)
			{
				for (int l = 0; l <l2; ++l)
				{
					//randomizer for unique dirt mounds
					//BlockPos pos = new BlockPos(x1+U, y1+1+l, z1+G);					
					int x = x1+U;
					int y = y1+1+l;
					int z = z1+G;
					if(flag){
						int ig = rand.nextInt(6);
						if (ig >= 2){ 					
							place_block(world, x,y,z, pblock, dirtblock);
						}}
 					if(!flag){
 						place_block(world, x,y,z, pblock, dirtblock);
 					}
  	  
 					}}}
	   
	   }
 
	//place the block
  //goes through a couple checks to displace -- crush non solids
  public static void place_block (World world, int x, int y, int z, Block pblock, Block dirtblock){

		if(world.getBlock(x,y,z) == Blocks.air 	  
				||  world.getBlock(x,y,z).getMaterial()== Material.plants
				||  world.getBlock(x,y,z).getMaterial()== Material.lava
				||  world.getBlock(x,y,z).getMaterial()== Material.water)
		{
			//world.setBlockState(pos, pblock.getDefaultState(), 012);
			world.setBlock(x,y,z, pblock); 
		}
		if(world.getBlock(x,y,z).getMaterial()== Material.plants
				|| world.getBlock(x,y,z).getMaterial()== Material.vine
				|| world.getBlock(x,y,z)== Blocks.snow_layer){
			(world.getBlock(x,y,z)).dropBlockAsItem(world,x,y,z, world.getBlockMetadata(x,y,z), 0);
			//world.setBlockState(pos, pblock.getDefaultState(), 012);
			world.setBlock(x,y,z, pblock); 
		}
		
		else {
			if(ConfigurationFactory.Bomb_Debris){
			dirtblock.dropBlockAsItem(world, x,y,z, 0, 0);
		}}
  }
  

}
