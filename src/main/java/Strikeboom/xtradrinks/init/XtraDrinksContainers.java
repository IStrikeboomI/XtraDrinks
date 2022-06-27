package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.entity.GreenmanEntity;
import Strikeboom.XtraDrinks.guis.containers.DehydratorContainer;
import Strikeboom.XtraDrinks.guis.containers.GreenmanContainer;
import Strikeboom.XtraDrinks.guis.containers.LiquidDehydratorContainer;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class XtraDrinksContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, XtraDrinks.MOD_ID);

    public static final RegistryObject<ContainerType<DehydratorContainer>> DEHYDRATOR_CONTAINER = CONTAINERS.register("dehydrator",
            () -> IForgeContainerType.create((windowId, inv, data) -> new DehydratorContainer(windowId, data.readBlockPos(), inv, inv.player, IWorldPosCallable.NULL)));
    public static final RegistryObject<ContainerType<LiquidDehydratorContainer>> LIQUID_DEHYDRATOR_CONTAINER = CONTAINERS.register("liquid_dehydrator",
            () -> IForgeContainerType.create((windowId, inv, data) -> new LiquidDehydratorContainer(windowId, data.readBlockPos(), inv, inv.player,IWorldPosCallable.NULL)));
    public static final RegistryObject<ContainerType<GreenmanContainer>> GREENMAN_CONTAINER = CONTAINERS.register("greenman",
            () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new GreenmanContainer(windowId, inv.player.level.getNearestEntity(GreenmanEntity.class, new EntityPredicate().range(5),inv.player,pos.getX(),pos.getY(),pos.getZ(),new AxisAlignedBB(pos).inflate(1)),inv);
            }));

}
