package bar.pvp.hcfaddons.utils;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static bar.pvp.hcfaddons.utils.ColorUtils.translate;

@Getter
public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    private List<String> lore = new ArrayList<>();

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
        if (itemMeta.hasLore()) {
            this.lore = itemMeta.getLore();
        }
    }

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder name(String name) {
        itemMeta.setDisplayName(translate(name));
        return this;
    }

    public ItemBuilder lore(String lore) {
        this.lore.add(translate(lore));

        return this;
    }

    public ItemBuilder lore(String... lores) {
        for (String s : lores) {
            lore(s);
        }
        return this;
    }

    public ItemBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder dur(int dur) {
        itemStack.setDurability((short) dur);

        return this;
    }

    public ItemStack build() {
        if (itemMeta != null) {
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }

        return itemStack;
    }


    public ItemBuilder lore(List<String> lore) {
        for (String str : lore) {
            lore(str);
        }
        return this;
    }

}