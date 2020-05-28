package typicals.alchemicalexpansion.util.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import typicals.alchemicalexpansion.item.crafting.Machines.PillFurnaceRecipe;

import java.util.Map;

public class PillFurnaceParser{

    public static boolean parseJson (JsonElement json){
        json.getAsJsonArray();
        //TODO port the below code over to RecipeParser to do this automatically based on recipe json object
        for(JsonElement obj : json.getAsJsonArray()){
            JsonObject type = obj.getAsJsonObject();
            JsonArray inputs = type.get("inputs").getAsJsonArray();

            PillFurnaceRecipe recipe = new PillFurnaceRecipe();
            Item[] items = new Item[inputs.size()];

            for(int i = 0; i < inputs.size(); i++){
                items[i] = itemFromString(inputs.get(i).getAsString());
            }

            recipe.inputs = items;



        }
        return true;
    }

    public static Item itemFromString (String item){
        //TODO
        return null;
    }

}
