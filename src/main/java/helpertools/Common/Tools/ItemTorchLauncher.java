package helpertools.Common.Tools;

import helpertools.Common.Entity.Renders.Models_Crossbow;
import helpertools.Utils.HelpTab;
import helpertools.Utils.Texty;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTorchLauncher extends ToolBase_Crossbow{

   public ItemTorchLauncher(ToolMaterial material, String unlocalizedName) {
       super(material);
       this.maxStackSize = 1;
       this.damageVsEntity = 3;
       this.attackSpeed = -2.0F;
       setUnlocalizedName(unlocalizedName);
       setCreativeTab(HelpTab.HelperTools);
       this.addPropertyOverride(new ResourceLocation("mode"), new IItemPropertyGetter()
       {
           @SideOnly(Side.CLIENT)
           public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn)
           {
        	   if(entityIn == null){
        	   		return 0.0F;
           	   	}
           		if(entityIn instanceof EntityPlayer && entityIn.getHeldItemMainhand() == stack
           			|| entityIn instanceof EntityPlayer && entityIn.getHeldItemOffhand() == stack){
        	   		if(getTload(stack)== 0){
        	   			return 0.0F;
        	   		}/*
        	   		switch(getMode(stack)){
        	   		case 0:	return 0.1F;				 
					case 1: return 0.2F;
					case 2: return 0.3F;
					case 3: return 0.4F;
					default: return 0.0F;
        	   		}
        	   		*/
        	   		return getMode(stack)+1;
           		}
       		 return 0.0F;}
       });
       
   }
   
   @Override
   public String getUnlocalizedName(ItemStack stack) {
	   String name= "Torch";
	   if (stack.hasTagCompound()) {
			name =  whatModeString(stack);}
	   
	   return super.getUnlocalizedName() + "_" + (name);
	   
      // return super.getUnlocalizedName() + "." + (stack.getItemDamage() == 0 ? "white" : "black");
   }
   
   @Override
   public void addInformation(ItemStack stack, EntityPlayer entity, List list, boolean par4)
   {
	   list.add(TextFormatting.WHITE + "Fires Torches & Arrows & Dynamite ");
	   list.add(TextFormatting.ITALIC + "While sneaking to toggle ammunition");
	   list.add(TextFormatting.ITALIC + "Right click to reload or Fire");
	   if (stack.hasTagCompound()){
		    if(whatModeString(stack) != "null"){
		    	list.add(whatModeString(stack)+ " mode");
		    	//list.add(getTload(stack)+ "");
		    	}}
   }
   
	
	//Tinker's Construct Stone Torch Support
	
	String TinkTorch = "TConstruct:decoration.stonetorch";
	//String TinkTorch = "HelperToolsID:SugarBlock";
	String Tcon = "TConstruct";
	//String Tcon = "HelperToolsID";
	/* TODO outdated tcon support
	public boolean StoneTorchSearch(EntityLivingBase entityLiving) {
		
		if(Loader.isModLoaded(Tcon))
		 {			
			 return ((EntityPlayer) entityLiving).inventory.hasItem(Item.getItemFromBlock(Block.getBlockFromName(TinkTorch)));
			 
			}
		 else
			return false;
			};
			
	public boolean StoneTorchConsume(EntityLivingBase entityLiving) {
		
		if(Loader.isModLoaded(Tcon))
		 {			
			 return ((EntityPlayer)entityLiving).inventory.consumeInventoryItem(Item.getItemFromBlock(Block.getBlockFromName(TinkTorch)));
					 
			}
		 else
			return false;
			};
	*/		
			
   @Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
   {
	   EntityPlayer player = (EntityPlayer) entityLiving;
	   World world = entityLiving.worldObj;
	   boolean flag = false;
	   if(!world.isRemote){
		  
		if (entityLiving.isSneaking() )
		{ 	
			if(Transfer_Mode(stack, world, player)){
				//return true;
				flag = true;
			}
	   }
	   }	
		 return flag;
   }
   
   @Override
   public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
   {
	   
	   if(player.worldObj.isRemote){
		   if(!player.isSneaking() && getTload(stack) ==2){
		   world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F));
		   }
	   		return new ActionResult(EnumActionResult.FAIL, stack);}
	   
	   if(!player.isSneaking() && getTload(stack) ==2){
		   //Firing function
		   crossbow_FIRE(stack, world, player);       	   
   		}
	   	if(player.isSneaking() && getTload(stack)== 0){
	   		//Loading function
	   		crossbow_LOAD(stack, world, player);
	   	}	  
	   	return new ActionResult(EnumActionResult.SUCCESS, stack);
   }
}
