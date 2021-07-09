package bar.pvp.hcfaddons.bounty.model;

import bar.pvp.hcfaddons.bounty.entry.BountyEntry;
import bar.pvp.hcfaddons.bounty.entry.PersistentBountyEntry;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public interface Bounty {

    /**
     * The id is also the uniqueId of the faction or player.
     * Keeps stuff consistent.
     *
     * @return the id of the bounty
     */
    UUID getId();

    String getName();

    double getBounty();

    List<PersistentBountyEntry> getEntries();

    BountyEntry issueBounty(Player issuer, double value);

    ItemStack createUIItem();

    /**
     * Tests whether they should be given the bounty reward.
     * @param player the player who died
     * @param killer the killer
     * @return true if they should be given the reward, false if not
     */
    boolean test(Player player, Player killer);

}
