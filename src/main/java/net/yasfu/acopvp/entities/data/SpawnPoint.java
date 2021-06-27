package net.yasfu.acopvp.entities.data;

import java.io.Serializable;

public class SpawnPoint implements Serializable {

    public boolean enabled = true;

    public LocationSerializable location;

    /**
     * Spawn group, used for team spawns, otherwise grouping or tagging spawns
     */
    public String spawnGroup;

}
