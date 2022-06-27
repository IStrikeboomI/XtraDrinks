package Strikeboom.XtraDrinks.blocks;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.entity.GreenmanEntity;
import Strikeboom.XtraDrinks.init.XtraDrinksConfig;
import Strikeboom.XtraDrinks.init.XtraDrinksEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class GreenmanLure extends Block {
    public GreenmanLure(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable IBlockReader pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(new TranslationTextComponent("block." + XtraDrinks.MOD_ID + ".tooltip.greenman_lure").withStyle(TextFormatting.GREEN));
    }

    @Override
    public void onPlace(BlockState pState, World pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pLevel.isClientSide()) {
            if (XtraDrinksConfig.GREENMAN_LURE_ENABLED.get()) {
                pLevel.destroyBlock(pPos, false);
                GreenmanEntity entity = new GreenmanEntity(XtraDrinksEntities.GREENMAN.get(), pLevel);
                entity.setPos(pPos.getX(), pPos.getY(), pPos.getZ());
                pLevel.addFreshEntity(entity);
            }
        }
    }
}
