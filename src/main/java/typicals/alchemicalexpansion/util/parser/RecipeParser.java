package typicals.alchemicalexpansion.util.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import typicals.alchemicalexpansion.util.LoggerUtil;

public class RecipeParser {

    //takes in a tag and gets all the items in that tag
    public static ItemStack[] getItems(String tag, JsonElement json){
        JsonObject type = json.getAsJsonObject();
        JsonArray tagContents = type.get(tag).getAsJsonArray();

        return parseItems(tagContents);

    }

    public static ItemStack[] parseItems(JsonArray itemsJson) {
        ItemStack[] items = new ItemStack[itemsJson.size()];

        for(int i = 0; i < itemsJson.size(); i++) {
            try {
                items[i] = itemFromString(itemsJson.get(i).getAsString());
            } catch (JsonParseException e){
                LoggerUtil.error("Item could not be parsed", e);
            }
        }

        return items;
    }

    public static ItemStack itemFromString (String item){
        return itemStackFromString(item);
    }

    public static ItemStack itemStackFromString(String itemStackString) {
        return itemStackFromResourceLocation(new ResourceLocation(itemStackString));
    }

    public static ItemStack itemStackFromResourceLocation(ResourceLocation location) {
        IForgeRegistry<Item> itemRegistry = ForgeRegistries.ITEMS;
        return new ItemStack(itemRegistry.getValue(location));
    }
}

