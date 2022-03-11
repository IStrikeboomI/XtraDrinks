package Strikeboom.xtradrinks.worldgen.filters;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.Random;

public class CropPlacementFilter extends PlacementFilter {
    @Override
    protected boolean shouldPlace(PlacementContext p_191835_, Random p_191836_, BlockPos p_191837_) {

        return false;
    }

    @Override
    public PlacementModifierType<?> type() {
        return PlacementModifierType.BLOCK_PREDICATE_FILTER;
    }
}
