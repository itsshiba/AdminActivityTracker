package uwu.itsshiba.adminactivitytracker.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Header;

@Header("Admin Activity Tracker Configuration")
public class Config extends OkaeriConfig {
    @Comment("Should the plugin log commands executed by administrators?")
    public boolean logAdminCommands = true;

    public boolean isLogAdminCommands() {
        return logAdminCommands;
    }

    @Comment("Should the plugin log items dropped by the administrators?")
    public boolean logDroppedItems = true;

    public boolean isLogDroppedItems() {
        return logDroppedItems;
    }

    @Comment("Should the plugin log items, which administators got from creative inventory?")
    public boolean logCreativeItems = true;

    public boolean isLogCreativeItems() {
        return logCreativeItems;
    }

    @Comment("Should the plugin log items moved from the adminstrators inventory?")
    @Comment("For example, from administator inventory to chest/other player inventory")
    public boolean logMovedItems = true;

    public boolean isLogMovedItems() {
        return logMovedItems;
    }
}
