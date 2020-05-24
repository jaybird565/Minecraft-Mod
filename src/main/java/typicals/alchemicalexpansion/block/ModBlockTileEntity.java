package typicals.alchemicalexpansion.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;

public abstract class ModBlockTileEntity extends ModBlock implements ITileEntityProvider {

    protected ModBlockTileEntity(Material materialIn) {
        super(materialIn);
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }
}
