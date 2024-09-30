package org.tamerlan.tamerlanlib.events;

import org.joml.Vector2d;

public class MouseEvents {
    public enum MouseEventType {
        COMMON,
        CLICK,
        RELEASE,
        SCROLL,
        DRAG,
        POS;

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
        public final Vector2d mousePos;

        public MouseEvent(EventType eventType, int button, int action, int mods, Vector2d mousePos) {
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

    public static class MouseScrollEvent implements IEvent {
        public final Vector2d mousePos;
        public final Vector2d scroll;

        public MouseScrollEvent(Vector2d mousePos, Vector2d scroll) {
            this.mousePos = mousePos;
            this.scroll = scroll;
        }

        @Override
        public EventType getEventType() {
            return MouseEventType.SCROLL.type;
        }
    }
    public static class MousePosEvent implements IEvent {
        public final Vector2d mousePos;
        public MousePosEvent(Vector2d mousePos) {
            this.mousePos = mousePos;
        }

        @Override
        public EventType getEventType() {
            return MouseEventType.POS.type;
        }
    }
    public static class MouseDragEvent implements IEvent {
        public final Vector2d from;
        public final Vector2d to;
        public final int button;

        public MouseDragEvent(Vector2d from, Vector2d to, int button) {
            this.from = from;
            this.to = to;
            this.button = button;
        }

        @Override
        public EventType getEventType() {
            return MouseEventType.DRAG.type;
        }
    }
}
