package org.tamerlan.tamerlanlib.gui.area;

import net.minecraft.world.phys.Vec2;
import org.tamerlan.tamerlanlib.gui.Transform2D;

public class TransformedArea implements IGUIArea {
    public Transform2D transform;
    public IGUIArea area;

    public TransformedArea(Transform2D transform, IGUIArea area) {
        this.transform = transform;
        this.area = area;
    }

    @Override
    public boolean isInsideArea(Vec2 pos) {
        return area.isInsideArea(transform.applyReverse(new Transform2D(pos)).pos);
    }
}
