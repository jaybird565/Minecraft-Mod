package typicals.alchemicalexpansion.gui.container;

import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.Array;
import typicals.alchemicalexpansion.AlchemicalExpansion;
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

    private int[] fields;

    public PillFurnaceContainer(IInventory playerInv, IInventory furnaceInv) {
        //AlchemicalExpansion.logger.debug("PillFurnaceContainer creation started");
        this.inventory = furnaceInv;
        addSlots(playerInv);
        this.fields = new int[inventory.getFieldCount()];
        this.updateFields();
        //AlchemicalExpansion.logger.debug("PillFurnaceContainer creation finished");
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
        //AlchemicalExpansion.logger.debug("Listener: " + listener.toString() + " added.");
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();


        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);

            for(int field = 0; field < inventory.getFieldCount(); field++) {
                if(this.fields[field] != inventory.getField(field)) {
                    //AlchemicalExpansion.logger.debug("Pill furnace field " + Integer.toString(field) + " updated from: " + Integer.toString(this.fields[field]) + " to: " + Integer.toString(inventory.getField(field)));
                    icontainerlistener.sendWindowProperty(this, field, inventory.getField(field));
                    this.fields[field] = inventory.getField(field);
                }
            }

        }

    }

    private void updateFields() {
        for(int field = 0; field < inventory.getFieldCount(); field++) {
            if(this.fields[field] != inventory.getField(field)) {
                //AlchemicalExpansion.logger.debug("Pill furnace field " + Integer.toString(field) + " updated from: " + Integer.toString(this.fields[field]) + " to: " + Integer.toString(inventory.getField(field)));
                this.fields[field] = inventory.getField(field);
            }
        }
    }

}
