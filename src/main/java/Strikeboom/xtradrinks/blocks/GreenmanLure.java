package Strikeboom.xtradrinks.blocks;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.entity.GreenmanEntity;
import Strikeboom.xtradrinks.init.XtraDrinksConfig;
import Strikeboom.xtradrinks.init.XtraDrinksEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class GreenmanLure extends Block implements TooltipProvider {
    public GreenmanLure(Properties p_49795_) {
        super(p_49795_);
    }


    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pLevel.isClientSide()) {
            if (XtraDrinksConfig.GREENMAN_LURE_ENABLED.get()) {
                pLevel.destroyBlock(pPos, false);
                GreenmanEntity entity = new GreenmanEntity(XtraDrinksEntities.GREENMAN.get(), pLevel);
                entity.setPos(pPos.getX(), pPos.getY(), pPos.getZ());
                pLevel.addFreshEntity(entity);
            }
        }
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag flag, DataComponentGetter componentGetter) {
        tooltipAdder.accept(Component.translatable("block." + XtraDrinks.MOD_ID + ".tooltip.greenman_lure").withStyle(ChatFormatting.GREEN));
    }
}
