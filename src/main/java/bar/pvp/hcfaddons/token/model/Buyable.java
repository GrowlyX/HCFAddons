package bar.pvp.hcfaddons.token.model;

import org.bukkit.entity.Player;

public interface Buyable {

    int getPrice();

    void apply(Player player);

}
