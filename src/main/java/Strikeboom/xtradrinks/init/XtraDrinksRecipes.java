package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.recipes.dehydrator.DehydratorRecipe;
import Strikeboom.XtraDrinks.recipes.dehydrator.DehydratorRecipeSerializer;
import Strikeboom.XtraDrinks.recipes.dehydrator.DehydratorRecipeType;
import Strikeboom.XtraDrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipe;
import Strikeboom.XtraDrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeSerializer;
import Strikeboom.XtraDrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeType;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class XtraDrinksRecipes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, XtraDrinks.MOD_ID);

    public static final RegistryObject<DehydratorRecipeSerializer> DEHYDRATOR = RECIPES.register("dehydrator", DehydratorRecipeSerializer::new);
    public static final IRecipeType<DehydratorRecipe> DEHYDRATOR_TYPE = new DehydratorRecipeType();

    public static final RegistryObject<LiquidDehydratorRecipeSerializer> LIQUID_DEHYDRATOR = RECIPES.register("liquid_dehydrator", LiquidDehydratorRecipeSerializer::new);
    public static final IRecipeType<LiquidDehydratorRecipe> LIQUID_DEHYDRATOR_TYPE = new LiquidDehydratorRecipeType();


    public static void register() {
        Registry.register(Registry.RECIPE_TYPE,DehydratorRecipeType.ID,DEHYDRATOR_TYPE);
        Registry.register(Registry.RECIPE_TYPE,LiquidDehydratorRecipeType.ID,LIQUID_DEHYDRATOR_TYPE);
    }
}
