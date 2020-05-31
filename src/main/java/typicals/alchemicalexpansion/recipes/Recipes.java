package typicals.alchemicalexpansion.recipes;


import net.minecraft.item.ItemStack;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.item.crafting.Machines.MachineRecipe;
import typicals.alchemicalexpansion.item.crafting.Machines.PillFurnaceRecipe;
import typicals.alchemicalexpansion.util.ItemUtil;

import java.util.ArrayList;
import java.util.List;

public class Recipes {

    public static List<PillFurnaceRecipe> pillFurnaceRecipes = new ArrayList<PillFurnaceRecipe>();

    public static PillFurnaceRecipe getRecipeFromInputs(List<ItemStack> inputs) {
        AlchemicalExpansion.logger.debug("Recipe from inputs requested finding recipe with inputs: " + ItemUtil.stackListToString(inputs));
        int numberOfInputs = inputs.size();
        //Index pillFurnaceRecipes and compare all of its inputs to the supplied
        for (int i = 0; i < pillFurnaceRecipes.size(); i++) {
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
        AlchemicalExpansion.logger.debug("Recipe not found...");
        //TODO return a new MachineRecipe instance with inputs and outputs unset if there are no recipes that match the inputs
        return new PillFurnaceRecipe();
    }

    public static PillFurnaceRecipe getRecipeFromOutputs(List<ItemStack> outputs) {
        AlchemicalExpansion.logger.debug("Recipe from outputs requested finding recipe with outputs: " + ItemUtil.stackListToString(outputs));
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
        AlchemicalExpansion.logger.debug("Recipe not found...");
        //TODO return a new MachineRecipe instance with inputs and outputs unset if there are no recipes that match the inputs
        return new PillFurnaceRecipe();
    }


}
