package helpertools.renders;

import helpertools.Mod_Registry;
import helpertools.Main;
import helpertools.entities.Entity_Extraction_Balloon;
import helpertools.entities.Entity_Extraction_Balloon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class Render_Extraction_Balloon extends Render
{
    private float field_77002_a;
    private static final String __OBFID = "CL_00000995";
	private static final ResourceLocation arrowTextures = new ResourceLocation("helpertools", "textures/blocks/balloon_block.png"); 


    public Render_Extraction_Balloon(float f1)
    {
        this.field_77002_a = f1;
    }

    //IIcon iicon = Mod_Registry.Balloon.getIcon(0, 0);
    
    public void doRender(Entity_Extraction_Balloon ent, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        GL11.glPushMatrix();
        this.bindEntityTexture(ent);
        GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_+0.5f, (float)p_76986_6_);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f2 = this.field_77002_a;
        GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
        
        //IIcon iicon = Mod_Registry.bomb.getIconFromDamage(0);
        IIcon iicon = Mod_Registry.extraction_balloon.getIconFromDamage(0);
        Tessellator tessellator = Tessellator.instance;
        float f3 = iicon.getMinU();
        float f4 = iicon.getMaxU();
        float f5 = iicon.getMinV();
        float f6 = iicon.getMaxV();
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV((double)(0.0F - f8), (double)(0.0F - f9), 0.0D, (double)f3, (double)f6);
        tessellator.addVertexWithUV((double)(f7 - f8), (double)(0.0F - f9), 0.0D, (double)f4, (double)f6);
        tessellator.addVertexWithUV((double)(f7 - f8), (double)(1.0F - f9), 0.0D, (double)f4, (double)f5);
        tessellator.addVertexWithUV((double)(0.0F - f8), (double)(1.0F - f9), 0.0D, (double)f3, (double)f5);
        tessellator.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }
    
    protected ResourceLocation getEntityTexture(Entity_Extraction_Balloon p_110775_1_)
    {
        return TextureMap.locationItemsTexture;
        //return arrowTextures;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity ent)
    {
        return this.getEntityTexture((Entity_Extraction_Balloon)ent);
    }

    public void doRender(Entity ent, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((Entity_Extraction_Balloon)ent, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}