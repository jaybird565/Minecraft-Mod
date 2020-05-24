package typicals.alchemicalexpansion.block;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import typicals.alchemicalexpansion.AlchemicalExpansion;

@ObjectHolder(AlchemicalExpansion.MODID)
public class ModBlocks {


    public static final PillFurnaceBlock PILL_FURNACE_BLOCK = null;


    public static void registerBlocks(IForgeRegistry<Block> registry) {
        registry.register(new PillFurnaceBlock());
    }


    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.register(PILL_FURNACE_BLOCK.itemBlock());
    }


}
