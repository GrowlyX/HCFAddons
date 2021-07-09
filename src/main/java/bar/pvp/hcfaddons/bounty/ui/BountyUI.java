package bar.pvp.hcfaddons.bounty.ui;

import bar.pvp.hcfaddons.bounty.BountyService;
import bar.pvp.hcfaddons.internal.service.Services;
import me.blazingtide.phoenix.button.Button;
import me.blazingtide.phoenix.button.builder.ButtonBuilder;
import me.blazingtide.phoenix.pagination.PaginatedGUI;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class BountyUI extends PaginatedGUI {

    public BountyUI(Player player) {
        super(player, "Bounties", 54);
    }

    @Override
    public List<Button> getPageButtons() {
        final BountyUI ui = this;

        return Services.get(BountyService.class).getBounties()
                .stream()
                .map(bounty -> new ButtonBuilder()
                        .withGUI(ui)
                        .withItem(bounty.createUIItem())
                        .build(player)).collect(Collectors.toList());
    }
}
