package org.tamerlan.tamerlanlib.gui;

import net.minecraft.world.phys.Vec2;
import org.joml.Matrix2f;
import org.joml.Vector2f;

public class Transform2D {
    public Vec2 pos = new Vec2(0, 0);
    public int zOffset = 0;
    public Vec2 scale = new Vec2(1, 1);
    public float rotation = 0;

    public Transform2D() {
    }
    public Transform2D(Vec2 pos) {
        this.pos = pos;
    }

    public Transform2D(Transform2D other) {
        pos = other.pos;
        zOffset = other.zOffset;
        scale = other.scale;
        rotation = other.rotation;
    }

    public Transform2D translate(Vec2 dir) {
        pos = pos.add(dir);
        return this;
    }

    public Transform2D translated(Vec2 dir) {
        return new Transform2D(this).translate(dir);
    }

    public Transform2D setZ(int zOffset) {
        this.zOffset = zOffset;
        return this;
    }

    public Transform2D withZ(int z) {
        return new Transform2D(this).setZ(z);
    }

    public Transform2D scale(Vec2 scale) {
        this.scale = new Vec2(this.scale.x * scale.x, this.scale.y * scale.y);
        return this;
    }

    public Transform2D scaled(Vec2 scale) {
        return new Transform2D(this).scale(scale);
    }

    public Transform2D rotate(float r) {
        rotation += r;
        return this;
    }

    public Transform2D rotated(float r) {
        return new Transform2D(this).rotate(r);
    }

    public Transform2D apply(Transform2D other) {
        var ans = new Transform2D(this);
        var p = new Vector2f(other.pos.x, other.pos.y).mul(new Matrix2f().rotate(rotation));
        ans.translate(new Vec2(p.x, p.y));
        ans.scale(other.scale);
        ans.rotate(other.rotation);
        ans.setZ(ans.zOffset + other.zOffset);
        return ans;
    }

    public Transform2D applyReverse(Transform2D other) {
        var ans = new Transform2D();
        ans.zOffset = other.zOffset - zOffset;
        ans.scale = new Vec2(other.scale.x / this.scale.x, other.scale.y / this.scale.y);
        ans.rotation = other.rotation - this.rotation;
        var p1 = other.pos.add(this.pos.scale(-1));
        var p = new Vector2f(p1.x, p1.y).mul(new Matrix2f().rotate(-rotation));
        ans.pos = new Vec2(p.x, p.y);
        return ans;
    }


}
