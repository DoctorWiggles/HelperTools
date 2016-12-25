package helpertools.Common.Entity.Renders;

import helpertools.Common.Entity.Entity_Mirage;

import java.lang.reflect.Field;
import java.util.UUID;

import javax.annotation.Nullable;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class Render_Mirage extends RenderBiped{

	private static final ResourceLocation texture = new ResourceLocation("textures/entity/zombie/husk.png");
	
	
	
	public Render_Mirage(RenderManager renderManagerIn,
			ModelBiped modelBipedIn, float shadowSize) {
		super(renderManagerIn, modelBipedIn, shadowSize);
		
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerBipedArmor(this));
	}
	
	
	public void doRender(EntityLiving entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
		GL11.glPushMatrix();			
		GL11.glTranslatef(0F, 0.05F, 0F);
		Float f = 0.9F;
		GL11.glScalef(f, f, f);
		GL11.glEnable(GL11.GL_BLEND); 
		//GL11.glBlendFunc(GL11.GL_ONE,GL11.GL_ONE); 
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		//GlStateManager.blendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
		Float f2 = 0.5F;
		GL11.glColor4f(f2,f2, f2,0.5F);  
		//GlStateManager.color(f, f, f);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GL11.glDisable(GL11.GL_BLEND);
        
        GL11.glPopMatrix();
        if (!this.renderOutlines)
        {
            this.renderLeash(entity, x, y, z, entityYaw, partialTicks);
        }
    }
	
	public ResourceLocation PlayerSkin(EntityLiving entity){
		
		Entity_Mirage mob = (Entity_Mirage)entity;
		UUID targetID = mob.getplayerID();
		NetworkPlayerInfo info = Minecraft.getMinecraft().getConnection().getPlayerInfo(targetID);
		try {
			System.out.println(""+info.getSkinType());
		} catch (Exception e) {			
		}
		
        return info == null ? DefaultPlayerSkin.getDefaultSkin(targetID) : info.getLocationSkin();
		
	}
	
	protected ResourceLocation getEntityTexture(EntityLiving entity)
    {
        return PlayerSkin(entity);
    }

}
