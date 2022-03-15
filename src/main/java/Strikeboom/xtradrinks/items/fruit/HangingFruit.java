package Strikeboom.xtradrinks.items.fruit;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;

public class HangingFruit extends ItemNameBlockItem {
    public HangingFruit(Block p_41579_) {
        super(p_41579_, new Item.Properties().durability(0).stacksTo(64).food(new FoodProperties.Builder().nutrition(2).saturationMod(.5f).build()).tab(XtraDrinks.CREATIVE_MODE_TAB));
    }
}
