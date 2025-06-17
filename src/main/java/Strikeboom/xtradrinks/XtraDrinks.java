package Strikeboom.xtradrinks;

import Strikeboom.xtradrinks.client.setup.ClientSetup;
import Strikeboom.xtradrinks.init.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(XtraDrinks.MOD_ID)
public class XtraDrinks {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "xtradrinks";


    public XtraDrinks(IEventBus modbus, ModContainer modContainer) {
        XtraDrinksFluids.FLUID_TYPES.register(modbus);
        XtraDrinksFluids.FLUIDS.register(modbus);
        XtraDrinksBlocks.BLOCKS.register(modbus);
        XtraDrinksItems.ITEMS.register(modbus);
        XtraDrinksBlockEntities.BLOCK_ENTITIES.register(modbus);
        XtraDrinksMenus.MENUS.register(modbus);
        XtraDrinksEntities.ENTITIES.register(modbus);
        XtraDrinksRecipes.RECIPE_TYPES.register(modbus);
        XtraDrinksRecipes.RECIPES.register(modbus);
        XtraDrinksRecipes.RECIPE_DISPLAYS.register(modbus);
        XtraDrinksRecipes.RECIPE_BOOK_CATEGORIES.register(modbus);
        XtraDrinksConfiguredFeatures.CONFIGURED_FEATURES.register(modbus);
        XtraDrinksPlacedFeatures.PLACED_FEATURES.register(modbus);
        XtraDrinksBiomeModifiers.BIOME_MODIFIERS.register(modbus);
        XtraDrinksCreativeModeTabs.CREATIVE_MODE_TABS.register(modbus);


        modContainer.registerConfig(ModConfig.Type.COMMON, XtraDrinksConfig.BUILDER.build());
    }
}
