package bar.pvp.hcfaddons.agent;

import bar.pvp.hcfaddons.agent.faction.FactionWrapper;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface Agent {

    String getName();

    double getPlayerBalance(Player player);

    void removeBalanceFromPlayer(Player player, double amount);

    void addBalanceToPlayer(Player player, double amount);

    Optional<FactionWrapper> retrieveFaction(String name);

    Optional<FactionWrapper> retrieveFaction(UUID uniqueId);

}
