package bar.pvp.hcfaddons.player.impl;

import bar.pvp.hcfaddons.internal.mongo.model.EntityID;
import lombok.Data;

import java.util.UUID;

@Data
public class AbstractProfile {

    @EntityID
    protected final UUID uniqueId;
    protected final String name;
    protected int reputation;
    protected int tokens;

}
