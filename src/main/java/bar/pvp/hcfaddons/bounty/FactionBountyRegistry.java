package bar.pvp.hcfaddons.bounty;

import bar.pvp.hcfaddons.bounty.model.FactionBounty;
import bar.pvp.hcfaddons.internal.registry.Registry;

public class FactionBountyRegistry extends Registry<FactionBounty> {

    @Override
    public Class<FactionBounty> getEntityType() {
        return FactionBounty.class;
    }

    @Override
    public String getId() {
        return "faction bounties";
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }
}
