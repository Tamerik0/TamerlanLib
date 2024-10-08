package org.tamerlan.tamerlanlib.events;

public class KeyboardEvents {
    public enum KeyboardEventType {
        COMMON,
        PRESS,
        RELEASE,
        REPEAT,
        CHAR;

        public final IEvent.EventType type;

        KeyboardEventType() {
            type = new IEvent.EventType();
        }
    }
    public static class KeyEvent implements IEvent{
        public final EventType eventType;
        public final int key;
        public final int scancode;
        public final int modifiers;
        public final int action;
        public KeyEvent(EventType eventType, int key, int scancode, int modifiers, int action){
            this.eventType = eventType;
            this.key = key;
            this.scancode = scancode;
            this.modifiers = modifiers;
            this.action = action;
        }

        @Override
        public EventType getEventType() {
            return eventType;
        }
    }
    public static class CharEvent implements IEvent{
        public final char character;
        public final int modifiers;

        public CharEvent(char character, int modifiers) {
            this.character = character;
            this.modifiers = modifiers;
        }

        @Override
        public EventType getEventType() {
            return KeyboardEventType.CHAR.type;
        }
    }
}
