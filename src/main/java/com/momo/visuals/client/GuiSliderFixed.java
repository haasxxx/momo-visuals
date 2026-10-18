package com.momo.visuals.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiSliderFixed extends GuiButton {
    public float sliderValue;
    public float sliderMaxValue;
    public float sliderMinValue;
    public boolean dragging;
    public String label;
    public java.util.function.Consumer<Float> callback;

    public GuiSliderFixed(int id, int x, int y, String label, float startingValue, float maxValue, float minValue, java.util.function.Consumer<Float> callback) {
        super(id, x, y, 150, 20, label);
        this.label = label;
        this.sliderValue = startingValue;
        this.sliderMaxValue = maxValue;
        this.sliderMinValue = minValue;
        this.callback = callback;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int k = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            this.drawTexturedModalRect(this.x, this.y, 0, 46 + k * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            
            int color = 14737632;
            if (!this.enabled) color = 10526880;
            else if (this.hovered) color = 16777120;
            
            this.drawCenteredString(mc.fontRenderer, this.label + ": " + String.format("%.2f", sliderValue), this.x + this.width / 2, this.y + (this.height - 8) / 2, color);
        }
    }

    @Override
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            if (this.dragging) {
                this.sliderValue = (float) (mouseX - (this.x + 4)) / (float) (this.width - 8);
                if (this.sliderValue < 0.0F) this.sliderValue = 0.0F;
                if (this.sliderValue > 1.0F) this.sliderValue = 1.0F;
                this.sliderValue = this.sliderMinValue + (this.sliderValue * (this.sliderMaxValue - this.sliderMinValue));
                this.callback.accept(this.sliderValue);
            }
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.x + (int) (this.sliderValue * (float) (this.width - 8)), this.y, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.x + (int) (this.sliderValue * (float) (this.width - 8)) + 4, this.y, 196, 66, 4, 20);
        }
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY)) {
            this.sliderValue = (float) (mouseX - (this.x + 4)) / (float) (this.width - 8);
            if (this.sliderValue < 0.0F) this.sliderValue = 0.0F;
            if (this.sliderValue > 1.0F) this.sliderValue = 1.0F;
            this.sliderValue = this.sliderMinValue + (this.sliderValue * (this.sliderMaxValue - this.sliderMinValue));
            this.callback.accept(this.sliderValue);
            this.dragging = true;
            return true;
        }
        return false;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        this.dragging = false;
    }
}