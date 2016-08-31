package helpertools.Common.Items;

import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import helpertools.Utils.Texty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
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
		
		 if (!world.isRemote){ return false;}		
		
		String State = "State: " + world.getBlockState(pos);		
		String Meta = "Meta: " + BlockStateHelper.getMetafromState(world,pos);
		String ID = "ID: " + BlockStateHelper.returnID(world,pos);
		String Local = "Local : " + world.getBlockState(pos).getBlock().getLocalizedName();
		String Unlocal = "Unlocal : " + world.getBlockState(pos).getBlock().getUnlocalizedName();
		String posi = "" + pos;
		String blockid = "#: " + BlockStateHelper.returnBlock_ID(BlockStateHelper.returnID(world, pos));
		
		Texty.print(theplayer, TextFormatting.WHITE+ posi 
				+TextFormatting.YELLOW+ Local);
		
		Texty.print(theplayer, TextFormatting.GRAY + State);
		
		
		
		Texty.print(theplayer, 
				blockid+ " "+
				TextFormatting.GREEN +Meta + " "+
				TextFormatting.BLUE +ID + " "+
				TextFormatting.RED + Unlocal);
		
		return false;
	   
	    	
	    }
	
	 public void drop_blockItem(World world, BlockPos pos1){
		   
		   (world.getBlockState(pos1).getBlock()).dropBlockAsItem(world, pos1, world.getBlockState(pos1), 0);
	   }

}
