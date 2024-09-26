package org.tamerlan.tamerlanlib.gui;

import net.minecraft.client.gui.GuiGraphics;

public interface IRenderableWithRotation {
    void renderWithRotation(GuiGraphics context, float r);
}
