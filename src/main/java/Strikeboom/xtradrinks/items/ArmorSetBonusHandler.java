package Strikeboom.xtradrinks.items;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.XtraDrinksItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.ArmorHurtEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

//Handles the set bonuses for the fizzium, liquadium, and juicetanium armor
@EventBusSubscriber(modid = XtraDrinks.MOD_ID)
public class ArmorSetBonusHandler {
    @SubscribeEvent
    public static void onArmorTickEvent(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide()) {
            int fizzium = 0, liquadium = 0, juicetanium = 0;
            for (EquipmentSlot equipmentslot : EquipmentSlotGroup.ARMOR) {
                if (equipmentslot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                    ItemStack itemstack = player.getItemBySlot(equipmentslot);
                    if (itemstack.getItem() == XtraDrinksItems.FIZZIUM_HELMET.get()
                     || itemstack.getItem() == XtraDrinksItems.FIZZIUM_CHESTPLATE.get()
                     || itemstack.getItem() == XtraDrinksItems.FIZZIUM_LEGGINGS.get()
                     || itemstack.getItem() == XtraDrinksItems.FIZZIUM_BOOTS.get()) {
                        fizzium++;
                    }
                    if (       itemstack.getItem() == XtraDrinksItems.LIQUADIUM_HELMET.get()
                            || itemstack.getItem() == XtraDrinksItems.LIQUADIUM_CHESTPLATE.get()
                            || itemstack.getItem() == XtraDrinksItems.LIQUADIUM_LEGGINGS.get()
                            || itemstack.getItem() == XtraDrinksItems.LIQUADIUM_BOOTS.get()) {
                        liquadium++;
                    }
                    if (       itemstack.getItem() == XtraDrinksItems.JUICETANIUM_HELMET.get()
                            || itemstack.getItem() == XtraDrinksItems.JUICETANIUM_CHESTPLATE.get()
                            || itemstack.getItem() == XtraDrinksItems.JUICETANIUM_LEGGINGS.get()
                            || itemstack.getItem() == XtraDrinksItems.JUICETANIUM_BOOTS.get()) {
                        juicetanium++;
                    }
                }
            }
            if (fizzium == 4) {
                player.addEffect(new MobEffectInstance(MobEffects.SPEED, 40,1));
            }
            if (liquadium == 4) {
                player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 40,1));
            }
            if (juicetanium == 4) {
                player.addEffect(new MobEffectInstance(MobEffects.SPEED, 40,2));
                player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 40,2));
                player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 40,1));
                player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 40,1));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 40,2));

            }
        }
    }
}
