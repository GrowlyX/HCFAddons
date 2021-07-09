package bar.pvp.hcfaddons.salvage;

import bar.pvp.hcfaddons.utils.ItemBuilder;
import me.blazingtide.phoenix.GUI;
import me.blazingtide.phoenix.button.Button;
import me.blazingtide.phoenix.button.builder.ButtonBuilder;
import me.blazingtide.phoenix.result.TickResult;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public class SalvageUI extends GUI {

    private ItemStack selectedItem;
    private boolean completed;

    public SalvageUI(Player player) {
        super(player, "Salvage", 54);
    }

    @Override
    public Optional<TickResult> onTick() {
        for (int i = 0; i < getSize(); i++) {
            buttons[i] = new ButtonBuilder()
                    .withItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE))
                    .withGUI(this)
                    .build(player);
        }

        if (selectedItem != null) {
            buttons[13] = new Button(player, this, selectedItem, (event -> {
                player.getInventory().addItem(selectedItem);
                player.updateInventory();

                selectedItem = null;
                update();
            }));
        } else {
            buttons[13] = null;
        }

        if (selectedItem != null) {
            final SalvageableItem item = Arrays.stream(SalvageableItem.values())
                    .filter(salvageableItem -> salvageableItem.getSource().equals(selectedItem.getType()))
                    .findFirst().get();

            final SalvageReturn left = item.getReturns()[0];
            final SalvageReturn right = item.getReturns()[1];

            buttons[30] = new ButtonBuilder()
                    .withItem(new ItemStack(left.getMaterial(), left.getAmount()))
                    .withGUI(this)
                    .build(player);

            final EnchantmentReturn enchantmentReturn = EnchantmentReturn.of(selectedItem);

            if (!enchantmentReturn.getEnchantments().isEmpty()) {
                buttons[32] = new ButtonBuilder()
                        .withItem(enchantmentReturn.getItem())
                        .withGUI(this)
                        .build(player);
            }

            //If the enchants are empty then display this part in the enchants portion to make the GUI
            //look much cleaner
            if (enchantmentReturn.getEnchantments().isEmpty()) {
                buttons[32] = new ButtonBuilder()
                        .withItem(new ItemStack(right.getMaterial(), right.getAmount()))
                        .withGUI(this)
                        .build(player);
            } else {
                buttons[31] = new ButtonBuilder()
                        .withItem(new ItemStack(right.getMaterial(), right.getAmount()))
                        .withGUI(this)
                        .build(player);
            }

            buttons[49] = new ButtonBuilder()
                    .withItem(new ItemBuilder(Material.GREEN_WOOL)
                            .name("&a&lConfirm")
                            .lore("&7", "&7Click to salvage your item", "&7remember, this is permanent.")
                            .build())
                    .withGUI(this)
                    .onClick(event -> {
                        player.getInventory().addItem(new ItemStack(right.getMaterial(), right.getAmount()));
                        player.getInventory().addItem(new ItemStack(left.getMaterial(), left.getAmount()));
                        if (!enchantmentReturn.getEnchantments().isEmpty()) {
                            player.getInventory().addItem(enchantmentReturn.getItem());
                        }
                        completed = true;
                        player.closeInventory();
                        player.sendMessage(ChatColor.GREEN + "Successfully salvaged your item!");
                    })
                    .build(player);
        }

        return Optional.empty();
    }

    @Override
    public void onClickRaw(InventoryClickEvent event) {
        if (!event.getClickedInventory().equals(player.getOpenInventory().getBottomInventory())) {
            return;
        }

        final ItemStack currentItem = event.getCurrentItem();

        boolean allowed = false;
        event.setCancelled(true);

        for (SalvageableItem value : SalvageableItem.values()) {
            if (value.getSource().equals(currentItem.getType())) {
                allowed = true;
                break;
            }
        }

        if (!allowed) {
            player.sendMessage(ChatColor.RED + "You cannot salvage this item!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1);
            return;
        }

        this.selectedItem = currentItem;
        event.setCurrentItem(null);
        player.updateInventory();
        update();
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        if (!completed) {
            player.getInventory().addItem(selectedItem);
            player.updateInventory();
        }
    }
}
