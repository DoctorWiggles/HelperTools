package helpertools.Com.Items;

import helpertools.Main;
import helpertools.Com.Config;
import helpertools.Com.Entity.Entity_FlyingItem;
import helpertools.Com.Entity.Entity_Mirage;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import helpertools.Utils.Texty;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
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
      list.add("");
      list.add(TextFormatting.ITALIC + "While sneaking press 'p' to create a shadow");
      list.add(TextFormatting.ITALIC + "Press again to swap places with your shadow");      
      }
	
	public boolean hasCustomEntity(ItemStack stack)
    {
        return true;
    }
	//Custom Item Entity
    public Entity createEntity(World world, Entity location, ItemStack itemstack)
    {
    	if(Config.No_Fun_Allowed){return null;}
    	BlockPos pos = location.getPosition();
    	Entity_FlyingItem  ent = new Entity_FlyingItem(world, itemstack);
    	
    	ent.setLocationAndAngles(pos.getX()+0.5,pos.getY(), pos.getZ()+0.5, 0, 0);
        return ent;
    }
    
 	//Right click Action
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
    	if(player.isSneaking()){    
    		Create_Mirage(stack, player, world);
    		
    		Texty.Sound_Server(player, SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.2F, 1.0F);}
    	
    	if(!player.isSneaking()){
    		Swap_Mirage(stack, player, world);   
    		
    		Texty.Sound_Server(player, SoundEvents.ENTITY_GHAST_SHOOT, 1.5F, 0.5F);     		 	
    		for (int i = 0; i < (Main.Randy.nextInt(50)*2)+20; ++i)
    			Texty.Particle(2F, player, EnumParticleTypes.SMOKE_NORMAL,0,0,0);}
    	
    	return new ActionResult(EnumActionResult.SUCCESS, stack);
    	
    }
    
    public void damageHat(EntityPlayer player, ItemStack stack){
		if(player.capabilities.isCreativeMode){return;}		
		stack.damageItem(1, player);
		
		if(stack.getItemDamage() <= 0){
			player.inventory.setInventorySlotContents(39, null);			
		}
		
	}
    
    
//====================== Save Data & Lookup Functions ==========================================//
	
    //Default ID
    public static final UUID noID = new UUID(0,0);
    
    //Default Data Setter
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isheld) {  
    	
    	if(world.isRemote){return;}
    	
    	if (!stack.hasTagCompound()) {    		
    		stack.setTagCompound(new NBTTagCompound()); 
    		stack.getTagCompound().setUniqueId("Mirage", noID);
    	}    
    	
    }
    
    //Look for Mirage from data
    @Nullable
    public Entity_Mirage getMirage(ItemStack stack, World world ){    	
    	try{
            UUID uuid = this.getShadeID(stack);
            return uuid == null ? null : this.getMirageByUUID(world, uuid);
        }
        catch (IllegalArgumentException var2)
        {
            return null;
        }
    	
    }
    //Actual Look up
    @Nullable
    public Entity_Mirage getMirageByUUID(World world, UUID uuid)
    {	
        for (int i = 0; i < world.loadedEntityList.size(); ++i)
        {
        	Entity entity = (Entity)world.loadedEntityList.get(i);

            if (uuid.equals(entity.getUniqueID()))
            {	Entity_Mirage mirage = (Entity_Mirage)entity;            	
                return mirage;
            }
        }
        return null;
    }
    
    //Getter
    public UUID getShadeID(ItemStack stack) {
		return stack.getTagCompound().getUniqueId("Mirage");
	}        
    //Setter   
    public void setShade(ItemStack stack, UUID Value)
   	{
    	if (!stack.hasTagCompound()) {    		
    		stack.setTagCompound(new NBTTagCompound()); 
    	}    	
    	stack.getTagCompound().setUniqueId("Mirage",  Value );    	
   	} 
    	
    	
//=================================== Mirage Functions ============================================//
    
	public BlockPos Swap_Mirage(ItemStack stack, EntityPlayer player,  World world){
		//if(world.isRemote){return;}
		try{
			
		Entity_Mirage mob = this.getMirage(stack, world);
		if(mob == null || player == null){return null;}
		
		BlockPos M = mob.getPosition();
		BlockPos P = player.getPosition();
		
		//System.out.println(player.getDistanceToEntity(mob));
		damageHat(player, stack);
		
		mob.setLocationAndAngles(P.getX()+0.5, P.getY(), P.getZ()+0.5, mob.rotationYaw, mob.rotationPitch);
		player.setLocationAndAngles(M.getX()+0.5, M.getY(), M.getZ()+0.5, player.rotationYaw, player.rotationPitch );
		return M;
		
		}catch(Exception e){}
		return null;
	}
	
	public void Create_Mirage(ItemStack stack, EntityPlayer player, World world){		
		if(world.isRemote){return;}	
			this.Destroy_Old_Mirage(stack, player, world);
		try{
			
		BlockPos pos = player.getPosition();
		UUID ID = player.getUniqueID();
		if(ID == null){return;}
		
		Entity_Mirage mob = new Entity_Mirage(world,ID, player);
		mob.setLocationAndAngles(pos.getX()+0.5,pos.getY(), pos.getZ()+0.5, 0, 0);
		this.setShade(stack, mob.getUniqueID());
		world.spawnEntityInWorld(mob);
		damageHat(player, stack);
		}catch(Exception e){}
		
	}
	
	public void Destroy_Old_Mirage(ItemStack stack, EntityPlayer player, World world){
		if(world.isRemote){return;}	
		try{
			
		Entity_Mirage mob = this.getMirage(stack, world);
		if(mob != null)mob.setDead();
		
		} catch (Exception e){}
		
	}
	
}
