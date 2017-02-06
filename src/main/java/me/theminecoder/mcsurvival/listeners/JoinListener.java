package me.theminecoder.mcsurvival.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.stream.Stream;

/**
 * @author theminecoder
 * @version 1.0
 */
public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Stream.of(
                ChatColor.YELLOW + "Welcome to theminecoder's survival server!",
                "",
                ChatColor.YELLOW + "This server is comprised of an open source plugin which anyone can edit if they wish to.",
                ChatColor.YELLOW + "Check it out here: " + ChatColor.AQUA + "https://github.com/theminecoder/mcsurvival"
        ).forEach(event.getPlayer()::sendMessage);

        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            player.sendMessage(ChatColor.BLUE + "Since we see this is your first time, you have received the starter kit!");
            player.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
            player.getInventory().addItem(new ItemStack(Material.STONE_AXE));
            player.getInventory().addItem(new ItemStack(Material.APPLE, 5));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }
}
