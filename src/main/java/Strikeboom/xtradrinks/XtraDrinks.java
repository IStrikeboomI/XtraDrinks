package Strikeboom.xtradrinks;

import Strikeboom.xtradrinks.client.setup.ClientSetup;
import Strikeboom.xtradrinks.init.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(XtraDrinks.MOD_ID)
public class XtraDrinks {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "xtradrinks";

    public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(XtraDrinksItems.JUICETANIUM_INGOT.get());
        }
    };

    public XtraDrinks() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();

        XtraDrinksFluids.FLUID_TYPES.register(modbus);
        XtraDrinksFluids.FLUIDS.register(modbus);
        XtraDrinksBlocks.BLOCKS.register(modbus);
        XtraDrinksItems.ITEMS.register(modbus);
        XtraDrinksBlockEntities.BLOCK_ENTITIES.register(modbus);
        XtraDrinksMenus.MENUS.register(modbus);
        XtraDrinksEntities.ENTITIES.register(modbus);
        XtraDrinksRecipes.RECIPE_TYPES.register(modbus);
        XtraDrinksRecipes.RECIPES.register(modbus);
        XtraDrinksConfiguredFeatures.CONFIGURED_FEATURES.register(modbus);
        XtraDrinksPlacedFeatures.PLACED_FEATURES.register(modbus);
        XtraDrinksBiomeModifiers.BIOME_MODIFIERS.register(modbus);

        modbus.addListener(this::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, XtraDrinksConfig.COMMON);
    }
    private void init(FMLCommonSetupEvent event) {
        event.enqueueWork(XtraDrinksPackets::init);
    }
}
