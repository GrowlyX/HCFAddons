package bar.pvp.hcfaddons.bounty.entry;

import java.util.UUID;

public interface BountyEntry {

    double getValue();

    UUID getIssuer();

    String getIssuerName();

}
