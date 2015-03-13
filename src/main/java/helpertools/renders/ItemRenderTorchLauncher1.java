package helpertools.renders;

import helpertools.models.ModelTorchLauncher1;

import java.lang.ref.Reference;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.ForgeHooks;



//@SideOnly(Side.CLIENT)
public class ItemRenderTorchLauncher1 implements IItemRenderer {

	private ModelTorchLauncher1 ModelTorchLauncher1;
		
	public ItemRenderTorchLauncher1()
	{
		
		ModelTorchLauncher1 = new ModelTorchLauncher1();
	}
	
	
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		switch(type)
		{
		case EQUIPPED: return true;	
		//
		case EQUIPPED_FIRST_PERSON: return true;
		//	
		default: return false;
		}

	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {

		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		switch(type){
		
		case EQUIPPED:{
				GL11.glPushMatrix();
				//				
				GL11.glScalef(.12F,.12F,.12F);
				GL11.glRotatef(230, 0F,0F,20F);
				GL11.glRotatef(94, 0F,20F,0F);
				GL11.glRotatef(10, 20F,0F,0F);
				//GL11.glTranslatef(2.1F,0F,0F);
				GL11.glTranslatef(0F,.2F,0F);
				GL11.glTranslatef(.6F,0F,-6.6F);
				//GL11.glTranslatef(0F,-.5F,0F);
				
				//				
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("helpertools", "textures/models/TorchLauncherTextV7.png")); 
				//
				//Modelv5Staff4.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);
				//
				ModelTorchLauncher1.renderModel(item, 0.625F);
				//
				GL11.glPopMatrix();
				break;
		}
		case EQUIPPED_FIRST_PERSON:{
			
			GL11.glPushMatrix();
			
			//GL11.glScalef(.1F,.1F,.1F);
			//GL11.glRotatef(200, 0F,0F,20F);
			//GL11.glTranslatef(-9F,0F,0F);
			//GL11.glTranslatef(0F,-6.5F,0F);
			//
			GL11.glScalef(.20F,.20F,.20F);
			GL11.glRotatef(230, 0F,0F,20F);
			GL11.glRotatef(75, 0F,20F,0F);
			GL11.glRotatef(10, 10F,20F,0F);
			GL11.glRotatef(10, 20F,0F,0F);
			GL11.glTranslatef(2.1F,0F,0F);
			GL11.glTranslatef(0F,-0F,0F);
			GL11.glTranslatef(0F,0F,-4F);
			//
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("helpertools", "textures/models/TorchLauncherTextV7.png")); 
			//
			//Modelv5Staff4.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);
			//
			ModelTorchLauncher1.renderModel(item, 0.625F);
			//
			GL11.glPopMatrix();
			
			break;
			
		}
		default:
			break;
		}

	}

}
