package helpertools.Common.Items;

import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class Debug_States extends Item{
	
	public Debug_States( String unlocalizedName)
    {
    	super ();
        this.maxStackSize = 1;  
        setUnlocalizedName(unlocalizedName);
        setCreativeTab(HelpTab.HelperTools);
    }
	
	public boolean onItemUse(ItemStack thestaff, EntityPlayer theplayer, World world, BlockPos pos, EnumFacing theface, float fty1, float fty2, float fty3){
		
		 if (!world.isRemote){ /**
			 ChatComponentTranslation chatmessy5 = new ChatComponentTranslation(
						EnumChatFormatting.GRAY + "" + world, new Object[0]);
				theplayer.addChatComponentMessage(chatmessy5); **/
			 return false;}		
		String Messy = "State: " + world.getBlockState(pos);		
		String Messy2 = "Meta: " + BlockStateHelper.getMetafromState(world,pos);
		String Messy3 = "ID: " + BlockStateHelper.returnID(world,pos);
		String Messy4 = "Local : " + world.getBlockState(pos).getBlock().getLocalizedName();
		String Messy5 = "Unlocal : " + world.getBlockState(pos).getBlock().getUnlocalizedName();
		String Messy6 = "" + pos;
		
		ChatComponentTranslation chatmessy2 = new ChatComponentTranslation(
				EnumChatFormatting.WHITE+ Messy6 
				+EnumChatFormatting.YELLOW+ Messy4, new Object[0]);
		theplayer.addChatComponentMessage(chatmessy2);
		ChatComponentTranslation chatmessy1 = new ChatComponentTranslation(EnumChatFormatting.GRAY + Messy, new Object[0]);
		theplayer.addChatComponentMessage(chatmessy1);		
		
		ChatComponentTranslation chatmessy = new ChatComponentTranslation(
				//EnumChatFormatting.GRAY +
				//Messy + " "+
				EnumChatFormatting.GREEN +Messy2 + " "+
				EnumChatFormatting.BLUE +Messy3 + " "+
				EnumChatFormatting.RED + Messy5
				
				, new Object[0]);
		(theplayer).addChatComponentMessage(chatmessy);
		
		/**
		ChatComponentTranslation chatmessy2 = new ChatComponentTranslation(
				EnumChatFormatting.GRAY + "" + world, new Object[0]);
		theplayer.addChatComponentMessage(chatmessy2);
		**/
		
		
		
		
		return false;
	   
	    	
	    }

}
