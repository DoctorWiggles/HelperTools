package helpertools.tools;

import java.util.List;

import helpertools.Common_Registry;
import helpertools.HelpTab;
import helpertools.Helpertoolscore;
import helpertools.util.KeyBindings;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Random;

import org.apache.commons.lang3.ClassUtils;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemDebugTool extends Item {

	  public ItemDebugTool() {
	       super();
	       this.maxStackSize = 1;  
	       setUnlocalizedName("debugtool");
	       setCreativeTab(HelpTab.HelperTools);
	       setTextureName("helpertools:wand6");
	       
	   }
	  
	  protected static Random growrand = new Random();
	  
	  private int value;
	  /**
	  public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	   {
	   par3List.add("§6*Danger Explosive*");
	   //par3List.add("Red: " + "255");
	   //par3List.add("§4Poop");
	   
	   }
	   **/
	 
	  ////////////////////////////////////////////////////////////////////////
	  public int getMode(ItemStack itemStack)
		{
			if(itemStack.stackTagCompound == null)
			{
				return 0;
			}

			return itemStack.stackTagCompound.getInteger("mode");
		}
	  
	  
	  ////////////
	  
	  public void toggleMode(ItemStack itemStack)
		{
			if(itemStack.stackTagCompound == null)
			{
				itemStack.setTagCompound(new NBTTagCompound());
			}

			itemStack.stackTagCompound.setInteger("mode", getMode(itemStack) < 4 ? getMode(itemStack)+1 : 0);
			
		}
	  /////////////////////////////
	  
	  public void setMode(ItemStack itemStack, int Value)
		{
			if(itemStack.stackTagCompound == null)
			{
				itemStack.setTagCompound(new NBTTagCompound());
			}

			itemStack.stackTagCompound.setInteger("mode",  Value );
			//this.tagMap.put(p_74768_1_, new NBTTagInt(p_74768_2_));
		}
	  
	  ////////////////////////////////////////////////////////////////
	  public String getItemStackDisplayName(ItemStack p_77653_1_)
	    {
		  return ("§d" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(p_77653_1_) + ".name")).trim();
	    }
	    ///////////////////////////////////////////////////////
	    private boolean inGround;
	    boolean test1;
	    int offmode = 2;
	    //////////////////////////////////////////////////////
	    
	    public void writeItemStackToNBT(NBTTagCompound p_70014_1_)
	    {
	        p_70014_1_.setByte("inGround", (byte)(this.inGround ? 1 : 0));
	        p_70014_1_.setInteger("test1", (int)(this.test1 ? 16 : 0));
	        
	    }
	    
	    
	    public void readItemStackFromNBT(NBTTagCompound p_70037_1_)
	    {
	        this.inGround = p_70037_1_.getByte("inGround") == 1;
	        this.test1 = p_70037_1_.getInteger("test1") == 1;

	    }
	    
	   // return !getSheared() && !isChild();
	    
	    //setSheared(true);
	    
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if (!entityLiving.worldObj.isRemote) {
			if (stack.stackTagCompound == null) {
				stack.stackTagCompound = new NBTTagCompound();
				this.value = new Random().nextInt();
				stack.stackTagCompound.setString("owner",
						((EntityPlayer) entityLiving).getDisplayName());
				stack.stackTagCompound.setInteger("code", (int) value);
				stack.stackTagCompound.setInteger("nbtmode", (int) 0);
				return false;
			}
			if (stack.stackTagCompound != null) {

				// Initiate Mode Cycling

				// int nbtmode = stack.stackTagCompound.getInteger("nbtmode");

				if (!entityLiving.isSneaking()) {
					/*
					ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
							"Mode is? : " + (getMode(stack)), new Object[0]);
					((EntityPlayer) entityLiving)
							.addChatComponentMessage(chatcomponenttranslation);
							*/

					return false;

				}
				if (entityLiving.isSneaking()) { 

					// int nbtmode =
					// stack.stackTagCompound.getInteger("nbtmode");
					// toggleMode(stack);
					// toggleMode(stack);
					
					
					 int intg = growrand.nextInt(5);
					setMode(stack, (intg));
				}

			}
			return false;
		}

		return false;
	}
	    

	    private Random rand = new Random();
	    
	    
	    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
	        itemStack.stackTagCompound = new NBTTagCompound();
	        
	        this.value = new Random().nextInt();
	        itemStack.stackTagCompound.setString("owner", player.getDisplayName());
	        //itemStack.stackTagCompound.setInteger("code", (int)(Math.random*Integer.MAX_VALUE));
	        itemStack.stackTagCompound.setInteger("code", (int)value);
	        itemStack.stackTagCompound.setInteger("nbtmode", (int)0);
	        
	    }    
	    
	    short timer =  360;
	    //update test
	    /**
	    public void onUpdate(ItemStack p_77663_1_, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
	    	
	    	ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
					"Timer: " + (timer), new Object[0]);
			((EntityPlayer) entity)
					.addChatComponentMessage(chatcomponenttranslation);
			if (timer == 0){
				timer =  360;
			}
			else 
				timer--;
			
	    			
	    }
	    **/
	    
	    ////////////////////////////////////////////////////////////////////////
		  public int getSpinDec(ItemStack itemStack) {
				if (itemStack.stackTagCompound == null) {
					return 360;
				}

				return itemStack.stackTagCompound.getShort("SpinDec");

			}		
			public void setSpinDec(ItemStack itemStack, short Value) {
				if (itemStack.stackTagCompound == null) {
					itemStack.setTagCompound(new NBTTagCompound());
				}

				itemStack.stackTagCompound.setShort("SpinDec",  Value);			
			}
	  /////////////////////////////////////////////////////////////////////
	    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
	    	

	    	
	    	
	    	if(!entity.worldObj.isRemote){
	    		return;
	    	}
	    	/**
	    	ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
					"Timer: " + (getSpinDec(stack)), new Object[0]);
			((EntityPlayer) entity)
					.addChatComponentMessage(chatcomponenttranslation);
			if (getSpinDec(stack)== 0){
				setSpinDec(stack, (short) 360);
			}
			else 
				setSpinDec(stack, (short)(getSpinDec(stack)-1));
			**/
				//timer--;
			
	    			
	    }
	    
	    
	    /** key code stuffs **/
	    //////////////////////
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player,
			List list, boolean par4) {
		// //////////////////////////////////////////
		list.add("§7§oSpoting those pesky problems");
		// ////////////////////////////////////////////

		if (itemStack.stackTagCompound != null) {
			String owner = itemStack.stackTagCompound.getString("owner");
			int code = itemStack.stackTagCompound.getInteger("code");
			list.add("owner: " + owner);
			if (owner.equals(player.getDisplayName())) {
				list.add(EnumChatFormatting.GREEN + "code: " + code);
			} else {
				list.add(EnumChatFormatting.RED + "code: "
						+ EnumChatFormatting.OBFUSCATED + code);
			}
		}

	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World par2World,
			EntityPlayer entityLiving) {
		if (!entityLiving.worldObj.isRemote) {
			/**
			ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
					"TOP CODE : " + (getMode(stack)));
			((EntityPlayer) entityLiving)
					.addChatComponentMessage(chatcomponenttranslation);
					**/
			
			////////////
			/**

			switch (getMode(stack)) {
			case 0:
				ChatComponentTranslation chatcomponenttranslation0 = new ChatComponentTranslation(
						"PEW PEW : " + (getMode(stack)), new Object[0]);
				((EntityPlayer) entityLiving)
						.addChatComponentMessage(chatcomponenttranslation0);
				break;
			case 1:
				ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation(
						"BANG : " + (getMode(stack)), new Object[0]);
				((EntityPlayer) entityLiving)
						.addChatComponentMessage(chatcomponenttranslation1);
				break;
			case 2:
				ChatComponentTranslation chatcomponenttranslation2 = new ChatComponentTranslation(
						"WOOF : " + (getMode(stack)), new Object[0]);
				((EntityPlayer) entityLiving)
						.addChatComponentMessage(chatcomponenttranslation2);
				break;
			case 3:
				ChatComponentTranslation chatcomponenttranslation3 = new ChatComponentTranslation(
						"ZAP : " + (getMode(stack)), new Object[0]);
				((EntityPlayer) entityLiving)
						.addChatComponentMessage(chatcomponenttranslation3);
				break;
			case 4:
				ChatComponentTranslation chatcomponenttranslation4 = new ChatComponentTranslation(
						"Krrr-POW! : " + (getMode(stack)), new Object[0]);
				((EntityPlayer) entityLiving)
						.addChatComponentMessage(chatcomponenttranslation4);
				break;
			}
			**/
			/////////
			
			/**
			ChatComponentTranslation chatcomponenttranslation8 = new ChatComponentTranslation(
					"STACK REACH : " + (getMode(stack)), new Object[0]);
			((EntityPlayer) entityLiving)
					.addChatComponentMessage(chatcomponenttranslation8);
					**/
			return stack;
			// return stack.setTagCompound("mode");

		}
		return stack;
	}
	///////////////////////////////////////////////
	
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
	
	
	
	Block Tblock = Blocks.lapis_block;
	//
	int Tmeta = 0;
	
	int idfb2 = 0;
	
	////////////////////////////////////////////////
	
	public boolean onItemUse(ItemStack thestaff, EntityPlayer theplayer, World theblock, int i1, int j1, int k1, int theface, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
		//if (!theplayer.worldObj.isRemote) {
			
			if (!theplayer.isSneaking()){
				
				if(theblock.getBlock(i1, j1, k1) == Blocks.grass ){

					ChatComponentTranslation chatcomponenttranslation4 = new ChatComponentTranslation(
							"Grass test " , new Object[0]);
					((EntityPlayer) theplayer)
							.addChatComponentMessage(chatcomponenttranslation4);
				}
				//Class[] classes = ClassUtils.getClass(theblock.getBlock(i1, j1, k1));
				
				//if(theblock instanceof IGrowable){}
				Block zablock = theblock.getBlock(i1,j1,k1);
				if(zablock == Blocks.dirt ||
						zablock == Common_Registry.LooseDirtBlock){
					theblock.setBlock(i1,j1,k1, Blocks.grass);
				}
				
				if (IGrowable.class.isAssignableFrom((theblock.getBlock(i1, j1, k1).getClass()))) {
					ChatComponentTranslation chatcomponenttranslation4 = new ChatComponentTranslation(
							"Growable???? " , new Object[0]);
					((EntityPlayer) theplayer)
							.addChatComponentMessage(chatcomponenttranslation4);
					
					ItemDye.applyBonemeal(thestaff, theblock, i1, j1, k1, theplayer);
					
					for (int i2 = 0; i2 < 20; ++i2)
		            {
		                double d0 = itemRand.nextGaussian() * 0.02D;
		                double d1 = itemRand.nextGaussian() * 0.02D;
		                double d2 = itemRand.nextGaussian() * 0.02D;
		                float d3 = i2/10;
		                Block block = theblock.getBlock(i1, j1, k1);
		                theblock.spawnParticle("happyVillager", (double)((float)i1 + itemRand.nextFloat()), (double)j1 + (double)itemRand.nextFloat() * block.getBlockBoundsMaxY()+(d3), (double)((float)k1 + itemRand.nextFloat()), d0, d1, d2);
		            }
					
					
					
					
					
					/**
					short short1 = 32;
					for (int lp = 0; lp < short1; ++lp)
		            {
					theblock.spawnParticle("portal", i1+.5, j1+.6, k1+.5, .5, .5, .5);
		            }
		            **/
				}
				/**
		if (!theblock.isAirBlock(i1, j1, k1)
        		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.lava 
        		|| theblock.getBlock(i1, j1, k1).getMaterial() == Material.water)
        {
        	if(theplayer.capabilities.isCreativeMode || theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff))))
    		{
        		 theblock.playSoundEffect((double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
        		 theblock.setBlock(i1, j1, k1, Blocks.cobblestone);
        		 theblock.setBlock(i1, j1, k1, Blocks.air);
        		//theblock.setBlock(i1, j1, k1, Tblock, Tmeta, 0);
        		//theblock.setBlock(i1, j1, k1, (Block.getBlockById(getTBlock(thestaff))), Tmeta, 0); 
        		 //
        		 //theblock.setBlock(i1, j1, k1, (Block.getBlockById(getTBlock(thestaff))), (getTMeta(thestaff)), 0);
        		 //
        		 theblock.setBlock(i1, j1, k1, (Block.getBlockById(getTBlock(thestaff))), (returnTMeta(thestaff)), 0);
        		 theblock.setBlock(i1, j1, k1, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 0);
        		if (!theplayer.capabilities.isCreativeMode){                	
                    theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff)));	
                    thestaff.damageItem(1, theplayer);
                    }	
    		}
        
        }
		
		**/
			}
			if (theplayer.isSneaking()){

				
				//int idfb = 0;
				//setTBlock(thestaff, 0);
				
				//theblock.getBlock(i1, j1, k1).getIdFromBlock(idfb);
				
				Tblock = theblock.getBlock(i1, j1, k1);
	    		Tmeta = theblock.getBlockMetadata(i1, j1, k1);    
	    		
				//int idfb2 = Tblock.getIdFromBlock(Tblock);
				
				//setTBlock(thestaff, Tblock.getIdFromBlock(Tblock));
	    		setTBlock(thestaff, theblock.getBlock(i1, j1, k1).getIdFromBlock(theblock.getBlock(i1, j1, k1)));
	    		setTMeta(thestaff,theblock.getBlockMetadata(i1, j1, k1));
	    		/**
	    		///Bed test
	    		 * **/
	    		//credit http://www.minecraftforge.net/forum/index.php?topic=16041.0
	    		
	    		ChunkCoordinates coordinates = theplayer.getBedLocation(0);
				if (coordinates == null) {
					coordinates = theblock.getSpawnPoint();
				}
				float yOffset = 1.62F;
				
				theplayer.setPosition((double)((float)coordinates.posX + 0.5F), (double)((float)coordinates.posY + yOffset + 0.1F), (double)((float)coordinates.posZ + 0.5F));
				
				/**
	    		int posX = 0;

	    		ChunkCoordinates chunkCoords = theplayer.getBedLocation(theplayer.dimension());
	    	      if (chunkCoords == null) {
	    	        chunkCoords = world.func_175694_M();
	    	      }
	    	      chunkCoords = CWSettings.verifyRespawnCoordinates(theblock, chunkCoords, false);
	    	      if (chunkCoords == null) {
	    	        chunkCoords = world.func_175694_M();
	    	      }
	    	      
	    		ChunkCoordinates chunkcoordinates1 = EntityPlayer.verifyRespawnCoordinates(theblock, null, false);
	    		 
	    		ChunkCoordinates chunkcoordinates1 = theblock.getBlock(ChunkCoordinates.posX, theblock.posY, theblock.posZ).getBedSpawnPosition(p_71056_0_, p_71056_1_.posX, p_71056_1_.posY, p_71056_1_.posZ, null);
	    		
	    		
	    		ChunkCoordinates chunkcoordinates = theplayer.playerLocation;
	            ChunkCoordinates chunkcoordinates1 = theplayer.playerLocation;
	            Block block = (chunkcoordinates == null ? null : worldObj.getBlock(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ));

	            if (chunkcoordinates != null && block.isBed(worldObj, chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, this))
	            {
	                block.setBedOccupied(this.worldObj, chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, this, false);
	                chunkcoordinates1 = block.getBedSpawnPosition(this.worldObj, chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, this);

	                if (chunkcoordinates1 == null)
	                {
	                    chunkcoordinates1 = new ChunkCoordinates(chunkcoordinates.posX, chunkcoordinates.posY + 1, chunkcoordinates.posZ);
	                }
				**/
	                
	            //}
	            /**
	            /////Bed test
				**/
				
				//getMode(stack)
				/*
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
						"Block ID is? : " + (idfb2), new Object[0]);
				((EntityPlayer) theplayer)
						.addChatComponentMessage(chatcomponenttranslation);
				*/
	    		
			}

			
			ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
					"Block ID is? : " + (getTBlock(thestaff)) + " : " + (getTMeta(thestaff)), new Object[0]);
			((EntityPlayer) theplayer)
					.addChatComponentMessage(chatcomponenttranslation);
					
		//}
		return true;
    }
	    
	    
