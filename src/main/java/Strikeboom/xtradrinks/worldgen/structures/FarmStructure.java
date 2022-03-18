package Strikeboom.xtradrinks.worldgen.structures;

import Strikeboom.xtradrinks.init.XtraDrinksConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
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
import java.util.Random;

public class FarmStructure extends StructureFeature<JigsawConfiguration> {
    public FarmStructure() {
        super(JigsawConfiguration.CODEC, context -> {
            if (!isFeatureChunk(context)) {
                return Optional.empty();
            } else {
                return createPiecesGenerator(context);
            }
        }, PostPlacementProcessor.NONE);
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        ChunkPos chunkPos = context.chunkPos();
        Random random = new Random(chunkPos.x + chunkPos.z * 0x9E7F71L);

        return XtraDrinksConfig.STRUCTURES_ENABLED.get() && random.nextInt(0,300) >= XtraDrinksConfig.FARM_MIN_HEIGHT.get();
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    private static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);
        ChunkPos chunkPos = context.chunkPos();
        Random random = new Random(chunkPos.x + chunkPos.z * 0x9E7F71L);
        BlockPos skyBlockPos = blockpos.mutable().move(0,random.nextInt(0,300),0);

        return JigsawPlacement.addPieces(
                context,
                PoolElementStructurePiece::new,
                skyBlockPos,
                false,
                false);
    }
}
