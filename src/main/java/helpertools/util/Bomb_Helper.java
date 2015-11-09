package helpertools.util;

import helpertools.Common_Registry;
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

public class Bomb_Helper {
	
	//protected Random rand;
	   protected static Random rand = new Random();
	   
	   
	//particle cloud spawn	
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
	
	public static void particlecloud2(World world, int i4, int j4, int k4){
		
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
	           
	           world.spawnParticle("happyVillager", i4+f-.5, j4+f1+.5, k4+f2+.5, p, p1, p2);	           
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
	           
	           world.spawnParticle("happyVillager", i4+f-.5, j4+f1, k4+f2+.5, p, p1, p2);
	           
	       }
		
		
	}
	//function to place a half ellipsiod 
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
  

  public static void sphere_place(World world, int r, int x, int y, int z, Block p_block, Block d_block){
		
		for(int tx=-r; tx< r+1; tx++){
		    for(int ty=-r; ty< r+1; ty++){
		        for(int tz=-r; tz< r+1; tz++){
		            if(Math.sqrt(Math.pow(tx, 2)  +  Math.pow(ty, 2)  +  Math.pow(tz, 2)) <= r-2){
		            	
		            	world.setBlock(tx+x, ty+y, tz+z, p_block);
		            } } } }
  				}
  
  //Simple sphere generator -Adapted from blood magic mod's meteor function
  public static void sphere_miracle_bomb(World world,int radius, int x, int y, int z)
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
                	  miracle_grow(world, x + i, y + j, z + k);
                	  
                	  }}}}
  }
  
 public static void miracle_grow(World world, int x, int y, int z){
	 Block target = world.getBlock(x, y, z);
	 Block above = world.getBlock(x, y+1, z);
	 Block below2 = world.getBlock(x, y-2, z);
	 
	  if(target == Blocks.dirt ||
			  target == Common_Registry.LooseDirtBlock)
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
 
 
 public static void bonemeal_grow(World world, Block block, int x, int y, int z){
	 
	 if (block instanceof IGrowable)
     {
         IGrowable igrowable = (IGrowable)block;

         if (igrowable.func_149851_a(world, x, y, z, world.isRemote))
         {
             if (!world.isRemote)
             {
                 if (igrowable.func_149852_a(world, world.rand, x, y, z))
                 {
                     igrowable.func_149853_b(world, world.rand, x, y, z);
                     int ig = rand.nextInt(4);
             		//if (ig <= 2){ 	
             			//for(int i = 0; i <= ig; i++){
                     //igrowable.func_149853_b(world, world.rand, x, y, z);
                    
             		//}
             			}}}}
 }
  
 
 //=======================================================================================//
 
 public static void sphere_frost_bomb(World world,int radius, int x, int y, int z)
 {
	 int ig = rand.nextInt(7);
     int newRadius = radius;
     if (world.provider.isHellWorld){
    	 newRadius = radius -2;
     }

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
                	 frost_convert(world, x + i, y + j, z + k);
               	  
               	  }}}}
 }
 
 
 
 public static void frost_convert(World world, int x, int y, int z){
	 Block target = world.getBlock(x, y, z);
	 Block above = world.getBlock(x, y+1, z);	
	 
	 if(target == Blocks.snow_layer){
		  int meta = world.getBlockMetadata(x,y,z);
		  if (meta >=7){
			  world.setBlock(x,y,z, Blocks.snow);}
		  else{world.setBlockMetadataWithNotify(x,y,z, meta+1, 2);}
		  return;
	  }
	 
	  if(target == Blocks.farmland
			  && !above.isBlockNormalCube()){
		  world.setBlock(x,y,z, Blocks.dirt);}
	  
	  if(above == Blocks.air && target.isBlockNormalCube())		
		{ world.setBlock(x,y+1,z, Blocks.snow_layer);}
	  
	  if(above.getMaterial()== Material.plants
				|| above.getMaterial()== Material.vine){
		  (world.getBlock(x,y+1,z)).dropBlockAsItem(world,x,y+1,z, world.getBlockMetadata(x,y+1,z), 0);
		  world.setBlock(x,y+1,z, Blocks.snow_layer);
	  }  
	  int ig = rand.nextInt(15);
	  if(target == Blocks.ice && ig<= 1){
		  world.setBlock(x,y,z, Blocks.packed_ice);
	  }
	  if(target == Blocks.water || target == Blocks.flowing_water && ig<=5){
		  world.setBlock(x,y,z, Blocks.ice);}
	  if(target == Blocks.lava){
		  world.setBlock(x,y,z, Blocks.obsidian);}
	  if(target == Blocks.flowing_lava && ig<= 5){
		  world.setBlock(x,y,z, Blocks.cobblestone);}
	  if(target == Blocks.fire){
		  world.setBlock(x,y,z, Blocks.air);}
	  
  }
 
 
 public static void sphere_desert_bomb(World world,int radius, int x, int y, int z)
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
                	 desert_convert(world, x + i, y + j, z + k);
               	  
               	  }}}}
 }
 
 public static void desert_convert(World world, int x, int y, int z){
	 Block target = world.getBlock(x, y, z);
	 Block above = world.getBlock(x, y+1, z);	
	 Block below = world.getBlock(x, y-1, z);	
	 
	 int ig = rand.nextInt(4);
	 int ig2 = rand.nextInt(30);
	 
	 if(target== Blocks.deadbush &&ig2<= 1){
		 world.setBlock(x,y,z, Blocks.cactus);}
	 
	 if(target.getMaterial()== Material.plants && below.isBlockNormalCube() && ig2<= 4){
		 world.setBlock(x,y,z, Blocks.deadbush);}	 
	 
	 if (ig <= 2){ 
	 if(target == Blocks.stone)
		{world.setBlock(x,y,z, Blocks.cobblestone);}
	 
	 if(target == Blocks.grass)
		{world.setBlock(x,y,z, Blocks.dirt);}
	 
	 if(target == Blocks.dirt ||target == Common_Registry.LooseDirtBlock )
		{ world.setBlock(x,y,z, Blocks.sand);}
	 
	 if(target == Blocks.stone_stairs)
		{int meta = world.getBlockMetadata(x, y, z);
		  world.setBlock(x,y,z, Blocks.sandstone_stairs);
		  world.setBlockMetadataWithNotify(x,y,z, meta, 2);}}
	 
	 if (ig <= 1){
		 if(target == Blocks.cobblestone)
			{world.setBlock(x,y,z, Blocks.sandstone);}
		 if(target == Blocks.packed_ice)
			{world.setBlock(x,y,z, Blocks.ice);}
		 }
	 if (ig2 <= 4){
		 if(target == Blocks.cobblestone)
			{world.setBlock(x,y,z, Blocks.sand);}
		 if(target == Blocks.ice)
			{world.setBlock(x,y,z, Blocks.water);}
	 }
	 
	 if(target == Blocks.snow_layer)
		{int meta = world.getBlockMetadata(x, y, z);
		if(meta-4 <= 0){world.setBlock(x,y,z, Blocks.air);}
		else
		  world.setBlockMetadataWithNotify(x,y,z, meta-4, 2);
		  }
	 if(target == Blocks.snow)
		{world.setBlock(x,y,z, Blocks.snow_layer);
		  world.setBlockMetadataWithNotify(x,y,z, 4, 2);
		  }
	 
	 //High grief and ugly capabilities - might remove drying up water && thawing ice
	 if(target == Blocks.water || target == Blocks.flowing_water && ig2<=10){
		  world.setBlock(x,y,z, Blocks.air);}
	 
 }
  
  
  

}
