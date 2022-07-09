package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipe;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipeSerializer;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipeType;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipe;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeSerializer;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XtraDrinksRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, XtraDrinks.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, XtraDrinks.MOD_ID);

    public static final RegistryObject<DehydratorRecipeSerializer> DEHYDRATOR = RECIPES.register("dehydrator", DehydratorRecipeSerializer::new);
    public static final RegistryObject<RecipeType<DehydratorRecipe>> DEHYDRATOR_TYPE = RECIPE_TYPES.register("dehydrator", DehydratorRecipeType::new);

    public static final RegistryObject<LiquidDehydratorRecipeSerializer> LIQUID_DEHYDRATOR = RECIPES.register("liquid_dehydrator", LiquidDehydratorRecipeSerializer::new);
    public static final RegistryObject<RecipeType<LiquidDehydratorRecipe>> LIQUID_DEHYDRATOR_TYPE = RECIPE_TYPES.register("liquid_dehydrator", LiquidDehydratorRecipeType::new);
}
