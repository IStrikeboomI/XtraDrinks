package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.network.ServerboundDrainPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class XtraDrinksPackets {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(XtraDrinks.MOD_ID,"main"), () -> PROTOCOL_VERSION,PROTOCOL_VERSION::equals,PROTOCOL_VERSION::equals);

    public static void init() {
        INSTANCE.messageBuilder(ServerboundDrainPacket.class,0, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ServerboundDrainPacket::encode)
                .decoder(ServerboundDrainPacket::new)
                .consumer(ServerboundDrainPacket::handle)
                .add();
    }
}
