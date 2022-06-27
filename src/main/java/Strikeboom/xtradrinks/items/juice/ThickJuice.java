package Strikeboom.XtraDrinks.items.juice;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.init.XtraDrinksItems;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThickJuice extends Item {
    List<EffectInstance> effects = new ArrayList<>();
    public ThickJuice(EffectInstance... effects) {
        super(new Properties().durability(0).stacksTo(1).food(new Food.Builder().nutrition(7).saturationMod(.7f).alwaysEat().build()).tab(XtraDrinks.CREATIVE_MODE_TAB));
        this.effects.addAll(List.of(effects));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable World pWorld, List<ITextComponent> pTooltipComponents, ITooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pWorld, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TranslationTextComponent("item." + XtraDrinks.MOD_ID + ".tooltip.juice"));

        List<Pair<Attribute, AttributeModifier>> list1 = Lists.newArrayList();
        if (!effects.isEmpty()) {
            for(EffectInstance effectinstance : effects) {
                IFormattableTextComponent iformattabletextcomponent = new TranslationTextComponent(effectinstance.getDescriptionId());
                Effect effect = effectinstance.getEffect();
                Map<Attribute, AttributeModifier> map = effect.getAttributeModifiers();
                if (!map.isEmpty()) {
                    for(Map.Entry<Attribute, AttributeModifier> entry : map.entrySet()) {
                        AttributeModifier attributemodifier = entry.getValue();
                        AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.getName(), effect.getAttributeModifierValue(effectinstance.getAmplifier(), attributemodifier), attributemodifier.getOperation());
                        list1.add(new Pair<>(entry.getKey(), attributemodifier1));
                    }
                }

                if (effectinstance.getAmplifier() > 0) {
                    iformattabletextcomponent = new TranslationTextComponent("potion.withAmplifier", iformattabletextcomponent, new TranslationTextComponent("potion.potency." + effectinstance.getAmplifier()));
                }

                if (effectinstance.getDuration() > 20) {
                    iformattabletextcomponent = new TranslationTextComponent("potion.withDuration", iformattabletextcomponent, EffectUtils.formatDuration(effectinstance, 1.0f));
                }

                pTooltipComponents.add(iformattabletextcomponent.withStyle(effect.getCategory().getTooltipFormatting()));
            }
        }

        if (!list1.isEmpty()) {
            pTooltipComponents.add(StringTextComponent.EMPTY);
            pTooltipComponents.add((new TranslationTextComponent("potion.whenDrank")).withStyle(TextFormatting.DARK_PURPLE));

            for(Pair<Attribute, AttributeModifier> pair : list1) {
                AttributeModifier attributemodifier2 = pair.getSecond();
                double d0 = attributemodifier2.getAmount();
                double d1;
                if (attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    d1 = attributemodifier2.getAmount();
                } else {
                    d1 = attributemodifier2.getAmount() * 100.0D;
                }

                if (d0 > 0.0D) {
                    pTooltipComponents.add((new TranslationTextComponent("attribute.modifier.plus." + attributemodifier2.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), new TranslationTextComponent(pair.getFirst().getDescriptionId()))).withStyle(TextFormatting.BLUE));
                } else if (d0 < 0.0D) {
                    d1 = d1 * -1.0D;
                    pTooltipComponents.add((new TranslationTextComponent("attribute.modifier.take." + attributemodifier2.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), new TranslationTextComponent(pair.getFirst().getDescriptionId()))).withStyle(TextFormatting.RED));
                }
            }
        }
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 40;
    }
    public UseAction getUseAnimation(ItemStack pStack) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, World pWorld, LivingEntity pLivingEntity) {
        pStack.shrink(1);
        for (EffectInstance effect: effects) {
            pLivingEntity.addEffect(effect);
        }
        return pStack.isEmpty() ? new ItemStack(XtraDrinksItems.THICK_CUP.get()): pStack;
    }
}

