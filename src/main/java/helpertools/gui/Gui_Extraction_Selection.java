package helpertools.gui;

import helpertools.Mod_Registry;
import helpertools.util.Text;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class Gui_Extraction_Selection extends GuiScreen{

	private GuiButton a;
	private GuiButton b;
	private GuiButton c;
	
	
	 private int x, y, z;
	 private EntityPlayer player;
	 private World world;
	 private int xSize, ySize;
	 //private ResourceLocation backgroundimage = new ResourceLocation("helpertools" + ":" + "textures/client/gui/GuiSmasher.png");
	 private ResourceLocation backgroundimage = new ResourceLocation("helpertools" + ":" + "textures/client/gui/demo_background.png");

	 
	 
	 
	public Gui_Extraction_Selection(EntityPlayer player, World world, int x, int y, int z) {
		 
        this.x = x;
        this.y = y;
        this.z = z;
        this.player = player;
        this.world = world;
        xSize = 248;
        		//176;
        ySize = 166;
        	//214;
    }
	
	
	
	//Drawing our screen duh
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		//Gui background fade - tinting
		//this.drawDefaultBackground();
		 
		 this.mc.getTextureManager().bindTexture(backgroundimage);
	        int x = (this.width - xSize) / 2;
	        int y = (this.height - ySize) / 2;
	        drawTexturedModalRect(x, y, 0, 0, xSize,  ySize);
	        
	        drawn_scroll_box(mouseX,mouseY);
		 
	    super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	protected static RenderItem itemRender = new RenderItem();
	protected static ItemStack decoy = new ItemStack(Mod_Registry.Balloon, 0 , 0);
	//testing
	public void drawn_scroll_box(int mouseX, int mouseY){
		if(mouseX<356 && mouseX>107 ){
			FontRenderer font = null;	
			GL11.glPushMatrix();
			RenderHelper.disableStandardItemLighting();
			ToolHud.enableStandardItemLighting();
			try{
			      itemRender.renderItemIntoGUI(font, this.mc.getTextureManager(), decoy, mouseX, mouseY);
			      }
			      catch(NullPointerException exception){
			      }
			RenderHelper.disableStandardItemLighting();
			GL11.glPopMatrix();			
		}
		String mosPos = "X: "+ mouseX + " Y: " + mouseY;
		this.mc.fontRenderer.drawStringWithShadow(mosPos, 200, 200, 0xF4F2FF);
		
	}
	
	//pause game while in single player or not
	//doesn't work in SMP
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	    //return true;
	}
	
	//lets make some buttons
	public void initGui() {
	    this.buttonList.add(this.a = new GuiButton(0, this.width / 2 - 100, this.height / 2 - 24, "This is button a"));
	    //this.buttonList.add(this.b = new GuiButton(1, this.width / 2 - 100, this.height / 2 + 4, "This is button b"));
	    //adjustable sizes
	    this.buttonList.add(this.b = new GuiButton(1, this.width / 2 - 100, this.height / 2 + 4, 100, 20, "This is button b"));
	    
	    this.buttonList.add(this.c = new GuiButton(2, this.width / 2 - 100, this.height / 2 + 32, button_c_name()));
	}
	
	
	public String button_c_name(){
		String name = "default";
		int x = this.x;
		int y = this.y;
		int z = this.z;
		int dim = this.world.provider.dimensionId;
		
		name = ("Location: "+ x +", " + y +", "+ z +" Dimension: "+ dim);
		
		return name;		
	}
	//actions for buttons etc
	//attach network pipelines and so on here
	@Override
	protected void actionPerformed(GuiButton button) 
			//throws IOException
	{
		//default close gui and return to normal game
		
		if (button == this.a) {
	        //Main.packetHandler.sendToServer(...);
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	        
	        //Sample output
	        Text.out("Button A pressed!");
	    }
	    if (button == this.b){
	        //Main.packetHandler.sendToServer(...);
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	        
	        //Sample output
	        Text.out("Woah there",EnumChatFormatting.RED);
		}
	    if (button == this.c){
	        //Main.packetHandler.sendToServer(...);
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	        
	        //Sample output
	        Text.out(button_c_name(),EnumChatFormatting.BLUE);
		}
	    }
	    
	   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
