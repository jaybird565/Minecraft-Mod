package typicals.AlchemicalExpansion.client;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import typicals.AlchemicalExpansion.AlchemicalExpansion;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid= AlchemicalExpansion.MODID)
public class ModelRegistrationHandler {

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        //register models
    }
}
