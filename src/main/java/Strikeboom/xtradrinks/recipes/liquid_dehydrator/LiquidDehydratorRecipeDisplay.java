package Strikeboom.xtradrinks.recipes.liquid_dehydrator;

import Strikeboom.xtradrinks.init.XtraDrinksItems;
import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record LiquidDehydratorRecipeDisplay(SlotDisplay output) implements RecipeDisplay {
    public static final MapCodec<LiquidDehydratorRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            SlotDisplay.CODEC.fieldOf("output").forGetter(LiquidDehydratorRecipeDisplay::result)
                    )
                    .apply(instance, LiquidDehydratorRecipeDisplay::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, LiquidDehydratorRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
            SlotDisplay.STREAM_CODEC,
            LiquidDehydratorRecipeDisplay::output,
            LiquidDehydratorRecipeDisplay::new
    );
    @Override
    public SlotDisplay result() {
        return output;
    }

    @Override
    public SlotDisplay craftingStation() {
        return new SlotDisplay.ItemSlotDisplay(XtraDrinksItems.LIQUID_DEHYDRATOR_ITEM);
    }

    @Override
    public Type<? extends RecipeDisplay> type() {
        return XtraDrinksRecipes.LIQUID_DEHYDRATOR_DISPLAY.get();
    }
}
