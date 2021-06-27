package net.yasfu.acopvp.entities.data;

import org.bukkit.inventory.ItemStack;

public class ItemStackSerializable extends ConfigSerializableWrapper<ItemStack> {

    public ItemStackSerializable(ItemStack object) {
        super(object);
    }

    @Override
    public ItemStack getFinalObject() {
        if (this.configSerializable != null) {
            return this.configSerializable;
        }

        return ItemStack.deserialize(this.configSerializedMap);
    }

}
