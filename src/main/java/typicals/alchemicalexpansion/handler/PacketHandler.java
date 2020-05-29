package typicals.alchemicalexpansion.handler;


import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import typicals.alchemicalexpansion.network.ModMessage;

public class PacketHandler implements IMessageHandler {
    private static int lastPacketId = 0;

    public static SimpleNetworkWrapper INSTANCE = null;

    public PacketHandler() {

    }

    public static int nextPacketId() {
        return ++lastPacketId;
    }

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        registerMessages();
    }

    public static void registerMessages() {
        //register messages sent to server from client
        //INSTANCE.registerMessage();

    }

    //send message to appropriate handler and optionally return a reply
    @Override
    public IMessage onMessage(IMessage message, MessageContext ctx) {
        if (message instanceof ModMessage) {

        }

        return null;
    }
}
