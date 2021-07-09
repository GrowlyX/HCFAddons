package bar.pvp.hcfaddons.player;

import org.bukkit.entity.Player;

public interface WeakPlayerEntity extends PlayerEntity {

    /**
     * Since all weak player entities are "weak" by nature and stored inside
     * weak collection / maps. We can store a hard reference to a player entity without worrying about any
     * memory issues.
     *
     * @return the player's bukkit instance
     */
    Player getPlayer();

    @Override
    default boolean isWeak() {
        return true;
    }

    @Override
    default boolean isCacheable() {
        return false;
    }

    @Override
    default boolean isPersistent() {
        return false;
    }

}
