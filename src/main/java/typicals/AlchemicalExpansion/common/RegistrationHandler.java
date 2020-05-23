package typicals.AlchemicalExpansion.common;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;

import typicals.AlchemicalExpansion.AlchemicalExpansion;

@Mod.EventBusSubscriber(modid = AlchemicalExpansion.MODID)
public class RegistrationHandler {

    @SubscribeEvent
    public static void onBlockRegistry(Register<Block> event) {
        final Block[] blocks = {new StoneBlock()};
        event.getRegistry().registerAll(blocks);
    }

    @SubscribeEvent
    public static void onItemRegistry(Register<Item> event) {
        final Item[] itemBlocks = {ModBlocks.STONE_BLOCK.itemBlock()};
        event.getRegistry().registerAll(itemBlocks);


    }
}
