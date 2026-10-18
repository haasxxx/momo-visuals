package com.momo.visuals;

import com.momo.visuals.common.HitHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {}
    
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new HitHandler());
    }
}