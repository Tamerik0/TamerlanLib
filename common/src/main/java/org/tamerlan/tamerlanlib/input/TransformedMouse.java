package org.tamerlan.tamerlanlib.input;

import org.joml.Matrix4d;
import org.joml.Vector2d;
import org.joml.Vector4d;
import org.tamerlan.easyteams.Easyteams;
import org.tamerlan.tamerlanlib.events.MouseEvents;
import org.tamerlan.tamerlanlib.util.MathUtils;

public class TransformedMouse extends AbstractMouse {
    AbstractMouse parent;
    public Matrix4d transform = new Matrix4d();
    Vector2d pos;

    public TransformedMouse(AbstractMouse parent) {
        this.parent = parent;
        pos = parent.getPos();
        parent.events.addListener(MouseEvents.MouseEventType.COMMON.type, (MouseEvents.MouseEvent event) -> events.listenEvent(new MouseEvents.MouseEvent(event.eventType, event.button, event.action, event.mods, updatePos(event.mousePos))));
        parent.events.addListener(MouseEvents.MouseEventType.SCROLL.type, (MouseEvents.MouseScrollEvent event) -> events.listenEvent(new MouseEvents.MouseScrollEvent(updatePos(event.mousePos), event.scroll)));
        parent.events.addListener(MouseEvents.MouseEventType.POS.type, (MouseEvents.MousePosEvent event) -> {
            events.listenEvent(new MouseEvents.MousePosEvent(updatePos(event.mousePos)));
            Easyteams.LOGGER.info(pos.x+" "+pos.y);
        });
    }

    protected Vector2d updatePos(Vector2d pos) {
        return this.pos = MathUtils.toVector2d(transform.transform(new Vector4d(pos, 0, 1)));
    }

    @Override
    public Vector2d getPos() {
        return pos;
    }
}
