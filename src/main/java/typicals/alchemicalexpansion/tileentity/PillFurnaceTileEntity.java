package typicals.alchemicalexpansion.tileentity;

import net.minecraft.block.Block;
import net.minecraft.util.ITickable;
import typicals.alchemicalexpansion.block.ModBlocks;

public class PillFurnaceTileEntity extends ItemHandlerTileEntity implements ITickable {

    public static final int SIZE = 11;

    public static final Block[] validBlocks = {ModBlocks.PILL_FURNACE_LIT, ModBlocks.PILL_FURNACE};

    private int counter = 0;

    private boolean isSmelting = false;

    public PillFurnaceTileEntity() {
        super();
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public Block[] validBlocks() {
        return validBlocks;
    }

    @Override
    public void update() {

        //don't update on client side
        if (world.isRemote) {
            return;
        }

        if (counter == 20) {

            //do something once a second

            counter = 0;
        } else {
            counter++;
        }

    }
}
