package Strikeboom.xtradrinks.recipes.dehydrator;

import Strikeboom.xtradrinks.init.XtraDrinksItems;
import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record DehydratorRecipeDisplay(SlotDisplay input, SlotDisplay output) implements RecipeDisplay {
    public static final MapCodec<DehydratorRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            SlotDisplay.CODEC.fieldOf("input").forGetter(DehydratorRecipeDisplay::input),
                            SlotDisplay.CODEC.fieldOf("output").forGetter(DehydratorRecipeDisplay::result)
                    )
                    .apply(instance, DehydratorRecipeDisplay::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, DehydratorRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
            SlotDisplay.STREAM_CODEC,
            DehydratorRecipeDisplay::input,
            SlotDisplay.STREAM_CODEC,
            DehydratorRecipeDisplay::output,
            DehydratorRecipeDisplay::new
    );
    @Override
    public SlotDisplay result() {
        return output;
    }

    @Override
    public SlotDisplay craftingStation() {
        return new SlotDisplay.ItemSlotDisplay(XtraDrinksItems.DEHYDRATOR_ITEM);
    }

    @Override
    public Type<? extends RecipeDisplay> type() {
        return XtraDrinksRecipes.DEHYDRATOR_DISPLAY.get();
    }
}
