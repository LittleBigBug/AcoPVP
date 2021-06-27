package net.yasfu.acopvp.entities.data;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public abstract class ConfigSerializableWrapper<T extends ConfigurationSerializable> implements Serializable {

    protected transient T configSerializable;
    protected transient HashMap<String, Object> configSerializedMap;

    /**
     * Creates and returns configSerializable from the configSerializedMap data
     */
    public abstract T getFinalObject();

    public ConfigSerializableWrapper(T object) {
        this.setObject(object);
    };

    public void setObject(T object) {
        this.configSerializable = object;
    }

    public void writeObject(ObjectOutputStream out)
            throws IOException {

        HashMap<String, Object> serialized = new HashMap<>(this.configSerializable.serialize());
        out.writeObject(serialized);
    }

    public void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {

        @SuppressWarnings("unchecked")
        HashMap<String, Object> map = (HashMap<String, Object>) in.readObject();

        this.configSerializedMap = map;
    }

}
