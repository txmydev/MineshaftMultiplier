package net.skulluhc.mineshaftmultiplier;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MineshaftMultiplierPlugin extends JavaPlugin implements Listener {

    private double multiplier;

    @Override
    public void onEnable() {
        if(!getDataFolder().exists()) getDataFolder().mkdir();
        saveDefaultConfig();

        multiplier = getConfig().getDouble("multiplier");
        Bukkit.getLogger().info("Using a multiplier of " + multiplier);

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onWorldCreate(org.bukkit.event.world.WorldInitEvent event) {
        Bukkit.getLogger().info("Trying to hook up the mineshaft multiplier to world " + event.getWorld().getName() + "...");
        new CustomMineshaftHook(this, multiplier, event.getWorld());
    }


}
