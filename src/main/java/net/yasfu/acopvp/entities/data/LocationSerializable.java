package net.yasfu.acopvp.entities.data;

import org.bukkit.Location;

public class LocationSerializable extends ConfigSerializableWrapper<Location> {

    public LocationSerializable(Location object) {
        super(object);
    }

    @Override
    public Location getFinalObject() {
        if (this.configSerializable != null) {
            return this.configSerializable;
        }

        return Location.deserialize(this.configSerializedMap);
    }

}
