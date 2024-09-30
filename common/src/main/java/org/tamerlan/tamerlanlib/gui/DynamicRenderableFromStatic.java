package org.tamerlan.tamerlanlib.gui;

import net.minecraft.client.gui.GuiGraphics;
import org.joml.Vector2d;

public class DynamicRenderableFromStatic implements IDynamicRenderable{
    IRenderable base;
    public DynamicRenderableFromStatic(IRenderable base){
        this.base = base;
    }
    @Override
    public void renderAtPosition(GuiGraphics context, Vector2d pos) {
        new TransformedElement(base).translate(pos).render(context);
    }

    @Override
    public void renderWithRotation(GuiGraphics context, float r) {
        new TransformedElement(base).rotate(r).render(context);
    }

    @Override
    public void renderWithScale(GuiGraphics context, Vector2d scale) {
        new TransformedElement(base).scale(scale).render(context);
    }

    @Override
    public void renderOnZLayer(GuiGraphics context, int z) {
        new TransformedElement(base).setZ(z).render(context);
    }

    @Override
    public void renderWithTransform(GuiGraphics context, Transform2D transform) {
        new TransformedElement(base, transform).render(context);
    }
}
