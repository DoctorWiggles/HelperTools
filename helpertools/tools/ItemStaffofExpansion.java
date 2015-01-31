package helpertools.tools;

import java.util.List;

import helpertools.HelpTab;
import helpertools.Helpertoolscore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkEvent;

public class ItemStaffofExpansion extends ItemSpade
{
    private static final String __OBFID = "CL_00000035";

  
   

    public ItemStaffofExpansion(ToolMaterial material)
    {
    	super (material);
        this.maxStackSize = 1;  
        setUnlocalizedName("staffofexpansion");
        //setCreativeTab(Helpertoolscore.HelperTools);
        setCreativeTab(HelpTab.HelperTools);
        setTextureName("helpertools:wand7");
        this.setMaxDamage(1428);
    }
    //flavor text
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    par3List.add(EnumChatFormatting.ITALIC + "sets blocks");
    }
    /*
    public String getItemStackDisplayName(ItemStack p_77653_1_)
    {
        return (EnumChatFormatting.GREEN + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(p_77653_1_) + ".name")).trim();
    }
    */
   
    /////////////////////////////////////////////////////////////
    public int getMode(ItemStack itemStack)
   	{
   		if(itemStack.stackTagCompound == null)
   		{
   			return 0;
   		}

   		return itemStack.stackTagCompound.getInteger("mode");
   		
   	}
      
      public boolean isMetadataSpecific(ItemStack itemStack)
   	{
   		return false;
   	}
      
      ///////////////////////////////////////////
      public void setMode(ItemStack itemStack, int Value)
   	{
   		if(itemStack.stackTagCompound == null)
   		{
   			itemStack.setTagCompound(new NBTTagCompound());
   		}

   		itemStack.stackTagCompound.setInteger("mode",  Value );
   		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
   	} 
   	///////////////////
      
    public int getTBlock(ItemStack itemStack)
	{
		if(itemStack.stackTagCompound == null)
		{
			return 0;
		}

		return itemStack.stackTagCompound.getInteger("TBlock");
		
	}
	public void setTBlock(ItemStack itemStack, int Value)
	{
		if(itemStack.stackTagCompound == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		itemStack.stackTagCompound.setInteger("TBlock",  Value );
		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	} 
	////////
	public int getTMeta(ItemStack itemStack)
	{
		if(itemStack.stackTagCompound == null)
		{
			return 0;
		}

		return itemStack.stackTagCompound.getInteger("TMeta");
		
	}
	public void setTMeta(ItemStack itemStack, int Value)
	{
		if(itemStack.stackTagCompound == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		itemStack.stackTagCompound.setInteger("TMeta",  Value );
		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	} 
	
	
	//////
	public int returnTMeta(ItemStack thestaff)
    {
		return getTMeta(thestaff);
		
    }
	
	public Block returnTBlock(ItemStack thestaff)
	{
		return Block.getBlockById(getTBlock(thestaff));
	}
	
    
	/** Offmode here prevents getblock from double dipping into switch mode code**/
	// ///////////////////////////////////////////////////////////
		public int getOffMode(ItemStack itemStack) {
			if (itemStack.stackTagCompound == null) {
				return 0;
			}

			return itemStack.stackTagCompound.getInteger("OffMode");

		}		
		public void setOffMode(ItemStack itemStack, int Value) {
			if (itemStack.stackTagCompound == null) {
				itemStack.setTagCompound(new NBTTagCompound());
			}

			itemStack.stackTagCompound.setInteger("OffMode", Value);			
		}
    
    //////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////
    
    
    
    
    
	//private int mode = 2;
	//declares mode integer
	
	//
	
	//
	//
	//(theblock.getBlock(i1, j1+2, k1)).setTickRandomly(false);
    //
	//Auto generated stuff
	//Allows modes to be seen and set outside of class
	//public int getMode() {
	//	return mode;
	//}
	//public void setMode(int mode) {
	//	this.mode = mode;
	//}


	//tileEntity.setFacing((short)change);
	//tileBlock.setFacing((short)theface);
	 
	
	//Generic tool stuff
	public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        if ((double)p_150894_3_.getBlockHardness(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D)
        {
            p_150894_1_.damageItem(1, p_150894_7_);
        }

        return true;
    }
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
        p_77644_1_.damageItem(2, p_77644_3_);
        return true;
    }
	//Custom mode changing code
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
		
		if (getMode(stack)== 0)
   		{
		   setMode(stack, 2);
   		}
		if (getOffMode(stack)== 0)
   		{
		   setOffMode(stack, 2);
   		}
		if (!entityLiving.worldObj.isRemote) {
		if (entityLiving.isSneaking()&& getOffMode(stack)== 2)
    	{ 
			if (getMode(stack) == 4)
			{
		   		//entityLiving.playSound("mob.chicken.plop", 3.0F, .3F);
		   		entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", 3F, .3F);
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Extend Mode", new Object[0]);
				((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
				setMode(stack,2);
				return true;
			}
			else if (getMode(stack) == 2)
			{			
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Flat Mode", new Object[0]);
				((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
			//entityLiving.playSound("mob.chicken.plop", .3F, 3.0F);}
			entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", .3F, 3.0F);
			setMode(stack,4);
    	}
			return true;
    	}
		}
		if (getOffMode(stack)== 4)
   		{
		   setOffMode(stack, 2);
   		}
		 return false;
    }
		
	
		
	

	
	//The guts of the placement code
	/** This is a huge mess **/
	
	//Basically It will go through checks and determine what action it should perform next
	/**During this, it looks for Which mode -> Which face of the block 
	 * -> Which blocks are legal -> If they are legal, place or swap
	 * -> And finally depending on which gamemode to remove durability and items**/
    public boolean onItemUse(ItemStack thestaff, EntityPlayer theplayer, World theblock, int i1, int j1, int k1, int theface, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	if (getMode(thestaff)== 0)
   		{
		   setMode(thestaff, 2);
   		}
    	//if (!theplayer.worldObj.isRemote) {
    	
    	//
    	//////////////////////////////////////////////////////////////////////////////////

		//Wall mode 4
		/** if this is true it performs this action **/
    	//Checks to make sure you're not sneaking
    	if (!theplayer.isSneaking()
    			&& getMode(thestaff) == 4)
    	{ 
    		//////////////////////////////////////
    		 if (theface == 0)
    	        {
    	            --j1;
    	            //BOTTOM FACE
    	            //W/E_T/B_N/S
    	            if (theblock.isAirBlock(i1-1, j1+1, k1)
    	            		|| theblock.getBlock(i1-1, j1+1, k1).getMaterial() == Material.lava 
    	            		|| theblock.getBlock(i1-1, j1+1, k1).getMaterial() == Material.water)
    	            {
    	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
    	        		{
    	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
    	            		 theblock.setBlock(i1-1, j1+1, k1, Blocks.cobblestone);
    	            		 theblock.setBlock(i1-1, j1+1, k1, Blocks.air);
    	            		theblock.setBlock(i1-1, j1+1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
    	            		if (!theplayer.capabilities.isCreativeMode){                	
    	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
    	                        thestaff.damageItem(1, theplayer);
    	                        }	
    	        		}
    	            
    	            }
    	            if (theblock.isAirBlock(i1+1, j1+1, k1)
    	            		|| theblock.getBlock(i1+1, j1+1, k1).getMaterial() == Material.lava 
    	            		|| theblock.getBlock(i1+1, j1+1, k1).getMaterial() == Material.water)
    	            {
    	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
    	        		{
    	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
    	            		 theblock.setBlock(i1+1, j1+1, k1, Blocks.cobblestone);
    	            		 theblock.setBlock(i1+1, j1+1, k1, Blocks.air);
    	            		theblock.setBlock(i1+1, j1+1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
    	            		if (!theplayer.capabilities.isCreativeMode){                	
    	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
    	                        thestaff.damageItem(1, theplayer);
    	                        }	
    	        		}
    	            
    	            }
    	          //W/E_T/B_N/S
    	            if (theblock.isAirBlock(i1, j1+1, k1-1)
    	            		|| theblock.getBlock(i1, j1+1, k1-1).getMaterial() == Material.lava 
    	            		|| theblock.getBlock(i1, j1+1, k1-1).getMaterial() == Material.water)
    	            {
    	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
    	        		{
    	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
    	            		 theblock.setBlock(i1, j1+1, k1-1, Blocks.cobblestone);
    	            		 theblock.setBlock(i1, j1+1, k1-1, Blocks.air);
    	            		theblock.setBlock(i1, j1+1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
    	            		if (!theplayer.capabilities.isCreativeMode){                	
    	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
    	                        thestaff.damageItem(1, theplayer);
    	                        }	
    	        		}
    	            
    	            }
    	            if (theblock.isAirBlock(i1-1, j1+1, k1-1)
    	            		|| theblock.getBlock(i1-1, j1+1, k1-1).getMaterial() == Material.lava 
    	            		|| theblock.getBlock(i1-1, j1+1, k1-1).getMaterial() == Material.water)
    	            {
    	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
    	        		{
    	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
    	            		 theblock.setBlock(i1-1, j1+1, k1-1, Blocks.cobblestone);
    	            		 theblock.setBlock(i1-1, j1+1, k1-1, Blocks.air);
    	            		theblock.setBlock(i1-1, j1+1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
    	            		if (!theplayer.capabilities.isCreativeMode){                	
    	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
    	                        thestaff.damageItem(1, theplayer);
    	                        }	
    	        		}
    	            
    	            }
    	            if (theblock.isAirBlock(i1+1, j1+1, k1-1)
    	            		|| theblock.getBlock(i1+1, j1+1, k1-1).getMaterial() == Material.lava 
    	            		|| theblock.getBlock(i1+1, j1+1, k1-1).getMaterial() == Material.water)
    	            {
    	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
    	        		{
    	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
    	            		 theblock.setBlock(i1+1, j1+1, k1-1, Blocks.cobblestone);
    	            		 theblock.setBlock(i1+1, j1+1, k1-1, Blocks.air);
    	            		theblock.setBlock(i1+1, j1+1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
    	            		if (!theplayer.capabilities.isCreativeMode){                	
    	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
    	                        thestaff.damageItem(1, theplayer);
    	                        }	
    	        		}
    	            
    	            }

      	          //W/E_T/B_N/S
      	            if (theblock.isAirBlock(i1, j1+1, k1+1)
      	            		|| theblock.getBlock(i1, j1+1, k1+1).getMaterial() == Material.lava 
      	            		|| theblock.getBlock(i1, j1+1, k1+1).getMaterial() == Material.water)
      	            {
      	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
      	        		{
      	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
      	            		 theblock.setBlock(i1, j1+1, k1+1, Blocks.cobblestone);
      	            		 theblock.setBlock(i1, j1+1, k1+1, Blocks.air);
      	            		theblock.setBlock(i1, j1+1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
      	            		if (!theplayer.capabilities.isCreativeMode){                	
      	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
      	                        thestaff.damageItem(1, theplayer);
      	                        }	
      	        		}
      	            
      	            }
      	            if (theblock.isAirBlock(i1-1, j1+1, k1+1)
      	            		|| theblock.getBlock(i1-1, j1+1, k1+1).getMaterial() == Material.lava 
      	            		|| theblock.getBlock(i1-1, j1+1, k1+1).getMaterial() == Material.water)
      	            {
      	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
      	        		{
      	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
      	            		 theblock.setBlock(i1-1, j1+1, k1+1, Blocks.cobblestone);
      	            		 theblock.setBlock(i1-1, j1+1, k1+1, Blocks.air);
      	            		theblock.setBlock(i1-1, j1+1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
      	            		if (!theplayer.capabilities.isCreativeMode){                	
      	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
      	                        thestaff.damageItem(1, theplayer);
      	                        }	
      	        		}
      	            
      	            }
      	            if (theblock.isAirBlock(i1+1, j1+1, k1+1)
      	            		|| theblock.getBlock(i1+1, j1+1, k1+1).getMaterial() == Material.lava 
      	            		|| theblock.getBlock(i1+1, j1+1, k1+1).getMaterial() == Material.water)
      	            {
      	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
      	        		{
      	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
      	            		 theblock.setBlock(i1+1, j1+1, k1+1, Blocks.cobblestone);
      	            		 theblock.setBlock(i1+1, j1+1, k1+1, Blocks.air);
      	            		theblock.setBlock(i1+1, j1+1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
      	            		if (!theplayer.capabilities.isCreativeMode){                	
      	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
      	                        thestaff.damageItem(1, theplayer);
      	                        }	
      	        		}
      	            
      	            }
      	            
    	            //////////////////
    	            {

    	        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
    	        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
    	            }
    	            //East/west axis, Vertical axis, North/south axis
    	            return true;
    	        }
   ////////////// 	/////////////////////	////////// /////////////////////////////////////////////////////////////
    		  if (theface == 1)
    	        {
    	            ++j1;
    	            //TOP FACE
 	            //W/E_T/B_N/S
 	            if (theblock.isAirBlock(i1-1, j1-1, k1)
 	            		|| theblock.getBlock(i1-1, j1-1, k1).getMaterial() == Material.lava 
 	            		|| theblock.getBlock(i1-1, j1-1, k1).getMaterial() == Material.water)
 	            {
 	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
 	        		{
 	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
 	            		 theblock.setBlock(i1-1, j1-1, k1, Blocks.cobblestone);
 	            		 theblock.setBlock(i1-1, j1-1, k1, Blocks.air);
 	            		theblock.setBlock(i1-1, j1-1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
 	            		if (!theplayer.capabilities.isCreativeMode){                	
 	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
 	                        thestaff.damageItem(1, theplayer);
 	                        }	
 	        		}
 	            
 	            }
 	            if (theblock.isAirBlock(i1+1, j1-1, k1)
 	            		|| theblock.getBlock(i1+1, j1-1, k1).getMaterial() == Material.lava 
 	            		|| theblock.getBlock(i1+1, j1-1, k1).getMaterial() == Material.water)
 	            {
 	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
 	        		{
 	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
 	            		 theblock.setBlock(i1+1, j1-1, k1, Blocks.cobblestone);
 	            		 theblock.setBlock(i1+1, j1-1, k1, Blocks.air);
 	            		theblock.setBlock(i1+1, j1-1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
 	            		if (!theplayer.capabilities.isCreativeMode){                	
 	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
 	                        thestaff.damageItem(1, theplayer);
 	                        }	
 	        		}
 	            
 	            }
 	          //W/E_T/B_N/S
 	            if (theblock.isAirBlock(i1, j1-1, k1-1)
 	            		|| theblock.getBlock(i1, j1-1, k1-1).getMaterial() == Material.lava 
 	            		|| theblock.getBlock(i1, j1-1, k1-1).getMaterial() == Material.water)
 	            {
 	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
 	        		{
 	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
 	            		 theblock.setBlock(i1, j1-1, k1-1, Blocks.cobblestone);
 	            		 theblock.setBlock(i1, j1-1, k1-1, Blocks.air);
 	            		theblock.setBlock(i1, j1-1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
 	            		if (!theplayer.capabilities.isCreativeMode){                	
 	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
 	                        thestaff.damageItem(1, theplayer);
 	                        }	
 	        		}
 	            
 	            }
 	            if (theblock.isAirBlock(i1-1, j1-1, k1-1)
 	            		|| theblock.getBlock(i1-1, j1-1, k1-1).getMaterial() == Material.lava 
 	            		|| theblock.getBlock(i1-1, j1-1, k1-1).getMaterial() == Material.water)
 	            {
 	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
 	        		{
 	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
 	            		 theblock.setBlock(i1-1, j1-1, k1-1, Blocks.cobblestone);
 	            		 theblock.setBlock(i1-1, j1-1, k1-1, Blocks.air);
 	            		theblock.setBlock(i1-1, j1-1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
 	            		if (!theplayer.capabilities.isCreativeMode){                	
 	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
 	                        thestaff.damageItem(1, theplayer);
 	                        }	
 	        		}
 	            
 	            }
 	            if (theblock.isAirBlock(i1+1, j1-1, k1-1)
 	            		|| theblock.getBlock(i1+1, j1-1, k1-1).getMaterial() == Material.lava 
 	            		|| theblock.getBlock(i1+1, j1-1, k1-1).getMaterial() == Material.water)
 	            {
 	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
 	        		{
 	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
 	            		 theblock.setBlock(i1+1, j1-1, k1-1, Blocks.cobblestone);
 	            		 theblock.setBlock(i1+1, j1-1, k1-1, Blocks.air);
 	            		theblock.setBlock(i1+1, j1-1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
 	            		if (!theplayer.capabilities.isCreativeMode){                	
 	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
 	                        thestaff.damageItem(1, theplayer);
 	                        }	
 	        		}
 	            
 	            }

   	          //W/E_T/B_N/S
   	            if (theblock.isAirBlock(i1, j1-1, k1+1)
   	            		|| theblock.getBlock(i1, j1-1, k1+1).getMaterial() == Material.lava 
   	            		|| theblock.getBlock(i1, j1-1, k1+1).getMaterial() == Material.water)
   	            {
   	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
   	        		{
   	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
   	            		 theblock.setBlock(i1, j1-1, k1+1, Blocks.cobblestone);
   	            		 theblock.setBlock(i1, j1-1, k1+1, Blocks.air);
   	            		theblock.setBlock(i1, j1-1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
   	            		if (!theplayer.capabilities.isCreativeMode){                	
   	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
   	                        thestaff.damageItem(1, theplayer);
   	                        }	
   	        		}
   	            
   	            }
   	            if (theblock.isAirBlock(i1-1, j1-1, k1+1)
   	            		|| theblock.getBlock(i1-1, j1-1, k1+1).getMaterial() == Material.lava 
   	            		|| theblock.getBlock(i1-1, j1-1, k1+1).getMaterial() == Material.water)
   	            {
   	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
   	        		{
   	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
   	            		 theblock.setBlock(i1-1, j1-1, k1+1, Blocks.cobblestone);
   	            		 theblock.setBlock(i1-1, j1-1, k1+1, Blocks.air);
   	            		theblock.setBlock(i1-1, j1-1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
   	            		if (!theplayer.capabilities.isCreativeMode){                	
   	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
   	                        thestaff.damageItem(1, theplayer);
   	                        }	
   	        		}
   	            
   	            }
   	            if (theblock.isAirBlock(i1+1, j1-1, k1+1)
   	            		|| theblock.getBlock(i1+1, j1-1, k1+1).getMaterial() == Material.lava 
   	            		|| theblock.getBlock(i1+1, j1-1, k1+1).getMaterial() == Material.water)
   	            {
   	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
   	        		{
   	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
   	            		 theblock.setBlock(i1+1, j1-1, k1+1, Blocks.cobblestone);
   	            		 theblock.setBlock(i1+1, j1-1, k1+1, Blocks.air);
   	            		theblock.setBlock(i1+1, j1-1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
   	            		if (!theplayer.capabilities.isCreativeMode){                	
   	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
   	                        thestaff.damageItem(1, theplayer);
   	                        }	
   	        		}
   	            
   	            }
   	            
 	            //////////////////
 	            {

 	        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
 	        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
 	            }
 	            //East/west axis, Vertical axis, North/south axis
 	            return true;
 	        }
   //////////////////////////////////////////////////////////////////////////////////// 		  	
    		  if (theface == 2)
    	        {
    	            --k1;
    	            //NORTH FACE
  	            //W/E_T/B_N/S
  	            if (theblock.isAirBlock(i1, j1+1, k1+1)
  	            		|| theblock.getBlock(i1, j1+1, k1+1).getMaterial() == Material.lava 
  	            		|| theblock.getBlock(i1, j1+1, k1+1).getMaterial() == Material.water)
  	            {
  	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
  	        		{
  	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
  	            		 theblock.setBlock(i1, j1+1, k1+1, Blocks.cobblestone);
  	            		 theblock.setBlock(i1, j1+1, k1+1, Blocks.air);
  	            		theblock.setBlock(i1, j1+1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
  	            		if (!theplayer.capabilities.isCreativeMode){                	
  	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
  	                        thestaff.damageItem(1, theplayer);
  	                        }	
  	        		}
  	            
  	            }
  	          if (theblock.isAirBlock(i1, j1-1, k1+1)
	            		|| theblock.getBlock(i1, j1-1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1, j1-1, k1+1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1, j1-1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1, j1-1, k1+1, Blocks.air);
	            		theblock.setBlock(i1, j1-1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
  	        //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1+1, j1+1, k1+1)
	            		|| theblock.getBlock(i1+1, j1+1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1+1, k1+1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1+1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1+1, k1+1, Blocks.air);
	            		theblock.setBlock(i1+1, j1+1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            if (theblock.isAirBlock(i1, j1+1, k1+1)
	            		|| theblock.getBlock(i1, j1+1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1, j1+1, k1+1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1, j1+1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1, j1+1, k1+1, Blocks.air);
	            		theblock.setBlock(i1, j1+1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            if (theblock.isAirBlock(i1-1, j1+1, k1+1)
	            		|| theblock.getBlock(i1-1, j1+1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1+1, k1+1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1+1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1+1, k1+1, Blocks.air);
	            		theblock.setBlock(i1-1, j1+1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1+1, j1, k1+1)
	            		|| theblock.getBlock(i1+1, j1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1, k1+1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1, k1+1, Blocks.air);
	            		theblock.setBlock(i1+1, j1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            if (theblock.isAirBlock(i1, j1, k1+1)
	            		|| theblock.getBlock(i1, j1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1, j1, k1+1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1, j1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1, j1, k1+1, Blocks.air);
	            		theblock.setBlock(i1, j1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            if (theblock.isAirBlock(i1-1, j1, k1+1)
	            		|| theblock.getBlock(i1-1, j1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1, k1+1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1, k1+1, Blocks.air);
	            		theblock.setBlock(i1-1, j1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }

	            //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1+1, j1-1, k1+1)
	            		|| theblock.getBlock(i1+1, j1-1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1-1, k1+1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1-1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1-1, k1+1, Blocks.air);
	            		theblock.setBlock(i1+1, j1-1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            if (theblock.isAirBlock(i1, j1-1, k1+1)
	            		|| theblock.getBlock(i1, j1-1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1, j1-1, k1+1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1, j1-1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1, j1-1, k1+1, Blocks.air);
	            		theblock.setBlock(i1, j1-1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            if (theblock.isAirBlock(i1-1, j1-1, k1+1)
	            		|| theblock.getBlock(i1-1, j1-1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1-1, k1+1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1-1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1-1, k1+1, Blocks.air);
	            		theblock.setBlock(i1-1, j1-1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
  	            //////////////////
  	            {

  	        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
  	        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
  	            }
  	            //East/west axis, Vertical axis, North/south axis
  	            return true;
    	        } 
     //////////////////////////////////////////////////////////////////////////////////////
    		  if (theface == 3)
    	        {
    	            ++k1;
    	         //SOUTH FACE
	            //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1, j1+1, k1-1)
	            		|| theblock.getBlock(i1, j1+1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1, j1+1, k1-1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1, j1+1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1, j1+1, k1-1, Blocks.air);
	            		theblock.setBlock(i1, j1+1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	          if (theblock.isAirBlock(i1, j1-1, k1-1)
	            		|| theblock.getBlock(i1, j1-1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1, j1-1, k1-1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1, j1-1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1, j1-1, k1-1, Blocks.air);
	            		theblock.setBlock(i1, j1-1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	        //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1+1, j1+1, k1-1)
	            		|| theblock.getBlock(i1+1, j1+1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1+1, k1-1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1+1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1+1, k1-1, Blocks.air);
	            		theblock.setBlock(i1+1, j1+1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            if (theblock.isAirBlock(i1, j1+1, k1-1)
	            		|| theblock.getBlock(i1, j1+1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1, j1+1, k1-1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1, j1+1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1, j1+1, k1-1, Blocks.air);
	            		theblock.setBlock(i1, j1+1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            if (theblock.isAirBlock(i1-1, j1+1, k1-1)
	            		|| theblock.getBlock(i1-1, j1+1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1+1, k1-1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1+1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1+1, k1-1, Blocks.air);
	            		theblock.setBlock(i1-1, j1+1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1+1, j1, k1-1)
	            		|| theblock.getBlock(i1+1, j1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1, k1-1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1, k1-1, Blocks.air);
	            		theblock.setBlock(i1+1, j1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            if (theblock.isAirBlock(i1, j1, k1-1)
	            		|| theblock.getBlock(i1, j1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1, j1, k1-1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1, j1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1, j1, k1-1, Blocks.air);
	            		theblock.setBlock(i1, j1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            if (theblock.isAirBlock(i1-1, j1, k1-1)
	            		|| theblock.getBlock(i1-1, j1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1, k1-1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1, k1-1, Blocks.air);
	            		theblock.setBlock(i1-1, j1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }

	            //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1+1, j1-1, k1-1)
	            		|| theblock.getBlock(i1+1, j1-1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1-1, k1-1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1-1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1-1, k1-1, Blocks.air);
	            		theblock.setBlock(i1+1, j1-1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            if (theblock.isAirBlock(i1, j1-1, k1-1)
	            		|| theblock.getBlock(i1, j1-1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1, j1-1, k1-1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1, j1-1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1, j1-1, k1-1, Blocks.air);
	            		theblock.setBlock(i1, j1-1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            if (theblock.isAirBlock(i1-1, j1-1, k1-1)
	            		|| theblock.getBlock(i1-1, j1-1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1-1, k1-1).getMaterial() == Material.water)
	            {
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        		{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1-1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1-1, k1-1, Blocks.air);
	            		theblock.setBlock(i1-1, j1-1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        }	
	        		}
	            
	            }
	            //////////////////
	            {

	        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
	        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
	            }
	            //East/west axis, Vertical axis, North/south axis
	            return true;
  	        } 
 //////////////////////////////////////////////////////////// //////////////////////
    		 
    		  if (theface == 4)
    	        {
    	            --i1;
    	            //WEST FACE
	            //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1+1, j1, k1-1)
	            		|| theblock.getBlock(i1+1, j1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1, k1-1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1, k1-1, Blocks.air);
	            		theblock.setBlock(i1+1, j1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            if (theblock.isAirBlock(i1+1, j1, k1+1)
	            		|| theblock.getBlock(i1+1, j1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1, k1+1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1, k1+1, Blocks.air);
	            		theblock.setBlock(i1+1, j1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1+1, j1+1, k1-1)
	            		|| theblock.getBlock(i1+1, j1+1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1+1, k1-1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1+1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1+1, k1-1, Blocks.air);
	            		theblock.setBlock(i1+1, j1+1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            if (theblock.isAirBlock(i1+1, j1+1, k1)
	            		|| theblock.getBlock(i1+1, j1+1, k1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1+1, k1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1+1, k1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1+1, k1, Blocks.air);
	            		theblock.setBlock(i1+1, j1+1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            if (theblock.isAirBlock(i1+1, j1+1, k1+1)
	            		|| theblock.getBlock(i1+1, j1+1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1+1, k1+1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1+1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1+1, k1+1, Blocks.air);
	            		theblock.setBlock(i1+1, j1+1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	          //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1+1, j1-1, k1-1)
	            		|| theblock.getBlock(i1+1, j1-1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1-1, k1-1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1-1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1-1, k1-1, Blocks.air);
	            		theblock.setBlock(i1+1, j1-1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            if (theblock.isAirBlock(i1+1, j1-1, k1)
	            		|| theblock.getBlock(i1+1, j1-1, k1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1-1, k1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1-1, k1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1-1, k1, Blocks.air);
	            		theblock.setBlock(i1+1, j1-1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            if (theblock.isAirBlock(i1+1, j1-1, k1+1)
	            		|| theblock.getBlock(i1+1, j1-1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1+1, j1-1, k1+1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1+1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1+1, j1-1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1+1, j1-1, k1+1, Blocks.air);
	            		theblock.setBlock(i1+1, j1-1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            	  //////////////////
		            {

		        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
		        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
		            }
		            //East/west axis, Vertical axis, North/south axis
		            return true;
	  	        } 
   ////////////////////////////////////////////////////////////////////////////

    		  if (theface == 5)
    	        {
    	            ++i1;
    	            //EAST FACE
	            //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1-1, j1, k1-1)
	            		|| theblock.getBlock(i1-1, j1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1, k1-1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1, k1-1, Blocks.air);
	            		theblock.setBlock(i1-1, j1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            if (theblock.isAirBlock(i1-1, j1, k1+1)
	            		|| theblock.getBlock(i1-1, j1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1, k1+1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1, k1+1, Blocks.air);
	            		theblock.setBlock(i1-1, j1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1-1, j1+1, k1-1)
	            		|| theblock.getBlock(i1-1, j1+1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1+1, k1-1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1+1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1+1, k1-1, Blocks.air);
	            		theblock.setBlock(i1-1, j1+1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            if (theblock.isAirBlock(i1-1, j1+1, k1)
	            		|| theblock.getBlock(i1-1, j1+1, k1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1+1, k1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1+1, k1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1+1, k1, Blocks.air);
	            		theblock.setBlock(i1-1, j1+1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            if (theblock.isAirBlock(i1-1, j1+1, k1+1)
	            		|| theblock.getBlock(i1-1, j1+1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1+1, k1+1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1+1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1+1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1+1, k1+1, Blocks.air);
	            		theblock.setBlock(i1-1, j1+1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	          //W/E_T/B_N/S
	            if (theblock.isAirBlock(i1-1, j1-1, k1-1)
	            		|| theblock.getBlock(i1-1, j1-1, k1-1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1-1, k1-1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1-1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1-1, k1-1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1-1, k1-1, Blocks.air);
	            		theblock.setBlock(i1-1, j1-1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            if (theblock.isAirBlock(i1-1, j1-1, k1)
	            		|| theblock.getBlock(i1-1, j1-1, k1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1-1, k1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1-1, k1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1-1, k1, Blocks.air);
	            		theblock.setBlock(i1-1, j1-1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            if (theblock.isAirBlock(i1-1, j1-1, k1+1)
	            		|| theblock.getBlock(i1-1, j1-1, k1+1).getMaterial() == Material.lava 
	            		|| theblock.getBlock(i1-1, j1-1, k1+1).getMaterial() == Material.water)
	            	{
	            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
	        			{
	            		 theblock.playSoundEffect((double)((float)i1-1 + 0.5F), (double)((float)j1-1 + 0.5F), (double)((float)k1+1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
	            		 theblock.setBlock(i1-1, j1-1, k1+1, Blocks.cobblestone);
	            		 theblock.setBlock(i1-1, j1-1, k1+1, Blocks.air);
	            		theblock.setBlock(i1-1, j1-1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
	            		if (!theplayer.capabilities.isCreativeMode){                	
	                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
	                        thestaff.damageItem(1, theplayer);
	                        	}	
	        			}
	            	}
	            	  //////////////////
		            {

		        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
		        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
		            }
		            //East/west axis, Vertical axis, North/south axis
		            return true;
	  	        } 
    /////////////		 ///////////////////////////////////////
    	}
    	
///////////////////////////////////////////////////////////////////////////////////////
    	/** //pillar mode   //pillar mode   //pillar mode   //pillar mode   //pillar mode   //// name = new ////(arguments); <--- Thanks eclipse for the autocomplete.**/
   /////////////////////////////////////////////////////////////////////////////////// ////////////////////
    	//If true perfroms the pillar generation
    	//
    	if (!theplayer.isSneaking()
    			&& getMode(thestaff) == 2)
    	{ 
    		
        if (theface == 0)
        {
            --j1;
            //BOTTOM FACE
            if (theblock.isAirBlock(i1, j1, k1)
            		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1, k1, Blocks.air);
            		theblock.setBlock(i1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }	
        		}
            
            }
            if (theblock.isAirBlock(i1, j1-1, k1)
            		|| theblock.getBlock(i1, j1-1, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1-1, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{	
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1-1, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1-1, k1, Blocks.air);
            		theblock.setBlock(i1, j1-1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }
        		}
            }            
            if (theblock.isAirBlock(i1, j1-2, k1)
            		|| theblock.getBlock(i1, j1-2, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1-2, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1-2, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1-2, k1, Blocks.air);
            		theblock.setBlock(i1, j1-2, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }
        		}
            }
            {

        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
            }
            //East/west axis, Vertical axis, North/south axis
            return true;
        }

        if (theface == 1)
        {
            ++j1;
            //TOP FACE
            if (theblock.isAirBlock(i1, j1, k1)
            		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1, k1, Blocks.air);
            		theblock.setBlock(i1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }	
        		}
            
            }
            if (theblock.isAirBlock(i1, j1+1, k1)
            		|| theblock.getBlock(i1, j1+1, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1+1, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1+1, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1+1, k1, Blocks.air);
            		theblock.setBlock(i1, j1+1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }	
        		}
            
            }
            
            if (theblock.isAirBlock(i1, j1+2, k1)
            		|| theblock.getBlock(i1, j1+2, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1+2, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1+2, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1+2, k1, Blocks.air);
            		theblock.setBlock(i1, j1+2, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
                    (theblock.getBlock(i1, j1+2, k1)).setTickRandomly(false);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }
        		}
            
            }
            {

        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
            }
            return true;	
            
        }

        if (theface == 2)
        {
            --k1;
            //NORTH FACE
            if (theblock.isAirBlock(i1, j1, k1)
            		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1, k1, Blocks.air);
            		theblock.setBlock(i1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }	
        		}
            
            }
            if (theblock.isAirBlock(i1, j1, k1-1)
            		|| theblock.getBlock(i1, j1, k1-1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1, k1-1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1, k1-1, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1, k1-1, Blocks.air);
            		theblock.setBlock(i1, j1, k1-1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }
        		}
            }
            if (theblock.isAirBlock(i1, j1, k1-2)
            		|| theblock.getBlock(i1, j1, k1-2).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1, k1-2).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1, k1-2, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1, k1-2, Blocks.air);
            		theblock.setBlock(i1, j1, k1-2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }
        		}
            }
            {

        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
            }
            return true;
        }

        if (theface == 3)
        {
            ++k1;
            //SOUTH FACE
            if (theblock.isAirBlock(i1, j1, k1)
            		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1, k1, Blocks.air);
            		theblock.setBlock(i1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }	
        		}
            
            }
            if (theblock.isAirBlock(i1, j1, k1+1)
            		|| theblock.getBlock(i1, j1, k1+1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1, k1+1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1, k1+1, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1, k1+1, Blocks.air);
            		theblock.setBlock(i1, j1, k1+1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }
        		}
            }
            if (theblock.isAirBlock(i1, j1, k1+2)
            		|| theblock.getBlock(i1, j1, k1+2).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1, k1+2).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1, k1+2, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1, k1+2, Blocks.air);
            		theblock.setBlock(i1, j1, k1+2, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }
        		}
            }
            {

        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
            }
            return true;
        }

        if (theface == 4)
        {
            --i1;
            //WEST FACE
            if (theblock.isAirBlock(i1, j1, k1)
            		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1, k1, Blocks.air);
            		theblock.setBlock(i1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }	
        		}
            	
            }
            if (theblock.isAirBlock(i1-1, j1, k1)
            		|| theblock.getBlock(i1-1, j1, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1-1, j1, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1-1, j1, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1-1, j1, k1, Blocks.air);
            		theblock.setBlock(i1-1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }
        		}
            }
            if (theblock.isAirBlock(i1-2, j1, k1)
            		|| theblock.getBlock(i1-2, j1, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1-2, j1, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		theblock.setBlock(i1-2, j1, k1, Blocks.cobblestone);
            		theblock.setBlock(i1-2, j1, k1, Blocks.air);
            		theblock.setBlock(i1-2, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }
            		
        		}
            }
            {

        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
            }
            return true;
        }

        if (theface == 5)
        {
            ++i1;
            //EAST FACE
            if (theblock.isAirBlock(i1, j1, k1)
            		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1, j1, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1, j1, k1, Blocks.air);
            		theblock.setBlock(i1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }	
        		}
            
            }
            if (theblock.isAirBlock(i1+1, j1, k1)
            		|| theblock.getBlock(i1+1, j1, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1+1, j1, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1+1, j1, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1+1, j1, k1, Blocks.air);
            		theblock.setBlock(i1+1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }
        		}
            }
            if (theblock.isAirBlock(i1+2, j1, k1)
            		|| theblock.getBlock(i1+2, j1, k1).getMaterial() == Material.lava 
            		|| theblock.getBlock(i1+2, j1, k1).getMaterial() == Material.water)
            {
            	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
        		{
            		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
            		 theblock.setBlock(i1+2, j1, k1, Blocks.cobblestone);
            		 theblock.setBlock(i1+2, j1, k1, Blocks.air);
            		theblock.setBlock(i1+2, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
            
            		if (!theplayer.capabilities.isCreativeMode){                	
                        theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                        thestaff.damageItem(1, theplayer);
                        }
        		}
            }
            {

        		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
        				(double)k1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
            }
            return true;
        }
////////////////////////////////////////////////////////////////////////////////////////////

		
		/**
		 * If these all are false it returns with a default action
		 */
		
        
        //////////////////////////
        if (!theplayer.canPlayerEdit(i1, j1, k1, theface, thestaff))
        {
            return false;
        }
        
    	}
    	//////
    	if (theplayer.isSneaking())
    	{ 
    		setTBlock(thestaff, theblock.getBlock(i1, j1, k1).getIdFromBlock(theblock.getBlock(i1, j1, k1)));
    		setTMeta(thestaff,theblock.getBlockMetadata(i1, j1, k1)); 		
    		//
    		
    		theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
    				(double)k1 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
    		
    		 setOffMode(thestaff, 4);
 			return true;
    	}
    	else
    	{ 
    		
    		return false;     		
    	}
    	//}
    	//return false;
    	
    }

    //Standard line that allows 3D rendering
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    
    
}