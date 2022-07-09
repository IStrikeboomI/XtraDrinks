package Strikeboom.xtradrinks.network;

import Strikeboom.xtradrinks.guis.menus.LiquidDehydratorMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ServerboundDrainPacket{
    public ServerboundDrainPacket() {
    }
    public ServerboundDrainPacket(FriendlyByteBuf byteBuf) {
    }
    public void encode(FriendlyByteBuf byteBuf) {

    }
    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            AbstractContainerMenu menu = contextSupplier.get().getSender().containerMenu;
            if (menu instanceof LiquidDehydratorMenu liquidDehydratorMenu) {
                liquidDehydratorMenu.blockEntity.drain();
            }
        });
        contextSupplier.get().setPacketHandled(true);
    }
}
