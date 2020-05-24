package typicals.alchemicalexpansion.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
