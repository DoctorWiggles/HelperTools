package helpertools.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helpertools.ModRegistry;
import helpertools.HelpTab;
import helpertools.Main;
import helpertools.entities.Dynamite_Projectile;
import helpertools.util.InventoryUtil;

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
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class ActiveMagicalFuelBlock extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon field_150200_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_150199_b;
    private static final String __OBFID = "CL_00000273";

    public ActiveMagicalFuelBlock(String name)
    {
        super(Material.clay);
        this.setBlockName(name);
        //this.setCreativeTab(HelpTab.HelperTools);       
        this.setBlockTextureName("helpertools:MagicalFuel3");
        this.setHardness(0.6F);
        setHarvestLevel("pickaxe",0);
        this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
        
        this.setLightLevel(1F);        
    }
	
	
    
	  	public boolean isOpaqueCube(){ return false; }

	    public boolean renderAsNormalBlock(){ return false; }
	    
	    Block torch = Blocks.redstone_torch;
	    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	    {
	        if(world.getBlockMetadata(x, y, z) == 1){
	        	world.setBlock(x, y, z, ModRegistry.MagicalFuelBlock, 0, 123);    
            	world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "mob.chicken.plop", 0.3F,   0.5F);
            	
            	if(!player.capabilities.isCreativeMode){torch.dropBlockAsItem(world,x,y,z, 0, 0);}
            	return true;
	        }	        
	    	else{world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "random.click", 0.3F,   0.5F);
        	return true;}
	        
	    }

	    /////////////////////////////
	    /** Redstone Functionality**/
	    /////////////////////////////
	    
	    
	    public void onBlockAdded(World world, int x, int y, int z)
	    {
	        if (!world.isRemote)
	        {
	            if ( !world.isBlockIndirectlyGettingPowered(x, y, z) && world.getBlockMetadata(x, y, z) == 0)
	            {
	            	world.setBlock(x, y, z, ModRegistry.MagicalFuelBlock);
	               
	            }
	            else if ( world.isBlockIndirectlyGettingPowered(x, y, z))
	            {
	            	world.scheduleBlockUpdate(x, y, z, this, 4);
	                
	            }
	        }
	    }
	    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	    {
	        if (!world.isRemote)
	        {
	            if ( !world.isBlockIndirectlyGettingPowered(x, y, z) && world.getBlockMetadata(x, y, z) == 0)
	            {
	            	world.setBlock(x, y, z, ModRegistry.MagicalFuelBlock);
	                
	            }
	            else if ( world.isBlockIndirectlyGettingPowered(x, y, z))
	            {
	            	world.scheduleBlockUpdate(x, y, z, this, 4);
	            }
	        }
	    }

	    public void updateTick(World world, int x, int y, int z, Random rand)
	    {
	        if (!world.isRemote && world.isBlockIndirectlyGettingPowered(x, y, z) && world.getBlockMetadata(x, y, z) == 0)
	        {
	            world.setBlock(x, y, z, ModRegistry.MagicalFuelBlock);
	        }
	    }

	    public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_)
	    {
	        return Item.getItemFromBlock(ModRegistry.MagicalFuelBlock);
	    }
	    
    
    
    public IIcon[] icons = new IIcon[6];
    
    @Override
    public void registerBlockIcons(IIconRegister reg) {
       // for (int i = 0; i < 6; i ++) {
            this.icons[0] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[1] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[2] = reg.registerIcon(this.textureName + "_" + 2);
            this.icons[3] = reg.registerIcon(this.textureName + "_" + 2);
            this.icons[4] = reg.registerIcon(this.textureName + "_" + 2);
            this.icons[5] = reg.registerIcon(this.textureName + "_" + 2);
            
        //}
    }
    
    @Override
    public IIcon getIcon(int side, int meta) {
        return this.icons[side];
    }
}