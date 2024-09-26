package org.tamerlan.tamerlanlib.neoforge;

import org.tamerlan.tamerlanlib.Tamerlanlib;
import net.neoforged.fml.common.Mod;

@Mod(Tamerlanlib.MOD_ID)
public final class TamerlanlibNeoForge {
    public TamerlanlibNeoForge() {
        // Run our common setup.
        Tamerlanlib.init();
    }
}
