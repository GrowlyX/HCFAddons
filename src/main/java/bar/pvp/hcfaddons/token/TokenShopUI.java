package bar.pvp.hcfaddons.token;

import bar.pvp.hcfaddons.internal.service.Services;
import bar.pvp.hcfaddons.player.impl.persist.PersistentProfile;
import bar.pvp.hcfaddons.player.registry.PlayerService;
import bar.pvp.hcfaddons.token.model.TokenItem;
import me.blazingtide.phoenix.GUI;
import me.blazingtide.phoenix.button.builder.ButtonBuilder;
import me.blazingtide.phoenix.result.TickResult;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class TokenShopUI extends GUI {

    public TokenShopUI(Player player) {
        super(player, "Token Shop", 3 * 9);
    }

    @Override
    public Optional<TickResult> onTick() {
        final List<TokenItem> registry = Services.get(TokenShopService.class).getRegistry();
        final PersistentProfile profile = Services.get(PlayerService.class).getPlayerRegistry().get(player.getUniqueId()).get();

        for (int i = 0; i < registry.size(); i++) {
            final TokenItem tokenItem = registry.get(i);

            buttons[i] = new ButtonBuilder()
                    .withItem(tokenItem.createUIItem())
                    .withGUI(this)
                    .onClick(event -> {
                        if (profile.getTokens() < tokenItem.getPrice()) {
                            player.sendMessage(ChatColor.RED + "You do not have enough money to purchase this item.");
                            return;
                        }

                        profile.setTokens(profile.getTokens() - tokenItem.getPrice());
                        player.sendMessage(ChatColor.GREEN + "You have bought " + ChatColor.WHITE + tokenItem.getName() + ChatColor.GREEN + "!");
                        tokenItem.apply(player);
                        player.closeInventory();
                    })
                    .build(player);
        }

        return Optional.empty();
    }
}
