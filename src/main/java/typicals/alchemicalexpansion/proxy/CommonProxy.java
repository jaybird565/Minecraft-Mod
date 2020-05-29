package typicals.alchemicalexpansion.proxy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.handler.BlockRegistrationHandler;
import typicals.alchemicalexpansion.handler.ItemRegistrationHandler;
import typicals.alchemicalexpansion.handler.PacketHandler;
import typicals.alchemicalexpansion.util.RegistryUtil;
import typicals.alchemicalexpansion.util.parser.ContentParser;


public class CommonProxy {

    public static Configuration config;

    public void preInit(FMLPreInitializationEvent event) {
        AlchemicalExpansion.logger.debug("alchemicalexpansion says hello from pre-init");
        //load config
        //TODO
        PacketHandler.registerMessages(AlchemicalExpansion.MODID);
        RegistryUtil.registerEventHandler(new BlockRegistrationHandler());
        RegistryUtil.registerEventHandler(new ItemRegistrationHandler());
    }

    public void init(FMLInitializationEvent event) {
        AlchemicalExpansion.logger.debug("alchemicalexpansion says hello from init");
        RegistryUtil.registerGuiHandler();
    }

    public void postInit(FMLPostInitializationEvent event) {
        AlchemicalExpansion.logger.debug("alchemicalexpansion says hello from post-init");
        ContentParser.parseContent(FMLCommonHandler.instance().findContainerFor(AlchemicalExpansion.MODID));
    }




}
