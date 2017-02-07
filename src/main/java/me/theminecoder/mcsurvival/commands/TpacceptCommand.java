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
public class TpacceptCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player bukkitPlayer = (Player) sender; //person who got the TP request

        try {
            if (PlayerManager.getTeleportCache().get(bukkitPlayer.getUniqueId()) != null) {

                Player bukkitTarget = Bukkit.getPlayer(PlayerManager.getTeleportCache().get(bukkitPlayer.getUniqueId()));

                if (bukkitTarget == null) {
                    bukkitPlayer.sendMessage(ChatColor.RED + "The player that requested the teleport went offline!");
                    return true;

                } else {
                    bukkitTarget.teleport(bukkitPlayer);
                    bukkitTarget.sendMessage(ChatColor.YELLOW + "You have teleported to " + ChatColor.RED + bukkitPlayer.getName());
                    bukkitPlayer.sendMessage(ChatColor.RED + bukkitTarget.getName() + ChatColor.YELLOW + " has teleported to you!");
                    PlayerManager.getTeleportCache().invalidate(bukkitPlayer.getUniqueId());
                    return true;
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (CacheLoader.InvalidCacheLoadException ignored) {
            bukkitPlayer.sendMessage(ChatColor.RED + "You have no pending teleport requests! Has it been more than 120 seconds since your last request?");
        }

        return true;
    }
}
