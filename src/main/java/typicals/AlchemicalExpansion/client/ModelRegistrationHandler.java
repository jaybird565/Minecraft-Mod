package typicals.AlchemicalExpansion.client;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import typicals.AlchemicalExpansion.AlchemicalExpansion;
import typicals.AlchemicalExpansion.common.ModBlocks;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid= AlchemicalExpansion.MODID)
public class ModelRegistrationHandler {

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        registerItemModel(Item.getItemFromBlock(ModBlocks.STONE_BLOCK), 0);
    }

    private static void registerItemModel(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
