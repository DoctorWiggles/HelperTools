package helpertools.Com.Blocks;

import helpertools.Com.ItemRegistry;
import helpertools.Utils.HelpTab;
import helpertools.Utils.InventoryUtil;
import helpertools.Utils.ModUtil;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Block_Lamp extends Block{

	protected static final AxisAlignedBB HitBox = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 1.0D, 0.8D);
	
	private final boolean isOn;
	
	public Block_Lamp(String unlocal, boolean isOn)
	{
		super(Material.GLASS);
		this.setUnlocalizedName(unlocal);
		this.setSoundType(SoundType.GLASS);		   
		this.setHardness(0.6F);
		setHarvestLevel("pickaxe",0);
		
		this.isOn = isOn;
        if (isOn)
        {
            this.setLightLevel(1.0F);
        }
        else{
        	this.setCreativeTab(HelpTab.HelperTools);    
        }
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {return HitBox;}	
	public boolean isFullCube(IBlockState state){return false;}	
	public boolean isOpaqueCube(IBlockState state){return false;}	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		return Item.getItemFromBlock(ItemRegistry.LampBlock);
	}

	//==================== Redstone Updates =====================================//
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn){
		world.scheduleUpdate(pos, this, 4);
	}

	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		//LampFunctions.redstone_toggle(world, state, pos);
		world.scheduleUpdate(pos, this, 4);
	}

	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
		LampFunctions.redstone_toggle(world, state, pos);
	}

	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		LampFunctions.power(world, pos, state, player, heldItem);
		return true;        
	}

	
	
//==================== functions =====================================//
	
public static class LampFunctions{
	
		
	public static IBlockState off = ItemRegistry.LampBlock.getDefaultState();
	public static IBlockState on = ItemRegistry.LampBlock_on.getDefaultState();
	public static IBlockState perm = ItemRegistry.LampBlock_perm.getDefaultState();
	
	
	public static void power(World world, BlockPos pos, IBlockState state, EntityPlayer player, @Nullable ItemStack stack){
		if(player.worldObj.isRemote){return;}
		if(player.capabilities.isCreativeMode){
			if(state == off){
				perm(world, pos); return;}
			if(state == on || state == perm){
				off(world, pos); return;}			
		}
		if(state == perm){
			Block torch = Blocks.REDSTONE_TORCH;
			player.dropItem(new ItemStack(torch, 1, 0), false);
			off(world, pos); return;
		}
		if (stack.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_TORCH) && state == off)
		{
			InventoryPlayer inv = player.inventory;
			InventoryUtil.consumeInventoryItemStack(stack, inv);
			perm(world, pos); return;
		}
	}
	
	public static void off(World world, BlockPos pos){
		ModUtil.Sound_Server(world, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF,  0.3F, 0.5F);
		world.setBlockState(pos, off, 123);		
	}
	
	public static void on(World world, BlockPos pos){
		ModUtil.Sound_Server(world, pos, SoundEvents.ENTITY_CHICKEN_EGG,  0.3F, 0.5F);
		world.setBlockState(pos, on, 123);		
	}
	
	public static void perm(World world, BlockPos pos){
		ModUtil.Sound_Server(world, pos, SoundEvents.ENTITY_CHICKEN_EGG,  0.3F, 0.5F);
		world.setBlockState(pos, perm, 123);		
	}
	
	public static void redstone_toggle(World world, IBlockState state, BlockPos pos){

		if (!world.isRemote)
		{
			if (state == off && world.isBlockPowered(pos))
			{
				on(world, pos);
			}
			else if (state == on && !world.isBlockPowered(pos))
			{
				off(world, pos);
			}
		}
	}
	
}
public static class LampBlock_Item extends ItemBlock{

		public LampBlock_Item(Block block) {
			super(block);
			
		}
		
		public LampBlock_Item setUnlocalizedName(String unlocal)
	    {
	        super.setUnlocalizedName(unlocal);
	        return this;
	    }
				 
		@Override
	    @SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	      {
	      list.add(TextFormatting.WHITE + "Redstone powered lamp"); 
	      list.add(TextFormatting.ITALIC + "Right click with a redstone torch");
	      list.add(TextFormatting.ITALIC + "To self power the lamp");
	      
	      }
		
	}
	
	
}
