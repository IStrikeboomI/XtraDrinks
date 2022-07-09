package Strikeboom.xtradrinks.items.armor.armormaterials;

import Strikeboom.xtradrinks.init.XtraDrinksItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class FizziumArmorMaterial implements ArmorMaterial {
    @Override
    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return new int[]{455, 525, 560, 385}[pSlot.getIndex()];
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot pSlot) {
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
