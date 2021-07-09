package bar.pvp.hcfaddons.player.impl.weak;

import bar.pvp.hcfaddons.player.WeakPlayerEntity;
import bar.pvp.hcfaddons.player.impl.AbstractProfile;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class WeakProfile extends AbstractProfile implements WeakPlayerEntity {

    private final Player player;

    public WeakProfile(Player player) {
        super(player.getUniqueId(), player.getName());
        this.player = player;
    }

}
