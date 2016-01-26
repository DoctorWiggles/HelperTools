package helpertools.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helpertools.HelpTab;
import helpertools.Main;
import helpertools.blocks.tile_entities.TileEntityIllusion;
import helpertools.entities.EntityDynamiteProjectile;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class IllusionBlock extends BlockBreakable implements ITileEntityProvider
{
    @SideOnly(Side.CLIENT)
    private IIcon field_150200_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_150199_b;
    private static final String __OBFID = "CL_00000273";

    public IllusionBlock(Material glass, boolean b, String name)
    {
    	 super(name, glass, b);
        this.setBlockName("IllusionBlock");
        this.setCreativeTab(HelpTab.HelperTools);       
        //this.setCreativeTab(CreativeTabs.tabBlock);
        this.setBlockTextureName("helpertools:IllusionFace");
        this.setHardness(0.6F);
        setHarvestLevel("pickaxe",0);
        
        
    }
    
    /**
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		return Item.getItemFromBlock(Helpertoolscore.IllusionBlock);
    }
    **/
   
    
	
    public IIcon[] nonactive = new IIcon[6];
	public IIcon[] active = new IIcon[6];
    
    
    
    public IIcon[] icons = new IIcon[6];
    
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    
    @Override
    public void registerBlockIcons(IIconRegister reg) {
       // for (int i = 0; i < 6; i ++) {
            this.icons[0] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[1] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[2] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[3] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[4] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[5] = reg.registerIcon(this.textureName + "_" + 1);
            
            this.nonactive[0] = reg.registerIcon(this.textureName + "_" + 1);
            this.nonactive[1] = reg.registerIcon(this.textureName + "_" + 1);
            this.nonactive[2] = reg.registerIcon(this.textureName + "_" + 1);
            this.nonactive[3] = reg.registerIcon(this.textureName + "_" + 1);
            this.nonactive[4] = reg.registerIcon(this.textureName + "_" + 1);
            this.nonactive[5] = reg.registerIcon(this.textureName + "_" + 1);
            
            //this.active[0] = reg.registerIcon(this.textureName + "_" + 1);
            //this.active[1] = reg.registerIcon(this.textureName + "2_" + 3);
            //this.active[2] = reg.registerIcon(this.textureName + "2_" + 2);
            //this.active[3] = reg.registerIcon(this.textureName + "2_" + 2);
            //this.active[4] = reg.registerIcon(this.textureName + "2_" + 2);
            //this.active[5] = reg.registerIcon(this.textureName + "2_" + 2);
            
        //}
    }
    @Override
	public IIcon getIcon(IBlockAccess i, int x, int y, int z, int side) {
		switch (i.getBlockMetadata(x, y, z)) {
		case 1:
			return this.active[side];
		case 0:
			return this.nonactive[side];
		default:
			return this.nonactive[side];
		}
	}
    
    @Override
    public IIcon getIcon(int side, int meta) {
    	switch (meta) {
		case 1:
			return this.active[side];
		case 0:
			return this.nonactive[side];
		default:
			return this.nonactive[side];
		}
       // return this.icons[side];
    }
    
    
    
    /**
     * Tile entity construction
     * Orange Tutorials 
     * 
     */
    

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityIllusion();
    }

    @Override
    public boolean hasTileEntity(int metadata) {

        return true;
    }
    
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer player, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	
    		//tileentityhopper
    	TileEntityIllusion tile = (TileEntityIllusion)p_149727_1_.getTileEntity(p_149727_2_, p_149727_3_, p_149727_4_);

            
            if (tile != null)
            {
            	ItemStack itemstack = player.inventory.getCurrentItem();
            	/**
                if (itemstack != null && itemstack.getItem() == Items.bowl && !player.capabilities.isCreativeMode)
                {
                	
                }
                **/
                if (itemstack != null)
                {
                	return false;
                }
                //p_149727_5_.func_146093_a(tileentityhopper);
                	//(tileentityhopper).atck7 = 6;
            	if(!player.isSneaking()){

                    p_149727_1_.playSoundEffect((double)p_149727_2_ + 0.5D, (double)p_149727_3_ + 0.5D, (double)p_149727_4_ + 0.5D, "random.click", 0.3F,   0.5F);
            	}
            }

            return true;
        
    }
    
    
    
}