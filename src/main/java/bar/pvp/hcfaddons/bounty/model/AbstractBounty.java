package bar.pvp.hcfaddons.bounty.model;

import bar.pvp.hcfaddons.bounty.entry.BountyEntry;
import bar.pvp.hcfaddons.bounty.entry.PersistentBountyEntry;
import bar.pvp.hcfaddons.internal.mongo.model.EntityID;
import com.google.common.collect.Lists;
import lombok.Data;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

@Data
public abstract class AbstractBounty implements Bounty {

    @EntityID
    protected final UUID id;
    protected final List<PersistentBountyEntry> entries = Lists.newArrayList();
    protected final String name;
    protected double bounty;

    @Override
    public BountyEntry issueBounty(Player issuer, double value) {
        final PersistentBountyEntry entry = new PersistentBountyEntry(value, issuer.getUniqueId(), issuer.getName());
        entries.add(entry);
        bounty += value;

        return entry;
    }

}
