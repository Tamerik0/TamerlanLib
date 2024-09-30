package org.tamerlan.tamerlanlib.gui;

import net.minecraft.client.gui.GuiGraphics;
import org.joml.Vector2d;

public interface IRenderableWithPosition {
    void renderAtPosition(GuiGraphics context, Vector2d pos);
}
