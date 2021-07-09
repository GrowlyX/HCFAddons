package bar.pvp.hcfaddons.commands;

import bar.pvp.hcfaddons.AddonsPlugin;
import bar.pvp.hcfaddons.internal.service.Services;
import bar.pvp.hcfaddons.player.impl.persist.PersistentProfile;
import bar.pvp.hcfaddons.player.impl.weak.WeakProfile;
import bar.pvp.hcfaddons.player.registry.PlayerService;
import me.blazingtide.commands.Commands;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InternalCommand implements CommandHolder {
    @Override
    public void init() {
        Commands.begin()
                .label("internal")
                .permission("addons.admin")
                .usage("<subcommand (player/agent)> <target>")
                .execute(arguments -> {
                    final CommandSender sender = arguments.sender(CommandSender.class);

                    final String sub = arguments.get(0)
                            .as(String.class);

                    if (sub.equalsIgnoreCase("player")) {
                        final Player target = arguments.get(1)
                                .as(Player.class);

                        final PlayerService playerService = Services.get(PlayerService.class);

                        sender.sendMessage(ChatColor.GRAY + "Checking " + target.getName() + "'s profile type..");
                        sender.sendMessage(ChatColor.GRAY + (playerService.getWeakPlayerMap().containsKey(target.getUniqueId()) ? WeakProfile.class : PersistentProfile.class).getName());
                        return;
                    }

                    if (sub.equalsIgnoreCase("agent")) {
                        sender.sendMessage(ChatColor.GRAY + "The agent this server is connect to is: " + AddonsPlugin.get().getAgent().getName());
                        return;
                    }
                }).create();


    }
}
