package Strikeboom.xtradrinks.blocks;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.entity.GreenmanEntity;
import Strikeboom.xtradrinks.init.XtraDrinksEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GreenmanLure extends Block {
    public GreenmanLure(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(new TranslatableComponent("block." + XtraDrinks.MOD_ID + ".tooltip.greenman_lure").withStyle(ChatFormatting.GREEN));
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pLevel.isClientSide()) {
            pLevel.destroyBlock(pPos,false);
            GreenmanEntity entity = new GreenmanEntity(XtraDrinksEntities.GREENMAN.get(),pLevel);
            entity.setPos(pPos.getX(),pPos.getY(),pPos.getZ());
            pLevel.addFreshEntity(entity);
        }
    }
}
