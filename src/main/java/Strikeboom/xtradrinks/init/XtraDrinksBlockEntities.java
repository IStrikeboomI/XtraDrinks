package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.blockentities.DehydratorBlockEntity;
import Strikeboom.xtradrinks.blockentities.LiquidDehydratorBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class XtraDrinksBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, XtraDrinks.MOD_ID);

    public static final Supplier<BlockEntityType<DehydratorBlockEntity>> DEHYDRATOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("dehydrator",() -> new BlockEntityType<>(DehydratorBlockEntity::new,XtraDrinksBlocks.DEHYDRATOR.get()));
    public static final Supplier<BlockEntityType<LiquidDehydratorBlockEntity>> LIQUID_DEHYDRATOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("liquid_dehydrator",() -> new BlockEntityType<>(LiquidDehydratorBlockEntity::new,XtraDrinksBlocks.LIQUID_DEHYDRATOR.get()));

}
