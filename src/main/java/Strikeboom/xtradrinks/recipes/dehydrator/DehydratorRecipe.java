package Strikeboom.XtraDrinks.recipes.dehydrator;

import net.minecraft.item.ItemStack;

import java.util.Objects;

public final class DehydratorRecipe {
    private final ItemStack input;
    private final ItemStack output;

    DehydratorRecipe(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public ItemStack input() {
        return input;
    }

    public ItemStack output() {
        return output;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        DehydratorRecipe that = (DehydratorRecipe) obj;
        return Objects.equals(this.input, that.input) &&
                Objects.equals(this.output, that.output);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input, output);
    }

    @Override
    public String toString() {
        return "DehydratorRecipe[" +
                "input=" + input + ", " +
                "output=" + output + ']';
    }
}
