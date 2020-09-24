package Strikeboom.xtradrinks.items.armor;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ArmorMaterials {
    public static final ItemArmor.ArmorMaterial FIZZIUM = EnumHelper.addArmorMaterial("fizzium_armor", XtraDrinks.MOD_ID+":fizzium",149,new int[] {3,8,6,6}, 24, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,1.0f);
    public static final ItemArmor.ArmorMaterial LIQUIDADIUM = EnumHelper.addArmorMaterial("liquadium_armor", XtraDrinks.MOD_ID+":liquadium",149,new int[] {3,8,6,6}, 24, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,1.0f);
    public static final ItemArmor.ArmorMaterial JUICEITANIUM = EnumHelper.addArmorMaterial("jucetanium_armor", XtraDrinks.MOD_ID+":jucetanium",478,new int[] {8,11,8,9}, 32, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,3.0f);
}
