package pl.flezy.example.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.flezy.example.manager.CustomItemManager;
import pl.flezy.flezylib.utils.ColorUtils;
import pl.flezy.flezylib.utils.ItemUtils;

@CommandAlias("examplecommand")
@CommandPermission("op")
public class ExampleCommand extends BaseCommand {
    @HelpCommand
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("give")
    @Syntax("<item> <player>")
    @CommandCompletion("@customitems")
    public void give(CommandSender sender, String itemKey, OnlinePlayer onlinePlayer){
        ItemStack item = CustomItemManager.getCustomItem(itemKey);
        if (item == null) return;
        Player receiver = onlinePlayer.getPlayer();
        ItemUtils.giveOrDrop(receiver,item);
        sender.sendMessage(ColorUtils.format("&eDałeś legendarkę " + item.getItemMeta().getDisplayName() +
                "&e dla gracza " + receiver.getName()));
    }

    @Subcommand("get")
    @Syntax("<item>")
    @CommandCompletion("@customitems")
    public void get(Player player, String itemKey){
        ItemStack item = CustomItemManager.getCustomItem(itemKey);
        if (item == null) return;
        ItemUtils.giveOrDrop(player,item);
        player.sendMessage(ColorUtils.format("&eDostałeś legendarkę " + item.getItemMeta().getDisplayName()));
    }
}