package org.tamerlan.tamerlanlib.gui.area;

import org.joml.Vector2d;

public class CircleArea implements IGUIArea {
    Vector2d pos;
    float radius;

    public CircleArea(float radius) {
        this(new Vector2d(0, 0), radius);
    }

    public CircleArea(Vector2d pos, float radius) {
        this.pos = pos;
        this.radius = radius;
    }

    @Override
    public boolean isInsideArea(Vector2d pos) {
        return pos.distance(this.pos) <= radius;
    }
}
