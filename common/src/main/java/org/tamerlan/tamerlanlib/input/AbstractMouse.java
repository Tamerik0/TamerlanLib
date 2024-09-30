package org.tamerlan.tamerlanlib.input;

import net.minecraft.client.Minecraft;
import org.joml.Vector2d;
import org.tamerlan.tamerlanlib.events.MouseEventDispatcher;

public abstract class AbstractMouse {

    public static Vector2d getRealMousePos() {
        var mouse = Minecraft.getInstance().mouseHandler;
        return new Vector2d((float) mouse.xpos(), (float) mouse.ypos());
    }

    public static Vector2d toScreenSpace(Vector2d pos) {
        var client = Minecraft.getInstance();
        double x = pos.x * client.getWindow().getGuiScaledWidth() / client.getWindow().getScreenWidth();
        double y = pos.y * client.getWindow().getGuiScaledHeight() / client.getWindow().getScreenHeight();
        return new Vector2d(x, y);
    }

    public abstract Vector2d getPos();

    public final MouseEventDispatcher events = new MouseEventDispatcher();
}
