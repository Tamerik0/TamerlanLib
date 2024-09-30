package org.tamerlan.tamerlanlib.events;

import java.util.List;

public class MouseEventDispatcher extends EventDispatcher {
    public MouseEventDispatcher(){
        addHandler(MouseEvents.MouseEventType.COMMON.type, new EventHandler<MouseEvents.MouseEvent>());
        addHandler(MouseEvents.MouseEventType.CLICK.type, new EventHandler<MouseEvents.MouseEvent>());
        addHandler(MouseEvents.MouseEventType.RELEASE.type, new EventHandler<MouseEvents.MouseEvent>());
        addHandler(MouseEvents.MouseEventType.SCROLL.type, new EventHandler<MouseEvents.MouseScrollEvent>());
        addHandler(MouseEvents.MouseEventType.POS.type, new EventHandler<MouseEvents.MouseDragEvent>());
    }
    @Override
    protected List<IEvent> dispatchEvent(IEvent event) {
        if (event instanceof MouseEvents.MouseEvent mouseEvent) {
            if(event.getEventType() == MouseEvents.MouseEventType.COMMON.type) {
                if (mouseEvent.action == 1) {
                    return List.of(new MouseEvents.MouseEvent(MouseEvents.MouseEventType.CLICK.type, mouseEvent.button, mouseEvent.action, mouseEvent.mods, mouseEvent.mousePos));
                } else if (mouseEvent.action == 0) {
                    return List.of(new MouseEvents.MouseEvent(MouseEvents.MouseEventType.RELEASE.type, mouseEvent.button, mouseEvent.action, mouseEvent.mods, mouseEvent.mousePos));
                }
            }
        }
        return List.of();
    }
}
