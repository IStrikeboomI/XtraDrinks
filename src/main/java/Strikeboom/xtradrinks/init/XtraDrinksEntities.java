package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.entity.GreenmanEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
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
    public static final RegistryObject<Item> GREENMAN_EGG = XtraDrinksItems.ITEMS.register("greenman_spawn_egg", () -> new ForgeSpawnEggItem(GREENMAN, 0x004700,0x004d8e, new Item.Properties().durability(0).stacksTo(64).tab(XtraDrinks.CREATIVE_MODE_TAB)));
}
