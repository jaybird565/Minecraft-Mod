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
import typicals.alchemicalexpansion.util.ItemUtil;
import typicals.alchemicalexpansion.util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

public class RecipeParser {

    //takes in a tag and gets all the items in that tag
    public static List<ItemStack> getItems(String tag, JsonElement json){
        JsonObject type = json.getAsJsonObject();
        JsonArray tagContents = type.get(tag).getAsJsonArray();

        return parseItems(tagContents);

    }

    public static List<ItemStack> parseItems(JsonArray itemsJson) {
        List<ItemStack> items = new ArrayList<ItemStack>();

        for(int i = 0; i < itemsJson.size(); i++) {
            try {
                items.add(itemFromString(itemsJson.get(i).getAsString()));
            } catch (JsonParseException e){
                LoggerUtil.error("Item could not be parsed", e);
            }
        }
        return ItemUtil.compress(items);

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

