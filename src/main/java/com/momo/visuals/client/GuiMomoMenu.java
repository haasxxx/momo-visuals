package com.momo.visuals.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import java.io.IOException;

public class GuiMomoMenu extends GuiScreen {
    private final GuiScreen parent;
    private int page = 0; // 0 = Основное, 1 = Руки

    public GuiMomoMenu(GuiScreen parent) { this.parent = parent; }

    @Override
    public void initGui() {
        this.buttonList.clear();
        int y = 20;

        if (page == 0) {
            this.buttonList.add(new GuiButton(1, width / 2 - 100, y, "Туман: " + (Config.fogEnabled ? "ВКЛ" : "ВЫКЛ")));
            y += 20;
            this.buttonList.add(new GuiButton(10, width / 2 - 100, y, "Красный: " + (int)(Config.fogR * 100)));
            y += 20;
            this.buttonList.add(new GuiButton(11, width / 2 - 100, y, "Зеленый: " + (int)(Config.fogG * 100)));
            y += 20;
            this.buttonList.add(new GuiButton(12, width / 2 - 100, y, "Синий: " + (int)(Config.fogB * 100)));
            y += 25;
            this.buttonList.add(new GuiButton(2, width / 2 - 100, y, "Частицы: " + (Config.ambientParticles ? "ВКЛ" : "ВЫКЛ")));
            y += 20;
            this.buttonList.add(new GuiButton(3, width / 2 - 100, y, "Форма: " + getShapeName(Config.ambientShape)));
            
            this.buttonList.add(new GuiButton(100, width / 2 - 100, height - 50, "Настройки рук ->"));
        } else {
            this.buttonList.add(new GuiButton(20, width / 2 - 100, y, "View Model: " + (Config.viewModelEnabled ? "ВКЛ" : "ВЫКЛ")));
            y += 25;
            
            this.buttonList.add(new GuiSliderFixed(30, width / 2 - 100, y, "Pos X", Config.vmPosX, 2.0f, -2.0f, val -> Config.vmPosX = val)); y += 20;
            this.buttonList.add(new GuiSliderFixed(31, width / 2 - 100, y, "Pos Y", Config.vmPosY, 2.0f, -2.0f, val -> Config.vmPosY = val)); y += 20;
            this.buttonList.add(new GuiSliderFixed(32, width / 2 - 100, y, "Pos Z", Config.vmPosZ, 2.0f, -2.0f, val -> Config.vmPosZ = val)); y += 25;

            this.buttonList.add(new GuiSliderFixed(33, width / 2 - 100, y, "Rot X", Config.vmRotX, 360f, -360f, val -> Config.vmRotX = val)); y += 20;
            this.buttonList.add(new GuiSliderFixed(34, width / 2 - 100, y, "Rot Y", Config.vmRotY, 360f, -360f, val -> Config.vmRotY = val)); y += 20;
            this.buttonList.add(new GuiSliderFixed(35, width / 2 - 100, y, "Rot Z", Config.vmRotZ, 360f, -360f, val -> Config.vmRotZ = val));

            this.buttonList.add(new GuiButton(101, width / 2 - 100, height - 50, "<- Назад"));
        }

        this.buttonList.add(new GuiButton(0, width / 2 - 100, height - 25, "Закрыть"));
    }

    private String getShapeName(int shape) {
        if (shape == 0) return "Круг";
        if (shape == 1) return "Квадрат";
        return "Звезда";
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) mc.displayGuiScreen(parent);
        if (button.id == 100) { page = 1; initGui(); }
        if (button.id == 101) { page = 0; initGui(); }

        if (button.id == 1) Config.fogEnabled = !Config.fogEnabled;
        if (button.id == 2) Config.ambientParticles = !Config.ambientParticles;
        if (button.id == 3) Config.ambientShape = (Config.ambientShape + 1) % 3;
        if (button.id == 20) Config.viewModelEnabled = !Config.viewModelEnabled;

        if (button.id == 10) { Config.fogR += 0.1f; if (Config.fogR > 1.0f) Config.fogR = 0.0f; }
        if (button.id == 11) { Config.fogG += 0.1f; if (Config.fogG > 1.0f) Config.fogG = 0.0f; }
        if (button.id == 12) { Config.fogB += 0.1f; if (Config.fogB > 1.0f) Config.fogB = 0.0f; }
        
        initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(fontRenderer, page == 0 ? "Momo Visuals - Основное" : "Momo Visuals - Руки", width / 2, 10, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}