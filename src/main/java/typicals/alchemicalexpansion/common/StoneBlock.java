package typicals.alchemicalexpansion.common;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class StoneBlock extends ModBlock {

    public static final String name = "stone_block";

    public static final Material material = Material.ROCK;

    public StoneBlock() {
        super(material);
        this.init();
    }

    private void init() {
        this.setBlockName(name);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setSoundType(SoundType.STONE);
    }

    public Item itemBlock() {
        return new ItemBlock(ModBlocks.STONE_BLOCK).setRegistryName(ModBlocks.STONE_BLOCK.getRegistryName());
    }

}
