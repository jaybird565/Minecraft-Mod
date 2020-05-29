package typicals.alchemicalexpansion.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import typicals.alchemicalexpansion.util.BlockUtil;

import java.util.Arrays;


public abstract class InventoryTile extends ModTile implements IInventory{

    protected ItemStack[] itemStacks;

    public static final int STACK_LIMIT = 64;

    @Override
    public Block[] validBlocks() {
        return new Block[0];
    }

    public InventoryTile(int stacks) {
        super();
        this.itemStacks = new ItemStack[stacks];
        clear();
    }

    public int size() {
        return this.getSizeInventory();
    }

    public abstract boolean isStorage(int slot, ItemStack stack);

    public ItemStack[] getStacksInSlots(int[] slots) {
        ItemStack[] stacks = new ItemStack[slots.length];

        int stackIndex = 0;
        for(int slot : slots) {
            stacks[stackIndex] = this.getStackInSlot(slot);
            stackIndex++;
        }
        return stacks;
    }


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        //TODO
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        //TODO
        return null;
    }


    @Override
    public int getSizeInventory() {
        return this.itemStacks.length;
    }

    @Override
    public boolean isEmpty() {

        for (ItemStack itemstack : itemStacks) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.itemStacks[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = this.getStackInSlot(index);
        if(stack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack itemStackRemoved;
        if (stack.getCount() <= count) {
            itemStackRemoved = stack;
            this.setInventorySlotContents(index, ItemStack.EMPTY);
        } else {
            itemStackRemoved = stack.splitStack(count);
            if (stack.getCount() == 0) {
                this.setInventorySlotContents(index, ItemStack.EMPTY);
            }
        }
        this.markDirty();
        return itemStackRemoved;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = this.getStackInSlot(index);
        ItemStack rv = ItemStack.EMPTY;
        if(!stack.isEmpty()) {
            rv = stack;
            this.setInventorySlotContents(index, ItemStack.EMPTY);
        }
        return rv;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.itemStacks[index] = stack;
        if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }
        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return STACK_LIMIT;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return BlockUtil.canInteractWith(player, this.pos);
    }

    @Override
    public void openInventory(EntityPlayer player) {
        //do nothing by default
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        //do nothing by default
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return isStorage(index, stack);
    }


    @Override
    public void clear() {
        Arrays.fill(itemStacks, ItemStack.EMPTY);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
