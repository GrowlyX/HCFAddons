package bar.pvp.hcfaddons.utils;

import org.bukkit.ChatColor;

public class ColorUtils {

    public static String translate(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

}
