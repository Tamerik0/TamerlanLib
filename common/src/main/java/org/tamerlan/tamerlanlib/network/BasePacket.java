package org.tamerlan.tamerlanlib.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public abstract class BasePacket implements CustomPacketPayload {
    private PacketType<? extends BasePacket> type;

    protected BasePacket(PacketType<? extends BasePacket> type) {
        this.type = type;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return type.type;
    }
}
