package helpertools.Com.Tools;

import helpertools.Com.Config;
import helpertools.Utils.BlockStateHelper;
import helpertools.Utils.Texty;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;


public class ToolBase_Patterns extends ToolBase{

	public ToolBase_Patterns(ToolMaterial material) {
		super(material);
	}
	
    /////////////////////////////////////////////////////////////////////
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isheld) {
    	
    	if (!stack.hasTagCompound()) {
    		stack.setTagCompound(new NBTTagCompound());    		
    		stack.getTagCompound().setInteger("TBlock", 0);
    		stack.getTagCompound().setInteger("TMeta", 0);
    		stack.getTagCompound().setInteger("mode", 2);    		
    		stack.getTagCompound().setInteger("ToolLevel", 0);
    		stack.getTagCompound().setInteger("OffMode", 0); 
    		stack.getTagCompound().setInteger("Corner", 0); 
    		
    		}
    	
    }
    

    //=====================================================================//
  	public int getMode(ItemStack itemStack) {
		return itemStack.getTagCompound().getInteger("mode");

	}        
      
      public void setMode(ItemStack itemStack, int Value)
   	{
   		itemStack.getTagCompound().setInteger("mode",  Value );
   		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
   	} 
      
    //=====================================================================//
      
    
    public int getTBlock(ItemStack itemStack, int Nbtcount)
	{
		return itemStack.getTagCompound().getInteger("TBlock"+ Nbtcount);
		
	}
	public void setTBlock(ItemStack itemStack, int Value, int Nbtcount)
	{
		itemStack.getTagCompound().setInteger("TBlock"+ Nbtcount,  Value );
		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	} 
	//=====================================================================//
	public int getTMeta(ItemStack itemStack, int Nbtcount)
	{
		return itemStack.getTagCompound().getInteger("TMeta"+ Nbtcount);
		
	}
	public void setTMeta(ItemStack itemStack, int Value, int Nbtcount)
	{
		itemStack.getTagCompound().setInteger("TMeta"+ Nbtcount,  Value );
		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	} 
	
	
	//=====================================================================//
	public int returnTMeta(ItemStack thestaff, int Nbtcount)
    {
		return getTMeta(thestaff, Nbtcount);		
    }
	
	@Deprecated
	public Block returnTBlock(ItemStack thestaff, int Nbtcount)
	{
		return Block.getBlockById(getTBlock(thestaff, Nbtcount));
	}
	
	public Block returnTBlock_FromState(ItemStack stack, int Nbtcount)
	{		
		Block Blocky = BlockStateHelper.returnBlock(getTBlock(stack, Nbtcount));	
		
		return Blocky;
	}
	
	

	////////////////////////
	/** Rotation Counter**/
	public int getCorner(ItemStack itemStack) {
		return itemStack.getTagCompound().getInteger("Corner");

	}		
	public void setCorner(ItemStack itemStack, int Value) {
		itemStack.getTagCompound().setInteger("Corner", Value);			
	}
	
	public void rotateCorner(ItemStack stack, EntityPlayer player){
		int current = getCorner(stack);
		World world = player.worldObj;
		if(!player.isSneaking()){
			if(current+1 == 8){setCorner(stack, 4);
			Texty.Sound_Server(world, player, SoundEvents.BLOCK_LAVA_EXTINGUISH, (float)(1), (float)(1.3));
			return;}
			if(current+1 == 4){setCorner(stack, 0);
			Texty.Sound_Server(world, player, SoundEvents.BLOCK_LAVA_EXTINGUISH, (float)(1), (float)(1.3));
			return;}
			setCorner(stack, current+1);
			Texty.Sound_Server(world, player, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, (float)(.8), (float)( itemRand.nextFloat()*.75+.2));
			return;
		}
		if(player.isSneaking()){
			if(Config.ToolModeMesseges){
				Texty.print(player, TextFormatting.GRAY +"Flipping Pattern");}
			if(current+4 >= 8){
				setCorner(stack, current-4);
				Texty.Sound_Server(world, player, SoundEvents.BLOCK_LAVA_EXTINGUISH, (float)(1), (float)(1.3));
				return;
			}
			setCorner(stack, current+4);
			Texty.Sound_Server(world, player, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, (float)(.8), (float)( itemRand.nextFloat()*.75+.2));
			return;
		}
	}
	
    
	/** Offmode here prevents getblock from double dipping into switch mode code because i suck**/
	// ///////////////////////////////////////////////////////////
		public int getOffMode(ItemStack stack) {
			
			return stack.getTagCompound().getInteger("OffMode");

		}		
		public void setOffMode(ItemStack stack, int Value) {			
			stack.getTagCompound().setInteger("OffMode", Value);
		}
    
    //////////////////////////////////////////////////////////////    
   
    
		
}
