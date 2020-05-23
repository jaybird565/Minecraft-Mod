package typicals.alchemicalexpansion.common;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.proxy.CommonProxy;

public class Config {

    private static final String CONFIG_NAME = "alchemicalexpansion";

    public static boolean isLoaded = false;

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initConfig(cfg);
        } catch (Exception e) {
            AlchemicalExpansion.logger.log(Level.ERROR, "There was an error loading the config file.", e);
        } finally {
            if(cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    public static void initConfig(Configuration cfg) {
        isLoaded = true;
        //get data from config and set public members to those values
    }

}
