package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.entity.GreenmanEntity;
import Strikeboom.xtradrinks.menus.DehydratorMenu;
import Strikeboom.xtradrinks.menus.GreenmanMenu;
import Strikeboom.xtradrinks.menus.LiquidDehydratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class XtraDrinksMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, XtraDrinks.MOD_ID);

    public static final Supplier<MenuType<DehydratorMenu>> DEHYDRATOR_MENU = MENUS.register("dehydrator",
            () -> IMenuTypeExtension.create(DehydratorMenu::new));
    public static final Supplier<MenuType<LiquidDehydratorMenu>> LIQUID_DEHYDRATOR_MENU = MENUS.register("liquid_dehydrator",
            () -> IMenuTypeExtension.create(LiquidDehydratorMenu::new));
    public static final Supplier<MenuType<GreenmanMenu>> GREENMAN_MENU = MENUS.register("greenman",
            () -> IMenuTypeExtension.create(GreenmanMenu::new));

}
