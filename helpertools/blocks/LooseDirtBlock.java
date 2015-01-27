package helpertools.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helpertools.HelpTab;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LooseDirtBlock extends BlockFalling
{
    @SideOnly(Side.CLIENT)
    private IIcon field_150200_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_150199_b;
    private static final String __OBFID = "CL_00000273";

    public LooseDirtBlock( Material sand)
    {
        super(Material.sand);
        
        this.setBlockName("LooseDirtBlock");
        //this.setCreativeTab(CreativeTabs.tabBlock);
        this.setTickRandomly(true);
        
        setCreativeTab(HelpTab.HelperTools);
        this.setBlockTextureName("dirt");
        //this.setBlockTextureName("mofmod:GhastlyDust");
       
       
        
    }
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		return Item.getItemFromBlock(Blocks.dirt);
    }
	//public boolean isOpaqueCube()
    //{
    //    return false;
    //}

	/**
	public void updateTick(World pworld, int i1, int j1, int k1, Random p_149674_5_)
    {
    	 
        if (pworld.isRemote)
        		
        {
        	return;
        	
        }
        if(pworld.getBlock(i1, j1-1, k1) == Blocks.air
        		 ||  pworld.getBlock(i1, j1-1, k1).getMaterial()== Material.lava
          	  ||  pworld.getBlock(i1, j1-1, k1).getMaterial()== Material.water)
        		{ 
        	return;
        }
        pworld.setBlock(i1, j1, k1, Blocks.cobblestone);
        
    }
    **/
	 protected static Random growrand = new Random();
	 
	//////////////
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (!p_149674_1_.isRemote)
        {
            this.func_149830_m(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
        }
        if(p_149674_1_.getBlock(p_149674_2_, p_149674_3_-1, p_149674_4_) == Blocks.snow_layer)
        {
        	p_149674_1_.setBlock(p_149674_2_, p_149674_3_-1, p_149674_4_, Blocks.air);
        }
        int ig = growrand.nextInt(10);
			if (ig >= 8){
        if(p_149674_1_.getBlock(p_149674_2_, p_149674_3_-1, p_149674_4_) != Blocks.air
         		 &&  p_149674_1_.getBlock(p_149674_2_, p_149674_3_-1, p_149674_4_).getMaterial()!= Material.lava
           	  &&  p_149674_1_.getBlock(p_149674_2_, p_149674_3_-1, p_149674_4_).getMaterial()!= Material.water
           	  &&
           	p_149674_1_.getBlock(p_149674_2_, p_149674_3_-2, p_149674_4_) != Blocks.air
    		 &&  p_149674_1_.getBlock(p_149674_2_, p_149674_3_-2, p_149674_4_).getMaterial()!= Material.lava
      	  &&  p_149674_1_.getBlock(p_149674_2_, p_149674_3_-2, p_149674_4_).getMaterial()!= Material.water
        		&&p_149674_1_.getBlock(p_149674_2_, p_149674_3_-1, p_149674_4_) != Blocks.snow_layer
        		
        		
        		)
         		{ 
       
         
        	p_149674_1_.setBlock(p_149674_2_, p_149674_3_, p_149674_4_, Blocks.dirt);
         		}
			}
    }

    private void func_149830_m(World p_149830_1_, int p_149830_2_, int p_149830_3_, int p_149830_4_)
    {
        if (func_149831_e(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && p_149830_3_ >= 0)
        {
            byte b0 = 32;

            if (!fallInstantly && p_149830_1_.checkChunksExist(p_149830_2_ - b0, p_149830_3_ - b0, p_149830_4_ - b0, p_149830_2_ + b0, p_149830_3_ + b0, p_149830_4_ + b0))
            {
                if (!p_149830_1_.isRemote)
                {
                    EntityFallingBlock entityfallingblock = new EntityFallingBlock(p_149830_1_, (double)((float)p_149830_2_ + 0.5F), (double)((float)p_149830_3_ + 0.5F), (double)((float)p_149830_4_ + 0.5F), this, p_149830_1_.getBlockMetadata(p_149830_2_, p_149830_3_, p_149830_4_));
                    this.func_149829_a(entityfallingblock);
                    p_149830_1_.spawnEntityInWorld(entityfallingblock);
                }
            }
            
            else
            {
                p_149830_1_.setBlockToAir(p_149830_2_, p_149830_3_, p_149830_4_);

                while (func_149831_e(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && p_149830_3_ > 0)
                {
                    --p_149830_3_;
                }

                if (p_149830_3_ > 0)
                {
                    p_149830_1_.setBlock(p_149830_2_, p_149830_3_, p_149830_4_, this);
                }
            }
            
        }
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    
}