package typicals.alchemicalexpansion.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import typicals.alchemicalexpansion.util.BlockUtil;


public abstract class ItemHandlerTileEntity extends TileEntity {

    @CapabilityInject(IItemHandler.class)
    public static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

    public abstract int size();

    public abstract Block[] validBlocks();

    protected ItemStackHandler itemStackHandler = new ItemStackHandler(size()) {
        @Override
        protected void onContentsChanged(int slot) {
            //mark the InventoryTileEntity instance dirty to keep contents persistent
            ItemHandlerTileEntity.this.markDirty();
        }
    };

    //set items of ItemStackHandler to the items in the passed NBT
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
    }

    //get the items in the ItemStackHandler in NBT form
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) { super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        return compound;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && BlockUtil.canInteractWith(playerIn, this.getPos());
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == ITEM_HANDLER_CAPABILITY) {
            return ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
        }
        return super.getCapability(capability, facing);
    }

}
