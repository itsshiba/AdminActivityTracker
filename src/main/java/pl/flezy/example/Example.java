package pl.flezy.example;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.serdes.commons.SerdesCommons;
import eu.okaeri.configs.validator.okaeri.OkaeriValidator;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.flezy.example.commands.ExampleCommand;
import pl.flezy.example.config.Config;
import pl.flezy.example.config.Data;
import pl.flezy.example.customitems.ExampleCustomItem;
import pl.flezy.example.manager.CustomItemManager;
import pl.flezy.flezylib.FlezyLib;

import java.io.File;

public final class Example extends JavaPlugin {

    private static Example instance;
    private Config config;
    private Data data;

    @Override
    public void onEnable() {
        instance = this;
        FlezyLib.init(this);
        loadConfigs();

        registerListeners(
            new ExampleCustomItem()
        );

        registerCommands(
            new ExampleCommand()
        );

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

        data = (Data) ConfigManager.create(Data.class)
                .withConfigurer(new OkaeriValidator(new YamlBukkitConfigurer()))
                .withSerdesPack(registry -> {
                    registry.register(new SerdesCommons());
                    registry.register(new SerdesBukkit());
                })
                .withBindFile(new File(this.getDataFolder(), "data.yml"))
                .saveDefaults()
                .load(true);
    }

    private void registerCommands(BaseCommand... commandList) {
        PaperCommandManager manager = new PaperCommandManager(this);

        manager.enableUnstableAPI("help");

        manager.getCommandCompletions().registerCompletion("customitems", c ->
                CustomItemManager.customItems.stream().toList());

        for (BaseCommand command : commandList) {
            manager.registerCommand(command);
        }
    }

    private void registerListeners(Listener... listenersList){
        for (Listener listener : listenersList){
            getServer().getPluginManager().registerEvents(listener,this);
        }
    }

    public static Example instance() {
        return instance;
    }

    public Config config() {
        return config;
    }

    public Data data(){
        return data;
    }
}
