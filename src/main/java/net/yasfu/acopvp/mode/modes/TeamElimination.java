package net.yasfu.acopvp.mode.modes;

import net.yasfu.acopvp.mode.Mode;
import net.yasfu.acopvp.mode.ModeData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@ModeData(
        name = "TeamElimination",
        version = "1.0.0",
        author = "LittleBigBug"
)
public class TeamElimination extends Mode {

    @Override
    public void onGameStart() {

    }

    @Override
    public Location choosePlayerSpawn(Player ply) {
        return null;
    }

}
