package bar.pvp.hcfaddons;

import bar.pvp.hcfaddons.agent.Agent;
import bar.pvp.hcfaddons.agent.UniversalAgent;
import bar.pvp.hcfaddons.bounty.BountyService;
import bar.pvp.hcfaddons.commands.*;
import bar.pvp.hcfaddons.internal.logger.Logger;
import bar.pvp.hcfaddons.internal.mongo.MongoService;
import bar.pvp.hcfaddons.internal.service.ServiceManager;
import bar.pvp.hcfaddons.listener.BountyListener;
import bar.pvp.hcfaddons.listener.PlayerListener;
import bar.pvp.hcfaddons.player.registry.PlayerService;
import bar.pvp.hcfaddons.salvage.SalvageService;
import bar.pvp.hcfaddons.token.TokenShopService;
import bar.pvp.hcfaddons.trade.TradeService;
import bar.pvp.hcfaddons.utils.ItemBuilder;
import lombok.Getter;
import me.blazingtide.commands.Commands;
import me.blazingtide.phoenix.Phoenix;
import me.blazingtide.phoenix.pagination.PaginatedGUI;
import me.blazingtide.phoenix.pagination.button.PaginationType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class AddonsPlugin extends JavaPlugin {

    private final ServiceManager serviceManager = new ServiceManager();

    private static AddonsPlugin instance;

    private Agent agent = new UniversalAgent();

    @Override
    public void onEnable() {
        instance = this;
        registerServices();
        registerCommands();
        registerListeners();
        setupGUI();
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
        Logger.log("Registered new agent " + agent.getName());
    }

    private void setupGUI() {
        new Phoenix(this);

        //This crap is so terrible, I hate this library.
        PaginatedGUI.PAGINATED_GUI_FILLER = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                .name(" ")
                .build();

        PaginationType.NEXT_PAGE.setItem(new ItemBuilder(Material.ARROW)
                .name("&aNext Page").build());

        PaginationType.PREVIOUS_PAGE.setItem(new ItemBuilder(Material.ARROW)
                .name("&cPrevious Page").build());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new BountyListener(), this);
    }

    private void registerServices() {
        serviceManager.register(new MongoService("localhost",
                27017,
                "admin",
                "hcf-test",
                "password",
                "admin"));

        final PlayerService playerService = new PlayerService();

        serviceManager.register(playerService);
        serviceManager.register(playerService.getPlayerRegistry());

        final BountyService bountyService = new BountyService();

        serviceManager.register(bountyService);
        serviceManager.register(bountyService.getFactionBountyRegistry());
        serviceManager.register(bountyService.getPlayerBountyRegistry());

        serviceManager.register(new SalvageService());
        serviceManager.register(new TokenShopService());
        serviceManager.register(new TradeService());

        serviceManager.loadServices();
    }

    @Override
    public void onDisable() {
        serviceManager.unloadServices();
    }

    private void registerCommands() {
        new ServicesCommand().init();
        new InternalCommand().init();
        new ReputationCommand().init();
    }

    public static AddonsPlugin get() {
        return instance;
    }

}
