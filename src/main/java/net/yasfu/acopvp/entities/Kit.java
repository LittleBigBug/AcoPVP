package net.yasfu.acopvp.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import net.yasfu.acopvp.entities.data.ItemStackSerializable;
import org.bukkit.inventory.ItemStack;

@DatabaseTable(tableName = "kits")
public class Kit {

    @DatabaseField(id = true, generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private boolean enabled = true;

    @DatabaseField
    private ItemStackSerializable[] items;

    @DatabaseField
    private String[] allowedModes;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStackSerializable[] getItems() {
        return this.items;
    }

    public void setItems(ItemStack[] items) {
        ItemStackSerializable[] itemsSerializable = new ItemStackSerializable[items.length];

        int i = 0;

        for (ItemStack item : items) {
            itemsSerializable[i++] = new ItemStackSerializable(item);
        }

        this.items = itemsSerializable;
    }

    public String[] getAllowedModes() {
        return this.allowedModes;
    }

}
