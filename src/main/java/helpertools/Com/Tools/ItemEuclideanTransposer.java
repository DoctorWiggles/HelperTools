package helpertools.Com.Tools;

import helpertools.Main;
import helpertools.Com.Config;
import helpertools.Com.ItemRegistry;
import helpertools.Com.Blocks.TileEntityTranscriber;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import helpertools.Utils.InventoryUtil;
import helpertools.Utils.Texty;
import helpertools.Utils.Whitelist_Util;

import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemEuclideanTransposer extends ToolBase_Patterns
{
    public ItemEuclideanTransposer(ToolMaterial material, String unlocalizedName)
    {
    	super (material); 
	    setUnlocalizedName(unlocalizedName);
	    this.MaxMode = 4;
    }
    protected static Random growrand = new Random();
    
    
    //flavor text
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
    	list.add(TextFormatting.WHITE + "Sets blocks in a 5^cube pattern");
    	list.add(TextFormatting.ITALIC + "Use while sneaking for a pattern");
    	list.add(" ");
    	list.add(TextFormatting.ITALIC + "Can also be used with");
    	list.add(TextFormatting.ITALIC + "- Transcriber Block");
    	if (stack.hasTagCompound()){
    		list.add(whatModeString(stack)+ " mode");
    		list.add(whatCornerString(stack));
    	}
    }
      
	/** Cycle through modes on swing and prevents backflow from onItemUse**/
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
		if (getOffMode(stack)== 0){ setOffMode(stack, 2); return false;}
		if (entityLiving.isSneaking()&& getOffMode(stack)== 2)
    	{ 	
			ModeSound(entityLiving, stack);
			if (!entityLiving.worldObj.isRemote) {
				
				nextMode(stack);
				ModeText(entityLiving, stack);
				return true;
			}			
    	}
		if (getOffMode(stack)== 4){ setOffMode(stack, 2); }
		return false;
    }
	
	
	public String whatCornerString(ItemStack itemStack){
    	String corner= "Default", flip = "";    	
    	int value = getCorner(itemStack);   
    	
    	if(value > 3){
    		flip = "flipped & ";
    		value = value-4;
    		}
    	switch(value){
    	case 1: corner = "turned 90*";
    	break;
    	case 2: corner = "turned 180*";
    	break;
    	case 3: corner = "turned 270*";
    	break;    	
    	}
    	return "Pattern is "+flip+corner;
    	
    };
	
	public String whatModeString(ItemStack itemStack){
    	String modestring= "Error";
    	int mode = getMode(itemStack);    	
    	switch(mode){
		case 2:	modestring = "Flush";				 
			break;		
		case 4: modestring = "Submerged -1";
			break;
		default: modestring = "Error";
			break;
		}
    	return modestring;
    };
    
	
	public void ModeText(EntityLivingBase living, ItemStack itemStack){
		int mode = getMode(itemStack);
		if(Config.ToolModeMesseges){
			String Messy = whatModeString(itemStack) + " Mode";
			Texty.print(living, TextFormatting.GRAY + Messy);
		    }
	}
	
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing theface, float hitX, float hitY, float hitZ)
	{	
		changed = false;
		setOffMode(stack, 0);
		
		//if(world.isRemote)return EnumActionResult.FAIL;
		if(!player.isSneaking()){
			pos = apply_Offset(stack, player, world, pos, theface, false);
			place_pattern(stack, player, world, pos, theface);
			if(changed){
				player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.2F, .5F+Main.Randy.nextFloat());
				player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.7F, .5F+Main.Randy.nextFloat());
				return EnumActionResult.SUCCESS;
			}
		}
		if(player.isSneaking()){
			pos = apply_Offset(stack, player, world, pos, theface, true);
			create_pattern(stack, player, world, pos, theface);
			return EnumActionResult.SUCCESS;
		}
		player.playSound(SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, .4F, itemRand.nextFloat() * 0.4F + 0.5F);
		return EnumActionResult.FAIL;
	}


	
	
	
	public void place_pattern(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing theface ){
		int NBT = 0;
		
		int c = getCorner(stack);
		
		//Tried condensing this with flags etc but could figure out how to make it work
		// So here goes a wall of what might as well be >if statements
		switch(getCorner(stack)){
		case 0:	
			for(int X = 0; X < 5; ++X){ for(int Y = 0; Y < 5; ++Y){ for(int Z = 0; Z < 5; ++Z){	
				
				place_block(world, player, pos, X, Y, Z, stack, NBT); NBT++;}}}break;			
			
		case 1:	
			for(int Z = 0; Z > -5; --Z){ for(int Y = 0; Y < 5; ++Y){ for(int X = 0; X < 5; ++X){
				
				place_block(world, player, pos, X, Y, Z, stack, NBT); NBT++;}}}break;	
			
		case 2: 
			for(int X = 0; X > -5; --X){ for(int Y = 0; Y < 5; ++Y){ for(int Z = 0; Z > -5; --Z){
				
				place_block(world, player, pos, X, Y, Z, stack, NBT); NBT++;}}}break;	
			
		case 3: 
			for(int Z = 0; Z < 5; ++Z){ for(int Y = 0; Y < 5; ++Y){ for(int X = 0; X > -5; --X){
				
				place_block(world, player, pos, X, Y, Z, stack, NBT); NBT++;}}}break;		
			
		case 4:	
			for(int X = 0; X < 5; ++X){ for(int Y = 0; Y > -5; --Y){ for(int Z = 0; Z < 5; ++Z){	
				
				place_block(world, player, pos, X, Y, Z, stack, NBT); NBT++;}}}break;		
			
		case 5:	
			for(int Z = 0; Z > -5; --Z){ for(int Y = 0; Y > -5; --Y){ for(int X = 0; X < 5; ++X){
				
				place_block(world, player, pos, X, Y, Z, stack, NBT); NBT++;}}}break;		
			
		case 6: 
			for(int X = 0; X > -5; --X){ for(int Y = 0; Y > -5; --Y){ for(int Z = 0; Z > -5; --Z){
				
				place_block(world, player, pos, X, Y, Z, stack, NBT); NBT++;}}}break;	
			
		case 7: 
			for(int Z = 0; Z < 5; ++Z){ for(int Y = 0; Y > -5; --Y){ for(int X = 0; X > -5; --X){
				
				place_block(world, player, pos, X, Y, Z, stack, NBT); NBT++;}}}break;		
			
		default:
		}
		
		

	}
	
	public void create_pattern(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing theface ){
				
		int NBT = 0;
		for(int X = 0; X < 5; ++X){
			for(int Y = 0; Y < 5; ++Y){
				for(int Z = 0; Z < 5; ++Z){
					
					BlockPos pos2 = pos.add(X, Y, Z);
					setTBlock(stack, BlockStateHelper.returnID(world, pos2), NBT); 
		    		setTMeta(stack, BlockStateHelper.getMetafromState(world, pos2), NBT);
		    		NBT++;
					
				}
			}
		}
		player.playSound(SoundEvents.ENTITY_GHAST_SHOOT, 1.5F, .2F+Main.Randy.nextFloat()/4);
		if(!player.worldObj.isRemote){
			Texty.print((EntityLivingBase)player, TextFormatting.GRAY + "Pattern Saved");
		}

	}
	
	
	public BlockPos apply_Offset(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing theface, boolean pattern){
		BlockPos pos2 = pos.add(-2, 0, -2);
		
		if(!pattern){
		switch(getCorner(stack)){
		case 0:	
			break;
		case 1:	pos2 = pos2.add(0,0,+4);
			break;
		case 2:	pos2 = pos2.add(+4,0,+4);
			break;
		case 3:	pos2 = pos2.add(+4,0,0);
			break;
		case 4:	pos2 = pos2.add(0,+4,0);
			break;
		case 5:	pos2 = pos2.add(0,+4,+4);
			break;
		case 6:	pos2 = pos2.add(+4,+4,+4);
			break;
		case 7:	pos2 = pos2.add(+4,+4,0);
			break;
		}
		}
		
		if(world.getBlockState(pos).getBlock() == ItemRegistry.transcriberBlock){
			TileEntityTranscriber tile = (TileEntityTranscriber)world.getTileEntity(pos);
			if (tile != null)
            {
				pos2 = pos2.add(tile.offX, tile.offY, tile.offZ);
				return pos2;
            }
		}
		
		if(pattern)return pos2;
		
		switch(theface){
		case DOWN:	pos2 = pos2.add(0, -5, 0);					
			break;
		case UP:	pos2 = pos2.add(0, +1, 0);	
					if(getMode(stack) == 4){
						pos2 = pos2.add(0, -1, 0);	
					}
			break;
		case NORTH: pos2 = pos2.add(0, -2, -3);	
			break;
		case SOUTH: pos2 = pos2.add(0, -2, +3);	
			break;
		case WEST: 	pos2 = pos2.add(-3, -2, 0);	
			break;
		case EAST:	pos2 = pos2.add(+3, -2, 0);
			break;
		default:
		}
		
		
		return pos2;
	}
	
	
	//Scan inventory and remove items
	public boolean inventory_Check(ItemStack stack, EntityPlayer player, int NBT, boolean simulation){

		if(player.capabilities.isCreativeMode){return true;}

		ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock_FromState(stack, NBT)),0, returnTMeta(stack, NBT)); 
		Boolean whitelist_flag;
		whitelist_flag = Whitelist_Util.Block_Whitelist(returnTBlock_FromState(stack, NBT), player, returnTMeta(stack, NBT));
		if( player.inventory.hasItemStack(stacky) || whitelist_flag)
		{
			if(simulation){return true;}

			if(!whitelist_flag)InventoryUtil.consumeInventoryItemStack(stacky, player.inventory); 
			if(whitelist_flag){
				Whitelist_Util.Consume_Whitelist(stacky, player, returnTBlock_FromState(stack, NBT), returnTMeta(stack, NBT));
			}
			stack.damageItem(1, player);
			return true;
		}
		return false;

	}
	public boolean changed = false;
	
	public void place_block(World world,EntityPlayer player, BlockPos pos, int X, int Y, int Z, ItemStack stack, int NBT){

		BlockPos pos2 = pos.add(X, Y, Z);
		IBlockState state = world.getBlockState(pos2);

		if (returnTBlock_FromState(stack, NBT) == Blocks.AIR){return;}
		if(!(Texty.isValid(state, world, pos2))){return;}
		if(!(inventory_Check(stack, player, NBT, false))){return;}
		if (world.getBlockState(pos).getMaterial() == Material.VINE
				|| world.getBlockState(pos).getMaterial() == Material.PLANTS
				|| world.getBlockState(pos).getBlock() == Blocks.SNOW_LAYER) 
		{

			(world.getBlockState(pos).getBlock()).dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
		}  
		
		
		world.setBlockState(pos2, BlockStateHelper.returnState(getTBlock(stack, NBT)), 02);
		particle_FX(world, pos2);
		changed = true;

	}
	
	public void particle_FX(World world, BlockPos pos){
		
		int X = pos.getX(), Y = pos.getY(), Z = pos.getZ();		
		for (int lp = 0; lp < 32; ++lp)
        {
            double d6 = (double)lp / ((double)32 - 1.0D);
            
            float f = (this.growrand.nextFloat() - .5F) * 1.4F;
            float f1 = (this.growrand .nextFloat() - .5F) * 1.4F;
            float f2 = (this.growrand .nextFloat() - .5F) * 1.4F;
            
            float p = (this.growrand.nextFloat()) ;
            float p1 = (this.growrand .nextFloat() ) ;
            float p2 = (this.growrand .nextFloat() ) ;
            world.spawnParticle(EnumParticleTypes.PORTAL, X +p+.1, Y+.6+p1, Z +p2+.1, f, f1, f2);
        }
	}

	@SuppressWarnings("null")
	public Set<IBlockState> Blockmap (ItemStack stack){
		
		Set<IBlockState> BlockMap = null;
		
		for(int nbt = 0; nbt < 125; ++nbt){
		BlockMap.add(BlockStateHelper.returnState(getTBlock(stack, nbt)));
		}
		return BlockMap;
	}
    
}