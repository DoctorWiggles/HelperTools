package helpertools.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GeometryUtil {

	
	public static void sphere_place(World world, int r, int x, int y, int z){
		
		for(int tx=-r; tx< r+1; tx++){
			//(-5; -5 < 5+1; -5++
			//tx
		    for(int ty=-r; ty< r+1; ty++){
		    	//ty
		        for(int tz=-r; tz< r+1; tz++){
		        	//tz
		            if(Math.sqrt(Math.pow(tx, 2)  +  Math.pow(ty, 2)  +  Math.pow(tz, 2)) <= r-2){
		                //delete(tx+x, ty+y, tz+z);
		            	
		            	//world.setBlock(X2, Y2, Z2, Blocks.gold_block);
		            	world.setBlock(tx+x, ty+y, tz+z, Blocks.grass);
		            } } } }
		}
	
	public static Random rand = new Random();
	//public int radius;
    public static int maxChance = 1000;
    protected static Random growrand = new Random();
    static Block pblock = Blocks.sand;
	
	
	public static void createMeteorImpact(World world,int radius, int x, int y, int z, boolean[] flags)
    {
        boolean hasTerrae = false;
        boolean hasOrbisTerrae = false;
        boolean hasCrystallos = false;
        boolean hasIncendium = false;
        boolean hasTennebrae = false;

        if (flags != null && flags.length >= 5)
        {
            hasTerrae = flags[0];
            hasOrbisTerrae = flags[1];
            hasCrystallos = flags[2];
            hasIncendium = flags[3];
            hasTennebrae = flags[4];
        }

        int newRadius = radius;
        int chance = maxChance;

        if (hasOrbisTerrae)
        {
            newRadius += 2;
            chance += 200;
        } else if (hasTerrae)
        {
            newRadius += 1;
            chance += 100;
        }

        //world.createExplosion(null, x, y, z, newRadius * 4, true);

        float iceChance = hasCrystallos ? 1 : 0;
        float soulChance = hasIncendium ? 1 : 0;
        float obsidChance = hasTennebrae ? 1 : 0;

        float totalChance = iceChance + soulChance + obsidChance;

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

                    if (!world.isAirBlock(x + i, y + j, z + k))
                    {
                        continue;
                    }

                    int randNum = world.rand.nextInt(chance);
                    boolean hasPlacedBlock = false;
                    /*
                    for (MeteorParadigmComponent mpc : componentList)
                    {
                        if (mpc == null || !mpc.isValidBlockParadigm())
                        {
                            continue;
                        }

                        randNum -= mpc.getChance();

                        if (randNum < 0)
                        {
                            ItemStack blockStack = mpc.getValidBlockParadigm();
                            if(blockStack != null && blockStack.getItem() instanceof ItemBlock)
                            {
                            	((ItemBlock)blockStack.getItem()).placeBlockAt(blockStack, null, world, x + i, y + j, z + k, 0, 0, 0, 0, blockStack.getItemDamage());
                            	world.markBlockForUpdate(x + i, y + j, z + k);
                                hasPlacedBlock = true;
                                break;
                            }
//                            world.setBlock(x + i, y + j, z + k, Block.getBlockById(Item.getIdFromItem(blockStack.getItem())), blockStack.getItemDamage(), 3);
//                            hasPlacedBlock = true;
//                            break;
                        }
                    }
					*/
                    if (!hasPlacedBlock)
                    {
                        float randChance = rand.nextFloat() * totalChance;

                        if (randChance < iceChance)
                        {
                            world.setBlock(x + i, y + j, z + k, Blocks.ice, 0, 3);
                        } else
                        {
                            randChance -= iceChance;

                            if (randChance < soulChance)
                            {
                                switch (rand.nextInt(3))
                                {
                                    case 0:
                                        world.setBlock(x + i, y + j, z + k, Blocks.soul_sand, 0, 3);
                                        break;
                                    case 1:
                                        world.setBlock(x + i, y + j, z + k, Blocks.glowstone, 0, 3);
                                        break;
                                    case 2:
                                        world.setBlock(x + i, y + j, z + k, Blocks.netherrack, 0, 3);
                                        break;
                                }
                            } else
                            {
                                randChance -= soulChance;

                                if (randChance < obsidChance)
                                {
                                    world.setBlock(x + i, y + j, z + k, Blocks.obsidian, 0, 3);
                                } else
                                {
                                    randChance -= obsidChance;

                                    //int ig = growrand.nextInt(10); if (ig >= 2){ 	
            							
                                    //world.setBlock(x + i, y + j, z + k, Blocks.stone, 0, 3);
                                    world.setBlock(x + i, y + j, z + k, pblock, 0, 3);
            						//}
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
