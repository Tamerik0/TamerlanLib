package org.tamerlan.tamerlanlib.network;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.impl.NetworkAggregator;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class NetworkUtil {
    public static void registerAllPackets() {
        PacketType.registerAllPacketTypes();
    }
}
