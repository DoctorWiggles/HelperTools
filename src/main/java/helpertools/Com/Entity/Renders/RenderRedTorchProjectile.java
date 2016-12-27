package helpertools.Com.Entity.Renders;


import helpertools.Com.Entity.Entity_RedTorchProjectile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderRedTorchProjectile extends Render
{
	private static final ResourceLocation arrowTextures = new ResourceLocation("helpertools", "textures/models/Bolttexpmap.png"); 
	protected ModelBase Model_Bolt = new Model_BoltProjectile();
	
	public RenderRedTorchProjectile(RenderManager render)
    {
        super(render);
        this.shadowSize = 0.2F;
    }

	
	
    public void doRender(Entity_RedTorchProjectile entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.bindEntityTexture(entity);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
        GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * p_76986_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * p_76986_9_, 0.0F, 0.0F, 1.0F);
       
        byte b0 = 0;
        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = (float)(0 + b0 * 10) / 32.0F;
        float f5 = (float)(5 + b0 * 10) / 32.0F;
        float f6 = 0.0F;
        float f7 = 0.15625F;
        float f8 = (float)(5 + b0 * 10) / 32.0F;
        float f9 = (float)(10 + b0 * 10) / 32.0F;
        float f10 = 0.05625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(f10, f10, f10);
        GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
        GL11.glNormal3f(f10, 0.0F, 0.0F);
        GL11.glNormal3f(-f10, 0.0F, 0.0F);

        for (int i = 0; i < 4; ++i)
        {
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, f10);
        }
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("helpertools", "textures/models/TorchRedtextpmap.png"));  
        
        GL11.glRotatef(47F, 10F, 0.0F, 0.0F);
        GL11.glRotatef(89F, 0F, 10F, 0.0F);
        GL11.glScalef(25, 25, 25);
        Model_Bolt.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(Entity_RedTorchProjectile p_110775_1_)
    {
        return arrowTextures;
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((Entity_RedTorchProjectile)p_110775_1_);
    }

    public void doRender(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((Entity_RedTorchProjectile)entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}