package Strikeboom.xtradrinks.items.fruit;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlantableFruit extends ItemNameBlockItem {
    public PlantableFruit(Block p_41579_) {
        super(p_41579_,new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(.5f).build()).tab(XtraDrinks.CREATIVE_MODE_TAB));
        ComposterBlock.COMPOSTABLES.put(this,.65f);
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TranslatableComponent("item." + XtraDrinks.MOD_ID + ".tooltip.crop").withStyle(ChatFormatting.GREEN));
    }
}
