package com.momo.visuals.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ParticleShape extends Particle {
    private final int shape; // 0=круг, 1=квадрат, 2=звезда
    private final float r, g, b;

    public ParticleShape(World world, double x, double y, double z, float r, float g, float b, int shape, double mx, double my, double mz) {
        super(world, x, y, z);
        this.r = r; this.g = g; this.b = b;
        this.shape = shape;
        this.particleScale = 0.25f;
        this.motionX = mx; this.motionY = my; this.motionZ = mz;
        this.particleMaxAge = 40 + rand.nextInt(20);
        this.canCollide = false;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entity, float partialTicks, float rotX, float rotZ, float rotYZ, float rotXY, float rotXZ) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE); // Аддитивное свечение

        float f = ((float) this.particleAge + partialTicks) / (float) this.particleMaxAge;
        float scale = this.particleScale * (1.0f - f * f); // Плавно исчезает

        double x = this.posX - entity.posX;
        double y = this.posY - entity.posY;
        double z = this.posZ - entity.posZ;

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buf = tess.getBuffer();
        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);

        if (this.shape == 1) { // Квадрат
            buf.pos(x - scale, y - scale, z).color(r, g, b, 1.0f).endVertex();
            buf.pos(x + scale, y - scale, z).color(r, g, b, 1.0f).endVertex();
            buf.pos(x + scale, y + scale, z).color(r, g, b, 1.0f).endVertex();
            buf.pos(x - scale, y + scale, z).color(r, g, b, 1.0f).endVertex();
        } else if (this.shape == 2) { // Звезда (упрощенная)
            buf.pos(x, y - scale, z).color(r, g, b, 1.0f).endVertex();
            buf.pos(x + scale * 0.5f, y, z).color(r, g, b, 1.0f).endVertex();
            buf.pos(x, y + scale, z).color(r, g, b, 1.0f).endVertex();
            buf.pos(x - scale * 0.5f, y, z).color(r, g, b, 1.0f).endVertex();
        } else { // Круг (упрощенный ромб)
            buf.pos(x, y - scale, z).color(r, g, b, 1.0f).endVertex();
            buf.pos(x + scale, y, z).color(r, g, b, 1.0f).endVertex();
            buf.pos(x, y + scale, z).color(r, g, b, 1.0f).endVertex();
            buf.pos(x - scale, y, z).color(r, g, b, 1.0f).endVertex();
        }
        
        tess.draw();
        
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }
}