package bar.pvp.hcfaddons.token.model;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public interface BuyableCommand extends Buyable{

    String createCommand(Player player);

    @Override
    default void apply(Player player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), createCommand(player));
    }
}
