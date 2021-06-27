package net.yasfu.acopvp.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import net.yasfu.acopvp.entities.data.SpawnPoint;
import net.yasfu.acopvp.mode.Mode;

@DatabaseTable(tableName = "arenas")
public class Arena {

    @DatabaseField(id = true, generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private String region;

    @DatabaseField(defaultValue = "world")
    private String world;

    @DatabaseField
    private Mode[] allowedModes;

    @DatabaseField
    private SpawnPoint[] spawnPoints;

    public void setName(String name) {
        this.name = name;
    }

    public void setRegion(String region, String world) {
        this.region = region;
        this.world = world;
    }

    public void setAllowedModes(Mode[] modes) {
        this.allowedModes = modes;
    }

    public Mode[] getAllowedModes() {
        return this.allowedModes;
    }

    public void setSpawnPoints(SpawnPoint[] spawnPoints) {
        this.spawnPoints = spawnPoints;
    }

    public SpawnPoint[] getSpawnPoints() {
        return this.spawnPoints;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getRegion() {
        return this.region;
    }

    public String getWorld() {
        return this.world;
    }

}
