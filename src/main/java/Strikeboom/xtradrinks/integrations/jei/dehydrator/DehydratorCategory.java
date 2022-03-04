package Strikeboom.xtradrinks.integrations.jei.dehydrator;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.XtraDrinksBlocks;
import Strikeboom.xtradrinks.integrations.jei.XtraDrinksJeiPlugin;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
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
        BACKGROUND = gui.createDrawable(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/dehydrator.png"),4,15,150,60);
        ICON = gui.createDrawableIngredient(new ItemStack(XtraDrinksBlocks.DEHYDRATOR.get()));
    }
    @Override
    public ResourceLocation getUid() {
        return XtraDrinksJeiPlugin.DEHYDRATOR;
    }

    @Override
    public Class<? extends DehydratorRecipe> getRecipeClass() {
        return DehydratorRecipe.class;
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
    public void draw(DehydratorRecipe recipe, PoseStack stack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, stack, mouseX, mouseY);
        ARROW.draw(stack,75,20);
    }

    @Override
    public void setIngredients(DehydratorRecipe recipe, IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM,recipe.input());
        ingredients.setOutput(VanillaTypes.ITEM,recipe.output());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DehydratorRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
        stacks.init(0,true,50,19);
        stacks.init(1,false,111,19);
        stacks.set(ingredients);
    }
}
