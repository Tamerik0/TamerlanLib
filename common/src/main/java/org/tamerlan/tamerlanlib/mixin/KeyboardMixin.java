package org.tamerlan.tamerlanlib.mixin;

import net.minecraft.client.KeyboardHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.tamerlan.tamerlanlib.events.KeyboardEvents;
import org.tamerlan.tamerlanlib.input.Keyboard;

@Mixin(KeyboardHandler.class)
public class KeyboardMixin {
    @Inject(method = "keyPress", at = @At(value = "HEAD"))
    void keyPress(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        Keyboard.events.listenEvent(new KeyboardEvents.KeyEvent(KeyboardEvents.KeyboardEventType.COMMON.type, key, scancode, modifiers, action));
    }

    @Inject(method = "charTyped", at = @At(value = "HEAD"))
    void charTyped(long window, int codepoint, int mods, CallbackInfo ci) {
        if (Character.charCount(codepoint) == 1) {
            Keyboard.events.listenEvent(new KeyboardEvents.CharEvent((char) codepoint, mods));
        } else {
            for (var c : Character.toChars(codepoint))
                Keyboard.events.listenEvent(new KeyboardEvents.CharEvent(c, mods));
        }
    }
}
