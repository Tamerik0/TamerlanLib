package org.tamerlan.tamerlanlib.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec2;

public interface IRenderableWithPosition {
    void renderAtPosition(GuiGraphics context, Vec2 pos);
}
