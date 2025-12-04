package uwu.itsshiba.adminactivitytracker.listeners;

import uwu.itsshiba.adminactivitytracker.Main;
import uwu.itsshiba.adminactivitytracker.config.Config;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InventoryListener implements Listener {
    @EventHandler
    public void onInventoryCreative(InventoryCreativeEvent event) {

        Config config = Main.instance().config();
        Player player = (Player) event.getWhoClicked();

        if (!config.isLogCreativeItems() || !player.hasPermission("aat.admin")) {
            return;
        }

        if (event.getCursor().getType() == Material.AIR) {
            return;
        }

        File file = new File("plugins/AdminActivityTracker/admin/" + player.getName() + ".txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try (FileWriter fileWriter = new FileWriter(file, true)) {
            LocalDateTime currentDate = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = currentDate.format(myFormatObj);
            fileWriter.write("[" + formattedDate + "] " + player.getName() + " got from creative inventory: " + event.getCursor().getType() + " x" + event.getCursor().getAmount() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Config config = Main.instance().config();

        if (!config.isLogDroppedItems() || !player.hasPermission("aat.admin")) {
            return;
        }

        File file = new File("plugins/AdminActivityTracker/admin/" + player.getName() + ".txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try (FileWriter fileWriter = new FileWriter(file, true)) {
            LocalDateTime currentDate = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = currentDate.format(myFormatObj);
            fileWriter.write("[" + formattedDate + "] " + player.getName() + " dropped the item: " + event.getItemDrop().getName().toUpperCase() + " x" + event.getItemDrop().getItemStack().getAmount() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryClickEvent event) {

        Config config = Main.instance().config();
        Player player = (Player) event.getWhoClicked();

        if (!config.isLogMovedItems() || !player.hasPermission("aat.admin")) {
            return;
        }


        if (event.getCursor().getType() == Material.AIR) {
            return;
        }

        File file = new File("plugins/AdminActivityTracker/admin/" + player.getName() + ".txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try (FileWriter fileWriter = new FileWriter(file, true)) {
            LocalDateTime currentDate = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = currentDate.format(myFormatObj);
            fileWriter.write("[" + formattedDate + "] " + player.getName() + " moved the item from inventory: " + event.getCursor().getType() + " x" + event.getCursor().getAmount() +  "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
