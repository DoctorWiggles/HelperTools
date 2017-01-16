package helpertools.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helpertools.ModRegistry;
import helpertools.HelpTab;
import helpertools.Main;
import helpertools.entities.Dynamite_Projectile;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class Charm_block extends Block
{
    public Charm_block(String unlocal)
    {
        super(Material.clay);
        this.setBlockName(unlocal);    
        this.setBlockTextureName("helpertools:block_cbc");
        //this.setHardness(0.6F);
        setHarvestLevel("pickaxe",0);
        setBlockUnbreakable();
        setResistance(100);
        this.setCreativeTab(HelpTab.HelperTools);  
        this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);    
        
        
    }
	
   
    
	  public boolean isOpaqueCube()
	    {
	        return false;
	    }

	    public boolean renderAsNormalBlock()
	    {
	        return false;
	    }
	    public boolean canDropFromExplosion(Explosion moo)
	    {
	        return false;
	    }
	    
	    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	    {
	        return null;
	    }

	   
    public IIcon[] icons = new IIcon[6];
    
    @Override
    public void registerBlockIcons(IIconRegister reg) {
       // for (int i = 0; i < 6; i ++) {
            this.icons[0] = reg.registerIcon(this.textureName + "_" + 2);
            this.icons[1] = reg.registerIcon(this.textureName + "_" + 1);
            this.icons[2] = reg.registerIcon(this.textureName + "_" + 0);
            this.icons[3] = reg.registerIcon(this.textureName + "_" + 0);
            this.icons[4] = reg.registerIcon(this.textureName + "_" + 0);
            this.icons[5] = reg.registerIcon(this.textureName + "_" + 0);
            
        //}
    }
    
    @Override
    public IIcon getIcon(int side, int meta) {
    	  if (meta > 5)
    	        meta = 0;
        return this.icons[side];
    }
    
    /*
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 6; i ++) {
            list.add(new ItemStack(item, 1, i));
        }
    }
    */
    
    
    public boolean onBlockActivated(World world, int x1, int y1, int z1, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        //if (!world.isRemote ){return true;}
    	if(!player.capabilities.isCreativeMode){return false;}
        int meta;
        meta = world.getBlockMetadata(x1, y1, z1);
        
        int next_meta = 0;
           
        if(player.isSneaking()){
			  if(meta == 0){
				  return false;
			  }
			  next_meta = 0;
		  }
		  if(!player.isSneaking()){
			  
			  next_meta= meta + 1;
			  if(next_meta >5){next_meta = 0;}
		  }		
		  
		  
        world.setBlockMetadataWithNotify(x1, y1, z1, next_meta, 2);
        world.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, (double)z1 + 0.5D, "random.click", 0.3F,   0.5F);
        //System.out.println(next_meta);
        if (!world.isRemote ){
        ChatComponentTranslation text = new ChatComponentTranslation(EnumChatFormatting.GRAY +"Boost: +"+ next_meta*5, new Object[0]);
   	 	ChatComponentTranslation text2 = new ChatComponentTranslation(EnumChatFormatting.GRAY +"Boost: off", new Object[0]);
   	 	if (next_meta == 0){ player.addChatComponentMessage(text2); }
   	 	else
   	 		player.addChatComponentMessage(text); 
        }
        
       
        	return true;
        
    }
    
    
}

