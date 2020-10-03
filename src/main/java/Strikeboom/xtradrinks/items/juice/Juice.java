package Strikeboom.xtradrinks.items.juice;

import Strikeboom.xtradrinks.init.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Juice extends ItemFood {
    List<PotionEffect> effects = new ArrayList<>();
    public Juice(PotionEffect... effects) {
        super(4, 0.4f, false);
        this.effects.addAll(Arrays.asList(effects));
        setMaxStackSize(1);
        setAlwaysEdible();
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 22;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        stack.shrink(1);
        for (PotionEffect effect: effects) {
            entityLiving.addPotionEffect(new PotionEffect(effect.getPotion(),effect.getDuration() * 20,effect.getAmplifier()));
        }
         return stack.isEmpty() ? new ItemStack(ModItems.drink_cup): stack;
    }
}
