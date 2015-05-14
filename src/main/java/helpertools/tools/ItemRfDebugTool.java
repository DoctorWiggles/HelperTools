package helpertools.tools;

import java.util.List;
import java.util.Random;

import helpertools.HelpTab;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cofh.api.energy.ItemEnergyContainer;
//import helpertools.rfenergy.ItemEnergyContainer;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional.InterfaceList;
import cpw.mods.fml.common.Optional.Method;

//optional @ A&M&T Mod
@cpw.mods.fml.common.Optional.InterfaceList({@cpw.mods.fml.common.Optional.Interface(iface="cofh.api.energy.ItemEnergyContainer", modid="CoFHCore")})
public class ItemRfDebugTool extends ItemEnergyContainer {

	  public ItemRfDebugTool() {
	       super();
	       this.maxStackSize = 1;  
	       setUnlocalizedName("rfdebugtool");
	       setCreativeTab(HelpTab.HelperTools);
	       setTextureName("helpertools:RFToolTest");
	       
	       this.setCapacity(400);
	       this.setMaxReceive(50);
	       this.setMaxExtract(50);
	       
	       this.setMaxDamage(400);
	      
	       
	   }
	  
	  
	  /**
	  public int getDamage(ItemStack stack)
	    {
	        return getEnergyStored(stack);
	    }	  
	  public double getDurabilityForDisplay(ItemStack stack)
	    {
	        return (double)getEnergyStored(stack) / (double)getMaxEnergyStored(stack);
	    }
	   	**/
	  
	  public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		  
		  int damage = 400 - (getEnergyStored(stack));
		  
		  stack.setItemDamage(damage);		
		  
		  
		  return;
		  
	  }

	  
	  public String getItemStackDisplayName(ItemStack p_77653_1_)
	    {
		  return ("§4" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(p_77653_1_) + ".name")).trim();
	    }
	  
	  
	  @Override
		public void addInformation(ItemStack itemStack, EntityPlayer player,
				List list, boolean par4) {
			// //////////////////////////////////////////
			list.add("§7§oHow do rf");
			// ////////////////////////////////////////////

			
				int myenergy = getEnergyStored(itemStack);
				list.add(EnumChatFormatting.GREEN + "Energy is: " + myenergy);
		
		}
	  
	  public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
	       
		  this.receiveEnergy(itemStack, 50, false);
		  this.setDamage(itemStack, 350);
	        
	        //this.value = new Random().nextInt();
	        
	    }   
	  
	  public ItemStack onItemRightClick(ItemStack stack, World par2World,
				EntityPlayer entityLiving) {
			if (!entityLiving.worldObj.isRemote) {
				
				if(!entityLiving.isSneaking()){
					this.receiveEnergy(stack, 50, false);
				}
				if(entityLiving.isSneaking()){
					this.extractEnergy(stack, 25, false);
				}
				
				
			}
			return stack;
	  }
}
