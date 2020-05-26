package typicals.alchemicalexpansion.gui.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import typicals.alchemicalexpansion.tileentity.PillFurnaceTileEntity;

public class PillFurnace extends ModContainer {

    public static final int startX = 6;
    public static final int startY = 6;
    public static final int slotWidth = 16;
    public static final int slotSpacing = 2;
    public static final int inventorySpacing = 6;

    public PillFurnace(IInventory playerInventory, TileEntity tileEntity) {
        this.tileEntity = (PillFurnaceTileEntity) tileEntity;

        addSlots(playerInventory);
    }

    private void addSlots(IInventory playerInventory) {
        //get ItemHandler from tile entity
        IItemHandler itemHandler = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);


        int slotSize = slotWidth + slotSpacing;


        //add reagent slots
        for(int i = 0; i < 9; i++) {
            addSlotToContainer(new SlotItemHandler(itemHandler, i, (startX + (i % 3) * slotSize), (startY + (i / 3) * slotSize)));
        }

        //add fuel slot
        int y = startY + slotSize * 3 + slotSize;
        addSlotToContainer(new SlotItemHandler(itemHandler, 9, startX + slotSize, y));

        //add result slot

        //8 units from left, + 3 reagent slots + 1 spacer slot
        int x = startX + slotSize * 3 + slotSize;
        //8 units from top + 1 slot
        y = startY + slotSize;
        addSlotToContainer(new SlotItemHandler(itemHandler, 10, x, y));


        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, startX + j * slotSize, startY + (slotSize * 5) + inventorySpacing + (i * slotSize)));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, startX + k * slotSize, startY + (5 * slotSize) + inventorySpacing + (slotSize * 3) + inventorySpacing));
        }
    }






}
