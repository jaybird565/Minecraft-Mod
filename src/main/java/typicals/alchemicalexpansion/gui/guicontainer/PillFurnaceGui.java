package typicals.alchemicalexpansion.gui.guicontainer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.gui.container.PillFurnace;

public class PillFurnaceGui extends GuiContainer {

    public static final int WIDTH = 176;
    public static final int HEIGHT = 195;

    private static final ResourceLocation background = new ResourceLocation(AlchemicalExpansion.MODID, "textures/gui/pill_furnace_basic_gui.png");

    public PillFurnaceGui(PillFurnace container) {
        super(container);
        this.xSize = WIDTH;
        this.ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        //guiLeft = (screen width - gui window width) / 2
        //guiRight = (screen height - gui window height) / 2
        //initialized in GuiContainer.this.initGui()
        //draws the background image from 0,0 to xSize, ySize on screen starting at guiLeft, guiTop
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
