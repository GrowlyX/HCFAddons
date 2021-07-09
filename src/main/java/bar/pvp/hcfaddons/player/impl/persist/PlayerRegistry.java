package bar.pvp.hcfaddons.player.impl.persist;

import bar.pvp.hcfaddons.internal.registry.Registry;

public class PlayerRegistry extends Registry<PersistentProfile> {

    @Override
    public Class<PersistentProfile> getEntityType() {
        return PersistentProfile.class;
    }

    @Override
    public String getId() {
        return "profiles";
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }
}
