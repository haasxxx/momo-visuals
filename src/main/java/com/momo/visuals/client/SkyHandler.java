package com.momo.visuals.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class SkyHandler {
    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        if (!Config.skyEnabled) return;
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;

        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GlStateManager.pushMatrix();
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0, mc.displayWidth, mc.displayHeight, 0, -1, 1);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buf = tess.getBuffer();
        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);

        buf.pos(0, 0, 0).color(Config.skyTopR, Config.skyTopG, Config.skyTopB, 0.2f).endVertex();
        buf.pos(mc.displayWidth, 0, 0).color(Config.skyTopR, Config.skyTopG, Config.skyTopB, 0.2f).endVertex();
        buf.pos(mc.displayWidth, mc.displayHeight, 0).color(Config.skyBotR, Config.skyBotG, Config.skyBotB, 0.2f).endVertex();
        buf.pos(0, mc.displayHeight, 0).color(Config.skyBotR, Config.skyBotG, Config.skyBotB, 0.2f).endVertex();

        tess.draw();

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.popMatrix();

        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
    }
}