package pl.flezy.example.manager;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.flezy.flezylib.utils.ColorUtils;
import pl.flezy.flezylib.utils.CustomItemUtils;

import java.util.List;

public class CustomItemManager {
    public static final List<String> customItems = List.of(
            "example_item"
    );

    public static ItemStack getCustomItem(String itemKey){
        ItemStack item;
        ItemMeta meta;
        switch (itemKey) {
            case "example_item":
                item = new ItemStack(Material.NETHERITE_SWORD);
                meta = item.getItemMeta();
                meta.setItemModel(new NamespacedKey(NamespacedKey.MINECRAFT, itemKey));
                meta.setDisplayName(ColorUtils.format("&e&lExample Item"));
                meta.setLore(ColorUtils.formatList(
                        "&7Example lore"
                ));

                meta.setUnbreakable(true);
                meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

                CustomItemUtils.setCustomItem(meta, itemKey);
                item.setItemMeta(meta);
                return item;
        }
        return null;
    }
}
