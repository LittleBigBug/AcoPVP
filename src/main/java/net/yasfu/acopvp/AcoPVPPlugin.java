package net.yasfu.acopvp;

import net.yasfu.acopvp.mode.ModeHandler;
import org.bstats.bukkit.Metrics;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AcoPVPPlugin extends JavaPlugin {

    private static AcoPVPPlugin singleton;

    private static final int pluginId = 10573;

    @Override
    public void onEnable() {
        new Metrics(this, pluginId);

        String version = getDescription().getVersion();
        Logger logger = getLogger();

        singleton = this;

        logger.info("AcoWorth Started - " + version);
        logger.info("Developed by LittleBigBug");
        logger.info("https://github.com/littlebigbug/acoworth");

        File f = new File(getDataFolder() + "/");
        if (!f.exists()) { f.mkdir(); }
        this.saveDefaultConfig();

        PluginCommand pvpCmd = this.getCommand("acopvp");

        if (pvpCmd == null) {
            logger.log(Level.SEVERE, "AcoPVP command missing (??)");
            return;
        }

        ModeHandler.registerModes();
    }

    public static AcoPVPPlugin getInst() {
        return singleton;
    }

}
