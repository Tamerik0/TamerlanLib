package org.tamerlan.tamerlanlib.input;

import org.joml.Vector2d;
import org.tamerlan.tamerlanlib.events.MouseEvents;

public class DummyMouse extends AbstractMouse {
    Vector2d pos;

    @Override
    public Vector2d getPos() {
        return pos;
    }

    public void setPos(Vector2d pos) {
        this.pos = pos;
        events.listenEvent(new MouseEvents.MousePosEvent(pos));
    }

    public void setCallbacks() {
        RealMouse.instance.events.addListener(MouseEvents.MouseEventType.COMMON.type, events::listenEvent);
        RealMouse.instance.events.addListener(MouseEvents.MouseEventType.SCROLL.type, events::listenEvent);
    }
}
