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

    @Override
    public String toString() {
        String rv = "inputs:\n";
        for(ItemStack input : this.inputs) {
            rv += input.toString();
        }

        for(ItemStack output : this.outputs) {
            rv += output.toString();
        }

        return rv;

    }


}



