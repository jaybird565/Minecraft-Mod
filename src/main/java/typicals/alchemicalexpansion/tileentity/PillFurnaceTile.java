package typicals.alchemicalexpansion.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IInteractionObject;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.block.ModBlocks;
import typicals.alchemicalexpansion.block.PillFurnaceBlock;
import typicals.alchemicalexpansion.gui.container.PillFurnaceContainer;
import typicals.alchemicalexpansion.item.ModItems;


public class PillFurnaceTile extends InventoryTile implements ITickable, IInteractionObject {

    public static final int SIZE = 11;

    public static final Block[] validBlocks = {ModBlocks.PILL_FURNACE_LIT, ModBlocks.PILL_FURNACE};

    private int burnTime = 0;

    public int getBurnTime() {
        return burnTime;
    }

    private int totalBurnTime = 0;

    public int getTotalBurnTime() {
        return totalBurnTime;
    }

    private int cookTime = 0;

    public int getCookTime() {
        return cookTime;
    }
    private int totalCookTime = 200; //200 ticks to smelt something by default

    public int getTotalCookTime() {
        return totalCookTime;
    }

    public float percentCooked() {
        return (float) MathHelper.clamp((((float) cookTime) / ((float) totalCookTime)), 0.0, 1.0);
    }

    public float percentBurned() {
        return (float) MathHelper.clamp(((float) burnTime) / ((float) totalBurnTime),0.0,1.0);
    }

    public PillFurnaceTile() {
        super(SIZE);
    }

    @Override
    public Block[] validBlocks() {
        return validBlocks;
    }

    @Override
    public boolean isStorage(int slot, ItemStack stack) {
        if((slot >= 0 ) && (slot < 9)) {
            return true;
        }else if (slot == 9) {
            return true;
        } else if (slot == 10) {
            return false;
        } else {
            return false;
        }
    }


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        this.readCookTime(compound)
                .readTotalCookTime(compound)
                .readBurnTime(compound)
                .readTotalBurnTime(compound);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound data = super.writeToNBT(compound);

        this.writeBurnTime(data)
                .writeTotalBurnTime(data)
                .writeCookTime(data)
                .writeTotalCookTime(data);

        return data;

    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        super.handleUpdateTag(tag);
        this.readBurnTime(tag).readCookTime(tag).readTotalBurnTime(tag).readTotalCookTime(tag);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound compound = super.getUpdateTag();
        this.writeBurnTime(compound)
                .writeCookTime(compound)
                .writeTotalBurnTime(compound)
                .writeTotalCookTime(compound);
        return compound;
    }

    @Override
    public void update() {

        //shamelessly stolen from TileEntityFurnace
        boolean isBurning = this.isBurning();
        boolean markDirty = false;

        if (this.isBurning())
        {
            --this.burnTime;
        }

        if (!this.world.isRemote)
        {
            ItemStack fuelStack = this.getStackInSlot(PillFurnaceContainer.FUEL_SLOT);

            if (this.isBurning() || !fuelStack.isEmpty() )
            {
                if (!this.isBurning() && this.canCook())
                {
                    this.burnTime = this.getBurnTime(fuelStack.getItem());

                    if (this.isBurning())
                    {
                        markDirty = true;

                        if (!fuelStack.isEmpty())
                        {
                            Item item = fuelStack.getItem();
                            fuelStack.shrink(1);

                            if (fuelStack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(fuelStack);
                                this.setInventorySlotContents(PillFurnaceContainer.FUEL_SLOT, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canCook())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalBurnTime = this.getBurnTime(fuelStack.getItem());
                        this.cookItem();
                        markDirty = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = 0;
                //idk what this was so I commented it out
                //this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

        }

        if (isBurning != this.isBurning())
        {
            markDirty = true;
            this.updateBlockState();
        }

        if (markDirty)
        {
            this.markDirty();
        }


    }

    public void cookItem(){

        if (this.canCook()) {
            //TODO change pill stats/ result based on ingredients
            this.setInventorySlotContents(PillFurnaceContainer.RESULT_SLOT, new ItemStack(ModItems.PILL));
        }
    }

    public int getBurnTime(Item fuel) {
        //TODO get item burn time
        return 200;
    }


    public boolean canCook() {
        //TODO check if valid recipe
        return true;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public boolean isCooking() {
        return this.cookTime > 0;
    }

    public void updateBlockState() {
        if(this.getContainingBlock() instanceof PillFurnaceBlock) {
            PillFurnaceBlock block = (PillFurnaceBlock) this.getContainingBlock();
            block.updatePillFurnaceState(this.isBurning(), this.world, this.getPos());
        }
    }

    @Override
    public int getField(int id) {
        switch(id) {
            case 0: return burnTime;
            case 1: return totalBurnTime;
            case 2: return cookTime;
            case 3: return totalCookTime;
            default: return -1;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch(id) {
            case 0: burnTime = value;
            case 1: totalBurnTime = value;
            case 2: cookTime = value;
            case 3: totalCookTime = value;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new PillFurnaceContainer(playerInventory, this);
    }

    public PillFurnaceTile writeBurnTime(NBTTagCompound compound) {
        compound.setInteger("burnTime", this.getBurnTime());
        return this;
    }

    public PillFurnaceTile readBurnTime(NBTTagCompound compound) {
        this.burnTime = compound.getInteger("burnTime");
        return this;
    }

    public PillFurnaceTile writeCookTime(NBTTagCompound compound) {
        compound.setInteger("cookTime", this.getCookTime());
        return this;
    }

    public PillFurnaceTile readCookTime(NBTTagCompound compound) {
        this.cookTime = compound.getInteger("cookTime");
        return this;
    }

    public PillFurnaceTile writeTotalBurnTime(NBTTagCompound compound) {
        compound.setInteger("totalBurnTime", this.getTotalBurnTime());
        return this;
    }
    public PillFurnaceTile readTotalBurnTime(NBTTagCompound compound) {
        this.totalBurnTime = compound.getInteger("totalBurnTime");
        return this;
    }

    public PillFurnaceTile writeTotalCookTime(NBTTagCompound compound) {
        compound.setInteger("totalCookTime", this.getTotalCookTime());
        return this;
    }

    public PillFurnaceTile readTotalCookTime(NBTTagCompound compound) {
        this.totalCookTime = compound.getInteger("totalCookTime");
        return this;
    }

    @Override
    public String getGuiID() {
        return AlchemicalExpansion.MODID + ":" + PillFurnaceBlock.pathOff;
    }
}
