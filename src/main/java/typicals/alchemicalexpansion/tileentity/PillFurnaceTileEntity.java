package typicals.alchemicalexpansion.tileentity;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import typicals.alchemicalexpansion.block.ModBlocks;
import typicals.alchemicalexpansion.gui.container.PillFurnace;
import typicals.alchemicalexpansion.item.ModItems;

public class PillFurnaceTileEntity extends InventoryTileEntity implements ITickable {

    public static final int SIZE = 11;

    public static final Block[] validBlocks = {ModBlocks.PILL_FURNACE_LIT, ModBlocks.PILL_FURNACE};

    private int burnTime = 0;

    private int cookTime = 0;

    private int tickCounter = 0;

    private int cookDuration = 200; //200 ticks to smelt something by default

    private boolean isSmelting = false;

    public PillFurnaceTileEntity() {
        super();
    }

    @Override
    public int size() {
        return SIZE;
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
    public void update() {

        //don't update on client side
        if (world.isRemote) {
            return;
        }

        //update result slot
        if (cookTime >= cookDuration) {
            cookTime = 0;
            ItemStack resultStack = this.getStackInSlot(PillFurnace.RESULT_SLOT);
            if (resultStack.isEmpty()) {
                this.itemStackHandler.setStackInSlot(1, new ItemStack(ModItems.PILL));
            }
        }
        //update cookTime
        if(this.isSmelting && hasValidReagents()) {
                this.cookTime++;
        } else {
            this.cookTime = 0;
        }

        //update burnTime
        ItemStack fuelStack = this.getStackInSlot(PillFurnace.FUEL_SLOT);
        if (--burnTime <= 0) {
            //burn fuel if cooking
            if (isFuel(fuelStack) && cookTime > 0) {
                this.burnTime += fuelStack.getItem().getItemBurnTime(fuelStack);
                fuelStack.shrink(1);
            } else {
                this.burnTime = 0;
            }
        }
        //update isSmelting
        if(burnTime > 0) {
            this.isSmelting = true;
        }

    }

    public static boolean isFuel(ItemStack stack) {
        return stack.getItem().getItemBurnTime(stack) > 0;
    }

    public boolean hasValidReagents() {
        //TODO check if reagents are a recipe in typicals.alchemicalexpansion.recipes.Recipes
        for(ItemStack reagent : this.getStacksInSlots(PillFurnace.REAGENT_SLOTS)) {
            if(!reagent.isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
