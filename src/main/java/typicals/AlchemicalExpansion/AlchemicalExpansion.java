package typicals.AlchemicalExpansion;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid=AlchemicalExpansion.MODID)
public class AlchemicalExpansion {

    public static final String MODID = "alchemicalexpansion";
    public static Logger logger;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.debug("AlchemicalExpansion says hello from pre-init");
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        logger.debug("AlchemicalExpansion says hello from init");

    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        logger.debug("AlchemicalExpansion says hello from post-init");
    }



}

