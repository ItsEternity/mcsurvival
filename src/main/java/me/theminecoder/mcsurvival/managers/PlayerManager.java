package me.theminecoder.mcsurvival.managers;

import me.theminecoder.mcsurvival.objects.SurvivalPlayer;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author theminecoder
 * @version 1.0
 */
public final class PlayerManager {

    private static Map<UUID, SurvivalPlayer> playerMap = new ConcurrentHashMap<>();

    private PlayerManager(){
    }

    public static Map<UUID, SurvivalPlayer> getPlayerMap() {
        return playerMap;
    }

    public static SurvivalPlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    public static SurvivalPlayer getPlayer(UUID uuid) {
        return playerMap.get(uuid);
    }
}
