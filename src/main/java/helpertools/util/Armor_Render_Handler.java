package helpertools.util;

import helpertools.Common_Registry;
import helpertools.items.Armor_Mystic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.RenderPlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** Botania Bauble support code **/
public class Armor_Render_Handler {

	
	

	@SubscribeEvent
	public void onPlayerRender(RenderPlayerEvent.Specials.Post event) {
		
		EntityPlayer player = event.entityPlayer;
		ItemStack helm = player.inventory.armorItemInSlot(3);
		
		if(helm != null && helm.getItem() instanceof Armor_Mystic){
			

			float yaw = player.prevRotationYawHead + (player.rotationYawHead - player.prevRotationYawHead) * event.partialRenderTick;
			float yawOffset = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * event.partialRenderTick;
			float pitch = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * event.partialRenderTick;

			GL11.glPushMatrix();
			GL11.glRotatef(yawOffset, 0, -1, 0);
			GL11.glRotatef(yaw - 270, 0, 1, 0);
			GL11.glRotatef(pitch, 0, 0, 1);
			
			
			GL11.glPushMatrix();
			GL11.glColor4f(1F, 1F, 1F, 1F);
			Item_Player_Render(helm, event, RenderType.HEAD);
			GL11.glPopMatrix();
			
			GL11.glPopMatrix();
		}

		
		
	}
	
	public void Item_Player_Render(ItemStack stack, RenderPlayerEvent event, RenderType type) {
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
						
			Helper.translateToHeadLevel(event.entityPlayer);
			faceTranslate();
			//GL11.glTranslatef(-0.1F, -0.52F, 0F);
			
			//scale(0.75F);
			//GL11.glTranslatef(0.04F, -0.5F, 0F);
			
			scale(1.1F);
			GL11.glTranslatef(-0.14F, -0.52F, 0F);
			
			//renderIcon(Armor_Mystic.getIIcon);
			renderIcon(Common_Registry.mystic_mask.getIcon(stack, 0));
		
		
	}
	
	public void faceTranslate() {
		GL11.glRotatef(90F, 0F, 1F, 0F);
		GL11.glRotatef(180F, 1F, 0F, 0F);
		GL11.glTranslatef(-0.4F, 0.1F, -0.25F);
	}

	public void chestTranslate() {
		GL11.glRotatef(180F, 1F, 0F, 0F);
		GL11.glTranslatef(-0.5F, -0.7F, 0.15F);
	}

	public void scale(float f) {
		GL11.glScalef(f, f, f);
	}

	IIcon[] icons;
	
	public void renderIcon(int i) {
		IIcon icon = icons[i];
		float f = icon.getMinU();
		float f1 = icon.getMaxU();
		float f2 = icon.getMinV();
		float f3 = icon.getMaxV();
		ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 1F / 16F);
	}
	
	public void renderIcon(IIcon icon) {
		//IIcon icon = icons[i];
		float f = icon.getMinU();
		float f1 = icon.getMaxU();
		float f2 = icon.getMinV();
		float f3 = icon.getMaxV();
		ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 1F / 16F);
	}
	
	public static class Helper {

		public static void rotateIfSneaking(EntityPlayer player) {
			if(player.isSneaking())
				applySneakingRotation();
		}

		public static void applySneakingRotation() {
			GL11.glRotatef(28.64789F, 1.0F, 0.0F, 0.0F);
		}

		public static void translateToHeadLevel(EntityPlayer player) {
			GL11.glTranslated(0, (player != Minecraft.getMinecraft().thePlayer ? 1.62F : 0F) - player.getDefaultEyeHeight() + (player.isSneaking() ? 0.0625 : 0), 0);
		}

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
