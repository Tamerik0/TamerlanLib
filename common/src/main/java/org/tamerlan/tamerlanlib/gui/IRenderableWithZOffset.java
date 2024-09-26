package org.tamerlan.tamerlanlib.gui;

import net.minecraft.client.gui.GuiGraphics;

public interface IRenderableWithZOffset {
    void renderOnZLayer(GuiGraphics context, int z);
}
