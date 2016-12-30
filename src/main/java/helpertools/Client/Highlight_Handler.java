package helpertools.Client;

import java.util.HashSet;
import java.util.Set;

import org.lwjgl.opengl.GL11;

import helpertools.Com.Blocks.FloaterBlock.FloaterBlock_Item;
import helpertools.Utils.Texty;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Highlight_Handler {
	
	@SubscribeEvent
	public void Floater_Highlight(RenderWorldLastEvent evt) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.thePlayer;
		if (player.getHeldItemMainhand() != null) {

			ItemStack heldstack = player.getHeldItemMainhand();
			Item held = heldstack.getItem();

			if (held != null && held instanceof FloaterBlock_Item) {
				//TODO for Tools Set<BlockPos> positions = new HashSet<BlockPos>();

				World world = player.worldObj;

				if (!player.isSneaking()) {
					RayTraceResult mouseOver = Minecraft.getMinecraft().objectMouseOver;
					BlockPos pos = mouseOver.getBlockPos();
					IBlockState state = world.getBlockState(pos); 
					if(Texty.isValid(state, world, pos)){
						//positions.add(pos);
						Render_Outlines(evt, state, pos, 240, 180, 240, 2.5F);
					}
				}
			}

		}
	}
	
	public void Render_Outlines (RenderWorldLastEvent event, IBlockState state, BlockPos pos, int r, int g, int b, float f){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.thePlayer;
		
		GlStateManager.disableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GL11.glLineWidth(f);
            double partialTicks = event.getPartialTicks();
            double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
            double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
            double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

            AxisAlignedBB slightlyLargeBB = state.getSelectedBoundingBox(player.worldObj, pos)
                    .expand(-0.005, -0.005, -0.005)
                    .offset(-d0, -d1, -d2);
            RenderGlobal.func_189697_a(slightlyLargeBB, (float) r/255F, (float) g/255F, (float) b/255F, (float) 100/255F);
        
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.depthMask(false);
	}
	
	
}
