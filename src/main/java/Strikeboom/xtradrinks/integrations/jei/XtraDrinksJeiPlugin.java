package Strikeboom.xtradrinks.integrations.jei;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.menus.DehydratorMenu;
import Strikeboom.xtradrinks.init.XtraDrinksBlocks;
import Strikeboom.xtradrinks.init.XtraDrinksFluids;
import Strikeboom.xtradrinks.init.XtraDrinksTags;
import Strikeboom.xtradrinks.integrations.jei.dehydrator.DehydratorCategory;
import Strikeboom.xtradrinks.integrations.jei.liquid_dehydrator.LiquidDehydratorCategory;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipe;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipeHandler;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipe;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeHandler;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
public class XtraDrinksJeiPlugin implements IModPlugin {
    public static final RecipeType<DehydratorRecipe> DEHYDRATOR = RecipeType.create(XtraDrinks.MOD_ID,"dehydrator",DehydratorRecipe.class);
    public static final RecipeType<LiquidDehydratorRecipe> LIQUID_DEHYDRATOR = RecipeType.create(XtraDrinks.MOD_ID,"liquid_dehydrator",LiquidDehydratorRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(XtraDrinks.MOD_ID,"jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IModPlugin.super.registerRecipes(registration);
        registration.addRecipes(DEHYDRATOR,DehydratorRecipeHandler.getRecipes());
        registration.addRecipes(LIQUID_DEHYDRATOR,LiquidDehydratorRecipeHandler.getRecipes());

        registration.addIngredientInfo(List.of(new FluidStack(XtraDrinksFluids.MOLTEN_FIZZIUM.get(),1000),new FluidStack(XtraDrinksFluids.MOLTEN_LIQUADIUM.get(),1000)), VanillaTypes.FLUID,
                new TranslatableComponent("jei."+XtraDrinks.MOD_ID+".buckets_found"),new TranslatableComponent("jei."+XtraDrinks.MOD_ID+".buckets_liquid_dehydrator"));
        registration.addIngredientInfo(List.of(new ItemStack(XtraDrinksFluids.MOLTEN_FIZZIUM_BUCKET.get()),new ItemStack(XtraDrinksFluids.MOLTEN_LIQUADIUM_BUCKET.get())), VanillaTypes.ITEM,
                new TranslatableComponent("jei."+XtraDrinks.MOD_ID+".buckets_found"),new TranslatableComponent("jei."+XtraDrinks.MOD_ID+".buckets_liquid_dehydrator"));
        List<ItemStack> fruits = new ArrayList<>();
        Registry.ITEM.getTagOrEmpty(XtraDrinksTags.FRUIT).forEach(itemHolder -> fruits.add(new ItemStack(itemHolder.value())));
        registration.addIngredientInfo(fruits, VanillaTypes.ITEM,
                new TranslatableComponent("jei."+XtraDrinks.MOD_ID+".fruit"));
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IModPlugin.super.registerCategories(registration);
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(
                new DehydratorCategory(helper),
                new LiquidDehydratorCategory(helper));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        IModPlugin.super.registerRecipeCatalysts(registration);
        registration.addRecipeCatalyst(new ItemStack(XtraDrinksBlocks.DEHYDRATOR.get()),DEHYDRATOR);
        registration.addRecipeCatalyst(new ItemStack(XtraDrinksBlocks.LIQUID_DEHYDRATOR.get()),LIQUID_DEHYDRATOR);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        IModPlugin.super.registerRecipeTransferHandlers(registration);
        registration.addRecipeTransferHandler(DehydratorMenu.class,DEHYDRATOR,0,1,0,36);
    }
}
