package typicals.alchemicalexpansion.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.common.ModBlocks;
import typicals.alchemicalexpansion.common.ModItems;
import typicals.alchemicalexpansion.common.PacketHandler;

@EventBusSubscriber
public class CommonProxy {

    public static Configuration config;

    public void preInit(FMLPreInitializationEvent event) {
        AlchemicalExpansion.logger.debug("alchemicalexpansion says hello from pre-init");
        PacketHandler.registerMessages(AlchemicalExpansion.MODID);
    }

    public void init(FMLInitializationEvent event) {
        AlchemicalExpansion.logger.debug("alchemicalexpansion says hello from init");
    }

    public void postInit(FMLPostInitializationEvent event) {
        AlchemicalExpansion.logger.debug("alchemicalexpansion says hello from post-init");
    }

    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        ModBlocks.registerBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        ModBlocks.registerItemBlocks(event.getRegistry());
        ModItems.registerItems(event.getRegistry());
    }

}
