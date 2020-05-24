package typicals.alchemicalexpansion.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class ModBlockTileEntity extends ModBlock implements ITileEntityProvider {

    protected ModBlockTileEntity(Material materialIn) {
        super(materialIn);
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }
}
