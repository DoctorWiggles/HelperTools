package helpertools.Common.Tools;

import helpertools.Utils.BlockStateHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
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
    		
    		//String Messy = "No compound";
    		//ChatComponentTranslation chatmessy = new ChatComponentTranslation(TextFormatting.GRAY + Messy, new Object[0]);
			//((EntityPlayer) entity).addChatComponentMessage(chatmessy);
    		}
    	
    	/**
    	if (stack.hasTagCompound() && (Main.Randy.nextFloat() >= .96)) {
    		String Messy = "mode " + (getMode(stack) 
    				+ " ID " + (getTBlock(stack))
    				+ " Level " + getToolLevel(stack)
    				+ " Block " + BlockStateHelper.returnBlock_ID((getTBlock(stack)))
    				+ " Name " + this.returnTBlock_FromState(stack)
    				+ " meta " + getTMeta(stack));
    		ChatComponentTranslation chatmessy = new ChatComponentTranslation(TextFormatting.GRAY + Messy, new Object[0]);
			((EntityPlayer) entity).addChatComponentMessage(chatmessy);
    	}
    	**/
    	

   	

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
