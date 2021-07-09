package bar.pvp.hcfaddons.internal.schedulers;

import bar.pvp.hcfaddons.AddonsPlugin;
import org.bukkit.Bukkit;

import java.util.concurrent.CompletableFuture;

public class Schedulers {

    public static CompletableFuture<Void> async(Runnable runnable) {
        final CompletableFuture<Void> future = new CompletableFuture<>();

        Bukkit.getScheduler().runTaskAsynchronously(AddonsPlugin.get(), () -> {
            runnable.run();
            future.complete(null);
        });

        return future;
    }

}
