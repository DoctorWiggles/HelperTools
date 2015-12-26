package helpertools.tools;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;

import helpertools.Common_Registry;
import helpertools.ConfigurationFactory;
import helpertools.HelpTab;
import helpertools.Helpertoolscore;
import helpertools.util.InventoryUtil;
import helpertools.util.KeyBindings;
import helpertools.util.Whitelist_Util;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkEvent;

public class ItemStaffofExpansion extends ItemTool
{
	
	
    public ItemStaffofExpansion(ToolMaterial material)
    {
    	super (1F, material, Common_Registry.properharvest);
        this.maxStackSize = 1;  
        setUnlocalizedName("staffofexpansion");
        //setCreativeTab(Helpertoolscore.HelperTools);
        setCreativeTab(HelpTab.HelperTools);
        setTextureName("helpertools:wand7");
    }
    
    protected static Random growrand = new Random();
    //flavor text
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	par3List.add(EnumChatFormatting.WHITE + "Sets blocks in the world");
        par3List.add(EnumChatFormatting.ITALIC + "While sneaking change mode");
        par3List.add(EnumChatFormatting.ITALIC + "- Or select block to place");
        par3List.add(EnumChatFormatting.ITALIC + "While enchanted with efficiency");
        par3List.add(EnumChatFormatting.ITALIC + "- Press 'o' to toggle size");
        par3List.add(EnumChatFormatting.ITALIC + "");
    if(whatBlockString(stack) != "null" && whatModeString(stack)!= "null"){
    	par3List.add(whatBlockString(stack) + whatModeString(stack)+ " mode");
    }
    }
   
