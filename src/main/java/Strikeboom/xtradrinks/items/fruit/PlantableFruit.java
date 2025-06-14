package Strikeboom.xtradrinks.items.fruit;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class PlantableFruit extends BlockItem {
    public PlantableFruit(Block p_41579_, ResourceLocation loc) {
        super(p_41579_,new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc)));
        NeoForgeDataMaps.COMPOSTABLES.(this,.65f);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
        tooltipAdder.accept(Component.translatable("item." + XtraDrinks.MOD_ID + ".tooltip.crop").withStyle(ChatFormatting.GREEN));

    }
}
