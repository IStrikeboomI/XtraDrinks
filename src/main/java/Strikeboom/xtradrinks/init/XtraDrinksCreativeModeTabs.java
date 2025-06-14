package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.stream.Collectors;

public class XtraDrinksCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, XtraDrinks.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_MODE_TAB = CREATIVE_MODE_TABS.register(XtraDrinks.MOD_ID,() -> CreativeModeTab.builder()
            .icon(() -> XtraDrinksItems.JUICETANIUM_INGOT.get().getDefaultInstance())
            .title(Component.translatable("itemGroup.xtradrinks"))
            .displayItems((parameters, output) -> {
                output.acceptAll(XtraDrinksItems.ITEMS.getEntries().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()));
            })
            .build());
}
