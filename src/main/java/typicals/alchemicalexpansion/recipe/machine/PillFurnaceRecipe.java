package typicals.alchemicalexpansion.recipe.machine;


import net.minecraft.item.ItemStack;
import typicals.alchemicalexpansion.tileentity.PillFurnaceTile;


public class PillFurnaceRecipe extends MachineRecipe {

    public ItemStack getOutput() {
        if(this.outputs.size() < 1) {
            return ItemStack.EMPTY;
        } else {
            return this.outputs.get(0);
        }
    }

    public boolean canCraft(PillFurnaceTile tile) {
        return super.canCraft(tile.getReagents());
    }



}



