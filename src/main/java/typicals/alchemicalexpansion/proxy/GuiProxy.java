package typicals.alchemicalexpansion.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import typicals.alchemicalexpansion.common.Blocks.Testing.PillFurnace;
import typicals.alchemicalexpansion.common.Blocks.Testing.PillFurnaceGui;
import typicals.alchemicalexpansion.common.Blocks.Testing.PillFurnaceTileEntity;

public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof PillFurnaceTileEntity) {
            return new PillFurnace(player.inventory, (PillFurnaceTileEntity) tileEntity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof PillFurnaceTileEntity) {
            PillFurnaceTileEntity containerTileEntity = (PillFurnaceTileEntity) tileEntity;
            return new PillFurnaceGui(containerTileEntity, new PillFurnace(player.inventory, containerTileEntity));
        }
        return null;
        }
}

