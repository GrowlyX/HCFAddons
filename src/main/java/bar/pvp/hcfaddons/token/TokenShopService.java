package bar.pvp.hcfaddons.token;

import bar.pvp.hcfaddons.commands.TokenShopCommand;
import bar.pvp.hcfaddons.internal.service.Service;
import bar.pvp.hcfaddons.token.model.TokenItem;
import bar.pvp.hcfaddons.token.model.impl.SwordTokenItem;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

@Getter
public class TokenShopService implements Service {

    private final List<TokenItem> registry = Lists.newArrayList();

    @Override
    public String getId() {
        return "token shop";
    }

    @Override
    public void enable() {
        registry.add(new SwordTokenItem());

        new TokenShopCommand().init();
    }

    @Override
    public void disable() {

    }
}
