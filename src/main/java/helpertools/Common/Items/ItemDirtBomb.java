package helpertools.Common.Items;


import helpertools.Common.ItemRegistry;
import helpertools.Common.Entity.Entity_DirtBombProjectile;
import helpertools.Utils.HelpTab;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemDirtBomb extends Item {

	  public ItemDirtBomb(String unlocalizedName) {
	       super();
	       this.maxStackSize = 32;  
	       setUnlocalizedName("dirtbomb");
	       //setCreativeTab(Helpertoolscore.HelperTools);
	       setCreativeTab(HelpTab.HelperTools);
	       setUnlocalizedName(unlocalizedName);
	       
	   }
	  
	  
	  @Override
	   //TODO public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
	  /**
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
	           
	           float f = 6.0F;
       f = (f * f + f * 2.0F) / 3.0F;
       
       if( player.worldObj.isRemote){
    	   return stack;
       }
       if(!player.capabilities.isCreativeMode){--stack.stackSize;}
       //consumeInventoryItem(ItemRegistry.dirtbomb);
       
       //Entity_DirtBombProjectile Bomb = new Entity_DirtBombProjectile(world, player);
       world.spawnEntityInWorld(new Entity_DirtBombProjectile(world, player));
       //Entity_DirtBombProjectile Bomb = new Entity_DirtBombProjectile(world, player);
       		//Bomb.Type=2;
       		//world.spawnEntityInWorld(Bomb);
       		
	 
       		//world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
       		world.playSound(player, player.getPosition(), SoundEvents.entity_arrow_shoot, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
       		
       		return stack;
	   }
	  **/
	  public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	    {
		  float f = 6.0F;
	       f = (f * f + f * 2.0F) / 3.0F;
	       
	        if (!playerIn.capabilities.isCreativeMode)
	        {
	            --itemStackIn.stackSize;
	        }

	       
	        if (!worldIn.isRemote)
	        {
	        	Entity_DirtBombProjectile Bomb = new Entity_DirtBombProjectile(worldIn, playerIn);
	        	Bomb.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
	            worldIn.spawnEntityInWorld(Bomb);
	            //worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.entity_arrow_shoot, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	            worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT,
	            		SoundCategory.PLAYERS, .4F, itemRand.nextFloat() * 0.4F + 0.8F);
	        }

	        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	    }
}
