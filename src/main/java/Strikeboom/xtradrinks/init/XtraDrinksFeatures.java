package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.worldgen.hangingcrops.HangingCropConfig;
import Strikeboom.XtraDrinks.worldgen.hangingcrops.HangingCropFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class XtraDrinksFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, XtraDrinks.MOD_ID);

    public static final RegistryObject<Feature<HangingCropConfig>> HANGING_CROP = FEATURES.register("hanging_crop",HangingCropFeature::new);
}
