package net.yasfu.acopvp.util;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import net.yasfu.acopvp.AcoPVPPlugin;
import org.bukkit.World;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RegionUtil {

    /**
     * @param region Region name
     * @param world Bukkit World
     * @return Found WorldGuard region
     */
    public static ProtectedRegion CheckRegion(String region, World world) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        com.sk89q.worldedit.world.World weWorld = BukkitAdapter.adapt(world);

        RegionManager regionManager = container.get(weWorld);

        if (regionManager == null) {
            Logger logger = AcoPVPPlugin.getInst().getLogger();
            logger.log(Level.WARNING, "RegionManager for world " + world.getName() + " is null.");
            return null;
        }

        return regionManager.getRegion(region);
    }

}
