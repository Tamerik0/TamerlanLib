package org.tamerlan.tamerlanlib.gui.adapters;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import org.tamerlan.tamerlanlib.gui.BaseGUIElement;
import org.tamerlan.tamerlanlib.input.ScreenMouse;
import org.tamerlan.tamerlanlib.input.TransformedMouse;

public class GUIElementAdapter extends BaseGUIElement {
    RenderableAdapter renderable;
    GuiEventListenerAdapter listener;
    TransformedMouse mouse = new TransformedMouse(ScreenMouse.instance);
    public GUIElementAdapter(AbstractWidget widget){
        this.listener = new GuiEventListenerAdapter(widget, mouse);
        this.renderable = new RenderableAdapter(widget, mouse);
        init();
    }
    public GUIElementAdapter(GuiEventListener listener){
        this.listener = new GuiEventListenerAdapter(listener, mouse);
        init();
    }
    public GUIElementAdapter(Renderable renderable){
        this.renderable = new RenderableAdapter(renderable, mouse);
        init();
    }
    public void render(GuiGraphics context){
        var c = transform.getGlobalTransform();
        var c1 = transform.getGlobalTransform().invert();
        mouse.transform = transform.getGlobalTransform().invert();
        super.render(context);
    }
    private void init(){
        if(renderable != null)
            addChild(renderable);
    }

    @Override
    public void remove() {
        super.remove();
    }
}
