package pl.flezy.example.customitems;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.flezy.flezylib.utils.CustomItemUtils;

public class ExampleCustomItem implements Listener {
    private static final String itemKey = "example_item";

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (!event.getAction().isRightClick()) return;
        if (CustomItemUtils.isCustomItem(item, itemKey) &&
        CustomItemUtils.startCooldown(player, item, 5)) {
            Location loc = player.getLocation();
            loc.getWorld().strikeLightningEffect(loc);
        }
    }
}
