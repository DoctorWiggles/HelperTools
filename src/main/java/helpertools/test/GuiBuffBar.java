package helpertools.test;

import helpertools.Helpertoolscore;
import helpertools.tools.ItemStaffofExpansion;

import java.awt.Point;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
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
/**http://www.minecraftforum.net/forums/mapping-and-modding/
 * minecraft-mods/modification-development/1420597-trying-to-render-an-item-within-a-gui**/
/**http://www.minecraftforge.net/wiki/Gui_Overlay **/

////////////////////////////////////
/** Old Code kept for reference 
 * Pretty much useless          **/
//////////////////////////////////


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
  protected  FontRenderer fontRendererObj;
  
  private void drawItemStack(ItemStack itemstack, int X1, int Y1, String p_146982_4_)
  {
	  
	  GL11.glPushMatrix();
	  GL11.glEnable(GL11.GL_BLEND);
	  GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);	 
      //GL11.glTranslatef(0.0F, 0.0F, 32.0F);
      //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      //RenderHelper.enableStandardItemLighting();
      //RenderHelper.enableGUIStandardItemLighting();
      //GL11.glDisable(GL11.GL_LIGHTING);     
	  //GL11.glEnable(GL11.GL_LIGHTING);
	  //GL11.glEnable(GL11.GL_DEPTH_TEST);
      itemRender.zLevel = 2.0F;
      FontRenderer font = null;
      //if (itemstack != null) font = itemstack.getItem().getFontRenderer(itemstack);
      //if (font == null) font = fontRendererObj;
      
      //itemRender.renderItemAndEffectIntoGUI(null, this.mc.getTextureManager(), itemstack, X1, Y1);      
      //GL11.glEnable(GL11.GL_BLEND);
      //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      itemRender.renderItemIntoGUI(font, this.mc.getTextureManager(), itemstack, X1, Y1);
      //ItemRenderer.renderItemIntoGUI(font, this.mc.getTextureManager(), itemstack, X1, Y1);
      //itemRender.renderItemAndEffectIntoGUI(font, this.mc.getTextureManager(), itemstack, X1, Y1);
      //itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), itemstack, X1, Y1);
      //GL11.glDisable(GL11.GL_BLEND);
      //GL11.glColor4f(0F, 0F, 0F, 0F);
      //RenderHelper.disableStandardItemLighting();     
      //itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), itemstack, X1, Y1 );
      //this.zLevel = 0.0F;
      itemRender.zLevel = 0.0F;
      //GL11.glDisable(GL11.GL_LIGHTING);
      //GL11.glDisable(GL11.GL_DEPTH_TEST);
      GL11.glDisable(GL11.GL_BLEND);
      GL11.glPopMatrix();
      
      /**
      IIcon icon = itemstack.getIconIndex();
      this.drawTexturedModelRectFromIcon(X1+36, Y1+20, icon, 16, 16);
      **/
  }

  private void drawHudFrame(int xPos, int yPos, ResourceLocation backgroundimage, ItemStack heldItem, ItemStaffofExpansion tool, int modo) {

      int xSize = 38+2;
      int ySize = 26+2;


      	GL11.glPushMatrix();
      	GL11.glColor4f(1.0F, 1.0F, 1.0F,1.0F);  
      	this.mc.getTextureManager().bindTexture(backgroundimage);
      	this.drawTexturedModalRect(xPos-1, yPos-1, 67-1, 118-1, xSize,  ySize);
      	GL11.glPopMatrix();
      	
      	GL11.glPushMatrix();
      	GL11.glEnable(GL11.GL_BLEND);
      	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      	//GL11.glColor4f(.2F, .2F, .2F,.4f); 
      	this.drawTexturedModalRect(xPos-1, yPos-1, 116-1, 118-1, xSize,  ySize);
      	GL11.glDisable(GL11.GL_BLEND);
      	GL11.glPopMatrix();
      	
      	
      	
	  
  }
  
  private void drawModeIcons(int xPos, int yPos, ResourceLocation backgroundimage, ItemStack heldItem, ItemStaffofExpansion tool, int modo) {
	  int xSize = 38+2;
      int ySize = 26+2;


      	GL11.glPushMatrix();
      	//GL11.glEnable(32826);
      	//GL11.glEnable(GL11.GL_BLEND);
      	GL11.glColor4f(1.0F, 1.0F, 1.0F,1.0F);      	
      	//GL11.glDepthFunc(1);
      	//GL11.glAlphaFunc(func, ref);
      	this.mc.getTextureManager().bindTexture(backgroundimage);
      	//GL11.glScalef(.18F,.1F,.1F); 
      	//GL11.glScalef(.8F,.80F,0F); 

      	//GL11.glTranslatef(120F, 225F, 0.0F);
      	this.drawTexturedModalRect(xPos-1, yPos-1, 16-1, 15+16*(modo)-1, xSize,  ySize);
      	//GL11.glDisable(32826);
      	//GL11.glDisable(GL11.GL_BLEND);
      	GL11.glPopMatrix();
	}
  
  private void drawEmpoweredBar(int xPos, int yPos, ResourceLocation Image, ItemStack heldItem, ItemStaffofExpansion tool, int Empowerment){
	  
      int xSize = Empowerment*7+1; 

      	GL11.glPushMatrix();
      	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      	this.mc.getTextureManager().bindTexture(Image);
      	this.drawTexturedModalRect(xPos+2, yPos+20, 68, 92, xSize,  3);
      	GL11.glPopMatrix();
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
    
    int xPos = 20;
    int yPos = 20;
    /**
    Collection collection = this.mc.thePlayer.getActivePotionEffects();
    if (!collection.isEmpty())
    {
    **/
      
      ItemStack heldItem = this.mc.thePlayer.inventory.getCurrentItem();
		 if ((heldItem == null) || (!(heldItem.getItem() instanceof ItemStaffofExpansion))) {
		      return;
		    }
		 
		 ItemStaffofExpansion  Tool = (ItemStaffofExpansion)heldItem.getItem();
		 //ItemStack stacky = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock(heldItem)),0, Tool.returnTMeta(heldItem));
		 ItemStack StackyHelper = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock(heldItem)),0, Tool.returnTMeta(heldItem));
		 //ItemStack StackyHelper = new ItemStack (Helpertoolscore.euclideantransposer,0, 0);		 
		  // System.out.println("Erra!");	    	 
	    	  //Tool.set_HudHookBoolean(heldItem, 0);

		 //IIcon giraffe = (Blocks.dirt).getIcon(1, 0);
		 //IIcon giraffe = (Tool.returnTBlock(heldItem)).getIcon(4, Tool.returnTMeta(heldItem));
		 
		 //this.drawTexturedModelRectFromIcon(xPos+36, yPos+20, giraffe, 16, 16);
		 
		 //RenderItem.renderItemIntoGUI(null, this.mc.getTextureManager(), StackyHelper, xPos+36, yPos+36);

		 /** **/
		 //drawPlayerModel(xPos + 51, yPos + 75, 30, (float)(xPos + 51) - 64, (float)(yPos + 75 - 50) - 64, this.mc.thePlayer);
		 
		 //blockRender.renderBlockAsItem(Tool.returnTBlock(heldItem),Tool.returnTMeta(heldItem),10F);
		
		 //ResourceLocation backgroundimage = new ResourceLocation("helpertools" + ":" + "textures/client/gui/DemoTab_" + Modo + ".png");
		 
		 ResourceLocation backgroundimage = new ResourceLocation("helpertools" + ":" + "textures/client/gui/DemoTab_21.png");
		 int Modo = Tool.getMode(heldItem);
		 
		 
		 /////////////////////////
		 /** Draw some Things **/
		 ///////////////////////
		 
		 try{
			 this.drawItemStack(StackyHelper, xPos+2, yPos+2, (String)null);
		      }
		      catch(NullPointerException exception){
		    	  GL11.glPopMatrix();
		      }
		 
		 drawHudFrame(xPos, yPos,backgroundimage, heldItem, Tool, Modo);
		 
		 drawModeIcons(xPos, yPos,backgroundimage, heldItem, Tool, Modo);
	      
	      	int Empowerment = Tool.getToolLevel(heldItem);
	      	if(Empowerment >0){
	      	drawEmpoweredBar(xPos, yPos,backgroundimage, heldItem, Tool, Empowerment);
	      	}


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