package Strikeboom.xtradrinks.integrations.jei.dehydrator;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.XtraDrinksBlocks;
import Strikeboom.xtradrinks.integrations.jei.XtraDrinksJeiPlugin;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class DehydratorCategory implements IRecipeCategory<DehydratorRecipe> {
    public final IDrawableAnimated ARROW;
    public final IDrawable BACKGROUND;
    private final IDrawable ICON;
    public DehydratorCategory(IGuiHelper gui) {
        final IDrawableStatic STATIC_PROGRESS_BAR = gui.createDrawable(ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID, "textures/gui/container/dehydrator.png"),176,0,24,17);
        ARROW = gui.createAnimatedDrawable(STATIC_PROGRESS_BAR, 50, IDrawableAnimated.StartDirection.LEFT,false);
        BACKGROUND = gui.createDrawable(ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID, "textures/gui/container/dehydrator.png"),47,23,100,40);
        ICON = gui.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(XtraDrinksBlocks.DEHYDRATOR.get()));
    }

    @Override
    public IRecipeType<DehydratorRecipe> getRecipeType() {
        return XtraDrinksJeiPlugin.DEHYDRATOR;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block."+XtraDrinks.MOD_ID+".dehydrator");
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
    public void draw(DehydratorRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        BACKGROUND.draw(guiGraphics);
        ARROW.draw(guiGraphics,33 ,12);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DehydratorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT,8,12).add(recipe.getIngredients().getFirst());
        builder.addSlot(RecipeIngredientRole.OUTPUT,69,12).add(recipe.getResultItem());
    }
}
