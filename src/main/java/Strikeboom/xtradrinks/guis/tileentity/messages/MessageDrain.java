package Strikeboom.xtradrinks.guis.tileentity.messages;

import Strikeboom.xtradrinks.guis.container.ContainerLiquidDehydrator;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageDrain implements IMessage {
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }
    public static class Handler implements IMessageHandler<MessageDrain,IMessage> {

        @Override
        public IMessage onMessage(MessageDrain message, MessageContext ctx) {
            final EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServerWorld().addScheduledTask(() -> {
                if (player.openContainer instanceof ContainerLiquidDehydrator && player.openContainer.canInteractWith(player)) {
                    ((ContainerLiquidDehydrator) player.openContainer).drain();
                }
            });
            return null;
        }
    }
}
