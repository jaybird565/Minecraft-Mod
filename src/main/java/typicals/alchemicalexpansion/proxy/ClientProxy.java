package typicals.alchemicalexpansion.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import typicals.alchemicalexpansion.handler.InputHandler;
import typicals.alchemicalexpansion.Keybindings;
import typicals.alchemicalexpansion.handler.ModelRegistrationHandler;
import typicals.alchemicalexpansion.util.LoggerUtil;
import typicals.alchemicalexpansion.util.RegistryUtil;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        LoggerUtil.dev("alchemicalexpansion says hello from preInit on the client!");
        super.preInit(event);
        RegistryUtil.registerEventHandler(new ModelRegistrationHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        LoggerUtil.dev("alchemicalexpansion says hello from init on the client!");
        super.init(event);
        RegistryUtil.registerEventHandler(new InputHandler());
        Keybindings.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        LoggerUtil.dev("alchemicalexpansion says hello from postInit on the client!");
        super.postInit(event);
    }
}
