package azuazu3939.lifecraft;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class LifeCraft extends JavaPlugin {

    private static LifeCraft craft;
    public LifeCraft() {craft = this;}
    public static LifeCraft inst() {return craft;}
    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("LifeCraft")).setExecutor(new LifeCraftCommand());

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new LifeCraftListener(), this);
        pm.registerEvents(new CraftListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
