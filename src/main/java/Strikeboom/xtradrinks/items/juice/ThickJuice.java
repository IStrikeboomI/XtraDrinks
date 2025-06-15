package Strikeboom.xtradrinks.items.juice;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.XtraDrinksItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ThickJuice extends Item {
    List<MobEffectInstance> effects = new ArrayList<>();
    public ThickJuice(ResourceLocation loc, MobEffectInstance... effects) {
        super(new Properties().durability(0).stacksTo(1).food(new FoodProperties.Builder().nutrition(7).saturationModifier(.7f).alwaysEdible().build())
                .component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK)
                .usingConvertsTo(XtraDrinksItems.THICK_CUP.get()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc)));
        this.effects.addAll(List.of(effects));
    }


    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
        tooltipAdder.accept(Component.translatable("item." + XtraDrinks.MOD_ID + ".tooltip.juice"));
        PotionContents.addPotionTooltip(effects,tooltipAdder,1.0f,context.tickRate());
    }


    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pStack.shrink(1);
        for (MobEffectInstance effect: effects) {
            pLivingEntity.addEffect(effect);
        }
        return pStack.isEmpty() ? new ItemStack(XtraDrinksItems.THICK_CUP.get()): pStack;
    }
}

