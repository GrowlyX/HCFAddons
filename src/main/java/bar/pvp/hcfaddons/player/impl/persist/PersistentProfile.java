package bar.pvp.hcfaddons.player.impl.persist;

import bar.pvp.hcfaddons.internal.mongo.model.Entity;
import bar.pvp.hcfaddons.internal.schedulers.Schedulers;
import bar.pvp.hcfaddons.internal.service.Services;
import bar.pvp.hcfaddons.player.PersistentPlayerEntity;
import bar.pvp.hcfaddons.player.impl.AbstractProfile;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Entity
public class PersistentProfile extends AbstractProfile implements PersistentPlayerEntity {

    public PersistentProfile(UUID uniqueId, String name) {
        super(uniqueId, name);
    }

    @Override
    public CompletableFuture<Void> save() {
        final CompletableFuture<Void> future = new CompletableFuture<>();

        Schedulers.async(() -> {
            final PlayerRegistry playerRegistry = Services.get(PlayerRegistry.class);

            playerRegistry.save(this);
            future.complete(null);
        });

        return future;
    }
}
