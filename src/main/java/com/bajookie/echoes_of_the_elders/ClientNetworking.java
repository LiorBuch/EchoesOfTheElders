package com.bajookie.echoes_of_the_elders;

import com.bajookie.echoes_of_the_elders.system.Capability.IHasCapability;
import com.bajookie.echoes_of_the_elders.system.Capability.ModCapabilities;
import com.bajookie.echoes_of_the_elders.system.Raid.networking.c2s.RequestCapabilitySync;
import com.bajookie.echoes_of_the_elders.system.Raid.networking.s2c.CapabilitySync;
import com.bajookie.echoes_of_the_elders.system.screen_switch.ScreenSwitchCapability;
import com.bajookie.echoes_of_the_elders.util.EntityUtil;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.LivingEntity;

public class ClientNetworking {
    public static void init() {
        ClientEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof LivingEntity livingEntity) {
                RequestCapabilitySync.send(livingEntity);
            }
        });

        ClientPlayNetworking.registerGlobalReceiver(CapabilitySync.TYPE, ((packet, player, responseSender) -> {
            var world = player.getWorld();
            var entityToSync = EntityUtil.getEntityByUUID(world, packet.entityUuid());
            if (entityToSync instanceof IHasCapability iHasCapability) {
                iHasCapability.echoesOfTheElders$setCapabilities(packet.capabilities());
            }
        }));
    }
}
