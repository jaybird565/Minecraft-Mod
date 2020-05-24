package typicals.alchemicalexpansion.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import typicals.alchemicalexpansion.AlchemicalExpansion;

public abstract class ModBlock extends Block {

    protected boolean enabled;

    protected ModBlock(Material materialIn) {
        super(materialIn);
    }

    protected ModBlock(Material materialIn, MapColor MapColorIn) {
        super(materialIn, MapColorIn);
    }

    protected void setBlockName(String name) {
        this.setRegistryName(AlchemicalExpansion.MODID, name);
        this.setUnlocalizedName(AlchemicalExpansion.MODID + "." + name);
    }

    public abstract Item itemBlock();

    public boolean isEnabled() {
        return this.enabled;
    }


}
