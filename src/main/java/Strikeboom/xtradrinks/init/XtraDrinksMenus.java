package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.menus.DehydratorMenu;
import Strikeboom.xtradrinks.guis.menus.LiquidDehydratorMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XtraDrinksMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, XtraDrinks.MOD_ID);

    public static final RegistryObject<MenuType<DehydratorMenu>> DEHYDRATOR_MENU = MENUS.register("dehydrator",
            () -> IForgeMenuType.create((windowId, inv, data) -> new DehydratorMenu(windowId, data.readBlockPos(), inv, inv.player)));
    public static final RegistryObject<MenuType<LiquidDehydratorMenu>> LIQUID_DEHYDRATOR_MENU = MENUS.register("liquid_dehydrator",
            () -> IForgeMenuType.create((windowId, inv, data) -> new LiquidDehydratorMenu(windowId, data.readBlockPos(), inv, inv.player)));
}
