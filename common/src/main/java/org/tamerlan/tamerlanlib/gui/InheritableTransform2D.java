package org.tamerlan.tamerlanlib.gui;

import org.joml.Matrix4d;

public class InheritableTransform2D extends Transform2D {
    Transform2D parent;

    public InheritableTransform2D(InheritableTransform2D parent) {
        this.parent = parent;
    }
    public InheritableTransform2D(Transform2D transform){
        super(transform);
    }
    public InheritableTransform2D(Transform2D transform, Transform2D parent){
        super(transform);
        this.parent = parent;
    }

    public Matrix4d getGlobalTransform() {
        if (parent == null)
            return toMatrix();
        if(parent instanceof InheritableTransform2D transform)
            return transform.getGlobalTransform().mul(toMatrix());
        else
            return parent.apply(this);
    }
}
