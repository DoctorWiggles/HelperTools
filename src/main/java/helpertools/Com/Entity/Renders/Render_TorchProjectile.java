package helpertools.Com.Entity.Renders;

import helpertools.Com.Entity.Projectile_Torch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class Render_TorchProjectile extends Render
{
	private static final ResourceLocation arrowTextures = new ResourceLocation("helpertools", "textures/models/bolttexpmap.png"); 
	protected ModelBase Model_Bolt = new Model_BoltProjectile();
	
	public Render_TorchProjectile(RenderManager renderu)
    {
        super(renderu);
        this.shadowSize = 0.2F;
    }
	

    public void doRender(Projectile_Torch entity, double off_X, double off_Y, double off_Z, float fl_1, float fl_2)
    {
        this.bindEntityTexture(entity);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)off_X, (float)off_Y, (float)off_Z);
        GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * fl_2 - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * fl_2, 0.0F, 0.0F, 1.0F);
        //Tessellator tessellator = Tessellator.instance;
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
        /**
        float f11 = (float)entity.arrowShake - p_76986_9_;

        if (f11 > 0.0F)
        {
            float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
            GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
        }
         **/
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
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("helpertools", "textures/models/torchtexpmap.png"));  
        GL11.glRotatef(47F, 10F, 0.0F, 0.0F);
        GL11.glRotatef(89F, 0F, 10F, 0.0F);
        GL11.glScalef(25, 25, 25);
        //Model_BoltProjectile.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        Model_Bolt.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(Projectile_Torch ent)
    {
        return arrowTextures;
    }

    
    protected ResourceLocation getEntityTexture(Entity ent)
    {
        return this.getEntityTexture((Projectile_Torch)ent);
    }

    
    public void doRender(Entity entity, double off_X, double off_Y, double off_Z, float fl_1, float fl_2)
    {
        this.doRender((Projectile_Torch)entity, off_X, off_Y, off_Z, fl_1, fl_2);
    }
}