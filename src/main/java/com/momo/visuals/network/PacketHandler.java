package com.momo.visuals.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("momo_visuals");
    
    public static void init() {
        INSTANCE.registerMessage(PacketHitParticle.Handler.class, PacketHitParticle.class, 0, Side.CLIENT);
    }
}