package org.tamerlan.tamerlanlib.gui.area;


import net.minecraft.world.phys.Vec2;

public class RectArea implements IGUIArea {
    Vec2 p0;
    Vec2 p1;

    public RectArea(Vec2 p0, Vec2 p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    public RectArea(float x, float y, float width, float height) {
        p0 = new Vec2(x, y);
        p1 = p0.add(new Vec2(width, height));
    }
    public RectArea(float width, float height) {
        p0 = new Vec2(0, 0);
        p1 = p0.add(new Vec2(width, height));
    }

    @Override
    public boolean isInsideArea(Vec2 pos) {
        return pos.x >= p0.x && pos.x <= p1.x && pos.y >= p0.y && pos.y <= p1.y;
    }
}
