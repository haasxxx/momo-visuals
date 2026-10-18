package com.momo.visuals.client;

import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FogHandler {
    @SubscribeEvent
    public void onFogColors(EntityViewRenderEvent.FogColors event) {
        if (!Config.fogEnabled) return;
        event.setRed(Config.fogR);
        event.setGreen(Config.fogG);
        event.setBlue(Config.fogB);
    }
}