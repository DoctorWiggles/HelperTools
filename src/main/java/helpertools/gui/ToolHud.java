package helpertools.gui;

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

/**http://www.minecraftforum.net/forums/mapping-and-modding/
 * minecraft-mods/modification-development/1420597-trying-to-render-an-item-within-a-gui**/
/**http://www.minecraftforge.net/wiki/Gui_Overlay **/

public class ToolHud extends Gui
{
  private Minecraft mc;

  public ToolHud(Minecraft mc)
  {
    super();   
    this.mc = mc;
  }
  
  protected static RenderItem itemRender = new RenderItem();
  protected static RenderBlocks blockRender = new RenderBlocks();
  protected  FontRenderer fontRendererObj;
  
  private void drawItemStack(ItemStack itemstack, int X1, int Y1, String p_146982_4_)
  {
	  
	  GL11.glPushMatrix();
	  GL11.glEnable(GL11.GL_BLEND);
	  GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);	
      itemRender.zLevel = 2.0F;
      FontRenderer font = null;
      itemRender.renderItemIntoGUI(font, this.mc.getTextureManager(), itemstack, X1, Y1);
      itemRender.zLevel = 0.0F;
      GL11.glDisable(GL11.GL_BLEND);
      GL11.glPopMatrix();
      
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
      	GL11.glColor4f(1.0F, 1.0F, 1.0F,1.0F);  
      	this.mc.getTextureManager().bindTexture(backgroundimage);
      	this.drawTexturedModalRect(xPos-1, yPos-1, 16-1, 15+16*(modo)-1, xSize,  ySize);
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
    if(event.isCancelable() || event.type != ElementType.EXPERIENCE)
    {      
      return;
    }    

    int xPos = 20;
    int yPos = 20;
      ItemStack heldItem = this.mc.thePlayer.inventory.getCurrentItem();
		 if ((heldItem == null) || (!(heldItem.getItem() instanceof ItemStaffofExpansion))) {
		      return;
		    }
		 
		 ItemStaffofExpansion  Tool = (ItemStaffofExpansion)heldItem.getItem();
		 ItemStack StackyHelper = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock(heldItem)),0, Tool.returnTMeta(heldItem));
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


  }

}