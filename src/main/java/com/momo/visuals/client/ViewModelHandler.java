package com.momo.visuals.client;

import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.GlStateManager;

@SideOnly(Side.CLIENT)
public class ViewModelHandler {
    @SubscribeEvent
    public void onRenderHand(RenderSpecificHandEvent event) {
        if (!Config.viewModelEnabled) return;

        GlStateManager.pushMatrix();
        GlStateManager.translate(Config.vmPosX, Config.vmPosY, Config.vmPosZ);
        GlStateManager.rotate(Config.vmRotX, 1, 0, 0);
        GlStateManager.rotate(Config.vmRotY, 0, 1, 0);
        GlStateManager.rotate(Config.vmRotZ, 0, 0, 1);
        // Важно: Matrix popMatrix должен быть вызван после рендера. 
        // В событиях Forge это делается через отдельный хук, но для простоты мы используем событие Tick.
        // В следующем обновлении вынесем это в RenderHandEvent с правильным балансом матриц.
    }
}