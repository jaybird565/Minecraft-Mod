package typicals.alchemicalexpansion.util.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.item.ItemStack;
import typicals.alchemicalexpansion.recipe.machine.PillFurnaceRecipe;
import typicals.alchemicalexpansion.recipe.Recipes;
import typicals.alchemicalexpansion.util.LoggerUtil;

import java.util.List;


public class PillFurnaceParser{



    //reads the given contents of the JsonElement
    public static boolean parseJson(JsonElement json) {

        JsonArray jsonRecipes = null;
        try {
            jsonRecipes = json.getAsJsonArray();
        } catch (JsonParseException e) {
            LoggerUtil.error("PillFurnace recipe couldn't be parsed", e);
            return false;
        }

        if (jsonRecipes != null) {
            for (JsonElement jsonRecipe : json.getAsJsonArray()) {
                PillFurnaceRecipe recipe = new PillFurnaceRecipe();


                //Adds all items under the input tag to a array of inputs
                List<ItemStack> inputs = RecipeParser.getItems("input", jsonRecipe);
                recipe.setInputs(inputs);

                List<ItemStack> outputs = RecipeParser.getItems("output", jsonRecipe);
                recipe.setOutputs(outputs);

                Recipes.pillFurnaceRecipes.add(recipe);
            }
            return true;
        } else {
            return false;
        }

    }

}
