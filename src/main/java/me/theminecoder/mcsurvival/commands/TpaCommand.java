package me.theminecoder.mcsurvival.commands;

import me.theminecoder.mcsurvival.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.stream.Stream;


/**
 * @author ItsEternity
 * @version 1.0
 */
public class TpaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        Player bukkitPlayer = (Player) sender;
        if (args.length == 0) {
            bukkitPlayer.sendMessage(ChatColor.RED + "Invalid usage! /tpa <target>");
            return true;
        }

        Player bukkitTarget = Bukkit.getPlayerExact(args[0]);

        if (bukkitTarget == null) {
            bukkitPlayer.sendMessage(ChatColor.RED + args[0] + " is not online!");
            return false;

        } else {

            PlayerManager.getTeleportCache().put(bukkitTarget.getUniqueId(), bukkitPlayer.getUniqueId());

            Stream.of(
                    ChatColor.RED + bukkitPlayer.getName() + ChatColor.YELLOW + " has requested to teleport to you!",
                    ChatColor.YELLOW + "Use " + ChatColor.RED + "/tpaccept" + ChatColor.YELLOW + " to accept the request",
                    ChatColor.YELLOW + "Use " + ChatColor.RED + "/tpdeny" + ChatColor.YELLOW + " to accept the request",
                    ChatColor.YELLOW + "This request will expire in " + ChatColor.RED + "120 seconds!"

            ).forEach(bukkitTarget::sendMessage);
            bukkitPlayer.sendMessage(ChatColor.YELLOW + "Teleport request sent to " + ChatColor.RED + bukkitTarget.getName());
            return true;
        }
    }
}
