package helpertools.blocks;

import helpertools.HelpTab;
import helpertools.Helpertoolscore;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ObeliskBlock extends Block implements ITileEntityProvider{

    @SideOnly(Side.CLIENT)
    private IIcon field_150200_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_150199_b;
    private static final String __OBFID = "CL_00000273";

    public ObeliskBlock()
    {
        super(Material.clay);
        this.setBlockName("ObeliskBlock");
        this.setCreativeTab(HelpTab.HelperTools);       
        //this.setCreativeTab(CreativeTabs.tabBlock);
        this.setBlockTextureName("helpertools:Transcriber");
        this.setHardness(0.6F);
        setHarvestLevel("pickaxe",0);
        
        this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 2.0F, 0.8F);
        
        
    }
    
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		return Item.getItemFromBlock(Helpertoolscore.ObeliskBlock);
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
       return false;
    }
   
    
	
    public IIcon[] nonactive = new IIcon[6];
	public IIcon[] active = new IIcon[6];
    
    
    
    public IIcon[] icons = new IIcon[6];
    
    @Override
    public void registerBlockIcons(IIconRegister reg) {
       // for (int i = 0; i < 6; i ++) {
            this.icons[0] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[1] = reg.registerIcon(this.textureName + "_" + 3);
            this.icons[2] = reg.registerIcon(this.textureName + "_" + 2);
            this.icons[3] = reg.registerIcon(this.textureName + "_" + 2);
            this.icons[4] = reg.registerIcon(this.textureName + "_" + 2);
            this.icons[5] = reg.registerIcon(this.textureName + "_" + 2);
            
            this.nonactive[0] = reg.registerIcon(this.textureName + "_" + 1);
            this.nonactive[1] = reg.registerIcon(this.textureName + "_" + 3);
            this.nonactive[2] = reg.registerIcon(this.textureName + "_" + 2);
            this.nonactive[3] = reg.registerIcon(this.textureName + "_" + 2);
            this.nonactive[4] = reg.registerIcon(this.textureName + "_" + 2);
            this.nonactive[5] = reg.registerIcon(this.textureName + "_" + 2);
            
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
     * @Orange Tutorials 
     * 
     */
    

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityObelisk();
    }

    @Override
    public boolean hasTileEntity(int metadata) {

        return true;
    }
}
    