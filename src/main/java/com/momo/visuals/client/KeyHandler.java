package com.momo.visuals.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class KeyHandler {
    public static KeyBinding openMenu;

    public static void init() {
        openMenu = new KeyBinding("Меню Momo Visuals", Keyboard.KEY_RSHIFT, "key.categories.misc");
        ClientRegistry.registerKeyBinding(openMenu);
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        if (openMenu.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiMomoMenu(null));
        }
    }
}