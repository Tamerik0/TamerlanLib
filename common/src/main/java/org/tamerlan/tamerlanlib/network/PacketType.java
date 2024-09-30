package org.tamerlan.tamerlanlib.network;

import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.tamerlan.tamerlanlib.events.EventHandler;
import org.tamerlan.tamerlanlib.events.IEvent;

import java.util.ArrayList;
import java.util.List;

public class PacketType<T extends CustomPacketPayload> {
    public class OnPacketEvent extends IEvent.CommonEvent {
        public T packet;
        public NetworkManager.PacketContext context;

        public OnPacketEvent(T packet, NetworkManager.PacketContext context) {
            this.packet = packet;
            this.context = context;
        }
    }

    public CustomPacketPayload.Type<T> type;

    public StreamCodec<RegistryFriendlyByteBuf, T> codec;

    public NetworkManager.Side side;
    boolean registered = false;
    private static final List<PacketType> instances = new ArrayList<>();

    public PacketType(NetworkManager.Side side, CustomPacketPayload.Type<T> type, StreamCodec<RegistryFriendlyByteBuf, T> codec, boolean autoRegister) {
        this.side = side;
        this.type = type;
        this.codec = codec;
        instances.add(this);
        if (autoRegister)
            register();
    }

    public PacketType(NetworkManager.Side side, CustomPacketPayload.Type<T> type, StreamCodec<RegistryFriendlyByteBuf, T> codec) {
        this(side, type, codec, true);
    }

    public PacketType(NetworkManager.Side side, ResourceLocation type, StreamCodec<RegistryFriendlyByteBuf, T> codec) {
        this(side, type, codec, true);
    }

    public PacketType(NetworkManager.Side side, ResourceLocation type, StreamCodec<RegistryFriendlyByteBuf, T> codec, boolean autoRegister) {
        this(side, new CustomPacketPayload.Type<>(type), codec);
    }

    public final EventHandler<OnPacketEvent> event = new EventHandler<>();

    public void register() {
        if (registered)
            return;
        if (side.equals(NetworkManager.s2c())) {
            if (Platform.getEnvironment() == Env.CLIENT) {
                NetworkManager.registerReceiver(side, type, codec, ((packet, packetContext) -> event.listenEvent(new OnPacketEvent(packet, packetContext))));
            } else {
                NetworkManager.registerS2CPayloadType(type, codec);
            }
        } else {
            NetworkManager.registerReceiver(side, type, codec, (packet, context) -> event.listenEvent(new OnPacketEvent(packet, context)));
        }
        registered = true;
    }

    static void registerAllPacketTypes() {
        for (var type : instances)
            type.register();
    }
}
