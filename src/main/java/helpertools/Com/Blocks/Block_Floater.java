package helpertools.Com.Blocks;

import helpertools.Main;
import helpertools.Com.ItemRegistry;
import helpertools.Com.Blocks.Block_Transcriber.TranscriberBlock_Item;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import helpertools.Utils.ModUtil;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Block_Floater extends Block{
	
	public Block_Floater(String unlocalizedName) {
        super(Material.CLOTH);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(HelpTab.HelperTools);
        this.setHardness(0.2F);
        setHarvestLevel("axe",0);
        this.setSoundType(SoundType.WOOD);
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {	//TODO uh... didn't realize there's been a dupe for balloons
    	//it's been like this for over a year and no one mentioned it
    	//well
    	//uh....
    	//FREE BALLOONS
    	//UNLIMITED VALUE
        return Item.getItemFromBlock(this);
    }
    
    
    
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	Block block = ItemRegistry.BalloonBlock;
    	IBlockState balloon = ItemRegistry.BalloonBlock.getDefaultState();
    	
    	BlockPos offset = new BlockPos(pos.up());
    	IBlockState blocky = world.getBlockState(offset); 
    	
    	if(valid_Whitelist(blocky, world, offset)){
    		world.setBlockState(offset, balloon, 123);
    	}
    	else{
    		ItemStack item = new ItemStack(block);
    		ModUtil.itemdrop(world, offset, item);
    	}
    	
    	
        return this.getStateFromMeta(meta);
    }
    
    public static boolean valid_Whitelist(IBlockState state, World world, BlockPos pos){
    	Material matt = state.getMaterial();

    	if(state.getBlock().isAir(state, world, pos)
    			|| state.getBlock() == Blocks.SNOW_LAYER
    			|| matt == Material.FIRE
    			|| matt == Material.WATER
    			|| matt == Material.LAVA
    			|| matt == Material.PLANTS
    			|| matt == Material.VINE){

    		return true;
    	}
    	return false;
    }
    
public static class FloaterBlock_Item extends ItemBlock
{
	public final Block blocky;

	public FloaterBlock_Item(Block block)
	{	super(block);
		this.blocky = block;
	}

	public FloaterBlock_Item setUnlocalizedName(String unlocal)
	{
		super.setUnlocalizedName(unlocal);
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(TextFormatting.ITALIC + "Can be placed in mid-air and water");
		list.add(TextFormatting.ITALIC + "Shift click to place below you");

	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
		if(player.isSneaking()){
			BlockPos pos = player.getPosition().down();
			IBlockState state = world.getBlockState(pos); 
			Place_Floater(state, world, pos);
			if(!player.capabilities.isCreativeMode && Place_Floater(state, world, pos)){
				--stack.stackSize;}
			
			return new ActionResult(EnumActionResult.SUCCESS, stack);		
		}

		if(!player.isSneaking()){ 
			RayTraceResult mouseOver = Minecraft.getMinecraft().objectMouseOver;			
			BlockPos pos = mouseOver.getBlockPos();
			IBlockState state = world.getBlockState(pos); 
			if(Place_Floater(state, world, pos)){
				if(!player.capabilities.isCreativeMode)
				--stack.stackSize;}
			
			return new ActionResult(EnumActionResult.SUCCESS, stack);
		}
		
            return new ActionResult(EnumActionResult.FAIL, stack);
        
    }
	

	public static boolean Place_Floater(IBlockState state, World world, BlockPos pos){
		IBlockState floater = ItemRegistry.FloaterBlock.getDefaultState();
		
		if (ModUtil.isValid(world, pos)){
			for (int pl = 0; pl < 200; ++pl)
			{
        		float f = (Main.Randy.nextFloat() - .2F) * 1.4F;
                float f1 = (Main.Randy.nextFloat() - .2F) * 1.4F;
                float f2 = (Main.Randy.nextFloat() - .2F) * 1.4F;        		
                world.spawnParticle(EnumParticleTypes.BLOCK_CRACK,
    					pos.getX()+f, pos.getY()+f1, pos.getZ()+f2, 
    					0, 0, 0,Block.getStateId(floater));	
    			}
					
			if(world.isRemote){return false;}
			
			world.setBlockState(pos, floater, 123);
			ModUtil.Sound_Server(world, pos, SoundEvents.BLOCK_STONE_PLACE, 1F, 1F);

			if(ModUtil.isValid(world, pos.up())){
				world.setBlockState(pos.up(), ItemRegistry.BalloonBlock.getDefaultState(), 123);    			
			}
			else{
				ModUtil.itemdrop(world, pos, ItemRegistry.BalloonBlock);
			}
			return true;
		}
		return false;
	}

}

}
