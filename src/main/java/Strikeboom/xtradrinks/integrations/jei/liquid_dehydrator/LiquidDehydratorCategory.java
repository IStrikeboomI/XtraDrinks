package Strikeboom.XtraDrinks.integrations.jei.liquid_dehydrator;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.init.XtraDrinksBlocks;
import Strikeboom.XtraDrinks.integrations.jei.XtraDrinksJeiPlugin;
import Strikeboom.XtraDrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipe;
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

public class LiquidDehydratorCategory implements IRecipeCategory<LiquidDehydratorRecipe> {
    public final IDrawableAnimated ARROW;
    public final IDrawable BACKGROUND;
    private final IDrawable ICON;
    public LiquidDehydratorCategory(IGuiHelper gui) {
        final IDrawableStatic STATIC_PROGRESS_BAR = gui.createDrawable(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/liquid_dehydrator.png"),176,0,24,17);
        ARROW = gui.createAnimatedDrawable(STATIC_PROGRESS_BAR, 50, IDrawableAnimated.StartDirection.LEFT,false);
        BACKGROUND = gui.createDrawable(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/liquid_dehydrator.png"),4,5,125,74);
        ICON = gui.createDrawableIngredient(new ItemStack(XtraDrinksBlocks.LIQUID_DEHYDRATOR.get()));
    }


    @SuppressWarnings("removal")
    @Override
    public ResourceLocation getUid() {
        return XtraDrinksJeiPlugin.LIQUID_DEHYDRATOR;
    }

    @SuppressWarnings("removal")
    @Override
    public Class<? extends LiquidDehydratorRecipe> getRecipeClass() {
        return LiquidDehydratorRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("block."+XtraDrinks.MOD_ID+".liquid_dehydrator").getString();
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
    public void draw(LiquidDehydratorRecipe recipe, MatrixStack stack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, stack, mouseX, mouseY);
        ARROW.draw(stack,45,27);
    }

    @Override
    public void setIngredients(LiquidDehydratorRecipe recipe, IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.FLUID, recipe.input());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.output());
    }

    @Override
    public void setRecipe(IRecipeLayout builder, LiquidDehydratorRecipe recipe, IIngredients ingredients) {
        builder.getFluidStacks().init(0,true, 4, 3, 24, 66,1000, true, null);
        builder.getFluidStacks().set(0, recipe.input());
        builder.getItemStacks().init(0,false,82,28);
        builder.getItemStacks().set(0, recipe.output());
    }
}
