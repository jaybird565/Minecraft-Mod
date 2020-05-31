package typicals.alchemicalexpansion.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class BlockUtil {

    public static final double MAX_INTERACTION_DISTANCE = 8D;

    public static boolean canInteractWith(EntityPlayer player, BlockPos pos) {
        return player.getDistanceSq(pos) <= MAX_INTERACTION_DISTANCE * MAX_INTERACTION_DISTANCE;
    }
}
