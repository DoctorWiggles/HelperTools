package helpertools.items;

import java.util.Random;

import helpertools.HelpTab;
import helpertools.Main;
import helpertools.util.Text;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class Item_debug extends Item{

	public Item_debug(String name) {
	       super();
	       this.maxStackSize = 1;  
	       setUnlocalizedName(name);
	       setCreativeTab(HelpTab.HelperTools);
	       setTextureName("helpertools:RFToolTest");
	       
	   }
	
	public Random rand = new Random();
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		
		
		 
		if (player.worldObj.isRemote) {
			
			
			return stack;}
			
			NBTTagCompound tag = player.getEntityData();

			NBTBase modeTag = tag.getTag("MyInteger");
			if (modeTag != null) {
				
				//Text.out(player, "Current int:" + ((NBTTagInt)modeTag).data), EnumChatFormatting.AQUA);
				Text.out(player, "Current int:" + ((NBTTagInt)modeTag).func_150287_d(), EnumChatFormatting.AQUA);
			}

			if(player.isSneaking()){
			int asdf = 0;
			 asdf = rand.nextInt(150);
			tag.setInteger("MyInteger", asdf);
			
			//tag.setInteger("MyInteger", 20);
		
			}
		
		
		
		
		
		return stack;
		}
}
