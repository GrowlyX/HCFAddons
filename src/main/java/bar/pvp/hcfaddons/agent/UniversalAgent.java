package bar.pvp.hcfaddons.agent;

import bar.pvp.hcfaddons.agent.faction.FactionWrapper;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class UniversalAgent implements Agent {
    @Override
    public String getName() {
        return "universal";
    }

    @Override
    public double getPlayerBalance(Player player) {
        return 20000;
    }

    @Override
    public void removeBalanceFromPlayer(Player player, double amount) {

    }

    @Override
    public void addBalanceToPlayer(Player player, double amount) {

    }

    @Override
    public Optional<FactionWrapper> retrieveFaction(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<FactionWrapper> retrieveFaction(UUID uniqueId) {
        return Optional.empty();
    }
}
