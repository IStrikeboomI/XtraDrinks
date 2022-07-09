package Strikeboom.xtradrinks.recipes.dehydrator;

import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class DehydratorRecipeSerializer implements RecipeSerializer<DehydratorRecipe> {
    public static boolean doesItemHaveRecipe(ItemStack item, Level world) {
        for (DehydratorRecipe recipe: world.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.DEHYDRATOR_TYPE.get())) {
            if (recipe.getIngredients().get(0).test(item)) {
                return true;
            }
        }
        return false;
    }
    public static ItemStack getItemStackOutputFromInput(ItemStack item, Level world) {
        for (DehydratorRecipe recipe: world.getRecipeManager().getAllRecipesFor(XtraDrinksRecipes.DEHYDRATOR_TYPE.get())) {
            if (recipe.getIngredients().get(0).test(item)) {
                return recipe.getResultItem().copy();
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public DehydratorRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
        JsonElement jsonelement = GsonHelper.isArrayNode(pJson, "input") ? GsonHelper.getAsJsonArray(pJson, "input") : GsonHelper.getAsJsonObject(pJson, "input");
        Ingredient ingredient = Ingredient.fromJson(jsonelement);

        if (!pJson.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack itemstack;
        if (pJson.get("result").isJsonObject()) itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
        else {
            String s1 = GsonHelper.getAsString(pJson, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            itemstack = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
        }
        return new DehydratorRecipe(pRecipeId,ingredient,itemstack);
    }

    @Nullable
    @Override
    public DehydratorRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
        Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
        ItemStack itemstack = pBuffer.readItem();
        return new DehydratorRecipe(pRecipeId,ingredient,itemstack);
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, DehydratorRecipe pRecipe) {
        pRecipe.getIngredients().get(0).toNetwork(pBuffer);
        pBuffer.writeItem(pRecipe.getResultItem());
    }
}
