package helpertools.items;

import helpertools.Common_Registry;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Floater_block_item extends ItemBlock
{
    public final Block blocky;
    @SideOnly(Side.CLIENT)
    private IIcon icon;
    
    protected static Random growrand = new Random();

    public Floater_block_item(Block block)
    {	super(block);
        this.blocky = block;
    }

    /**
     * Sets the unlocalized name of this item to the string passed as the parameter, prefixed by "item."
     */
    public Floater_block_item setUnlocalizedName(String unlocal)
    {
        super.setUnlocalizedName(unlocal);
        return this;
    }
    
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
      {      
    	par3List.add(EnumChatFormatting.ITALIC + "Can be placed in mid-air and water"); 
    	par3List.add(EnumChatFormatting.ITALIC + "Shift click to place below you"); 
      
      
      }

    /**
     * Returns 0 for /terrain.png, 1 for /gui/items.png
     */
    @SideOnly(Side.CLIENT)
    public int getSpriteNumber()
    {
        return this.blocky.getItemIconName() != null ? 1 : 0;
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_)
    {
        return this.icon != null ? this.icon : this.blocky.getBlockTextureFromSide(1);
    }
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
    	
    	if(player.isSneaking()){ 
    		ChunkCoordinates coord;
    		coord = player.getPlayerCoordinates();
    		
    		
        	int x = (int) coord.posX;
        	int y = (int) player.posY-1;
        	int z = (int) coord.posZ;
        	Block block;
        	block = world.getBlock(x, y, z);
        	Material matt;
        	matt = block.getMaterial();
        	//block = world.getBlock(x1, y1, z1);
        	
        	if(block == Blocks.air 
        			|| matt == Material.fire
        			|| matt == Material.water
        			|| matt == Material.lava
        			|| matt == Material.plants
        			|| matt == Material.vine){ 
        		if(!player.canPlayerEdit(x, y-1, z,  0,stack)){
        			return stack;
        		}
        		if (!world.canMineBlock(player, x, y-1, z))
                {
                    return stack;
                }
        		customplace(world,x, y, z);
        	}
        			return stack;
        		
        	
    	}
    	
    	MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

        if (movingobjectposition == null)
        {
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

            
                if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack))
                {
                    return stack;
                }

                Material material = world.getBlock(i, j, k).getMaterial();

                if (material == Material.water && world.getBlock(i, j+1, k) == Blocks.air
                		||material == Material.lava && world.getBlock(i, j+1, k) == Blocks.air)
                {
                   // world.setBlockToAir(i, j, k);
                    customplace(world,i, j, k);
                    
                }
        }
        
        return stack;
        
        
    }
    
    
    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x1, int y1, int z1, int face, float f1, float f2, float f3)
    {
        Block block = world.getBlock(x1, y1, z1);
        
        if(player.isSneaking()){
        			return false;
        	
        }

        if (block == Blocks.snow_layer && (world.getBlockMetadata(x1, y1, z1) & 7) < 1)
        {
            face = 1;
        }
        else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(world, x1, y1, z1))
        {
            if (face == 0)
            {
                --y1;
            }

            if (face == 1)
            {
                ++y1;
            }

            if (face == 2)
            {
                --z1;
            }

            if (face == 3)
            {
                ++z1;
            }

            if (face == 4)
            {
                --x1;
            }

            if (face == 5)
            {
                ++x1;
            }
        }

        if (stack.stackSize == 0)
        {
            return false;
        }
        else if (!player.canPlayerEdit(x1, y1, z1, face, stack))
        {
            return false;
        }
        else if (y1 == 255 && this.blocky.getMaterial().isSolid())
        {
            return false;
        }
        else if (world.canPlaceEntityOnSide(this.blocky, x1, y1, z1, false, face, player, stack))
        {
            int i1 = this.getMetadata(stack.getItemDamage());
            int j1 = this.blocky.onBlockPlaced(world, x1, y1, z1, face, f1, f2, f3, i1);

            if (placeBlockAt(stack, player, world, x1, y1, z1, face, f1, f2, f3, j1))
            {
                world.playSoundEffect((double)((float)x1 + 0.5F), (double)((float)y1 + 0.5F), (double)((float)z1 + 0.5F), this.blocky.stepSound.func_150496_b(), (this.blocky.stepSound.getVolume() + 1.0F) / 2.0F, this.blocky.stepSound.getPitch() * 0.8F);
                --stack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean func_150936_a(World world, int x1, int y1, int z1, int face, EntityPlayer player, ItemStack stack)
    {
        Block block = world.getBlock(x1, y1, z1);

        if (block == Blocks.snow_layer)
        {
            face = 1;
        }
        else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(world, x1, y1, z1))
        {
            if (face == 0)
            {
                --y1;
            }

            if (face == 1)
            {
                ++y1;
            }

            if (face == 2)
            {
                --z1;
            }

            if (face == 3)
            {
                ++z1;
            }

            if (face == 4)
            {
                --x1;
            }

            if (face == 5)
            {
                ++x1;
            }
        }

        return world.canPlaceEntityOnSide(this.blocky, x1, y1, z1, false, face, (Entity)null, stack);
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack stack)
    {
        return this.blocky.getUnlocalizedName();
    }

    /**
     * Returns the unlocalized name of this item.
     */
    public String getUnlocalizedName()
    {
        return this.blocky.getUnlocalizedName();
    }

    /**
     * gets the CreativeTab this item is displayed on
     */
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTab()
    {
        return this.blocky.getCreativeTabToDisplayOn();
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        this.blocky.getSubBlocks(item, tab, list);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg)
    {
        String s = this.blocky.getItemIconName();

        if (s != null)
        {
            this.icon = reg.registerIcon(s);
        }
    }

    /**
     * Called to actually place the block, after the location is determined
     * and all permission checks have been made.
     *
     * @param stack The item stack that was used to place the block. This can be changed inside the method.
     * @param player The player who is placing the block. Can be null if the block is not being placed by a player.
     * @param side The side the player (or machine) right-clicked on.
     */
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {

       if (!world.setBlock(x, y, z, blocky, metadata, 3))
       {
           return false;
       }

       if (world.getBlock(x, y, z) == blocky)
       {
           blocky.onBlockPlacedBy(world, x, y, z, player, stack);
           blocky.onPostBlockPlaced(world, x, y, z, metadata);
       }

       return true;
    }
    
    public void customplace (World world, int x, int y, int z){
    	if (!world.isRemote){        	
        	
        	world.setBlock(x, y, z, blocky, 0, 123);

    		world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F),
    				blocky.stepSound.getStepResourcePath(), (blocky.stepSound.getVolume() + 1.0F) / 2.0F,
    				blocky.stepSound.getPitch() * 0.8F);
    		
    		balloon(world, x, y, z);
        	}
        	
        	if (world.isRemote){
        	int crackid = blocky.getIdFromBlock(blocky);
        	
        	String particle = "blockcrack_" + crackid + "_" + 0;
    		for (int pl = 0; pl < 5; ++pl)
			{
    		float f = (this.growrand.nextFloat() - .2F) * 1.4F;
            float f1 = (this.growrand .nextFloat() - .2F) * 1.4F;
            float f2 = (this.growrand .nextFloat() - .2F) * 1.4F;
    		world.spawnParticle(particle, x+f, y+f1-0.3, z+f2, 0, 0, 0); 
    		
			
				}
        	}
    }
    Block balloon = Common_Registry.Balloon;
    public void balloon(World world, int x, int y, int z){
    Block block;
	block = world.getBlock(x, y+1, z);
	Material matt;
	matt = block.getMaterial();
	if(block == Blocks.air 
			|| matt == Material.fire
			|| matt == Material.water
			|| matt == Material.lava
			|| matt == Material.plants
			|| matt == Material.vine){ 
	world.setBlock(x, y+1, z, balloon, 0, 123);
	
	
	}
	else{
	balloon.dropBlockAsItem(world,x,y,z, 0, 0);}
		
    }
}