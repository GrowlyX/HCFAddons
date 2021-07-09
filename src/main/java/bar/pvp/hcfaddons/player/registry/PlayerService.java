package bar.pvp.hcfaddons.player.registry;

import bar.pvp.hcfaddons.internal.service.Service;
import bar.pvp.hcfaddons.player.WeakPlayerEntity;
import bar.pvp.hcfaddons.player.impl.persist.PlayerRegistry;
import lombok.Getter;

import java.util.UUID;
import java.util.WeakHashMap;

@Getter
public class PlayerService implements Service {

    /**
     * Only for running commands, we can store them temporarily just in case we need to refer to them again in the instant but
     * it's better for weak references to just expire naturally.
     */
    private final WeakHashMap<UUID, WeakPlayerEntity> weakPlayerMap = new WeakHashMap<>();
    private final PlayerRegistry playerRegistry = new PlayerRegistry();

    @Override
    public String getId() {
        return "players";
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }
}
