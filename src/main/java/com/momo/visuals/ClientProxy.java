package com.momo.visuals;

import com.momo.visuals.client.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        KeyHandler.init();
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
        MinecraftForge.EVENT_BUS.register(new FogHandler());
        MinecraftForge.EVENT_BUS.register(new SkyHandler());
        MinecraftForge.EVENT_BUS.register(new ParticleHandler());
        MinecraftForge.EVENT_BUS.register(new ViewModelHandler());
        MinecraftForge.EVENT_BUS.register(new HitboxHandler()); // Регистрация хитбоксов
    }
}