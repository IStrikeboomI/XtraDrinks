package Strikeboom.xtradrinks.handlers;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.entity.EntityGreenman;
import Strikeboom.xtradrinks.init.ModBlocks;
import Strikeboom.xtradrinks.init.ModFluids;
import Strikeboom.xtradrinks.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber(modid = XtraDrinks.MOD_ID)
public class RegistryHandler {

    @SubscribeEvent
    public static void BlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void ItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void EntityRegister(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().register(EntityEntryBuilder.create()
                                    .entity(EntityGreenman.class)
                                    .id(new ResourceLocation(XtraDrinks.MOD_ID,"greenman"),333)
                                    .name("greenman")
                                    .egg(0x004700,0x004d8e)
                                    .tracker(50,1,true)
                                    .spawn(EnumCreatureType.AMBIENT,1,0,0, Biomes.PLAINS,Biomes.EXTREME_HILLS,Biomes.JUNGLE,Biomes.TAIGA,Biomes.FOREST,Biomes.ROOFED_FOREST,Biomes.BIRCH_FOREST,Biomes.MUTATED_FOREST,Biomes.TAIGA_HILLS,Biomes.BIRCH_FOREST_HILLS,Biomes.SAVANNA,Biomes.SAVANNA_PLATEAU,Biomes.DESERT, Biomes.MUTATED_PLAINS,Biomes.FOREST_HILLS)
                                    .build());
    }

    @SubscribeEvent
    public static void ModelRegister(ModelRegistryEvent event) {
        ModItems.ITEMS.forEach(item -> XtraDrinks.proxy.registerItemModel(item));
        ModFluids.FLUIDS.forEach(fluid -> XtraDrinks.proxy.registerFluidTexture(fluid));
    }
}
