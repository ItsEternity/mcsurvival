package me.theminecoder.mcsurvival.commands;

import com.google.common.cache.CacheLoader;
import me.theminecoder.mcsurvival.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.ExecutionException;

/**
 * @author ItsEternity
 * @version 1.0
 */
public class TpdenyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player bukkitPlayer = (Player) sender;
        try {
            if (PlayerManager.getTeleportCache().get(bukkitPlayer.getUniqueId()) != null) {
                bukkitPlayer.sendMessage(ChatColor.YELLOW + "You have " + ChatColor.RED + "denied" + ChatColor.YELLOW + " the teleport request");

                Player bukkitTarget = Bukkit.getPlayer(PlayerManager.getTeleportCache().get(bukkitPlayer.getUniqueId()));
                if (bukkitTarget != null) {
                    bukkitTarget.sendMessage(ChatColor.RED + bukkitPlayer.getName() + ChatColor.YELLOW + " has denied your teleport request!");
                }

                PlayerManager.getTeleportCache().invalidate(bukkitPlayer.getUniqueId());
                return true;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (CacheLoader.InvalidCacheLoadException ignored) {
            bukkitPlayer.sendMessage(ChatColor.RED + "You have no pending teleport requests! Has it been more than 120 seconds since your last request?");

        }
        return true;
    }
}
