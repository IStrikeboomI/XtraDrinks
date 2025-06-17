package Strikeboom.xtradrinks.items;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.XtraDrinksItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Juicer extends TooltipItem {
    public Juicer(String tooltip, Properties pProperties) {
        super(tooltip, pProperties);

    }
    @Override
    public ItemStack getCraftingRemainder(ItemStack stack) {
        return stack.getItem() == this ? new ItemStack(this) : ItemStack.EMPTY;
    }

}
