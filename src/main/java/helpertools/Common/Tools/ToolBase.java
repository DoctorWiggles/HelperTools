package helpertools.Common.Tools;

import helpertools.Common.Config;
import helpertools.Utils.HelpTab;
import helpertools.Utils.Texty;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
/** Master Base Class for tools **/
public class ToolBase extends ItemTool{

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.LEAVES, Blocks.LEAVES2, Blocks.WEB, Blocks.WOOL, 
			Blocks.TRAPPED_CHEST, Blocks.CHEST, Blocks.BOOKSHELF});
	
	int MaxMode;	
	protected ToolBase(ToolMaterial materialIn) {
		super((float) Config.Tool_Attack_Damage,
			(float) Config.Tool_Attack_Speed,
			materialIn, EFFECTIVE_ON);
		this.maxStackSize = 1; 
		setCreativeTab(HelpTab.HelperTools);
		this.MaxMode = 0;
		
	}
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if ((double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(1, entityLiving);
        }

        return true;
    }
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(2, attacker);
        return true;
    }
	
	public boolean isMetadataSpecific(ItemStack itemStack)
 	{
 		return false;
 	}
	
	public void onCreated(ItemStack stack, World world, EntityPlayer player) 
	 {
		
	 }
	
	 public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isheld) 
	 {
	    	if (!stack.hasTagCompound()) {
	    		stack.setTagCompound(new NBTTagCompound());
	    		stack.getTagCompound().setInteger("mode", 2);    
	    	}
	}
	 
	public enum mode {
		Poop,cats
	}
	
	
	/** return what mode is being used **/
	public int getMode(ItemStack itemStack) {
		return itemStack.getTagCompound().getInteger("mode");
	}  
	/** set what mode is to be used **/
	public void setMode(ItemStack itemStack, int Value){
	   		itemStack.getTagCompound().setInteger("mode",  Value );
	} 
	/** Cycle through the next available mode**/
	public void nextMode(ItemStack itemStack){
		int cap = this.MaxMode;
		int cur = getMode(itemStack);
		if (cur >= cap){
			setMode(itemStack, 2);
		}
		else if(cur == 0){
			setMode(itemStack, 2);
		}
		else{
			setMode(itemStack, cur+2);
		}
	}
		
	public static void failedsound(World world, EntityPlayer player)
	{
		world.playSound(player, player.getPosition(), 
				SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.NEUTRAL,
				.6F, itemRand.nextFloat() * 0.4F + 0.8F);
	}
	
	
	public void ModeSound(EntityLivingBase living, ItemStack itemStack){
		int mode = getMode(itemStack);
		switch(mode){
		case 0: living.playSound(SoundEvents.ENTITY_CHICKEN_EGG, .3F, 3.0F);
			break;
		case 2:	living.playSound(SoundEvents.ENTITY_CHICKEN_EGG,  3F, .3F);					 
			break;		
		case 4: living.playSound(SoundEvents.ENTITY_CHICKEN_EGG, .3F, 3.0F);
			break;
		case 6: living.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 3F, .3F);
		break;
		default: living.playSound(SoundEvents.ITEM_FLINTANDSTEEL_USE,
				.4F, itemRand.nextFloat() * 0.4F + 0.8F);
			break;
		}
	}
	/** @deprecated Don't use 
	public void ModeText(EntityLivingBase living, ItemStack itemStack){
		int mode = getMode(itemStack);
		if(ConfigurationFactory.ToolModeMesseges){
			String Messy = whatModeString(itemStack) + " Mode";
			Texty.print(living, TextFormatting.GRAY + Messy);
		    }
	}	
	public String whatModeString(ItemStack itemStack){	  
    	String modestring= "Error";
    	int mode = getMode(itemStack);    	
    	switch(mode){
		case 2:	modestring = "Pillar";				 
			break;		
		case 4: modestring = "Wall";
			break;
		case 6: modestring = "Matching";
		break;
		default: modestring = "Error";
			break;
		}
    	return modestring;
    };
	**/
	
}
