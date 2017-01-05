package helpertools.Com.Blocks;


import helpertools.Com.ItemRegistry;
import helpertools.Utils.HelpTab;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Tile entity construction
 * Orange Tutorials 
 * 
 */


public class TranscriberBlock extends Block implements ITileEntityProvider
{
    public TranscriberBlock(String unlocalizedName){
    
        super(Material.CLAY);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(HelpTab.HelperTools); 
        this.setHardness(0.6F);
        setHarvestLevel("pickaxe",0);
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		return(ItemRegistry.TranscriberBlock_Item);
    }
    
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTranscriber();
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        super.breakBlock(world, pos, state);
        world.removeTileEntity(pos);
    }
    /**@TODO
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }
    **/
    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos,  int eventID, int eventParam) {
        super.eventReceived(state, worldIn, pos, eventID, eventParam);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }
    

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {

    	TileEntityTranscriber tile = (TileEntityTranscriber)world.getTileEntity(pos);

    	if (tile != null)
    	{
    		ItemStack itemstack = player.inventory.getCurrentItem();

    		if (itemstack != null)
    		{
    			return false;
    		}
    		if(!player.isSneaking()){

    			world.playSound(player, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
    			switch(side){
    			case DOWN:	(tile).offY = (tile).offY +1;
    						break;
    			case UP:	(tile).offY = (tile).offY -1;	    			 		
    						break;
    			case NORTH:(tile).offZ = (tile).offZ +1;
    						break;
    			case SOUTH:(tile).offZ = (tile).offZ -1;
    						break;
    			case WEST: (tile).offX = (tile).offX +1;			 
    						break;
    			case EAST:	(tile).offX = (tile).offX -1;	
    						break;
    			default: 
    			}


    		}
    		if(player.isSneaking()){
    			world.playSound(player, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);

    			switch(side){
    			case DOWN:	(tile).offY = (tile).offY -1;    	    			 		
    						break;
    			case UP:	(tile).offY = (tile).offY +1;	    			 		
    						break;
    			case NORTH: (tile).offZ = (tile).offZ -1;
    						break;
    			case SOUTH: (tile).offZ = (tile).offZ +1;
    						break;
    			case WEST:  (tile).offX = (tile).offX -1;			 
    						break;
    			case EAST:	(tile).offX = (tile).offX +1;	
    						break;
    			default: 
    			}



    			int maxb = 10;
    			//max bounds
    			if((tile).offX >= maxb){tile.offX = maxb;}

    			if((tile).offX <= -maxb){tile.offX = -maxb;}

    			if((tile).offY >= maxb){tile.offY = maxb;}

    			if((tile).offY <= -maxb){tile.offY = -maxb;}

    			if((tile).offZ >= maxb){tile.offZ = maxb;}

    			if((tile).offZ <= -maxb){tile.offZ = -maxb;}

    		}
    		//Forcing update packets to be sent
    		world.markAndNotifyBlock(pos, world.getChunkFromBlockCoords(pos), state, state, 012);
    		tile.markDirty();

    	}

    	return true;

    }
    
public static class TranscriberBlock_Item extends ItemBlock
    {
        public final Block blocky;

        public TranscriberBlock_Item(Block block)
        {	super(block);
            this.blocky = block;
        }

        public TranscriberBlock_Item setUnlocalizedName(String unlocal)
        {
            super.setUnlocalizedName(unlocal);
            return this;
        }
            
        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
          {
          par3List.add(TextFormatting.WHITE + "Creates a Transposer Guide"); 
          par3List.add(TextFormatting.ITALIC + "Right click sides with hands");
          par3List.add(TextFormatting.ITALIC + "- To move guide");
          par3List.add(TextFormatting.ITALIC + "Right click with Transposer");
          par3List.add(TextFormatting.ITALIC + "- To get or set at guide");
          
          }

    }
}