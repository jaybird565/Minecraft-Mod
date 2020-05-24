package typicals.alchemicalexpansion.common.Blocks.Testing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class PillFurnace extends Container {

    public PillFurnaceTileEntity tileEntity;

    public PillFurnace(IInventory playerInventory, PillFurnaceTileEntity tileEntity) {
        this.tileEntity = tileEntity;

        addOwnSlots();
        addPlayerSlots(playerInventory);
    }

    private void addOwnSlots() {
        //get ItemHandler from tile entity
        IItemHandler itemHandler = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);



        //start 9 from left
        int x = 9;
        //start 6 from top
        int y = 6;
        //add reagent slots
        for(int i = 0; i < 9; i++) {
            addSlotToContainer(new SlotItemHandler(itemHandler, i, (x + (i % 3) * 18), (y + (i / 3) * 18)));
        }
        //add fuel slot
        //start 9 units from left + 36 (2 slots)
        x = 45;
        //start 6 units from top + 18 * 3 (3 slots) down + 6 spacing units
        y = 66;
        addSlotToContainer(new SlotItemHandler(itemHandler, 9, x, y));

        //add result slot

        //9 units from left, + 3 reagent slots + 1 spacer slot
        x = 81;
        //6 units from top + 1 slot
        y = 24;

        addSlotToContainer(new SlotItemHandler(itemHandler, 10, x, y));

    }

    private void addPlayerSlots(IInventory playerInventory) {
        //TODO
    }


    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < 11) {
                if (!this.mergeItemStack(itemstack1, 11, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 11, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return tileEntity.canInteractWith(playerIn);
    }


}
