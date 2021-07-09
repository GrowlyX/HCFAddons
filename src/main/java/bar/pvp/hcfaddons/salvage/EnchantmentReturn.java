package bar.pvp.hcfaddons.salvage;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.Map;

public interface EnchantmentReturn {

    ItemStack getItem();

    Map<Enchantment, Integer> getEnchantments();

    static EnchantmentReturn of(ItemStack item) {
        return new EnchantmentReturn() {
            @Override
            public ItemStack getItem() {
                final ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
                final EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                getEnchantments().forEach((enchantment, integer) -> {
                    meta.addStoredEnchant(enchantment, integer, false);
                });

                item.setItemMeta(meta);

                return item;
            }

            @Override
            public Map<Enchantment, Integer> getEnchantments() {
                return item.getEnchantments();
            }
        };
    }

}
