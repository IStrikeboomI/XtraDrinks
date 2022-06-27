package Strikeboom.XtraDrinks.items.armor.armormaterials;

import Strikeboom.XtraDrinks.init.XtraDrinksItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class JuicetaniumArmorMaterial implements IArmorMaterial {
    @Override
    public int getDurabilityForSlot(EquipmentSlotType pSlot) {
        return new int[]{655, 725, 760, 585}[pSlot.getIndex()];
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType pSlot) {
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
