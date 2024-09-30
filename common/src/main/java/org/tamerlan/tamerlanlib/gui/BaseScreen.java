package org.tamerlan.tamerlanlib.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class BaseScreen extends Screen {
    protected BaseGUIElement base = new BaseGUIElement();

    public BaseScreen(Component component) {
        super(component);
    }

    public void init() {
        addRenderableOnly((graphics, a, b, c) -> base.render(graphics));
    }

    public void removed() {
        super.removed();
        base.remove();
    }
}
