package typicals.alchemicalexpansion.util.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.ModContainer;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import typicals.alchemicalexpansion.AlchemicalExpansion;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.util.Map;

public class ContentParser {

    private static Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    //parses alchemical expansion content
    public static boolean parseContent(ModContainer mod) {

        return CraftingHelper.findFiles(mod, modContentPath(mod), null, (root, file) -> {

            String fileName = file.getFileName().toString();

            if(mod.getModId().equals(AlchemicalExpansion.MODID)) {
                //taken from https://github.com/CoFH/ThermalExpansion/blob/92b52710c19f3923f81ece2f580b044d8d8111fc/src/main/java/cofh/thermalexpansion/util/parsers/ContentParser.java#L104

                if (!"json".equals(FilenameUtils.getExtension(fileName)) || fileName.startsWith("_")) {
                    return true;
                }
                BufferedReader reader = null;
                try {
                    reader = Files.newBufferedReader(file);
                    JsonObject json = JsonUtils.fromJson(GSON, reader, JsonObject.class);
                    //assume json array or object
                    for (Map.Entry<String, JsonElement> contentEntry : json.entrySet()) {
                        if(parseEntry(contentEntry)) {
                            AlchemicalExpansion.logger.debug("Content entry added from file " + fileName + ": \"" + contentEntry.getKey() + "\"");
                        } else {
                            AlchemicalExpansion.logger.error("Error parsing entry from file " + fileName + ": \"" + contentEntry.getKey() + "\" > Please make sure the entry is valid JSON.");
                        }

                    }
                } catch (Exception e) {
                    AlchemicalExpansion.logger.error("Error parsing content file " + fileName + "!", e);
                } finally {
                    IOUtils.closeQuietly(reader);
                }
                return true;
            } else {
                //no parsing files that are not alchemical expansion
                return false;
            }


        }, false, false);

    }

    public static String assetsPath(ModContainer mod) {
        return "assets/" + mod.getModId() + "/";
    }

    public static String modContentPath(ModContainer mod) {
        return assetsPath(mod) + "content/";
    }

    public static boolean parseEntry(Map.Entry<String, JsonElement> entry) {
        //TODO parse an entry in jay's custom content file(s)
        return true;
    }
}
