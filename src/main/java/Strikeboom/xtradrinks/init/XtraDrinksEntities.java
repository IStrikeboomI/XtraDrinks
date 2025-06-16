package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.entity.GreenmanEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class XtraDrinksEntities {
    public static final DeferredRegister.Entities ENTITIES = DeferredRegister.createEntities(XtraDrinks.MOD_ID);
    public static final Supplier<EntityType<GreenmanEntity>> GREENMAN = ENTITIES.register("greenman", () -> EntityType.Builder.of(GreenmanEntity::new, MobCategory.CREATURE)
            .sized(0.6F, 1.95F)
            .clientTrackingRange(10)
            .setShouldReceiveVelocityUpdates(false)
            .noLootTable()
            .build(ResourceKey.create(
                    Registries.ENTITY_TYPE,
                    ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID, "greenman")
            )));
}
