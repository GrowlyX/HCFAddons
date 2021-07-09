package bar.pvp.hcfaddons.salvage;

import org.bukkit.Material;

public interface SalvageReturn {

    Material getMaterial();

    int getAmount();

    static SalvageReturn of(Material material, int amount) {
        return new SalvageReturn() {
            @Override
            public Material getMaterial() {
                return material;
            }

            @Override
            public int getAmount() {
                return amount;
            }
        };
    }

}
