package helpertools.Common.Items;

import helpertools.Common.Entity.Entity_FlyingItem;
import helpertools.Utils.HelpTab;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Item_MirageHusk extends ItemArmor{

	
	public Item_MirageHusk(String unlocalizedName, ArmorMaterial material){
		super(material, 0, EntityEquipmentSlot.HEAD);
		this.maxStackSize = 1;  
		this.setUnlocalizedName(unlocalizedName);
		setCreativeTab(HelpTab.HelperTools);
    
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
      {
      list.add(TextFormatting.ITALIC + "An onimous mask");
      list.add(TextFormatting.ITALIC + "It seems to have a will of it's own");
      list.add(TextFormatting.ITALIC + "Press 'o' to create a shadow");
      list.add(TextFormatting.ITALIC + "Press 'o' to swap places with your shadow");
      
      }
	
	
	public boolean hasCustomEntity(ItemStack stack)
    {
        return true;
    }

    public Entity createEntity(World world, Entity location, ItemStack itemstack)
    {
    	BlockPos pos = location.getPosition();
    	Entity_FlyingItem  ent = new Entity_FlyingItem(world, itemstack);
    	
    	ent.setLocationAndAngles(pos.getX()+0.5,pos.getY(), pos.getZ()+0.5, 0, 0);
    	//world.spawnEntityInWorld(ent );
        return ent;
    }
	
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
		
		World world = playerIn.worldObj;
		if(world.isRemote){return false;}
		BlockPos pos = target.getPosition();
		Entity_FlyingItem  ent = new Entity_FlyingItem(world, stack);
		ent.setLocationAndAngles(pos.getX()+0.5,pos.getY(), pos.getZ()+0.5, 0, 0);
		//ent.
		world.spawnEntityInWorld(ent );
		ent.startRiding(target);
       int ID = target.getEntityId();       
       System.out.println(ID);
       
       
       Entity mob = world.getEntityByID(ID);
       //mob= mob.gete
       if(mob == null) return false;
       //mob.addVelocity(0, 2, 0);
      
       
       
       
       return true;
    }
	
}
