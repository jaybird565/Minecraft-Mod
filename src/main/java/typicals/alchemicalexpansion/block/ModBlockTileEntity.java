package typicals.alchemicalexpansion.block;

import net.minecraft.block.ITileEntityProvider;

public abstract class ModBlockTileEntity extends ModBlock implements ITileEntityProvider {

    public ModBlockTileEntity(String path) {
        super(path);
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }
}
