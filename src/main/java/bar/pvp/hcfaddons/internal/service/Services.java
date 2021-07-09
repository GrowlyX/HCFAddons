package bar.pvp.hcfaddons.internal.service;

import bar.pvp.hcfaddons.AddonsPlugin;

public class Services {

    public static <T extends Service> T get(Class<T> serviceClass) {
        return AddonsPlugin.get().getServiceManager().get(serviceClass).orElse(null);
    }

}
