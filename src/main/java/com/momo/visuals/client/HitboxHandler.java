package com.momo.visuals.client;

import artemislib.ArtemisLibAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

@SideOnly(Side.CLIENT)
public class HitboxHandler {
    private static final UUID MODIFIER_UUID = UUID.fromString("1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d");

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (Minecraft.getMinecraft().world == null) return;
        if (Minecraft.getMinecraft().world.getTotalWorldTime() % 20 != 0) return;

        for (Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (entity instanceof EntityLivingBase && !(entity instanceof net.minecraft.entity.player.EntityPlayer)) {
                EntityLivingBase living = (EntityLivingBase) entity;
                applyModifier(living, ArtemisLibAttributes.ENTITY_WIDTH, "width");
                applyModifier(living, ArtemisLibAttributes.ENTITY_HEIGHT, "height");
            }
        }
    }

    private void applyModifier(EntityLivingBase living, IAttribute attribute, String name) {
        if (attribute == null) return;
        IAttributeInstance instance = living.getEntityAttribute(attribute);
        if (instance == null) return;
        
        AttributeModifier old = instance.getModifier(MODIFIER_UUID);
        if (old != null) instance.removeModifier(old);
        if (!Config.hitboxEnabled) return;

        double amount = Config.hitboxScale - 1.0;
        if (amount > 0) {
            instance.applyModifier(new AttributeModifier(MODIFIER_UUID, "momo_hitbox_" + name, amount, 2));
        }
    }
}