package org.tamerlan.tamerlanlib.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec2;
import org.joml.AxisAngle4d;
import org.joml.Quaternionf;
import org.joml.Vector3d;

public class TransformedElement extends TransformableRenderable {
    IRenderable widget;

    public TransformedElement(IRenderable widget) {
        this(widget, new Transform2D());
    }

    public TransformedElement(IRenderable widget, Transform2D transform) {
        super(transform);
        this.widget = widget;
    }


    public TransformedElement(TransformedElement other) {
        super(other);
        this.widget = other.widget;
    }


    public TransformableRenderable translate(Vec2 dir) {
        transform.translate(dir);
        return this;
    }

    public TransformableRenderable translated(Vec2 dir) {
        return new TransformableRenderable(this).translate(dir);
    }

    public TransformableRenderable setZ(int zOffset) {
        transform.zOffset = zOffset;
        return this;
    }

    public TransformableRenderable withZ(int z) {
        return new TransformableRenderable(this).setZ(z);
    }

    public TransformableRenderable scale(Vec2 scale) {
        transform.scale(scale);
        return this;
    }

    public TransformableRenderable scaled(Vec2 scale) {
        return new TransformableRenderable(this).scale(scale);
    }

    public TransformableRenderable rotate(float r) {
        transform.rotate(r);
        return this;
    }

    public TransformableRenderable rotated(float r) {
        return new TransformableRenderable(this).rotate(r);
    }

    @Override
    public void render(GuiGraphics context) {
        PoseStack matrices = context.pose();
        matrices.pushPose();
        matrices.scale(transform.scale.x, transform.scale.y, 1);
        matrices.mulPose(new Quaternionf(new AxisAngle4d(transform.rotation, new Vector3d(0, 0, 1))));
        matrices.translate(transform.pos.x, transform.pos.y, transform.zOffset);
        widget.render(context);
        matrices.popPose();
    }

}
