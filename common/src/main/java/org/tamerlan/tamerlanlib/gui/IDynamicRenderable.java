package org.tamerlan.tamerlanlib.gui;

import net.minecraft.client.gui.GuiGraphics;

public interface IDynamicRenderable extends IRenderableWithPosition, IRenderableWithScale, IRenderableWithRotation, IRenderableWithZOffset {
    void renderWithTransform(GuiGraphics context, Transform2D transform);
}
