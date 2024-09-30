package org.tamerlan.tamerlanlib.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import org.joml.Vector2d;
import org.tamerlan.tamerlanlib.core.IRemovable;
import org.tamerlan.tamerlanlib.events.*;
import org.tamerlan.tamerlanlib.gui.adapters.GUIElementAdapter;
import org.tamerlan.tamerlanlib.gui.area.IAreaProvider;
import org.tamerlan.tamerlanlib.gui.area.IGUIArea;
import org.tamerlan.tamerlanlib.gui.area.RectArea;
import org.tamerlan.tamerlanlib.gui.area.TransformedArea;
import org.tamerlan.tamerlanlib.input.AbstractMouse;
import org.tamerlan.tamerlanlib.input.ScreenMouse;

public class BaseGUIElement implements IRenderable, GUIContainerProvider, EventHandlerProvider<IEvent>, IAreaProvider, IRemovable {
    public final GUIContainer container = new GUIContainer();
    public final EventHandler<IEvent> eventHandler = new EventHandler<>();
    public AbstractMouse mouse = ScreenMouse.instance;

    public final EventHandler<MouseEvents.MouseEvent> mouseClickedOnElementEvent = new EventHandler<>();
    public final EventHandler<MouseEvents.MouseEvent> mouseReleasedFromElementEvent = new EventHandler<>();

    public final EventHandler<IEvent.CommonEvent> mouseEnteredAreaEvent = new EventHandler<>();
    public final EventHandler<IEvent.CommonEvent> mouseExitAreaEvent = new EventHandler<>();

    public InheritableTransform2D transform = new InheritableTransform2D(null);
    public IGUIArea localArea = new RectArea(0, 0, 100, 100);

    BaseGUIElement parent;
    boolean underCursor;

    public BaseGUIElement() {
        mouse.events.addListener(MouseEvents.MouseEventType.CLICK.type, (MouseEvents.MouseEvent event) -> {
            if (underCursor) mouseClickedOnElementEvent.listenEvent(event);
        });
        mouse.events.addListener(MouseEvents.MouseEventType.RELEASE.type, (MouseEvents.MouseEvent event) -> {
            if (underCursor) mouseReleasedFromElementEvent.listenEvent(event);
        });
    }

    public BaseGUIElement(Vector2d pos) {
        transform.pos = pos;
    }

    public BaseGUIElement(BaseGUIElement parent) {
        setParent(parent);
    }

    public BaseGUIElement(BaseGUIElement parent, Vector2d pos) {
        this(pos);
        setParent(parent);
    }

    public <T extends BaseGUIElement> T addChild(T child) {
        child.setParent(this);
        return child;
    }

    public <T extends IRenderable> T addChild(T child) {
        container.addRenderable(child);
        return child;
    }

    public GUIElementAdapter addChild(AbstractWidget widget) {
        return addChild(new GUIElementAdapter(widget));
    }

    public <T extends EventHandlerProvider<IEvent>> T addChild(T child) {
        return (T) eventHandler.addListener(child);
    }

    public <T extends EventListener<IEvent>> T addChild(T child) {
        return (T) eventHandler.addListener(child);
    }

    public BaseGUIElement getParent() {
        return parent;
    }

    public void setParent(BaseGUIElement parent) {
        if (parent == null) {
            this.parent = null;
            return;
        }
        var oldTransform = transform;
        transform = new InheritableTransform2D(parent.transform);
        transform.pos = oldTransform.pos;
        transform.scale = oldTransform.scale;
        transform.zOffset = oldTransform.zOffset;
        transform.rotation = oldTransform.rotation;
        this.parent = parent;
        parent.container.addRenderable(this);
        parent.eventHandler.addListener(this);
    }

    @Override
    public GUIContainer getContainer() {
        return container;
    }

    public void renderWithoutTransform(GuiGraphics context) {
        var mousePos = mouse.getPos();
        var underCursorNow = getArea().isInsideArea(mousePos);
        if (underCursor != underCursorNow) {
            if (underCursor = underCursorNow)
                mouseEnteredAreaEvent.listenEvent(new IEvent.CommonEvent());
            else mouseExitAreaEvent.listenEvent(new IEvent.CommonEvent());
        }
        container.render(context);
    }

    @Override
    public IGUIArea getArea() {
        return new TransformedArea(this.transform.getGlobalTransform(), this.localArea);
    }


    @Override
    public void render(GuiGraphics context) {
        new TransformedElement(this::renderWithoutTransform, transform).render(context);
    }

    @Override
    public void remove() {
        container.remove();
        eventHandler.remove();
        if (parent != null) {
            parent.container.removeRenderable(this);
            parent.eventHandler.removeListener(eventHandler);
        }
    }

    @Override
    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public static class Builder {
        BaseGUIElement element = new BaseGUIElement();

        public Builder trasform(Transform2D transform) {
            element.transform = new InheritableTransform2D(transform, element.transform.parent);
            return this;
        }

        public Builder position(Vector2d pos) {
            element.transform.pos = pos;
            return this;
        }

        public Builder area(IGUIArea area) {
            element.localArea = area;
            return this;
        }

        public Builder parent(Transform2D transform2D) {
            element.transform.parent = transform2D;
            return this;
        }

        public Builder parent(BaseGUIElement transform2D) {
            element.setParent(transform2D);
            return this;
        }

        public Builder addChild(BaseGUIElement child) {
            element.addChild(child);
            return this;
        }

        public BaseGUIElement build() {
            return element;
        }
    }
}
