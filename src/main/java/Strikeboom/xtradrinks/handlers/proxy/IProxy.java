package Strikeboom.xtradrinks.handlers.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;

public interface IProxy {
    void registerItemModel(Item item);
    void registerFluidTexture(Fluid fluid);
    void preInit();
    void init();
}
