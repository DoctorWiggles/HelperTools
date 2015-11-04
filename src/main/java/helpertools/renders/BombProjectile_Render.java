package helpertools.renders;

import helpertools.Common_Registry;
import helpertools.Helpertoolscore;
import helpertools.entities.BombProjectile_Entity;
import helpertools.entities.EntityDirtBombProjectile;
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
public class BombProjectile_Render extends Render
{
    private float field_77002_a;
    private static final String __OBFID = "CL_00000995";

    public BombProjectile_Render(float f1)
    {
        this.field_77002_a = f1;
    }

    
    public IIcon text (int type){    
    return Common_Registry.bomb.getIconFromDamage(type);	
    }
    
    //===============================================================================================//
    public void doRender(BombProjectile_Entity ent, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
    	 try{
        GL11.glPushMatrix();
        this.bindEntityTexture(ent);
        GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f2 = this.field_77002_a;
        GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
        
        //IIcon iicon = Common_Registry.dirtbomb.getIconFromDamage(0);        
        IIcon iicon = text(ent.read_type());
        
        
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
    	 catch(Exception exception){
			  System.out.println("------------- RENDERING BROKE ----------");
		 }
    }
    
    
    protected ResourceLocation getEntityTexture(BombProjectile_Entity p_110775_1_)
    {        return TextureMap.locationItemsTexture;         }

    protected ResourceLocation getEntityTexture(Entity ent)
    {        return this.getEntityTexture((BombProjectile_Entity)ent);    }
    
    public void doRender(Entity ent, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {        this.doRender((BombProjectile_Entity)ent, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);    }
}