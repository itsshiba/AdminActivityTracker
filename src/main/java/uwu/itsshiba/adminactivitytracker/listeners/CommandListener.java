package uwu.itsshiba.adminactivitytracker.listeners;

import uwu.itsshiba.adminactivitytracker.Main;
import uwu.itsshiba.adminactivitytracker.config.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommandListener implements Listener {

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {

        Config config = Main.instance().config();
        Player player = event.getPlayer();

        if (!config.isLogAdminCommands() || !player.hasPermission("aat.admin")) {
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
            fileWriter.write("[" + formattedDate + "] " + player.getName() + " issued server command: " + event.getMessage() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
