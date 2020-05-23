package typicals.AlchemicalExpansion.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public abstract class ModBlock extends Block {

    protected ModBlock(Material materialIn) {
        super(materialIn);
    }

    protected ModBlock(Material materialIn, MapColor MapColorIn) {
        super(materialIn, MapColorIn);
    }

    public abstract Item itemBlock();

}
