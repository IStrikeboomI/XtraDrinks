package Strikeboom.xtradrinks.recipes.liquid_dehydrator;

import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class LiquidDehydratorRecipeSerializer implements RecipeSerializer<LiquidDehydratorRecipe> {
    public static boolean doesFluidStackHaveRecipe(FluidStack fluid, Level world) {
        for (LiquidDehydratorRecipe recipe : world.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE.get())) {
            if (recipe.getInput().getFluid() == fluid.getFluid()) {
                return true;
            }
        }
        return false;
    }
    public static ItemStack getItemStackFromFluidStack(FluidStack fluidStack, Level world) {
        for (LiquidDehydratorRecipe recipe : world.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE.get())) {
            if (recipe.getInput().getFluid() == fluidStack.getFluid()) {
                return recipe.getResultItem();
            }
        }
        return ItemStack.EMPTY;
    }
    public static FluidStack getFluidStackFromItemStack(ItemStack itemStack, Level world) {
        for (LiquidDehydratorRecipe recipe : world.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE.get())) {
            if (recipe.getResultItem().sameItem(itemStack)) {
                return recipe.getInput();
            }
        }
        return FluidStack.EMPTY;
    }
    public static FluidStack getFluidStackFromFluidStackInput(FluidStack fluidStack, Level world) {
        for (LiquidDehydratorRecipe recipe : world.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE.get())) {
            if (recipe.getInput().isFluidEqual(fluidStack)) {
                return recipe.getInput();
            }
        }
        return FluidStack.EMPTY;
    }

    @Override
    public LiquidDehydratorRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
        ResourceLocation fluidResourceLocation = ResourceLocation.of(GsonHelper.getAsString(pJson.get("input").getAsJsonObject(), "fluid", "minecraft:empty"), ':');
        int fluidAmount = GsonHelper.getAsInt(pJson.get("input").getAsJsonObject(), "amount", 0);
        if (fluidResourceLocation.getPath().equals("empty") || !ForgeRegistries.FLUIDS.containsKey(fluidResourceLocation)) {
            throw new com.google.gson.JsonSyntaxException("Missing or invalid fluid, expected to find a string or object");
        }
        if (fluidAmount <= 0) {
            throw new com.google.gson.JsonSyntaxException("Missing fluid amount, expected to find an int");
        }
        FluidStack fluidStack = new FluidStack(ForgeRegistries.FLUIDS.getValue(fluidResourceLocation), fluidAmount);
        if (!pJson.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack itemstack;
        if (pJson.get("result").isJsonObject()) itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
        else {
            String s1 = GsonHelper.getAsString(pJson, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            itemstack = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
        }
        return new LiquidDehydratorRecipe(pRecipeId,fluidStack,itemstack);
    }

    @Nullable
    @Override
    public LiquidDehydratorRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
        FluidStack ingredient = pBuffer.readFluidStack();
        ItemStack itemstack = pBuffer.readItem();
        return new LiquidDehydratorRecipe(pRecipeId,ingredient,itemstack);
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, LiquidDehydratorRecipe pRecipe) {
        pBuffer.writeFluidStack(pRecipe.getInput());
        pBuffer.writeItem(pRecipe.getResultItem());
    }
}
