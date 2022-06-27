package Strikeboom.XtraDrinks.integrations.jei.dehydrator;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.init.XtraDrinksBlocks;
import Strikeboom.XtraDrinks.integrations.jei.XtraDrinksJeiPlugin;
import Strikeboom.XtraDrinks.recipes.dehydrator.DehydratorRecipe;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class DehydratorCategory implements IRecipeCategory<DehydratorRecipe> {
    public final IDrawableAnimated ARROW;
    public final IDrawable BACKGROUND;
    private final IDrawable ICON;
    public DehydratorCategory(IGuiHelper gui) {
        final IDrawableStatic STATIC_PROGRESS_BAR = gui.createDrawable(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/dehydrator.png"),176,0,24,17);
        ARROW = gui.createAnimatedDrawable(STATIC_PROGRESS_BAR, 50, IDrawableAnimated.StartDirection.LEFT,false);
        BACKGROUND = gui.createDrawable(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/dehydrator.png"),47,23,100,40);
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
    public String getTitle() {
        return new TranslationTextComponent("block."+ XtraDrinks.MOD_ID+".dehydrator").getString();
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
    public void draw(DehydratorRecipe recipe, MatrixStack stack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, stack, mouseX, mouseY);
        ARROW.draw(stack,33 ,12);
    }
    @Override
    public void setIngredients(DehydratorRecipe recipe, IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM, recipe.input());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.output());
    }
    @Override
    public void setRecipe(IRecipeLayout builder, DehydratorRecipe recipe, IIngredients ingredients) {
        builder.getItemStacks().init(0,true,8,12);
        builder.getItemStacks().set(0,recipe.input());
        builder.getItemStacks().init(1, false,69,12);
        builder.getItemStacks().set(1,recipe.output());
    }
}
