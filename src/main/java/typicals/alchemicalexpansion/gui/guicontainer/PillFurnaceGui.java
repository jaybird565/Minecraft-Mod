package typicals.alchemicalexpansion.gui.guicontainer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.gui.container.PillFurnace;

public class PillFurnaceGui extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(AlchemicalExpansion.MODID, "textures/gui/pill_furnace.png");

    public PillFurnaceGui(PillFurnace container) {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
