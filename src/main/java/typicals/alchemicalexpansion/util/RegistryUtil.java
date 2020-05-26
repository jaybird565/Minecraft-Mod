package typicals.alchemicalexpansion.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.block.ModBlock;
import typicals.alchemicalexpansion.handler.BlockRegistrationHandler;
import typicals.alchemicalexpansion.handler.EventHandler;
import typicals.alchemicalexpansion.handler.GuiHandler;
import typicals.alchemicalexpansion.handler.ItemRegistrationHandler;
import typicals.alchemicalexpansion.item.ModFood;
import typicals.alchemicalexpansion.item.ModItem;

public class RegistryUtil {

    public static final String nameSpace = AlchemicalExpansion.MODID;

    public static final GuiHandler guiHandler = new GuiHandler();

    public static boolean blocksRegistered() {
        return BlockRegistrationHandler.blocksRegistered();
    }

    public static boolean itemsRegistered() {
        return ItemRegistrationHandler.itemsRegistered();
    }

    public static ResourceLocation resourceLocation(ModBlock block) {
        return resourceLocation(block.getPath());
    }

    public static ResourceLocation resourceLocation(ModItem item) {
        return resourceLocation(item.getPath());
    }
    public static ResourceLocation resourceLocation(ModFood item) {
        return resourceLocation(item.getPath());
    }

    public static ResourceLocation resourceLocation(String path) {
        return new ResourceLocation(nameSpace, path);
    }

    public static void registerEventHandler(EventHandler handler) {
        MinecraftForge.EVENT_BUS.register(handler);
    }

    public static void registerGuiHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(AlchemicalExpansion.instance, guiHandler);
    }
}
