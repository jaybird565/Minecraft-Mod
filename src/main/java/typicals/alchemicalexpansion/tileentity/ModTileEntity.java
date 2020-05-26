package typicals.alchemicalexpansion.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;
import typicals.alchemicalexpansion.util.BlockUtil;


public abstract class ModTileEntity extends TileEntity {

    public abstract Block[] validBlocks();

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && BlockUtil.canInteractWith(playerIn, this.getPos());
    }

}
