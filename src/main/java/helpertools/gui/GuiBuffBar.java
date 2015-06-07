package helpertools.gui;

import helpertools.tools.ItemStaffofExpansion;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

//
// GuiBuffBar implements a simple status bar at the top of the screen which
// shows the current buffs/debuffs applied to the character.
//
public class GuiBuffBar extends Gui
{
  private Minecraft mc;

  public GuiBuffBar(Minecraft mc)
  {
    super();
   
    // We need this to invoke the render engine.
    this.mc = mc;
  }

  private static final int BUFF_ICON_SIZE = 18;
  private static final int BUFF_ICON_SPACING = BUFF_ICON_SIZE + 2; // 2 pixels between buff icons
  private static final int BUFF_ICON_BASE_U_OFFSET = 0;
  private static final int BUFF_ICON_BASE_V_OFFSET = 198;
  private static final int BUFF_ICONS_PER_ROW = 8;
 
  //
  // This event is called by GuiIngameForge during each frame by
  // GuiIngameForge.pre() and GuiIngameForce.post().
  //
  
  //copypasta itemstack drawing
  //I literally grabbed random code from the minecraft engine
  //and hoped it worked
  protected static RenderItem itemRender = new RenderItem();
  protected static RenderBlocks blockRender = new RenderBlocks();
  protected FontRenderer fontRendererObj;
  
  private void drawItemStack(ItemStack itemstack, int X1, int Y1, String p_146982_4_)
  {
	  
      GL11.glTranslatef(0.0F, 0.0F, 32.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glDisable(GL11.GL_LIGHTING);  
      this.zLevel = 2.0F;
      itemRender.zLevel = 2.0F;
      FontRenderer font = null;
      //if (itemstack != null) font = itemstack.getItem().getFontRenderer(itemstack);
      //if (font == null) font = fontRendererObj;
      //itemRender.renderItemAndEffectIntoGUI(null, this.mc.getTextureManager(), itemstack, X1, Y1);      
      
      itemRender.renderItemIntoGUI(font, this.mc.getTextureManager(), itemstack, X1, Y1);
      
      //itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), itemstack, X1, Y1 );
      this.zLevel = 0.0F;
      itemRender.zLevel = 0.0F;
      
  }
  
  public static void drawPlayerModel(int par0, int par1, int par2, float par3, float par4, EntityLivingBase par5EntityLivingBase)

  {

	  GL11.glEnable(GL11.GL_COLOR_MATERIAL);

	  GL11.glPushMatrix();

	  GL11.glTranslatef((float)par0, (float)par1, 50.0F);

	  GL11.glScalef((float)(-par2), (float)par2, (float)par2);

	  GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);

	  float f2 = par5EntityLivingBase.renderYawOffset;

	  float f3 = par5EntityLivingBase.rotationYaw;

	  float f4 = par5EntityLivingBase.rotationPitch;

	  float f5 = par5EntityLivingBase.prevRotationYawHead;

	  float f6 = par5EntityLivingBase.rotationYawHead;

	  GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);

	  RenderHelper.enableStandardItemLighting();

