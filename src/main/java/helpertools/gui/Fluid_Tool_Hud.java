package helpertools.gui;

import helpertools.ConfigurationFactory;
import helpertools.Main;
import helpertools.tools.Staff_Expansion;
import helpertools.tools.Staff_Transformation;
import helpertools.tools.Item_Bubblegun_2;

import java.awt.Point;
import java.lang.reflect.Field;
import java.nio.FloatBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GLAllocation;
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
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ItemFluidContainer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**http://www.minecraftforum.net/forums/mapping-and-modding/
 * minecraft-mods/modification-development/1420597-trying-to-render-an-item-within-a-gui**/
/**http://www.minecraftforge.net/wiki/Gui_Overlay **/
/**https://www.opengl.org/discussion_boards/showthread.php/156794-How-to-change-the-brightness **/

//https://github.com/coolAlias/Tutorial-Demo/blob/master/src/main/java/tutorial/client/gui/GuiManaBar.java

public class Fluid_Tool_Hud extends Gui
{
  private Minecraft mc;

  public Fluid_Tool_Hud(Minecraft mc)
  {
    super();   
    this.mc = mc;
  }
  
  protected static RenderItem itemRender = new RenderItem();
  protected static RenderBlocks blockRender = new RenderBlocks();
  protected  FontRenderer fontRendererObj;
  //protected FontRenderer fontRender = this.mc.fontRenderer;
  private static final ResourceLocation BLOCK_TEXTURE = TextureMap.locationBlocksTexture;
  
  
  @SubscribeEvent(priority = EventPriority.NORMAL)
  public void onRenderExperienceBar(RenderGameOverlayEvent event)
  {
    if(event.isCancelable() || event.type != ElementType.EXPERIENCE)
    {      
      return;
    }    
    
    //config hook
    if(ConfigurationFactory.RenderToolHuds == false){
    	return;
    }

    int xPos = 20;
    int yPos = 20;
    
      ItemStack heldItem = this.mc.thePlayer.inventory.getCurrentItem();
		 if ((heldItem == null) || (!(heldItem.getItem() instanceof Item_Bubblegun_2))) {			
		      return;
			 
		    }
		 
		 ItemStack StackyHelper = null;
		 ResourceLocation backgroundimage = new ResourceLocation("helpertools" + ":" + "textures/client/gui/Fluid_Hud_Tab.png");
		 
		 try{
		 //Exchange Stave Castings
		 if(heldItem.getItem() instanceof Item_Bubblegun_2){
			 Item_Bubblegun_2  Tool = (Item_Bubblegun_2)heldItem.getItem();
			
			 if(Item_Bubblegun_2.getFluid_2(heldItem) != null){
			        FluidStack fluidstack =  Item_Bubblegun_2.getFluid_2(heldItem);
			        Fluid fluid = fluidstack.getFluid();
			        Block block = fluid.getBlock();
			        
			        int amount = Tool.return_Fluid_Amount(heldItem);
			        amount = amount / 1000;
			        
			        int size = amount * 2;
			        //IIcon ico = block.getBlockTextureFromSide(0);
			        
			        
			        
			        //IIcon icon= fluid.getStillIcon();
			        
			        //IIcon icon= fluid.getFlowingIcon();
			        
			        IIcon icon = getIcon();
			        //this.drawTexturedModelRectFromIcon(xPos+10, yPos+10, icon, 16, 16);
			        
			        this.drawFluid(fluidstack, 1*size, xPos+2, yPos+5+(32)-size , 12, size);
			        //this.drawFluid(fluid, 20, 122, 19, 16, 58);
			        
			        //this.mc.renderEngine.bindTexture(fluid.getTextureSheet());
			        //this.mc.renderEngine.bindTexture(LiquidDictionary.getCanonicalLiquid("Water").getTextureSheet());
			        
			        
			        
			 //StackyHelper = new ItemStack (Item.getItemFromBlock(Tool.returnTBlock(heldItem)),0, Tool.returnTMeta(heldItem));
			 
			      	String fluidname = "empty";
			      	fluidname = Tool.what_fluidname(heldItem);
			      	
			      	//this.drawString(fontRender, fluidname, 1, 1, 0xffffffff);
			      	//fontRendererObj.drawStringWithShadow(fluidname, 1, 1, 0xffffffff);
			      	this.mc.fontRenderer.drawStringWithShadow(fluidname, xPos + 20, yPos, 0xF4F2FF);

		 }}
		 }
		 catch(Exception exception){
			  System.out.println("------------- RENDERING BROKE ----------");
		 }
		 try{
		 this.mc.getTextureManager().bindTexture(backgroundimage);
		 this.drawTexturedModalRect(xPos-1, yPos-1, 137, 96, 32,  64);
		 }catch(IllegalStateException IllegalStateException){
			 System.out.println("------------- RENDERING BROKE ----------");
		 }
		 	
		 


  } 
  
  //==========================================================================================//
  
  
  
  

  public IIcon getIcon()
	{
		return BlockStaticLiquid.getLiquidIcon("lava_still");
	}
  
  /** http://www.mod-buildcraft.com/forums/archive/index.php?thread-272-1.html **/
  private void drawFluid(FluidStack fluid, int level, int x, int y, int width, int height){
      if(fluid == null || fluid.getFluid() == null) {
          return;
      }
      IIcon icon = fluid.getFluid().getIcon(fluid);
      mc.renderEngine.bindTexture(BLOCK_TEXTURE);
      //RenderUtils.setGLColorFromInt(fluid.getFluid().getColor(fluid));
      int fullX = width / 16;
      int fullY = height / 16;
      int lastX = width - fullX * 16;
      int lastY = height - fullY * 16;
      int fullLvl = (height - level) / 16;
      int lastLvl = (height - level) - fullLvl * 16;
      for(int i = 0; i < fullX; i++) {
          for(int j = 0; j < fullY; j++) {
              if(j >= fullLvl) {
                  drawCutIcon(icon, x + i * 16, y + j * 16, 16, 16, j == fullLvl ? lastLvl : 0);
              }
          }
      }
      for(int i = 0; i < fullX; i++) {
          drawCutIcon(icon, x + i * 16, y + fullY * 16, 16, lastY, fullLvl == fullY ? lastLvl : 0);
      }
      for(int i = 0; i < fullY; i++) {
          if(i >= fullLvl) {
              drawCutIcon(icon, x + fullX * 16, y + i * 16, lastX, 16, i == fullLvl ? lastLvl : 0);
          }
      }
      drawCutIcon(icon, x + fullX * 16, y + fullY * 16, lastX, lastY, fullLvl == fullY ? lastLvl : 0);
  }

  //The magic is here
  private void drawCutIcon(IIcon icon, int x, int y, int width, int height, int cut){
      Tessellator tess = Tessellator.instance;
      tess.startDrawingQuads();
      tess.addVertexWithUV(x, y + height, zLevel, icon.getMinU(), icon.getInterpolatedV(height));
      tess.addVertexWithUV(x + width, y + height, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(height));
      tess.addVertexWithUV(x + width, y + cut, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
      tess.addVertexWithUV(x, y + cut, zLevel, icon.getMinU(), icon.getInterpolatedV(cut));
      tess.draw();
  }
}

