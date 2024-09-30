package org.tamerlan.tamerlanlib.gui.area;

import org.joml.Matrix4d;
import org.joml.Vector2d;
import org.joml.Vector4d;
import org.tamerlan.tamerlanlib.util.MathUtils;

public class TransformedArea implements IGUIArea {
    public Matrix4d transform;
    public IGUIArea area;

    public TransformedArea(Matrix4d transform, IGUIArea area) {
        this.transform = transform;
        this.area = area;
    }

    @Override
    public boolean isInsideArea(Vector2d pos) {
        return area.isInsideArea(MathUtils.toVector2d(transform.invert().transform(new Vector4d(pos, 0, 1))));
    }
}
