package bar.pvp.hcfaddons.bounty;

import bar.pvp.hcfaddons.bounty.model.FactionBounty;
import bar.pvp.hcfaddons.bounty.model.PlayerBounty;
import bar.pvp.hcfaddons.internal.registry.Registry;

public class PlayerBountyRegistry extends Registry<PlayerBounty> {

    @Override
    public Class<PlayerBounty> getEntityType() {
        return PlayerBounty.class;
    }

    @Override
    public String getId() {
        return "player bounties";
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }
}
