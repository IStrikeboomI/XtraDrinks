package Strikeboom.XtraDrinks.worldgen.structures;

import Strikeboom.xtradrinks.init.XtraDrinksConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;

import java.util.Optional;

public class WellStructure extends StructureFeature<JigsawConfiguration> {
    public WellStructure() {
        super(JigsawConfiguration.CODEC, context -> {
            if (!isFeatureChunk(context)) {
                return Optional.empty();
            } else {
                return createPiecesGenerator(context);
            }
        }, PostPlacementProcessor.NONE);
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        BlockPos pos = context.chunkPos().getWorldPosition();

        int landHeight = context.chunkGenerator().getFirstOccupiedHeight(pos.getX(), pos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());

        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor());

        BlockState topBlock = columnOfBlocks.getBlock(landHeight);

        return topBlock.getFluidState().isEmpty() && XtraDrinksConfig.STRUCTURES_ENABLED.get() && XtraDrinksConfig.WELLS_ENABLED.get();
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    private static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        return JigsawPlacement.addPieces(
                context,
                PoolElementStructurePiece::new,
                blockpos,
                false,
                true);
    }
}
