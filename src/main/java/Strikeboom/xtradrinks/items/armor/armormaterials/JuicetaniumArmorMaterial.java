package Strikeboom.xtradrinks.items.armor.armormaterials;

import Strikeboom.xtradrinks.init.XtraDrinksItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class JuicetaniumArmorMaterial implements ArmorMaterial {
    @Override
    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return new int[]{655, 725, 760, 585}[pSlot.getIndex()];
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return new int[] {5, 10, 8, 4}[pSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return 25;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_NETHERITE;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(XtraDrinksItems.JUICETANIUM_INGOT.get());
    }

    @Override
    public String getName() {
        return "xtradrinks:juicetanium";
    }

    @Override
    public float getToughness() {
        return 5f;
    }

    @Override
    public float getKnockbackResistance() {
        return .5f;
    }
}
