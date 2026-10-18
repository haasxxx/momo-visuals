package com.momo.visuals.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
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
        this.buttonList.add(new GuiSlider(new GuiSlider.FormatHelper() {
            public String getText(int id, String name, float value) { return "Цвет тумана: " + String.format("%.2f", value); }
        }, 10, width / 2 - 100, y, 200, 20, "Fog Color", 0.0, 1.0, Config.fogR, true, true));
        
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

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) mc.displayGuiScreen(parent);
        if (button.id == 1) Config.fogEnabled = !Config.fogEnabled;
        if (button.id == 2) Config.ambientParticles = !Config.ambientParticles;
        if (button.id == 3) Config.ambientShape = (Config.ambientShape + 1) % 3;
        if (button.id == 4) Config.hitParticles = !Config.hitParticles;
        if (button.id == 5) Config.hitShape = (Config.hitShape + 1) % 3;
        initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(fontRenderer, "Momo Visuals Settings", width / 2, 10, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}