package Strikeboom.XtraDrinks;

import Strikeboom.XtraDrinks.client.setup.ClientSetup;
import Strikeboom.XtraDrinks.init.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "xtradrinks";

    public static final ItemGroup CREATIVE_MODE_TAB = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(XtraDrinksItems.JUICETANIUM_INGOT.get());
        }
    };

    public XtraDrinks() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();

        XtraDrinksFluids.FLUIDS.register(modbus);
        XtraDrinksBlocks.BLOCKS.register(modbus);
        XtraDrinksItems.ITEMS.register(modbus);
        XtraDrinksTileEntities.TILE_ENTITIES.register(modbus);
        XtraDrinksContainers.CONTAINERS.register(modbus);
        XtraDrinksEntities.ENTITIES.register(modbus);
        XtraDrinksStructures.STRUCTURES.register(modbus);
        XtraDrinksRecipes.RECIPES.register(modbus);
        XtraDrinksFeatures.FEATURES.register(modbus);

        modbus.addListener(this::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, XtraDrinksConfig.COMMON);
    }
    private void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            XtraDrinksRecipes.register();
            XtraDrinksPackets.init();
            XtraDrinksStructures.setupStructures();
            XtraDrinksStructureFeatures.registerConfiguredStructures();
        });
    }
}
