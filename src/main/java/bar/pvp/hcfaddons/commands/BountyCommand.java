package bar.pvp.hcfaddons.commands;

import bar.pvp.hcfaddons.AddonsPlugin;
import bar.pvp.hcfaddons.agent.Agent;
import bar.pvp.hcfaddons.bounty.PlayerBountyRegistry;
import bar.pvp.hcfaddons.bounty.model.PlayerBounty;
import bar.pvp.hcfaddons.bounty.ui.BountyUI;
import bar.pvp.hcfaddons.internal.service.Services;
import me.blazingtide.commands.Commands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Optional;

public class BountyCommand implements CommandHolder {

    private static final double MIN_BOUNTY = 1000;

    @Override
    public void init() {
        final Agent agent = AddonsPlugin.get().getAgent();


        Commands.begin()
                .label("bounty")
                .usage("<player/faction> <amount>")
                .execute(arguments -> {
                    final Player sender = arguments.sender(Player.class);

                    final Optional<String> optional = arguments.get(0)
                            .allowEmpty()
                            .as(String.class);

                    if (!optional.isPresent()) {
                        new BountyUI(sender).open();
                        return;
                    }

                    optional.ifPresent(s -> {
                        final Player target = Bukkit.getPlayer(s);

                        if (target != null) {
                            final Double amount = arguments.get(1)
                                    .as(Double.class);

                            if (target.equals(sender)) {
                                sender.sendMessage(ChatColor.RED + "You cannot create a bounty on yourself.");
                                return;
                            }

                            if (agent.getPlayerBalance(sender) < amount) {
                                sender.sendMessage(ChatColor.RED + "You do not have the sufficient balance to create this bounty.");
                                return;
                            }

                            if (amount < MIN_BOUNTY) {
                                sender.sendMessage(ChatColor.RED + "You must put $" + MIN_BOUNTY + " or more for the bounty.");
                                return;
                            }

                            final PlayerBountyRegistry service = Services.get(PlayerBountyRegistry.class);
                            final Optional<PlayerBounty> bountyOptional = service.get(target.getUniqueId());

                            bountyOptional.ifPresent(bounty -> bounty.issueBounty(sender, amount));

                            if (!bountyOptional.isPresent()) {
                                final PlayerBounty bounty = new PlayerBounty(target.getUniqueId(), target.getName());

                                bounty.issueBounty(sender, amount);
                                service.persistLocally(bounty);
                            }

                            agent.removeBalanceFromPlayer(sender, amount);
                            sender.sendMessage(ChatColor.GREEN + "Created bounty for " + target.getName() + " worth $" + amount + "!");
                        }
                    });
                })
                .create();
    }
}
