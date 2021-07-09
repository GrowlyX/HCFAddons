package bar.pvp.hcfaddons.salvage;

import lombok.Getter;
import org.bukkit.Material;

import static bar.pvp.hcfaddons.salvage.SalvageReturn.of;

@Getter
public enum SalvageableItem {

    DIAMOND_SWORD(Material.DIAMOND_SWORD, of(Material.DIAMOND, 2), of(Material.STICK, 1)),
    DIAMOND_PICKAXE(Material.DIAMOND_PICKAXE, of(Material.DIAMOND, 3), of(Material.STICK, 2)),
    DIAMOND_AXE(Material.DIAMOND_AXE, of(Material.DIAMOND, 3), of(Material.STICK, 2)),
    DIAMOND_SHOVEL(Material.DIAMOND_SHOVEL, of(Material.DIAMOND, 1), of(Material.STICK, 2)),
    //todo: fill this up with all of the other materials




    ;
    private final Material source;
    private final SalvageReturn[] returns;

    SalvageableItem(Material source, SalvageReturn... returns) {
        this.source = source;
        this.returns = returns;
    }

    }
/**
 * SalvageReturn.of(Material.DIAMOND, 2)
 */