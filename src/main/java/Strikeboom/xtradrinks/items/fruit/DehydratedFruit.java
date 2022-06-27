package Strikeboom.XtraDrinks.items.fruit;

import Strikeboom.XtraDrinks.XtraDrinks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class DehydratedFruit extends Item {
    public DehydratedFruit() {
        super(new Item.Properties().food(new Food.Builder().nutrition(7).saturationMod(1f).build()).tab(XtraDrinks.CREATIVE_MODE_TAB));
        ComposterBlock.COMPOSTABLES.put(this,.9f);
    }


    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType) {
        return 800;
    }
}
