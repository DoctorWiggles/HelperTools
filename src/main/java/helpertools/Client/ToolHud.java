package helpertools.Client;


import helpertools.Common.ConfigurationFactory;
import helpertools.Common.Tools.ItemStaffofExpansion;
import helpertools.Common.Tools.ItemStaffofTransformation;
import helpertools.Common.Tools.ToolBase_Default;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.opengl.GL11;


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
  
  private void drawItemStack(ItemStack itemstack, int X1, int Y1, String string)
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
    if(event.isCancelable() || event.getType() != ElementType.EXPERIENCE)
    {      
      return;
    }    
    
    //config hook
    if(ConfigurationFactory.RenderToolHuds == false){
    	return;
    }

    int xPos = 20;
    int yPos = 20;
    int offhandPos = 0;
    	
    	
    	ItemStack heldItem = this.mc.thePlayer.getHeldItem(EnumHand.MAIN_HAND);
      
	if ((heldItem == null) || (!(heldItem.getItem() instanceof ToolBase_Default))) {		
			heldItem = this.mc.thePlayer.getHeldItem(EnumHand.OFF_HAND);
		if ((heldItem == null) || (!(heldItem.getItem() instanceof ToolBase_Default))) {
			return;
		}
		}
	if(!heldItem.hasTagCompound()){return;}
	
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
			 int meta = Tool.returnTMeta(heldItem);
			 meta = PillarAdjust(Tool.returnTBlock_FromState(heldItem), meta);
			 StackyHelper = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock_FromState(heldItem)),0, meta);
			 backgroundimage = new ResourceLocation("helpertools" + ":" + "textures/client/gui/HudTab_7.png");
		 }
		 
		//Expanding Stave Casting
		 if(heldItem.getItem() instanceof ItemStaffofExpansion){
			 ItemStaffofExpansion  Tool = (ItemStaffofExpansion)heldItem.getItem();
			 Empowerment = Tool.getToolLevel(heldItem);
			 Modo = Tool.getMode(heldItem);
			 //StackyHelper = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock(heldItem)),0, Tool.returnTMeta(heldItem));
			 int meta = Tool.returnTMeta(heldItem);
			 meta = PillarAdjust(Tool.returnTBlock_FromState(heldItem), meta);
			 StackyHelper = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock_FromState(heldItem)),0, meta);
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
  
  public static int PillarAdjust(Block block, int meta){
	  
	  if(block instanceof BlockRotatedPillar
			  ||block instanceof BlockStairs){
			meta = 0;
			}
	  if(block instanceof BlockSlab && meta >= 8){
		  return meta - 8;
	  }	  
	  if(block == Blocks.QUARTZ_BLOCK && meta >= 2){
		  return meta = 2;
			}
	  
	  return meta;
  }
  
  
  //////////////////////////////////////////////////////////////
  /** Bunch of crud needed to properly shade blocks selected **/
  /////////////////////////////////////////////////////////////
  
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