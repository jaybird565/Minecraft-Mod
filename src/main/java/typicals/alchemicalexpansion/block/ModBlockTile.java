package typicals.alchemicalexpansion.block;

import net.minecraft.block.ITileEntityProvider;

public abstract class ModBlockTile extends ModBlock implements ITileEntityProvider {

    public ModBlockTile(String path) {
        super(path);
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }

}
