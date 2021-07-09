package bar.pvp.hcfaddons.commands;

import bar.pvp.hcfaddons.internal.service.Services;
import bar.pvp.hcfaddons.player.impl.persist.PlayerRegistry;
import me.blazingtide.commands.Commands;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReputationCommand implements CommandHolder {
    @Override
    public void init() {
        Commands.begin()
                .label("giverep")
                .usage("<player> <amount>")
                .permission("addons.admin")
                .execute(arguments -> {
                    final CommandSender sender = arguments.sender(CommandSender.class);

                    Player target = arguments.get(0)
                            .as(Player.class);

                    Integer amount = arguments.get(1)
                            .as(Integer.class);

                    Services.get(PlayerRegistry.class).get(target.getUniqueId()).ifPresent(profile -> {
                        profile.setTokens(profile.getTokens() + amount);
                        sender.sendMessage(ChatColor.GREEN + "Done!");
                    });
                });
    }
}
