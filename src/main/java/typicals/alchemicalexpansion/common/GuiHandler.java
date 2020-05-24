package typicals.alchemicalexpansion.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import typicals.alchemicalexpansion.common.blocks.testing.PillFurnaceBlock;
import typicals.alchemicalexpansion.common.container.PillFurnace;
import typicals.alchemicalexpansion.client.gui.PillFurnaceGui;
import typicals.alchemicalexpansion.common.util.TileEntityUtil;

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

