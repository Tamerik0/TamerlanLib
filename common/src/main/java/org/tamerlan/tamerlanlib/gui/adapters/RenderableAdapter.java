package org.tamerlan.tamerlanlib.gui.adapters;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import org.tamerlan.tamerlanlib.gui.IRenderable;
import org.tamerlan.tamerlanlib.input.AbstractMouse;

public class RenderableAdapter implements IRenderable {
    Renderable widget;
    AbstractMouse mouse;

    public RenderableAdapter(Renderable widget, AbstractMouse mouse) {
        this.widget = widget;
        this.mouse = mouse;
    }

    @Override
    public void render(GuiGraphics context) {
        widget.render(context, (int) mouse.getPos().x, (int) mouse.getPos().y, 0);
    }
}
