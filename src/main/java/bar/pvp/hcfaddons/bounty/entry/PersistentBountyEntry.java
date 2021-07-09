package bar.pvp.hcfaddons.bounty.entry;

import lombok.Data;

import java.util.UUID;

@Data
public class PersistentBountyEntry implements BountyEntry {

    private final double value;
    private final UUID issuer;
    private final String issuerName;

}
