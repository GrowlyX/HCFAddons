package bar.pvp.hcfaddons.token.model;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface BuyableItem extends Buyable{

    ItemStack createItem(Player player);

    @Override
    default void apply(Player player) {
        player.getInventory().addItem(createItem(player));
        player.updateInventory();
    }
}
