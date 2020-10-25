package Strikeboom.xtradrinks;

import Strikeboom.xtradrinks.entity.EntityGreenman;
import Strikeboom.xtradrinks.guis.tileentity.TileEntityDehydrator;
import Strikeboom.xtradrinks.guis.tileentity.TileEntityLiquidDehydrator;
import Strikeboom.xtradrinks.guis.tileentity.recipes.dehydrator.DehydratorRecipeRegister;
import Strikeboom.xtradrinks.guis.tileentity.recipes.liquid_dehydrator.LiquidDehydratorRecipeRegister;
import Strikeboom.xtradrinks.handlers.GuiHandler;
import Strikeboom.xtradrinks.handlers.OreDictHandler;
import Strikeboom.xtradrinks.handlers.PacketHandler;
import Strikeboom.xtradrinks.handlers.proxy.IProxy;
import Strikeboom.xtradrinks.init.ModBlocks;
import Strikeboom.xtradrinks.init.ModFluids;
import Strikeboom.xtradrinks.init.ModItems;
import Strikeboom.xtradrinks.init.ModSmelting;
import Strikeboom.xtradrinks.integrations.Integrations;
import Strikeboom.xtradrinks.worldgen.CropOnGrassWorldGen;
import Strikeboom.xtradrinks.worldgen.FruitOnTreeWorldGen;
import Strikeboom.xtradrinks.worldgen.StructureWorldGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = XtraDrinks.MOD_ID, name = "Xtra Drinks", version ="1.2",acceptedMinecraftVersions ="[1.12]")
public class XtraDrinks
{
    public static final String MOD_ID = "xtradrinks";
    public static Logger logger;

    @Mod.Instance
    public static XtraDrinks instance;

    public static final CreativeTabs XTRADRINKS_CREATIVE_TAB = new CreativeTabs(MOD_ID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.jucetanium_ingot);
        }

    };

    @SidedProxy(serverSide = "Strikeboom.xtradrinks.handlers.proxy.ServerProxy",clientSide = "Strikeboom.xtradrinks.handlers.proxy.ClientProxy")
    public static IProxy proxy;

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance,new GuiHandler());
        ModFluids.preInit();
        ModBlocks.preInit();
        ModItems.preInit();
        Integrations.preInit();
        proxy.preInit();
        logger.info("Pre Init");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Integrations.init();
        ModSmelting.init();
        OreDictHandler.init();
        DehydratorRecipeRegister.init();
        LiquidDehydratorRecipeRegister.init();
        MinecraftForge.EVENT_BUS.register(EntityGreenman.GreenmanEvents.class);
        GameRegistry.registerTileEntity(TileEntityDehydrator.class,new ResourceLocation(XtraDrinks.MOD_ID, "dehydrator"));
        GameRegistry.registerTileEntity(TileEntityLiquidDehydrator.class,new ResourceLocation(XtraDrinks.MOD_ID, "liquid_dehydrator"));
        PacketHandler.init();
        GameRegistry.registerWorldGenerator(new StructureWorldGen(),2);
        GameRegistry.registerWorldGenerator(new CropOnGrassWorldGen(),2);
        GameRegistry.registerWorldGenerator(new FruitOnTreeWorldGen(),3);
        proxy.init();
        logger.info("Init");
    }
}
