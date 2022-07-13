package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.entity.GreenmanEntity;
import Strikeboom.xtradrinks.guis.menus.DehydratorMenu;
import Strikeboom.xtradrinks.guis.menus.GreenmanMenu;
import Strikeboom.xtradrinks.guis.menus.LiquidDehydratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XtraDrinksMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, XtraDrinks.MOD_ID);

    public static final RegistryObject<MenuType<DehydratorMenu>> DEHYDRATOR_MENU = MENUS.register("dehydrator",
            () -> IForgeMenuType.create((windowId, inv, data) -> new DehydratorMenu(windowId, data.readBlockPos(), inv, inv.player)));
    public static final RegistryObject<MenuType<LiquidDehydratorMenu>> LIQUID_DEHYDRATOR_MENU = MENUS.register("liquid_dehydrator",
            () -> IForgeMenuType.create((windowId, inv, data) -> new LiquidDehydratorMenu(windowId, data.readBlockPos(), inv, inv.player)));
    public static final RegistryObject<MenuType<GreenmanMenu>> GREENMAN_MENU = MENUS.register("greenman",
            () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new GreenmanMenu(windowId, inv.player.level.getNearestEntity(GreenmanEntity.class, TargetingConditions.DEFAULT.range(5),inv.player,pos.getX(),pos.getY(),pos.getZ(),new AABB(pos).inflate(1)), inv);
            }));

}
