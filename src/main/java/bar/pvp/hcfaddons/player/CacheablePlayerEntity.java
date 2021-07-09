package bar.pvp.hcfaddons.player;

public interface CacheablePlayerEntity extends PlayerEntity {

    @Override
    default boolean isCacheable() {
        return true;
    }

}
