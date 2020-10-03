package Strikeboom.xtradrinks.handlers;

import Strikeboom.xtradrinks.init.ModItems;
import Strikeboom.xtradrinks.items.fruit.*;
import Strikeboom.xtradrinks.items.juice.Juice;
import Strikeboom.xtradrinks.items.juice.ThickJuice;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {
    public static void init() {
        OreDictionary.registerOre("ingotFizzium", ModItems.fizzium_ingot);
        OreDictionary.registerOre("ingotJucetanium", ModItems.jucetanium_ingot);
        OreDictionary.registerOre("ingotLiquadium", ModItems.liquadium_ingot);
        OreDictionary.registerOre("fruitCitric",ModItems.lemon);
        OreDictionary.registerOre("fruitCitric",ModItems.lime);
        OreDictionary.registerOre("fruit",ModItems.orange);
        OreDictionary.registerOre("fruit",ModItems.coconut);
        OreDictionary.registerOre("fruit", Items.APPLE);
        OreDictionary.registerOre("fruit", Items.MELON);
        OreDictionary.registerOre("fruit", Items.CHORUS_FRUIT);
        OreDictionary.registerOre("fruit", Items.CHORUS_FRUIT_POPPED);
        OreDictionary.registerOre("fruitTropical",ModItems.lime);
        OreDictionary.registerOre("fruitTropical",ModItems.lemon);
        OreDictionary.registerOre("fruitTropical",ModItems.pomegranate);
        OreDictionary.registerOre("fruitTropical",ModItems.orange);
        OreDictionary.registerOre("fruitTropical",ModItems.coconut);
        for (Item item:ModItems.ITEMS) {
            if (item instanceof PlantableFruit) {
                OreDictionary.registerOre("fruit",item);
            }
            if (item instanceof DehydratedFruit) {
                OreDictionary.registerOre("fruitDehydrated",item);
            }
            if (item instanceof Juice) {
                OreDictionary.registerOre("juice",item);
            }
            if (item instanceof ThickJuice) {
                OreDictionary.registerOre("juiceThick",item);
            }
        }

    }
}
