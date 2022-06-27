package Strikeboom.XtraDrinks.items.armor.armormaterials;

import Strikeboom.XtraDrinks.init.XtraDrinksItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class FizziumArmorMaterial implements IArmorMaterial {
    @Override
    public int getDurabilityForSlot(EquipmentSlotType pSlot) {
        return new int[]{455, 525, 560, 385}[pSlot.getIndex()];
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType pSlot) {
        return new int[] {3, 6, 8, 3}[pSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return 19;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(XtraDrinksItems.FIZZIUM_INGOT.get());
    }

    @Override
    public String getName() {
        return "xtradrinks:fizzium";
    }

    @Override
    public float getToughness() {
        return 3f;
    }

    @Override
    public float getKnockbackResistance() {
        return .3f;
    }
}
