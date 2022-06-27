package Strikeboom.XtraDrinks.network;

import Strikeboom.XtraDrinks.guis.containers.LiquidDehydratorContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ServerboundDrainPacket{
    public ServerboundDrainPacket() {
    }
    public ServerboundDrainPacket(PacketBuffer byteBuf) {
    }
    public void encode(PacketBuffer byteBuf) {

    }
    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            Container menu = contextSupplier.get().getSender().containerMenu;
            if (menu instanceof LiquidDehydratorContainer liquidDehydratorContainer) {
                liquidDehydratorContainer.blockEntity.drain();
            }
        });
        contextSupplier.get().setPacketHandled(true);
    }
}
