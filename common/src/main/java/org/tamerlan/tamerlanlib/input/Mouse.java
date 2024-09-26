package org.tamerlan.tamerlanlib.input;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec2;

public class Mouse {
    public static Vec2 getMousePos(){
        var mouse = Minecraft.getInstance().mouseHandler;
        return new Vec2((float) mouse.xpos(), (float) mouse.ypos());
    }
    public static Vec2 getMouseScreenPos(){
        return toScreenSpace(getMousePos());
    }

    public static Vec2 toScreenSpace(Vec2 pos){
        var client = Minecraft.getInstance();
        float x = pos.x * (float)client.getWindow().getScreenWidth() / client.getWindow().getWidth();
        float y = pos.y * (float)client.getWindow().getScreenHeight() / client.getWindow().getHeight();
        return new Vec2(x, y);
    }
}
