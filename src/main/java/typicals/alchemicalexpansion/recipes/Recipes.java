package typicals.alchemicalexpansion.recipes;


import net.minecraft.item.ItemStack;
import typicals.alchemicalexpansion.item.crafting.Machines.MachineRecipe;
import typicals.alchemicalexpansion.item.crafting.Machines.PillFurnaceRecipe;

import java.util.List;

public class Recipes {

    public static List<PillFurnaceRecipe> pillFurnaceRecipes;

    public static PillFurnaceRecipe getRecipeFromInputs(List<ItemStack> inputs) {
        int numberOfInputs;
        //Index pillFurnaceRecipes and compare all of its inputs to the supplied
        for (int i = 0; i < pillFurnaceRecipes.size(); i++) {
            numberOfInputs = inputs.size();
            List<ItemStack> pillFurnaceInputList = pillFurnaceRecipes.get(i).getInputs();
            if (pillFurnaceInputList.size() == inputs.size()) {
                for (int j = 0; j < pillFurnaceInputList.size(); j++) {
                    if (numberOfInputs == 0) {
                        return pillFurnaceRecipes.get(i);
                    } else {
                        for (int k = 0; k < inputs.size(); k++) {
                            if (inputs.get(k).isItemEqual(pillFurnaceInputList.get(j))) {
                                --numberOfInputs;
                                break;
                            }
                        }
                    }
                }
            }
        }
        //TODO return a new MachineRecipe instance with inputs and outputs unset if there are no recipes that match the inputs
        return null;
    }

    public static PillFurnaceRecipe getRecipeFromOutputs(List<ItemStack> outputs) {
        int numberOfOutputs;
        //Index pillFurnaceRecipes and compare all of its inputs to the supplied
        for (int i = 0; i < pillFurnaceRecipes.size(); i++) {
            numberOfOutputs = outputs.size();
            List<ItemStack> pillFurnaceOutputList = pillFurnaceRecipes.get(i).getOutputs();
            if (pillFurnaceOutputList.size() == outputs.size()) {
                for (int j = 0; j < pillFurnaceOutputList.size(); j++) {
                    if (numberOfOutputs == 0) {
                        return pillFurnaceRecipes.get(i);
                    } else {
                        for (int k = 0; k < outputs.size(); k++) {
                            if (outputs.get(k).isItemEqual(pillFurnaceOutputList.get(j))) {
                                --numberOfOutputs;
                                break;
                            }
                        }
                    }
                }
            }
        }
        //TODO return a new MachineRecipe instance with inputs and outputs unset if there are no recipes that match the inputs
        return null;
    }
}
