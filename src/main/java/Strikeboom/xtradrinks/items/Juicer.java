package Strikeboom.XtraDrinks.items;

import Strikeboom.XtraDrinks.XtraDrinks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Juicer extends Item {
    public Juicer(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable World pWorld, List<ITextComponent> pTooltipComponents, ITooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pWorld, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TranslationTextComponent("item." + XtraDrinks.MOD_ID + ".tooltip.juicer").withStyle(TextFormatting.GRAY));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return stack.getItem() == this;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(this);
    }
}
