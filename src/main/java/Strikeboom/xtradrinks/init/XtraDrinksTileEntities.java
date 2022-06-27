package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.guis.tileentities.DehydratorTileEntity;
import Strikeboom.XtraDrinks.guis.tileentities.LiquidDehydratorTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class XtraDrinksTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, XtraDrinks.MOD_ID);

    public static final RegistryObject<TileEntityType<DehydratorTileEntity>> DEHYDRATOR_TILE_ENTITY = TILE_ENTITIES.register("dehydrator",() -> TileEntityType.Builder.of(DehydratorTileEntity::new,XtraDrinksBlocks.DEHYDRATOR.get()).build(null));
    public static final RegistryObject<TileEntityType<LiquidDehydratorTileEntity>> LIQUID_DEHYDRATOR_TILE_ENTITY = TILE_ENTITIES.register("liquid_dehydrator",() -> TileEntityType.Builder.of(LiquidDehydratorTileEntity::new,XtraDrinksBlocks.LIQUID_DEHYDRATOR.get()).build(null));

}
