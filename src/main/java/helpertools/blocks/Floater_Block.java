package helpertools.blocks;

import helpertools.Common_Registry;
import helpertools.HelpTab;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Floater_Block extends Block
{
    public Floater_Block(String unlocal)
    {
    	super(Material.cloth);
        this.setBlockName(unlocal);    
        this.setBlockTextureName("helpertools:floater");
        this.setHardness(0.2F);
        setHarvestLevel("axe",0);
        this.setCreativeTab(HelpTab.HelperTools);   
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
    	return Item.getItemFromBlock(this);
    }
    
    public IIcon[] icons = new IIcon[6];
    
    @Override
    public void registerBlockIcons(IIconRegister reg) {
       // for (int i = 0; i < 6; i ++) {
            this.icons[0] = reg.registerIcon(this.textureName + "_" + 2);
            this.icons[1] = reg.registerIcon(this.textureName + "_" + 0);
            this.icons[2] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[3] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[4] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[5] = reg.registerIcon(this.textureName + "_" + 1);
            
        //}
    }
    
    @Override
    public IIcon getIcon(int side, int meta) {
    	  if (meta > 5)
    	        meta = 0;
        return this.icons[side];
    }
    
    Block balloon = Common_Registry.Balloon;
    
    public int onBlockPlaced(World world, int x, int y, int z, int p_149660_5_, float f1, float f2, float f3, int p_149660_9_)
    {
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
    	else{balloon.dropBlockAsItem(world,x,y,z, 0, 0);}
        return p_149660_9_;
    }
    
    /**
    @Override
    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity)
    {
        return true;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int x, int y, int p_149668_4_)
    {
        float f = 0.325F;
        return AxisAlignedBB.getBoundingBox((double)((float)x + f), (double)y, (double)((float)p_149668_4_), (double)((float)(x + 1) - f), (double)((float)(y + 1) ), (double)((float)(p_149668_4_ + 1) - f));
    }
    **/
}