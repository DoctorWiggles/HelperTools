package helpertools.util;

import java.util.HashSet;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public class HelperExplosions {
	
	
	
	protected static Random rand = new Random();
	
	public static void doExplosionU(World world, float explosionSize, double explosionX, double explosionY, double explosionZ)
    {
        float f = explosionSize;
        //HashSet hashset = new HashSet();
        int i;
        int j;
        int k;
        
        int radius = 16;
        double d5;
        double d6;
        double d7;

        for (i = 0; i < radius; ++i)
        {
            for (j = 0; j < radius; ++j)
            {
                for (k = 0; k < radius; ++k)
                {
                    if (i == 0 || i == radius - 1 || j == 0 || j == radius - 1 || k == 0 || k == radius - 1)
                    {
                        double d0 = (double)((float)i / ((float)radius - 1.0F) * 2.0F - 1.0F);
                        double d1 = (double)((float)j / ((float)radius - 1.0F) * 2.0F - 1.0F);
                        double d2 = (double)((float)k / ((float)radius - 1.0F) * 2.0F - 1.0F);
                        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                        d0 /= d3;
                        d1 /= d3;
                        d2 /= d3;
                        float f1 = explosionSize * (0.7F + rand.nextFloat() * 0.6F);
                        d5 = explosionX;
                        d6 = explosionY;
                        d7 = explosionZ;

                        for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F)
                        {
                            int j1 = MathHelper.floor_double(d5);
                            int k1 = MathHelper.floor_double(d6);
                            int l1 = MathHelper.floor_double(d7);
                            Block block = world.getBlock(j1, k1, l1);
                            int Gmeta = 0;	
                            Gmeta =  world.getBlockMetadata(j1, k1, l1); 

                            if (block.getMaterial() != Material.air)
                            {
                            	 block.dropBlockAsItem(world,j1, k1, l1, Gmeta, 0);
                            	 world.setBlock(j1, k1, l1, Blocks.air);
                                //float f3 = entity != null ? entity.func_145772_a(this, this.worldObj, j1, k1, l1, block) : block.getExplosionResistance(this.exploder, worldObj, j1, k1, l1, explosionX, explosionY, explosionZ);
                                //f1 -= (f3 + 0.3F) * f2;
                            }

                           

                            //d5 += d0 * (double)f2;
                            //d6 += d1 * (double)f2;
                           // d7 += d2 * (double)f2;
                        }
                    }
                }
            }
        }
    }
	
	public static void doExplosionG(World world, float Size, int X, int Y, int Z)
	{
		int Si = (int)Size;
        //HashSet hashset = new HashSet();
        int i;
        int j;
        int k;
        
        
        //Si = 3;
		
        //int G2 = 0; G2 < 3; ++G2
		 for (i = 0; i < Si; ++i)
	        {
	            for (j = 0; j < Si; ++j)
	            {
	                for (k = 0; k < Si; ++k)
	                {
	                	int ptr = Si/2;
	                	int X2 = X+i-ptr;
	                	int Y2 = Y+j-ptr;
	                	int Z2 = Z+k-ptr;
	                	
	                	//Block block = world.getBlock(X+i-Si, Y+j-Si, Z+k-Si);
	                	//Block block = world.getBlock(X+i-ptr, Y+j-ptr, Z+k-ptr);
	                	Block block = world.getBlock(X2, Y2, Z2);
                        int Gmeta = 0;	
                        Gmeta =  world.getBlockMetadata(X2, Y2, Z2); 
	                	//if (block.getMaterial() != Material.air)
                        //{
                        	 block.dropBlockAsItem(world,X2, Y2, Z2, Gmeta, 0);
                        	 world.setBlock(X2, Y2, Z2, Blocks.gold_block);
                            
                        //}

	                }
	            }
	        }
		
		
	}
	
	
	
	
}


