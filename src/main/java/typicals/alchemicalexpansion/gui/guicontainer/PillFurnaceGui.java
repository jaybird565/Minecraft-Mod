package typicals.alchemicalexpansion.gui.guicontainer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.gui.container.PillFurnace;

public class PillFurnaceGui extends GuiContainer {

    public static final int WIDTH = width();
    public static final int HEIGHT = height();

    private static final ResourceLocation background = new ResourceLocation(AlchemicalExpansion.MODID, "textures/gui/pill_furnace.png");

    public PillFurnaceGui(PillFurnace container) {
        super(container);
        this.xSize = WIDTH;
        this.ySize = HEIGHT;
    }

    private static int width() {
        int slotSize = PillFurnace.slotSpacing + PillFurnace.slotWidth;
        return PillFurnace.startX + (9 * slotSize) + PillFurnace.startX;
    }

    private static int height() {
        int slotSize = PillFurnace.slotSpacing + PillFurnace.slotWidth;
        int startY = PillFurnace.startY;
        int inventorySpacing = PillFurnace.inventorySpacing;
        return startY + (slotSize * 5) + inventorySpacing + slotSize * 3 + inventorySpacing + slotSize + startY;
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
