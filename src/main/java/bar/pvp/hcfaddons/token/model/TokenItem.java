package bar.pvp.hcfaddons.token.model;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

@Data
public abstract class TokenItem implements Buyable {

    private final int price;

    public abstract ItemStack createUIItem();

    public abstract String getName();

}
