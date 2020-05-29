package typicals.alchemicalexpansion.gui.guicontainer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import typicals.alchemicalexpansion.AlchemicalExpansion;
import typicals.alchemicalexpansion.gui.container.PillFurnaceContainer;
import typicals.alchemicalexpansion.tileentity.PillFurnaceTile;

public class PillFurnaceGui extends GuiContainer {

    public static final int WIDTH = 176;
    public static final int HEIGHT = 195;

    private PillFurnaceTile furnaceInv;
    private InventoryPlayer playerInv;

    private static final ResourceLocation background = new ResourceLocation(AlchemicalExpansion.MODID, "textures/gui/pill_furnace_basic_gui.png");

    public PillFurnaceGui(InventoryPlayer playerInv, IInventory furnaceInv) {
        super(new PillFurnaceContainer(playerInv, furnaceInv));
        this.furnaceInv = (PillFurnaceTile) furnaceInv;
        this.playerInv = playerInv;
        this.xSize = WIDTH;
        this.ySize = HEIGHT;
    }

    @Override
    public void updateScreen() {
        //AlchemicalExpansion.logger.debug("PillFurnaceGui screen update started.");
        super.updateScreen();
        //AlchemicalExpansion.logger.debug("PillFurnaceGui screen update finished.");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        //AlchemicalExpansion.logger.debug("Screen drawing started for PillFurnaceGui ");
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

        //AlchemicalExpansion.logger.debug("Screen drawing finished for PillFurnaceGui ");
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

        PillFurnaceTile furnaceTile = this.furnaceInv;

        int burnTime = furnaceTile.getField(0);
        int totalBurnTime = furnaceTile.getField(1);
        double burnPercent = percent(burnTime, totalBurnTime);
        int value = scale(14, 1 - burnPercent);
        drawTexturedModalRect(guiLeft + 39, guiTop + 68 + value, 176, value, 14, 14 - value);

        int cookTime = furnaceTile.getField(2);
        int totalCookTime = furnaceTile.getField(3);
        double cookPercent = percent(cookTime, totalCookTime);
        value =  scale(24, cookPercent);
        if((cookPercent == 0.0) || (cookPercent == 1.0)) {
            value = 0;
        }
        drawTexturedModalRect(guiLeft + 80, guiTop + 30, 176, 14, value, 17);

    }

    public static double percent(int part, int total) {
        double dPart = part;
        double dTotal = total;
        return MathHelper.clamp(( dPart / (dTotal - 1)), 0.0, 1.0);
    }

    public static int scale(int scalar, double value) {
        Double result = Math.ceil(scalar * value);
        return result.intValue();
    }



}
