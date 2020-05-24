package typicals.alchemicalexpansion.util;

import net.minecraft.util.ResourceLocation;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.block.ModBlock;

public class RegistryUtil {

    public static ResourceLocation resourceLocation(ModBlock block) {
        return resourceLocation(block.getUnlocalizedName());
    }

    public static ResourceLocation resourceLocation(String name) {
        return new ResourceLocation(AlchemicalExpansion.MODID, name);
    }

}
