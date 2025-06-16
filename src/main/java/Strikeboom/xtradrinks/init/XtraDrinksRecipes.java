package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipe;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipeDisplay;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipeSerializer;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipe;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeDisplay;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class XtraDrinksRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(Registries.RECIPE_SERIALIZER, XtraDrinks.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, XtraDrinks.MOD_ID);
    public static final DeferredRegister<RecipeBookCategory> RECIPE_BOOK_CATEGORIES = DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, XtraDrinks.MOD_ID);
    public static final DeferredRegister<RecipeDisplay.Type<?>> RECIPE_DISPLAYS = DeferredRegister.create(Registries.RECIPE_DISPLAY, XtraDrinks.MOD_ID);


    public static final Supplier<DehydratorRecipeSerializer> DEHYDRATOR = RECIPES.register("dehydrator", DehydratorRecipeSerializer::new);
    public static final Supplier<RecipeType<DehydratorRecipe>> DEHYDRATOR_TYPE = RECIPE_TYPES.register("dehydrator",
            registryName -> new RecipeType<>() {
                @Override
                public String toString() {
                    return registryName.toString();
                }
            });
    public static final Supplier<RecipeBookCategory> DEHYDRATOR_CATEGORY = RECIPE_BOOK_CATEGORIES.register("dehydrator", RecipeBookCategory::new);
    public static final Supplier<RecipeDisplay.Type<DehydratorRecipeDisplay>> DEHYDRATOR_DISPLAY = RECIPE_DISPLAYS.register("dehydrator",() -> new RecipeDisplay.Type<>(DehydratorRecipeDisplay.MAP_CODEC,DehydratorRecipeDisplay.STREAM_CODEC));

    public static final Supplier<LiquidDehydratorRecipeSerializer> LIQUID_DEHYDRATOR = RECIPES.register("liquid_dehydrator", LiquidDehydratorRecipeSerializer::new);
    public static final Supplier<RecipeType<LiquidDehydratorRecipe>> LIQUID_DEHYDRATOR_TYPE = RECIPE_TYPES.register("liquid_dehydrator",
            registryName -> new RecipeType<>() {
                @Override
                public String toString() {
                    return registryName.toString();
                }
            });
    public static final Supplier<RecipeBookCategory> LIQUID_DEHYDRATOR_CATEGORY = RECIPE_BOOK_CATEGORIES.register("liquid_dehydrator", RecipeBookCategory::new);
    public static final Supplier<RecipeDisplay.Type<LiquidDehydratorRecipeDisplay>> LIQUID_DEHYDRATOR_DISPLAY = RECIPE_DISPLAYS.register("liquid_dehydrator",() -> new RecipeDisplay.Type<>(LiquidDehydratorRecipeDisplay.MAP_CODEC,LiquidDehydratorRecipeDisplay.STREAM_CODEC));

}
