package bar.pvp.hcfaddons.bounty.model;

import bar.pvp.hcfaddons.AddonsPlugin;
import bar.pvp.hcfaddons.agent.faction.FactionWrapper;
import bar.pvp.hcfaddons.bounty.FactionBountyRegistry;
import bar.pvp.hcfaddons.internal.mongo.model.Entity;
import bar.pvp.hcfaddons.internal.schedulers.Schedulers;
import bar.pvp.hcfaddons.internal.service.Services;
import bar.pvp.hcfaddons.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class FactionBounty extends AbstractBounty implements PersistentBounty {

    public FactionBounty(UUID id, String name) {
        super(id, name);
    }

    @Override
    public void save() {
        Schedulers.async(() -> Services.get(FactionBountyRegistry.class).save(this));
    }

    @Override
    public ItemStack createUIItem() {
        return new ItemBuilder(Material.FILLED_MAP)
                .name("&6" + name + "'s Bounty (Faction)")
                .lore("&7&m-------------------",
                        "&fValue: &2$&a" + bounty,
                        "&7&m-------------------",
                        "&6↘ Issuers:")
                .lore(entries.stream()
                        .map(bountyEntry -> "  &7■ &f" + bountyEntry.getIssuerName() + " &7added &a$" + bountyEntry.getValue())
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public boolean test(Player player, Player killer) {
        Optional<FactionWrapper> optional = AddonsPlugin.get().getAgent().retrieveFaction(id);

        return optional.isPresent() && optional.get().getMembers().contains(player.getUniqueId());
    }
}
