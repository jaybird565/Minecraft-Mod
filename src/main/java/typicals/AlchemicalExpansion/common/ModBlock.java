package typicals.AlchemicalExpansion.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class ModBlock extends Block {

    protected ModBlock(Material materialIn) {
        super(materialIn);
    }

    protected ModBlock(Material materialIn, MapColor MapColorIn) {
        super(materialIn, MapColorIn);
    }

}
