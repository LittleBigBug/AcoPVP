package net.yasfu.acopvp.mode.modes;

import net.yasfu.acopvp.mode.Mode;
import net.yasfu.acopvp.mode.ModeData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@ModeData(
        name = "ConstantFreeForAll",
        version = "1.0.0",
        author = "LittleBigBug"
)
public class ConstantFreeForAll extends Mode {

    @Override
    public void onGameStart() {

    }

    @Override
    public Location choosePlayerSpawn(Player ply) {
        return null;
    }

}
