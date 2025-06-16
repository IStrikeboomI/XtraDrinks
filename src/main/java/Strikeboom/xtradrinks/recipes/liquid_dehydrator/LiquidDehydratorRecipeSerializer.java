package Strikeboom.xtradrinks.recipes.liquid_dehydrator;

import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipe;
import com.google.gson.JsonObject;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;


import javax.annotation.Nullable;
import java.util.Optional;

public class LiquidDehydratorRecipeSerializer implements RecipeSerializer<LiquidDehydratorRecipe> {
    public static final MapCodec<LiquidDehydratorRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            FluidStack.CODEC.fieldOf("ingredient").forGetter(o -> o.INPUT),
            ItemStack.CODEC.fieldOf("result").forGetter(o -> o.OUTPUT)
    ).apply(inst, LiquidDehydratorRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, LiquidDehydratorRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    FluidStack.STREAM_CODEC,  liquidDehydratorRecipe -> liquidDehydratorRecipe.INPUT,
                    ItemStack.STREAM_CODEC, liquidDehydratorRecipe -> liquidDehydratorRecipe.OUTPUT,
                    LiquidDehydratorRecipe::new
            );

    @Override
    public MapCodec<LiquidDehydratorRecipe> codec() {
        return CODEC;
    }
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, LiquidDehydratorRecipe> streamCodec() {
        return STREAM_CODEC;
    }

}
