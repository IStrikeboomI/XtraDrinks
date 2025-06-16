package Strikeboom.xtradrinks.recipes.dehydrator;

import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.Optional;

public class DehydratorRecipeSerializer implements RecipeSerializer<DehydratorRecipe> {
    public static final MapCodec<DehydratorRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(o -> o.INPUT),
            ItemStack.CODEC.fieldOf("result").forGetter(o -> o.OUTPUT)
    ).apply(inst, DehydratorRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, DehydratorRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC, dehydratorRecipe -> dehydratorRecipe.INPUT,
                    ItemStack.STREAM_CODEC, dehydratorRecipe -> dehydratorRecipe.OUTPUT,
                    DehydratorRecipe::new
            );

    @Override
    public MapCodec<DehydratorRecipe> codec() {
        return CODEC;
    }
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, DehydratorRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
