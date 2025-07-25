package dev.pvpshiba.adminActivityTracker;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import dev.pvpshiba.adminActivityTracker.listeners.CommandListener;
import dev.pvpshiba.adminActivityTracker.listeners.InventoryListener;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.serdes.commons.SerdesCommons;
import eu.okaeri.configs.validator.okaeri.OkaeriValidator;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import dev.pvpshiba.adminActivityTracker.config.Config;
import pl.flezy.flezylib.FlezyLib;

import java.io.File;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    private static Main instance;
    private Config config;

    @Override
    public void onEnable() {
        instance = this;
        FlezyLib.init(this);
        loadConfigs();

        registerListeners(
            new CommandListener(),
            new InventoryListener()
        );
        Bukkit.getLogger().log(Level.INFO, "☀ Successfully registered all listeners.");
        Bukkit.getLogger().log(Level.INFO, "☀ Plugin AdminActivityTracker has been successfully enabled!");
        Bukkit.getLogger().log(Level.INFO, "☀ Created by pvpshiba ");

        File adminFolder = new File(getDataFolder(), "admin");
        if (!adminFolder.exists()) {
            Bukkit.getLogger().log(Level.INFO, "☀ Admin folder not found, creating it now...");
            adminFolder.mkdirs();
        }

    }

    private void loadConfigs() {
        config = (Config) ConfigManager.create(Config.class)
                .withConfigurer(new OkaeriValidator(new YamlBukkitConfigurer()))
                .withSerdesPack(registry -> {
                    registry.register(new SerdesCommons());
                    registry.register(new SerdesBukkit());
                })
                .withBindFile(new File(this.getDataFolder(), "config.yml"))
                .saveDefaults()
                .load(true);
    }

    private void registerCommands(BaseCommand... commandList) {
        PaperCommandManager manager = new PaperCommandManager(this);

        manager.enableUnstableAPI("help");

        for (BaseCommand command : commandList) {
            manager.registerCommand(command);
        }
    }

    private void registerListeners(Listener... listenersList){
        for (Listener listener : listenersList){
            getServer().getPluginManager().registerEvents(listener,this);
        }
    }

    public static Main instance() {
        return instance;
    }

    public Config config() {
        return config;
    }

}
