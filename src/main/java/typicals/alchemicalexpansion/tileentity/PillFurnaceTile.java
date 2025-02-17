package typicals.alchemicalexpansion.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IInteractionObject;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.block.ModBlocks;
import typicals.alchemicalexpansion.block.PillFurnaceBlock;
import typicals.alchemicalexpansion.gui.container.PillFurnaceContainer;
import typicals.alchemicalexpansion.recipe.machine.PillFurnaceRecipe;
import typicals.alchemicalexpansion.recipe.Recipes;
import typicals.alchemicalexpansion.util.ItemUtil;
import typicals.alchemicalexpansion.util.LoggerUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PillFurnaceTile extends InventoryTile implements ITickable, IInteractionObject {

    public static final int SIZE = 11;
    private int burnTime = 0;
    private int totalBurnTime = 0;
    private int cookTime = 0;
    private int totalCookTime = 200; //200 ticks to smelt something by default

    private PillFurnaceRecipe currentRecipe = null;

    public static final Block[] validBlocks = {ModBlocks.PILL_FURNACE_LIT, ModBlocks.PILL_FURNACE};

    public PillFurnaceTile() {
        super(SIZE);
    }


    public ItemStack getFuelStack() {
        return this.getStackInSlot(PillFurnaceContainer.FUEL_SLOT);
    }

    public ItemStack getResultStack() {
        return this.getStackInSlot(PillFurnaceContainer.RESULT_SLOT);
    }

    public boolean hasFuel() {
        return ItemUtil.isFuel(this.getFuelStack());
    }

    protected void burnFuel() {
        ItemStack fuelStack = this.getFuelStack();
        if(!fuelStack.isEmpty()) {
            int burnTime = ItemUtil.getBurnTime(fuelStack);
            fuelStack.shrink(1);
            this.burnTime += burnTime;
            this.totalBurnTime = burnTime;
        }
    }

    public List<ItemStack> getReagents() {
        List<ItemStack> reagents = new ArrayList<ItemStack>();
        for(int i : PillFurnaceContainer.REAGENT_SLOTS) {
            ItemStack stack = this.getStackInSlot(i);
            if(!stack.isEmpty()) {
                reagents.add(stack);
            }
        }
        return reagents;
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

    //onTick logic
    @Override
    public void update() {

        boolean wasBurning = this.isBurning();
        boolean markDirty = false;

        if(wasBurning) {
            this.burnTime--;
        }

        if(!this.onClient()) {

            boolean canCook = false;

            if(currentRecipe == null) {
                this.currentRecipe = (PillFurnaceRecipe) Recipes.getPillFurnaceRecipeFromInputs(this.getReagents());
            }

            //determine if we can cook the reagents
            if((this.currentRecipe != null) &&
                    (!ItemUtil.isEmpty(this.getReagents())) &&
                    (this.currentRecipe.isValid()) &&
                    (this.getResultStack().isItemEqual(this.currentRecipe.getOutput()) || this.getResultStack().isEmpty() || this.getResultStack().getItem().equals(Items.AIR)) &&
                    (this.getResultStack().getCount() + this.currentRecipe.getOutput().getCount() <= this.getResultStack().getMaxStackSize()) &&
                    (this.currentRecipe.canCraft(this))) {
                    canCook = true;

            } else {
                this.currentRecipe = null;
            }

            //add more fuel if has fuel and out of burnTime
            if(canCook && !this.isBurning() && this.hasFuel()) {
                this.burnFuel();
                markDirty = true;
            }

            if(canCook && this.isBurning()) {
                this.cookTime++;
                if(this.cookTime >= this.totalCookTime) {
                    LoggerUtil.dev("Crafting of PillFurnaceRecipe:\n" + this.currentRecipe.toString() + "with reagents:\n" + ItemUtil.stackListToString(this.getReagents()) + " started.");
                    //add recipe result to result slot
                    ItemStack resultStack = this.getResultStack();
                    ItemStack outputStack = this.currentRecipe.getOutput();

                    if(resultStack.isEmpty() || resultStack.getItem().equals(Items.AIR)) {
                        this.setInventorySlotContents(PillFurnaceContainer.RESULT_SLOT, outputStack);
                    } else if(resultStack.isItemEqual(outputStack)){
                        int outputStackCount = outputStack.getCount();
                        this.getResultStack().grow(outputStackCount);
                    }

                    //remove items from reagent/input slots
                    List<ItemStack> reagentStacks = Arrays.asList(this.getStacksInSlots(PillFurnaceContainer.REAGENT_SLOTS));
                    for(ItemStack input : this.currentRecipe.getInputs() ) {
                        if(!ItemUtil.decreaseItemStack(reagentStacks, input)) {
                            LoggerUtil.error("The recipe in PillFurnaceTile: " + this.toString() + "changed while crafting, this shouldn't happen...");
                        }
                    }


                    this.cookTime = 0;
                    markDirty = true;
                }
            } else {
                this.cookTime = 0;
            }

            //update block state if changed
            if(wasBurning != this.isBurning()) {
                this.updateBlockState();
                markDirty = true;
            }
        }

        //mark dirty if needed
        if(markDirty) {
            this.markDirty();
        }

    }

    //getters
    public int getBurnTime() {
        return burnTime;
    }

    public int getTotalBurnTime() {
        return totalBurnTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public int getTotalCookTime() {
        return totalCookTime;
    }

    //getter util
    public float percentCooked() {
        return (float) MathHelper.clamp((((float) cookTime) / ((float) totalCookTime)), 0.0, 1.0);
    }

    public float percentBurned() {
        return (float) MathHelper.clamp(((float) burnTime) / ((float) totalBurnTime),0.0,1.0);
    }

    //impl of IInventory
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if((index >= 0 ) && (index < 9)) {
            return true;
        }else if (index == 9) {
            return true;
        } else if (index == 10) {
            return false;
        } else {
            return false;
        }
    }

    @Override
    public Block[] validBlocks() {

        return validBlocks;

    }

    //impl of IInteractionObject
    @Override
    public String getGuiID() {
        return AlchemicalExpansion.MODID + ":" + PillFurnaceBlock.pathOff + "_tile_entity";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new PillFurnaceContainer(playerInventory, this);
    }

    //nbttag and field stuff for client server syncing
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
        this.readFromNBT(tag);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound compound = super.getUpdateTag();
        return this.writeToNBT(compound);
    }
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tag = getUpdateTag();
        final int METADATA = 0;
        return new SPacketUpdateTileEntity(this.pos, METADATA, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound updateTagDescribingTileEntityState = pkt.getNbtCompound();
        this.handleUpdateTag(updateTagDescribingTileEntityState);
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
            case 0: burnTime = value; break;
            case 1: totalBurnTime = value; break;
            case 2: cookTime = value; break;
            case 3: totalCookTime = value; break;
        }
    }

        @Override
    public int getFieldCount() {
        return 4;
    }

    //nbt getter and setter util
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

}
