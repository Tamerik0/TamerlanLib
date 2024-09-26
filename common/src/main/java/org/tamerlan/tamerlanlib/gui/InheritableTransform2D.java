package org.tamerlan.tamerlanlib.gui;

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

    public Transform2D getGlobalTransform() {
        if (parent == null)
            return new Transform2D(this);
        if(parent instanceof InheritableTransform2D transform)
            return transform.getGlobalTransform().apply(this);
        else
            return parent.apply(this);
    }
}
