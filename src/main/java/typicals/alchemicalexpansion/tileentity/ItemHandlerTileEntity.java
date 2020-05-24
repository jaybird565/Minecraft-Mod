package typicals.alchemicalexpansion.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemStackHandler;


public abstract class ItemHandlerTileEntity extends ModTileEntity {

    public abstract int size();

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
