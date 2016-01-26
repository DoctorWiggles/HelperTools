package helpertools.items;

import helpertools.Mod_Registry;
import helpertools.HelpTab;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

public class Item_jelly_Bucket extends ItemBucket {
	 private Block isFull;

	public Item_jelly_Bucket(Block p_i45331_1_) {
		super(p_i45331_1_);
		// TODO Auto-generated constructor stub
		this.maxStackSize = 1;
        this.isFull = p_i45331_1_;
		setTextureName("helpertools:jelly_bucket");
		setCreativeTab(HelpTab.HelperTools);
	}

	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        boolean flag = this.isFull == Blocks.air;
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

    private ItemStack func_150910_a(ItemStack p_150910_1_, EntityPlayer p_150910_2_, Item p_150910_3_)
    {
        if (p_150910_2_.capabilities.isCreativeMode)
        {
            return p_150910_1_;
        }
        else if (--p_150910_1_.stackSize <= 0)
        {
            return new ItemStack(p_150910_3_);
        }
        else
        {
            if (!p_150910_2_.inventory.addItemStackToInventory(new ItemStack(p_150910_3_)))
            {
                p_150910_2_.dropPlayerItemWithRandomChoice(new ItemStack(p_150910_3_, 1, 0), false);
            }

            return p_150910_1_;
        }
    }

    /**
     * Attempts to place the liquid contained inside the bucket.
     */
    public boolean tryPlaceContainedLiquid(World p_77875_1_, int p_77875_2_, int p_77875_3_, int p_77875_4_)
    {
        if (this.isFull == Blocks.air)
        {
            return false;
        }
        else
        {
            Material material = p_77875_1_.getBlock(p_77875_2_, p_77875_3_, p_77875_4_).getMaterial();
            boolean flag = !material.isSolid();

            if (!p_77875_1_.isAirBlock(p_77875_2_, p_77875_3_, p_77875_4_) && !flag)
            {
                return false;
            }
            else
            {
                if (p_77875_1_.provider.isHellWorld && this.isFull == Blocks.flowing_water)
                {
                    p_77875_1_.playSoundEffect((double)((float)p_77875_2_ + 0.5F), (double)((float)p_77875_3_ + 0.5F), (double)((float)p_77875_4_ + 0.5F), "random.fizz", 0.5F, 2.6F + (p_77875_1_.rand.nextFloat() - p_77875_1_.rand.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l)
                    {
                        p_77875_1_.spawnParticle("largesmoke", (double)p_77875_2_ + Math.random(), (double)p_77875_3_ + Math.random(), (double)p_77875_4_ + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                else
                {
                    if (!p_77875_1_.isRemote && flag && !material.isLiquid())
                    {
                        p_77875_1_.func_147480_a(p_77875_2_, p_77875_3_, p_77875_4_, true);
                    }

                  //  p_77875_1_.setBlock(p_77875_2_, p_77875_3_, p_77875_4_, Common_Registry.jelly_block, 0, 3);
                }

                return true;
            }
        }
    }
}
