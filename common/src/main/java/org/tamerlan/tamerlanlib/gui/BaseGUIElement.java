package org.tamerlan.tamerlanlib.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec2;
import org.tamerlan.tamerlanlib.core.IRemovable;
import org.tamerlan.tamerlanlib.events.*;
import org.tamerlan.tamerlanlib.gui.area.IAreaProvider;
import org.tamerlan.tamerlanlib.gui.area.IGUIArea;
import org.tamerlan.tamerlanlib.gui.area.RectArea;
import org.tamerlan.tamerlanlib.gui.area.TransformedArea;
import org.tamerlan.tamerlanlib.input.Mouse;

import java.util.function.Supplier;

public class BaseGUIElement implements IRenderable, GUIContainerProvider, InputHandlerProvider, IAreaProvider, IRemovable {
    public final GUIContainer container = new GUIContainer();
    public final InputHandler inputHandler = new InputHandler();
    public Supplier<Vec2> cursorPosSupplier = Mouse::getMouseScreenPos;

    public final EventHandler<MouseEvents.MouseEvent> mouseClickedOnElementEvent = new EventHandler<>();
    public final EventHandler<MouseEvents.MouseEvent> mouseReleasedFromElementEvent = new EventHandler<>();

    public final EventHandler<IEvent.CommonEvent> mouseEnteredAreaEvent = new EventHandler<>();
    public final EventHandler<IEvent.CommonEvent> mouseExitAreaEvent = new EventHandler<>();

    public InheritableTransform2D localTransform = new InheritableTransform2D(null);
    public IGUIArea localArea = new RectArea(0, 0, 100, 100);

    BaseGUIElement parent;
    boolean underCursor;

    public BaseGUIElement() {
        inputHandler.mouseEventDispatcher.addListener(MouseEvents.MouseEventType.CLICK.type, (MouseEvents.MouseEvent event) -> {
            if (underCursor) mouseClickedOnElementEvent.listenEvent(event);
        });
        inputHandler.mouseEventDispatcher.addListener(MouseEvents.MouseEventType.RELEASE.type, (MouseEvents.MouseEvent event) -> {
            if (underCursor) mouseReleasedFromElementEvent.listenEvent(event);
        });
    }

    public BaseGUIElement(Vec2 pos) {
        localTransform.pos = pos;
    }

    public BaseGUIElement(BaseGUIElement parent) {
        setParent(parent);
    }

    public BaseGUIElement(BaseGUIElement parent, Vec2 pos) {
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

    public <T extends InputHandlerProvider> T addChild(T child) {
        inputHandler.addListener(child.getInputHandler());
        return child;
    }

    public BaseGUIElement getParent() {
        return parent;
    }

    public void setParent(BaseGUIElement parent) {
        if (parent == null) {
            this.parent = null;
            return;
        }
        var oldTransform = localTransform;
        localTransform = new InheritableTransform2D(parent.localTransform);
        localTransform.pos = oldTransform.pos;
        localTransform.scale = oldTransform.scale;
        localTransform.zOffset = oldTransform.zOffset;
        localTransform.rotation = oldTransform.rotation;
        this.parent = parent;
        parent.container.addRenderable(this);
        parent.inputHandler.addListener(inputHandler);
    }

    @Override
    public GUIContainer getContainer() {
        return container;
    }

    @Override
    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public void renderWithoutTransform(GuiGraphics context) {
        var mousePos = cursorPosSupplier.get();
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
        return new TransformedArea(this.localTransform.getGlobalTransform(), this.localArea);
    }


    @Override
    public void render(GuiGraphics context) {
        new TransformedElement(this::renderWithoutTransform, localTransform).render(context);
    }

    @Override
    public void remove() {
        container.remove();
        inputHandler.remove();
        if (parent != null) {
            parent.container.removeRenderable(this);
            parent.inputHandler.removeListener(inputHandler);
        }
    }

    public static class Builder {
        BaseGUIElement element = new BaseGUIElement();

        public Builder trasform(Transform2D transform) {
            element.localTransform = new InheritableTransform2D(transform, element.localTransform.parent);
            return this;
        }

        public Builder position(Vec2 pos) {
            element.localTransform.pos = pos;
            return this;
        }

        public Builder cursorPosSupplier(Supplier<Vec2> supplier) {
            element.cursorPosSupplier = supplier;
            return this;
        }

        public Builder area(IGUIArea area) {
            element.localArea = area;
            return this;
        }

        public Builder parent(Transform2D transform2D) {
            element.localTransform.parent = transform2D;
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
