package helpertools.tools;

import java.util.List;
import java.util.Random;

import helpertools.HelpTab;
import helpertools.Helpertoolscore;
import helpertools.blocks.tile_entities.TileEntityTranscriber;
import helpertools.util.InventoryUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
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
    public ItemEuclideanTransposer(ToolMaterial material)
    {
    	super (material);
        this.maxStackSize = 1;  
        setUnlocalizedName("euclideantransposer");
        setCreativeTab(HelpTab.HelperTools);
        setTextureName("helpertools:EuWand1");
    }
    //////////////////
    protected static Random growrand = new Random();
    ////////////////////////////////////////////////
    
    
    
    //flavor text
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    par3List.add(EnumChatFormatting.ITALIC + "sets patterns 5x5");
    }
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
		
		
		////////////////////////
		/** Rotation Counter**/
		public int getCorner(ItemStack itemStack) {
			if (itemStack.stackTagCompound == null) {
				return 0;
			}

			return itemStack.stackTagCompound.getInteger("Corner");

		}		
		public void setCorner(ItemStack itemStack, int Value) {
			if (itemStack.stackTagCompound == null) {
				itemStack.setTagCompound(new NBTTagCompound());
			}

			itemStack.stackTagCompound.setInteger("Corner", Value);			
		}
    
    //////////////////////////////////////////////////////////////
   
		/** should make an complex interface like this to cut down on space, using string tree to call... Zzzzz
		public int getTMeta(ItemStack itemStack, int Nbtcount, String get)
		{
			if(itemStack.stackTagCompound == null)
			{
				return 0;
			}

			return itemStack.stackTagCompound.getInteger("TMeta" + Nbtcount);
			
		}
		**/
	
	//Generic tool stuff
	public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        if ((double)p_150894_3_.getBlockHardness(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D)
        {
            p_150894_1_.damageItem(2, p_150894_7_);
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
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Flush Mode", new Object[0]);
				((EntityPlayer) entityLiving).addChatComponentMessage(chatcomponenttranslation);
				setMode(stack,2);
				return true;
			}
			else if (getMode(stack) == 2)
			{			
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(EnumChatFormatting.GRAY + "Submerged -1", new Object[0]);
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
    	float py = (this.growrand .nextFloat()) ;
    	int mOffset = ((((getMode(thestaff)/2)*-1) +1));
    	
    	if (getOffMode(thestaff)== 0)
   		{
		   setOffMode(thestaff, 2);
   		}
    	
    	/**
    	//in game repair via fuel
    	if(theblock.getBlock(i1, j1, k1) == Helpertoolscore.MagicalFuelBlock
    			||theblock.getBlock(i1, j1, k1) == Helpertoolscore.ActiveMagicalFuelBlock){

			 int damage;
			//thestaff.setItemDamage(0);
			 damage = thestaff.getItemDamage();
			 ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
						"Damage is? : " + (damage), new Object[0]);
				((EntityPlayer) theplayer)
						.addChatComponentMessage(chatcomponenttranslation);
				if (damage > 8){
					thestaff.setItemDamage(damage - 1);					
					theblock.setBlock(i1, j1, k1, Blocks.air);
					
					float py = (this.growrand .nextFloat()) ;
					theplayer.worldObj.playSoundAtEntity(theplayer, "mob.endermen.portal", 1.2F, .8F+py);
					//theblock.spawnEntityInWorld(new EntityLightningBolt(theblock,i1, j1, k1));
					for (int l = 0; l < 64; ++l){
						 float f = (this.growrand.nextFloat()/5F) ;
				           //float f1 = (this.growrand .nextFloat()/1 )-.5F;
				           float f1 = (this.growrand .nextFloat()/5F );
				           float f2 = (this.growrand .nextFloat()/5F );
						//theblock.spawnParticle("magicCrit", i1+f1*1.4+.1, j1+f1*1.4+.5, k1+f2*1.2+.1, f*3, f1*3, f2*3);
						theblock.spawnParticle("reddust", i1+f1*5+.1, j1+f1*5+.5, k1+f2*5+.1, 30, f1*30, 30);
						//this.worldObj.spawnParticle("magicCrit", finX+f+.2, finY+.5+f1, finZ+f2+.3, 0, 0, 0);
					}
					
				}
				return true;
    	}
    	**/
    	
    	
    	/////////////////////////////
    	/** placement and get code**/
    	////////////////////////////
    	int successful = 0;
    	int P = 5;
    	int proxyskip = 0;
    	if (!theplayer.isSneaking()){
    		
    		//placement via transcriber proxy
    		if(theblock.getBlock(i1, j1, k1) == Helpertoolscore.TranscriberBlock){
    			TileEntityTranscriber tile = (TileEntityTranscriber)theblock.getTileEntity(i1, j1, k1);
    			if (tile != null)
                {

    				//ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
    				//		"Tile Detected" + (tile).offX, new Object[0]);
    				//((EntityPlayer) theplayer)
    				//		.addChatComponentMessage(chatcomponenttranslation);
    				
    				i1 = i1 -2 + (tile.offX);
    				j1 = j1 -1 + (tile.offY);
    				k1 = k1 -2 + (tile.offZ);
    				proxyskip = 1;
                }
    		}
    		
    		//placement via staff
    		if(theblock.getBlock(i1, j1, k1) != Helpertoolscore.TranscriberBlock
    				&& proxyskip == 0){
    			    		
    		//dynamic placement offsets
        	//W/E_T/B_N/S
    			
        	if (theface == 0){    	
        		j1 = j1 - mOffset -6;
        		i1 = i1 - 2;
        		k1 = k1 - 2;
            	}
        	if (theface == 1){    	
        		j1 = mOffset + j1;
        		i1 = i1 - 2;
        		k1 = k1 - 2;
        	}
        	//North south
        	if (theface == 2){    	
        		j1 = j1-3;
        		i1 = i1 - 2;
        		k1 = k1 - 5 -mOffset;
            	}
        	if (theface == 3){    	
        		j1 = j1-3;
        		i1 = i1 - 2;
        		k1 = k1 +1 +mOffset;
            	}
        	//West East
        	if (theface == 4){    	
        		j1 = j1-3;
        		i1 = i1 - 5 -mOffset;
        		k1 = k1 -2 ;
            	}
        	if (theface == 5){    	
        		j1 = j1-3;
        		i1 = i1 +1 +mOffset;
        		k1 = k1 -2 ;
            	}
        	
    		}
    		
    	for (int G2 = 0; G2 < P; ++G2)
        {
    		int G2counter = G2*P*P;
    		for (int U = 0; U < P; ++U)
    		{
    			int Ucounter = U*P;
    			for (int l = 0; l < P; ++l)
    			{
    				int Nbtcounter = G2counter+Ucounter+l+1;
    				
    				int X_1 = i1+U;
    				int Y_1 = j1+1+l;
    				int Z_1 = k1+G2;
    				
    	    		
    	    		
    				/**
    				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
							"Counter is? : " + (Nbtcounter), new Object[0]);
					((EntityPlayer) theplayer)
							.addChatComponentMessage(chatcomponenttranslation);
    				**/
    				if (returnTBlock(thestaff, Nbtcounter) != Blocks.air){
    				
    					/** displacement whitelist **/
    				if (theblock.isAirBlock(X_1 , Y_1 , Z_1 )
    						|| theblock.getBlock(X_1 , Y_1 , Z_1 ).getMaterial() == Material.lava 
    						|| theblock.getBlock(X_1 , Y_1 , Z_1 ).getMaterial() == Material.water
    						|| theblock.getBlock(X_1 , Y_1 , Z_1 ).getMaterial() == Material.plants 
    						|| theblock.getBlock(X_1 , Y_1 , Z_1 ).getMaterial() == Material.vine )
    				{
    					ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff, Nbtcounter)),0, returnTMeta(thestaff, Nbtcounter)); 
    					//stacky = new ItemStack (Item.getItemFromBlock(Blocks.dirt), 0,0);
    					//if (theplayer.capabilities.isCreativeMode|| theplayer.inventory.hasItem(Item.getItemFromBlock(returnTBlock(thestaff, Nbtcounter)))
    					if (theplayer.capabilities.isCreativeMode|| theplayer.inventory.hasItemStack(stacky)
    							){
    					//theblock.playSoundEffect((double)((float)X_1  + 0.5F), (double)((float)Y_1  + 0.5F), (double)((float)Z_1  + 0.5F), returnTBlock(thestaff, Nbtcounter).stepSound.getStepResourcePath(), (returnTBlock(thestaff, Nbtcounter).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff, Nbtcounter).stepSound.getPitch() * 0.8F);
    						/** plants reinbursement **/ /**Having to work around blocks like this isn't fun **/
    						if (theblock.getBlock(X_1 , Y_1 , Z_1 ).getMaterial() == Material.vine
    	    						|| theblock.getBlock(X_1 , Y_1 , Z_1 ).getMaterial() == Material.plants) 
    						{
    							(theblock.getBlock(X_1 , Y_1 , Z_1 )).dropBlockAsItem(theblock,X_1 , Y_1 , Z_1 , (theblock.getBlockMetadata(X_1 , Y_1 , Z_1 )), 0);
    						}
    						theblock.setBlock(X_1 , Y_1 , Z_1 , Blocks.dirt);
    						
    					theblock.setBlock(X_1 , Y_1 , Z_1 , returnTBlock(thestaff, Nbtcounter), (returnTMeta(thestaff, Nbtcounter)), 0);
    					successful = 1;
    					short short1 = 32;
    					for (int lp = 0; lp < short1; ++lp)
    		            {
    		                double d6 = (double)lp / ((double)short1 - 1.0D);
    		                /*
    		                float f = (this.growrand.nextFloat() - 1.2F) * 0.2F;
    		                float f1 = (this.growrand .nextFloat() - 1.2F) * 0.2F;
    		                float f2 = (this.growrand .nextFloat() - 1.2F) * 0.2F;
    		                */
    		                float f = (this.growrand.nextFloat() - .5F) * 1.4F;
    		                float f1 = (this.growrand .nextFloat() - .5F) * 1.4F;
    		                float f2 = (this.growrand .nextFloat() - .5F) * 1.4F;
    		                
    		                float p = (this.growrand.nextFloat()) ;
    		                float p1 = (this.growrand .nextFloat() ) ;
    		                float p2 = (this.growrand .nextFloat() ) ;
    		                //double d7 = d3 + (this.posX - d3) * d6 + (this.growrand .nextDouble() - 0.5D) * (double)this.width * 2.0D;
    		                //double d8 = d4 + (this.posY - d4) * d6 + this.growrand .nextDouble() * (double)this.height;
    		                //double d9 = d5 + (this.posZ - d5) * d6 + (this.growrand .nextDouble() - 0.5D) * (double)this.width * 2.0D;
    		                theblock.spawnParticle("portal", X_1 +p+.1, j1+.6+l+p1, Z_1 +p2+.1, f, f1, f2);
    		                
    		                //int crackid = getIdFromItem(Helpertoolscore.euclideantransposer);
    		                //int crackid2 = getIdFromItem(getItemFromBlock(Helpertoolscore.LooseDirtBlock));
    		                //int crackid3 = getIdFromItem(getItemFromBlock(returnTBlock(thestaff, Nbtcounter)));
    		                //theblock.spawnParticle("iconcrack_" + (crackid3), X_1 +p+.1, j1+.6+l+p1, Z_1 +p2+.1, f, f1, f2);
    		                //theblock.spawnParticle("iconcrack_" + (crackid3), X_1 +p+.1, j1+.6+l+p1, Z_1 +p2+.1, 0, 2, 0);
    		                
    		               // theblock.spawnParticle("tilecrack_" + (Blocks.anvil), X_1 +p+.1, j1+.6+l+p1, Z_1 +p2+.1, f, f1, f2);
    		                
    		            }
    					
    					if (!theplayer.capabilities.isCreativeMode){
    						//theplayer.inventory.consumeInventoryItem(Item.getItemFromBlock(returnTBlock(thestaff, Nbtcounter)));	
    						//stacky = new ItemStack (Item.getItemFromBlock(Blocks.dirt), 0,0); 
    						InventoryUtil.consumeInventoryItemStack(stacky, theplayer.inventory);
    						 thestaff.damageItem(1, theplayer);
    					}
    					}
    					
        
    				}
    			}
    			
    				}
    			}
    		
    		
        }
    	if (successful == 1){
			
    		
			theplayer.worldObj.playSoundAtEntity(theplayer, "mob.endermen.portal", 1.2F, .5F+py);
			theplayer.worldObj.playSoundAtEntity(theplayer, "mob.endermen.portal", 1.7F, .5F+py);
			successful = 0;
			return true;
		}
		if (successful ==0){
			theplayer.worldObj.playSoundAtEntity(theplayer, "random.click",.4F, itemRand.nextFloat() * 0.4F + 0.5F);
			successful = 0;
			return true;
		}
    	}
    	
    	/////////////////////////////
    	/** Pattern Collection **/
    	if (theplayer.isSneaking()){
    		
    		if(theblock.getBlock(i1, j1, k1) == Helpertoolscore.TranscriberBlock){
    			TileEntityTranscriber tile = (TileEntityTranscriber)theblock.getTileEntity(i1, j1, k1);
    			if (tile != null)
                {

    				
    				//theplayer.worldObj.playSoundAtEntity(theplayer, "mob.ghast.fireball", 1.2F, .2F+py/5);
    				
    				i1 = i1 + (tile.offX);
    				j1 = j1 + (tile.offY);
    				k1 = k1 + (tile.offZ);
                }
    		}
    		
    		
    		i1 = i1 - 2;
    		k1 = k1 - 2;
    		/** Z **/
    		for (int G2 = 0; G2 < P; ++G2)
            {
        		int G2counter = G2*P*P;
        		/** X **/
        		for (int U = 0; U < P; ++U)
        		{
        			int Ucounter = U*P;
        			/** Y **/
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
    		if(!theplayer.worldObj.isRemote){
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
						EnumChatFormatting.GRAY + "Pattern Saved", new Object[0]);
				((EntityPlayer) theplayer)
						.addChatComponentMessage(chatcomponenttranslation);
    		theplayer.worldObj.playSoundAtEntity(theplayer, "mob.ghast.fireball", 1.5F, .2F+py/4);
    		//theblock.playSoundEffect((double)i1 + 0.5D, (double)j1 + 0.5D, 
    		//		(double)k1 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
    		}
    		setOffMode(thestaff, 4);
    		return true;
    		
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