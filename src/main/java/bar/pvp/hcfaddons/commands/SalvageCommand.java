package bar.pvp.hcfaddons.commands;

import bar.pvp.hcfaddons.salvage.SalvageUI;
import me.blazingtide.commands.Commands;
import org.bukkit.entity.Player;

public class SalvageCommand implements CommandHolder {
    @Override
    public void init() {
        Commands.begin()
                .label("salvage")
                .permission("addons.salvage")
                .execute(arguments -> {
                    final Player sender = arguments.sender(Player.class);

                    new SalvageUI(sender).open();
                }).create();
    }
}
