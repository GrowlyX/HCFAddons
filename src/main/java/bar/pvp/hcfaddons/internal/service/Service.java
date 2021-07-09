package bar.pvp.hcfaddons.internal.service;

import bar.pvp.hcfaddons.AddonsPlugin;
import bar.pvp.hcfaddons.internal.logger.Logger;

public interface Service {

    String getId();

    void enable();

    void disable();

    default void log(String str) {
        Logger.log("[" + getId() + "] " + str);
    }

    default AddonsPlugin plugin() {
        return AddonsPlugin.get();
    }

}
