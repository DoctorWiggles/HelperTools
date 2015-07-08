package helpertools.Client;


import helpertools.Common.Tools.ItemStaffofExpansion;
import helpertools.Common.Tools.ItemStaffofTransformation;

import java.awt.Point;
import java.lang.reflect.Field;
import java.nio.FloatBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**http://www.minecraftforum.net/forums/mapping-and-modding/
 * minecraft-mods/modification-development/1420597-trying-to-render-an-item-within-a-gui**/
/**http://www.minecraftforge.net/wiki/Gui_Overlay **/
/**https://www.opengl.org/discussion_boards/showthread.php/156794-How-to-change-the-brightness **/

public class ToolHud extends Gui
{
  private static Minecraft mc;

  public ToolHud(Minecraft mc)
  {
    super();   
    this.mc = mc;
  }
  
  //protected static RenderItem itemRender = new RenderItem();
  //protected static RenderItem itemRender = new RenderItem(mc.getTextureManager(), mc.itemModelMesher);
  //protected RenderItem itemRender ;  
  //protected static RenderBlocks blockRender = new RenderBlocks();
  protected  FontRenderer fontRendererObj;
  
  //protected RenderItem itemRender = mc.getRenderItem();
  
  private void drawItemStack(ItemStack itemstack, int X1, int Y1, String p_146982_4_)
  {
	  
	  GL11.glPushMatrix();
      //itemRender.zLevel = -2.0F;
      FontRenderer font = null;
      //RenderHelper.disableStandardItemLighting();
      
      /**
      Now using Mc's version which seems to be fixed now 
	   **/
      RenderHelper.enableGUIStandardItemLighting();
      //this.enableGUIStandardItemLighting();
      
      try{
      //itemRender.renderItemIntoGUI(font, this.mc.getTextureManager(), itemstack, X1, Y1);
      //RenderItem.renderItemIntoGUI(itemstack, X1, Y1);
      
      (mc.getRenderItem()).renderItemIntoGUI(itemstack, X1, Y1);
      }
      catch(NullPointerException exception){
      }
      
      //RenderHelper.disableStandardItemLighting();
      //itemRender.zLevel = 0.0F;
      GL11.glPopMatrix();
      
  }

  private void drawHudFrame(int xPos, int yPos, ResourceLocation backgroundimage) {

      int xSize = 38+2;
      int ySize = 26+2;


      	GL11.glPushMatrix();
      	//GL11.glEnable(GL11.GL_BLEND);
      	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      	GL11.glColor4f(1.0F, 1.0F, 1.0F,1.0F);  
      	this.mc.getTextureManager().bindTexture(backgroundimage);
      	this.drawTexturedModalRect(xPos-1, yPos-1, 67-1, 118-1, xSize,  ySize);
      	//GL11.glDisable(GL11.GL_BLEND);
      	GL11.glPopMatrix();
      	
      		  
  }
  
  private void drawModeIcons(int xPos, int yPos, ResourceLocation backgroundimage, int modo) {
	  int xSize = 38+2;
      int ySize = 26+2;

      	GL11.glPushMatrix();
      	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      	GL11.glColor4f(1.0F, 1.0F, 1.0F,1.0F);  
      	this.mc.getTextureManager().bindTexture(backgroundimage);
      	this.drawTexturedModalRect(xPos-1, yPos-1, 16-1, 15+16*(modo)-1, xSize,  ySize);
      	GL11.glPopMatrix();
	}
  
