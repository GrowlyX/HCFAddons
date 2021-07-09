package bar.pvp.hcfaddons.token.model.impl;

import bar.pvp.hcfaddons.token.model.BuyableItem;
import bar.pvp.hcfaddons.token.model.TokenItem;
import bar.pvp.hcfaddons.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SwordTokenItem extends TokenItem implements BuyableItem {

    public SwordTokenItem() {
        super(50);
    }

    @Override
    public ItemStack createItem(Player player) {
        return new ItemStack(Material.DIAMOND_SWORD);
    }

    @Override
    public ItemStack createUIItem() {
        return new ItemBuilder(Material.DIAMOND_SWORD)
                .name("&eAmazing Diamond Sword")
                .lore("&fPrice: " + this.getPrice())
                .build();
    }

    @Override
    public String getName() {
        return "Amazing Diamond Sword";
    }
}
