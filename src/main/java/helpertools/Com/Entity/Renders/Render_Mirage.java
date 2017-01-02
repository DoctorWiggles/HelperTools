package helpertools.Com.Entity.Renders;

import helpertools.Main;
import helpertools.Com.Entity.Entity_Mirage;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class Render_Mirage extends Render{

	
	private final Render_Player_Mirage steve_render;
	private final Render_Player_Mirage alex_render;
	
	public Render_Mirage(RenderManager renderManager) {
		super(renderManager);
		steve_render = new Render_Player_Mirage(renderManager, false);
		alex_render = new Render_Player_Mirage(renderManager, true);
	}
	

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
		
		if(is_Skinny(entity)){
			alex_render.doRender((EntityLiving)entity, x, y, z, yaw, partialTickTime);
			}
		else{steve_render.doRender((EntityLiving)entity, x, y, z, yaw, partialTickTime);}
	}
	
	public boolean is_Skinny(Entity entity){
		Entity_Mirage mob = (Entity_Mirage)entity;
		UUID targetID = mob.getplayerID();
		NetworkPlayerInfo info = Minecraft.getMinecraft().getConnection().getPlayerInfo(targetID);
		try {
			if (info.getSkinType()!= "default"){
				return true;
				}
		} catch (Exception e) {			
		}
		return false;
	}
	

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

	

	public class Render_Player_Mirage extends RenderBiped{
	
	public final ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/models/mirage.png");
	
	
	public Render_Player_Mirage(RenderManager renderManagerIn, boolean flag) {
		super(renderManagerIn, new ModelPlayer(0, flag), 0.5f);
		
		//this.addLayer(new LayerHeldItem(this));
		//this.addLayer(new LayerBipedArmor(this));
		this.addLayer(new LayerArrow(this));
	}
	
	
	
	public void doRender(EntityLiving entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
		GL11.glPushMatrix();			
		GL11.glTranslatef(0F, 0.05F, 0F);
		Float f = 0.92F;
		GL11.glScalef(f, f, f);
		GL11.glEnable(GL11.GL_BLEND); 
		//GL11.glBlendFunc(GL11.GL_ONE,GL11.GL_ONE); 
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		//GlStateManager.blendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
		//float alpha = (entity.getHealth()/40)+0.2F;
		float alpha = 0.5F;
		
		Float f2 = 0.5F;
		GL11.glColor4f(f2,f2, f2,alpha);  
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GL11.glDisable(GL11.GL_BLEND);
        
        GL11.glPopMatrix();
    }
	
	public ResourceLocation PlayerSkin(EntityLiving entity){
		
		Entity_Mirage mob = (Entity_Mirage)entity;
		UUID targetID = mob.getplayerID();
		NetworkPlayerInfo info = Minecraft.getMinecraft().getConnection().getPlayerInfo(targetID);
        return info == null ? DefaultPlayerSkin.getDefaultSkin(targetID) : info.getLocationSkin();
		
	}
	
	protected ResourceLocation getEntityTexture(EntityLiving entity){	
		
		ResourceLocation texture = this.texture;
		try{
			texture = PlayerSkin(entity);
		} catch (Exception e) {				
		}
    
        return texture;
    }
	}



}