	  GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);

	  GL11.glRotatef(-((float)Math.atan((double)(par4 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);

	  par5EntityLivingBase.renderYawOffset = (float)Math.atan((double)(par3 / 40.0F)) * 20.0F;

	  par5EntityLivingBase.rotationYaw = (float)Math.atan((double)(par3 / 40.0F)) * 40.0F;

	  par5EntityLivingBase.rotationPitch = -((float)Math.atan((double)(par4 / 40.0F))) * 20.0F;

	  par5EntityLivingBase.rotationYawHead = par5EntityLivingBase.rotationYaw;

	  par5EntityLivingBase.prevRotationYawHead = par5EntityLivingBase.rotationYaw;

	  GL11.glTranslatef(0.0F, par5EntityLivingBase.yOffset, 0.0F);

	  RenderManager.instance.playerViewY = 180.0F;

	  RenderManager.instance.renderEntityWithPosYaw(par5EntityLivingBase, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);

	  par5EntityLivingBase.renderYawOffset = f2;

	  par5EntityLivingBase.rotationYaw = f3;

	  par5EntityLivingBase.rotationPitch = f4;

	  par5EntityLivingBase.prevRotationYawHead = f5;

	  par5EntityLivingBase.rotationYawHead = f6;

	  GL11.glPopMatrix();

	  RenderHelper.disableStandardItemLighting();

	  GL11.glDisable(GL12.GL_RESCALE_NORMAL);

	  OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);

	  GL11.glDisable(GL11.GL_TEXTURE_2D);

	  OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

  }
 
  
  @SubscribeEvent(priority = EventPriority.NORMAL)
  public void onRenderExperienceBar(RenderGameOverlayEvent event)
  {
    //
    // We draw after the ExperienceBar has drawn.  The event raised by GuiIngameForge.pre()
    // will return true from isCancelable.  If you call event.setCanceled(true) in
    // that case, the portion of rendering which this event represents will be canceled.
    // We want to draw *after* the experience bar is drawn, so we make sure isCancelable() returns
    // false and that the eventType represents the ExperienceBar event.
    if(event.isCancelable() || event.type != ElementType.EXPERIENCE)
    {      
      return;
    }
    
    
    

    // Starting position for the buff bar - 2 pixels from the top left corner.
    int xPos = 2;
    int yPos = 2;
    /**
    Collection collection = this.mc.thePlayer.getActivePotionEffects();
    if (!collection.isEmpty())
    {
    **/
      //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      //GL11.glDisable(GL11.GL_LIGHTING);      
      //Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("helpertools", "textures/models/Staff6.png")); 
      //this.mc.renderEngine.bindTexture("/gui/inventory.png"); 
      //IIcon giraffe = (Blocks.dirt).getIcon(1, 0);
      //Minecraft.getMinecraft().renderEngine.getTexture(giraffe); 
      //Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("/gui/container/inventory.png")); 
      
      ItemStack heldItem = this.mc.thePlayer.inventory.getCurrentItem();
		 if ((heldItem == null) || (!(heldItem.getItem() instanceof ItemStaffofExpansion))) {
		      return;
		    }
		 //ItemStack stacky = new ItemStack (Item.getItemFromBlock(returnTBlock(thestaff)),0, returnTMeta(thestaff)); 
		 //ItemStack StackyHelper = new ItemStack (Item.getItemFromBlock(Blocks.dirt));
		 
		 ItemStaffofExpansion  Tool = (ItemStaffofExpansion)heldItem.getItem();
		 //ItemStack stacky = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock(heldItem)),0, Tool.returnTMeta(heldItem));
		 ItemStack StackyHelper = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock(heldItem)),0, Tool.returnTMeta(heldItem));
		 //ItemStack StackyHelper = ItemStaffofExpansion.HudHook;
		 
		 
		//Most techinal blocks don't derive and item stack from themselves, returning a null pointer
	      //and making me exploder, catching it here and setting the texture to use a ? placeholder
		 
		 //if ( Tool.get_HudHookBoolean(heldItem) == 1) {
		//	 System.out.println(" " +Tool.get_HudHookBoolean(heldItem) );
	      try{
		 this.drawItemStack(StackyHelper, xPos+20, yPos+20, (String)null);
	      }
	      catch(NullPointerException exception){
	    	 // System.out.println("Erra!");	    	 
	    	  //Tool.set_HudHookBoolean(heldItem, 0);
	     
	         }
		 //}
	      
		 //IIcon giraffe = (Blocks.dirt).getIcon(1, 0);
		 IIcon giraffe = (Tool.returnTBlock(heldItem)).getIcon(4, Tool.returnTMeta(heldItem));
		 
		 //this.drawTexturedModelRectFromIcon(xPos+36, yPos+20, giraffe, 16, 16);
		 
		 //RenderItem.renderItemIntoGUI(null, this.mc.getTextureManager(), StackyHelper, xPos+36, yPos+36);

		 /** **/
		 //drawPlayerModel(xPos + 51, yPos + 75, 30, (float)(xPos + 51) - 64, (float)(yPos + 75 - 50) - 64, this.mc.thePlayer);
		 
		 //blockRender.renderBlockAsItem(Tool.returnTBlock(heldItem),Tool.returnTMeta(heldItem),10F);
		 
		 ResourceLocation backgroundimage = new ResourceLocation("helpertools" + ":" + "textures/client/gui/DemoTab.png");
		  
		  int xSize = 250;
	      int ySize = 240;
	      
	      
	      GL11.glPushMatrix();
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		        this.mc.getTextureManager().bindTexture(backgroundimage);
		        int x = (20 - xSize) / 2;
		        int y = (20 - ySize) / 2;
		       
		       
		        
		        GL11.glScalef(.18F,.1F,.1F); 
		        GL11.glScalef(.8F,.80F,0F); 

		        GL11.glTranslatef(120F, 230F, 0.0F);
		        this.drawTexturedModalRect(xPos+20, yPos+20, 0, 0, xSize,  ySize);
		        
		        

		        GL11.glPopMatrix();
		 
		 
      /**
      for (Iterator iterator = this.mc.thePlayer.getActivePotionEffects()
          .iterator(); iterator.hasNext(); xPos += BUFF_ICON_SPACING)
      {
        PotionEffect potioneffect = (PotionEffect) iterator.next();
        Potion potion = Potion.potionTypes[potioneffect.getPotionID()];

        if (potion.hasStatusIcon())
        {
          int iconIndex = potion.getStatusIconIndex();
          this.drawTexturedModalRect(
              xPos, yPos,
              BUFF_ICON_BASE_U_OFFSET + iconIndex % BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE,
              BUFF_ICON_BASE_V_OFFSET + iconIndex / BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE,
              BUFF_ICON_SIZE, BUFF_ICON_SIZE);
        }      
      }
      **/
    //}
  }
}