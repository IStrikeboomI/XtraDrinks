package Strikeboom.xtradrinks.guis.tileentity.recipes.dehydrator;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Objects;


public class DehydratorRecipe {
    private final Item item;
    private final int count;
    private final Item stackItem;

    public DehydratorRecipe(Item item, ItemStack stack) {
        this.item = item;
        this.count = stack.getCount();
        this.stackItem = stack.getItem();
    }

    public Item getItem() {
        return this.item;
    }
    public int getStackCount() {

        return this.count;
    }

    public Item getStackItem() {
        return this.stackItem;
    }

}
