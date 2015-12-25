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

public class StokedPipe extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon field_150200_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_150199_b;
    private static final String __OBFID = "CL_00000273";

    public StokedPipe()
    {
        super(Material.clay);
        //this.setTickRandomly(true);
        this.setBlockName("StokedPipe");
        this.setCreativeTab(HelpTab.HelperTools);       
        this.setBlockTextureName("helpertools:stoked2");
        this.setHardness(0.6F);
        setHarvestLevel("pickaxe",0);
       // this.slipperiness = 1F;
        this.setBlockBounds(0.33F, 0.0F, 0.33F, 0.66F, 1.0F, 0.66F);
    }
		
	public boolean isOpaqueCube(){ return false;}
	
	public boolean renderAsNormalBlock(){ return false;}
    
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
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
    protected static Random growrand = new Random();
    
    float minf = .0f;
    float maxf = .2f;

    Random randf = new Random();
    
    float ming = .0f;
    float maxg = .2f;

    Random randg = new Random();
    
   
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	super.updateTick(world,x,y, z,rand);
    	short short1 =8;
		for (int lp = 0; lp < short1; ++lp)
       {
    	 world.spawnParticle("largesmoke", (double)((float)x + rand.nextFloat()), (double)((float)y + 1.1F), (double)((float)z + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
       }
    }
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        super.randomDisplayTick(world, x, y, z, rand);

        if(world.getBlock(x, y+1, z) == Blocks.air
        		&& world.getBlock(x, y-1, z) != Blocks.air){
            world.spawnParticle("smoke", (double)((float)x + rand.nextFloat()), (double)((float)y + 1.1F), (double)((float)z + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
            world.spawnParticle("smoke", (double)((float)x + rand.nextFloat()), (double)((float)y + 1.1F), (double)((float)z + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
            world.spawnParticle("largesmoke", (double)((float)x+.5), (double)((float)y + 1.1F), (double)((float)z+.5), 0.0D, 0.0D, 0.0D);
            //largesmoke, smoke, portal, snowballpoof    offset; ? ;directions
           
        }
        /**
        else if(world.getBlock(x, y+1, z) != Blocks.air
        		&& world.getBlock(x, y-1, z) == Blocks.air){
    	   
    	   float finalf = randf.nextFloat() * (maxf - minf) + minf;
    	   float finalg = randg.nextFloat() * (maxg - ming) + ming;
            world.spawnParticle("dripWater", (double)((float)x+.5+ finalf), (double)((float)y - 0.05F), (double)((float)z+.5+ finalg), 0.0D, 0.0D, 0.0D);
            world.spawnParticle("dripWater", (double)((float)x +.5+ finalf), (double)((float)y - 0.05F), (double)((float)z+.5+ finalg), 0.0D, 0.0D, 0.0D);
            world.spawnParticle("bubble", (double)((float)x+.5+ finalf), (double)((float)y - 0.05F), (double)((float)z+.5+ finalg), 0.0D, 0.0D, 0.0D);
            //largesmoke, smoke, portal, snowballpoof    offset; ? ;directions
           
        }
        **/
    }
}