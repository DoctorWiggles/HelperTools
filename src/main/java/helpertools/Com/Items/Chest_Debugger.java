package helpertools.Com.Items;

import helpertools.Main;
import helpertools.Com.ModLootTables;
import helpertools.Utils.HelpTab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class Chest_Debugger extends Item{

	
	public Chest_Debugger(String unlocalizedName){
		super();
		this.setUnlocalizedName(unlocalizedName);
		setCreativeTab(HelpTab.HelperTools);
    
    }
	
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing theface, float hitX, float hitY, float hitZ)
	{
		
		//world.setBlockState(pos, Blocks.CHEST.getDefaultState());
		IBlockState iblockstate = Blocks.CHEST.getDefaultState();
		world.setBlockState(pos, Blocks.CHEST.correctFacing(world, pos, iblockstate), 2);
        TileEntity tileentity = world.getTileEntity(pos);

        if (tileentity instanceof TileEntityChest)   
        {
        	//ResourceLocation location = ModLootTables.Dungeon_Additive;
        	ResourceLocation location = LootTableList.CHESTS_SPAWN_BONUS_CHEST;
        	
        	
            ((TileEntityChest)tileentity).setLootTable(location, Main.Randy.nextLong());
        }
		
		
		return EnumActionResult.SUCCESS;
	}
	
}
