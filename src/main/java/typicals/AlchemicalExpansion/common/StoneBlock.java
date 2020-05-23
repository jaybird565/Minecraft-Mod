package typicals.AlchemicalExpansion.common;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import typicals.AlchemicalExpansion.AlchemicalExpansion;

public class StoneBlock extends ModBlock {
    public static final String name = "mod_stone_block";

    public static final Material material = Material.ROCK;

    public StoneBlock() {
        super(material);
        this.init();
    }

    private void init() {
        this.setRegistryName(AlchemicalExpansion.MODID, name);
        this.setUnlocalizedName(AlchemicalExpansion.MODID + "." + name);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setSoundType(SoundType.STONE);
    }

    public Item itemBlock() {
        return new ItemBlock(ModBlocks.STONE_BLOCK).setRegistryName(ModBlocks.STONE_BLOCK.getRegistryName());
    }
}
