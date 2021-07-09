package bar.pvp.hcfaddons.internal.logger;

import bar.pvp.hcfaddons.AddonsPlugin;

public class Logger {

    public static void log(String message) {
        AddonsPlugin.get().getLogger().info(message);
    }

}
