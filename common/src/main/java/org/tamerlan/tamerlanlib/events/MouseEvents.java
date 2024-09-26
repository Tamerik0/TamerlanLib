package org.tamerlan.tamerlanlib.events;

import net.minecraft.world.phys.Vec2;

public class MouseEvents {
    public enum MouseEventType {
        COMMON,
        CLICK,
        RELEASE,
        SCROLL;

        public final IEvent.EventType type;

        MouseEventType() {
            type = new IEvent.EventType();
        }
    }

    public static class MouseEvent implements IEvent {
        public final EventType eventType;
        public final int button;
        public final int action;
        public final int mods;
        public final Vec2 mousePos;

        public MouseEvent(IEvent.EventType eventType, int button, int action, int mods, Vec2 mousePos) {
            this.eventType = eventType;
            this.button = button;
            this.action = action;
            this.mods = mods;
            this.mousePos = mousePos;
        }

        @Override
        public EventType getEventType() {
            return eventType;
        }
    }

    class MouseScrollEvent implements IEvent {
        public final Vec2 mousePos;
        public final Vec2 scroll;

        public MouseScrollEvent(Vec2 mousePos, Vec2 scroll) {
            this.mousePos = mousePos;
            this.scroll = scroll;
        }

        @Override
        public EventType getEventType() {
            return MouseEventType.SCROLL.type;
        }
    }
}
