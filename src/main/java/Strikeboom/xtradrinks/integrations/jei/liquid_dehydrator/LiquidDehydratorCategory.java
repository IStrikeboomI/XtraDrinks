package Strikeboom.xtradrinks.integrations.jei.liquid_dehydrator;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.XtraDrinksBlocks;
import Strikeboom.xtradrinks.integrations.jei.XtraDrinksJeiPlugin;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class LiquidDehydratorCategory implements IRecipeCategory<LiquidDehydratorRecipe> {
    public final IDrawableAnimated ARROW;
    public final IDrawable BACKGROUND;
    private final IDrawable ICON;
    public LiquidDehydratorCategory(IGuiHelper gui) {
        final IDrawableStatic STATIC_PROGRESS_BAR = gui.createDrawable(ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID, "textures/gui/container/liquid_dehydrator.png"),176,0,24,17);
        ARROW = gui.createAnimatedDrawable(STATIC_PROGRESS_BAR, 50, IDrawableAnimated.StartDirection.LEFT,false);
        BACKGROUND = gui.createDrawable(ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID, "textures/gui/container/liquid_dehydrator.png"),4,5,125,74);
        ICON = gui.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(XtraDrinksBlocks.LIQUID_DEHYDRATOR.get()));
    }

    @Override
    public IRecipeType<LiquidDehydratorRecipe> getRecipeType() {
        return XtraDrinksJeiPlugin.LIQUID_DEHYDRATOR;
    }


    @Override
    public Component getTitle() {
        return Component.translatable("block."+XtraDrinks.MOD_ID+".liquid_dehydrator");
    }

    @Override
    public int getHeight() {
        return BACKGROUND.getHeight();
    }

    @Override
    public int getWidth() {
        return BACKGROUND.getWidth();
    }


    @Override
    public IDrawable getIcon() {
        return ICON;
    }

    @Override
    public void draw(LiquidDehydratorRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        BACKGROUND.draw(guiGraphics);
        ARROW.draw(guiGraphics,45,27);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, LiquidDehydratorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT,4,3).add(recipe.getInput()).setFluidRenderer(1000,true,24,66);
        builder.addSlot(RecipeIngredientRole.OUTPUT,82,28).add(recipe.getResultItem());
    }
}
