package helpertools.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helpertools.HelpTab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class ItemPowerCrystal extends Item {

	public ItemPowerCrystal()
	{
    	super();        
        this.maxStackSize = 32;  
	    setUnlocalizedName("powercrystal");
        setCreativeTab(HelpTab.HelperTools);
        this.setHasSubtypes(true);
	    //setTextureName("helpertools:PowerFlake");
    }
    
	public IIcon[] Crystal = new IIcon[1];
	public IIcon[] Bone = new IIcon[1];
	
	/**
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {		
		switch (meta) {
		case 1:
			return this.Crystal[1];
		case 0:
			return this.Bone[1];
		default:
			return this.Crystal[1];
		}
    }
	
	@SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack meta)
    {
		this.Bone[1] = reg.
        return this.getIconFromDamage(meta.getItemDamage());
    }
	
	**/
	public IIcon[] icons = new IIcon[2];

	//...

	@Override
	public void registerIcons(IIconRegister reg) {
	    for (int i = 0; i < 2; i ++) {
	        //this.icons[i] = reg.registerIcon("helpertools" + ":PowerFlake" + i);
	        this.icons[i] = reg.registerIcon("helpertools" + ":PowerFlake_" + i);
	    }
	}
	@Override
	public IIcon getIconFromDamage(int meta) {
	    if (meta > 1)
	        meta = 0;

	    return this.icons[meta];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 2; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}
	
	/**
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
    **/
    
}
