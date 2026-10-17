package com.momo.visuals;

import com.momo.visuals.client.FogHandler;
import com.momo.visuals.client.ParticleHandler;
import com.momo.visuals.client.SkyHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new FogHandler());
        MinecraftForge.EVENT_BUS.register(new SkyHandler());
        MinecraftForge.EVENT_BUS.register(new ParticleHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {
    }
}