package me.nikl.gamebox.nms;

import me.nikl.gamebox.utility.PurpurCompatibility;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Backwards-compatible facade for modules compiled against the old NMS API.
 */
public class NmsUtility {
  public ItemStack addGlow(ItemStack itemStack) {
    return PurpurCompatibility.addGlow(itemStack);
  }

  public ItemStack removeGlow(ItemStack itemStack) {
    return PurpurCompatibility.removeGlow(itemStack);
  }

  public void updateInventoryTitle(Player player, String title) {
    PurpurCompatibility.updateInventoryTitle(player, title);
  }
}
