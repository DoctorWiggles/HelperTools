package helpertools.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helpertools.Common_Registry;
import helpertools.HelpTab;
import helpertools.Helpertoolscore;
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

public class MagicalFuelBlock extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon field_150200_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_150199_b;
    private static final String __OBFID = "CL_00000273";

    
    
    public MagicalFuelBlock()
    {
        super(Material.clay);
        //this.setTickRandomly(true);
        this.setBlockName("MagicalFuelBlock");
        this.setCreativeTab(HelpTab.HelperTools);       
        this.setBlockTextureName("helpertools:MagicalFuel3");
        this.setHardness(0.6F);
        setHarvestLevel("pickaxe",0);
      
        this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
        
        //this.setLightLevel(0.3F);
        
        
    }
	
	
    
	  public boolean isOpaqueCube()
	    {
	        return false;
	    }

	    public boolean renderAsNormalBlock()
	    {
	        return false;
	    }
	    
	    /**
	    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	    {
	        if (p_149727_1_.isRemote)
	        {
	            
	            return true;
	        }
	        if(!player.isSneaking()){
	        	
	        	
	        	return false;
	        }
	        
	        	
	        if (p_149727_1_.getBlockMetadata(p_149727_2_, p_149727_3_, p_149727_4_)== 0)
	        {
	            p_149727_1_.setBlockMetadataWithNotify(p_149727_2_, p_149727_3_, p_149727_4_, 1, 2);
	            ((p_149727_1_.getBlock(p_149727_2_, p_149727_3_, p_149727_4_))).setLightLevel(0.3F);
	            p_149727_1_.playSoundEffect((double)p_149727_2_ + 0.5D, (double)p_149727_3_ + 0.5D, (double)p_149727_4_ + 0.5D, "random.click", 0.3F,   0.5F);
	           

	            return true;
	        }
	        else if (p_149727_1_.getBlockMetadata(p_149727_2_, p_149727_3_, p_149727_4_)== 1)
	        {
	            p_149727_1_.setBlockMetadataWithNotify(p_149727_2_, p_149727_3_, p_149727_4_, 0, 2);
	            ((p_149727_1_.getBlock(p_149727_2_, p_149727_3_, p_149727_4_))).setLightLevel(1F);
	            p_149727_1_.playSoundEffect((double)p_149727_2_ + 0.5D, (double)p_149727_3_ + 0.5D, (double)p_149727_4_ + 0.5D, "random.click", 0.3F,   0.5F);
	         

	            return true;
	        }
	        else {
	        	return false;
	        }
	    }
	    **/
	    
	    /////////////////////////////
	    /** Redstone Functionality**/
	    /////////////////////////////
	    
	    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
	    {
	        if (!p_149726_1_.isRemote)
	        {
	            if ( !p_149726_1_.isBlockIndirectlyGettingPowered(p_149726_2_, p_149726_3_, p_149726_4_))
	            {
	                p_149726_1_.scheduleBlockUpdate(p_149726_2_, p_149726_3_, p_149726_4_, this, 4);
	            }
	            else if ( p_149726_1_.isBlockIndirectlyGettingPowered(p_149726_2_, p_149726_3_, p_149726_4_))
	            {
	                p_149726_1_.setBlock(p_149726_2_, p_149726_3_, p_149726_4_, Common_Registry.ActiveMagicalFuelBlock);
	            }
	        }
	    }
	    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	    {
	        if (!p_149695_1_.isRemote)
	        {
	            if ( !p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_))
	            {
	                p_149695_1_.scheduleBlockUpdate(p_149695_2_, p_149695_3_, p_149695_4_, this, 4);
	            }
	            else if ( p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_))
	            {
	                p_149695_1_.setBlock(p_149695_2_, p_149695_3_, p_149695_4_, Common_Registry.ActiveMagicalFuelBlock);
	            }
	        }
	    }
	    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
	    {
	        if (!p_149674_1_.isRemote && !p_149674_1_.isBlockIndirectlyGettingPowered(p_149674_2_, p_149674_3_, p_149674_4_))
	        {
	            p_149674_1_.setBlock(p_149674_2_, p_149674_3_, p_149674_4_, Common_Registry.ActiveMagicalFuelBlock);
	        }
	    }

	    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	    {
	        return Item.getItemFromBlock(Common_Registry.MagicalFuelBlock);
	    }
	    
    
    
    public IIcon[] icons = new IIcon[6];
    
    @Override
    public void registerBlockIcons(IIconRegister reg) {
       // for (int i = 0; i < 6; i ++) {
            this.icons[0] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[1] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[2] = reg.registerIcon(this.textureName + "_" + 3);
            this.icons[3] = reg.registerIcon(this.textureName + "_" + 3);
            this.icons[4] = reg.registerIcon(this.textureName + "_" + 3);
            this.icons[5] = reg.registerIcon(this.textureName + "_" + 3);
            
        //}
    }
    
    @Override
    public IIcon getIcon(int side, int meta) {
        return this.icons[side];
    }
}