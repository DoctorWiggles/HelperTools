package helpertools.Com.Tools;

import helpertools.Com.Config;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.HelpTab;
import helpertools.Utils.InventoryUtil;
import helpertools.Utils.Texty;
import helpertools.Utils.Whitelist_Util;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
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

public class ItemStaffofTransformation extends ToolBase_Default
{
    public ItemStaffofTransformation(ToolMaterial material, String unlocalizedName)
    {
    	super (material);  
        setUnlocalizedName(unlocalizedName);
        this.MaxMode = 6;
    }
    
    protected static Random growrand = new Random();
    
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
    	list.add(TextFormatting.WHITE + "Swaps blocks in the world");
        list.add(TextFormatting.ITALIC + "While sneaking change mode");
        list.add(TextFormatting.ITALIC + "- Or select block to swap");
        list.add(" ");
        list.add(TextFormatting.ITALIC + "While enchanted with efficiency");
        list.add(TextFormatting.ITALIC + "- Press 'o' to toggle size");
        list.add(TextFormatting.ITALIC + "");
    	if (stack.hasTagCompound()){
    if(whatBlockString(stack) != "null" && whatModeString(stack)!= "null"){
    	list.add(whatBlockString(stack) + whatModeString(stack)+ " mode");
    }}}
    
    
    public String whatModeString(ItemStack itemStack){	  
    	String modestring= "Error";
    	int mode = getMode(itemStack);    	
    	switch(mode){
		case 2:	modestring = "Single";				 
			break;		
		case 4: modestring = "Wall";
			break;
		case 6: modestring = "Mass";
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
	
	private Block Gblock = Blocks.AIR;	
	private int Gmeta = 0;	
	
	
	
	/** Cycle through modes on swing and prevents backflow from onItemUse**/
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
		if (getOffMode(stack)== 0){ setOffMode(stack, 2); }
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
	
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing theface, float hitX, float hitY, float hitZ)
	{	
		
		if(player.isSneaking()){
			select_Block(stack, player, pos);
			setOffMode(stack, 4);
			return EnumActionResult.SUCCESS;
		}
		Set<BlockPos> positions = Mode_Function(stack, player, pos, theface, false);
		if(positions != null && !positions.isEmpty()){
			return EnumActionResult.SUCCESS;
		}
		failedsound(world, player);
		return EnumActionResult.FAIL;    	
    }
	
	//Change function depending on mode
	//Can perform differently for simulations	
	@Nullable
	public Set<BlockPos> Mode_Function(ItemStack stack, EntityPlayer player, BlockPos pos, EnumFacing theface, boolean simulation){
				
		if(!player.isSneaking() && creative_Check(stack, player, pos)){
			switch(getMode(stack)){
			
			case 2:				 
				return Single_Mode(stack, player, pos, simulation);	
			
			case 4: 
				return Wall_Mode(stack, player, pos, theface, simulation);
			
			case 6: 
				return Mass_Mode(stack, player, pos, theface, simulation);
			
			default: return null;
			}
						
		}
		return null;
	}
	
	
	//Block to select	
	public void select_Block(ItemStack stack, EntityPlayer player, BlockPos pos){
		World world = player.worldObj;
		setTBlock(stack, BlockStateHelper.returnID(world, pos)); 
		setTMeta(stack, BlockStateHelper.getMetafromState(world, pos)); 	

		failedsound(world, player);

		setOffMode(stack, 4);
	}
	
	//Check to modify black listed blocks
	public boolean creative_Check(ItemStack stack, EntityPlayer player, BlockPos pos){		
		World world = player.worldObj;
		if ((returnTBlock_FromState(stack)) != Blocks.AIR){
			
			if(!player.capabilities.isCreativeMode &&
					BlockStateHelper.getBlockfromState(world, pos) != Blocks.BEDROCK
					&& BlockStateHelper.getBlockfromState(world, pos) != Blocks.AIR
					|| player.capabilities.isCreativeMode
					&& BlockStateHelper.getBlockfromState(world, pos) != Blocks.AIR){
				
				return true;
			}
		}    			
		return false;		
	}
	
	
	//Area to place blocks
	public Set<BlockPos> Single_Mode(ItemStack stack, EntityPlayer player, BlockPos pos, boolean simulation){
		World world = player.worldObj;
		Set<BlockPos> positions = new HashSet<BlockPos>();
		
  		if (BlockStateHelper.getBlockfromState(world, pos) != (returnTBlock_FromState(stack))
          		|| BlockStateHelper.getMetafromState(world, pos) != (returnTMeta(stack))
                   && BlockStateHelper.getBlockfromState(world, pos) == (returnTBlock_FromState(stack)))
  		{
  			if(inventory_Check(stack, player, simulation)){
  				  positions.add(pos);  
  				  if(!simulation)TRANSFORM(stack, player, world, pos);
  			}
  		}  		
  		
  		
  		failedsound(world, player);
		return positions;
	}
	//Area to place blocks
	public Set<BlockPos> Wall_Mode(ItemStack stack, EntityPlayer player, BlockPos pos, EnumFacing theface, boolean simulation){
		World world = player.worldObj;
		Set<BlockPos> positions = new HashSet<BlockPos>();
		int wall = (getToolLevel(stack)+ 2); 
		int x1 = pos.getX();
		int y1 = pos.getY();
		int z1 = pos.getZ();   
		
		Gblock = BlockStateHelper.getBlockfromState(world, pos);
		Gmeta = BlockStateHelper.getMetafromState(world, pos); 
		
		//Some sort of spaghetti monster code
		for (int l = 0; l < wall; ++l)
		{   
			for (int ml = 0; ml < wall; ++ml)
			{ 
				for (int sl = 0; sl < 4; ++sl)
				{ 

					int sectA =1;
					int sectB =1;
					switch(sl){
					case 0:
						break;
					case 1: sectA= -1;       						
						break;        							
					case 2:	sectA= -1;
							sectB= -1;
						break;
					case 3:	sectB= -1;
						break;
					}
					int x3 = 0;
					int y3 = 0;
					int z3 = 0;
					//////////////////////////////////////
					switch(theface){
					case DOWN:	z3 = z3 - l*sectA; 
								x3 = x3 - ml*sectB;        	    			 		
								break;
					case UP:	z3 = z3 - l*sectA; 
								x3 = x3 - ml*sectB;
								break;
					case NORTH:	y3 = y3 - l*sectA; 
								x3 = x3 - ml*sectB; 
								break;
					case SOUTH:	y3 = y3 - l*sectA; 
								x3 = x3 - ml*sectB;
								break;
					case WEST:   y3 = y3 - l*sectA; 
								z3 = z3 - ml*sectB;			 
								break;
					case EAST:	 y3 = y3 - l*sectA; 
					z3 = z3 - ml*sectB;	
								break;
					default: 
					}
					int x2 = x1 + x3;
					int y2 = y1 + y3;
					int z2 = z1 + z3;
					BlockPos pos2 = new BlockPos(x2, y2, z2);
					Block Compare_Block = BlockStateHelper.getBlockfromState(world, pos2);
					int Compare_Meta = BlockStateHelper.getMetafromState(world, pos2);
					
					//Whitelist Placement
					if (Compare_Block != (returnTBlock_FromState(stack))
							&& Compare_Block == Gblock
							&& Compare_Meta == Gmeta
							|| Compare_Meta != (returnTMeta(stack))
							&& Compare_Block == (returnTBlock_FromState(stack))
							&& Compare_Block == Gblock
							&& Compare_Meta == Gmeta)
					{
						if(inventory_Check(stack, player, simulation)){
							positions.add(pos2);  
							if(!simulation)TRANSFORM(stack, player, world, pos2);
						} 
					}

				}
			}
		}
		failedsound(world, player);
		return positions;
	}
	//Area to place blocks
	public Set<BlockPos> Mass_Mode(ItemStack stack, EntityPlayer player, BlockPos pos, EnumFacing theface, boolean simulation){
		World world = player.worldObj;
		Set<BlockPos> positions = new HashSet<BlockPos>();
		int mass = (getToolLevel(stack)*2+ 3);
		int x1 = pos.getX();
		int y1 = pos.getY();
		int z1 = pos.getZ();  
		
		Gblock = BlockStateHelper.getBlockfromState(world, pos);
		Gmeta = BlockStateHelper.getMetafromState(world, pos); 



		for (int xl = 0; xl < mass; ++xl)
		{   
			for (int yl = 0; yl < mass; ++yl)
			{ 
				for (int zl = 0; zl < mass; ++zl)
				{    						
					int offy = (mass-1)/2;
					int x3 = xl - offy;
					int y3 = yl - offy;
					int z3 = zl - offy;
					//////////////////////////////////////
					int x2 = x1 + x3;
					int y2 = y1 + y3;
					int z2 = z1 + z3;

					BlockPos pos2 = new BlockPos(x2, y2, z2);
					Block Compare_Block = BlockStateHelper.getBlockfromState(world, pos2);
					int Compare_Meta = BlockStateHelper.getMetafromState(world, pos2);
					//Whitelist Placement

					if (Compare_Block != (returnTBlock_FromState(stack))
							&& Compare_Block == Gblock
							&& Compare_Meta == Gmeta
							|| Compare_Meta != (returnTMeta(stack))
							&& Compare_Block == (returnTBlock_FromState(stack))
							&& Compare_Block == Gblock
							&& Compare_Meta == Gmeta)
					{
						if(inventory_Check(stack, player, simulation)){
							positions.add(pos2);  
							if(!simulation)TRANSFORM(stack, player, world, pos2);
						} 
					}
				}
			}
		}		
		failedsound(world, player);
		return positions;
	}
	//Scan inventory and remove items
	public boolean inventory_Check(ItemStack stack, EntityPlayer player, boolean simulation){
		
		if(player.capabilities.isCreativeMode){return true;}
		
		ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock_FromState(stack)),0, returnTMeta(stack)); 
		Boolean whitelist_flag;
		whitelist_flag = Whitelist_Util.Block_Whitelist(returnTBlock_FromState(stack), player, returnTMeta(stack));
		if( player.inventory.hasItemStack(stacky) || whitelist_flag)
		{
			if(simulation){return true;}
			
			if(!whitelist_flag)InventoryUtil.consumeInventoryItemStack(stacky, player.inventory); 
			if(whitelist_flag){
				Whitelist_Util.Consume_Whitelist(stacky, player, returnTBlock_FromState(stack), returnTMeta(stack));
			}
            stack.damageItem(1, player);
            return true;
		}
		return false;
		
	}
	
	//Place the Block and apply FX
	public void TRANSFORM (ItemStack stack, EntityPlayer player, World world, BlockPos pos)
	{
		SoundType soundtype = returnTBlock_FromState(stack).getSoundType();
        Texty.Sound_Blocks(world, player, soundtype.getPlaceSound(), (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
		
        (world.getBlockState(pos).getBlock()).dropBlockAsItem(world, pos, world.getBlockState(pos), 0);      		
  		world.setBlockState(pos, BlockStateHelper.returnState(getTBlock(stack)), 02);    		
  		
  		int crackid = (getTBlock(stack));
		int crackmeta = (returnTMeta(stack));
		
		for (int pl = 0; pl < 50/(1*getToolLevel(stack)+1); ++pl)
		{
			float f = (this.growrand.nextFloat() - .2F) * 1.4F;
			float f1 = (this.growrand .nextFloat() - .2F) * 1.4F;
			float f2 = (this.growrand .nextFloat() - .2F) * 1.4F;
			world.spawnParticle(EnumParticleTypes.BLOCK_CRACK,
					pos.getX()+f, pos.getY()+f1+.3, pos.getZ()+f2, 0, 0, 0, crackid,crackmeta );       	            		
		}
	}
	
}