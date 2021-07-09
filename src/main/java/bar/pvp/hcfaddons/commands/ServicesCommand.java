package bar.pvp.hcfaddons.commands;

import bar.pvp.hcfaddons.AddonsPlugin;
import bar.pvp.hcfaddons.internal.service.Service;
import me.blazingtide.commands.Commands;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.stream.Collectors;

public class ServicesCommand implements CommandHolder {
    @Override
    public void init() {
        Commands.begin()
                .permission("addons.admin")
                .label("services")
                .execute(arguments -> {
                    final CommandSender sender = arguments.sender(CommandSender.class);

                    sender.sendMessage(ChatColor.BLUE + "Registered Services: " + ChatColor.WHITE + AddonsPlugin.get().getServiceManager().getServices()
                            .stream().map(Service::getId).collect(Collectors.joining(", ")));
                }).create();
    }
}
