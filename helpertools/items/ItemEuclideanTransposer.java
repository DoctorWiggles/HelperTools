package helpertools.items;

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

public class ItemEuclideanTransposer extends ItemSpade
{
    private static final String __OBFID = "CL_00000035";

  
   

    public ItemEuclideanTransposer(ToolMaterial material)
    {
    	super (material);
        this.maxStackSize = 1;  
        setUnlocalizedName("euclideantransposer");
        //setCreativeTab(Helpertoolscore.HelperTools);
        setCreativeTab(HelpTab.HelperTools);
        setTextureName("helpertools:EuWand1");
    }
    //flavor text
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    par3List.add(EnumChatFormatting.ITALIC + "sets patterns");
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
      
    public int getTBlock(ItemStack itemStack, int Nbtcount)
	{
		if(itemStack.stackTagCompound == null)
		{
			return 0;
		}

		return itemStack.stackTagCompound.getInteger("TBlock" + Nbtcount);
		
	}
	public void setTBlock(ItemStack itemStack, int Value, int Nbtcount)
	{
		if(itemStack.stackTagCompound == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		itemStack.stackTagCompound.setInteger("TBlock" + Nbtcount,  Value );
		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	} 
	////////
	public int getTMeta(ItemStack itemStack, int Nbtcount)
	{
		if(itemStack.stackTagCompound == null)
		{
			return 0;
		}

		return itemStack.stackTagCompound.getInteger("TMeta" + Nbtcount);
		
	}
	public void setTMeta(ItemStack itemStack, int Value, int Nbtcount)
	{
		if(itemStack.stackTagCompound == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		itemStack.stackTagCompound.setInteger("TMeta"+ Nbtcount,  Value );
		//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
	} 
	
	
	//////
	public int returnTMeta(ItemStack thestaff, int Nbtcount)
    {
		return getTMeta(thestaff, Nbtcount);
		
    }
	
	public Block returnTBlock(ItemStack thestaff, int Nbtcount)
	{
		return Block.getBlockById(getTBlock(thestaff, Nbtcount));
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
    	int P = 13;
    	if (!theplayer.isSneaking()){
    	for (int G2 = 0; G2 < P; ++G2)
        {
    		int G2counter = G2*P*P;
    		for (int U = 0; U < P; ++U)
    		{
    			int Ucounter = U*P;
    			for (int l = 0; l < P; ++l)
    			{
    				int Nbtcounter = G2counter+Ucounter+l+1;
    				/**
    				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
							"Counter is? : " + (Nbtcounter), new Object[0]);
					((EntityPlayer) theplayer)
							.addChatComponentMessage(chatcomponenttranslation);
    				**/
    				if (theblock.isAirBlock(i1+U, j1+1+l, k1+G2)
    						|| theblock.getBlock(i1+U, j1+1+l, k1+G2).getMaterial() == Material.lava 
    						|| theblock.getBlock(i1+U, j1+1+l, k1+G2).getMaterial() == Material.water)
    				{
    					
    					theblock.playSoundEffect((double)((float)i1+U + 0.5F), (double)((float)j1+1+l + 0.5F), (double)((float)k1+G2 + 0.5F), returnTBlock(thestaff, Nbtcounter).stepSound.getStepResourcePath(), (returnTBlock(thestaff, Nbtcounter).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff, Nbtcounter).stepSound.getPitch() * 0.8F);
    					theblock.setBlock(i1+U, j1+1+l, k1+G2, returnTBlock(thestaff, Nbtcounter), (returnTMeta(thestaff, Nbtcounter)), 0);
        		
        
    				}
    			
    			
    				}
    			}
        }
    	}
    	if (theplayer.isSneaking()){
    		
    		for (int G2 = 0; G2 < P; ++G2)
            {
        		int G2counter = G2*P*P;
        		for (int U = 0; U < P; ++U)
        		{
        			int Ucounter = U*P;
        			for (int l = 0; l < P; ++l)
        			{
        				int Nbtcounter = G2counter+Ucounter+l+1;
        				/**
        				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
    							"Counter is? : " + (Nbtcounter), new Object[0]);
    					((EntityPlayer) theplayer)
    							.addChatComponentMessage(chatcomponenttranslation);
        				**/
        				
        					setTBlock(thestaff, theblock.getBlock(i1+U, j1+l, k1+G2).getIdFromBlock(theblock.getBlock(i1+U, j1+l, k1+G2)), Nbtcounter);
        					setTMeta(thestaff,theblock.getBlockMetadata(i1+U, j1+l, k1+G2), Nbtcounter); 		
        					//theblock.playSoundEffect((double)((float)i1+U + 0.5F), (double)((float)j1+1+l + 0.5F), (double)((float)k1+G2 + 0.5F), returnTBlock(thestaff, Nbtcounter).stepSound.getStepResourcePath(), (returnTBlock(thestaff, Nbtcounter).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff, Nbtcounter).stepSound.getPitch() * 0.8F);
        					//theblock.setBlock(i1+U, j1+1+l, k1+G2, returnTBlock(thestaff, Nbtcounter));
            		
            
        				
        			
        			
        				}
        			}
            }
    	}
    	return false;
    }

    //Standard line that allows 3D rendering
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    
    
}