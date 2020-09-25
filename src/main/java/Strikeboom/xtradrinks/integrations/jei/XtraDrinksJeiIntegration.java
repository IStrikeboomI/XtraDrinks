package Strikeboom.xtradrinks.integrations.jei;


import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.container.ContainerDehydrator;
import Strikeboom.xtradrinks.init.ModBlocks;
import Strikeboom.xtradrinks.init.ModFluids;
import Strikeboom.xtradrinks.integrations.jei.dehydrator.DehydratorRecipeCategory;
import Strikeboom.xtradrinks.integrations.jei.dehydrator.DehydratorRecipes;
import Strikeboom.xtradrinks.integrations.jei.liquid_dehydrator.LiquidDehydratorCategory;
import Strikeboom.xtradrinks.integrations.jei.liquid_dehydrator.LiquidDehydratorRecipes;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import java.util.Arrays;

@JEIPlugin
public class XtraDrinksJeiIntegration implements IModPlugin {

    public static final String DEHYDRATOR_UID = XtraDrinks.MOD_ID + ".dehydrator";
    public static final String LIQUID_DEHYDRATOR_UID = XtraDrinks.MOD_ID + ".liquid_dehydrator";


    public XtraDrinksJeiIntegration() {}
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new DehydratorRecipeCategory(guiHelper),new LiquidDehydratorCategory(guiHelper));
    }

    @Override
    public void register(IModRegistry registry) {
        registry.addRecipes(DehydratorRecipes.recipes(),DEHYDRATOR_UID);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.dehydrator),DEHYDRATOR_UID);

        registry.addRecipes(LiquidDehydratorRecipes.recipes(),LIQUID_DEHYDRATOR_UID);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.liquid_dehydrator),LIQUID_DEHYDRATOR_UID);
        registry.getRecipeTransferRegistry().addRecipeTransferHandler(ContainerDehydrator.class,DEHYDRATOR_UID,0,1,0,36);

        registry.addIngredientInfo(Arrays.asList(FluidUtil.getFilledBucket(new FluidStack(ModFluids.liquadium_fluid,1000)),FluidUtil.getFilledBucket(new FluidStack(ModFluids.fizzium_fluid,1000))), VanillaTypes.ITEM,
                I18n.format("jei."+XtraDrinks.MOD_ID+".buckets_found"),I18n.format("jei."+XtraDrinks.MOD_ID+".buckets_liquid_dehydrator"));

    }


}
