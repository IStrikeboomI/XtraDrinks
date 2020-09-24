package Strikeboom.xtradrinks.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class Juicer extends Item {

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public Item setContainerItem(Item containerItem) {
        return this;
    }

    @Nullable
    @Override
    public Item getContainerItem() {
        return this;
    }
}
