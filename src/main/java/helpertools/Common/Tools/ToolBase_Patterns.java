package helpertools.Common.Tools;

import helpertools.Main;
import helpertools.Common.ConfigurationFactory;
import helpertools.Utils.BlockStateHelper;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;


public class ToolBase_Patterns extends ItemSpade{

	public ToolBase_Patterns(ToolMaterial material) {
		super(material);
		// TODO Auto-generated constructor stub
	}
	

	//Generic tool stuff
	public boolean onBlockDestroyed(ItemStack stack, World world, Block theblock, BlockPos pos1, EntityLivingBase entity)
    {
		
        if ((double)theblock.getBlockHardness(world, pos1) != 0.0D)
        {
            stack.damageItem(1, entity);
        }

        return true;
    }
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2)
    {
		stack.damageItem(2, entity2);
        return true;
    }
	

    public boolean isMetadataSpecific(ItemStack itemStack)
 	{
 		return false;
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
    		//ChatComponentTranslation chatmessy = new ChatComponentTranslation(EnumChatFormatting.GRAY + Messy, new Object[0]);
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
    		ChatComponentTranslation chatmessy = new ChatComponentTranslation(EnumChatFormatting.GRAY + Messy, new Object[0]);
			((EntityPlayer) entity).addChatComponentMessage(chatmessy);
    	}
    	**/
    	

   	

    }
    
    
    public String whatModeString(ItemStack stack){	  
    	String modestring = "null";

    	if (getMode(stack) == 2){
    		modestring = "Pillar";
    	}
    	else if(getMode(stack) == 4){
    		modestring = "Wall";
    	}
    	else if(getMode(stack) == 6){
    		modestring = "Matching";
    	}
    	else{
    		modestring = "null";
    	}  
    	return modestring;
    };


		
	

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
		public int getOffMode(ItemStack itemStack) {
			
			return itemStack.getTagCompound().getInteger("OffMode");

		}		
		public void setOffMode(ItemStack itemStack, int Value) {
			
			itemStack.getTagCompound().setInteger("OffMode", Value);			
		}
    
    //////////////////////////////////////////////////////////////    
   
    
		
}
