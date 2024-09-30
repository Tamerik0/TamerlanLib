package org.tamerlan.tamerlanlib.gui.adapters;

import net.minecraft.client.gui.components.events.GuiEventListener;
import org.tamerlan.tamerlanlib.core.IRemovable;
import org.tamerlan.tamerlanlib.events.EventListener;
import org.tamerlan.tamerlanlib.events.KeyboardEventDispatcher;
import org.tamerlan.tamerlanlib.events.KeyboardEvents;
import org.tamerlan.tamerlanlib.events.MouseEvents;
import org.tamerlan.tamerlanlib.input.AbstractMouse;
import org.tamerlan.tamerlanlib.input.Keyboard;
import org.tamerlan.tamerlanlib.input.MouseDragHandler;
import org.tamerlan.tamerlanlib.input.ScreenMouse;

import java.util.ArrayList;
import java.util.List;

public class GuiEventListenerAdapter implements IRemovable {
    KeyboardEventDispatcher keyboardEventDispatcher = Keyboard.events;
    AbstractMouse mouse;
    MouseDragHandler dragHandler;
    GuiEventListener guiEventListener;
    List<EventListener> listeners = new ArrayList<>();

    public GuiEventListenerAdapter(GuiEventListener guiEventListener) {
        this(guiEventListener, ScreenMouse.instance);
    }

    public GuiEventListenerAdapter(GuiEventListener listener, AbstractMouse mouse) {
        this.mouse = mouse;
        this.guiEventListener = listener;
        dragHandler = new MouseDragHandler(mouse);
        listeners.add(keyboardEventDispatcher.addListener(KeyboardEvents.KeyboardEventType.PRESS.type, (KeyboardEvents.KeyEvent event) -> guiEventListener.keyPressed(event.key, event.scancode, event.modifiers)));
        listeners.add(keyboardEventDispatcher.addListener(KeyboardEvents.KeyboardEventType.RELEASE.type, (KeyboardEvents.KeyEvent event) -> guiEventListener.keyReleased(event.key, event.scancode, event.modifiers)));
        listeners.add(keyboardEventDispatcher.addListener(KeyboardEvents.KeyboardEventType.CHAR.type, (KeyboardEvents.CharEvent event) -> guiEventListener.charTyped(event.character, event.modifiers)));
        listeners.add(mouse.events.addListener(MouseEvents.MouseEventType.CLICK.type, (MouseEvents.MouseEvent event) -> guiEventListener.mouseClicked(event.mousePos.x, event.mousePos.y, event.button)));
        listeners.add(mouse.events.addListener(MouseEvents.MouseEventType.RELEASE.type, (MouseEvents.MouseEvent event) -> guiEventListener.mouseReleased(event.mousePos.x, event.mousePos.y, event.button)));
        listeners.add(mouse.events.addListener(MouseEvents.MouseEventType.POS.type, (MouseEvents.MousePosEvent event) -> guiEventListener.mouseMoved(event.mousePos.x, event.mousePos.y)));
        dragHandler.event.addListener(event -> guiEventListener.mouseDragged(event.from.x, event.from.y, event.button, event.to.x, event.to.y));
        listeners.add(mouse.events.addListener(MouseEvents.MouseEventType.SCROLL.type, (MouseEvents.MouseScrollEvent event) -> guiEventListener.mouseScrolled(event.mousePos.x, event.mousePos.y, event.scroll.x, event.scroll.y)));
    }

    @Override
    public void remove() {
        for (var listener : listeners) {
            keyboardEventDispatcher.removeListener(listener);
            mouse.events.removeListener(listener);
        }
        dragHandler.remove();
    }
}
