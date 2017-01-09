package helpertools.Client;


import helpertools.Main;
import helpertools.Com.Config;
import helpertools.Com.Entity.Entity_Mirage;
import helpertools.Com.Items.Item_MirageHusk;
import helpertools.Com.Tools.ItemEuclideanTransposer;
import helpertools.Com.Tools.ItemStaffofExpansion;
import helpertools.Com.Tools.ItemStaffofTransformation;
import helpertools.Com.Tools.ToolBase;
import helpertools.Com.Tools.ToolBase_Default;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
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
  
  protected  FontRenderer fontRendererObj;
  
  //protected RenderItem itemRender = mc.getRenderItem();
  
  @SubscribeEvent(priority = EventPriority.NORMAL)
  public void Render_Mirage_Hud(RenderGameOverlayEvent event)
  { 
	  if(event.isCancelable() || event.getType() != ElementType.EXPERIENCE){
		  return;}    

	  if(Config.RenderToolHuds == false){
		  return;}

	  EntityPlayerSP player = this.mc.thePlayer;
	  World world = player.worldObj;
	  ItemStack hat = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
	  //if(!player.isSneaking())return;
	  
	  if(hat == null){
		  return;}
	  if(!(hat.getItem() instanceof Item_MirageHusk)){
		  return;}
	  try{

		 
		  Item_MirageHusk husk = (Item_MirageHusk)hat.getItem();
		  Entity_Mirage shade = husk.getMirage(hat, world);

		  if(shade != null){
			  
		  int distance = (int)player.getDistanceToEntity(shade);
		  int Health = (int)shade.getHealth();
		  
		  String distance_info = "close";
		  if(distance > 16){distance_info = "nearby";}
		  if(distance > 48){distance_info = "in the area";}
		  if(distance > 86){distance_info = "distant";}
		  if(distance > 120){distance_info = "remote";}
		  if(distance > 170){distance_info = "far away";}
		  
		  String health_info = "";	
		  if(Health <12){health_info = " weak";}
		  if(Health <7){health_info = " fading";}
		  
		  
		  //this.drawString(fontRender, fluidname, 1, 1, 0xffffffff);
		  //fontRendererObj.drawStringWithShadow(fluidname, 1, 1, 0xffffffff);
		  this.mc.fontRendererObj.drawStringWithShadow
		  ("A"+health_info+" shadow is "+distance_info,  20, 8, 0xF4F2FF);
		  }
		  else {this.mc.fontRendererObj.drawStringWithShadow
	  		("No active shadow",  20, 8, 0xF4F2FF);
		  	}
	  }catch(Exception e){}
	  
	  		

  }
  
  @SubscribeEvent(priority = EventPriority.NORMAL)
  public void Render_Stave_Huds(RenderGameOverlayEvent event)
  { 
    if(event.isCancelable() || event.getType() != ElementType.EXPERIENCE)
    {    
      return;
    }    
    
    //config hook
    if(Config.RenderToolHuds == false){
    	return;
    }

    int xPos = 20;
    int yPos = 20;
    int offhandPos = 0;
    	
    	EntityPlayerSP player = this.mc.thePlayer;
    	ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
      
	if ((heldItem == null) || (!(heldItem.getItem() instanceof ToolBase))) {		
			heldItem = player.getHeldItem(EnumHand.OFF_HAND);
		if ((heldItem == null) || (!(heldItem.getItem() instanceof ToolBase))) {
			return;
		}
		}
	if(!heldItem.hasTagCompound()){return;}
	
		 //Defualting
		 int Empowerment = 0;
		 int Modo = 0;
		 ItemStack StackyHelper = null;
		 ResourceLocation backgroundimage = new ResourceLocation(Main.PATH + "textures/client/gui/Expand_Tab.png");
		 
		 
		 //Exchange Stave Castings		 
		 if(heldItem.getItem() instanceof ItemStaffofTransformation){
			 ItemStaffofTransformation  Tool = (ItemStaffofTransformation)heldItem.getItem();
			 Empowerment = Tool.getToolLevel(heldItem);
			 Modo = Tool.getMode(heldItem);
			 int meta = Tool.returnTMeta(heldItem);
			 meta = PillarAdjust(Tool.returnTBlock_FromState(heldItem), meta);
			 StackyHelper = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock_FromState(heldItem)),0, meta);
			 backgroundimage = new ResourceLocation(Main.PATH + "textures/client/gui/Exchange_Tab.png");
			 
			 draw_things(xPos, yPos, backgroundimage, Modo, Empowerment, StackyHelper);
			 return;
		 }
		 
		//Expanding Stave Casting
		 if(heldItem.getItem() instanceof ItemStaffofExpansion){
			 ItemStaffofExpansion  Tool = (ItemStaffofExpansion)heldItem.getItem();
			 Empowerment = Tool.getToolLevel(heldItem);
			 Modo = Tool.getMode(heldItem);
			 int meta = Tool.returnTMeta(heldItem);
			 meta = PillarAdjust(Tool.returnTBlock_FromState(heldItem), meta);
			 StackyHelper = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock_FromState(heldItem)),0, meta);
			 
			 draw_things(xPos, yPos, backgroundimage, Modo, Empowerment, StackyHelper);
			 return;
		 }
		 
		//Expanding Stave Casting
		 if(heldItem.getItem() instanceof ItemEuclideanTransposer){
			 ItemEuclideanTransposer  Tool = (ItemEuclideanTransposer)heldItem.getItem();
			 int rotation = Tool.getCorner(heldItem);
			 if(rotation >3){rotation = rotation -4;}
			 
			 
			 backgroundimage = new ResourceLocation(Main.PATH + "textures/client/gui/EU_Tab.png");
			 
			 drawHudFrame(xPos-2, yPos-4, 8, 6, 24, 28, backgroundimage);
			 
			 drawModeIcons(xPos, yPos+3, 10, 40, 21*rotation, 17, 16, backgroundimage, Modo);
			 return;
			 
		 }	 
		 
		 
		 /////////////////////////
		 /** Draw some Things **/
		 ///////////////////////		 
		 //drawHudFrame(xPos, yPos,backgroundimage);
		 /**
		 drawHudFrame(xPos-1, yPos,65, 117, 41, 28, backgroundimage);
		 
		 drawModeIcons(xPos, yPos,16, 15, 16*(Modo)-1, 40, 28, backgroundimage, Modo);
	      
	      	
	      	if(Empowerment >0){
	      	drawEmpoweredBar(xPos-1, yPos,backgroundimage, Empowerment);
	      	}
	      	
	      this.drawItemStack(StackyHelper, xPos+3, yPos+3, (String)null);
			     
		**/

  } 
  
  public void draw_things(int xPos, int yPos, 
		  ResourceLocation backgroundimage, int Modo, int Empowerment, ItemStack StackyHelper){

	  drawHudFrame(xPos-2, yPos,65, 117, 41, 28, backgroundimage);

	  drawModeIcons(xPos, yPos,16, 15, 16*(Modo)-1, 40, 28, backgroundimage, Modo);

	  if(Empowerment >0){
		  drawEmpoweredBar(xPos-2, yPos,backgroundimage, Empowerment);
	  }

	  this.drawItemStack(StackyHelper, xPos+3, yPos+3, (String)null);


  }
  
  private void drawItemStack(ItemStack itemstack, int X1, int Y1, String string)
  {
	  
	  GL11.glPushMatrix();
      FontRenderer font = null;      
      
      RenderHelper.enableGUIStandardItemLighting();
      
      try{
      mc.getRenderItem().renderItemIntoGUI(itemstack, X1, Y1);
      }
      catch(NullPointerException exception){}
      
      GL11.glPopMatrix();
      
  }

  private void drawHudFrame(int xPos, int yPos, int xStart, int ystart, int xSize, int ySize,  ResourceLocation backgroundimage) {

	  
      	GL11.glPushMatrix();
      	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      	GL11.glColor4f(1.0F, 1.0F, 1.0F,1.0F);  
      	this.mc.getTextureManager().bindTexture(backgroundimage);
      	this.drawTexturedModalRect(xPos, yPos, xStart, ystart, xSize,  ySize);
      	GL11.glPopMatrix();
      	
      		  
  }
  
  private void drawModeIcons(int xPos, int yPos, int xStart, int ystart,int yoffset, int xSize, int ySize,  ResourceLocation backgroundimage, int modo) {
	  //int xSize = 38+2;
     // int ySize = 26+2;

      	GL11.glPushMatrix();
      	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      	GL11.glColor4f(1.0F, 1.0F, 1.0F,1.0F);  
      	this.mc.getTextureManager().bindTexture(backgroundimage);
      	this.drawTexturedModalRect(xPos, yPos, xStart, ystart+yoffset, xSize,  ySize);
      	GL11.glPopMatrix();
	}
  
  private void drawEmpoweredBar(int xPos, int yPos, ResourceLocation Image, int Empowerment){
	  
      int xSize = Empowerment*7+1; 

      	GL11.glPushMatrix();
      	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      	this.mc.getTextureManager().bindTexture(Image);
      	this.drawTexturedModalRect(xPos+4, yPos+21, 68, 92, xSize,  3);
      	GL11.glPopMatrix();
  }
    
  
  
  public static int PillarAdjust(Block block, int meta){
	  
	  if(block instanceof BlockLeaves){
		  return meta-4;
	  }
	  
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
  
  

}