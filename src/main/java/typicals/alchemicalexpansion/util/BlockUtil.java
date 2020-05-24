package typicals.alchemicalexpansion.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class BlockUtil {

    public static boolean canInteractWith(EntityPlayer player, BlockPos pos) {
        return player.getDistanceSq(pos) <= 64D;
    }
}
