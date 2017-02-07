package me.theminecoder.mcsurvival.managers;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import me.theminecoder.mcsurvival.objects.SurvivalPlayer;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author theminecoder
 * @version 1.0
 */
public final class PlayerManager {

    private static Map<UUID, SurvivalPlayer> playerMap = new ConcurrentHashMap<>();

    private static LoadingCache<UUID, UUID> teleportCache = CacheBuilder.newBuilder()
            .expireAfterWrite(120, TimeUnit.SECONDS)
            .maximumSize(500)
            .build(new CacheLoader<UUID, UUID>() {
                @Override
                public UUID load(UUID key) throws Exception {
                    return null;
                }
            });

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


    public static LoadingCache<UUID, UUID> getTeleportCache() {
        return teleportCache;
    }
}
