package com.momo.visuals.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class ParticleHandler {

    private static final Random RAND = new Random();

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.world == null || mc.player == null) return;

        // Спавним каждые 3 тика, чтобы не грузить систему
        if (mc.world.getTotalWorldTime() % 3 != 0) return;

        double x = mc.player.posX + (RAND.nextDouble() - 0.5) * 24;
        double y = mc.player.posY + RAND.nextDouble() * 12;
        double z = mc.player.posZ + (RAND.nextDouble() - 0.5) * 24;

        mc.world.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0, 0, 0);
    }
}