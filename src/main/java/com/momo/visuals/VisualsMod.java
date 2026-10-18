package com.momo.visuals;

import com.momo.visuals.network.PacketHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = VisualsMod.MODID, name = VisualsMod.NAME, version = VisualsMod.VERSION, acceptedMinecraftVersions = "[1.12.2]")
public class VisualsMod {
    public static final String MODID = "visuals";
    public static final String NAME = "Momo Visuals";
    public static final String VERSION = "1.0";

    @Mod.Instance(MODID)
    public static VisualsMod instance;

    @SidedProxy(clientSide = "com.momo.visuals.ClientProxy", serverSide = "com.momo.visuals.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.init();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}