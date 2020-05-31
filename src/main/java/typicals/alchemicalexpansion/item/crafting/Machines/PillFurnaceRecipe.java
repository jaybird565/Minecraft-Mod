package typicals.alchemicalexpansion.item.crafting.Machines;


import net.minecraft.item.ItemStack;


public class PillFurnaceRecipe extends MachineRecipe {

    public ItemStack getOutput() {
        if(this.outputs.size() < 1) {
            return ItemStack.EMPTY;
        } else {
            return this.outputs.get(0);
        }
    }

}



