package typicals.alchemicalexpansion.util.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.item.ItemStack;
import typicals.alchemicalexpansion.item.crafting.Machines.PillFurnaceRecipe;
import typicals.alchemicalexpansion.recipes.Recipes;
import typicals.alchemicalexpansion.util.LoggerUtil;


public class PillFurnaceParser{



    //reads the given contents of the JsonElement
    public static boolean parseJson(JsonElement json) {

        JsonArray jsonRecipes = null;
        try {
            jsonRecipes = json.getAsJsonArray();
        } catch (JsonParseException e) {
            LoggerUtil.error("PillFurnace recipes couldn't be parsed", e);
            return false;
        }

        if (jsonRecipes != null) {
            for (JsonElement jsonRecipe : json.getAsJsonArray()) {
                PillFurnaceRecipe recipe = new PillFurnaceRecipe();

                //Adds all items under the input tag to a array of inputs
                ItemStack[] inputs = RecipeParser.getItems("input", jsonRecipe);
                recipe.setInputs(inputs);

                ItemStack[] outputs = RecipeParser.getItems("output", jsonRecipe);
                recipe.setOutputs(outputs);

                Recipes.pillFurnaceRecipes.add(recipe);
            }
            return true;
        } else {
            return false;
        }

    }

}
