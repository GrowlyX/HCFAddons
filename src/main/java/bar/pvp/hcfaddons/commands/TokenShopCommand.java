package bar.pvp.hcfaddons.commands;

import bar.pvp.hcfaddons.internal.service.Services;
import bar.pvp.hcfaddons.player.impl.persist.PersistentProfile;
import bar.pvp.hcfaddons.player.registry.PlayerService;
import bar.pvp.hcfaddons.token.TokenShopUI;
import me.blazingtide.commands.Commands;
import me.blazingtide.commands.argument.CommandArguments;
import me.blazingtide.commands.sender.Sender;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class TokenShopCommand implements CommandHolder {
    @Override
    public void init() {
        Commands.begin()
                .label("tokenshop")
                .permission("addons.tokenshop")
                .execute(arguments -> {
                    final Player sender = arguments.sender(Player.class);

                    new TokenShopUI(sender).open();
                }).create();

        Commands.begin()
                .label("givetoken")
                .permission("addons.admin")
                .usage("<target> <amount>")
                .execute(arguments -> {
                    final CommandSender sender = arguments.sender(CommandSender.class);

                    final Player target = arguments.get(0)
                            .as(Player.class);
                    Integer amount = arguments.get(1)
                            .as(Integer.class);

                    PersistentProfile profile = Services.get(PlayerService.class).getPlayerRegistry().get(target).get();

                    profile.setTokens(profile.getTokens() + amount);
                    sender.sendMessage(ChatColor.GREEN + "Gave tokens.");
                }).create();
    }
}
