package bar.pvp.hcfaddons.agent.faction;

import java.util.List;
import java.util.UUID;

public interface FactionWrapper {

    UUID getUniqueId();

    String getName();

    List<UUID> getMembers();

}
