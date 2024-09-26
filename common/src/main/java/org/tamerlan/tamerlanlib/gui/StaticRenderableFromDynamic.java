package org.tamerlan.tamerlanlib.gui;

import net.minecraft.client.gui.GuiGraphics;

public class StaticRenderableFromDynamic implements IRenderable{
    public final Transform2D transform = new Transform2D();
    private final IDynamicRenderable base;

    public StaticRenderableFromDynamic(IDynamicRenderable base) {
        this.base = base;
    }

    @Override
    public void render(GuiGraphics context) {
        base.renderWithTransform(context, transform);
    }
}
