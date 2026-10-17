package com.momo.visuals.client;

import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FogHandler {

    // Настрой цвет тумана здесь: R, G, B от 0.0 до 1.0
    private static final float FOG_R = 0.8f;
    private static final float FOG_G = 0.2f;
    private static final float FOG_B = 0.2f;

    @SubscribeEvent
    public void onFogColors(EntityViewRenderEvent.FogColors event) {
        event.setRed(FOG_R);
        event.setGreen(FOG_G);
        event.setBlue(FOG_B);
    }
}