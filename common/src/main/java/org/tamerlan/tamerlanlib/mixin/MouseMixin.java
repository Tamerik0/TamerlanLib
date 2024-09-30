package org.tamerlan.tamerlanlib.mixin;

import net.minecraft.client.MouseHandler;
import org.joml.Vector2d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.tamerlan.tamerlanlib.events.MouseEvents;
import org.tamerlan.tamerlanlib.input.RealMouse;

@Mixin(MouseHandler.class)
public class MouseMixin {
    @Inject(method = "onPress", at = @At(value = "HEAD"))
    public void onPress(long window, int button, int action, int mods, CallbackInfo ci) {
        RealMouse.instance.events.listenEvent(new MouseEvents.MouseEvent(MouseEvents.MouseEventType.COMMON.type, button, action, mods, RealMouse.getRealMousePos()));
    }

    @Inject(method = "onScroll", at = @At(value = "HEAD"))
    public void onScroll(long window, double x, double y, CallbackInfo ci) {
        RealMouse.instance.events.listenEvent(new MouseEvents.MouseScrollEvent(RealMouse.getRealMousePos(), new Vector2d(x, y)));
    }

    @Inject(method = "onMove", at = @At(value = "HEAD"))
    public void onMove(long window, double x, double y, CallbackInfo ci) {
        RealMouse.instance.events.listenEvent(new MouseEvents.MousePosEvent(new Vector2d(x, y)));
    }

}
