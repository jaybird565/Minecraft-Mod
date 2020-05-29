package typicals.alchemicalexpansion.gui.guicontainer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.gui.container.PillFurnaceContainer;
import typicals.alchemicalexpansion.tileentity.PillFurnaceTile;

public class PillFurnaceGui extends GuiContainer {

    public static final int WIDTH = 176;
    public static final int HEIGHT = 195;

    private IInventory furnaceInv;
    private InventoryPlayer playerInv;

    private static final ResourceLocation background = new ResourceLocation(AlchemicalExpansion.MODID, "textures/gui/pill_furnace_basic_gui.png");

    public PillFurnaceGui(InventoryPlayer playerInv, IInventory furnaceInv) {
        super(new PillFurnaceContainer(playerInv, furnaceInv));
        this.furnaceInv = furnaceInv;
        this.playerInv = playerInv;
        this.xSize = WIDTH;
        this.ySize = HEIGHT;
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(background);
        //guiLeft = (screen width - gui window width) / 2
        //guiRight = (screen height - gui window height) / 2
        //initialized in GuiContainer.this.initGui()
        //draws the background image from 0,0 to xSize, ySize on screen starting at guiLeft, guiTop
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        PillFurnaceTile furnaceTile = (PillFurnaceTile) this.furnaceInv;
        if(furnaceTile.isBurning()) {
            drawTexturedModalRect(guiLeft + 39, guiTop + 68, 176, (int)(14 * (1 - furnaceTile.percentBurned())), 189, 14);
        }
        drawTexturedModalRect(guiLeft + 79, guiTop + 29, 176, 15, 176 + (int)(24 * furnaceTile.percentCooked()), 31);


    }


}
