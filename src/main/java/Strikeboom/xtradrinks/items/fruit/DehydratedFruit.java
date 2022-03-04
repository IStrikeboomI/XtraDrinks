package Strikeboom.xtradrinks.items.fruit;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class DehydratedFruit extends Item {
    public DehydratedFruit() {
        super(new Item.Properties().durability(0).stacksTo(64).food(new FoodProperties.Builder().nutrition(7).saturationMod(1f).build()).tab(XtraDrinks.CREATIVE_MODE_TAB));
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 800;
    }
}
