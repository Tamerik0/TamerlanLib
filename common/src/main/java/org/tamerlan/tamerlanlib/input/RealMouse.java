package org.tamerlan.tamerlanlib.input;

import org.joml.Vector2d;
import org.tamerlan.tamerlanlib.events.MouseEvents;

public class RealMouse extends AbstractMouse {
    public static RealMouse instance = new RealMouse();
    Vector2d pos = new Vector2d(0, 0);

    public RealMouse() {
        events.addListener(MouseEvents.MouseEventType.POS.type, (MouseEvents.MousePosEvent event) -> pos = event.mousePos);
    }

    @Override
    public Vector2d getPos() {
        return pos;
    }
}
