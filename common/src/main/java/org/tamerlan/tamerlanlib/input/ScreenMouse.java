package org.tamerlan.tamerlanlib.input;

import org.joml.Vector2d;
import org.tamerlan.tamerlanlib.events.MouseEvents;

public class ScreenMouse extends AbstractMouse {
    public static ScreenMouse instance = new ScreenMouse();
    Vector2d pos;

    public ScreenMouse() {
        pos = RealMouse.instance.getPos();
        RealMouse.instance.events.addListener(MouseEvents.MouseEventType.COMMON.type, (MouseEvents.MouseEvent event) -> events.listenEvent(new MouseEvents.MouseEvent(event.eventType, event.button, event.action, event.mods, updatePos(event.mousePos))));
        RealMouse.instance.events.addListener(MouseEvents.MouseEventType.SCROLL.type, (MouseEvents.MouseScrollEvent event) -> events.listenEvent(new MouseEvents.MouseScrollEvent(updatePos(event.mousePos), event.scroll)));
        RealMouse.instance.events.addListener(MouseEvents.MouseEventType.POS.type, (MouseEvents.MousePosEvent event) -> {
            events.listenEvent(new MouseEvents.MousePosEvent(updatePos(event.mousePos)));
        });
    }
    protected Vector2d updatePos(Vector2d pos) {
        return this.pos = AbstractMouse.toScreenSpace(pos);
    }
    @Override
    public Vector2d getPos() {
        return pos;
    }
}
