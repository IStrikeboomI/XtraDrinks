package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.blocks.Dehydrator;
import Strikeboom.xtradrinks.blocks.GreenmanLure;
import Strikeboom.xtradrinks.blocks.LiquidDehydrator;
import Strikeboom.xtradrinks.blocks.crops.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class ModBlocks {
    public static final List<Block> BLOCKS = new LinkedList<>();

    public static Block dehydrator;
    public static Block liquid_dehydrator;
    public static Block greenman_lure;
    public static Block pomegranate;
    public static Block grape;
    public static Block pineapple;
    public static Block pines;
    public static Block lemon;
    public static Block lime;
    public static Block coconut;
    public static Block oranges;
    public static Block cranberry;
    public static Block blueberry;
    public static Block blackberry;
    public static Block juicetanium_block;
    public static Block fizzium_block;
    public static Block liquadium_block;

    public static void preInit() {
        lemon = new Lemon().setRegistryName("lemon").setUnlocalizedName("lemon");
        lime = new Lime().setRegistryName("lime").setUnlocalizedName("lime");
        pineapple = new Pineapple().setRegistryName("pineapple").setUnlocalizedName("pineapple");
        pomegranate = new Pomegranate().setRegistryName("pomegranate").setUnlocalizedName("pomegranate");
        grape = new Grape().setRegistryName("grape").setUnlocalizedName("grape");
        cranberry = new Cranberry().setRegistryName("cranberry").setUnlocalizedName("cranberry");
        blueberry = new BlueBerry().setRegistryName("blueberry").setUnlocalizedName("blueberry");
        blackberry = new Blackberry().setRegistryName("blackberry").setUnlocalizedName("blackberry");
        pines = new Pines().setUnlocalizedName("pines").setRegistryName("pines");
        oranges = new Orange().setUnlocalizedName("orange").setRegistryName("orange");
        coconut = new Coconut().setUnlocalizedName("coconut").setRegistryName("coconut");

        dehydrator = new Dehydrator().setRegistryName("dehydrator").setUnlocalizedName("dehydrator");
        liquid_dehydrator = new LiquidDehydrator().setRegistryName("liquid_dehydrator").setUnlocalizedName("liquid_dehydrator");
        juicetanium_block = new Block(Material.IRON).setRegistryName("juicetanium").setUnlocalizedName("juicetanium").setHardness(10.0f).setResistance(100.0f);
        fizzium_block = new Block(Material.IRON).setRegistryName("fizzium").setUnlocalizedName("fizzium").setHardness(10.0f).setResistance(100.0f);
        liquadium_block = new Block(Material.IRON).setRegistryName("liquadium").setUnlocalizedName("liquadium").setHardness(10.0f).setResistance(100.0f);
        greenman_lure = new GreenmanLure().setRegistryName("greenman_lure").setUnlocalizedName("greenman_lure");
        //spacing for easier copy ability
        BLOCKS.add(pomegranate  );
        BLOCKS.add(grape        );
        BLOCKS.add(pineapple    );
        BLOCKS.add(pines        );
        BLOCKS.add(lemon        );
        BLOCKS.add(lime         );
        BLOCKS.add(coconut      );
        BLOCKS.add(oranges      );
        BLOCKS.add(cranberry    );
        BLOCKS.add(blueberry    );
        BLOCKS.add(blackberry   );
        add(dehydrator          );
        add(liquid_dehydrator   );
        add(fizzium_block       );
        add(juicetanium_block   );
        add(liquadium_block     );
        add(greenman_lure);
    }

    private static void add(@Nonnull Block block) {
        block.setCreativeTab(XtraDrinks.XTRADRINKS_CREATIVE_TAB);
        BLOCKS.add(block);
        ModItems.ITEMS.add(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }
}
