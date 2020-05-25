package typicals.alchemicalexpansion.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.block.ModBlocks;
import typicals.alchemicalexpansion.handler.GuiHandler;
import typicals.alchemicalexpansion.handler.PacketHandler;
import typicals.alchemicalexpansion.item.ModItems;
import typicals.alchemicalexpansion.tileentity.TileEntities;

@EventBusSubscriber
public class CommonProxy {

    public static Configuration config;

    public void preInit(FMLPreInitializationEvent event) {
        AlchemicalExpansion.logger.debug("alchemicalexpansion says hello from pre-init");
        PacketHandler.registerMessages(AlchemicalExpansion.MODID);
    }

    public void init(FMLInitializationEvent event) {
        AlchemicalExpansion.logger.debug("alchemicalexpansion says hello from init");
        NetworkRegistry.INSTANCE.registerGuiHandler(AlchemicalExpansion.instance, new GuiHandler());
    }

    public void postInit(FMLPostInitializationEvent event) {
        AlchemicalExpansion.logger.debug("alchemicalexpansion says hello from post-init");
    }

    @SubscribeEvent
    public static void onBlockRegistry(Register<Block> event) {
        ModBlocks.registerBlocks(event.getRegistry());
        TileEntities.registerTileEntities();
    }


    @SubscribeEvent
    public static void onItemRegistry(Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        ModBlocks.registerItemBlocks(registry);
        ModItems.registerItems(registry);
    }

}