/////////////////////////////////////////////////////////////////////
  public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
  	
  	if(entity.worldObj.isRemote){
  		return;  	}
  	//Item item = entity.inventory.currentItem;
  	if (!(entity instanceof EntityPlayer)){
			return;
		}
  	if(((EntityPlayer) entity).getCurrentEquippedItem() == null){
  		return;
  		}
  		
  	 ItemStack item = ((EntityPlayer) entity).getCurrentEquippedItem();
  	 Item item2 = item.getItem();
  	if (!(item2 == this)){
  		return;
  	}  	
  	
		return;
  			
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
   
  	public String whatBlockString(ItemStack stack){  		
  		String modestring = "null";
  		
  		if (getTBlock(stack) == 0){
  			modestring = "null";
  			}  		
  			if (getTBlock(stack) != 0){
  			modestring = returnTBlock(stack).getLocalizedName() + " ";  			
  			return modestring;
  			} 
  		return modestring;
  		
  	};
 
    /////////////////////////////////////////////////////////////
  	public int getMode(ItemStack itemStack) {
		if (itemStack.stackTagCompound == null) {
			return 2;
		}
		if (itemStack.stackTagCompound.getInteger("mode") == 0){
			setMode(itemStack, 2); 
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
			//setTBlock(itemStack, 2);
			//return 2;
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
	
    
	/** Offmode here prevents getblock from double dipping into switch mode code because i suck**/
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
    
    /** Tool levels **/
		public int getToolLevel(ItemStack itemStack) {
			if (itemStack.stackTagCompound == null) {
				return 0;
			}

			return itemStack.stackTagCompound.getInteger("ToolLevel");

		}		
		public void setToolLevel(ItemStack itemStack, int Value) {
			if (itemStack.stackTagCompound == null) {
				itemStack.setTagCompound(new NBTTagCompound());
			}

			itemStack.stackTagCompound.setInteger("ToolLevel", Value);			
		}
		
		public void ToolEmpower(ItemStack itemStack, EntityLivingBase entityLiving){
			int Toolmax = EnchantmentHelper.getEnchantmentLevel(32, itemStack);
			int NextLevel = (getToolLevel(itemStack))+1;
			
			EntityPlayer Player = (EntityPlayer) entityLiving;
			if (Toolmax <5 && Player.capabilities.isCreativeMode){Toolmax = 5;}
			
			if(NextLevel>Toolmax){
				setToolLevel(itemStack,0);
				entityLiving.worldObj.playSoundAtEntity(entityLiving, "random.fizz", (float)(1), (float)(1.3));
			}
			if(NextLevel<=Toolmax){
				setToolLevel(itemStack,NextLevel);
				entityLiving.worldObj.playSoundAtEntity(entityLiving, "random.orb", (float)(.8), (float)( itemRand.nextFloat()*.75+.2));
			}
			 if(ConfigurationFactory.ToolPowerMesseges == true){	
				 String Messy = ("Rank: "+(getToolLevel(itemStack)));
					ChatComponentTranslation chatmessy = new ChatComponentTranslation(EnumChatFormatting.GRAY + Messy, new Object[0]);
					((EntityPlayer) entityLiving).addChatComponentMessage(chatmessy);
				    }
			//
			//System.out.println("Empowering!"+"  The level is... "+(getToolLevel(itemStack))); 
		}
		
		
		
		/** returns a rounded number for tool levels**/
		public int getEff2Level(ItemStack itemStack) {
			if (itemStack == null) {
				return 0;
			}
			//int eff = EnchantmentHelper.getEnchantmentLevel(32, itemStack);
			int eff = (getToolLevel(itemStack));
			
			if ((eff%2)!=0){
				//odd
				eff = eff-1;
			}
			eff = eff/2;
			if (eff <= 0){
				eff = 0;
			}
			return eff;

		}	
		
		
		
    
	//(theblock.getBlock(i1, j1+2, k1)).setTickRandomly(false);
   
	//tileEntity.setFacing((short)change);
	//tileBlock.setFacing((short)theface);
	 
	
	//Generic tool stuff
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
    {
        if ((double)block.getBlockHardness(world, x, y, z) != 0.0D)
        {
            stack.damageItem(1, living);
        }

        return true;
    }
	public boolean hitEntity(ItemStack stack, EntityLivingBase livingbase, EntityLivingBase living)
    {
        stack.damageItem(2, living);
        return true;
    }
	
	//Custom mode changing code
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
		
		
		if (getOffMode(stack)== 0)
   		{
		   setOffMode(stack, 2);
   		}
		if (!entityLiving.worldObj.isRemote) {
		if (entityLiving.isSneaking()&& getOffMode(stack)== 2)
    	{ 
			//Chat Messege and mode switcher
			setMode(stack, getMode(stack)+2);
			if (getMode(stack) > 6){
				setMode(stack, 2);
			}
			String Messy = "";
			double loud1 = 3;
			double loud2 = 0.3;
			
			switch(getMode(stack)){
			case 2: Messy = "Pillar Mode";
			break;
			case 4: Messy = "Wall Mode";
					loud1 = 0.3;
					loud2 = 3;
			break;
			case 6: Messy = "Matching Mode";
			break;
			default:
			break;
			}
			entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.chicken.plop", (float)(loud1), (float)(loud2));
			//config hook
		    if(ConfigurationFactory.ToolModeMesseges == true){		    
			ChatComponentTranslation chatmessy = new ChatComponentTranslation(EnumChatFormatting.GRAY + Messy, new Object[0]);
			((EntityPlayer) entityLiving).addChatComponentMessage(chatmessy);
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
	
	//Witelist and placement of blocks
	public void Placement (ItemStack thestaff, EntityPlayer player, World world, int x, int y, int z, int theface){
		
		//Whitelist Placement
		if (world.isAirBlock(x, y, z)
        		|| world.getBlock(x, y, z).getMaterial() == Material.lava 
        		|| world.getBlock(x, y, z).getMaterial() == Material.water
				|| world.getBlock(x, y, z).getMaterial() == Material.plants 
				|| world.getBlock(x, y, z).getMaterial() == Material.vine 
				|| world.getBlock(x, y, z) == Blocks.snow_layer)
        {
			
			ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff));  
			Item item = Item.getItemFromBlock(returnTBlock(thestaff));    
			Boolean whitelist_flag;
			whitelist_flag = Whitelist_Util.Block_Whitelist(returnTBlock(thestaff), player, returnTMeta(thestaff));
			
        	if(player.capabilities.isCreativeMode || player.inventory.hasItemStack(stacky )
        			|| whitelist_flag)
    		{
        		//destroys and returns blocks like grass
        		if (world.getBlock(x, y, z).getMaterial() == Material.vine
						|| world.getBlock(x, y, z).getMaterial() == Material.plants
						|| world.getBlock(x, y, z) == Blocks.snow_layer) 
				{
					(world.getBlock(x, y, z)).dropBlockAsItem(world,x, y, z, (world.getBlockMetadata(x, y, z)), 0);
				}
        		 world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), returnTBlock(thestaff).stepSound.getStepResourcePath(), (returnTBlock(thestaff).stepSound.getVolume() + 1.0F) / 2.0F, returnTBlock(thestaff).stepSound.getPitch() * 0.8F);
        		 world.setBlock(x, y, z, Blocks.cobblestone);
        		//world.setBlock(x, y, z, Blocks.air);
        		world.setBlock(x, y, z, (returnTBlock(thestaff)), (returnTMeta(thestaff)), 012);
        		
        		int crackid = (getTBlock(thestaff));
                int crackmeta = (returnTMeta(thestaff));
                String particle = "blockcrack_" + crackid + "_" + crackmeta;
        		for (int pl = 0; pl < 5; ++pl)
    			{
        		float f = (this.growrand.nextFloat() - .2F) * 1.4F;
                float f1 = (this.growrand .nextFloat() - .2F) * 1.4F;
                float f2 = (this.growrand .nextFloat() - .2F) * 1.4F;
        		world.spawnParticle(particle, x+f, y+f1+.3, z+f2, 0, 0, 0);        	            		
    			}
        		//successful = 1;
        		if (!player.capabilities.isCreativeMode){                	
        			if(!whitelist_flag)InventoryUtil.consumeInventoryItemStack(stacky, player.inventory); 
        			if(whitelist_flag){
        				Whitelist_Util.Consume_Whitelist(stacky, player, returnTBlock(thestaff), returnTMeta(thestaff));
        				//player.inventory.consumeInventoryItem(item);
        				}  
                    thestaff.damageItem(1, player);
                    }	
    			}
       
			   }
		
	}
	
	
		
	
	
	//The guts of the placement code
	/** This is a huge mess **/
	
	//Basically It will go through checks and determine what action it should perform next
	/**During this, it looks for Which mode -> Which face of the block 
	 * -> Which blocks are legal -> If they are legal, place or swap
	 * -> And finally depending on which gamemode to remove durability and items**/
    public boolean onItemUse(ItemStack thestaff, EntityPlayer theplayer, World theblock, int x1, int y1, int z1, int theface, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	//Modifies size based on tool level
    		
    	int pillar = (getToolLevel(thestaff)+ 3);
    	int wall = (getToolLevel(thestaff)+ 2); 
    	
    	//if operation is successful set a flag
    	//boolean successful = false;   	
    	
    	//////////////////////////////////////////////////////////////////////////////////

		//Pillar Mode 2
		/** if this is true it performs this action **/
    	//Checks to make sure you're not sneaking
    	
    	if (!theplayer.isSneaking()
    			&& getMode(thestaff) == 2)
    	{ 
    		//int successful = 0;
    	            //--z1; 
    	            //BOTTOM FACE
    	            //W/E_T/B_N/S
    		
        			for (int l = 0; l < pillar; ++l)
        			{        		
        				int x3 = 0;
        	            int y3 = 0;
        	            int z3 = 0;
        	    		//////////////////////////////////////
        	    		 switch(theface){
        	    		 //Bottom
        	    		 case 0:	y3 = y3 -1 - l; 			 
        	    			 break;
        	    		//Top
        	    		 case 1:	y3 = y3 +1 + l; 			 
        	    			 break;
        	    		//North
        	    		 case 2:	z3 = z3 -1 - l;
        	    			 break;
        	    		//South
        	    		 case 3:	z3 = z3 +1 + l; 
        	    			 break;
        	    		//West
        	    		 case 4:   	x3 = x3 -1 -l; 			 
        	    			 break;
        	    		//East
        	    		 case 5:	x3 = x3 +1 +l;  
        	    			 break;
        	    			 default:
        	    			 }
        				int x2 = x1 + x3;
        	            int y2 = y1 + y3;
        	            int z2 = z1 + z3;
        	            
        	            Placement(thestaff, theplayer, theblock, x2, y2, z2, theface);
        			}
        			
    	        		theblock.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
    	        				(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
    	        		return true;
    	           
    	            //East/west axis, Vertical axis, North/south axis
    	            //return true;
    	        }
    	///////////////////////////////////////////////////////////////////////////
    	/**    ~~~~~~~~                  Wall Mode               ~~~~~~~~~~~~~  **/
    	/////////////////////////////////////////////////////////////////////////
    	

    	if (!theplayer.isSneaking()
    			&& getMode(thestaff) == 4)
    	{ 
    		//int successful = 0;
    	            //--z1; 
    	            //BOTTOM FACE
    	            //W/E_T/B_N/S
    		
        			for (int l = 0; l < wall; ++l)
        			{   
        				for (int ml = 0; ml < wall; ++ml)
            			{ 
        					for (int sl = 0; sl < 4; ++sl)
                			{ 
        						int sectA =1;
        						int sectB =1;
        						switch(sl){
        						case 0:
        							break;
        						case 1: sectA= -1;       						
        							break;        							
        						case 2:	sectA= -1;
        								sectB= -1;
        							break;
        						case 3:	sectB= -1;
        							break;
        							
        						}
        						//z3 = z3 +offy - l*sectA; 
    	    			 		//x3 = x3 +offy - ml*sectB;
        					
        				//int offy = (wall-1)/2;
        				int x3 = 0;
        	            int y3 = 0;
        	            int z3 = 0;
        	    		//////////////////////////////////////
        	    		 switch(theface){
        	    		 //Bottom
        	    		 case 0:	z3 = z3 - l*sectA; 
	    			 				x3 = x3 - ml*sectB;        	    			 		
        	    			 break;
        	    		//Top
        	    		 case 1:	z3 = z3 - l*sectA; 
        	    			 		x3 = x3 - ml*sectB;
        	    			 		
        	    			 break;
        	    		//North
        	    		 case 2:	y3 = y3 - l*sectA; 
			 						x3 = x3 - ml*sectB; 
        	    			 break;
        	    		//South
        	    		 case 3:	y3 = y3 - l*sectA; 
	 								x3 = x3 - ml*sectB;
	 						break;
        	    		//West
        	    		 case 4:   y3 = y3 - l*sectA; 
	 							   z3 = z3 - ml*sectB;			 
        	    			 break;
        	    		//East
        	    		 case 5:	 y3 = y3 - l*sectA; 
						   			 z3 = z3 - ml*sectB;	
        	    			 break;
        	    			 default: 
        	    		 }
        				int x2 = x1 + x3;
        	            int y2 = y1 + y3;
        	            int z2 = z1 + z3;
        	            
        	            Placement(thestaff, theplayer, theblock, x2, y2, z2, theface);
                			}
        	            }
        			}
        			
    	            
    	        	theblock.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
    	        				(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
    	        		
    	        	return true;
    	          
    	        }
    	/////////////////////////////////////////////////////////////////////////
    	/** Matching Mode 6 **/
    	/////////////////////////////////////////////////////////////////////////
    	if (!theplayer.isSneaking()
    			&& getMode(thestaff) == 6)
    	{ 
    		//int successful = 0;
    	            //--z1; 
    	            //BOTTOM FACE
    	            //W/E_T/B_N/S
    		
        			for (int l = 0; l < wall; ++l)
        			{   
        				for (int ml = 0; ml < wall; ++ml)
            			{ 
        					for (int sl = 0; sl < 4; ++sl)
                			{ 
        						int sectA =1;
        						int sectB =1;
        						switch(sl){
        						case 0:
        							break;
        						case 1: sectA= -1;       						
        							break;        							
        						case 2:	sectA= -1;
        								sectB= -1;
        							break;
        						case 3:	sectB= -1;
        							break;
        							
        						}
        						//z3 = z3 +offy - l*sectA; 
    	    			 		//x3 = x3 +offy - ml*sectB;
        					
        				//int offy = (wall-1)/2;        				
        				int x3 = 0;
        	            int y3 = 0;
        	            int z3 = 0;
        	            //Matching block offset
        	            int x5 = 0;
        	            int y5 = 0;
        	            int z5 = 0; 
        	    		//////////////////////////////////////
        	    		 switch(theface){
        	    		 //Bottom
        	    		 case 0:	z3 = z3 - l*sectA; 
	    			 				x3 = x3 - ml*sectB; 
	    			 				y5 = y5 - 1;
        	    			 break;
        	    		//Top
        	    		 case 1:	z3 = z3 - l*sectA; 
        	    			 		x3 = x3 - ml*sectB;
        	    			 		y5 = y5 + 1;        	    			 		
        	    			 break;
        	    		//North
        	    		 case 2:	y3 = y3 - l*sectA; 
			 						x3 = x3 - ml*sectB; 
			 						z5 = z5 - 1;
        	    			 break;
        	    		//South
        	    		 case 3:	y3 = y3 - l*sectA; 
	 								x3 = x3 - ml*sectB;
	 								z5 = z5 + 1;
	 						break;
        	    		//West
        	    		 case 4:   y3 = y3 - l*sectA; 
	 							   z3 = z3 - ml*sectB;
	 							   x5 = x5 - 1;
        	    			 break;
        	    		//East
        	    		 case 5:	y3 = y3 - l*sectA; 
						   			z3 = z3 - ml*sectB;
						   			x5 = x5 + 1;
        	    			 break;
        	    			 default: 
        	    		 }
        	    		//Target Matching blocks
        				int x2 = x1 + x3;
        	            int y2 = y1 + y3;
        	            int z2 = z1 + z3;
        	            //Matching blocks to be placed
        	            int xT4 = x2 + x5;
        	            int yT4 = y2 + y5;
        	            int zT4 = z2 + z5;   
        	            if (theblock.getBlockMetadata(x2, y2, z2) == returnTMeta(thestaff) && 
        	            		theblock.getBlock(x2, y2, z2) == returnTBlock(thestaff)){
        	            
        	            Placement(thestaff, theplayer, theblock, xT4, yT4, zT4, theface);
                			}
                			}
        	            }
        			}
        			
    	            
    	        	theblock.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
    	        				(double)z1 + 0.5D, "fire.ignite", .4F, itemRand.nextFloat() * 0.4F + 0.8F);
    	        		
    	        	return true;
    	          
    	        }
        
        //////////////////////////
        if (!theplayer.canPlayerEdit(x1, y1, z1, theface, thestaff))
        {
            return false;
        }
        
    	
    	//////
    	if (theplayer.isSneaking())
    	{ 
    		setTBlock(thestaff, theblock.getBlock(x1, y1, z1).getIdFromBlock(theblock.getBlock(x1, y1, z1)));
    		setTMeta(thestaff,theblock.getBlockMetadata(x1, y1, z1)); 		
    		//
    		
    		ItemStack stacky2 = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff)); 
    		
    		
    		theblock.playSoundEffect((double)x1 + 0.5D, (double)y1 + 0.5D, 
    				(double)z1 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
    		
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

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    
    
}