package helpertools.Com.Entity.Renders;

import org.lwjgl.opengl.GL11;

import helpertools.Com.Entity.Phantom_Cube;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Render_PhantomCube extends Render<Phantom_Cube>
{
    public Render_PhantomCube(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0.0F;
    }

    
    public void doRender(Phantom_Cube entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        if (entity.getBlock() != null)
        {
            IBlockState iblockstate = entity.getBlock();

            if (iblockstate.getRenderType() == EnumBlockRenderType.MODEL)
            {
                World world = entity.getWorldObj();

                if (iblockstate != world.getBlockState(new BlockPos(entity)) && iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE)
                {
                    this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                    GlStateManager.pushMatrix();
                    GlStateManager.disableLighting();
                    Tessellator tessellator = Tessellator.getInstance();
                    VertexBuffer vertexbuffer = tessellator.getBuffer();
                    
                    
                    vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
                    BlockPos blockpos = new BlockPos(entity.posX, entity.getEntityBoundingBox().maxY, entity.posZ);
                    
                    
                    GlStateManager.translate(
                    		(float)(x - (double)blockpos.getX()), 
                    		(float)(y - (double)blockpos.getY()), 
                    		(float)(z - (double)blockpos.getZ()));
                    
                    
                    BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
                    blockrendererdispatcher.getBlockModelRenderer().
                    	renderModel(world, blockrendererdispatcher.getModelForState(iblockstate), iblockstate, blockpos,
                    	vertexbuffer,false, MathHelper.getPositionRandom(entity.getOrigin()));
                    tessellator.draw();
                    
                    GlStateManager.enableLighting();
                    GlStateManager.popMatrix();
                    
                }
            }
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Phantom_Cube entity)
    {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}