package Strikeboom.XtraDrinks.recipes.dehydrator;

import Strikeboom.XtraDrinks.init.XtraDrinksRecipes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class DehydratorRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DehydratorRecipe> {
    public static boolean doesItemHaveRecipe(ItemStack item, World world) {
        for (DehydratorRecipe recipe: world.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.DEHYDRATOR_TYPE)) {
            if (recipe.getIngredients().get(0).test(item)) {
                return true;
            }
        }
        return false;
    }
    public static ItemStack getItemStackOutputFromInput(ItemStack item, World world) {
        for (DehydratorRecipe recipe: world.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.DEHYDRATOR_TYPE)) {
            if (recipe.getIngredients().get(0).test(item)) {
                return recipe.getResultItem().copy();
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public DehydratorRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
        JsonElement jsonelement = JSONUtils.isArrayNode(pJson, "input") ? JSONUtils.getAsJsonArray(pJson, "input") : JSONUtils.getAsJsonObject(pJson, "input");
        Ingredient ingredient = Ingredient.fromJson(jsonelement);

        if (!pJson.has("output")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack itemstack;
        if (pJson.get("output").isJsonObject()) itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(pJson, "output"));
        else {
            String s1 = JSONUtils.getAsString(pJson, "output");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            itemstack = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
        }
        return new DehydratorRecipe(pRecipeId,ingredient,itemstack);
    }

    @Nullable
    @Override
    public DehydratorRecipe fromNetwork(ResourceLocation pRecipeId, PacketBuffer pBuffer) {
        Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
        ItemStack itemstack = pBuffer.readItem();
        return new DehydratorRecipe(pRecipeId,ingredient,itemstack);
    }

    @Override
    public void toNetwork(PacketBuffer pBuffer, DehydratorRecipe pRecipe) {
        pRecipe.getIngredients().get(0).toNetwork(pBuffer);
        pBuffer.writeItem(pRecipe.getResultItem());
    }
}
