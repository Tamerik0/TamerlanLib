package org.tamerlan.tamerlanlib.gui;

import org.joml.*;

public class Transform2D {
    public Vector2d pos = new Vector2d(0, 0);
    public int zOffset = 0;
    public Vector2d scale = new Vector2d(1, 1);
    public double rotation = 0;

    public Transform2D() {
    }

    public Transform2D(Vector2d pos) {
        this.pos = pos;
    }

    public Transform2D(Transform2D other) {
        pos = new Vector2d(other.pos);
        zOffset = other.zOffset;
        scale = new Vector2d(other.scale);
        rotation = other.rotation;
    }

    public Transform2D translate(Vector2d dir) {
        pos.add(dir);
        return this;
    }

    public Transform2D translated(Vector2d dir) {
        return new Transform2D(this).translate(dir);
    }

    public Transform2D setZ(int zOffset) {
        this.zOffset = zOffset;
        return this;
    }

    public Transform2D withZ(int z) {
        return new Transform2D(this).setZ(z);
    }

    public Transform2D scale(Vector2d scale) {
        this.scale = new Vector2d(this.scale.x * scale.x, this.scale.y * scale.y);
        return this;
    }

    public Transform2D scaled(Vector2d scale) {
        return new Transform2D(this).scale(scale);
    }

    public Transform2D rotate(double r) {
        rotation += r;
        return this;
    }

    public Transform2D rotated(float r) {
        return new Transform2D(this).rotate(r);
    }

    public Matrix4d toMatrix() {
        return new Matrix4d().translate(new Vector3d(pos, zOffset)).rotate(new AxisAngle4d(rotation, new Vector3d(0, 0, 1))).scale(new Vector3d(scale, 1));
    }

    public Matrix4d apply(Transform2D other) {
        return toMatrix().mul(other.toMatrix());
    }

    public Matrix4d applyReverse(Transform2D other) {
        return toMatrix().invert().mul(other.toMatrix());
    }

    public Vector4d apply(Vector4d pos) {
        return toMatrix().transform(pos);
    }

    public Vector3d apply(Vector3d pos) {
        var v = apply(new Vector4d(pos, 1));
        return new Vector3d(v.x, v.y, v.z);
    }

    public Vector2d apply(Vector2d pos) {
        var v = apply(new Vector4d(pos, 0, 1));
        return new Vector2d(v.x, v.y);
    }
    public Vector4d applyReverse(Vector4d pos){
        return toMatrix().invert().transform(pos);
    }
    public Vector3d applyReverse(Vector3d pos) {
        var v = applyReverse(new Vector4d(pos, 1));
        return new Vector3d(v.x, v.y, v.z);
    }

    public Vector2d applyReverse(Vector2d pos) {
        var v = applyReverse(new Vector4d(pos, 0, 1));
        return new Vector2d(v.x, v.y);
    }
}
