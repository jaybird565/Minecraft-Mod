package typicals.alchemicalexpansion.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import typicals.alchemicalexpansion.AlchemicalExpansion;

public class RegistryUtil {

    public static void registerTileEntity(Class<TileEntity> clazz, String name) {
        GameRegistry.registerTileEntity(clazz, new ResourceLocation(AlchemicalExpansion.MODID, name));
    }

}
