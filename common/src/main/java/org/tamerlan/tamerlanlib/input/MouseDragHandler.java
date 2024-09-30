package org.tamerlan.tamerlanlib.input;

import org.joml.Vector2d;
import org.tamerlan.tamerlanlib.core.IRemovable;
import org.tamerlan.tamerlanlib.events.EventHandler;
import org.tamerlan.tamerlanlib.events.MouseEvents;

public class MouseDragHandler implements IRemovable {
    AbstractMouse mouse;
    public final EventHandler<MouseEvents.MouseDragEvent> event = new EventHandler<>();
    int activeButton = -1;
    Vector2d startPos;

    public MouseDragHandler(AbstractMouse mouse) {
        this.mouse = mouse;
        mouse.events.addListener(MouseEvents.MouseEventType.CLICK.type, (MouseEvents.MouseEvent event) -> {
            if (activeButton == -1) {
                activeButton = event.button;
                startPos = event.mousePos;
            }
        });
        mouse.events.addListener(MouseEvents.MouseEventType.RELEASE.type, (MouseEvents.MouseEvent event) -> {
            if (event.button == activeButton)
                activeButton = -1;
        });
        mouse.events.addListener(MouseEvents.MouseEventType.POS.type, (MouseEvents.MousePosEvent event) -> {
            if (activeButton != -1)
                this.event.listenEvent(new MouseEvents.MouseDragEvent(startPos, event.mousePos, activeButton));
        });
    }

    @Override
    public void remove() {
        event.remove();
    }
}
