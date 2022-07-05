package Strikeboom.XtraDrinks.recipes.liquid_dehydrator;

import Strikeboom.XtraDrinks.init.XtraDrinksRecipes;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class LiquidDehydratorRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<LiquidDehydratorRecipe> {
    public static boolean doesFluidStackHaveRecipe(FluidStack fluid, World world) {
        for (LiquidDehydratorRecipe recipe : world.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE)) {
            if (recipe.getInput().getFluid() == fluid.getFluid()) {
                return true;
            }
        }
        return false;
    }
    public static ItemStack getItemStackFromFluidStack(FluidStack fluidStack, World world) {
        for (LiquidDehydratorRecipe recipe : world.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE)) {
            if (recipe.getInput().getFluid() == fluidStack.getFluid()) {
                return recipe.getResultItem();
            }
        }
        return ItemStack.EMPTY;
    }
    public static FluidStack getFluidStackFromItemStack(ItemStack itemStack, World world) {
        for (LiquidDehydratorRecipe recipe : world.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE)) {
            if (recipe.getResultItem().sameItem(itemStack)) {
                return recipe.getInput();
            }
        }
        return FluidStack.EMPTY;
    }
    public static FluidStack getFluidStackFromFluidStackInput(FluidStack fluidStack, World world) {
        for (LiquidDehydratorRecipe recipe : world.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE)) {
            if (recipe.getInput().isFluidEqual(fluidStack)) {
                return recipe.getInput();
            }
        }
        return FluidStack.EMPTY;
    }

    @Override
    public LiquidDehydratorRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
        ResourceLocation fluidResourceLocation = ResourceLocation.of(JSONUtils.getAsString(pJson.get("input").getAsJsonObject(), "fluid", "minecraft:empty"), ':');
        int fluidAmount = JSONUtils.getAsInt(pJson.get("input").getAsJsonObject(), "amount", 0);
        FluidStack fluidStack = new FluidStack(ForgeRegistries.FLUIDS.getValue(fluidResourceLocation), fluidAmount);

        if (!pJson.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack itemstack;
        if (pJson.get("result").isJsonObject()) itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(pJson, "result"));
        else {
            String s1 = JSONUtils.getAsString(pJson, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            itemstack = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
        }
        return new LiquidDehydratorRecipe(pRecipeId,fluidStack,itemstack);
    }

    @Nullable
    @Override
    public LiquidDehydratorRecipe fromNetwork(ResourceLocation pRecipeId, PacketBuffer pBuffer) {
        FluidStack ingredient = pBuffer.readFluidStack();
        ItemStack itemstack = pBuffer.readItem();
        return new LiquidDehydratorRecipe(pRecipeId,ingredient,itemstack);
    }

    @Override
    public void toNetwork(PacketBuffer pBuffer, LiquidDehydratorRecipe pRecipe) {
        pBuffer.writeFluidStack(pRecipe.getInput());
        pBuffer.writeItem(pRecipe.getResultItem());
    }
}
