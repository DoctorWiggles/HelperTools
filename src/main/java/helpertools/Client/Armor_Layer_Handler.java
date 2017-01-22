package helpertools.Client;

import helpertools.Client.Armor_Render_Handler_old.RenderType;
import helpertools.Com.Items.Item_MirageHusk;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

//Adapted from Botania baubles again :^)
public final class Armor_Layer_Handler implements LayerRenderer<EntityPlayer> {

		static RenderItem Item = Minecraft.getMinecraft().getRenderItem();
		@Override
		public void doRenderLayer(@Nonnull EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
			if(player.getActivePotionEffect(MobEffects.INVISIBILITY) != null)
				return;

			ItemStack helm = player.inventory.armorItemInSlot(3);

			if(helm != null && helm.getItem() instanceof Item_MirageHusk){			

				float yaw = player.prevRotationYawHead + (player.rotationYawHead - player.prevRotationYawHead) * partialTicks;
				float pitch = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * partialTicks;
				float yawOffset = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
				
				GlStateManager.pushMatrix();
				SneakingOffset(player);
				GlStateManager.rotate(yawOffset, 0, -1, 0);
				GlStateManager.rotate(yaw - 270, 0, 1, 0);
				GlStateManager.rotate(pitch, 0, 0, 1);
				
				GlStateManager.rotate(90, 0, 1, 0);
				GlStateManager.rotate(180, 0, 0, 1);
				
				GlStateManager.translate(-0.02, 0.20, 0.35);
				float x = 0.92F;
				GL11.glScalef(x, x, x);
				
				
				Item_Player_Render(helm, RenderType.HEAD);
				GL11.glPopMatrix();
			}		
		}	

		public void Item_Player_Render(ItemStack stack, RenderType type) {
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);					

			this.Item.renderItem(stack, ItemCameraTransforms.TransformType.FIXED);		
		}

		public static void SneakingOffset(EntityPlayer player) {
			if(player.isSneaking()){
				GlStateManager.translate(0F, 0.2F, 0F);			
			}
				
		}

		@Override
		public boolean shouldCombineTextures() {
			return false;
		}

}
