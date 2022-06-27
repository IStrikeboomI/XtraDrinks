package Strikeboom.XtraDrinks.items.armor;

import Strikeboom.XtraDrinks.XtraDrinks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class LiquadiumArmor extends ArmorItem {
    public LiquadiumArmor(IArmorMaterial pMaterial, EquipmentSlotType pSlot) {
        super(pMaterial, pSlot, new Properties().stacksTo(1).fireResistant().tab(XtraDrinks.CREATIVE_MODE_TAB));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable World pWorld, List<ITextComponent> pTooltipComponents, ITooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pWorld, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TranslationTextComponent("item." + XtraDrinks.MOD_ID + ".tooltip.liquadium_armor").withStyle(TextFormatting.YELLOW));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        int ArmourPieces = 0;

        for (EquipmentSlotType slot : EquipmentSlotType.values()) {
            if (slot.getType() == EquipmentSlotType.Group.ARMOR) {
                if (player.getItemBySlot(slot).getItem() instanceof LiquadiumArmor) {
                    ArmourPieces++;
                }
            }
        }

        if (ArmourPieces == 4) {
            player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 40,1));
        }
    }
}
