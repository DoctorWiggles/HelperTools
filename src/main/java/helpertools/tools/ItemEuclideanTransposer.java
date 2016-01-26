package helpertools.tools;

import java.util.List;
import java.util.Random;

import helpertools.Mod_Registry;
import helpertools.HelpTab;
import helpertools.Main;
import helpertools.blocks.tile_entities.TileEntityTranscriber;
import helpertools.util.InventoryUtil;
import helpertools.util.Whitelist_Util;
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

public class ItemEuclideanTransposer extends ItemTool
{
    public ItemEuclideanTransposer(ToolMaterial material)
    {
    	super (2,material, Mod_Registry.properharvest);
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
    par3List.add(EnumChatFormatting.WHITE + "Sets blocks in a 5^cube pattern");
    par3List.add(EnumChatFormatting.ITALIC + "Use while sneaking for a pattern");
    par3List.add(" ");
    par3List.add(EnumChatFormatting.ITALIC + "Can also be used with");
    par3List.add(EnumChatFormatting.ITALIC + "- Transcriber Block");
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
    public boolean onItemUse(ItemStack thestaff, EntityPlayer player, World world, int i1, int j1, int k1, int theface, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	float py = (this.growrand .nextFloat()) ;
    	int mOffset = ((((getMode(thestaff)/2)*-1) +1));
    	
    	if (getOffMode(thestaff)== 0)
   		{
		   setOffMode(thestaff, 2);
   		}
    	
    	/////////////////////////////
    	/** placement and get code**/
    	////////////////////////////
    	int successful = 0;
    	int P = 5;
    	int proxyskip = 0;
    	if (!player.isSneaking()){
    		
    		
    		//placement via transcriber proxy
    		if(world.getBlock(i1, j1, k1) == Mod_Registry.TranscriberBlock){
    			TileEntityTranscriber tile = (TileEntityTranscriber)world.getTileEntity(i1, j1, k1);
    			if (tile != null)
                {

    				//ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
    				//		"Tile Detected" + (tile).offX, new Object[0]);
    				//((EntityPlayer) player)
    				//		.addChatComponentMessage(chatcomponenttranslation);
    				
    				i1 = i1 -2 + (tile.offX);
    				j1 = j1 -1 + (tile.offY);
    				k1 = k1 -2 + (tile.offZ);
    				proxyskip = 1;
                }
    		}
    		
    		//placement via staff
    		if(world.getBlock(i1, j1, k1) != Mod_Registry.TranscriberBlock
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
    				
    				if (returnTBlock(thestaff, Nbtcounter) != Blocks.air){
    				
    					/** displacement whitelist **/
    				if (world.isAirBlock(X_1 , Y_1 , Z_1 )
    						|| world.getBlock(X_1 , Y_1 , Z_1 ).getMaterial() == Material.lava 
    						|| world.getBlock(X_1 , Y_1 , Z_1 ).getMaterial() == Material.water
    						|| world.getBlock(X_1 , Y_1 , Z_1 ).getMaterial() == Material.plants 
    						|| world.getBlock(X_1 , Y_1 , Z_1 ).getMaterial() == Material.vine )
    				{
    					
    					
    					ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff, Nbtcounter)),0, returnTMeta(thestaff, Nbtcounter)); 
    					Boolean whitelist_flag;
    					whitelist_flag = Whitelist_Util.Block_Whitelist(returnTBlock(thestaff, Nbtcounter), player, returnTMeta(thestaff, Nbtcounter));
    					
    					if (player.capabilities.isCreativeMode|| player.inventory.hasItemStack(stacky)
    							||whitelist_flag
    							){
    					//world.playSoundEffect((double)((float)X_1  + 0.5F), (double)((float)Y_1  + 0.5F), (double)((float)Z_1  + 0.5F), returnTBlock(thestaff, Nbtcounter).stepSound.getStepResourcePath(), (returnTBlock(thestaff, Nbtcounter).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff, Nbtcounter).stepSound.getPitch() * 0.8F);
    						/** plants reinbursement **/ /**Having to work around blocks like this isn't fun **/
    						if (world.getBlock(X_1 , Y_1 , Z_1 ).getMaterial() == Material.vine
    	    						|| world.getBlock(X_1 , Y_1 , Z_1 ).getMaterial() == Material.plants) 
    						{
    							(world.getBlock(X_1 , Y_1 , Z_1 )).dropBlockAsItem(world,X_1 , Y_1 , Z_1 , (world.getBlockMetadata(X_1 , Y_1 , Z_1 )), 0);
    						}
    						world.setBlock(X_1 , Y_1 , Z_1 , Blocks.dirt);
    						
    					world.setBlock(X_1 , Y_1 , Z_1 , returnTBlock(thestaff, Nbtcounter), (returnTMeta(thestaff, Nbtcounter)), 0);
    					successful = 1;
    					short short1 = 32;
    					for (int lp = 0; lp < short1; ++lp)
    		            {
    		                double d6 = (double)lp / ((double)short1 - 1.0D);
    		                
    		                float f = (this.growrand.nextFloat() - .5F) * 1.4F;
    		                float f1 = (this.growrand .nextFloat() - .5F) * 1.4F;
    		                float f2 = (this.growrand .nextFloat() - .5F) * 1.4F;
    		                
    		                float p = (this.growrand.nextFloat()) ;
    		                float p1 = (this.growrand .nextFloat() ) ;
    		                float p2 = (this.growrand .nextFloat() ) ;
    		                world.spawnParticle("portal", X_1 +p+.1, j1+.6+l+p1, Z_1 +p2+.1, f, f1, f2);   
    		            }
    					
    					if (!player.capabilities.isCreativeMode){
    						if(!whitelist_flag)InventoryUtil.consumeInventoryItemStack(stacky, player.inventory); 
    		    			if(whitelist_flag){
    		    				Whitelist_Util.Consume_Whitelist(stacky, player, returnTBlock(thestaff, Nbtcounter), returnTMeta(thestaff, Nbtcounter));
    		    				//player.inventory.consumeInventoryItem(item);
    		    				}   	                        
    						 thestaff.damageItem(1, player);
    					}
    					}
    					
        
    						}
    					}
    			
    				}
    			}
    		
    		
        }
    	if (successful == 1){
			
    		
			player.worldObj.playSoundAtEntity(player, "mob.endermen.portal", 1.2F, .5F+py);
			player.worldObj.playSoundAtEntity(player, "mob.endermen.portal", 1.7F, .5F+py);
			successful = 0;
			return true;
		}
		if (successful ==0){
			player.worldObj.playSoundAtEntity(player, "random.click",.4F, itemRand.nextFloat() * 0.4F + 0.5F);
			successful = 0;
			return true;
		}
    	}
    	
    	/////////////////////////////
    	/** Pattern Collection **/
    	if (player.isSneaking()){
    		
    		if(world.getBlock(i1, j1, k1) == Mod_Registry.TranscriberBlock){
    			TileEntityTranscriber tile = (TileEntityTranscriber)world.getTileEntity(i1, j1, k1);
    			if (tile != null)
                {

    				
    				//player.worldObj.playSoundAtEntity(player, "mob.ghast.fireball", 1.2F, .2F+py/5);
    				
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
        				
        					setTBlock(thestaff, world.getBlock(i1+U, j1+l, k1+G2).getIdFromBlock(world.getBlock(i1+U, j1+l, k1+G2)), Nbtcounter);
        					setTMeta(thestaff,world.getBlockMetadata(i1+U, j1+l, k1+G2), Nbtcounter); 		
        					}
        			}
            }
    		if(!player.worldObj.isRemote){
				ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
						EnumChatFormatting.GRAY + "Pattern Saved", new Object[0]);
				((EntityPlayer) player)
						.addChatComponentMessage(chatcomponenttranslation);
    		player.worldObj.playSoundAtEntity(player, "mob.ghast.fireball", 1.5F, .2F+py/4);
    		}
    		setOffMode(thestaff, 4);
    		return true;
    		
    	}
    	return false;
    }

  
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    
    
}