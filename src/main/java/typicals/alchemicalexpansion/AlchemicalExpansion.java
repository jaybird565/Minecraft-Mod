package typicals.alchemicalexpansion;

import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;
import typicals.alchemicalexpansion.command.ListRecipesCommand;
import typicals.alchemicalexpansion.handler.BlockRegistrationHandler;
import typicals.alchemicalexpansion.proxy.CommonProxy;


@Mod(modid = AlchemicalExpansion.MODID)
public class AlchemicalExpansion {

    public static final String MODID = "alchemicalexpansion";

    public static Logger logger;

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new ListRecipesCommand());
    }
    @Instance
    public static AlchemicalExpansion instance;

    @SidedProxy(clientSide = "typicals.alchemicalexpansion.proxy.ClientProxy", serverSide = "typicals.alchemicalexpansion.proxy.ServerProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }


}