  private void drawEmpoweredBar(int xPos, int yPos, ResourceLocation Image, int Empowerment){
	  
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
    
    //config hook
    //if(Helpertoolscore.RenderToolHuds == false){
    //	return;
    //}

    int xPos = 20;
    int yPos = 20;
    
      ItemStack heldItem = this.mc.thePlayer.inventory.getCurrentItem();
		 if ((heldItem == null) || (!(heldItem.getItem() instanceof ItemStaffofExpansion))) {
			 
			 if ((heldItem == null) ||!(heldItem.getItem() instanceof ItemStaffofTransformation)){
		      return;
			 }
		    }
		 
		 //Defualting
		 int Empowerment = 0;
		 int Modo = 0;
		 ItemStack StackyHelper = null;
		 ResourceLocation backgroundimage = new ResourceLocation("helpertools" + ":" + "textures/client/gui/HudTab_4.png");
		 
		 
		 //Exchange Stave Castings		 
		 if(heldItem.getItem() instanceof ItemStaffofTransformation){
			 ItemStaffofTransformation  Tool = (ItemStaffofTransformation)heldItem.getItem();
			 Empowerment = Tool.getToolLevel(heldItem);
			 Modo = Tool.getMode(heldItem);
			 StackyHelper = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock_FromState(heldItem)),0, Tool.returnTMeta(heldItem));
			 backgroundimage = new ResourceLocation("helpertools" + ":" + "textures/client/gui/HudTab_7.png");
		 }
		 
		//Expanding Stave Casting
		 if(heldItem.getItem() instanceof ItemStaffofExpansion){
			 ItemStaffofExpansion  Tool = (ItemStaffofExpansion)heldItem.getItem();
			 Empowerment = Tool.getToolLevel(heldItem);
			 Modo = Tool.getMode(heldItem);
			 //StackyHelper = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock(heldItem)),0, Tool.returnTMeta(heldItem));
			 StackyHelper = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock_FromState(heldItem)),0, Tool.returnTMeta(heldItem));
		 }	 
		 
		 
		 /////////////////////////
		 /** Draw some Things **/
		 ///////////////////////		 
		 drawHudFrame(xPos, yPos,backgroundimage);
		 
		 drawModeIcons(xPos, yPos,backgroundimage, Modo);
	      
	      	
	      	if(Empowerment >0){
	      	drawEmpoweredBar(xPos, yPos,backgroundimage, Empowerment);
	      	}
	      	
	      this.drawItemStack(StackyHelper, xPos+2, yPos+2, (String)null);
			     


  } 
  
  
  //////////////////////////////////////////////////////////////
  /** Bunch of crud needed to properly shade blocks selected **/
  /////////////////////////////////////////////////////////////
  
  private static FloatBuffer colorBuffer = GLAllocation.createDirectFloatBuffer(16);
  //private static final Vec3 Vector_B = Vec3.createVectorHelper(0.20000000298023224D, 1.0D, -0.699999988079071D).normalize();
  //private static final Vec3 Vector_C = Vec3.createVectorHelper(-0.20000000298023224D, 1.0D, 0.699999988079071D).normalize();
  private static final Vec3 LIGHT0_POS = (new Vec3(0.20000000298023224D, 1.0D, -0.699999988079071D)).normalize();
  private static final Vec3 LIGHT1_POS = (new Vec3(-0.20000000298023224D, 1.0D, 0.699999988079071D)).normalize();
  private static final String __OBFID = "CL_00000629";
  @ Deprecated
  private static FloatBuffer setColorBuffer(float p_74521_0_, float p_74521_1_, float p_74521_2_, float p_74521_3_)
  {
      colorBuffer.clear();
      colorBuffer.put(p_74521_0_).put(p_74521_1_).put(p_74521_2_).put(p_74521_3_);
      colorBuffer.flip();
      /** Float buffer used to set OpenGL material colors */
      return colorBuffer;
  }
  @ Deprecated
  private static FloatBuffer setColorBuffer(double p_74517_0_, double p_74517_2_, double p_74517_4_, double p_74517_6_)
  {
      /**
       * Update and return colorBuffer with the RGBA values passed as arguments
       */
      return setColorBuffer((float)p_74517_0_, (float)p_74517_2_, (float)p_74517_4_, (float)p_74517_6_);
  }

  @ Deprecated
  public static void enableStandardItemLighting()
  {
      //GL11.glEnable(GL11.GL_LIGHTING);
      GL11.glEnable(GL11.GL_LIGHT0);
      GL11.glEnable(GL11.GL_LIGHT1);
      GL11.glEnable(GL11.GL_COLOR_MATERIAL);
      GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE);
      //Fine tuned the values
      //Default weren't working properly
      float f = .58F;
      float f1 = 4F;
      float f2 = 0.0F;
      float bright = 0f;
      GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, setColorBuffer(LIGHT0_POS.xCoord, LIGHT0_POS.yCoord, LIGHT0_POS.zCoord, 0.0D));
      GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, setColorBuffer(f1, f1, f1, 1.0F));
      GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, setColorBuffer(bright, bright, bright, 1.0F));
      GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, setColorBuffer(f2, f2, f2, 1.0F));
      GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, setColorBuffer(LIGHT1_POS.xCoord, LIGHT1_POS.yCoord, LIGHT1_POS.zCoord, 0.0D));
      GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, setColorBuffer(f1, f1, f1, 1.0F));
      GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, setColorBuffer(bright, bright, bright, 1.0F));
      GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, setColorBuffer(f2, f2, f2, 1.0F));
      GL11.glShadeModel(GL11.GL_FLAT);
      GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, setColorBuffer(f, f, f, 1.0F));
  }
  @ Deprecated
  public static void enableGUIStandardItemLighting()
  {
	  float f1 = -35;
	  float f2 = 188;
	  f1 = -30   ;
	  f2 = 165     ;
      GL11.glPushMatrix();
      GL11.glRotatef(f1, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glPopMatrix();
  }

}