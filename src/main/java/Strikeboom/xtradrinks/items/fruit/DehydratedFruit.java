package Strikeboom.xtradrinks.items.fruit;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.ComposterBlock;
import org.jetbrains.annotations.Nullable;

public class DehydratedFruit extends Item {
    public DehydratedFruit() {
        super(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1f).build()).tab(XtraDrinks.CREATIVE_MODE_TAB));
        ComposterBlock.COMPOSTABLES.put(this,.9f);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 800;
    }
}
