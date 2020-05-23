package typicals.alchemicalexpansion.common;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import typicals.alchemicalexpansion.AlchemicalExpansion;

@ObjectHolder(AlchemicalExpansion.MODID)
public class ModBlocks {

    public static final StoneBlock STONE_BLOCK = null;


    public static void registerBlocks(IForgeRegistry<Block> registry) {
        registry.register(new StoneBlock());
    }


    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.register(STONE_BLOCK.itemBlock());
    }

}
