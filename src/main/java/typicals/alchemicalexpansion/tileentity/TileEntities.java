package typicals.alchemicalexpansion.tileentity;


import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import typicals.alchemicalexpansion.AlchemicalExpansion;

public class TileEntities {


    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(PillFurnaceTileEntity.class, AlchemicalExpansion.MODID + ":" + "pill_furnace");
    }

}
