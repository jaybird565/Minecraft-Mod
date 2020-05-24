package typicals.alchemicalexpansion.common.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityUtil {

    public static TileEntity getTileEntity(World world, int x, int y, int z) {
        return getTileEntity(world, new BlockPos(x, y, z));
    }

    public static TileEntity getTileEntity(World world, BlockPos pos) {
        return world.getTileEntity(pos);
    }

}
