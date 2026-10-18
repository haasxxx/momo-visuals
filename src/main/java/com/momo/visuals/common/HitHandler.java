package com.momo.visuals.common;

import com.momo.visuals.network.PacketHandler;
import com.momo.visuals.network.PacketHitParticle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class HitHandler {
    @SubscribeEvent
    public void onHurt(LivingHurtEvent event) {
        if (event.getEntityLiving().world.isRemote) return;
        
        EntityLivingBase target = event.getEntityLiving();
        PacketHandler.INSTANCE.sendToAllAround(
            new PacketHitParticle(target.posX, target.posY, target.posZ),
            new NetworkRegistry.TargetPoint(target.dimension, target.posX, target.posY, target.posZ, 64.0D)
        );
    }
}