package helpertools.gui;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;

import org.lwjgl.opengl.GL11;

public class GuiSlider extends GuiButton {

public float sliderValue = 1.0F;
public float sliderMaxValue = 1.0F;
public float sliderMinValue = 1.0F;
public boolean dragging = false;
public String label;
private boolean hovered;

public GuiSlider(int id, int x, int y, String label, float startingValue, float maxValue, float minValue) {
    super(id, x, y, 150, 20, label);
    this.label = label;
    this.sliderValue = startingValue;
    this.sliderMaxValue = maxValue;
    this.sliderMinValue = minValue;
}

public int getHoverState(boolean par1) {
    return 0;
}


@Override
public void drawButton(Minecraft mc, int mouseX, int mouseY)
{
    if (this.visible)
    {
        FontRenderer fontrenderer = mc.fontRenderer;
        mc.getTextureManager().bindTexture(buttonTextures);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        int k = this.getHoverState(this.hovered);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
        this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
        this.mouseDragged(mc, mouseX, mouseY);
        int l = 14737632;

        if (packedFGColour != 0)
        {
            l = packedFGColour;
        }
        else if (!this.enabled)
        {
            l = 10526880;
        }
        else if (this.hovered)
        {
            l = 16777120;
        }

        this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
    }
}

protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {
    if (this.enabled && this.visible && this.packedFGColour == 0) {
        if (this.dragging) {
            this.sliderValue = (float) (par2 - (this.xPosition + 4)) / (float) (this.width - 8);

            if (this.sliderValue < 0.0F) {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F) {
                this.sliderValue = 1.0F;
            }

        }

        this.displayString = label + ": " + (int) (sliderValue * sliderMaxValue);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(this.xPosition + (int) (this.sliderValue * (float) (this.width - 8)), this.yPosition, 0, 66, 4, 20);
        this.drawTexturedModalRect(this.xPosition + (int) (this.sliderValue * (float) (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
    }
}

public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
    if (super.mousePressed(par1Minecraft, par2, par3)) {
        this.sliderValue = (float) (par2 - (this.xPosition + 4)) / (float) (this.width - 8);

        if (this.sliderValue < 0.0F) {
            this.sliderValue = 0.0F;
        }

        if (this.sliderValue > 1.0F) {
            this.sliderValue = 1.0F;
        }

        this.dragging = true;
        return true;
    } else {
        return false;
    }
}

public void mouseReleased(int par1, int par2) {
    this.dragging = false;
}
}