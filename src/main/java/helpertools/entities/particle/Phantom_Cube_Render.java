package helpertools.entities.particle;

import helpertools.Mod_Registry;
import helpertools.handlers.Ghostblock_Handler;
import helpertools.util.Coordinate;

import java.util.HashSet;
import java.util.Set;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Phantom_Cube_Render extends Render
{
	
	private final RenderBlocks RB = new RenderBlocks();
	
    private float field_77002_a;
    private static final String __OBFID = "CL_00000995";

    public Phantom_Cube_Render(float f1)
    {
        this.field_77002_a = f1;
    }

    
    public void doRender(Phantom_Cube ent, double x, double y, double z, float p_76986_8_, float p_76986_9_)
    {	
    	World world = ent.worldObj;
    	
    	Block block = Blocks.dirt;
    	int meta = 0;
    	if(ent.block != Blocks.air){
    		block = ent.block;
    		meta = ent.meta;
    	}
        
        
        int i = MathHelper.floor_double(ent.posX);
        int j = MathHelper.floor_double(ent.posY);
        int k = MathHelper.floor_double(ent.posZ);
        
        Tessellator t = Tessellator.instance;
        GL11.glPushMatrix();
        
        //GL11.glEnable(GL11.GL_BLEND);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glDisable(GL11.GL_LIGHTING);
        
        GL11.glTranslatef((float)x, (float)y, (float)z);
        
        
        this.bindEntityTexture(ent);
      
        
        //t.setBrightness(15 << 20 | 15 << 4);
        this.RB.setRenderBoundsFromBlock(block); 
        
        
        if(block.getMaterial() == Material.leaves ||
        	block.getMaterial() == Material.vine){
        render_grass(block, world, i, j, k, meta);        
        }
        else if(block == Blocks.grass){
            render_grassBlock(block, world, i, j, k, meta);        
            }
        else{
        	this.RB.renderBlockSandFalling(block, world, i, j, k, meta);
        }
        
        
        GL11.glEnable(GL11.GL_LIGHTING);
        //GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
        
    }
    
    public void render_grass(Block block, World world, int p_147749_3_, int p_147749_4_, int p_147749_5_, int meta)
    {
        float f = 0.5F;
        
        float r = .6F;
        float g = .8F;
        float b = .4F;
        		
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        //tessellator.setBrightness(block.getMixedBrightnessForBlock(world, p_147749_3_, p_147749_4_, p_147749_5_));
        tessellator.setColorOpaque_F(r, g, b);
        this.RB.renderFaceYNeg(block, -0.5D, -0.5D, -0.5D, this.RB.getBlockIconFromSideAndMetadata(block, 0, meta));        
        tessellator.setColorOpaque_F(r, g, b);        
        this.RB.renderFaceYPos(block, -0.5D, -0.5D, -0.5D, this.RB.getBlockIconFromSideAndMetadata(block, 1, meta));
        tessellator.setColorOpaque_F(r, g, b); 
        this.RB.renderFaceZNeg(block, -0.5D, -0.5D, -0.5D, this.RB.getBlockIconFromSideAndMetadata(block, 2, meta));
        tessellator.setColorOpaque_F(r, g, b); 
        this.RB.renderFaceZPos(block, -0.5D, -0.5D, -0.5D, this.RB.getBlockIconFromSideAndMetadata(block, 3, meta));
        tessellator.setColorOpaque_F(r, g, b); 
        this.RB.renderFaceXNeg(block, -0.5D, -0.5D, -0.5D, this.RB.getBlockIconFromSideAndMetadata(block, 4, meta));
        tessellator.setColorOpaque_F(r, g, b); 
        this.RB.renderFaceXPos(block, -0.5D, -0.5D, -0.5D, this.RB.getBlockIconFromSideAndMetadata(block, 5, meta));
        tessellator.draw();
    }
    
    public void render_grassBlock(Block block, World world, int p_147749_3_, int p_147749_4_, int p_147749_5_, int meta)
    {
        float f = 0.5F;
        float f1 = 1.0F;
        float f2 = 0.8F;
        float f3 = 0.6F;
        
        float r = .6F;
        float g = .8F;
        float b = .4F;
        
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, p_147749_3_, p_147749_4_, p_147749_5_));
        tessellator.setColorOpaque_F(f, f, f);
        this.RB.renderFaceYNeg(block, -0.5D, -0.5D, -0.5D, this.RB.getBlockIconFromSideAndMetadata(block, 0, meta));
        tessellator.setColorOpaque_F(r,g,b);
        this.RB.renderFaceYPos(block, -0.5D, -0.5D, -0.5D, this.RB.getBlockIconFromSideAndMetadata(block, 1, meta));
        tessellator.setColorOpaque_F(f2, f2, f2);
        this.RB.renderFaceZNeg(block, -0.5D, -0.5D, -0.5D, this.RB.getBlockIconFromSideAndMetadata(block, 2, meta));
        tessellator.setColorOpaque_F(f2, f2, f2);
        this.RB.renderFaceZPos(block, -0.5D, -0.5D, -0.5D, this.RB.getBlockIconFromSideAndMetadata(block, 3, meta));
        tessellator.setColorOpaque_F(f3, f3, f3);
        this.RB.renderFaceXNeg(block, -0.5D, -0.5D, -0.5D, this.RB.getBlockIconFromSideAndMetadata(block, 4, meta));
        tessellator.setColorOpaque_F(f3, f3, f3);
        this.RB.renderFaceXPos(block, -0.5D, -0.5D, -0.5D, this.RB.getBlockIconFromSideAndMetadata(block, 5, meta));
        tessellator.draw();
    }
    
    protected ResourceLocation getEntityTexture(Phantom_Cube p_110775_1_)
    {      //  return TextureMap.locationItemsTexture;  
    		return TextureMap.locationBlocksTexture;
    }

    protected ResourceLocation getEntityTexture(Entity ent)
    {        return this.getEntityTexture((Phantom_Cube)ent);    }
    
    public void doRender(Entity ent, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {        this.doRender((Phantom_Cube)ent, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);    }
}