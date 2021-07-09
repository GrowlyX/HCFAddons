package bar.pvp.hcfaddons.salvage;

import bar.pvp.hcfaddons.commands.SalvageCommand;
import bar.pvp.hcfaddons.internal.service.Service;

public class SalvageService implements Service {
    @Override
    public String getId() {
        return "salvage";
    }

    @Override
    public void enable() {
        new SalvageCommand().init();
    }

    @Override
    public void disable() {

    }
}
