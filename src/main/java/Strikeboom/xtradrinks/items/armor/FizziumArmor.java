package Strikeboom.xtradrinks.items.armor;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class FizziumArmor extends ItemArmor {
    public FizziumArmor(ArmorMaterial armorMaterial,int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(armorMaterial, renderIndexIn, equipmentSlotIn);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack Stack) {
        int ArmourPeices = 0;

        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR) {
                if (player.getItemStackFromSlot(slot).getItem() instanceof FizziumArmor) {
                    ArmourPeices++;
                }
            }
        }

        if (ArmourPeices == 4) {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 40, 1));
        }
    }

    @Override
    public boolean isRepairable() {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.fizzium_ingot;
    }
}
