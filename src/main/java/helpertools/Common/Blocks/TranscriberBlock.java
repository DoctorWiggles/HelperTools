package helpertools.Common.Blocks;


import helpertools.Common.ItemRegistry;
import helpertools.Utils.HelpTab;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Tile entity construction
 * Orange Tutorials 
 * 
 */


public class TranscriberBlock extends Block implements ITileEntityProvider
{
    public TranscriberBlock(String unlocalizedName)
    {
        super(Material.CLAY);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(HelpTab.HelperTools); 
        this.setHardness(0.6F);
        setHarvestLevel("pickaxe",0);
        
        
    }
    
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		return Item.getItemFromBlock(ItemRegistry.transcriberBlock);
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

    @Override
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }
    
    
    //public boolean onBlockActivated(World worldIn, int x1, int y1, int z1, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	    	
    	int x1 = pos.getX();
    	int y1 = pos.getY();
    	int z1 = pos.getZ();
    	
    	BlockPos pos1= new BlockPos(x1, y1, z1);
    	
    		//tileentityhopper
    	TileEntityTranscriber tile = (TileEntityTranscriber)worldIn.getTileEntity(pos1);

            
            if (tile != null)
            {
            	ItemStack itemstack = player.inventory.getCurrentItem();
            	/**
                if (itemstack != null && itemstack.getItem() == Items.bowl && !player.capabilities.isCreativeMode)
                {
                	
                }
                **/
                if (itemstack != null)
                {
                	return false;
                }
                //p_149727_5_.func_146093_a(tileentityhopper);
                	//(tileentityhopper).atck7 = 6;
            	if(!player.isSneaking()){

            		worldIn.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, (double)z1 + 0.5D, "random.click", 0.3F,   0.5F);
            		
            	 switch(side){
	    		 //Bottom 0
	    		 case DOWN:	(tile).offY = (tile).offY +1;
	    			 break;
	    		//Top 1
	    		 case UP:	(tile).offY = (tile).offY -1;	    			 		
	    			 break;
	    		//North 2
	    		 case NORTH:(tile).offZ = (tile).offZ +1;
	    			 break;
	    		//South 3
	    		 case SOUTH:(tile).offZ = (tile).offZ -1;
					break;
	    		//West 4
	    		 case WEST: (tile).offX = (tile).offX +1;			 
	    			 break;
	    		//East 5
	    		 case EAST:	(tile).offX = (tile).offX -1;	
	    			 break;
	    			 default: 
	    		 }
                
                  
            	}
            	
            	//on off?
            	if(player.isSneaking()){
            		worldIn.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, (double)z1 + 0.5D, "random.click", 0.3F,   0.6F);
            		//ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation((tile).offX +"X " + (tile).offY +"Y " + (tile).offZ +"Z ", new Object[0]);
            	   	// player.addChatComponentMessage(chatcomponenttranslation); 
            		
            	   //bottom / top
            		/**
            		 if (side == 0 && tile.offY++ <= maxb){
                      	(tile).offY = (tile).offY -1;
                      }
            		 **/
            		switch(side){
   	    		 //Bottom 0
   	    		 case DOWN:	(tile).offY = (tile).offY -1;    	    			 		
   	    			 break;
   	    		//Top 1
   	    		 case UP:	(tile).offY = (tile).offY +1;	    			 		
   	    			 break;
   	    		//North 2
   	    		 case NORTH:(tile).offZ = (tile).offZ -1;
   	    			 break;
   	    		//South 3
   	    		 case SOUTH:(tile).offZ = (tile).offZ +1;
   					break;
   	    		//West 4
   	    		 case WEST: (tile).offX = (tile).offX -1;			 
   	    			 break;
   	    		//East 5
   	    		 case EAST:	(tile).offX = (tile).offX +1;	
   	    			 break;
   	    			 default: 
   	    		 }
            	
            	
            	
            	int maxb = 10;
                //max bounds
                if((tile).offX >= maxb){
                	tile.offX = maxb;
                }
                if((tile).offX <= -maxb){
                	tile.offX = -maxb;
                }
                
                if((tile).offY >= maxb){
                	tile.offY = maxb;
                }
                if((tile).offY <= -maxb){
                	tile.offY = -maxb;
                }
                
                if((tile).offZ >= maxb){
                	tile.offZ = maxb;
                }
                if((tile).offZ <= -maxb){
                	tile.offZ = -maxb;
                }
                
            }
            }

            return true;
        
    }
}