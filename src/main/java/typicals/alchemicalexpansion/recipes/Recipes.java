package typicals.alchemicalexpansion.recipes;


import net.minecraft.item.ItemStack;
import typicals.alchemicalexpansion.item.crafting.Machines.MachineRecipe;
import typicals.alchemicalexpansion.item.crafting.Machines.PillFurnaceRecipe;

import java.util.ArrayList;
import java.util.List;

public class Recipes {

    public static List<PillFurnaceRecipe> pillFurnaceRecipes = new ArrayList<PillFurnaceRecipe>();



    public static PillFurnaceRecipe getPillFurnaceRecipeFromInputs(List<ItemStack> inputs) {

        //making this more efficient would be a great improvement. for now though
        //just split up recipe Lists as much as possible to prevent input size from
        //increasing
        for (PillFurnaceRecipe possibleRecipe : pillFurnaceRecipes) {

            if(possibleRecipe.canCraft(inputs)) {
                return possibleRecipe;
            }
       }
        return new PillFurnaceRecipe();
    }

    public static PillFurnaceRecipe getPillFurnaceRecipeFromOutputs(List<ItemStack> outputs) {
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
        return new PillFurnaceRecipe();
    }


    public static String recipeListToString(String category) {
        String rv = "";
        switch(category) {
            case "pillfurnace":
                for(PillFurnaceRecipe recipe : pillFurnaceRecipes) {
                    rv += recipe.toString() + "\n";
                }
                break;
        }

        return rv;
    }

}
