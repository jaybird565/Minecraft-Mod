package typicals.alchemicalexpansion.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import typicals.alchemicalexpansion.block.PillFurnaceBlock;
import typicals.alchemicalexpansion.gui.container.PillFurnace;
import typicals.alchemicalexpansion.gui.guicontainer.PillFurnaceGui;
import typicals.alchemicalexpansion.util.TileEntityUtil;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch(ID) {
            case PillFurnaceBlock.GUI_ID:
                return new PillFurnace(player.inventory, TileEntityUtil.getTileEntity(world, x, y, z));

            default: return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch(ID) {
            case PillFurnaceBlock.GUI_ID:
                return new PillFurnaceGui(new PillFurnace(player.inventory, TileEntityUtil.getTileEntity(world, x, y, z)));

            default: return null;
        }
    }
}

