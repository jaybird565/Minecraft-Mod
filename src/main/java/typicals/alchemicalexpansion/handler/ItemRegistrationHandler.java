package typicals.alchemicalexpansion.handler;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import typicals.alchemicalexpansion.block.ModBlock;
import typicals.alchemicalexpansion.item.ModItem;

public class ItemRegistrationHandler extends EventHandler {

    private static boolean itemsRegistered = false;


    public static final ModItem[] registeredItems = {

    };

    @SubscribeEvent
    public void onItemRegistry(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        registerItemBlocks(registry);
        registerItems(registry);

        itemsRegistered = true;
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        for(ModBlock block: BlockRegistrationHandler.registeredBlocks) {
            if(block.hasItemBlock()) {
                registry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
            }
        }
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        for(ModItem item: registeredItems) {
            registry.register(item);
        }
    }

    public static boolean itemsRegistered() {
        return itemsRegistered;
    }
}
