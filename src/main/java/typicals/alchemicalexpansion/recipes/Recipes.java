package typicals.alchemicalexpansion.recipes;


import net.minecraft.item.ItemStack;
import typicals.alchemicalexpansion.item.crafting.Machines.MachineRecipe;
import typicals.alchemicalexpansion.item.crafting.Machines.PillFurnaceRecipe;
import typicals.alchemicalexpansion.util.ItemUtil;
import typicals.alchemicalexpansion.util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

public class Recipes {

    public static List<PillFurnaceRecipe> pillFurnaceRecipes = new ArrayList<PillFurnaceRecipe>();

    public static PillFurnaceRecipe getRecipeFromInputs(List<ItemStack> inputs) {
        /*TODO make this check for count of items in slots not content of every slot
        Like 9 wheat should be valid even if all 9 are in top left slot of pill furnace.
        */
        LoggerUtil.dev("Recipe from inputs requested, finding recipe with inputs:\n" + ItemUtil.stackListToString(inputs));
        //Index pillFurnaceRecipes and compare all of its inputs to the supplied
        for (PillFurnaceRecipe possibleRecipe : pillFurnaceRecipes) {

            if(possibleRecipe.canCraft(inputs)) {
                return possibleRecipe;
            }
       }
        LoggerUtil.dev("Recipe not found with inputs:\n" + ItemUtil.stackListToString(inputs));
        return new PillFurnaceRecipe();
    }

    public static PillFurnaceRecipe getRecipeFromOutputs(List<ItemStack> outputs) {
        LoggerUtil.dev("Recipe from outputs requested, finding recipe with outputs:\n" + ItemUtil.stackListToString(outputs));
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
        LoggerUtil.dev("Recipe not found with outputs:\n" + ItemUtil.stackListToString(outputs));
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
