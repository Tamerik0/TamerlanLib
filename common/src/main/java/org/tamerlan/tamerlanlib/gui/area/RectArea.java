package org.tamerlan.tamerlanlib.gui.area;

import org.joml.Vector2d;

public class RectArea implements IGUIArea {
    Vector2d p0;
    Vector2d p1;

    public RectArea(Vector2d p0, Vector2d p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    public RectArea(float x, float y, float width, float height) {
        p0 = new Vector2d(x, y);
        p1 = p0.add(new Vector2d(width, height), new Vector2d());
    }

    public RectArea(float width, float height) {
        p0 = new Vector2d(0, 0);
        p1 = p0.add(new Vector2d(width, height), new Vector2d());
    }

    @Override
    public boolean isInsideArea(Vector2d pos) {
        return pos.x >= p0.x && pos.x <= p1.x && pos.y >= p0.y && pos.y <= p1.y;
    }
}
