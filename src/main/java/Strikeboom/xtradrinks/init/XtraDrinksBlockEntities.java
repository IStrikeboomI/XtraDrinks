package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.blockentities.DehydratorBlockEntity;
import Strikeboom.xtradrinks.guis.blockentities.LiquidDehydratorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XtraDrinksBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, XtraDrinks.MOD_ID);

    public static final RegistryObject<BlockEntityType<DehydratorBlockEntity>> DEHYDRATOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("dehydrator",() -> BlockEntityType.Builder.of(DehydratorBlockEntity::new,XtraDrinksBlocks.DEHYDRATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<LiquidDehydratorBlockEntity>> LIQUID_DEHYDRATOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("liquid_dehydrator",() -> BlockEntityType.Builder.of(LiquidDehydratorBlockEntity::new,XtraDrinksBlocks.LIQUID_DEHYDRATOR.get()).build(null));

}
