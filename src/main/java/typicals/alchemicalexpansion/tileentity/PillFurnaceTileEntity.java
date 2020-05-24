package typicals.alchemicalexpansion.tileentity;

import net.minecraft.block.Block;
import typicals.alchemicalexpansion.block.ModBlocks;

public class PillFurnaceTileEntity extends ItemHandlerTileEntity {

    public static final int SIZE = 11;

    public static final Block[] validBlocks = {ModBlocks.PILL_FURNACE_BLOCK};

    public static final String name = "pill_furnace";

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public Block[] validBlocks() {
        return validBlocks;
    }

}
