package typicals.alchemicalexpansion.handler;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.block.ModBlock;
import typicals.alchemicalexpansion.item.ModFood;
import typicals.alchemicalexpansion.item.ModItem;
import typicals.alchemicalexpansion.item.Pill;
import typicals.alchemicalexpansion.util.RegistryUtil;

public class ItemRegistrationHandler extends EventHandler {

    private static boolean itemsRegistered = false;


    public static final ModItem[] registeredItems = {
    };

    public static final ModFood[] registeredFood = {
            new Pill()
    };

    @SubscribeEvent
    public void onItemRegistry(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        registerItemBlocks(registry);
        registerItems(registry);
        registerFood(registry);

        itemsRegistered = true;
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        for(ModBlock block: BlockRegistrationHandler.registeredBlocks) {
            if(block.hasItemBlock()) {
                registry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()));
            }
        }
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        for(ModItem item: registeredItems) {
            item.setRegistryName(RegistryUtil.resourceLocation(item))
                    .setUnlocalizedName(AlchemicalExpansion.MODID + "." + item.getPath());
            registry.register(item);
        }
    }


    public static void registerFood(IForgeRegistry<Item> registry) {
        for(ModFood item : registeredFood) {
            item.setRegistryName(RegistryUtil.resourceLocation(item))
                    .setUnlocalizedName(AlchemicalExpansion.MODID + "." + item.getPath());
            registry.register(item);
        }
    }

    public static boolean itemsRegistered() {
        return itemsRegistered;
    }
}
