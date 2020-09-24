package Strikeboom.xtradrinks.handlers;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.tileentity.messages.MessageDrain;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(XtraDrinks.MOD_ID);
    public static void init() {
        NETWORK.registerMessage(MessageDrain.Handler.class,MessageDrain.class,1, Side.SERVER);
    }
}
