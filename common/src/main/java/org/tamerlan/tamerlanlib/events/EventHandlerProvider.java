package org.tamerlan.tamerlanlib.events;

public interface EventHandlerProvider<T extends IEvent> {
    EventHandler<T> getEventHandler();
}
