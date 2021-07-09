package bar.pvp.hcfaddons.bounty;

import bar.pvp.hcfaddons.bounty.model.Bounty;
import bar.pvp.hcfaddons.commands.BountyCommand;
import bar.pvp.hcfaddons.internal.service.Service;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

@Getter
public class BountyService implements Service {

    private final FactionBountyRegistry factionBountyRegistry = new FactionBountyRegistry();
    private final PlayerBountyRegistry playerBountyRegistry = new PlayerBountyRegistry();

    @Override
    public String getId() {
        return "bounties";
    }

    @Override
    public void enable() {
        factionBountyRegistry.loadAll();
        playerBountyRegistry.loadAll();
        log("Loaded all bounties (Faction & Player)");

        new BountyCommand().init();
    }

    @Override
    public void disable() {
        factionBountyRegistry.getCache().forEach((o, factionBounty) -> factionBountyRegistry.save(factionBounty));
        playerBountyRegistry.getCache().forEach((o, factionBounty) -> playerBountyRegistry.save(factionBounty));
    }

    public List<Bounty> getBounties() {
        List<Bounty> toReturn = Lists.newArrayList(factionBountyRegistry.getCache().values());
        toReturn.addAll(playerBountyRegistry.getCache().values());

        return toReturn;
    }

}
