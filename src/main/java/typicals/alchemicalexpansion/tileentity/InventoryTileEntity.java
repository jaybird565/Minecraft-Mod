package typicals.alchemicalexpansion.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;


public abstract class InventoryTileEntity extends ModTileEntity {

    @CapabilityInject(IItemHandler.class)
    public static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

    public abstract int size();


    public InventoryTileEntity() {
        super();
    }



    public abstract boolean isStorage(int slot, ItemStack stack);

    protected ItemStackHandler itemStackHandler = new ItemStackHandler(size()) {
        @Override
        protected void onContentsChanged(int slot) {
            //mark the InventoryTileEntity instance dirty to keep contents persistent
            InventoryTileEntity.this.markDirty();
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if(InventoryTileEntity.this.isStorage(slot, stack)) {
                return super.insertItem(slot, stack, simulate);
            } else {
                return stack;
            }
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
