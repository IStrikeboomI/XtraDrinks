package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.blockentities.DehydratorBlockEntity;
import Strikeboom.xtradrinks.blockentities.LiquidDehydratorBlockEntity;
import net.minecraft.core.Direction;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD,modid = XtraDrinks.MOD_ID)
public class XtraDrinksCapabilities {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK,
                XtraDrinksBlockEntities.DEHYDRATOR_BLOCK_ENTITY.get(),
                new ICapabilityProvider<>() {
                    @Override
                    public @Nullable IItemHandler getCapability(DehydratorBlockEntity object, Direction context) {
                        return object.itemMachineHandler;
                    }
                });
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK,
                XtraDrinksBlockEntities.LIQUID_DEHYDRATOR_BLOCK_ENTITY.get(),
                new ICapabilityProvider<>() {
                    @Override
                    public @Nullable IItemHandler getCapability(LiquidDehydratorBlockEntity object, Direction context) {
                        return object.itemHandler;
                    }
                });
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK,
                XtraDrinksBlockEntities.LIQUID_DEHYDRATOR_BLOCK_ENTITY.get(),
                new ICapabilityProvider<>() {
                    @Override
                    public @Nullable IFluidHandler getCapability(LiquidDehydratorBlockEntity object, Direction context) {
                        return object.fluidTank;
                    }
                });
        event.registerEntity(Capabilities.ItemHandler.ENTITY,
                XtraDrinksEntities.GREENMAN);
    }
}
