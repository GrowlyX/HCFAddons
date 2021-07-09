package bar.pvp.hcfaddons.player;

import java.util.concurrent.CompletableFuture;

public interface PersistentPlayerEntity extends CacheablePlayerEntity {

    CompletableFuture<Void> save();

    @Override
    default boolean isWeak() {
        return false;
    }

    @Override
    default boolean isPersistent() {
        return true;
    }

    void setReputation(int rep);

    void setTokens(int tokens);

}
