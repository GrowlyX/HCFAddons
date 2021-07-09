package bar.pvp.hcfaddons.listener;

import bar.pvp.hcfaddons.internal.service.Services;
import bar.pvp.hcfaddons.player.impl.persist.PersistentProfile;
import bar.pvp.hcfaddons.player.impl.persist.PlayerRegistry;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;
import java.util.UUID;

public class PlayerListener implements Listener {

    private static final PlayerRegistry playerRegistry = Services.get(PlayerRegistry.class);

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncJoin(AsyncPlayerPreLoginEvent event) {
        final UUID uniqueId = event.getUniqueId();
        final String name = event.getName();

        final Optional<PersistentProfile> optional = playerRegistry.get(uniqueId);

        optional.ifPresent(playerRegistry::persistLocally);

        if (!optional.isPresent()) {
            playerRegistry.persistLocally(new PersistentProfile(uniqueId, name));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!playerRegistry.getCache().containsKey(event.getPlayer().getUniqueId())) {
            event.getPlayer().sendMessage(ChatColor.RED + "Your profile failed to load.");
            return;
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        playerRegistry.get(player.getUniqueId())
                .ifPresent(profile -> profile.save()
                        .whenComplete((unused, throwable) -> playerRegistry.remove(player)));
    }

}
