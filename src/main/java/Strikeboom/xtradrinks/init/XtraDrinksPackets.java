package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.network.ServerboundDrainPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class XtraDrinksPackets {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(XtraDrinks.MOD_ID,"main"), () -> PROTOCOL_VERSION,PROTOCOL_VERSION::equals,PROTOCOL_VERSION::equals);

    public static void init() {
        INSTANCE.messageBuilder(ServerboundDrainPacket.class,0, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ServerboundDrainPacket::encode)
                .decoder(ServerboundDrainPacket::new)
                .consumerMainThread(ServerboundDrainPacket::handle)
                .add();
    }
}
