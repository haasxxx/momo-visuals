package com.momo.visuals.client;

import com.momo.visuals.client.particle.ParticleShape;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.Random;

public class ParticleHandler {
    private static final Random RAND = new Random();

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.world == null || mc.player == null || !Config.ambientParticles) return;
        
        // Спавним каждые 3 тика
        if (mc.world.getTotalWorldTime() % 3 != 0) return;

        double x = mc.player.posX + (RAND.nextDouble() - 0.5) * 20;
        double y = mc.player.posY + RAND.nextDouble() * 10;
        double z = mc.player.posZ + (RAND.nextDouble() - 0.5) * 20;

        mc.effectRenderer.addEffect(new ParticleShape(
            mc.world, x, y, z, 
            Config.ambientR, Config.ambientG, Config.ambientB, 
            Config.ambientShape, 
            0, 0.01, 0
        ));
    }
}