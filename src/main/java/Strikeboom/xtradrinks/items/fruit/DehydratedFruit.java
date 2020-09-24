package Strikeboom.xtradrinks.items.fruit;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class DehydratedFruit extends ItemFood {

    public DehydratedFruit() {
        super(6, .7f, false);

    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 400;
    }
}
