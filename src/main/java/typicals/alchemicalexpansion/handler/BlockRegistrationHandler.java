package typicals.alchemicalexpansion.handler;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import typicals.alchemicalexpansion.block.ModBlock;
import typicals.alchemicalexpansion.block.PillFurnaceBlock;
import typicals.alchemicalexpansion.tileentity.PillFurnaceTile;
import typicals.alchemicalexpansion.util.RegistryUtil;

public class BlockRegistrationHandler extends EventHandler {

    private static boolean blocksRegistered = false;

    public static final ModBlock[] registeredBlocks = {
            new PillFurnaceBlock(true),
            new PillFurnaceBlock(false),
    };

    @SubscribeEvent
    public void onBlockRegistry(RegistryEvent.Register<Block> event) {
        registerBlocks(event.getRegistry());
        registerTileEntities();
        blocksRegistered = true;
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(PillFurnaceTile.class, RegistryUtil.resourceLocation("pill_furnace"));
    }

    private static void registerBlocks(IForgeRegistry<Block> registry) {
        for(ModBlock block : registeredBlocks) {
            block.setRegistryName(RegistryUtil.resourceLocation(block))
                    .setUnlocalizedName(RegistryUtil.unlocalizedName(block));
            registry.register(block);
        }
    }

    public static boolean blocksRegistered() {
        return blocksRegistered;
    }

}
