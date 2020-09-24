package Strikeboom.xtradrinks.items.armor;

import Strikeboom.xtradrinks.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class LiquadiumArmor extends ItemArmor {
    public LiquadiumArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
    }
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack Stack) {
        int ArmourPeices = 0;

        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR) {
                if (player.getItemStackFromSlot(slot).getItem() instanceof LiquadiumArmor) {
                    ArmourPeices++;
                }
            }
        }

        if (ArmourPeices == 4) {
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 40, 1));
        }
    }

    @Override
    public boolean isRepairable() {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.liquadium_ingot;
    }
}
