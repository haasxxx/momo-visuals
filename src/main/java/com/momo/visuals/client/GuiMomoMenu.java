package com.momo.visuals.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import java.io.IOException;

public class GuiMomoMenu extends GuiScreen {
    private final GuiScreen parent;

    public GuiMomoMenu(GuiScreen parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        int y = 30;

        // Туман
        this.buttonList.add(new GuiButton(1, width / 2 - 100, y, "Туман: " + (Config.fogEnabled ? "ВКЛ" : "ВЫКЛ")));
        y += 25;
        this.buttonList.add(new GuiButton(10, width / 2 - 100, y, "Цвет тумана: " + getColorName(Config.fogR, Config.fogG, Config.fogB)));
        
        y += 35;
        // Частицы вокруг
        this.buttonList.add(new GuiButton(2, width / 2 - 100, y, "Частицы вокруг: " + (Config.ambientParticles ? "ВКЛ" : "ВЫКЛ")));
        y += 25;
        this.buttonList.add(new GuiButton(3, width / 2 - 100, y, "Форма (вокруг): " + getShapeName(Config.ambientShape)));
        
        y += 35;
        // Частицы удара
        this.buttonList.add(new GuiButton(4, width / 2 - 100, y, "Частицы удара: " + (Config.hitParticles ? "ВКЛ" : "ВЫКЛ")));
        y += 25;
        this.buttonList.add(new GuiButton(5, width / 2 - 100, y, "Форма (удар): " + getShapeName(Config.hitShape)));
        
        // Закрыть
        this.buttonList.add(new GuiButton(0, width / 2 - 100, height - 30, "Закрыть"));
    }

    private String getShapeName(int shape) {
        if (shape == 0) return "Круг";
        if (shape == 1) return "Квадрат";
        return "Звезда";
    }

    private String getColorName(float r, float g, float b) {
        if (r > 0.8f && g < 0.3f && b < 0.3f) return "Красный";
        if (r < 0.3f && g > 0.8f && b < 0.3f) return "Зеленый";
        if (r < 0.3f && g < 0.3f && b > 0.8f) return "Синий";
        if (r > 0.8f && g > 0.8f && b > 0.8f) return "Белый";
        return "Свой";
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) mc.displayGuiScreen(parent);
        if (button.id == 1) Config.fogEnabled = !Config.fogEnabled;
        if (button.id == 2) Config.ambientParticles = !Config.ambientParticles;
        if (button.id == 3) Config.ambientShape = (Config.ambientShape + 1) % 3;
        if (button.id == 4) Config.hitParticles = !Config.hitParticles;
        if (button.id == 5) Config.hitShape = (Config.hitShape + 1) % 3;
        
        // Переключение цвета тумана
        if (button.id == 10) {
            if (Config.fogR > 0.8f) { Config.fogR = 0.2f; Config.fogG = 0.8f; Config.fogB = 0.2f; } // На зеленый
            else if (Config.fogG > 0.8f) { Config.fogR = 0.2f; Config.fogG = 0.2f; Config.fogB = 0.8f; } // На синий
            else if (Config.fogB > 0.8f) { Config.fogR = 1.0f; Config.fogG = 1.0f; Config.fogB = 1.0f; } // На белый
            else { Config.fogR = 0.8f; Config.fogG = 0.2f; Config.fogB = 0.2f; } // На красный
        }
        initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(fontRenderer, "Momo Visuals Settings", width / 2, 10, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}