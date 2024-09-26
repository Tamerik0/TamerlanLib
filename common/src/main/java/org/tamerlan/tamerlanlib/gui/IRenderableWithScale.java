package org.tamerlan.tamerlanlib.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec2;

public interface IRenderableWithScale {
    void renderWithScale(GuiGraphics context, Vec2 scale);
}
