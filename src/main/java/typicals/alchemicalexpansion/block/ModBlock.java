package typicals.alchemicalexpansion.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public abstract class ModBlock extends Block {

    protected boolean hasItemBlock = true;

    //resource location path
    protected String path;

    protected ModBlock(String path) {
        this(path, Material.ROCK);
    }

    protected ModBlock(String path, Material material) {
        super(material);
        this.path = path;
    }

    protected ModBlock(String path, Material material, MapColor mapColor) {
        super(material, mapColor);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public boolean hasItemBlock() {
        return hasItemBlock;
    }

}
