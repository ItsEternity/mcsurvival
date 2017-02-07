package me.theminecoder.mcsurvival;

import com.google.gson.Gson;
import me.theminecoder.mcsurvival.commands.TpaCommand;
import me.theminecoder.mcsurvival.commands.TpacceptCommand;
import me.theminecoder.mcsurvival.commands.TpdenyCommand;
import me.theminecoder.mcsurvival.listeners.ChatListener;
import me.theminecoder.mcsurvival.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class Survival extends JavaPlugin {

    private static Survival instance;

    private Gson gson;

    public static Survival getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.getDataFolder().mkdirs();

        gson = new Gson();

        Stream.of(
                new ChatListener(),
                new JoinListener()
        ).forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));

        Bukkit.getPluginCommand("tpa").setExecutor(new TpaCommand());
        Bukkit.getPluginCommand("tpaccept").setExecutor(new TpacceptCommand());
        Bukkit.getPluginCommand("tpdeny").setExecutor(new TpdenyCommand());
    }

    @Override
    public void onDisable() {
    }

    public Gson getGson() {
        return gson;
    }
}
