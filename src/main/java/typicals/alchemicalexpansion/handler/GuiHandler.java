package typicals.alchemicalexpansion.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import typicals.alchemicalexpansion.block.PillFurnaceBlock;
import typicals.alchemicalexpansion.gui.container.PillFurnaceContainer;
import typicals.alchemicalexpansion.gui.guicontainer.PillFurnaceGui;
import typicals.alchemicalexpansion.tileentity.InventoryTile;
import typicals.alchemicalexpansion.util.TileEntityUtil;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch(ID) {
            case PillFurnaceBlock.GUI_ID:
                return new PillFurnaceContainer(player.inventory, (InventoryTile) TileEntityUtil.getTileEntity(world, x, y, z));

            default: return null;
        }
    }

    @Override
    public PillFurnaceGui getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch(ID) {
            case PillFurnaceBlock.GUI_ID:
                return new PillFurnaceGui(player.inventory, (InventoryTile) TileEntityUtil.getTileEntity(world, x, y, z));

            default: return null;
        }
    }
}

