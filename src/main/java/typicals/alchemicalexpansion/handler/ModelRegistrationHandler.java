package typicals.alchemicalexpansion.handler;


import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import typicals.alchemicalexpansion.block.ModBlocks;

public class ModelRegistrationHandler extends EventHandler {

    @SubscribeEvent
    public void onModelRegistry(ModelRegistryEvent event) {
        registerItemBlockModel(ModBlocks.PILL_FURNACE_BLOCK_OFF, 0);
        registerItemBlockModel(ModBlocks.PILL_FURNACE_BLOCK_ON, 0);
    }

    private static void registerItemBlockModel(Block block, int meta) {
        registerItemModel(Item.getItemFromBlock(block), meta);
    }

    private static void registerItemModel(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
