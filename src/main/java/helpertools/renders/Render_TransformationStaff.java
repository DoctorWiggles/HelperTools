package helpertools.renders;

import helpertools.models.Model_TransformationStaff;

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
public class Render_TransformationStaff implements IItemRenderer {

	private Model_TransformationStaff model;
		
	public Render_TransformationStaff()
	{
		
		model = new Model_TransformationStaff();
	}
	
	////////////////////////////////////////
	//Transformation staff model
	/////////////////////////////////////////
	
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
				GL11.glScalef(.1F,.1F,.1F);
				GL11.glRotatef(200, 0F,0F,20F);
				GL11.glTranslatef(-9F,0F,0F);
				GL11.glTranslatef(0F,-6.5F,0F);
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("helpertools", "textures/models/Staff6.png")); 
				
				model.renderModel(0.625F);
				//
				GL11.glPopMatrix();
				break;
		}
		case EQUIPPED_FIRST_PERSON:{
			
			GL11.glPushMatrix();
			
			GL11.glScalef(.1F,.1F,.1F);
			GL11.glRotatef(200, 0F,0F,20F);
			GL11.glTranslatef(-9F,0F,0F);
			GL11.glTranslatef(0F,-6.5F,0F);
			//
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("helpertools", "textures/models/Staff6.png")); 
			
			model.renderModel(0.625F);
			GL11.glPopMatrix();
			
			break;
			
		}
		default:
			break;
		}

	}

}
