package typicals.alchemicalexpansion.common.Blocks.Testing;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import typicals.alchemicalexpansion.common.ModBlock;
import typicals.alchemicalexpansion.common.ModBlocks;

public class PillFurnaceBlock extends ModBlock {
    public static final String name = "pill_furnace_block";

    public static final Material material = Material.ROCK;

    public PillFurnaceBlock() {
        super(material);
        this.init();
    }

    private void init() {
        this.setBlockName(name);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setSoundType(SoundType.STONE);
    }

    @Override
    public Item itemBlock() {
        return new ItemBlock(ModBlocks.PILL_FURNACE_BLOCK).setRegistryName(ModBlocks.PILL_FURNACE_BLOCK.getRegistryName());
    }
}
