package Strikeboom.xtradrinks.items.armor;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FizziumArmor extends ArmorItem {
    public FizziumArmor(ArmorMaterial pMaterial, EquipmentSlot pSlot) {
        super(pMaterial, pSlot, new Properties().stacksTo(1).fireResistant().tab(XtraDrinks.CREATIVE_MODE_TAB));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.translatable("item." + XtraDrinks.MOD_ID + ".tooltip.fizzium_armor").withStyle(ChatFormatting.YELLOW));
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        int ArmourPieces = 0;

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                if (player.getItemBySlot(slot).getItem() instanceof FizziumArmor) {
                    ArmourPieces++;
                }
            }
        }

        if (ArmourPieces == 4) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40,1));
        }
    }
}
