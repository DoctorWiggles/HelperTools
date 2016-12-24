package helpertools.Client;

import java.util.List;

import helpertools.Common.ItemRegistry;
import helpertools.Common.Entity.Entity_FlyingItem;
import helpertools.Common.Items.Item_MirageHusk;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.opengl.GL11;

import com.google.common.eventbus.Subscribe;

/**Originally Derived from Botania Baubles**/
public class Armor_Render_Handler {

	static RenderItem Item = Minecraft.getMinecraft().getRenderItem();


	@SubscribeEvent(priority = EventPriority.LOW)
	public void onPlayerRender(RenderPlayerEvent.Post event) {

		EntityPlayer player = event.getEntityPlayer();
		ItemStack helm = player.inventory.armorItemInSlot(3);

		if(helm != null && helm.getItem() instanceof Item_MirageHusk){			

			float yaw = player.prevRotationYawHead + (player.rotationYawHead - player.prevRotationYawHead) * event.getPartialRenderTick();
			float pitch = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * event.getPartialRenderTick();

			GL11.glPushMatrix();			
			GL11.glTranslatef(0F, 1.4F, 0F);
			SneakingOffset(player);

			GL11.glRotatef(yaw, 0, -1, 0);
			GL11.glRotatef(pitch, 1f, 0, 0);	
			GL11.glTranslatef(-0.03F, 0.2F, 0.3F);
			GL11.glColor4f(1F, 1F, 1F, 1F);
			Scale(0.92F);

			Item_Player_Render(helm, RenderType.HEAD);
			GL11.glPopMatrix();
		}		
	}	

	public void Item_Player_Render(ItemStack stack, RenderType type) {
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);					

		this.Item.renderItem(stack, ItemCameraTransforms.TransformType.FIXED);		
	}

	public static void SneakingOffset(EntityPlayer player) {
		if(player.isSneaking())
			GL11.glTranslatef(0F, -0.25F, 0F);
	}
	
	public static void Scale(float x){
		GL11.glScalef(x, x, x);
	}

	public static enum RenderType {
		/**
		 * Render Type for the player's body, translations apply on the player's rotation.
		 * Sneaking is not handled and should be done during the render.
		 * @see IBaubleRender.Helper
		 */
		BODY,

		/**
		 * Render Type for the player's body, translations apply on the player's head rotations.
		 * Sneaking is not handled and should be done during the render.~
		 * @see IBaubleRender.Helper
		 */
		HEAD;
	}
}
