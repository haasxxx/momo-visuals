package com.momo.visuals.network;

import com.momo.visuals.client.Config;
import com.momo.visuals.client.particle.ParticleShape;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketHitParticle implements IMessage {
    public double x, y, z;

    public PacketHitParticle() {}
    public PacketHitParticle(double x, double y, double z) { 
        this.x = x; this.y = y; this.z = z; 
    }

    @Override 
    public void fromBytes(ByteBuf buf) { 
        x = buf.readDouble(); y = buf.readDouble(); z = buf.readDouble(); 
    }
    
    @Override 
    public void toBytes(ByteBuf buf) { 
        buf.writeDouble(x); buf.writeDouble(y); buf.writeDouble(z); 
    }

    public static class Handler implements IMessageHandler<PacketHitParticle, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketHitParticle msg, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if (!Config.hitParticles) return;
                for (int i = 0; i < 12; i++) {
                    double mx = (Math.random() - 0.5) * 0.4;
                    double my = Math.random() * 0.3 + 0.1;
                    double mz = (Math.random() - 0.5) * 0.4;
                    Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleShape(
                        Minecraft.getMinecraft().world, msg.x, msg.y + 1, msg.z, 
                        Config.hitR, Config.hitG, Config.hitB, Config.hitShape, mx, my, mz
                    ));
                }
            });
            return null;
        }
    }
}