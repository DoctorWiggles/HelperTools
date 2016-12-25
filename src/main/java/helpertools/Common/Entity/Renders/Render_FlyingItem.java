package helpertools.Common.Entity.Renders;

import helpertools.Common.Entity.Entity_FlyingItem;
import helpertools.Common.Items.Item_MirageHusk;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Render_FlyingItem extends Render
{
    protected final Item item;
    private final RenderItem render;

    public Render_FlyingItem(RenderManager rendy, Item item, RenderItem render)
    {
        super(rendy);
        this.item = item;
        this.render = render;
    }

    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
        //GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        //GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(entity.rotationYaw, 0.0F, 1.0F, 0.0F);        
        
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        Entity_FlyingItem mob = (Entity_FlyingItem)entity;
        ItemStack stack = mob.getEntityItem();
        
        if(!(stack.getItem() instanceof Item_MirageHusk)){
        	float f = 0.9F;
        	stack = new ItemStack(stack.getItem(), 1, 0);
        	GlStateManager.scale(f, f, f);}
        
        this.render.renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
    }

    public ItemStack getitemstack(Entity p_177082_1_)
    {
        return new ItemStack(this.item, 1, 0);
    }
    
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}