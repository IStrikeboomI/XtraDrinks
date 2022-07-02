package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.entity.GreenmanEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class XtraDrinksEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, XtraDrinks.MOD_ID);

    public static final RegistryObject<EntityType<GreenmanEntity>> GREENMAN = ENTITIES.register("greenman", () -> EntityType.Builder.of(GreenmanEntity::new, EntityClassification.CREATURE)
            .sized(0.6F, 1.95F)
            .clientTrackingRange(10)
            .setShouldReceiveVelocityUpdates(false)
            .build("greenman"));
}
