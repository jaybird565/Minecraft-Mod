package typicals.alchemicalexpansion.gui.container;

import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.Array;
import typicals.alchemicalexpansion.tileentity.PillFurnaceTile;

public class PillFurnaceContainer extends BaseContainer {

    public static final int slotWidth = 16;
    public static final int slotSpacing = 2;

    public static final int FUEL_SLOT =  9;

    public static final int[] REAGENT_SLOTS = Array.range(0, 9);

    public static final int RESULT_SLOT = 10;

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

    public PillFurnaceContainer(IInventory playerInv, IInventory furnaceInv) {
        this.inventory = furnaceInv;
        addSlots(playerInv);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.inventory.setField(id, data);
    }

    private void addSlots(IInventory playerInventory) {
        //get ItemHandler from tile entity
        int slotSize = slotWidth + slotSpacing;

        //add reagent slots
        for(int i: REAGENT_SLOTS) {
            addSlotToContainer(new Slot(this.inventory, i, (20 + (i % 3) * slotSize), (12 + (i / 3) * slotSize)));
        }

        //add fuel slot
        addSlotToContainer(new Slot(this.inventory, FUEL_SLOT, 38, 84));

        //add result slot
        addSlotToContainer(new Slot(this.inventory, RESULT_SLOT, 116, 30));


        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * slotSize, 111 + (i * slotSize)));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * slotSize, 169));
        }
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.inventory);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        PillFurnaceTile inventory = (PillFurnaceTile) this.inventory;

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.cookTime != inventory.getCookTime())
            {
                this.cookTime = inventory.getCookTime();
                icontainerlistener.sendWindowProperty(this, 2, this.cookTime);
            }

            if (this.totalCookTime != inventory.getTotalCookTime())
            {
                this.totalCookTime = inventory.getTotalCookTime();
                icontainerlistener.sendWindowProperty(this, 0, this.totalCookTime);
            }

            if (this.burnTime != inventory.getBurnTime())
            {
                this.burnTime = inventory.getBurnTime();
                icontainerlistener.sendWindowProperty(this, 1, this.burnTime);
            }

            if (this.totalBurnTime != inventory.getTotalBurnTime())
            {
                this.totalBurnTime = inventory.getTotalBurnTime();
                icontainerlistener.sendWindowProperty(this, 3, this.totalBurnTime);
            }
        }

    }

}
