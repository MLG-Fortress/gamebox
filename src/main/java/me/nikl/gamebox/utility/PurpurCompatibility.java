package me.nikl.gamebox.utility;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.Duration;

/**
 * NMS-free wrappers for APIs exposed by Purpur/Paper.
 */
public final class PurpurCompatibility {
  private static final LegacyComponentSerializer LEGACY = LegacyComponentSerializer.legacySection();
  private static final GsonComponentSerializer GSON = GsonComponentSerializer.gson();

  private PurpurCompatibility() {
  }

  public static ItemStack addGlow(ItemStack itemStack) {
    if (itemStack == null) return null;
    ItemMeta meta = itemStack.getItemMeta();
    if (meta == null) return itemStack;
    meta.setEnchantmentGlintOverride(true);
    itemStack.setItemMeta(meta);
    return itemStack;
  }

  public static ItemStack removeGlow(ItemStack itemStack) {
    if (itemStack == null) return null;
    ItemMeta meta = itemStack.getItemMeta();
    if (meta == null) return itemStack;
    meta.setEnchantmentGlintOverride(false);
    itemStack.setItemMeta(meta);
    return itemStack;
  }

  @SuppressWarnings("deprecation")
  public static void updateInventoryTitle(Player player, String title) {
    if (player == null || title == null) return;
    InventoryView openInventory = player.getOpenInventory();
    try {
      openInventory.setTitle(title);
    } catch (UnsupportedOperationException | IllegalArgumentException ignored) {
      // Some inventory types no longer support title mutation.
    }
  }

  public static void sendJson(Player player, String json) {
    if (player == null || json == null) return;
    player.sendMessage(GSON.deserialize(json.trim()));
  }

  public static void sendActionbar(Player player, String message) {
    if (player == null || message == null) return;
    player.sendActionBar(legacy(message));
  }

  public static void sendTitle(Player player, String title, String subtitle, int stayTicks) {
    if (player == null) return;
    Title.Times times = Title.Times.times(Duration.ZERO, Duration.ofMillis(stayTicks * 50L), Duration.ZERO);
    player.showTitle(Title.title(legacy(title), legacy(subtitle), times));
  }

  private static Component legacy(String message) {
    return LEGACY.deserialize(ChatColor.translateAlternateColorCodes('&', message == null ? "" : message));
  }
}
