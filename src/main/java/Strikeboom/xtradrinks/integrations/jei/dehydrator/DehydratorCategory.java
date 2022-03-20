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
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class DehydratorCategory implements IRecipeCategory<DehydratorRecipe> {
    public final IDrawableAnimated ARROW;
    public final IDrawable BACKGROUND;
    private final IDrawable ICON;
    public DehydratorCategory(IGuiHelper gui) {
        final IDrawableStatic STATIC_PROGRESS_BAR = gui.createDrawable(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/dehydrator.png"),176,0,24,17);
        ARROW = gui.createAnimatedDrawable(STATIC_PROGRESS_BAR, 50, IDrawableAnimated.StartDirection.LEFT,false);
        BACKGROUND = gui.createDrawable(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/dehydrator.png"),47,23,100,40);
        ICON = gui.createDrawableIngredient(VanillaTypes.ITEM,new ItemStack(XtraDrinksBlocks.DEHYDRATOR.get()));
    }

    @Override
    public RecipeType<DehydratorRecipe> getRecipeType() {
        return XtraDrinksJeiPlugin.DEHYDRATOR;
    }

    @SuppressWarnings("removal")
    @Override
    public ResourceLocation getUid() {
        return getRecipeType().getUid();
    }

    @SuppressWarnings("removal")
    @Override
    public Class<? extends DehydratorRecipe> getRecipeClass() {
        return getRecipeType().getRecipeClass();
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("block."+XtraDrinks.MOD_ID+".dehydrator");
    }

    @Override
    public IDrawable getBackground() {
        return BACKGROUND;
    }

    @Override
    public IDrawable getIcon() {
        return ICON;
    }

    @Override
    public void draw(DehydratorRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
        ARROW.draw(stack,33 ,12);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DehydratorRecipe recipe, IFocusGroup focuses) {
        IRecipeCategory.super.setRecipe(builder, recipe, focuses);
        builder.addSlot(RecipeIngredientRole.INPUT,8,12).addItemStack(recipe.input());
        builder.addSlot(RecipeIngredientRole.OUTPUT,69,12).addItemStack(recipe.output());
    }
}
