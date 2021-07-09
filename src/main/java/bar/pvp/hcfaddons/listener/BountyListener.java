package bar.pvp.hcfaddons.listener;

import bar.pvp.hcfaddons.AddonsPlugin;
import bar.pvp.hcfaddons.bounty.BountyService;
import bar.pvp.hcfaddons.bounty.model.Bounty;
import bar.pvp.hcfaddons.internal.service.Services;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class BountyListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();
        final Player killer = player.getKiller();

        if (killer == null) {
            return;
        }

        for (Bounty bounty : Services.get(BountyService.class).getBounties()) {
            if (bounty.test(player, killer)) {
                AddonsPlugin.get().getAgent().addBalanceToPlayer(killer, bounty.getBounty());
                killer.sendMessage(ChatColor.GREEN + "You have claimed the bounty for " + player.getName() + "!");
            }
        }
    }

}
