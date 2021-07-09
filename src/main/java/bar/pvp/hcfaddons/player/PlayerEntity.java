package bar.pvp.hcfaddons.player;

import java.util.UUID;

public interface PlayerEntity {

    UUID getUniqueId();

    String getName();

    boolean isWeak();

    boolean isCacheable();

    boolean isPersistent();

    int getReputation();

    int getTokens();

}
