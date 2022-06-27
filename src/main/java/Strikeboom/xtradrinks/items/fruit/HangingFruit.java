package Strikeboom.XtraDrinks.items.fruit;

import Strikeboom.XtraDrinks.XtraDrinks;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class HangingFruit extends BlockNamedItem {
    public HangingFruit(Block p_41579_) {
        super(p_41579_, new Item.Properties().food(new Food.Builder().nutrition(2).saturationMod(.5f).build()).tab(XtraDrinks.CREATIVE_MODE_TAB));
        ComposterBlock.COMPOSTABLES.put(this,.65f);
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable World pWorld, List<ITextComponent> pTooltipComponents, ITooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pWorld, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TranslationTextComponent("item." + XtraDrinks.MOD_ID + ".tooltip.hanging_crop").withStyle(TextFormatting.GREEN));
    }

}
