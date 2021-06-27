package net.yasfu.acopvp.mode;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

/**
 * Player freezing utility
 */
public class PlayerFreeze implements Listener {

    private static final HashMap<UUID, Boolean> frozenPlayers = new HashMap<>();
    private static final HashMap<UUID, GameMode> lastGameMode = new HashMap<>();

    public static void freezePlayer(Player ply) {
        UUID uuid = ply.getUniqueId();
        frozenPlayers.put(uuid, true);

        GameMode mode = ply.getGameMode();
        lastGameMode.put(uuid, mode);

        ply.setGameMode(GameMode.ADVENTURE);
    }

    public static void unFreezePlayer(Player ply) {
        UUID uuid = ply.getUniqueId();
        frozenPlayers.remove(uuid);

        GameMode mode = lastGameMode.get(uuid);

        if (mode == null) {
            mode = GameMode.SURVIVAL;
        } else {
            lastGameMode.remove(uuid);
        }

        ply.setGameMode(mode);
    }

    public static void unFreezeAll() {
        frozenPlayers.clear();
    }

    public static boolean isFrozen(Player ply) {
        UUID uuid = ply.getUniqueId();
        Boolean frozen = frozenPlayers.get(uuid);

        if (frozen == null) {
            return false;
        }

        return frozen;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerMove(PlayerMoveEvent event) {
        Player ply = event.getPlayer();

        if (!isFrozen(ply)) {
            return;
        }

        event.setCancelled(true);
    }

}
