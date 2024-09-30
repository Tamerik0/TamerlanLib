package org.tamerlan.tamerlanlib.gui;

import net.minecraft.client.gui.GuiGraphics;
import org.joml.Vector2d;

public interface IRenderableWithScale {
    void renderWithScale(GuiGraphics context, Vector2d scale);
}