////////////////////////////////////////////////////////////////////////////////////////////
	    
	    /**
	    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
	    {
	    	//int nbtmode = stack.stackTagCompound.getInteger("nbtmode");
	    	 //nbtmode == 0
	    	 //alternativly
	    	
	    	if(!entityLiving.isSneaking())
	    	{
	    		if((stack.stackTagCompound.getInteger("nbtmode")) ==0 ){
	    			int code = stack.stackTagCompound.getInteger("code");
	    			ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("The code is: " + code, new Object[0]);
	    			((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
	    			ChatComponentTranslation chatcomponenttranslation2 = new ChatComponentTranslation("Working: " +(stack.stackTagCompound.getInteger("nbtmode")), new Object[0]);
	    			((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation2);
	    			return false;
	    			}
	    		if((stack.stackTagCompound.getInteger("nbtmode")) ==1){
	    			//int nbtmode = stack.stackTagCompound.getInteger("nbtmode");
	    			ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("Not Working: " +(stack.stackTagCompound.getInteger("nbtmode")), new Object[0]);
	    			((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
	    			ChatComponentTranslation chatcomponenttranslation2 = new ChatComponentTranslation("Working: " +(stack.stackTagCompound.getInteger("nbtmode")), new Object[0]);
	    			((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation2);
	    			return false;
		    		}
	    	}
	    	if (entityLiving.isSneaking())
	       	{ 		//Offset returns and cycles
	    			//Because for some reason this boolean is called twice every swing
	    			//So the offset only allows it to happen once, otherwise you get some not fun repeat issues here
	    			if (offmode ==2)
	    			{
	    				offmode = 0;
	    				//ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("Off 2", new Object[0]);
	    				 //((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
	    			}
	    			if (offmode == 1)
	    			{
	    				offmode = offmode +1;
	    				//ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("Off 1", new Object[0]);
	    				 //((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
	    			}
	    			//Initiate Mode Cycling
	    			if (offmode == 0){
	    				offmode = offmode +1;
	    				//int nbtmode = stack.stackTagCompound.getInteger("nbtmode");
	    					
	    					if (!this.inGround){
	    					this.inGround = true;
	    					return false;
	    					}
	    					if (this.inGround){
		    					this.inGround = false;
		    					return false;
		    					} 
	    					
	    						if ((stack.stackTagCompound.getInteger("nbtmode")) ==0){
	    							//nbtmode = 1;
	    							stack.stackTagCompound.setInteger("nbtmode", (int)1);
	    							return false;
		    						}
		    					if ((stack.stackTagCompound.getInteger("nbtmode")) ==1){
			    					//nbtmode = 0;
			    					stack.stackTagCompound.setInteger("nbtmode", (int)0);
			    					return false;
			    					}
	    				}
	       	}
	    	
	    	return false;
	    }
	    
	    **/
	    
	    
	    
}
