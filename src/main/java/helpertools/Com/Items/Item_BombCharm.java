package helpertools.Com.Items;

import helpertools.Com.Config;
import helpertools.Com.Entity.Entity_FlyingItem;
import helpertools.Utils.HelpTab;
import helpertools.Utils.ModUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Item_BombCharm extends Item{

	public Item_BombCharm(String unlocalizedName){
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName(unlocalizedName);
		setCreativeTab(HelpTab.HelperTools);
    
    }
	
	@Override	  
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
	{
		int max = getlevel(stack);
		if(max > Config.BombCharm_Level){max = Config.BombCharm_Level;}
		int cur = getsize(stack);

		
		if(player.capabilities.isCreativeMode){
			if(cur+5 >= 30){
				setsize(stack, 0);
				if(Config.ToolModeMesseges){
					if(!player.worldObj.isRemote)ModUtil.print(player,TextFormatting.GRAY, "Boost: off");
					ModUtil.Sound_Server(world, player, SoundEvents.BLOCK_LAVA_EXTINGUISH, (float)(1), (float)(1.3));
				}
			}
			else{ 
				setsize(stack, cur+5);
				if(Config.ToolModeMesseges){
					if(!player.worldObj.isRemote)ModUtil.print(player,TextFormatting.GRAY, "Boost: +"+(cur+5));
					ModUtil.Sound_Server(world, player, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, (float)(.8), (float)( itemRand.nextFloat()*.75+.2));
				}
				}
		}
		else{
			if(cur>= max){
				setsize(stack, 0);
				if(Config.ToolModeMesseges){
					if(!player.worldObj.isRemote)ModUtil.print(player,TextFormatting.GRAY, "Boost: off");
					ModUtil.Sound_Server(world, player, SoundEvents.BLOCK_LAVA_EXTINGUISH, (float)(1), (float)(1.3));
				}
			}
			else{
				setsize(stack, cur+1);
				if(Config.ToolModeMesseges){
					if(!player.worldObj.isRemote)ModUtil.print(player,TextFormatting.GRAY, "Boost: +"+(cur+1));
					ModUtil.Sound_Server(world, player, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, (float)(.8), (float)( itemRand.nextFloat()*.75+.2));
				}
			}
		}
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
	
	public boolean hasCustomEntity(ItemStack stack)
    {
		if(getlevel(stack) >3)return true;
		else return false;
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
	//==========================================================================//
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
			list.add(TextFormatting.ITALIC + "Boosts bomb radius");
			list.add(TextFormatting.ITALIC + "Keep anywhere in inventory");				
			list.add(TextFormatting.ITALIC + "Right click to adjust sizes");
			list.add(TextFormatting.ITALIC + " ");
			list.add(TextFormatting.ITALIC + "Craft with other charms to upgrade level");
			list.add(TextFormatting.ITALIC + "Each level requires an additional charm");
			list.add(TextFormatting.ITALIC + " ");
			if (stack.hasTagCompound()){
			list.add(TextFormatting.ITALIC + "Current size / level: "+getsize(stack)+" / "+ getlevel(stack));
			}
			
			list.add(TextFormatting.ITALIC + "Much larger effects while in creative");
			if(Config.BombCharm_Ward){
			list.add(TextFormatting.ITALIC + "Will consume itself to -");
			list.add(TextFormatting.ITALIC + "- prevent lethal explosion damage");
			}
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isheld) 
	 {
	    	if (!stack.hasTagCompound()) {
	    		stack.setTagCompound(new NBTTagCompound());
	    		stack.getTagCompound().setInteger("level", 1);   
	    		stack.getTagCompound().setInteger("size", 0); 
	    	}
	    	
	}
	
	

	//=================================================================//
	
	public int getlevel(ItemStack stack) {
		if (!stack.hasTagCompound()) {
    		stack.setTagCompound(new NBTTagCompound());
    		stack.getTagCompound().setInteger("level", 1);   
    		stack.getTagCompound().setInteger("size", 0); 
    	}
		return stack.getTagCompound().getInteger("level");
	} 
	public void setlevel(ItemStack itemStack, int Value){
   		itemStack.getTagCompound().setInteger("level",  Value );
	} 
	public int getsize(ItemStack itemStack) {
		return itemStack.getTagCompound().getInteger("size");
	} 
	public void setsize(ItemStack itemStack, int Value){
   		itemStack.getTagCompound().setInteger("size",  Value );
	} 
	
}
