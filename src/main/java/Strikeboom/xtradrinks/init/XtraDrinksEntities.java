package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.entity.GreenmanEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XtraDrinksEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, XtraDrinks.MOD_ID);

    public static final RegistryObject<EntityType<GreenmanEntity>> GREENMAN = ENTITIES.register("greenman", () -> EntityType.Builder.of(GreenmanEntity::new, MobCategory.CREATURE)
            .sized(0.6F, 1.95F)
            .clientTrackingRange(10)
            .setShouldReceiveVelocityUpdates(false)
            .build("greenman"));
    public static final RegistryObject<Item> GREENMAN_EGG = XtraDrinksItems.ITEMS.register("greenman_spawn_egg", () -> new ForgeSpawnEggItem(GREENMAN, 0x004700,0x004d8e, new Item.Properties().durability(0).stacksTo(64).tab(XtraDrinks.CREATIVE_MODE_TAB)));
}
