package typicals.alchemicalexpansion.util.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import typicals.alchemicalexpansion.item.crafting.Machines.MachineRecipe;
import typicals.alchemicalexpansion.item.crafting.Machines.PillFurnaceRecipe;

import static typicals.alchemicalexpansion.util.parser.PillFurnaceParser.itemFromString;

public class RecipeParser {

    //takes in a tag and gets all the items from that tag
    public static ItemStack[] getItems(String tag, JsonElement json){
        JsonObject type = json.getAsJsonObject();
        JsonArray tagContents = type.get(tag).getAsJsonArray();

        ItemStack[] items = new ItemStack[tagContents.size()];

        for(int i = 0; i < tagContents.size(); i++){
            items[i] = itemFromString(tagContents.get(i).getAsString());
        }


        return items;
    }

    public static ItemStack itemFromString (String item){
        return new ItemStack(Item.getByNameOrId(item));
    }
}

