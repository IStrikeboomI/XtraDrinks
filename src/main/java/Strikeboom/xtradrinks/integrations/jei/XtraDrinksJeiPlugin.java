package Strikeboom.xtradrinks.integrations.jei;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.*;
import Strikeboom.xtradrinks.integrations.jei.dehydrator.DehydratorCategory;
import Strikeboom.xtradrinks.integrations.jei.liquid_dehydrator.LiquidDehydratorCategory;
import Strikeboom.xtradrinks.menus.DehydratorMenu;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipe;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class XtraDrinksJeiPlugin implements IModPlugin {
    public static final IRecipeType<DehydratorRecipe> DEHYDRATOR = IRecipeType.create(XtraDrinks.MOD_ID,"dehydrator",DehydratorRecipe.class);
    public static final IRecipeType<LiquidDehydratorRecipe> LIQUID_DEHYDRATOR = IRecipeType.create(XtraDrinks.MOD_ID,"liquid_dehydrator",LiquidDehydratorRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID,"jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (Minecraft.getInstance().level != null) {
            Level level = Minecraft.getInstance().level;
            registration.addRecipes(DEHYDRATOR, level.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.DEHYDRATOR_TYPE.get()));
            registration.addRecipes(LIQUID_DEHYDRATOR, level.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE.get()));

            registration.addIngredientInfo(List.of(new FluidStack(XtraDrinksFluids.MOLTEN_FIZZIUM.get(), 1000), new FluidStack(XtraDrinksFluids.MOLTEN_LIQUADIUM.get(), 1000)), NeoForgeTypes.FLUID_STACK,
                    Component.translatable("jei." + XtraDrinks.MOD_ID + ".buckets_found").append(Component.translatable("jei." + XtraDrinks.MOD_ID + ".buckets_liquid_dehydrator")));
            registration.addIngredientInfo(List.of(new ItemStack(XtraDrinksItems.MOLTEN_FIZZIUM_BUCKET.get()), new ItemStack(XtraDrinksItems.MOLTEN_LIQUADIUM_BUCKET.get())), VanillaTypes.ITEM_STACK,
                    Component.translatable("jei." + XtraDrinks.MOD_ID + ".buckets_found").append(Component.translatable("jei." + XtraDrinks.MOD_ID + ".buckets_liquid_dehydrator")));
            List<ItemStack> fruits = new ArrayList<>();
            BuiltInRegistries.ITEM.getTagOrEmpty(XtraDrinksTags.FRUITS).forEach(itemHolder -> fruits.add(new ItemStack(itemHolder.value())));
            registration.addIngredientInfo(fruits, VanillaTypes.ITEM_STACK,
                    Component.translatable("jei." + XtraDrinks.MOD_ID + ".fruit"));
        }
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
        registration.addCraftingStation(DEHYDRATOR, new ItemStack(XtraDrinksBlocks.DEHYDRATOR.get()));
        registration.addCraftingStation(LIQUID_DEHYDRATOR, new ItemStack(XtraDrinksBlocks.LIQUID_DEHYDRATOR.get()));
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        IModPlugin.super.registerRecipeTransferHandlers(registration);
        registration.addRecipeTransferHandler(DehydratorMenu.class, XtraDrinksMenus.DEHYDRATOR_MENU.get(), DEHYDRATOR,0,1,2,36);
    }
}
