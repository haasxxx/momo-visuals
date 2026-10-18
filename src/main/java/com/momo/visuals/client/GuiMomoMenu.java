
package com.momo.visuals.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import java.io.IOException;

public class GuiMomoMenu extends GuiScreen {
    private final GuiScreen parent;

    public GuiMomoMenu(GuiScreen parent) { this.parent = parent; }

    @Override
    public void initGui() {
        this.buttonList.clear();
        int y = 15; // Начинаем чуть выше

        // --- БЛОК ТУМАНА ---
        this.buttonList.add(new GuiButton(1, width / 2 - 100, y, "Туман: " + (Config.fogEnabled ? "ВКЛ" : "ВЫКЛ")));
        y += 18;
        this.buttonList.add(new GuiButton(10, width / 2 - 100, y, "Красный: " + (int)(Config.fogR * 100)));
        y += 18;
        this.buttonList.add(new GuiButton(11, width / 2 - 100, y, "Зеленый: " + (int)(Config.fogG * 100)));
        y += 18;
        this.buttonList.add(new GuiButton(12, width / 2 - 100, y, "Синий: " + (int)(Config.fogB * 100)));
        y += 22;

        // --- БЛОК ЧАСТИЦ ---
        this.buttonList.add(new GuiButton(2, width / 2 - 100, y, "Частицы: " + (Config.ambientParticles ? "ВКЛ" : "ВЫКЛ")));
        y += 18;
        this.buttonList.add(new GuiButton(3, width / 2 - 100, y, "Форма частиц: " + getShapeName(Config.ambientShape)));
        y += 22;

        // --- БЛОК РУК (VIEWMODEL) ---
        this.buttonList.add(new GuiButton(20, width / 2 - 100, y, "View Model: " + (Config.viewModelEnabled ? "ВКЛ" : "ВЫКЛ")));
        y += 18;
        
        // Слайдеры (делим ширину пополам, чтобы сэкономить место)
        int sliderWidth = 95;
        int leftX = width / 2 - 100;
        int rightX = width / 2 + 5;

        this.buttonList.add(new GuiSliderFixed(30, leftX, y, "Pos X", Config.vmPosX, 2.0f, -2.0f, val -> Config.vmPosX = val));
        this.buttonList.add(new GuiSliderFixed(31, rightX, y, "Pos Y", Config.vmPosY, 2.0f, -2.0f, val -> Config.vmPosY = val));
        y += 18;
        this.buttonList.add(new GuiSliderFixed(32, leftX, y, "Pos Z", Config.vmPosZ, 2.0f, -2.0f, val -> Config.vmPosZ = val));
        y += 18;
        
        this.buttonList.add(new GuiSliderFixed(33, leftX, y, "Rot X", Config.vmRotX, 360f, -360f, val -> Config.vmRotX = val));
        this.buttonList.add(new GuiSliderFixed(34, rightX, y, "Rot Y", Config.vmRotY, 360f, -360f, val -> Config.vmRotY = val));
        y += 18;
        this.buttonList.add(new GuiSliderFixed(35, leftX, y, "Rot Z", Config.vmRotZ, 360f, -360f, val -> Config.vmRotZ = val));

        // --- КНОПКА ЗАКРЫТЬ ---
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

        if (button.id == 1) Config.fogEnabled = !Config.fogEnabled;
        if (button.id == 2) Config.ambientParticles = !Config.ambientParticles;
        if (button.id == 3) Config.ambientShape = (Config.ambientShape + 1) % 3;
        if (button.id == 20) Config.viewModelEnabled = !Config.viewModelEnabled;

        if (button.id == 10) { Config.fogR += 0.1f; if (Config.fogR > 1.0f) Config.fogR = 0.0f; }
        if (button.id == 11) { Config.fogG += 0.1f; if (Config.fogG > 1.0f) Config.fogG = 0.0f; }
        if (button.id == 12) { Config.fogB += 0.1f; if (Config.fogB > 1.0f) Config.fogB = 0.0f; }
        
        initGui(); // Обновляем текст на кнопках
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(fontRenderer, "Momo Visuals Settings", width / 2, 5, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}