package typicals.alchemicalexpansion.util.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import typicals.alchemicalexpansion.item.crafting.Machines.PillFurnaceRecipe;
import java.util.Map;

import static typicals.alchemicalexpansion.recipes.Recipes.pillFurnaceRecipes;

public class PillFurnaceParser extends RecipeParser{

    //reads the given contents of the JsonElement
    public static boolean parseJson(JsonElement json){
        //TODO turn this into a try catch
        for(JsonElement jsonRecipe : json.getAsJsonArray()){
            PillFurnaceRecipe recipe = new PillFurnaceRecipe();

            //Adds all items under the input tag to a array of inputs
            ItemStack[] inputs = RecipeParser.getItems("input", jsonRecipe);
            recipe.setInputs(inputs);

            ItemStack[] outputs = RecipeParser.getItems("output", jsonRecipe);
            recipe.setOutputs(outputs);

            pillFurnaceRecipes.add(recipe);

        }
        return true;
    }


}
