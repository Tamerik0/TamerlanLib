package org.tamerlan.tamerlanlib.gui.area;

import net.minecraft.world.phys.Vec2;

public class CircleArea implements IGUIArea {
    Vec2 pos;
    float radius;

    public CircleArea(float radius) {
        this(new Vec2(0, 0), radius);
    }

    public CircleArea(Vec2 pos, float radius) {
        this.pos = pos;
        this.radius = radius;
    }

    @Override
    public boolean isInsideArea(Vec2 pos) {
        return pos.distanceToSqr(this.pos) <= radius * radius;
    }
}
