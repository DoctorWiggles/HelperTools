package helpertools.tools;

import helpertools.Common_Registry;
import helpertools.HelpTab;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class Item_Bubblegun extends ItemBucket {
	 private Block isFull;

	public Item_Bubblegun(Block block) {
		super(block);
		// TODO Auto-generated constructor stub
		this.maxStackSize = 1;
        this.isFull = block;
		setTextureName("helpertools:Bubblegun");
		setCreativeTab(HelpTab.HelperTools);
		this.setUnlocalizedName("bubblegun");
	}
	
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
		//this.isFull = Blocks.air;
	}

	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        boolean flag = this.isFull == Blocks.air;
        
        if(player.isSneaking()){this.isFull = Blocks.air; }
        else {this.isFull = Common_Registry.jelly_block;}
        
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, flag);

        if (movingobjectposition == null)
        {
            return stack;
        }
        else
        {
            FillBucketEvent event = new FillBucketEvent(player, stack, world, movingobjectposition);
            if (MinecraftForge.EVENT_BUS.post(event))
            {
                return stack;
            }

            if (event.getResult() == Event.Result.ALLOW)
            {
                if (player.capabilities.isCreativeMode)
                {
                    return stack;
                }

                if (--stack.stackSize <= 0)
                {
                    return event.result;
                }

                if (!player.inventory.addItemStackToInventory(event.result))
                {
                    player.dropPlayerItemWithRandomChoice(event.result, false);
                }

                return stack;
            }
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!world.canMineBlock(player, i, j, k))
                {
                    return stack;
                }

                if (flag)
                {
                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack))
                    {
                        return stack;
                    }

                    Material material = world.getBlock(i, j, k).getMaterial();
                    int l = world.getBlockMetadata(i, j, k);

                    if (material == Material.water && l == 0)
                    {
                        world.setBlockToAir(i, j, k);
                        return this.func_150910_a(stack, player, Items.water_bucket);
                    }

                    if (material == Material.lava && l == 0)
                    {
                        world.setBlockToAir(i, j, k);
                        return this.func_150910_a(stack, player, Items.lava_bucket);
                    }
                }
                else
                {
                    if (this.isFull == Blocks.air)
                    {
                        return new ItemStack(Items.bucket);
                    }

                    if (movingobjectposition.sideHit == 0)
                    {
                        --j;
                    }

                    if (movingobjectposition.sideHit == 1)
                    {
                        ++j;
                    }

                    if (movingobjectposition.sideHit == 2)
                    {
                        --k;
                    }

                    if (movingobjectposition.sideHit == 3)
                    {
                        ++k;
                    }

                    if (movingobjectposition.sideHit == 4)
                    {
                        --i;
                    }

                    if (movingobjectposition.sideHit == 5)
                    {
                        ++i;
                    }

                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack))
                    {
                        return stack;
                    }

                    if (this.tryPlaceContainedLiquid(world, i, j, k) && !player.capabilities.isCreativeMode)
                    {
                        return new ItemStack(Items.bucket);
                    }
                }
            }

            return stack;
        }
    }

    private ItemStack func_150910_a(ItemStack stack, EntityPlayer player, Item item)
    {
        if (player.capabilities.isCreativeMode)
        {
            return stack;
        }
        else if (--stack.stackSize <= 0)
        {
            return new ItemStack(item);
        }
        else
        {
            if (!player.inventory.addItemStackToInventory(new ItemStack(item)))
            {
                player.dropPlayerItemWithRandomChoice(new ItemStack(item, 1, 0), false);
            }

            return stack;
        }
    }

    /**
     * Attempts to place the liquid contained inside the bucket.
     */
    public boolean tryPlaceContainedLiquid(World world, int x1, int y1, int z1)
    {
        if (this.isFull == Blocks.air)
        {
            return false;
        }
        else
        {
            Material material = world.getBlock(x1, y1, z1).getMaterial();
            boolean flag = !material.isSolid();

            if (!world.isAirBlock(x1, y1, z1) && !flag)
            {
                return false;
            }
            else
            {
                if (world.provider.isHellWorld && this.isFull == Blocks.flowing_water)
                {
                	world.playSoundEffect((double)((float)x1 + 0.5F), (double)((float)y1 + 0.5F), (double)((float)z1 + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l)
                    {
                    	world.spawnParticle("largesmoke", (double)x1 + Math.random(), (double)y1 + Math.random(), (double)z1 + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                else
                {
                    if (!world.isRemote && flag && !material.isLiquid())
                    {
                    	world.func_147480_a(x1, y1, z1, true);
                    }

                    world.setBlock(x1, y1, z1, Common_Registry.jelly_block, 0, 3);
                }

                return true;
            }
        }
    }
}
