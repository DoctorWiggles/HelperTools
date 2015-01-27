package helpertools.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helpertools.HelpTab;
import helpertools.entities.EntityDynamiteProjectile;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
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
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class TranscriberBlock extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon field_150200_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_150199_b;
    private static final String __OBFID = "CL_00000273";

    public TranscriberBlock()
    {
        super(Material.rock);
        this.setBlockName("TranscriberBlock");
        this.setCreativeTab(HelpTab.HelperTools);       
        //this.setCreativeTab(CreativeTabs.tabBlock);
        this.setBlockTextureName("helpertools:Transcriber");
        this.setHardness(0.6F);
        
        
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
            
            this.active[0] = reg.registerIcon(this.textureName + "_" + 1);
            this.active[1] = reg.registerIcon(this.textureName + "2_" + 3);
            this.active[2] = reg.registerIcon(this.textureName + "2_" + 2);
            this.active[3] = reg.registerIcon(this.textureName + "2_" + 2);
            this.active[4] = reg.registerIcon(this.textureName + "2_" + 2);
            this.active[5] = reg.registerIcon(this.textureName + "2_" + 2);
            
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
    
    
    
    
    
    
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (p_149727_1_.isRemote)
        {
            
            return true;
        }
        if(!player.isSneaking()){
        	
        	

        	int i1 = p_149727_2_;
        	int j1 = p_149727_3_ ;
        	int k1 = p_149727_4_;
        	EntitySkeleton entityskeleton = new EntitySkeleton(p_149727_1_);
            entityskeleton.setLocationAndAngles(i1+.5, j1+.3, k1+.5, 0, 0.0F);
            //entityskeleton.onSpawnWithEgg((IEntityLivingData)null);
            p_149727_1_.spawnEntityInWorld(entityskeleton);
            
        	//player.displayGUIWorkbench(p_149727_2_, p_149727_3_, p_149727_4_);
        	return false;
        }
        	
        if (p_149727_1_.getBlockMetadata(p_149727_2_, p_149727_3_, p_149727_4_)== 0)
        {
            //int i1 = p_149727_1_.getBlockMetadata(p_149727_2_, p_149727_3_, p_149727_4_);
            //int j1 = i1 & 7;
            //int k1 = 8 - (i1 & 8);
            p_149727_1_.setBlockMetadataWithNotify(p_149727_2_, p_149727_3_, p_149727_4_, 1, 2);
            p_149727_1_.playSoundEffect((double)p_149727_2_ + 0.5D, (double)p_149727_3_ + 0.5D, (double)p_149727_4_ + 0.5D, "random.click", 0.3F,   0.5F);
            //p_149727_1_.notifyBlocksOfNeighborChange(p_149727_2_, p_149727_3_, p_149727_4_, this);

            return true;
        }
        else if (p_149727_1_.getBlockMetadata(p_149727_2_, p_149727_3_, p_149727_4_)== 1)
        {
            //int i1 = p_149727_1_.getBlockMetadata(p_149727_2_, p_149727_3_, p_149727_4_);
            //int j1 = i1 & 7;
            //int k1 = 8 - (i1 & 8);
            p_149727_1_.setBlockMetadataWithNotify(p_149727_2_, p_149727_3_, p_149727_4_, 0, 2);
            p_149727_1_.playSoundEffect((double)p_149727_2_ + 0.5D, (double)p_149727_3_ + 0.5D, (double)p_149727_4_ + 0.5D, "random.click", 0.3F,   0.5F);
            //p_149727_1_.notifyBlocksOfNeighborChange(p_149727_2_, p_149727_3_, p_149727_4_, this);

            return true;
        }
        else {
        	return false;
        }
    }
    
    
    
    
}