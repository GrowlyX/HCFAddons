package bar.pvp.hcfaddons.internal.service;

import bar.pvp.hcfaddons.internal.logger.Logger;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class ServiceManager {

    private final List<Service> services = Lists.newArrayList();

    public void register(Service service) {
        services.add(service);
    }

    public void loadServices() {
        services.forEach(service -> {
            Logger.log("[Services] Loaded " + service.getId() + " service.");
            service.enable();
        });
    }

    public void unloadServices() {
        services.forEach(service -> {
            Logger.log("[Services] Unloaded " + service.getId() + " service.");
            service.disable();
        });
        services.clear();
    }

    public <T extends Service> Optional<T> get(Class<T> serviceClass) {
        return services.stream()
                .filter(service -> service.getClass().equals(serviceClass))
                .map(serviceClass::cast)
                .findFirst();
    }

}
